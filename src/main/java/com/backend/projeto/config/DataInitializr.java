package com.backend.projeto.config;

import com.backend.projeto.entity.Role;
import com.backend.projeto.entity.User;
import com.backend.projeto.repository.RoleRepository;
import com.backend.projeto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializr implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent){

        List<User> users = userRepository.findAll();
        if(users.isEmpty()){
            this.createUsers("Nataniel", "nataniel.paiva@gmail.com", passwordEncoder.encode("123456"), "ROLE_ALUNO");
            this.createUsers("Nataniel", "admin", passwordEncoder.encode("123456"), "ROLE_ADMIN");
        }

    }

    public void createUsers(String name, String email, String password, String role){

        Role roleObjetc = new Role();
        roleObjetc.setName(role);

        this.roleRepository.save(roleObjetc);

        User user = new User(name, email, password, Arrays.asList(roleObjetc));
        userRepository.save(user);

    }

}
