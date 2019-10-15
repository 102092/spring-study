package com.donghwan.demospring51;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner{

	@Autowired
	ApplicationContext ctx;
		
	@Autowired
	BookRepository bookRepository;
		
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Environment environment = ctx.getEnvironment();
		//System.out.println(Arrays.toString(environment.getActiveProfiles())); 
		//System.out.println(Arrays.toString(environment.getDefaultProfiles())); //아무런 설정을 하지 않아도 적용이 된다..
		
		System.out.println(environment.getProperty("app.name"));
		System.out.println(environment.getProperty("app.about"));
		
	}

}
