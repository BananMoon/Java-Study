package com.moonz.thejava.DI;

import com.moonz.thejava.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ContainerServiceTest {
    @Test   // @Inject 없이
    public void getObject_BookRepository() {
        BookRepository bookRepository = ContainerService.getObject(BookRepository.class);   // Class<T>를 넣으면 인스턴스 T를 리턴한다.

        Assertions.assertNotNull(bookRepository);
    }
    @Test
    public void getObject_BookService() {
        BookService bookService = ContainerService.getObject(BookService.class);   // Class<T>를 넣으면 인스턴스 T를 리턴한다.

        Assertions.assertNotNull(bookService);
        Assertions.assertNotNull(bookService.bookRepository);

    }
}