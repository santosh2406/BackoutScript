package com.codebase.sql.backoutscript.api.sql.statement.builder;

import java.util.ArrayList;
import java.util.List;

import com.codebase.sql.backoutscript.api.sql.bean.DeleteClauseSegmentBean;

/**
 * 
 * @author Santosh
 *
 */
public class DeleteSqlQueryBuilder {

	public static final String DELETE = "DELETE";
	public static final String FROM = "FROM";
	public static final String WHERE = "WHERE";
	public static final String SELECT = "SELECT * FROM";
	public static final String VALUES="VALUES";
	public static final String RENDOMNO="";

	private List<DeleteClauseSegmentBean> insertClauseSegments = new ArrayList<>();
	

	public String getInsertClause() {

		if (insertClauseSegments.isEmpty()) {
			return "";
		}

		StringBuilder selectClause = new StringBuilder(SELECT);
		String delim = " ";

		for (DeleteClauseSegmentBean segment : insertClauseSegments) {
			selectClause.append(delim);
			selectClause.append(segment.toString());
			delim = ",";
		}

		return selectClause.toString();
	}
}
