package agenda;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Lógica para ler de arquivos csv os dados necessários e povoar uma agenda. 
 * 
 * @author nazarenoandrade
 * @author Vinícius Sousa Azevedo.
 *
 */
public class LeitorDeAgenda {

	/**
	 * Coluna, no arquivo csv, que declara a posição do contato.
	 */
	private static final int COLUNA_POSICAO = 0;

	/**
	 * Coluna, no arquivo csv, que declara o nome do contato.
	 */
	private static final int COLUNA_NOME = 1;

	/**
	 * Coluna, no arquivo csv, que declara o sobrenome do contato.
	 */
	private static final int COLUNA_SOBRENOME = 2;

	/**
	 * Coluna, no arquivo csv, que declara o telefone prioritário do contato.
	 */
	private static final int COLUNA_PRIORITARIO = 3;

	/**
	 * Coluna, no arquivo csv, que declara o telefone de WhatsApp do contato.
	 */
	private static final int COLUNA_WHATSAPP = 4;

	/**
	 * Coluna, no arquivo csv, que declara o telefone adicional do contato.
	 */
	private static final int COLUNA_ADICIONAL = 5;


	/**
	 * Lê contatos de um arquivo CSV e os coloca em uma agenda.
	 * @param arquivoContatos Caminho para arquivo contendo contatos.
	 * @param agenda A agenda a manipular.
	 * @return O número de contatos adicionados à agenda.
	 * @throws IOException Caso não tenhamos permissão de ler o arquivo.
	 * @throws FileNotFoundException Caso o arquivo não exista.
	 */
	public int carregaContatos(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		int carregados = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(arquivoContatos))) {
			String linha;
			while ((linha = br.readLine()) != null) {
				carregados += 1;
				if (carregados == 1) {
					// pulamos a primeira linha, o cabeçalho
					continue;
				}
				String[] campos = linha.split(",");
				processaLinhaCsvContatos(campos, agenda);
			}
		}
		
		return carregados-1;
	}

	
	/**
	 * Coloca na agenda os dados de uma linha do arquivo de agenda inicial. 
	 * 
	 * @param campos As informações lidas do csv. 
	 * @param agenda A agenda a manipular. 
	 */
	private void processaLinhaCsvContatos(String[] campos, Agenda agenda) {
		int posicao = Integer.parseInt(campos[COLUNA_POSICAO]);
		String nome = campos[COLUNA_NOME].trim();
		String sobrenome = campos[COLUNA_SOBRENOME].trim();
		String prioritario = campos[COLUNA_PRIORITARIO].trim();
		String whatsApp = campos[COLUNA_WHATSAPP].trim();
		String adicional = campos[COLUNA_ADICIONAL].trim();

		agenda.cadastraContato(posicao, nome, sobrenome, prioritario, whatsApp, adicional);
	}

}
