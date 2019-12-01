package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpZoneRepository extends JpaRepository<CpZone,Integer> {
}
