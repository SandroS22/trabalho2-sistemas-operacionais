package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import main.Memoria;
import main.Pagina;

// assertEquals(x, y)
// x == Valor esperado
// y == Valor recebido para comparação 

public class TestesMemoriaFifo {

	private Memoria memoria = new Memoria();
	private Pagina pagina1 = new Pagina(1);
	private Pagina pagina2 = new Pagina(2);
	private Pagina pagina3 = new Pagina(3);
	private Pagina pagina3_2 = new Pagina(3);
	private Pagina pagina3_3 = new Pagina(3);
	private Pagina pagina3_4 = new Pagina(3);
	private Pagina pagina4 = new Pagina(4);
	private Pagina pagina4_2 = new Pagina(4);
	private Pagina pagina4_3 = new Pagina(4);
	private Pagina pagina5 = new Pagina(5);
	private Pagina pagina6 = new Pagina(6);

	@Test
	public void NumeroQuadrosIgualPaginasDistintas() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina4);

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void UmaPaginaAMais() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina4);
		memoria.adicionar(pagina5);

		assertEquals(1, memoria.fifo());
	}

	@Test
	public void CemaPaginasAMais() {
		memoria.setMaxQuadros(5);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina4);
		memoria.adicionar(pagina5);
		for (int i = 6; i < 106; i++) {
			memoria.adicionar(new Pagina(i));
		}

		assertEquals(100, memoria.fifo());
	}

	@Test
	public void UmaPaginaRepetida() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina3_2);

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void UmaPaginRepetidaCentenasDeVezes() {
		memoria.setMaxQuadros(3);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		for (int i = 0; i < 200; i++) {
			memoria.adicionar(new Pagina(3));
		}

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void DuasPaginaRepetidas() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina3_2);
		memoria.adicionar(pagina3_3);
		memoria.adicionar(pagina3_4);
		memoria.adicionar(pagina4);
		memoria.adicionar(pagina4_2);
		memoria.adicionar(pagina4_3);

		assertEquals(0, memoria.fifo());
	}

	@Test
	public void ApagandoMaisAntigo() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina4);
		memoria.adicionar(pagina5);

		// Esperando 1 pageFault
		assertEquals(1, memoria.fifo());
		// Espera o 3 como primeiro elemento da lista, ou seja, o mais antigo
		assertEquals(2, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagina1.isPresente());

		// Esvazia a lista de paginas e insere o 6
		memoria.setPaginas(new ArrayList<>());
		memoria.adicionar(pagina6);

		// Esperando 1 pageFault
		assertEquals(1, memoria.fifo());
		assertEquals(3, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagina2.isPresente());
	}
}
