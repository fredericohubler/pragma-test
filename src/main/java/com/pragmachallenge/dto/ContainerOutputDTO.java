package com.pragmachallenge.dto;

import com.pragmachallenge.model.BeerEnum;

public class ContainerOutputDTO {

    private BeerEnum beer;
    private Long id;
    private int currentTemperature;
    private Integer minTemperature;
    private Integer maxTemperature;

    @Override
    public String toString() {
        return "ContainerOutputDTO{" +
                "beer=" + beer +
                ", id=" + id +
                ", currentTemperature=" + currentTemperature +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContainerOutputDTO that = (ContainerOutputDTO) o;

        if (currentTemperature != that.currentTemperature) return false;
        if (minTemperature != that.minTemperature) return false;
        if (maxTemperature != that.maxTemperature) return false;
        if (beer != that.beer) return false;
        return id != null ? id.equals(that.id) : that.id == null;

    }

    @Override
    public int hashCode() {
        int result = beer != null ? beer.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + currentTemperature;
        result = 31 * result + minTemperature;
        result = 31 * result + maxTemperature;
        return result;
    }

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

    public void setCurrentTemperature(Integer currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Integer minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }
}
