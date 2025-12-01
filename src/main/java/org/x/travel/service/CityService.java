package org.x.travel.service;

import java.util.List;

import org.x.travel.entity.City;

public interface CityService {
    List<City> listCities();

    City getCity(int id);

    boolean createCity(City city, int userId);

    boolean updateCity(City city, int userId);
}
