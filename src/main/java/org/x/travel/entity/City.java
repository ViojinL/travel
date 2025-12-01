package org.x.travel.entity;

import java.time.LocalDateTime;

public class City {
    private int id;
    private String name;
    private String province;
    private Integer createdBy;
    private LocalDateTime createdAt;

    public City() {
    }

    public City(int id, String name, String province) {
        this.id = id;
        this.name = name;
        this.province = province;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
