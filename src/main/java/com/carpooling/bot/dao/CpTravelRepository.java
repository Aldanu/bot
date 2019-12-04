package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpTravel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpTravelRepository extends JpaRepository<CpTravel,Integer> {
}
