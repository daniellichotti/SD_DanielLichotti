package br.inatel.labs.labjpa;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import br.inatel.labs.labjpa.entity.NotaCompra;
import br.inatel.labs.labjpa.entity.NotaCompraItem;
import br.inatel.labs.labjpa.service.NotaCompraService;

public class LoadingDemo {
	
	@Autowired
	private NotaCompraService service;
	
	@Test
	public void demoEagerLoading() {
	  try {
		NotaCompraItem item = service.buscarNotaCompraItemPeloId( 1L );

		LocalDate dataEmissao = item.getNotaCompra().getDataEmissao();

		String descricao = item.getProduto().getDescricao();
		String razaoSocial = item.getNotaCompra().getFornecedor().getRazaoSocial();
		
		System.out.println( razaoSocial );
		System.out.println( descricao );
		System.out.println( dataEmissao );

		System.out.println("Aconteceu carregamento EAGER");

	  } catch (Exception e) {
		e.printStackTrace();
	  }
	}
	
	@Test
	public void demoLazyLoading() {
		try {
			NotaCompra nota = service.buscarNotaCompraPeloId( 1L );
			
			List<NotaCompraItem> listaDeItens = nota.getListaNotaCompraItem();
			
			int tamanho = listaDeItens.size();

			System.out.println( tamanho );

		} catch (Exception e) {
			System.out.println("O carregamento foi LAZY e por isso lançou exception");
			e.printStackTrace();
		}
	}
	
	@Test
	public void demoPlanejandoConsulta() {
	  try {
	    NotaCompra nota = service.buscarNotaCompraPeloIdComListaItem( 1L );

	    List<NotaCompraItem> listaDeItens = nota.getListaNotaCompraItem();
	    
	    int tamanho = listaDeItens.size();

	    System.out.println( tamanho );

	  } catch (Exception e) {
	    System.out.println("O carregamento foi LAZY e por isso lançou exception");
	    e.printStackTrace();
	  }
	}
	
	
}
	
