package com.SpringJWTDemo.SpringJWTDemo.service;

import com.SpringJWTDemo.SpringJWTDemo.model.UserDtl;
import com.SpringJWTDemo.SpringJWTDemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Optional;

@Service
@Transactional(rollbackOn = Throwable.class)
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Iterable<UserDtl> getAllUsers() {
        return userRepository.findAllByRole("ROLE_STUDENT");
    }

    public void save(UserDtl user) throws ParseException {

        if(user.getId() != null) {
            //Update request
            UserDtl userDtl=userRepository.findById(user.getId()).get();
            userDtl=prepareUserDtl(user);
            userRepository.save(userDtl);
        }else{
            //Add new entry
            UserDtl userDtl = prepareUserDtl(user);
            userRepository.save(userDtl);
        }

    }

    public UserDtl prepareUserDtl(UserDtl user) throws ParseException {
        UserDtl userDtl=new UserDtl();
        userDtl.setId(user.getId());
        userDtl.setfName(user.getfName());
        userDtl.setgName(user.getgName());
        userDtl.setEmail(user.getEmail());
        userDtl.setBirthDate(user.getBirthDate());
        userDtl.setPassword(encoder.encode(user.getPassword()));
        userDtl.setRole("ROLE_STUDENT");
        userDtl.setUserName(user.getUserName());
        return userDtl;
    }

    public Optional<UserDtl> getUserById(int id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Integer id) {
       userRepository.deleteById(id);
    }


}

