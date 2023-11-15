package main;

import java.util.Objects;

public class Pagina {

	private Integer tempo = 0;

	private Integer idPagina;

	private Integer presente = 0;

	public Pagina(Integer id) {
		this.idPagina = id;
	}

	public Pagina() {
	}

	public Integer getTempo() {
		return tempo;
	}

	public void setTempo(Integer tempo) {
		this.tempo = tempo;
	}

	public Integer getId() {
		return idPagina;
	}

	public void setId(Integer id) {
		this.idPagina = id;
	}

	public Integer isPresente() {
		return presente;
	}

	public void setPresente(Integer presente) {
		this.presente = presente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPagina);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagina other = (Pagina) obj;
		return Objects.equals(idPagina, other.idPagina);
	}

}
