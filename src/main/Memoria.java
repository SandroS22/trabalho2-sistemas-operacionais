package main;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private Integer maxQuadros;

	private List<Pagina> paginas = new ArrayList<>();

	private Integer hit;

	private Integer miss;

	public Memoria(Integer maxQuadros) {
		this.maxQuadros = maxQuadros;
	}

	public Integer getMaxQuadros() {
		return maxQuadros;
	}

	public void setMaxQuadros(Integer maxQuadros) {
		this.maxQuadros = maxQuadros;
	}

	public Integer getHit() {
		return hit;
	}

	public void setHit(Integer hit) {
		this.hit = hit;
	}

	public Integer getMiss() {
		return miss;
	}

	public void setMiss(Integer miss) {
		this.miss = miss;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public void adicionar(Pagina pagina){
		paginas.add(pagina);
	}

}
