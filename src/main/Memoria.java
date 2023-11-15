package main;

import java.util.ArrayList;
import java.util.List;

public class Memoria {

	private Integer maxQuadros;

	private List<Pagina> paginas = new ArrayList<>();

	private List<Pagina> quadros = new ArrayList<>();

	private Integer miss = 0;

	public Integer getMiss() {
		return this.miss;
	}

	public Memoria(Integer maxQuadros) {
		this.maxQuadros = maxQuadros;
	}

	public Memoria() {
	}

	public Integer getMaxQuadros() {
		return maxQuadros;
	}

	public void setMaxQuadros(Integer maxQuadros) {
		this.maxQuadros = maxQuadros;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public void setPaginas(List<Pagina> paginas) {
		this.paginas = paginas;
	}

	public List<Pagina> getQuadros() {
		return quadros;
	}

	public void setQuadross(List<Pagina> quadross) {
		this.quadros = quadross;
	}

	public void adicionar(Pagina pagina) {
		paginas.add(pagina);
	}

	public Integer fifo() {
		Integer pageFault = 0;
		for (Pagina pagina : paginas) {
			if (quadros.size() < maxQuadros) {
				pagina.setPresente(1);
				quadros.add(pagina);
			} else {
				int tempPosicao = 0;
				boolean presente = false;
				for (Pagina pag : quadros) {
					if (pag.getId() == pagina.getId()) {
						presente = true;
					}
				}
				if (!presente) {
					for (int i = 0; i < quadros.size(); i++) {
						if (quadros.get(i).getTempo() > quadros.get(tempPosicao).getTempo()) {
							tempPosicao = i;
						}
					}
					quadros.get(tempPosicao).setPresente(0);
					quadros.remove(tempPosicao);
					pageFault += 1;
					miss += 1;
					quadros.add(pagina);
				}
			}
		}
		for (Pagina pagina : quadros) {
			pagina.setTempo(pagina.getTempo() + 1);
		}
		return pageFault;
	}

}
