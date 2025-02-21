package com.bonappetit.service;


import com.bonappetit.config.UserSession;
import com.bonappetit.model.dto.UserLoginDTO;
import com.bonappetit.model.dto.UserRegisterDTO;
import com.bonappetit.model.entity.Recipe;
import com.bonappetit.model.entity.User;
import com.bonappetit.repo.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserSession userSession;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, UserSession userSession) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userSession = userSession;
    }


    public boolean register (UserRegisterDTO data) {

        //Проверка дали има такъв потребител или имейл
        Optional<User> existingUser = userRepository.findByUsernameOrEmail(data.getUsername(), data.getEmail());

        if (existingUser.isPresent()){
            return false;
        }

        //Ако няма такъв потребител, запиши го в репо заедно с данните му
        User user = new User();

        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));

        this.userRepository.save(user);

        return true;

    }

    public boolean login(UserLoginDTO data) {

        Optional<User> byUsername = userRepository.findByUsername(data.getUsername());

        if (byUsername.isEmpty()){
            return  false;
        }

        boolean passMatch = passwordEncoder.matches(data.getPassword(), byUsername.get().getPassword());

        if (!passMatch){
            return false;
        }

        userSession.login(byUsername.get().getId(), data.getUsername());

        return true;
    }

    //@Transactional
    public List<Recipe> findFavourites(Long id) {

            // Потърсете потребителя по id
            return userRepository.findById(id)
                    .map(User::getFavouriteRecipes) // Ако потребителят съществува, вземете любимите рецепти
                    .orElseGet(ArrayList::new);  // Ако няма потребител, върнете празен списък
        }

//        Optional<User> byId = userRepository.findById(id);
//
//        if (byId.isEmpty()) {
//            return new ArrayList<>();
//        }
//
//        return byId.get().getFavouriteRecipes();
//
//    }
}
