package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpPlace;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpPlaceRepository extends JpaRepository<CpPlace,Integer> {
}
