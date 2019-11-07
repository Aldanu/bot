package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpPerson;
import com.carpooling.bot.domain.CpUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpUserRepository extends JpaRepository<CpUser, Integer> {

    CpUser findByBotUserId(String botUserId);

    //List<CpUser> findAllByStatus(int status);
}
