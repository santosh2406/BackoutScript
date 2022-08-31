package com.codebase.sql.backoutscript.api;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * 
 * @author Santosh
 *
 */
public class SqlParser {

	private static final Logger logger = LogManager.getLogger(BackoutSqlHandler.class);
	
	public String getStatement(BufferedReader in) {
		StringBuffer buf = new StringBuffer();
		String line = null;
		while ((line = getLine(in)) != null) {

			if (line.length() == 0)
				break;

			buf.append(line);
			buf.append("\n");

		}

		if (buf.length() > 0) {
			return buf.toString();
		} else {
			return null;
		}

	}
	
	private String getLine(BufferedReader in){
		String line = null;
		while (true) {
			try {
				line = in.readLine();
			} catch (IOException e) {
				logger.error(e.getMessage(),e);
			}
			if (line != null) {
				line.trim();
			if ((line.length() != 0) && ((line.length() < 2) ||  (line.length() >= 2) && !(line.charAt(0) == '/' && line.charAt(1) == '/')))
				if (((line.length() < 2) || (line.length() >= 2) && !(line.charAt(0) == '/' && line.charAt(1) == '/')))
					break;
			} else {
				break;
			}

		}

		return line;
	}

}
