package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpTravelSearch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CpTravelSearchRepository extends JpaRepository<CpTravelSearch,Integer> {
}
