package com.herois.service;

import java.util.List;

import com.herois.DTO.HeroiDTO;
import com.herois.DTO.InformacaoHeroiDTO;
import com.herois.entity.Heroi;

public interface HeroiService {
	
	Heroi save(HeroiDTO dto);
	
	public List<InformacaoHeroiDTO>getAll();
	
	void delete(Integer id); 
}
