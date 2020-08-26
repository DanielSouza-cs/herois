package com.herois.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.herois.entity.Heroi;

public interface HeroiRepository extends JpaRepository<Heroi, Integer>{

	@Query(" select p from Heroi p left join fetch p.poderes where p.id = :id ")
    Optional<Heroi>findByIdFetchPoderes(@Param("id") Integer id);  
}
