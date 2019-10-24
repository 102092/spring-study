package com.donghwan.demospring51;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyEventHandler{

	@EventListener
	@Async
	public void onApplicationEvent(MyEvent event) {
		System.out.println(Thread.currentThread().toString());
		System.out.println("이벤트를 받았다. 데이터는 "+ event.getData());
		
	}

	@EventListener
	@Async
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println(Thread.currentThread().toString());
		System.out.println("ContextRefreshedEvent");
		
	}
	
	@EventListener
	@Async
	public void onApplicationEvent(ContextClosedEvent event) {
		System.out.println(Thread.currentThread().toString());
		System.out.println("ContextClosedEvent");
		
	}


}
