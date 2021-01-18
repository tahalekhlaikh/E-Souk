package com.spring.esouk.entity.service;

import com.spring.esouk.entity.Role;
import com.spring.esouk.entity.User;
import com.spring.esouk.entity.dao.RoleRepository;
import com.spring.esouk.entity.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public User saveAppUser(User user) {
        String hashPw=passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPw);


        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void AddRoleToUse(String Username, String roleName) {
        Role role=roleRepository.findByRole(roleName);
        User user=userRepository.findByUsername(Username);
        user.getRoles().add(role);
    }

    @Override
    public User findByUserName(String username) {
        return userRepository.findByUsername(username);
    }
}
