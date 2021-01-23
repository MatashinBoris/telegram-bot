package com.chatBot.telegramConverter.service;

import com.chatBot.telegramConverter.domain.User;
import com.chatBot.telegramConverter.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepo userRepo;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean checkAvailability(Integer telegramId) {
        return userRepo.findByTelegramId(telegramId) != null;
    }

    public void addUserOrSave(User user) {
        userRepo.save(user);
    }

    public User findByTelegramId(Integer id) {
        return userRepo.findByTelegramId(id);
    }

    public User findByTelegramIdAndSetValue(Integer id, String value) {
        User user = userRepo.findByTelegramId(id);
        user.setSelectedValue(value);
        return user;
    }
}
