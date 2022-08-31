package com.codebase.sql.backoutscript.api.sql.bean;

/**
 * 
 * @author Santosh
 *
 */
public class DropClauseSegmentBean {

	public class DropClauseSegment
	{
		private String tableName;
		private String schemaName;
		private String randomNumberGenerator;
			
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public String getSchemaName() {
			return schemaName;
		}
		public void setSchemaName(String schemaName) {
			this.schemaName = schemaName;
		}
		public String getRandomNumberGenerator() {
			return randomNumberGenerator;
		}
		public void setRandomNumberGenerator(String randomNumberGenerator) {
			this.randomNumberGenerator = randomNumberGenerator;
		}
		
	}
}
