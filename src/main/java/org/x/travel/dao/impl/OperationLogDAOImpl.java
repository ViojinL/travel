package org.x.travel.dao.impl;

import org.x.travel.dao.OperationLogDAO;
import org.x.travel.entity.OperationLog;
import org.x.travel.util.DBUtil;

public class OperationLogDAOImpl implements OperationLogDAO {
    @Override
    public int insert(OperationLog log) {
        return DBUtil.update("INSERT INTO operation_log (user_id, entity_type, entity_id, action, detail) VALUES (?, ?, ?, ?, ?)",
                log.getUserId(), log.getEntityType(), log.getEntityId(), log.getAction(), log.getDetail());
    }
}
