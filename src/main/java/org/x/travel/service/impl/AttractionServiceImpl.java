package org.x.travel.service.impl;

import java.util.List;

import org.x.travel.dao.AttractionDAO;
import org.x.travel.dao.OperationLogDAO;
import org.x.travel.dao.impl.AttractionDAOImpl;
import org.x.travel.dao.impl.OperationLogDAOImpl;
import org.x.travel.entity.Attraction;
import org.x.travel.entity.OperationLog;
import org.x.travel.service.AttractionService;

public class AttractionServiceImpl implements AttractionService {
    private final AttractionDAO attractionDAO = new AttractionDAOImpl();
    private final OperationLogDAO logDAO = new OperationLogDAOImpl();

    @Override
    public List<Attraction> listByCity(int cityId) {
        return attractionDAO.findByCityId(cityId);
    }

    @Override
    public Attraction getDetail(int id) {
        return attractionDAO.findById(id);
    }

    @Override
    public boolean createAttraction(Attraction attraction, int userId) {
        attraction.setCreatedBy(userId);
        int rows = attractionDAO.insert(attraction);
        if (rows > 0) {
            logDAO.insert(new OperationLog(userId, "attraction", null, "create", attraction.getName()));
        }
        return rows > 0;
    }

    @Override
    public boolean updateAttraction(Attraction attraction, int userId) {
        int rows = attractionDAO.update(attraction);
        if (rows > 0) {
            logDAO.insert(new OperationLog(userId, "attraction", null, "update", attraction.getName()));
        }
        return rows > 0;
    }

    @Override
    public boolean deleteAttraction(int id, int userId) {
        Attraction attraction = attractionDAO.findById(id);
        if (attraction == null) {
            return false;
        }
        int rows = attractionDAO.delete(id);
        if (rows > 0) {
            logDAO.insert(new OperationLog(userId, "attraction", null, "delete", attraction.getName()));
        }
        return rows > 0;
    }
}
