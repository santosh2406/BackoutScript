package com.codebase.sql.backoutscript.api.sql.query;

import com.codebase.sql.backoutscript.api.sql.bean.InsertClauseSegmentBean;
import com.codebase.sql.backoutscript.api.sql.statement.builder.InsertSqlQueryBuilder;

import net.sf.jsqlparser.statement.delete.Delete;

/**
 * 
 * @author Santosh
 *
 */
public class InsertSqlQuery extends InsertSqlQueryBuilder {

	InsertClauseSegmentBean obj = new InsertClauseSegmentBean();

	public String getInsertClause(Delete deleteStmt) {

		StringBuilder insertClause = new StringBuilder(INSERT);
		String delim = " ";
		insertClause.append(delim);
		insertClause.append(deleteStmt.getTable());
		insertClause.append(delim);
		insertClause.append(SELECT);
		insertClause.append(delim);
		
		String findSchemaname=deleteStmt.getTable().getSchemaName();
		String findTableName=deleteStmt.getTable().toString();
		findTableName=	findTableName.replaceAll(findSchemaname+".", findSchemaname+".TEMP_");
		insertClause.append(findTableName);
		insertClause.append(delim);
		insertClause.append(WHERE);
		insertClause.append(delim);
		insertClause.append(deleteStmt.getWhere());
		insertClause.append(";");
		
		return insertClause.toString();
	}
	
}
