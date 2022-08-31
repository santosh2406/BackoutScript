package com.codebase.backoutscript.test;

import java.io.File;
import java.sql.SQLException;

import com.codebase.sql.backoutscript.api.BackoutSqlHandler;

public class BackoutScriptTest {

	public static void main(String[] args) {
		File file = new File("C:\\Personal\\SpringBootWorkSpace\\backoutscript\\src\\main\\resources\\DDL_SQLFile.sql");
		
		BackoutSqlHandler handler = new BackoutSqlHandler();
		try {
			handler.backout(file);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
