package com.codebase.sql.backoutscript.api.sql.query;

import java.util.List;

import com.codebase.sql.backoutscript.api.sql.bean.UpdateClauseSegmentBean;
import com.codebase.sql.backoutscript.api.sql.statement.builder.UpdateSqlQueryBuilder;

import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.relational.ComparisonOperator;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.update.Update;

/**
 * 
 * @author Santosh
 *
 */
public class UpdateSqlQuery extends UpdateSqlQueryBuilder {

	// UpdateSqlQueryBean insert=new UpdateSqlQueryBean();
	UpdateClauseSegmentBean obj = new UpdateClauseSegmentBean();

	public String getUpdateClause(Update updateStmt) throws JSQLParserException {

		if (updateStmt != null && updateStmt instanceof Update) {
			List<Column> updateColm = updateStmt.getColumns();
		}
		String appendTable1Column = appendTable1Column(updateStmt);
		appendTable1Column = removeLastCharacter(appendTable1Column);
		String appendTable2Column = appendTable2Column(updateStmt);
		appendTable2Column = removeLastCharacter(appendTable2Column);

		StringBuilder updateClause = new StringBuilder(UPDATE);
		String delim = " ";
		String table = updateStmt.getTable().toString();
		table = removeFirstAndLastCharacter(table);
		updateClause.append(delim);
		// updateClause.append(DE3PTEMP);
		updateClause.append(table);
		updateClause.append(delim);

		updateClause.append("a");
		updateClause.append(delim);

		updateClause.append("SET");
		updateClause.append("(");
		updateClause.append(appendTable1Column);
		updateClause.append(")=(");
		updateClause.append(SELECT);
		updateClause.append(delim);
		updateClause.append(appendTable2Column);
		updateClause.append(" ");
		updateClause.append(FROM);
		updateClause.append(delim);
		// updateClause.append(DE3PTEMP);

		String findSchemaname = updateStmt.getTable().getSchemaName();
		String findTempTableName = updateStmt.getTable().toString();
		findTempTableName = findTempTableName.replaceAll(findSchemaname + ".", findSchemaname + ".TEMP_");
		updateClause.append(findTempTableName);
		updateClause.append(" ");
		if(findTempTableName!=null && table!=null )
		{
			boolean missMatchingTableName=findTempTableName.equals(findTempTableName);
			//if(missMatchingTableName)
			//System.out.println("******** Warnning :"+"Temp table mismatching  issue, Please verifies ");
		}

		updateClause.append("b");
		updateClause.append(delim);
		updateClause.append(WHERE);
		updateClause.append(delim);
		// updateClause.append("a.");

		String whereClause = updateStmt.getWhere().toString();

		Expression expr = CCJSqlParserUtil.parseCondExpression(whereClause);
		final String leftExpression = new String();
		final String rightExpression = new String();

		expr.accept(new ExpressionVisitorAdapter() {

			@Override
			protected void visitBinaryExpression(BinaryExpression expr) {
				if (expr instanceof ComparisonOperator) {
					updateClause.append("a.");
					updateClause.append(expr.getLeftExpression());
					updateClause.append("=");
					updateClause.append("b.");
					updateClause.append(expr.getLeftExpression());
					updateClause.append(" ");

					updateClause.append("AND");
					updateClause.append(" ");

					// updateClause.append(rightExpression);
				}

				super.visitBinaryExpression(expr);
			}
		});
		// updateClause.append("=");
		// updateClause.append("b.");

		Expression expr1 = CCJSqlParserUtil.parseCondExpression(whereClause);
		final String leftExpression1 = new String();
		final String rightExpression1 = new String();

		// expr.accept(new ExpressionVisitorAdapter() {
		//
		// @Override
		// protected void visitBinaryExpression(BinaryExpression expr) {
		// if (expr instanceof ComparisonOperator) {
		// updateClause.append("b.");
		// updateClause.append(expr.getLeftExpression());
		// // updateClause.append(rightExpression);
		// }
		//
		// super.visitBinaryExpression(expr);
		// }
		// });
		updateClause.append(" ");

		// updateClause.append("AND");
		// updateClause.append(" ");
		updateClause.append(updateStmt.getWhere());
		updateClause.append(")");

		updateClause.append(delim);

		updateClause.append(WHERE);
		updateClause.append(delim);

		updateClause.append("EXISTS (");
		updateClause.append(SELECT);
		updateClause.append(delim);
		updateClause.append("1");
		updateClause.append(delim);
		updateClause.append(FROM);
		updateClause.append(delim);
		// updateClause.append(DE3PTEMP);

		String findSchemaname1 = updateStmt.getTable().getSchemaName();
		String findTableName1 = updateStmt.getTable().toString();
		findTableName1 = findTableName1.replaceAll(findSchemaname1 + ".", findSchemaname1 + ".TEMP_");

		updateClause.append(findTableName1);
		updateClause.append(delim);
		updateClause.append("b");
		updateClause.append(delim);
		updateClause.append(WHERE);

		updateClause.append(delim);
		// updateClause.append("a.");

		final String leftExpression2 = new String();
		final String rightExpression2 = new String();

		expr.accept(new ExpressionVisitorAdapter() {

			@Override
			protected void visitBinaryExpression(BinaryExpression expr) {
				if (expr instanceof ComparisonOperator) {
					updateClause.append("a.");
					updateClause.append(expr.getLeftExpression());
					updateClause.append(expr.getStringExpression());
					updateClause.append("b.");
					updateClause.append(expr.getLeftExpression());
					updateClause.append(" ");

					updateClause.append("AND");
					updateClause.append(" ");

					// updateClause.append(rightExpression);
				}

				super.visitBinaryExpression(expr);
			}
		});
		// updateClause.append("=");
		// updateClause.append("b.");

		Expression expr2 = CCJSqlParserUtil.parseCondExpression(whereClause);
		final String leftExpression3 = new String();
		final String rightExpression3 = new String();

		// expr.accept(new ExpressionVisitorAdapter() {
		//
		// @Override
		// protected void visitBinaryExpression(BinaryExpression expr) {
		// if (expr instanceof ComparisonOperator) {
		// updateClause.append("b.");
		// updateClause.append(expr.getLeftExpression());
		// // updateClause.append(rightExpression);
		// }
		//
		// super.visitBinaryExpression(expr);
		// }
		// });
		updateClause.append(" ");

		// updateClause.append("AND");
		// updateClause.append(" ");

		updateClause.append(updateStmt.getWhere());
		updateClause.append(")");

		updateClause.append(";");
		// delim = ",";

		return updateClause.toString();
	}

