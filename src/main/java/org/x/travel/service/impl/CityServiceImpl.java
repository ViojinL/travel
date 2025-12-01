package org.x.travel.service.impl;

import java.util.List;

import org.x.travel.dao.CityDAO;
import org.x.travel.dao.OperationLogDAO;
import org.x.travel.dao.impl.CityDAOImpl;
import org.x.travel.dao.impl.OperationLogDAOImpl;
import org.x.travel.entity.City;
import org.x.travel.entity.OperationLog;
import org.x.travel.service.CityService;

public class CityServiceImpl implements CityService {
    private final CityDAO cityDAO = new CityDAOImpl();
    private final OperationLogDAO logDAO = new OperationLogDAOImpl();

    @Override
    public List<City> listCities() {
        return cityDAO.findAll();
    }

    @Override
    public City getCity(int id) {
        return cityDAO.findById(id);
    }

    @Override
    public boolean createCity(City city, int userId) {
        city.setCreatedBy(userId);
        int rows = cityDAO.insert(city);
        if (rows > 0) {
            logDAO.insert(new OperationLog(userId, "city", null, "create", city.getName()));
        }
        return rows > 0;
    }

    @Override
    public boolean updateCity(City city, int userId) {
        int rows = cityDAO.update(city);
        if (rows > 0) {
            logDAO.insert(new OperationLog(userId, "city", city.getId(), "update", city.getName()));
        }
        return rows > 0;
    }
}
