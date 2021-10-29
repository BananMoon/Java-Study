package Item2;
// Pizza의 하위클래스 Calzone
public class Calzone extends Pizza{
    private final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder> {
        private boolean sauceInside = false;    //기본값

        public Builder sauseInside() {
            sauceInside = true;
            return this;
        }
        @Override Calzone build() {
            return new Calzone(this);
        }
        @Override protected Builder self() { return this; }
    }

    private Calzone(Builder builder) {
        super(builder);
        sauceInside = builder.sauceInside;
    }
}
