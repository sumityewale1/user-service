package com.bizzcart.user_service.service;

import com.bizzcart.user_service.dto.User;
import com.netflix.spectator.api.histogram.PercentileBuckets;

public interface UserService {
    User registerUser(User user);

    User findByEmail(String email);

}

