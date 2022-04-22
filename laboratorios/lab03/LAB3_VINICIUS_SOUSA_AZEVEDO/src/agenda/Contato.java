package agenda;

/**
 * Classe responsável por armazenar os dados de um contato, além de possibilitar a leitura e alteração destes dados.
 * 
 * @author Vinícius Sousa Azevedo.
 */
public class Contato {
	
	/**
	 * Nome do contato.
	 */
	private String nome;

	/**
	 * Sobrenome do contato.
	 */
	private String sobrenome;

	/**
	 * Telefone prioritário do contato.
	 */
	private String prioritario;

	/**
	 * Telefone para WhatsApp do contato.
	 */
	private String whatsApp;

	/**
	 * Telefone adicional do contato.
	 */
	private String adicional;

	/**
	 * Valor booleano que informa se o contato é favoritado (true) ou não (false).
	 */
	private boolean isFavorito;
	
	/**
	 * Constrói o contato, atribuindo os parâmetros passados para seus atributos caso nenhum deles seja nulo e caso o nome e sobrenome não sejam inválidos (vazios ou apenas com espaços).
	 * 
	 * @param nome nome do contato.
	 * @param sobrenome sobrenome do contato.
	 * @param prioritario telefone prioritário do contato.
	 * @param whatsApp telefone de WhatsApp do contato.
	 * @param adicional telefone adicional do contato.
	 */
	public Contato(String nome, String sobrenome, String prioritario, String whatsApp, String adicional) {
		
		String[] atributos = {nome, sobrenome, prioritario, whatsApp, adicional};
		
		for (int i = 0; i < atributos.length; i++) {
			if (atributos[i] == null) {
				throw new NullPointerException("INSIRA VALORES VÁLIDOS!");
			}
		}
		
		if (nome.trim().equals("") && sobrenome.trim().equals("")) {
			throw new IllegalArgumentException("INSIRA UM NOME VÁLIDO!");
		}
		
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.prioritario = prioritario;
		this.whatsApp = whatsApp;
		this.adicional = adicional;
		this.isFavorito = false;
	}
	
	/**
	 * Retorna o nome completo do contato.
	 * 
	 * @return o valor, em String, do nome completo do contato.
	 */
	public String getNomeCompleto() {
		return this.nome + " " + this.sobrenome;
	}
	
	/**
	 * Retorna o nome do contato.
	 * 
	 * @return o valor, em String, do nome do contato.
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Retorna o sobrenome do contato.
	 * 
	 * @return o valor, em String, do sobrenome do contato.
	 */
	public String getSobrenome() {
		return this.sobrenome;
	}

	/**
	 * Retorna o telefone prioritário do contato.
	 * 
	 * @return o valor, em String, do telefone prioritário do contato.
	 */
	public String getPrioritario() {
		return this.prioritario;
	}
	
	/**
	 * Retorna o telefone de WhatsApp do contato.
	 * 
	 * @return o valor, em String, do telefone de WhatsApp do contato.
	 */
	public String getWhatsApp() {
		return this.whatsApp;
	}
	
	/**
	 * Retorna o telefone adicional do contato.
	 * 
	 * @return o valor, em String, do telefone adicional do contato.
	 */
	public String getAdicional() {
		return this.adicional;
	}
	
	/**
	 * Permite a alteração do valor de setIsFavorito.
	 * 
	 * @param valor o novo valor, em boolean, a ser atribuido para setIsFavorito.
	 */
	public void setIsFavorito(boolean valor) {
		this.isFavorito = valor;
	}
	
	/**
	 * Permite a alteração do telefone prioritário do contato.
	 * 
	 * @param novoPrioritario o novo valor, em String, a ser atribuido pra o telefone prioritário do contato.
	 */
	public void setPrioritario(String novoPrioritario) {
		if (novoPrioritario == null ) {
			throw new NullPointerException("INSIRA UM NÚMERO VÁLIDO!");
		} else {
			this.prioritario = novoPrioritario;
		}
		
	}
	
	/**
	 * Retorna o valor, em String, com os dados do contato. 
	 *
	 * @return o valor, em String, com os dados do contato.
	 */
	@Override
	public String toString() {
		String retorno = "";
		String nomeCompleto = this.getNomeCompleto();
		
		if (this.isFavorito) {
			nomeCompleto = "❤️ " + nomeCompleto;
		}
		
		retorno += "\n" + nomeCompleto + "\n";
		
		if (!this.prioritario.trim().equals("")) {
			retorno += this.prioritario + " (Prioritário)\n";
		}
		
		if (!this.whatsApp.trim().equals("")) {
			retorno += this.whatsApp + " (Whatsapp)\n";
		}
		
		if (!this.adicional.trim().equals("")) {
			retorno += this.adicional + " (Adicional)\n";
		}
		return retorno;
	}
}
