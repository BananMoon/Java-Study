package com.moonz.javapractice.CrackingCodingInterview.ch7.prob7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
public class AddRequest {
    private User fromUser;
    private User toUser;
    private LocalDateTime date;
    private RequestStatus requestStatus;
}
