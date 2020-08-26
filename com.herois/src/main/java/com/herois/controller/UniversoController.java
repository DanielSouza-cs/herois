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
import com.herois.entity.Universo;
import com.herois.repository.UniversoRepository;

@RestController
@RequestMapping("/api/universo")
public class UniversoController {
	private UniversoRepository universoRepository; 
	
	public UniversoController(UniversoRepository universoRepository) {
		this.universoRepository = universoRepository; 
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Universo save(@RequestBody Universo universo){
		return universoRepository.save(universo); 
	}
	
	@DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id ){
		universoRepository.deleteById(id);
    }
	
	@GetMapping("{id}")
	public Optional<Universo> getById(@PathVariable Integer id){
		return universoRepository.findById(id);  
	}
	@GetMapping
	public List<Universo> getAll(){
		return universoRepository.findAll();  
	}
}
