package com.herois.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "heroi")
public class Heroi {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private Integer id; 
	
	@Column(name = "nome")
	private String nome; 
	
	@OneToOne
	@JoinColumn(name = "universo_id")
	private Universo universo; 
	
	@Column(name = "data_cadastro", length = 20)
	private String dataCadastro; 
	
	@OneToMany(mappedBy = "heroi")
	private List<ItemPoder> poderes;
}
