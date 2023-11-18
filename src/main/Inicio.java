package main;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Inicio {

	public static void main(String[] args) {
		Memoria memoria = new Memoria(3);

		Integer paginasDiferentes = 5;

		Integer totalAcessos = 14;

//		for (int i = 0; i < totalAcessos; i++) {
//			// O +1 é por conta da característica do método nextInt() que considera apenas
//			// até o número anterior ao máximo
//			int randomNum = ThreadLocalRandom.current().nextInt(0, paginasDiferentes + 1);
//			memoria.adicionar(new Pagina(randomNum));
//		}
		List<Pagina> pags = new ArrayList<>();
		pags.add(new Pagina(1));
		pags.add(new Pagina(2));
		pags.add(new Pagina(3));
		pags.add(new Pagina(4));
		pags.add(new Pagina(5));
		pags.add(new Pagina(1));
		pags.add(new Pagina(2));
		pags.add(new Pagina(1));
		pags.add(new Pagina(2));
		pags.add(new Pagina(4));
		pags.add(new Pagina(5));
		pags.add(new Pagina(2));
		pags.add(new Pagina(3));
		pags.add(new Pagina(5));
		
		memoria.setPaginas(pags);

		System.out.println("FIFO");
		System.out.println("Page Fault: " + memoria.fifo());

		System.out.println("LRU");
		System.out.println("Page Fault: " + memoria.lru());
	}

}
