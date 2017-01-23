package com.mannanlive.translator;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.RoleEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.ResourceSummary;
import com.mannanlive.model.usermodel.NewUserRequest;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.model.usermodel.UserPrincipal;
import com.mannanlive.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashSet;

import static java.lang.String.format;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserTranslator {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConsoleRepository consoleRepository;

    public User translate(UserEntity entity) {
        User user = new User();
        user.getData().setId(entity.getId().toString());
        user.getData().getAttributes().setName(entity.getName());
        user.getData().getAttributes().setOrganisation(entity.getOrganisation());
        user.getData().getAttributes().setEmail(entity.getLogin());
        if (entity.getConsoles() != null) {
            for (ConsoleEntity console : entity.getConsoles()) {
                user.getData().getAttributes().getConsoles().add(
                        new ResourceSummary("consoles", console.getId().toString(), console.getName()));
            }
        }
        return user;
    }

    public UserDetails translateToPrincipal(UserEntity user) {
        UserPrincipal userPrincipal = new UserPrincipal(translate(user));
        userPrincipal.getData().getAttributes().setPassword(user.getPassword());
        userPrincipal.getData().getAttributes().setRoles(user.getRoles());
        return userPrincipal;
    }

    public UserEntity translate(NewUserRequest request) {
        //todo add validation e.g. email and throw HTTP Client Exception if invalid
        String organisation = request.getEmail().split("@")[1].toLowerCase();

        String password = passwordEncoder.encode(request.getPassword());
        UserEntity userEntity = new UserEntity(request.getEmail(), request.getName(), password, organisation);
        userEntity.setRoles(addUserRole());
        addConsoleIfSupplied(userEntity, request.getConsole());
        return userEntity;
    }

    private void addConsoleIfSupplied(UserEntity userEntity, Long consoleId) {
        if (consoleId == null) {
            return;
        }
        ConsoleEntity consoleEntity = consoleRepository.findOne(consoleId);
        if (consoleEntity == null) {
            throw new HttpClientErrorException(BAD_REQUEST, format("No console exists with id '%d'.", consoleId));
        }
        userEntity.setConsoles(new HashSet<>(1));
        userEntity.getConsoles().add(consoleEntity);
    }

    private HashSet<RoleEntity> addUserRole() {
        HashSet<RoleEntity> roles = new HashSet<>();
        RoleEntity e = new RoleEntity();
        e.setId(1);
        roles.add(e);
        return roles;
    }
}
