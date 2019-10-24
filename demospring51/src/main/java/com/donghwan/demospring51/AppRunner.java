package com.donghwan.demospring51;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class AppRunner implements ApplicationRunner {

	@Autowired
	ApplicationEventPublisher publishEvent;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		publishEvent.publishEvent(new MyEvent(this, 100));

	}
}
