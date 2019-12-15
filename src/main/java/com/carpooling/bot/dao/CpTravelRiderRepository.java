package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpTravelRider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpTravelRiderRepository extends JpaRepository<CpTravelRider,Integer> {
}
