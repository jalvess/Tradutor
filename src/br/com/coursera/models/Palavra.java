/**
 * 
 */
package br.com.coursera.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Jose
 *
 */
@XStreamAlias("Palavra")
public class Palavra {
	private String palavra;
	private String idioma;
	private ArrayList<String> traducoes = new ArrayList<>();

	/**
	 * @param palavra
	 * @param idioma
	 * @param traducoes
	 */
	public Palavra(String palavra, String idioma, String... traducoes) {
		this.palavra = palavra;
		this.idioma = idioma;
		this.traducoes.addAll(Arrays.asList(traducoes));
	}

	/**
	 * 
	 */
	public Palavra() {

	}

	public String getPalavra() {
		return this.palavra;
	}

	public String getIdioma() {
		return this.idioma;
	}

	@SuppressWarnings("unchecked")
	public List<String> getTraducoes() {
		return ((List<String>) this.traducoes.clone());
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public void addTraducao(String... traducao) {
		this.traducoes.addAll(Arrays.asList(traducao));
	}

	@Override
	public String toString() {
		return "Palavra [palavra=" + this.palavra + ", idioma=" + this.idioma + ", traducoes=" + this.traducoes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.idioma == null) ? 0 : this.idioma.hashCode());
		result = prime * result + ((this.palavra == null) ? 0 : this.palavra.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Palavra other = (Palavra) obj;
		if (this.idioma == null) {
			if (other.idioma != null)
				return false;
		} else if (!this.idioma.equals(other.idioma))
			return false;
		if (this.palavra == null) {
			if (other.palavra != null)
				return false;
		} else if (!this.palavra.equals(other.palavra))
			return false;
		return true;
	}
	
	

}
