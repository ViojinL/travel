package org.x.travel.dao;

import java.util.List;

import org.x.travel.entity.City;

public interface CityDAO {
    List<City> findAll();

    City findById(int id);

    int insert(City city);

    int update(City city);
}
