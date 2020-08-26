package com.herois.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.herois.entity.Poder;
import com.herois.repository.PoderRepository;

@RestController
@RequestMapping("/api/poder")
public class PoderController {
	private PoderRepository poderRepository;  
	
	public PoderController (PoderRepository poderRepository){
		this.poderRepository = poderRepository; 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Poder save(@RequestBody Poder poder) {
		return poderRepository.save(poder); 
	}
	
	@DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id ){
		poderRepository.deleteById(id);
    }
	
	@GetMapping("{id}")
	public Optional<Poder> getById(@PathVariable Integer id){
		return poderRepository.findById(id);  
	}
	@GetMapping
	public List<Poder> getAll(){
		return poderRepository.findAll();  
	}
}
