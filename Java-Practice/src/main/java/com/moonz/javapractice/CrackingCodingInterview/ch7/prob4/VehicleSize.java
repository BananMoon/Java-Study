package com.moonz.javapractice.CrackingCodingInterview.ch7.prob4;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum VehicleSize {
    MOTORCYCLE(3),
    COMPACT(5),
    LARGE(10),
    ;
    private int value;
}
