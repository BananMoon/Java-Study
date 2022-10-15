package com.moonz.javapractice.CrackingCodingInterview.ch7.prob4;

import lombok.Getter;
/*
필드
- 층 정보
- 주차공간 리스트
- 빈 주차공간 갯수
- row마다 주차공간 갯수
메서드
- 주차 가능 대수 늘리기, 줄이기
-
 */
public class Level {
    private int levelNum;
    private ParkingSpot[] spots;
    @Getter
    private int emptySpots;
    private int PARKING_SPOTS_PER_ROW = 10;

    public Level (int floor, int spotCnt) {     // 30개
        levelNum = floor;
        emptySpots = spotCnt;
        spots = new ParkingSpot[spotCnt];
        int largeSpotCnt = spotCnt / 4;         // 7개
        int motorcycleSpotCnt = spotCnt / 4;    // 7개
        int compactSpotCnt = spotCnt - largeSpotCnt - motorcycleSpotCnt;        // 16개

        for (int i=0; i<spotCnt; i++) {
            VehicleSize vs = VehicleSize.MOTORCYCLE;    // 그외(24~30)
            if (i < largeSpotCnt) vs = VehicleSize.LARGE;   // 0~7
            else if (i < largeSpotCnt + compactSpotCnt) {   // 8~23
                vs = VehicleSize.COMPACT;
            }
            int rowNum = i / PARKING_SPOTS_PER_ROW;     // 0~9번째/10=0, 10~19번째/10=1번째, 20~29번째/10=2
            spots[i] = new ParkingSpot(this, spotCnt, vs, rowNum);
        }
    }
    public void incrementAvailableSpot() {
        emptySpots+=1;
    }

    public void decrementAvailableSpot() {
        emptySpots-=1;
    }

    public boolean parkVehicle (Vehicle v) {
        if (emptySpots < v.getSpotsNeeded()) return false;
        int spotNum = findAvailableSpot(v);
        if (spotNum == -1) {
            return false;
        }
        return parkStartAtSpot(spotNum, v);
    }

    /* 주차할 공간 찾기. 찾으면 spot 인덱스를, 못찾으면 -1 */
    private int findAvailableSpot(Vehicle v) {
        int spotsNeeded = v.getSpotsNeeded();
        int foundSpots = 0;
        // TODO 마지막 row???
        int lastRow = -1;

        for (int i=0; i<spots.length; i++) {
            ParkingSpot spot = spots[i];
            spot.getRowNum()
            if (.isAvailable()
        }
    }
}
