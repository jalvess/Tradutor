/**
 * 
 */
package br.com.coursera.interfaces;

import java.io.File;
import java.util.HashMap;

/**
 * @author Jose
 * @param <T>
 *
 */
public interface ArquivoService<T> {
	/**
	 * @param registro void 
	 */
	boolean adicionaRegistro(T registro, String arquivo);
	HashMap<Integer, T> getRegistros(String arquivo);
	File criaArquivo(String fileName);
}
