package org.x.travel.dao.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.x.travel.dao.CityDAO;
import org.x.travel.entity.City;
import org.x.travel.util.DBUtil;

public class CityDAOImpl implements CityDAO {
    @Override
    public List<City> findAll() {
        List<Map<String, Object>> rows = DBUtil.query("SELECT id, name, province, created_by, created_at FROM city");
        List<City> cities = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            cities.add(map(row));
        }
        return cities;
    }

    @Override
    public City findById(int id) {
        Map<String, Object> row = DBUtil.queryOne("SELECT id, name, province, created_by, created_at FROM city WHERE id = ?", id);
        return row == null ? null : map(row);
    }

    @Override
    public int insert(City city) {
        return DBUtil.update("INSERT INTO city (name, province, created_by) VALUES (?, ?, ?)",
                city.getName(), city.getProvince(), city.getCreatedBy());
    }

    @Override
    public int update(City city) {
        return DBUtil.update("UPDATE city SET name = ?, province = ? WHERE id = ?",
                city.getName(), city.getProvince(), city.getId());
    }

    private City map(Map<String, Object> row) {
        City city = new City();
        city.setId(((Number) row.get("id")).intValue());
        city.setName((String) row.get("name"));
        city.setProvince((String) row.get("province"));
        Object createdBy = row.get("created_by");
        if (createdBy != null) {
            city.setCreatedBy(((Number) createdBy).intValue());
        }
        Object ts = row.get("created_at");
        if (ts instanceof Timestamp) {
            city.setCreatedAt(((Timestamp) ts).toLocalDateTime());
        }
        return city;
    }
}
