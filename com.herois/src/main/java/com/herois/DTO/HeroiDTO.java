package com.herois.DTO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HeroiDTO {
	private String nome;
	private Integer universo_id; 
	private List<ItemPoderDTO> poderes; 
}
