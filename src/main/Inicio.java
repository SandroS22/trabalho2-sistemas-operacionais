package main;

import java.util.concurrent.ThreadLocalRandom;

public class Inicio {

	public static void main(String[] args) {
		Memoria memoria = new Memoria(100);

		Integer paginasDiferentes = 256;

		Integer totalAcessos = 100_000;

		for (int i = 0; i < totalAcessos; i++) {
			// O +1 é por conta da característica do método nextInt() que considera apenas até o número anterior ao máximo
			int randomNum = ThreadLocalRandom.current().nextInt(0, paginasDiferentes + 1);
			memoria.adicionar(new Pagina(randomNum));
		}
		System.out.println("sucesso");
	}

}
