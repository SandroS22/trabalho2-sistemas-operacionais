package main;

import java.util.concurrent.ThreadLocalRandom;

public class Inicio {

	public static void main(String[] args) {
		Memoria memoria = new Memoria(4);

		Integer paginasDiferentes = 8;

		Integer totalAcessos = 77777;

		for (int i = 0; i < totalAcessos; i++) {
			// O +1 é por conta da característica do método nextInt() que considera apenas
			// até o número anterior ao máximo
			int randomNum = ThreadLocalRandom.current().nextInt(0, paginasDiferentes + 1);
			memoria.adicionar(new Pagina(randomNum));
		}
		System.out.println("FIFO");
		System.out.println("Page Fault: " + memoria.fifo());
	}

}
