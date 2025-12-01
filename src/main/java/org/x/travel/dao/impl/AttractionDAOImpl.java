package org.x.travel.dao.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.x.travel.dao.AttractionDAO;
import org.x.travel.entity.Attraction;
import org.x.travel.util.DBUtil;

public class AttractionDAOImpl implements AttractionDAO {
    @Override
    public List<Attraction> findByCityId(int cityId) {
        List<Map<String, Object>> rows = DBUtil.query("SELECT * FROM attraction WHERE city_id = ?", cityId);
        List<Attraction> attractions = new ArrayList<>();
        for (Map<String, Object> row : rows) {
            attractions.add(map(row));
        }
        return attractions;
    }

    @Override
    public Attraction findById(int id) {
        Map<String, Object> row = DBUtil.queryOne("SELECT * FROM attraction WHERE id = ?", id);
        return row == null ? null : map(row);
    }

    @Override
    public int insert(Attraction attraction) {
        return DBUtil.update("INSERT INTO attraction (city_id, name, price, description, created_by) VALUES (?, ?, ?, ?, ?)",
                attraction.getCityId(), attraction.getName(), attraction.getPrice(), attraction.getDescription(), attraction.getCreatedBy());
    }

    @Override
    public int update(Attraction attraction) {
        return DBUtil.update("UPDATE attraction SET name = ?, price = ?, description = ? WHERE id = ?",
                attraction.getName(), attraction.getPrice(), attraction.getDescription(), attraction.getId());
    }

    @Override
    public int delete(int id) {
        return DBUtil.update("DELETE FROM attraction WHERE id = ?", id);
    }

    private Attraction map(Map<String, Object> row) {
        Attraction attraction = new Attraction();
        attraction.setId(((Number) row.get("id")).intValue());
        attraction.setCityId(((Number) row.get("city_id")).intValue());
        attraction.setName((String) row.get("name"));
        Object price = row.get("price");
        if (price instanceof BigDecimal) {
            attraction.setPrice((BigDecimal) price);
        } else if (price != null) {
            attraction.setPrice(new BigDecimal(price.toString()));
        }
        attraction.setDescription((String) row.get("description"));
        attraction.setImagePath((String) row.get("image_path"));
        Object createdBy = row.get("created_by");
        if (createdBy != null) {
            attraction.setCreatedBy(((Number) createdBy).intValue());
        }
        Object ts = row.get("created_at");
        if (ts instanceof Timestamp) {
            attraction.setCreatedAt(((Timestamp) ts).toLocalDateTime());
        }
        return attraction;
    }
}
