package com.moonz.javapractice.CrackingCodingInterview.ch7.prob7;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class Message {
    private String content;
    private LocalDateTime date;
}
