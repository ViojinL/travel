package org.x.travel.dao;

import java.util.List;

import org.x.travel.entity.Attraction;

public interface AttractionDAO {
    List<Attraction> findByCityId(int cityId);

    Attraction findById(int id);

    int insert(Attraction attraction);

    int update(Attraction attraction);

    int delete(int id);
}
