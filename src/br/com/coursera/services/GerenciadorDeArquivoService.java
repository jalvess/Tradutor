/**
 * 
 */
package br.com.coursera.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;

import com.thoughtworks.xstream.XStream;

import br.com.coursera.interfaces.ArquivoService;

/**
 * @author Jose
 * @param <T>
 *
 */
public class GerenciadorDeArquivoService<T> implements ArquivoService<T> {

	/**
	 * Cria um arquivo XML com o objeto recebido no primeiro parâmetro
	 * 
	 * @param registro
	 **/
	@Override
	public boolean adicionaRegistro(T registro, String arquivo) {
		File file = criaArquivo(arquivo);
		XStream xs = instanciarXStream();
		HashMap<Integer, T> lista = criaListaRegistro(file, xs);
		lista.put(registro.hashCode(), registro);
		String xml = xs.toXML(lista);
		try {
			FileOutputStream stream = new FileOutputStream(file);
			OutputStreamWriter writer = new OutputStreamWriter(stream, "UTF-8");
			writer.write(xml);
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * @param registro
	 * @return XStream
	 */
	private XStream instanciarXStream() {
		XStream xs = new XStream();
		XStream.setupDefaultSecurity(xs);
		xs.allowTypesByWildcard(new String[] { "br.com.coursera.**" });
		return xs;
	}

	/**
	 * @param file
	 * @param xs
	 * @return List<T>
	 */
	@SuppressWarnings("unchecked")
	private HashMap<Integer, T> criaListaRegistro(File file, XStream xs) {
		try {
			HashMap<Integer, T> lista = ((HashMap<Integer, T>) xs.fromXML(file));
			return lista;
		} catch (Exception e) {
			return new HashMap<Integer, T>();
		}
	}

	/**
	 * @param file void
	 * @return
	 */
	@Override
	public File criaArquivo(String fileName) {
		File file = new File(fileName);
		if (file.exists()) {
			return file;
		}
		try {
			file.createNewFile();
		} catch (IOException e) {
			throw new RuntimeException();
		}
		return file;
	}

	/**
	 * @see br.com.coursera.interfaces.ArquivoService#getRegistros()
	 * @return
	 **/
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<Integer,T> getRegistros(String arquivo) {
		File file = new File(arquivo);
		XStream xstream = instanciarXStream();
		HashMap<Integer, T> objeto = (HashMap<Integer, T>) xstream.fromXML(file);
		return objeto;
	}

}
