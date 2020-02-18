package me.donghwan.demospringioc.book;

import org.springframework.stereotype.Repository;

/*
 * bean임.
 * 왜? @Repository 어노테이션이 붙어져있어서, auto scan을 통해 bean으로 등록됨.
 */

@Repository
public class BookRepository {

  public Book save(Book book) {
    return null;
  }
}	
