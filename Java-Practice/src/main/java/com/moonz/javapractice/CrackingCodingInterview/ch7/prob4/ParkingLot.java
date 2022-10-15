package com.moonz.javapractice.CrackingCodingInterview.ch7.prob4;

public class ParkingLot {
    private Level[] levels;
    private final static int NUM_LEVELS = 5;

    public ParkingLot() {
        levels = new Level[NUM_LEVELS];
        for (int i=0; i<NUM_LEVELS; i++) {
            levels[i] = new Level(i, 30);
        }
    }
    /*
    주차 하면 true, 주차 못하면 false
     */
    public boolean parkVehicle (Vehicle vehicle) {
        for (int i=0; i<levels.length; i++) {
            Level level = levels[i]
        }

    }
}
