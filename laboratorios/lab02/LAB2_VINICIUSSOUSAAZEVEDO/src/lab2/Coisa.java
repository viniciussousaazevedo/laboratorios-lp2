package lab2;

import static java.lang.Double.parseDouble;
import java.util.Scanner;

/**
 * Representação do Controle Institucional de Situação Acadêmica da UFCG, contendo o método principal no qual inicializa objetos e opera com valores específicos para, inicialmente, testar o funcionamento de classes Instanciáveis relacionadas ao COISA.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class Coisa {

	/**
	 * É o método principal, no qual inicializa objetos relacionados a RegistroTempoOnline, Disciplina, RegistroFinancas e Saude para realização de operações, inicialmente a nível de teste de entradas e saídas.
	 * 
	 * @param args array de String necessário para identificação, por parte do Java, do método principal.
	 */
    public static void main(String[] args) {
		RegistroTempoOnline tempoLP2 = new RegistroTempoOnline("LP2", 30);
		tempoLP2.adicionaTempoOnline(10);
		System.out.println(tempoLP2.atingiuMetaTempoOnline());
		tempoLP2.adicionaTempoOnline(10);
		tempoLP2.adicionaTempoOnline(10);
		System.out.println(tempoLP2.atingiuMetaTempoOnline());
		tempoLP2.adicionaTempoOnline(2);
		System.out.println(tempoLP2.atingiuMetaTempoOnline());
		System.out.println(tempoLP2.toString());

		Disciplina prog2 = new Disciplina("PROGRAMACAO 2");
		prog2.cadastraHoras(4);
		prog2.cadastraNota(1, 5.0);
		prog2.cadastraNota(2, 6.0);
		prog2.cadastraNota(3, 7.0);
		System.out.println(prog2.aprovado());

		prog2.cadastraNota(4, 10.0);
		System.out.println(prog2.aprovado());
		System.out.println(prog2.toString());
   	 
		RegistroFinancas minhaFinanca = new RegistroFinancas(100000);   	 
		minhaFinanca.aumentaReceita(12000, 1);
		minhaFinanca.aumentaReceita(72100, 2);
		minhaFinanca.pagaDespesa(20000);
		System.out.println(minhaFinanca.exibeFontes());
		System.out.println(minhaFinanca.toString());


		Saude saude = new Saude();
		System.out.println(saude.getStatusGeral());
		saude.defineSaudeMental("boa");
		saude.defineSaudeFisica("boa");
		System.out.println(saude.getStatusGeral());

		saude.defineSaudeMental("fraca");
		saude.defineSaudeFisica("fraca");
		System.out.println(saude.getStatusGeral());

		saude.defineSaudeMental("boa");
		saude.defineSaudeFisica("fraca");
		System.out.println(saude.getStatusGeral());
    }   


}

