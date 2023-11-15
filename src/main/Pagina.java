package main;

public class Pagina {

	private Integer tempo;

	private Integer idPagina;

	public Pagina(Integer id) {
		this.idPagina = id;
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

}
