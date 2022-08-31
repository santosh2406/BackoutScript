package com.codebase.sql.backoutscript;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import com.codebase.sql.backoutscript.property.FileStorageProperties;
/**
 * 
 * @author Santosh
 *
 */
@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class BackoutScriptApplication extends SpringBootServletInitializer {
	
	
	 @Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(BackoutScriptApplication.class);
	   }

	public static void main(String[] args) {
		SpringApplication.run(BackoutScriptApplication.class, args);
		
	}

}
