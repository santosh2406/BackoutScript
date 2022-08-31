package com.codebase.sql.backoutscript.api.sql.query;

import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.create.view.CreateView;

/**
 * 
 * @author Santosh
 *
 */
public class DropSqlQuery {

	public static final String UPDATE = "UPDATE";
	public static final String DROP = "DROP";
	public static final String TABLE = "TABLE";
	public static final String RANDOMNOGEN = "";
	public static final String SPACEDELIMITER = " ";

	public String getDropClause(CreateTable createStmt) {
		StringBuilder dropClause = new StringBuilder(DROP);
		dropClause.append(SPACEDELIMITER);
		dropClause.append(TABLE);
		dropClause.append(SPACEDELIMITER);
		dropClause.append(createStmt.getTable());
		return dropClause.toString();
	}

	/**
	 * @param createStmt
	 * @return
	 */
	public String getDropClause(CreateView createStmt) {
		// DROP TABLE persons;
		StringBuilder dropClause = new StringBuilder(DROP);
		dropClause.append(SPACEDELIMITER);
		dropClause.append("View");
		dropClause.append(SPACEDELIMITER);
		dropClause.append(createStmt.getView());
		return dropClause.toString();
	}

	public String getDropClause(String createStmt) {
		StringBuilder dropClause = new StringBuilder(DROP);
		dropClause.append(SPACEDELIMITER);
		dropClause.append("synonym");
		dropClause.append(SPACEDELIMITER);
		dropClause.append(createStmt);
		return dropClause.toString();
	}

	public String getDropIndexClause(String createStmt) {
		StringBuilder dropClause = new StringBuilder(DROP);
		dropClause.append(SPACEDELIMITER);
		dropClause.append("index");
		dropClause.append(SPACEDELIMITER);
		dropClause.append(createStmt);
		return dropClause.toString();
	}
}
