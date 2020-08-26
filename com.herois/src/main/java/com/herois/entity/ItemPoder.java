package com.herois.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "item_poder")
public class ItemPoder{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id; 
	
	@ManyToOne
	@JoinColumn(name = "poder_id")
	private Poder poder;
	
	@ManyToOne
	@JoinColumn(name = "heroi_id")
	private Heroi heroi; 
	
	@Column(name = "observacao")
	private String observacao; 
}
