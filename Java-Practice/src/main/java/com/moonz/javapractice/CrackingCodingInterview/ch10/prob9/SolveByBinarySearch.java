package com.moonz.javapractice.CrackingCodingInterview.ch10.prob9;

/**
 * 최적화된 방법 - 이진탐색 이용
 * 1. (0,0)에서, (length-1, length-1)에서 대각선 원소들만으로 이진탐색을 진행하며, x보다 큰 원소를 찾자마자 재귀 함수 호출
 * 2. 행렬의 사분면 중, 왼쪽하단 & 오른쪽상단 분면을 탐색한다.
 * 3. 왼쪽하단과 오른쪽상단 배열로 시작하는 첫 메서드부터 호출한다.
 * 재귀: findElement(2) -> findElement(3) -> partitionAndSearch() -> 새 범위의 행렬로 findElement(2) 재귀
 */
public class SolveByBinarySearch {
    public static void main(String[] args) {
        SolveByBinarySearch test = new SolveByBinarySearch();
        int[][] matrix = {
                {15,20,72,85},
                {20,35,80,95},
                {30,55,95,105},
                {50,90,110,210},
                {60,100,120,220}
        };
        Coordinate element = test.findElement(matrix, 210);
        System.out.println("x의 자리: ("+ element.row + ", " + element.col + ")");
    }

    /* 1. 시작, 끝 원소로 행렬 전달 */
    Coordinate findElement(int[][] matrix, int x) {
        Coordinate start = new Coordinate(0, 0);
        Coordinate end = new Coordinate(matrix.length - 1, matrix[0].length - 1);
        return findElement(matrix, start, end, x);
    }

    /* 2. helper - 적절한 위치 찾아서 두개의 행렬 분할&탐색할 메서드 호출 */
    Coordinate findElement(int[][] matrix, Coordinate origin, Coordinate dest, int x) {
        // 예외 처리
        if (!origin.isInBounds(matrix) || !dest.isInBounds(matrix)) {
            return null;
        }
        /* 시작 지점 == x 체크 */
        if (matrix[origin.row][origin.col] == x) {
            return origin;
        } else if (!origin.isBefore(dest)) {
            return null;
        }

        // 비즈니스 로직
        /* 대각선의 시작, 마지막 지점 지정 -> start, end */
        /* 자연스레 정사각형으로 맞춰짐.  */
        Coordinate start = new Coordinate(origin.row, origin.col);
        int diagonalDist = Math.min(dest.row - origin.row, dest.col - origin.col);    // 대각선은 가로, 세로 길이차이 중 작은 값으로.
        Coordinate end = new Coordinate(start.row + diagonalDist, start.col + diagonalDist);
        // 중심 pivot 초기화 (while문에서 재설정)
        Coordinate pivot = new Coordinate(0, 0);
        while (start.isBefore(end)) {
            pivot.setToMid(start, end);
            if (x > matrix[pivot.row][pivot.col]) {     // pivot 값이 x보다 작으면 더 오른쪽,아래로 내려가야함
                start.row = pivot.row + 1;
                start.col = pivot.col + 1;
            } else {   // pivot 위치 값이 x보다 큰 순간 중지해야해서, end를 pivot 전으로 올리면, start와 크로스되서 while문 종료!
                end.row = pivot.row - 1;
                end.col = pivot.col - 1;
            }
        }

        /* x보다 커진 첫번째 원소 start가 피봇이 되어 전달 */
        return partitionAndSearch(matrix, origin, dest, start, x);
    }
    /* 3. 행렬 분할 후 재귀 탐색 진행 메서드 */
    private Coordinate partitionAndSearch(int[][] matrix, Coordinate origin, Coordinate dest, Coordinate pivot, int x) {
        // 왼쪽 하단
        Coordinate leftLowerOrigin = new Coordinate(pivot.row, origin.col);
        Coordinate leftLowerDest = new Coordinate(dest.row, pivot.col - 1);
        // 오른쪽 상
        Coordinate rightUpperOrigin = new Coordinate(origin.row, pivot.col);
        Coordinate rightUpperDest = new Coordinate(pivot.row - 1, dest.col);

        Coordinate foundX = findElement(matrix, leftLowerOrigin, leftLowerDest, x);
        if (foundX == null) {
            return findElement(matrix, rightUpperOrigin, rightUpperDest, x);
        }
        return foundX;
    }

    private static class Coordinate {
        int row;
        int col;

        public Coordinate (int r, int c) {
            row = r;
            col = c;
        }

        boolean isInBounds(int[][] matrix) {
            return row >=0 && row < matrix.length && col >= 0 && col < matrix[0].length;
        }
        boolean isBefore(Coordinate next) {
            return this.row <= next.row && this.col <= next.col;
        }

        public void setToMid(Coordinate start, Coordinate end) {
            this.row = (start.row + end.row) / 2;
            this.col = (start.col + end.col) / 2;
        }
    }
}
