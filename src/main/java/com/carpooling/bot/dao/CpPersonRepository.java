package com.carpooling.bot.dao;

import com.carpooling.bot.domain.CpPerson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CpPersonRepository extends JpaRepository<CpPerson, Integer> {


    List<CpPerson> findAllByStatus(int status) ;
}
