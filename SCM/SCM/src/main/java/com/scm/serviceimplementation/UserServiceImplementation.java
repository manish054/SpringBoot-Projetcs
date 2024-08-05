package com.scm.serviceimplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helper.AppConstants;
import com.scm.repository.UserRepo;
import com.scm.service.UserService;
import com.scm.userform.UserForm;

@Service
public class UserServiceImplementation implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<Object> saveUser(UserForm userForm) {
        User newUser =new User();
        //extracting data from form and crearting user
        newUser.setName(userForm.getName());
        newUser.setEmailId(userForm.getEmail());
        newUser.setPassword(passwordEncoder.encode(userForm.getPassword()));
        newUser.setPhoneNum(userForm.getPhoneNum());
        newUser.setAbout(userForm.getAbout());
        newUser.setProfilePic("https://w7.pngwing.com/pngs/177/551/png-transparent-user-interface-design-computer-icons-default-stephen-salazar-graphy-user-interface-design-computer-wallpaper-sphere-thumbnail.png");
        newUser.setRoleList(List.of(AppConstants.ROLE_USER));
        newUser.setProvider(Providers.SELF);
        userRepo.save(newUser);
        return new ResponseEntity<>("",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Object> getUserById(Integer id) {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<>(user,HttpStatus.OK);
        }
        return new ResponseEntity<>(user,HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> updateUser(User user) {
        Optional<User> optional = userRepo.findById(user.getId());
        if(optional.isPresent()){

            try {
            User newUser = new User();
            newUser.setName(user.getName());
            newUser.setEmailId(user.getEmailId());
            newUser.setPassword(user.getPassword());
            newUser.setPhoneNum(user.getPhoneNum());
            newUser.setAbout(user.getAbout());
            newUser.setProfilePic(user.getProfilePic());
            newUser.setEmailVerified(user.isEmailVerified());
            newUser.setEnabled(user.isEnabled());
            newUser.setPhoneNumVerified(user.isPhoneNumVerified());

            return new ResponseEntity(userRepo.save(newUser),HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> deleteUser(Integer id) {
        Optional<User> optional = userRepo.findById(id);
        try{
            if(optional.isPresent()){
                userRepo.deleteById(id);
                return new ResponseEntity<>("Deleted", HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Object> isUserExist(Integer id) {
        Optional<User> opUser = userRepo.findById(id);
        if(opUser.isPresent())
            return new ResponseEntity<>("True", HttpStatus.FOUND);
        return new ResponseEntity<>("False", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> isUserExistByEmail(String email) {
        Optional<User> opUser = userRepo.findByEmailId(email);
        if(opUser.isPresent())
            return new ResponseEntity<>("True", HttpStatus.FOUND);
        return new ResponseEntity<>("False", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseEntity<Object> getAllUser() {
        return new ResponseEntity<>(userRepo.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> findByEmail(String email){
        User user  = userRepo.findByEmailId(email).get();
        if(user != null)
            return new ResponseEntity<>(user, HttpStatus.FOUND);
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    

}
