package com.pragmachallenge.dto;

import com.pragmachallenge.model.BeerEnum;

public class ContainerInputDTO {

    private BeerEnum beer;
    private Long id;
    private int currentTemperature;

    public BeerEnum getBeer() {
        return beer;
    }

    public void setBeer(BeerEnum beer) {
        this.beer = beer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (beer != that.beer) return false;
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result = beer != null ? beer.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + currentTemperature;
        return result;
    }

    @Override
    public String toString() {
        return "ContainerInputDTO{" +
                "beer=" + beer +
                ", id=" + id +
                ", currentTemperature=" + currentTemperature +
                '}';
    }
}
