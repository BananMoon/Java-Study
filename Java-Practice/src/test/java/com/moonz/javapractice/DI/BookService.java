package com.moonz.javapractice.DI;

import com.moonz.javapractice.BookRepository;

public class BookService {
    @Inject
    BookRepository bookRepository;

}
