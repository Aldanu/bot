package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpCarRepository extends JpaRepository<CpCar,Integer> {
}
