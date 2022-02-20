package com.moonz.javapractice;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {
    @Autowired
    BookService bookService;

    @Test
    public void DI() {
        Assertions.assertNotNull(bookService);
        Assertions.assertNotNull(bookService.bookRepository);
    }
}