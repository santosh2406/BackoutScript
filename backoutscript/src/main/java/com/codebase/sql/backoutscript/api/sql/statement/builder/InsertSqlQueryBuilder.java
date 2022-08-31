package com.codebase.sql.backoutscript.api.sql.statement.builder;

import java.util.ArrayList;
import java.util.List;

import com.codebase.sql.backoutscript.api.sql.bean.InsertClauseSegmentBean;

/**
 * 
 * @author Santosh
 *
 */
public class InsertSqlQueryBuilder {
	
	public static final String INSERT = "INSERT INTO";
	public static final String FROM = "FROM";
	public static final String WHERE = "WHERE";
	public static final String SELECT = "SELECT * FROM";
	public static final String VALUES="VALUES";
	public static final String RENDOMNO="";

	private List<InsertClauseSegmentBean> insertClauseSegments = new ArrayList<>();

	public String getInsertClause() {

		if (insertClauseSegments.isEmpty()) {
			return "";
		}

		StringBuilder selectClause = new StringBuilder(SELECT);
		String delim = " ";

		for (InsertClauseSegmentBean segment : insertClauseSegments) {
			selectClause.append(delim);
			selectClause.append(segment.toString());
			delim = ",";
		}

		return selectClause.toString();
	}

	public InsertSqlQueryBuilder addInsertSegment(InsertClauseSegmentBean segment) {

		this.insertClauseSegments.add(segment);
		return this;
	}

}