	// QUEUE_ID = '2360'

	public static String appendWhereClause(Update updateStmt) {

		Expression where = updateStmt.getWhere();
		System.out.println(where.toString());

		StringBuilder whereClause = new StringBuilder(WHERE);
		String delim = " ";

		whereClause.append(delim);
		return whereClause.toString();
	}

	// AUTO_DIST_METHOD
	// UPDT_BY
	// UPDT_DT
	// BP_ID
	// CHANGE_SEQUENCE_ID
	public static String appendTable1Column(Update updateStmt) {
		List<Column> updateColm = updateStmt.getColumns();
		StringBuilder appendColumnClause = new StringBuilder();
		String equalDelim = "=";
		String commalDelim = ",";

		String alias1 = "a.";
		String alias2 = "b.";

		for (Column colum : updateColm) {
			appendColumnClause.append(alias1);
			appendColumnClause.append(colum.toString());
			appendColumnClause.append(commalDelim);

		}
		return appendColumnClause.toString();
	}

	public static String appendTable2Column(Update updateStmt) {
		List<Column> updateColm = updateStmt.getColumns();
		StringBuilder appendColumnClause = new StringBuilder();
		String equalDelim = "=";
		String commalDelim = ",";
		String alias2 = "b.";

		for (Column colum : updateColm) {
			appendColumnClause.append(alias2);
			appendColumnClause.append(colum.toString());
			appendColumnClause.append(commalDelim);

		}
		return appendColumnClause.toString();
	}

	public static String removeLastCharacter(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str.substring(0, str.length() - 1);
		}
		return result;
	}

	public static String removeFirstAndLastCharacter(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str.substring(0, str.length());
		}
		return result;
	}

	public enum Connector {

		AND("AND"), OR("OR");

		private String value = "";

		Connector(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}
	}

	public enum Operator {

		EQUAL("="), NOT_EQUAL("!="), IN("IN"), IS_NULL("IS NULL"), IS_NOT_NULL("IS NOT NULL");

		private String value = "";

		Operator(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}
	}

	public enum Join {

		INNER_JOIN("INNER JOIN"), LEFT_OUTER_JOIN("LEFT OUTER JOIN");

		private String value = "";

		Join(String value) {
			this.value = value;
		}

		public String value() {
			return this.value;
		}
	}

}
