package com.spring.esouk.entity.service;

import com.spring.esouk.entity.Role;
import com.spring.esouk.entity.User;

public interface AccountService {
    public User saveAppUser(User user);
    public Role saveRole(Role role);
    public void AddRoleToUse(String Username,String roleName);
    public User findByUserName(String username);
}
