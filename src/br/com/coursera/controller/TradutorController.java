/**
 * 
 */
package br.com.coursera.controller;

import br.com.coursera.models.Palavra;
import br.com.coursera.services.GerenciadorDeArquivoService;

/**
 * @author Jose
 *
 */
public class TradutorController {
	/**
	 * @param p
	 * @return Palavra
	 */
	public Palavra traduzir(Palavra p) {
		GerenciadorDeArquivoService<Palavra> gerenciador = new GerenciadorDeArquivoService<>();
		return gerenciador.getRegistros("traducoes.xml").get(p.hashCode());
	}

	/**
	 * @param palavra void
	 */
	public boolean adicionaTraducao(Palavra palavra) {
		GerenciadorDeArquivoService<Palavra> gerenciador = new GerenciadorDeArquivoService<>();
		return gerenciador.adicionaRegistro(palavra, "traducoes.xml");
	}

}
