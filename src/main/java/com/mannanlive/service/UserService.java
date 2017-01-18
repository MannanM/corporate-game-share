package com.mannanlive.service;

import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.usermodel.NewUserRequest;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(NewUserRequest request) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(request.getEmail());
        userEntity.setOrganisation(request.getEmail().split("@")[1]);
        userEntity.setName(request.getEmail().split("@")[0]);
        userEntity = userRepository.save(userEntity);
        return translateUser(userEntity);
    }

    public List<User> findUsersInOrganisation(String organisation) {
        List<UserEntity> users = userRepository.findByOrganisation(organisation);
        return users.stream().map(this::translateUser).collect(Collectors.toList());
    }

    private User translateUser(UserEntity userEntity) {
        User user = new User();
        user.getData().setId(String.format("%d", userEntity.getId()));
        user.getData().getAttributes().setName(userEntity.getName());
        user.getData().getAttributes().setOrganisation(userEntity.getOrganisation());
        return user;
    }
}
