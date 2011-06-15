package net.pureessence.domain;


public class Happiness {
    private int level = 0;
    public Happiness(String way) {
        if("JUnit4".equals(way))
            level = 100;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
