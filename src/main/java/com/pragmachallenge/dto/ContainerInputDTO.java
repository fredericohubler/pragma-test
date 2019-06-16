package com.pragmachallenge.dto;

import com.pragmachallenge.model.BeerEnum;

public class ContainerInputDTO {

    private BeerEnum beer;
    private int currentTemperature;

    public BeerEnum getBeer() {
        return beer;
    }

    public void setBeer(BeerEnum beer) {
        this.beer = beer;
    }

    public int getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(int currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerInputDTO that = (ContainerInputDTO) o;

        if (currentTemperature != that.currentTemperature) return false;
        return beer == that.beer;

    }

    @Override
    public int hashCode() {
        int result = beer != null ? beer.hashCode() : 0;
        result = 31 * result + currentTemperature;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerInputDTO{" +
                "beer=" + beer +
                ", currentTemperature=" + currentTemperature +
                '}';
    }
}
