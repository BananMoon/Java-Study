package Item16;

public class Point {
    private double x;   // 데이터 필드에 직접 접근할 수 없음.
    private double y;

    public Point (double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }
}