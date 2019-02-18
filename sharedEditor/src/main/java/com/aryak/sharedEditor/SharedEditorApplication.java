package com.aryak.sharedEditor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import static com.aryak.sharedEditor.constant.Constants.PACKAGE_SCAN;

@SpringBootApplication(scanBasePackages={PACKAGE_SCAN})
@EnableScheduling
public class SharedEditorApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		 SpringApplicationBuilder builder = new SpringApplicationBuilder(SharedEditorApplication.class);
		 builder.headless(false).run(args);
	}
	
	 @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	        return application.headless(false).sources(SharedEditorApplication.class);
	    }

}

