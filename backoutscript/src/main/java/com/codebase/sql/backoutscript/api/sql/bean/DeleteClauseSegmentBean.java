package com.codebase.sql.backoutscript.api.sql.bean;

/**
 * 
 * @author Santosh
 *
 */
public class DeleteClauseSegmentBean {


	private String tableName;
	private String columnName;
	private String whereClause;
	private String randomNo;
	
	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getRandomNo() {
		return randomNo;
	}

	public void setRandomNo(String randomNo) {
		this.randomNo = randomNo;
	}
	
	public String getColumnName() {
		return columnName;
	}

	public DeleteClauseSegmentBean setColumnName(String columnName) {
		this.columnName = columnName;
		return this;
	}
	
	public String toString() {
		return tableName + "." + columnName;
	}

	/**
	 * @return the whereClause
	 */
	public String getWhereClause() {
		return whereClause;
	}

	/**
	 * @param whereClause the whereClause to set
	 */
	public void setWhereClause(String whereClause) {
		this.whereClause = whereClause;
	}


}
