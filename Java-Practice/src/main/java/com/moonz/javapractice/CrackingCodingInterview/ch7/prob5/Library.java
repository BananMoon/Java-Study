package com.moonz.javapractice.CrackingCodingInterview.ch7.prob5;

import java.util.Map;

/*
 책을 갖고 있는 서점
 */
public class Library {
    private Map<Long, Book> books;

    public Book addBook (long bookId, String details) {
        if (books.containsKey(bookId)) {
            return null;
        }
        Book book = new Book(bookId, details);
        books.put(bookId, book);
        return book;
    }

    public boolean removeById(long id) {
        if (!books.containsKey(id)) {
            return false;
        }
        books.remove(id);
        return true;
    }
    public boolean remove (Book book) {
        return removeById(book.getBookId());
    }
    public Book findById (long id) {
        return books.get(id);
    }
}
