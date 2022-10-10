package com.moonz.javapractice.CrackingCodingInterview.ch7.prob5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/*
대체적으로 데이터 저장 용도로 사용된다.
 */
@Getter
@Setter
@AllArgsConstructor
public class User {
    private Long userId;
    private String details;
    private int accountType;
}

@Getter
@Setter
@AllArgsConstructor
class Book {
    private Long bookId;
    private String details;
}