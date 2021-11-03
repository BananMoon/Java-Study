package Item10;

public enum EnumEx1 {
    THREE(3, 4_000),
    FOUR(4, 10_000),
    FIVE(5, 30_000);

    private final int match;
    private final int money;
    private int count;

    EnumEx1(int match, int money) {
        this.match = match;
        this.money = money;
    }

    public int getMatch() {
        return match;
    }
    public int getMoney() {
        return money;
    }
}
