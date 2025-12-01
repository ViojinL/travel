package org.x.travel.dao;

import org.x.travel.entity.User;

public interface UserDAO {
    User findByUsername(String username);
}
