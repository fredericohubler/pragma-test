package com.pragmachallenge.model;

public enum BeerEnum {
    PILSEN(4, 6),
    IPA(5, 6),
    LAGGER(4, 7),
    STOUT(6, 8),
    WHEAT_BEER(3, 5),
    PALE_ALE(4, 6);

    private int minTemp;
    private int maxTemp;

    BeerEnum(int minTemp, int maxTemp) {
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public int getMinTemp() {
        return minTemp;
    }

    public int getMaxTemp() {
        return maxTemp;
    }
}
