package me.donghwan.demosprinioc.book;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import me.donghwan.demospringioc.book.Book;
import me.donghwan.demospringioc.book.BookRepository;
import me.donghwan.demospringioc.book.BookService;
import me.donghwan.demospringioc.book.BookStatus;

@RunWith(SpringRunner.class)
public class BookServiceTest {
	
	@Mock
	BookRepository bookRepository;  //가짜 객체 , 만들어주지 않으면 null체크를 통과할 수 없음.

	@Test
	public void save() {
		Book book = new Book();
		
		when(bookRepository.save(book)).thenReturn(book); // 동일한 인스턴스 리턴해줄것 
		//BookRepository bookRepository = new BookRepository();
		BookService bookService = new BookService(bookRepository);

		Book result = bookService.save(book);
		assertThat(book.getCreated()).isNotNull();
		assertThat(book.getBookStatus()).isEqualTo(BookStatus.DRAFT);
		assertThat(result).isNotNull();

	}

}
