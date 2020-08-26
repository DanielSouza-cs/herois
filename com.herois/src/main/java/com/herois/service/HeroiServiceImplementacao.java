package com.herois.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.herois.DTO.HeroiDTO;
import com.herois.DTO.InformacaoHeroiDTO;
import com.herois.DTO.ItemPoderDTO;
import com.herois.entity.Heroi;
import com.herois.entity.ItemPoder;
import com.herois.entity.Poder;
import com.herois.entity.Universo;
import com.herois.exception.RegraNegocioException;
import com.herois.repository.HeroiRepository;
import com.herois.repository.ItemPoderRepository;
import com.herois.repository.PoderRepository;
import com.herois.repository.UniversoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HeroiServiceImplementacao implements HeroiService{
	
	private final HeroiRepository heroiRepository; 
	private final PoderRepository poderRepository; 
	private final UniversoRepository universoRepository; 
	private final ItemPoderRepository itemPoderRepository; 
	
	@Override
    @Transactional
	public Heroi save(HeroiDTO dto) {
		Integer idUniverso = dto.getUniverso_id();
		
        Universo universo = universoRepository
                .findById(idUniverso)
                .orElseThrow(() -> new RegraNegocioException("Código de universo inválido."));
        
         
        Heroi novo_heroi = new Heroi();
        novo_heroi.setNome(dto.getNome());
        novo_heroi.setDataCadastro(dataHoraAtual());
        novo_heroi.setUniverso(universo);
        List<ItemPoder> poderes = converterItems(novo_heroi, dto.getPoderes());
        heroiRepository.save(novo_heroi); 
        itemPoderRepository.saveAll(poderes);
        novo_heroi.setPoderes(poderes);
        return novo_heroi; 
	}
	
	public List<ItemPoder>converterItems(Heroi heroi, List<ItemPoderDTO>poderes){
		if(poderes.isEmpty()){
            throw new RegraNegocioException("Não é possivel Adicionar HEROI sem poderes");
        }

        return poderes
                .stream()
                .map( dto -> {
                    Integer idPoder = dto.getPoder_id();
                    Poder poder = poderRepository
                            .findById(idPoder)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de Poder inválido: "+ idPoder
                                    ));

                    ItemPoder itemPoder = new ItemPoder();
                    itemPoder.setHeroi(heroi);
                    itemPoder.setPoder(poder);
                    itemPoder.setObservacao(dto.getObservacao());
                    return itemPoder;
                }).collect(Collectors.toList());
	}
	
	@Override
	public void delete(Integer id){
		
	}
	
	@Override
	public List<InformacaoHeroiDTO>getAll(){
		List<Heroi>herois = heroiRepository.findAll(); 
		ArrayList<InformacaoHeroiDTO> listaHerois = new ArrayList<InformacaoHeroiDTO>();
		for(Heroi heroi : herois) {
			listaHerois.add(converter(heroi));
		}
		return listaHerois; 
				
	}
	
	private InformacaoHeroiDTO converter(Heroi heroi){
		String dataHora = heroi.getDataCadastro(); 
		return InformacaoHeroiDTO.builder()
				.codigo(heroi.getId())
				.nome(heroi.getNome())
				.universo(heroi.getUniverso().getNome())
				.dataCriacao(dataHora)
				.poderes(converter(heroi.getPoderes()))
				.build();
	}
	private String converter(List<ItemPoder> poderes){
		if (CollectionUtils.isEmpty(poderes)) {
			return "";
		}
		String poderesHeroi = ""; 
		for(ItemPoder poder : poderes)
			poderesHeroi += poder.getPoder().getNome() + "  |  "; 
		return poderesHeroi;
	}

	
	public String dataHoraAtual() {
        Locale locale = new Locale("pt","BR");
        GregorianCalendar calendar = new GregorianCalendar();
        SimpleDateFormat formatador = new SimpleDateFormat("dd/MMMMM/yyyy - HH:mm",locale);
        
        return formatador.format(calendar.getTime()); 
 }
}
