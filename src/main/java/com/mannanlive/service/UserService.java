package com.mannanlive.service;

import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.usermodel.NewUserRequest;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerNewUser(NewUserRequest request) {

//        if (emailExist(accountDto.getEmail())) {
//            throw new EmailExistsException(
//                    "There is an account with that email adress:" + accountDto.getEmail());
//        }
//
//        user.setFirstName(accountDto.getFirstName());
//        user.setLastName(accountDto.getLastName());
//        user.setPassword(passwordEncoder.encode(accountDto.getPassword()));
//        user.setRole(new Role(Integer.valueOf(1), user));

        UserEntity userEntity = new UserEntity();
//        userEntity.setEmail(request.getEmail());
//        userEntity.setOrganisation(request.getEmail().split("@")[1]);
        userEntity.setName(request.getEmail().split("@")[0]);
        userEntity = userRepository.save(userEntity);
        return translateUser(userEntity);
    }

    public List<User> findUsersInOrganisation(String organisation) {
//        List<UserEntity> users = userRepository.findByOrganisation(organisation);
//        return users.stream().map(this::translateUser).collect(Collectors.toList());
        return null;
    }

    private User translateUser(UserEntity userEntity) {
        User user = new User();
        user.getData().setId(String.format("%d", userEntity.getId()));
        user.getData().getAttributes().setName(userEntity.getName());
//        user.getData().getAttributes().setOrganisation(userEntity.getOrganisation());
        return user;
    }
}
