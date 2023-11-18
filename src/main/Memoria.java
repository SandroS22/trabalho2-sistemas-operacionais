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

	public void setQuadros(List<Pagina> quadros) {
		this.quadros = quadros;
	}

	public void adicionar(Pagina pagina) {
		paginas.add(pagina);
	}

	public Integer fifo() {
		Integer pageFault = 0;
		for (Pagina pagina : paginas) {
			boolean presente = false;
			for (Pagina pag : quadros) {
				if (pag.getId() == pagina.getId()) {
					presente = true;
				}
			}
			if (quadros.size() < maxQuadros) {
				pagina.setPresente(1);
				if (!presente) {
					quadros.add(pagina);
				}
			} else {
				int tempPosicao = 0;
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

	public Integer lru() {
		Integer pageFault = 0;
		for (Pagina pagina : paginas) {
			boolean presente = false;
			int idPresente = -1;
			for (Pagina pag : quadros) {
				if (pag.getId() == pagina.getId()) {
					presente = true;
					idPresente = pag.getId();
				}
			}
			if (quadros.size() < maxQuadros) {
				pagina.setPresente(1);
				if (!presente) {
					quadros.add(pagina);
				} else {
					for (Pagina p : quadros) {
						if (presente && p.getId() == idPresente) {
							p.setTempo(0);
						}
					}
				}
			} else {
				int tempPosicao = 0;
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
				} else {
					for (Pagina p : quadros) {
						if (presente && p.getId() == idPresente) {
							p.setTempo(0);
						}
					}
				}
			}
			for (Pagina p : quadros) {
				p.setTempo(p.getTempo() + 1);
			}
		}
		return pageFault;
	}

}
