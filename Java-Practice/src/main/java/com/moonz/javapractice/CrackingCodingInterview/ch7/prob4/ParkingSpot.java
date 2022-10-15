package com.moonz.javapractice.CrackingCodingInterview.ch7.prob4;

import lombok.Getter;
/* 주차 공간 클래스
- 현재 층 수
- 번호
- 공간 크기
- 주차가 된 vehicle 정보
- 열 번호
-
 */
public class ParkingSpot {
    private Level level;
    @Getter
    private int spotNum;
    @Getter
    private VehicleSize spotSize;   // 주차공간 크기
    private Vehicle vehicle;
    @Getter
    private int rowNum;    // 열 번호

    public ParkingSpot (Level level, int spotNum, VehicleSize vs, int rowNum) {
        this.level = level;
        this.spotNum = spotNum;
        spotSize = vs;
        this.rowNum = rowNum;
    }

    public boolean isAvailable () {
        return vehicle == null;
    }

    /* Spot 크기가 Vehicle에 충분히 맞고 주차할 수 있는지. 크기만 비교한다. */
    public boolean canFitVehicleOnSpot (Vehicle vehicle) {
        return isAvailable() && vehicle.canFitInSpot(this);
    }

    /* 주차 */
    public boolean park (Vehicle v) {
        if (!canFitVehicleOnSpot(v)) {
            return false;
        }
        vehicle = v;
        v.parkInSpot(this);
        level.decrementAvailableSpot(); /* 추가 */
        return true;
    }

    public void removeVehicle() {
        level.incrementAvailableSpot();
        vehicle = null;
        System.out.println(level + "층에 주차 공간이 생겼습니다.");
    }
}
