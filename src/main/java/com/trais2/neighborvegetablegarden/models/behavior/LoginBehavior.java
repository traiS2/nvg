package com.trais2.neighborvegetablegarden.models.behavior;

import com.trais2.neighborvegetablegarden.models.entity.User;

public interface LoginBehavior<T, R> {
    T login(R loginRequest);
}
