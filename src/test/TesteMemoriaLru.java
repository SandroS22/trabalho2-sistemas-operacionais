package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import main.Memoria;
import main.Pagina;

public class TesteMemoriaLru {

	private Memoria memoria = new Memoria();
	private Pagina pagina1 = new Pagina(1);
	private Pagina pagina1_2 = new Pagina(1);
	private Pagina pagina1_3 = new Pagina(1);
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

		assertEquals(0, memoria.lru());
	}

	@Test
	public void UmaPaginaAMais() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina4);
		memoria.adicionar(pagina5);

		assertEquals(1, memoria.lru());
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

		assertEquals(100, memoria.lru());
	}

	@Test
	public void UmaPaginaRepetida() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
		memoria.adicionar(pagina3);
		memoria.adicionar(pagina3_2);

		assertEquals(0, memoria.lru());
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

		assertEquals(0, memoria.lru());
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

		assertEquals(0, memoria.lru());
	}

	@Test
	public void ApagandoMaisAntigo() {
		memoria.setMaxQuadros(4);
		memoria.adicionar(pagina1);
		memoria.adicionar(pagina2);
//		1 chamado novamente, tornando o 2 o mais velho
		memoria.adicionar(pagina1_2);
		memoria.adicionar(pagina3);
//		1 chamado mais uma vez, tornando o 3 mais velho que o 1
		memoria.adicionar(pagina1_3);
		memoria.adicionar(pagina4);
		memoria.adicionar(pagina5);

//		O 2 é removido deixando o 3 como mais velho
		assertEquals(1, memoria.lru());
		assertEquals(1, memoria.getQuadros().get(0).getId());
		assertEquals(1, pagina1.isPresente());
		assertEquals(0, pagina2.isPresente());

		memoria.setPaginas(new ArrayList<>());
		memoria.adicionar(pagina6);

//		O 3 é removido deixando o 1 como mais velho
		assertEquals(1, memoria.lru());
		assertEquals(1, memoria.getQuadros().get(0).getId());
		assertEquals(0, pagina3.isPresente());

	}
}
