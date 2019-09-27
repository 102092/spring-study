package me.donghwan.demospringioc.book;

import java.util.Date;

/*
 *  book은 bean이 아니다.
 *  왜? 스프링 ioc컨테이너가 관리하지 않으니까.
 */

public class Book {

	private Date created;

	private BookStatus bookStatus;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

}
