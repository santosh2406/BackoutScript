package com.codebase.sql.backoutscript.api.sql.bean;

/**
 * 
 * @author Santosh
 *
 */
public class UpdateClauseSegmentBean {

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

	/*
	 * public String getTableName() { return tableName; }
	 * 
	 * public InsertClauseSegment setTableName(String tableName) { this.tableName =
	 * tableName; return this; }
	 */

	public String getRendomNo() {
		return randomNo;
	}

	public void setRendomNo(String rendomNo) {
		randomNo = rendomNo;
	}

	public String getColumnName() {
		return columnName;
	}

	public UpdateClauseSegmentBean setColumnName(String columnName) {
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
