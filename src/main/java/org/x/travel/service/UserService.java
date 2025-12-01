package org.x.travel.service;

import org.x.travel.entity.User;

public interface UserService {
    User authenticate(String username, String password);
}
