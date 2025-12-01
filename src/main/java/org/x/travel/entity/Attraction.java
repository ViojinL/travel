package org.x.travel.entity;

import java.math.BigDecimal;

public class Attraction {
    private int id;
    private int cityId;
    private String name;
    private BigDecimal price;
    private String description;
    private String imagePath;

    public Attraction() {
    }

    public Attraction(int id, int cityId, String name, BigDecimal price, String description, String imagePath) {
        this.id = id;
        this.cityId = cityId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
