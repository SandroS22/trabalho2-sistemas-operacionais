package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import main.Memoria;
import main.Pagina;

public class TestesMemoriaFifo {

	private Memoria memoria = new Memoria();
	private Pagina pagin1 = new Pagina(1);
	private Pagina pagin2 = new Pagina(2);
	private Pagina pagin3 = new Pagina(3);
	private Pagina pagin3_2 = new Pagina(3);
	private Pagina pagin3_3 = new Pagina(3);
	private Pagina pagin4 = new Pagina(4);
	private Pagina pagin5 = new Pagina(5);
	private Pagina pagin6 = new Pagina(6);

	@Test
	public void NumeroQuadrosIgualPaginasDistintas() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagin1);
		memoria.adicionar(pagin2);
		memoria.adicionar(pagin3);
		memoria.adicionar(pagin4);

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void UmaPaginaAmais() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagin1);
		memoria.adicionar(pagin2);
		memoria.adicionar(pagin3);
		memoria.adicionar(pagin4);
		memoria.adicionar(pagin5);

		assertEquals(1, memoria.fifo());
	}

	@Test
	public void UmaPaginaRepetida() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagin1);
		memoria.adicionar(pagin2);
		memoria.adicionar(pagin3);
		memoria.adicionar(pagin3_2);

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void DuasPaginasRepetidas() {
		memoria.setMaxQuadros(3);
		memoria.adicionar(pagin1);
		memoria.adicionar(pagin2);
		memoria.adicionar(pagin3);
		memoria.adicionar(pagin3_2);
		memoria.adicionar(pagin3_3);

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void ApagandoMaisAntigo() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagin1);
		memoria.adicionar(pagin2);
		memoria.adicionar(pagin3);
		memoria.adicionar(pagin4);
		memoria.adicionar(pagin5);

		// Esperando 1 pageFault
		assertEquals(1, memoria.fifo());
		// Espera o 3 como primeiro elemento da lista, ou seja, o mais antigo
		assertEquals(2, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagin1.isPresente());

		// Esvazia a lista de paginas e insere o 6
		memoria.setPaginas(new ArrayList<>());
		memoria.adicionar(pagin6);

		// Esperando 1 pageFault
		assertEquals(1, memoria.fifo());
		assertEquals(3, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagin2.isPresente());
	}

	@Test
	public void MaisAntigoNaoEhOMaisAEsquerdaDaLista() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagin1);
		memoria.adicionar(pagin2);
		memoria.adicionar(pagin3);
		memoria.adicionar(pagin4);
		memoria.adicionar(pagin5);

		// Esperando 1 pageFault
		assertEquals(1, memoria.fifo());
		// Espera o 3 como primeiro elemento da lista, ou seja, o mais antigo
		assertEquals(2, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagin1.isPresente());

		// Esvazia a lista de paginas e insere o 6
		memoria.setPaginas(new ArrayList<>());
		memoria.adicionar(pagin6);

		// Esperando 1 pageFault
		assertEquals(1, memoria.fifo());
		assertEquals(3, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagin2.isPresente());
	}
}
