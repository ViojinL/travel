package org.x.travel.service.impl;

import org.x.travel.dao.UserDAO;
import org.x.travel.dao.impl.UserDAOImpl;
import org.x.travel.entity.User;
import org.x.travel.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();

    @Override
    public User authenticate(String username, String password) {
        User user = userDAO.findByUsername(username);
        if (user == null) {
            return null;
        }
        if (user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
