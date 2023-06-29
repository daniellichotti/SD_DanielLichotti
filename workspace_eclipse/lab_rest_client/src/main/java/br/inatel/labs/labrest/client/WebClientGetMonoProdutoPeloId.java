package br.inatel.labs.labrest.client;

import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.inatel.labs.labrest.client.model.dto.ProdutoDTO;
import reactor.core.publisher.Mono;

public class WebClientGetMonoProdutoPeloId {
	
	public static void main(String[] args) {
		try {
			Mono<ProdutoDTO> monoProduto =  WebClient.create("http://localhost:8080")
				.get()
				.uri("/produto/99")
				.retrieve()
				.bodyToMono(ProdutoDTO.class)
				;
			
			monoProduto.subscribe();
			
			ProdutoDTO produtoEncontrado = monoProduto.block();
			
			System.out.println("Produto encontrado: ");
			System.out.println(produtoEncontrado);
			
		} catch (WebClientResponseException e) {
			System.out.println("Status da resposta: "+ e.getStatusCode());
			System.out.println("Mensagem: "+ e.getResponseBodyAsString());
		}
	}

}
