package com.service.parking_spot_utp.model.dto;

public class ParkingCampusDto {
    private String campus;
    private String direction;
    private String link; //Link de direccion de google maps
    private Integer quantityPlaces;
    private Integer availableSpot;

    public ParkingCampusDto(String campus, String direction, String link, Integer quantityPlaces, Integer availableSpot) {
        this.campus = campus;
        this.direction = direction;
        this.link = link;
        this.quantityPlaces = quantityPlaces;
        this.availableSpot = availableSpot;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Integer getQuantityPlaces() {
        return quantityPlaces;
    }

    public void setQuantityPlaces(Integer quantityPlaces) {
        this.quantityPlaces = quantityPlaces;
    }

    public Integer getAvailableSpot() {
        return availableSpot;
    }

    public void setAvailableSpot(Integer availableSpot) {
        this.availableSpot = availableSpot;
    }

    @Override
    public String toString() {
        return "ParkingCampusDto{" +
                "campus='" + campus + '\'' +
                ", direction='" + direction + '\'' +
                ", link='" + link + '\'' +
                ", quantityPlaces=" + quantityPlaces +
                ", availableSpot=" + availableSpot +
                '}';
    }
}
