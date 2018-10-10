package com.mannanlive.translator;

import com.mannanlive.entity.ConsoleEntity;
import com.mannanlive.entity.UserEntity;
import com.mannanlive.model.ResourceSummary;
import com.mannanlive.model.usermodel.User;
import com.mannanlive.model.usermodel.UserPrincipal;
import com.mannanlive.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserTranslator {
    @Autowired
    private ConsoleRepository consoleRepository;

    public User translate(UserEntity entity) {
        User user = new User();
        user.getData().setId(entity.getId().toString());
        user.getData().getAttributes().setName(entity.getName());
        user.getData().getAttributes().setOrganisation(entity.getOrganisation());
        user.getData().getAttributes().setEmail(entity.getEmail());
        user.getData().getAttributes().setImageLink(entity.getImageLink());
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
        userPrincipal.getData().getAttributes().setRoles(user.getRoles());
        return userPrincipal;
    }
}
