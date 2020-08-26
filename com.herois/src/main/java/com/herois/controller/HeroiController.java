package com.herois.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.herois.DTO.HeroiDTO;
import com.herois.DTO.InformacaoHeroiDTO;
import com.herois.entity.Heroi;
import com.herois.repository.HeroiRepository;
import com.herois.service.HeroiService;

@Controller
public class HeroiController {
	
	private HeroiRepository heroiRepository;
	
	@Autowired
	private HeroiService heroiService; 
	
	public HeroiController(HeroiRepository heroiRepository){
		this.heroiRepository = heroiRepository; 
	}
	
	@RequestMapping(value="/api/heroi/form", method=RequestMethod.GET)
	public String formHeroi() {
		return "heroiADD";
	}
	
	@RequestMapping(value="/api/heroi", method=RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void save(@RequestBody HeroiDTO dto){
		 heroiService.save(dto);
	}
	
	@RequestMapping(value="/api/heroi/{id}", method=RequestMethod.GET)
	public Optional<Heroi> getById(@PathVariable Integer id){
		return heroiRepository.findById(id);  
	}
	
	@RequestMapping(value="/api/heroi", method=RequestMethod.GET)
	public ModelAndView listaHerois(){
		ModelAndView mv = new ModelAndView("listaHerois");
		List<InformacaoHeroiDTO> herois = heroiService.getAll();
		mv.addObject("herois", herois);
		return mv;
	}
	
	@RequestMapping(value="/api/heroi/delete/{id}", method=RequestMethod.GET)
	@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id ){
		heroiRepository.deleteById(id);
    }
	
}
