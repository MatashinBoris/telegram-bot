package com.chatBot.telegramConverter.repos;

import com.chatBot.telegramConverter.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByTelegramId(Integer telegramId);

}
