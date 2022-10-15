package com.moonz.javapractice.CrackingCodingInterview.ch7.prob4;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
/* 탈 것 클래스
- vehicle이 갖고 있는 주차 공간 List<ParkingSpot>
- 필요한 주차공간 갯수
- 차 크기 (VehicleSize)
- 차량 주차 / 차량 주차 빼기 / 차량이 주차할 수 있는 공간인지 ParkingSpot 크기 비교
 */
public abstract class Vehicle {
    protected List<ParkingSpot> parkingSpots = new ArrayList<>();   // Vehicle의 주차 공간 리스트???
    // protected String licensePlate;   // 어디서 쓰이지?
    @Getter
    private int spotsNeeded;      // 필요한 주차공간
    @Getter
    protected VehicleSize size;

    /* 주어진 주차공간 ParkingSpot 에 차량 주차 */
    public void parkInSpot(ParkingSpot spot) {
        parkingSpots.add(spot);
    }

    /* 차를 빼고, 해당 공간에 차가 비어있다고 알려준다. */
    public void clearSpots() {
        for (int i=0; i<parkingSpots.size(); i++) {
            parkingSpots.get(i).removeVehicle();
        }

    }

    /* 주차하려는 차량을 수용할 수 있는 공간이 있는지 크기 비교 */
    public abstract boolean canFitInSpot(ParkingSpot parkingSpot);

}

class Motorcycle extends Vehicle {
    public Motorcycle() {
        size = VehicleSize.COMPACT;
        spotsNeeded = size.getValue();
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getSpotSize() == VehicleSize.MOTORCYCLE;
    }
}

class Car extends Vehicle {
    public Car() {
        size = VehicleSize.COMPACT;
        spotsNeeded = size.getValue();
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getSpotSize() == VehicleSize.LARGE || parkingSpot.getSpotSize() == VehicleSize.COMPACT;
    }
}

class Bus extends Vehicle {
    public Bus () {
        size = VehicleSize.LARGE;
        spotsNeeded = size.getValue();
    }

    @Override
    public boolean canFitInSpot(ParkingSpot parkingSpot) {
        return parkingSpot.getSpotSize() == VehicleSize.LARGE;
    }
}