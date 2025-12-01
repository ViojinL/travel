package org.x.travel.dao.impl;

import java.util.Map;

import org.x.travel.dao.UserDAO;
import org.x.travel.entity.User;
import org.x.travel.util.DBUtil;

public class UserDAOImpl implements UserDAO {
    @Override
    public User findByUsername(String username) {
        Map<String, Object> row = DBUtil.queryOne("SELECT id, username, password, role FROM user_account WHERE username = ?", username);
        if (row == null) {
            return null;
        }
        User user = new User();
        user.setId(((Number) row.get("id")).intValue());
        user.setUsername((String) row.get("username"));
        user.setPassword((String) row.get("password"));
        user.setRole((String) row.get("role"));
        return user;
    }
}
