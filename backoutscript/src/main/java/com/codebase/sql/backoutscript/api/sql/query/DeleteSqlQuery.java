package com.codebase.sql.backoutscript.api.sql.query;


import java.util.HashMap;
import java.util.List;

import com.codebase.sql.backoutscript.api.sql.bean.DeleteClauseSegmentBean;
import com.codebase.sql.backoutscript.api.sql.statement.builder.DeleteSqlQueryBuilder;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.insert.Insert;
/**
 * 
 * @author Santosh
 *
 */
public class DeleteSqlQuery extends DeleteSqlQueryBuilder{
	
	private DeleteClauseSegmentBean obj = new DeleteClauseSegmentBean();
	private Object[] values;
	private Object tableColumns[];

	public String getDeletedClause(Insert insertStmt) throws JSQLParserException {
		StringBuilder deleteClause = null;
		if (insertStmt.getSelect() == null) {
			List<Column> insertObj = insertStmt.getColumns(); // Check Iteration Order
			ItemsList valuesList = insertStmt.getItemsList();

			List<Expression> list = ((ExpressionList) valuesList).getExpressions();
			values = list.toArray();
			tableColumns = insertObj.toArray();

			HashMap<Object, Object> map = new HashMap<>();
			String appendAdd = null;

			for (int i = 0; i < tableColumns.length; i++) {

				map.put(tableColumns[i], values[i].toString() + " and");
			}

			String string = map.toString();
			String finalValues = string.substring(1, string.length() - 4).replaceAll("and,", "and").trim();

			deleteClause = new StringBuilder(DELETE);
			String delim = " ";
			deleteClause.append(delim);
			deleteClause.append(FROM);
			deleteClause.append(delim);
			deleteClause.append(insertStmt.getTable());
			deleteClause.append(delim);
			deleteClause.append(delim);
			deleteClause.append(WHERE);
			deleteClause.append(delim);
			deleteClause.append(finalValues);
			deleteClause.append(";");
			return deleteClause.toString();
		} else {
			deleteClause = new StringBuilder(DELETE);
			String delim = " ";
			deleteClause.append(delim);
			deleteClause.append(FROM);
			deleteClause.append(delim);
			deleteClause.append(insertStmt.getTable());
			deleteClause.append(delim);
			String selectStmt = insertStmt.getSelect().toString();
			selectStmt = selectStmt.replace("NOT EXISTS", "EXISTS");
			deleteClause.append(selectStmt);
			deleteClause.append(";");
			return deleteClause.toString();

		}
	}
}
