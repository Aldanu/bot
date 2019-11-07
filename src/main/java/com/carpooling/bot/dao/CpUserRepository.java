package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpUserRepository extends JpaRepository<CpUser, Integer> {

    CpUser findByBotUserId(String botUserId);
}
