package com.codebase.sql.backoutscript.api.sql.query;

import java.util.List;

import net.sf.jsqlparser.statement.alter.Alter;
import net.sf.jsqlparser.statement.alter.AlterExpression;
import net.sf.jsqlparser.statement.alter.AlterExpression.ColumnDataType;

/**
 * 
 * @author Santosh
 *
 */
public class AlterSqlQuery implements SqlQueryConstant{
	/**
	 * @param alterStmt
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public String getAlterClause(Alter alterStmt) {
		StringBuilder alterClause = new StringBuilder(ALTER);
		StringBuilder updateClause = new StringBuilder("UPDATE");
		List<AlterExpression> alterExp = alterStmt.getAlterExpressions();
		String word;
		for (AlterExpression obj : alterExp) {
			String operation = obj.getOperation().toString();

			if (operation.equals("ADD")) {
				// System.out.println("Add " + obj.getOperation());
				List<ColumnDataType> columnName = obj.getColDataTypeList();
				int size = columnName.size();
				alterClause.append(" TABLE ");
				alterClause.append(alterStmt.getTable());
				alterClause.append(" DROP COLUMN ");
				alterClause.append("(");

				updateClause.append(" ");
				updateClause.append(alterStmt.getTable());
				updateClause.append(" set ");

				for (int i = 0; i <= size - 1; i++) {
					String column = columnName.get(i).toString();
					int indexPosition = column.indexOf(' ');
					word = column.substring(0, indexPosition);
					alterClause.append(word + ",");
					updateClause.append(word);
					updateClause.append("=NULL,");

					// "ALTER TABLE table_name DROP COLUMN column_name"
				}
				// alterClause.append(")" + ";");
				// updateClause.append(";");

				String removedlastChar = removeLastCharacter(updateClause.toString());

				String removedAlterlastChar = removeLastCharacter(alterClause.toString());

				System.out.println(removedlastChar + ")" + ";");

				System.out.println(removedAlterlastChar + ")" + ";");

			} else if (operation.equals("MODIFY")) {
				System.out.println("MODIFY " + obj.getOperation());
				// ALTER TABLE Product MODIFY COLUMN column_name data type;
			}
		}

		return alterStmt.getTable().toString();
	}

	private String removeLastCharacter(String str) {
		String result = null;
		if ((str != null) && (str.length() > 0)) {
			result = str.substring(0, str.length() - 1);
		}
		return result;
	}
	
}
