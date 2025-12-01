package org.x.travel.service;

import java.util.List;

import org.x.travel.entity.Attraction;

public interface AttractionService {
    List<Attraction> listByCity(int cityId);

    Attraction getDetail(int id);

    boolean createAttraction(Attraction attraction, int userId);

    boolean updateAttraction(Attraction attraction, int userId);

    boolean deleteAttraction(int id, int userId);
}
