package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpTravelPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpTravelPlaceRepository  extends JpaRepository<CpTravelPlace,Integer> {

}
