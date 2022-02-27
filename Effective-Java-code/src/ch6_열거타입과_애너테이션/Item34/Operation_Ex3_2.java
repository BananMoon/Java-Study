package ch6_열거타입과_애너테이션.Item34;

public enum Operation_Ex3_2 {
    Plus {public double apply(double x, double y) { return x+y;}},
    MINUS{public double apply(double x, double y) { return x-y;}},
    TIMES{public double apply(double x, double y) { return x*y;}},
    DIVIDE{public double apply(double x, double y) { return x/y;}};

    public abstract double apply(double x, double y);
}
