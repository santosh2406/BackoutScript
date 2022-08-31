package com.codebase.sql.backoutscript.api.sql.statement.builder;

import java.util.ArrayList;
import java.util.List;

import com.codebase.sql.backoutscript.api.sql.bean.UpdateClauseSegmentBean;

/**
 * 
 * @author Santosh
 *
 */
public class UpdateSqlQueryBuilder {
	
	public static final String UPDATE = "UPDATE";
	public static final String FROM = "FROM";
	public static final String WHERE = "WHERE";
	public static final String GROUP_BY = "GROUP BY";
	public static final String ORDER_BY = "ORDER BY";
	public static final String SELECT = "SELECT";
	public static final String VALUES="VALUES";
	public static final String RENDOMNO="";

	private List<UpdateClauseSegmentBean> updateClauseSegments = new ArrayList<>();
	
	
	/*private List<SelectClauseSegment> selectClauseSegments = new ArrayList<>();
	private List<FromClauseSegment> fromClauseSegments = new ArrayList<>();
	private List<WhereClauseSegment> whereClauseSegments = new ArrayList<>();
	private List<GroupBySegment> groupBySegments = new ArrayList<>();
*/

	public String getInsertClause() {

		if (updateClauseSegments.isEmpty()) {
			return "";
		}

		StringBuilder selectClause = new StringBuilder(UPDATE);
		String delim = " ";

		for (UpdateClauseSegmentBean segment : updateClauseSegments) {
			selectClause.append(delim);
			selectClause.append(segment.toString());
			delim = ",";
		}

		return selectClause.toString();
	}

}
