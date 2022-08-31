package com.codebase.sql.backoutscript.api;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.codebase.sql.backoutscript.api.sql.query.AlterSqlQuery;
import com.codebase.sql.backoutscript.api.sql.query.DeleteSqlQuery;
import com.codebase.sql.backoutscript.api.sql.query.DropSqlQuery;
import com.codebase.sql.backoutscript.api.sql.query.InsertSqlQuery;
import com.codebase.sql.backoutscript.api.sql.query.UpdateSqlQuery;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.parser.CCJSqlParserManager;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.create.index.CreateIndex;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.CreateView;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.merge.Merge;
import net.sf.jsqlparser.statement.update.Update;

/**
 * 
 * @author Santosh
 *
 */
public class BackoutSqlHandler {

	private static final Logger logger = LogManager.getLogger(BackoutSqlHandler.class);
	
	public BackoutSqlHandler() {
	}

	public StringBuilder backout(File file) throws SQLException {
		logger.info("backout begin");
		StringBuilder reverseSql = new StringBuilder();
		try {
			
			FileReader fr = new FileReader(file);
			StringBuilder sb = new StringBuilder();
			
			BufferedReader br = new BufferedReader(fr);
			SqlParser sqlParser = new SqlParser();
			String statement = sqlParser.getStatement(br);
			sb.append(statement);
			br.close();
			String[] inst = sb.toString().split(";");
			CCJSqlParserManager parserManager = new CCJSqlParserManager();
			UpdateSqlQuery updateSql = new UpdateSqlQuery();
			for (int i = inst.length - 1; i >= 0; i--) {
				if (inst[i] != null && !inst[i].trim().equals("")) {
					try {
						Statement parsedStm = parserManager.parse(new StringReader(inst[i]));
						if (parsedStm != null && parsedStm instanceof Update) {
							Update updateStmt = (Update) parsedStm;
							String updatedStmt = updateSql.getUpdateClause(updateStmt);
							reverseSql.append(updatedStmt).append("\n");
							logger.info(updatedStmt);
						} else if (parsedStm != null && parsedStm instanceof Delete) {
							Delete deleteStmt = (Delete) parsedStm;// While parsing Instance should be of matching
																	// statements like Delete,
							InsertSqlQuery insertStmt = new InsertSqlQuery();
							String insertedStmt = insertStmt.getInsertClause(deleteStmt);
							reverseSql.append(insertedStmt).append("\n");
							logger.info(insertedStmt);
						} else if (parsedStm != null && parsedStm instanceof Insert) {
							Insert insertStmt = (Insert) parsedStm;
							DeleteSqlQuery delSql = new DeleteSqlQuery();
							String deletedStmt = delSql.getDeletedClause(insertStmt);
							reverseSql.append(deletedStmt).append("\n");
							logger.info(deletedStmt);
						} else if (parsedStm != null && parsedStm instanceof Merge) {
							logger.info(inst[i]);
							reverseSql.append(inst[i]).append("\n");
						} else if (parsedStm != null && parsedStm instanceof CreateTable) {
							CreateTable createStmt = (CreateTable) parsedStm;
							DropSqlQuery dropSql = new DropSqlQuery();
							String dropStmt = dropSql.getDropClause(createStmt);
							reverseSql.append(dropStmt).append("\n");
							logger.info(dropStmt);
						} else if (parsedStm != null && parsedStm instanceof CreateView) {

							CreateView createStmt = (CreateView) parsedStm;
							DropSqlQuery dropSql = new DropSqlQuery();
							String dropStmt = dropSql.getDropClause(createStmt);
							reverseSql.append(dropStmt).append("\n");
							logger.info(dropStmt);
						} else if (parsedStm != null && parsedStm instanceof Alter) {

							Alter alterStmt = (Alter) parsedStm;
							AlterSqlQuery alterSql = new AlterSqlQuery();
							String alterStmts = alterSql.getAlterClause(alterStmt);
							reverseSql.append(alterStmts).append("\n");
							logger.info(alterStmts);
						}
					} catch (JSQLParserException e) {
						logger.error(e.getMessage(), e);
					}
					
				}
			}
			
			logger.info("backout end");
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return reverseSql;
	}

}
