package com.donghwan.demospring51;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired //@Qualifier("dongBookRepository")
	List<BookRepository> bookRepository; // bookRepository 인스턴스는 생성이 가능하나,
	
//	@Autowired //생성자를 통한 의존성 주입 
//	public BookService(BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}
	
//	@Autowired(required = false) //set을 이용해서 주입받는,optional하게 해당하는 것이 없을시 생성하지 않도록.
//	public void setBookRepository(BookRepository bookRepository) {
//		this.bookRepository = bookRepository;
//	}
	
	public void printBookRepository() {
		//System.out.println(bookRepository.getClass());
		this.bookRepository.forEach(System.out::println);
	}
	
	@PostConstruct
	public void setup() {
		System.out.println(bookRepository.getClass());
	}
	
}
