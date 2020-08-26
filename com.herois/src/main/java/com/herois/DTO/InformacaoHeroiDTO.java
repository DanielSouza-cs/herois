package com.herois.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InformacaoHeroiDTO {
	private Integer codigo; 
	private String nome; 
	private String universo; 
	private String dataCriacao; 
	private String poderes; 
}
