package me.donghwan.demospringioc.book;

import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private BookRepository bookRepository;

  // 직접만들어서 쓰기. private BookRepository bookRepository = new BookRepository();

  public BookService(BookRepository bookRepository) { // IoC방식.
    this.bookRepository = bookRepository;
  }

  public Book save(Book book) {
    book.setCreated(new Date());
    book.setBookStatus(BookStatus.DRAFT);
    return bookRepository.save(book);
  }

  // 실행히에 콘솔창에 작성됨.
  @PostConstruct
  public void postContruct() {
    System.out.println("========");
    System.out.println("Hello");
  }

}
