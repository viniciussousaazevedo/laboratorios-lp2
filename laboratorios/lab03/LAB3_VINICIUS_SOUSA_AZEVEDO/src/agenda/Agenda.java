package agenda;

/**
 * Uma agenda que mantém uma lista de contatos com 100 posições, podendo até 10 serem favoritados, realizando operações sob esses elementos. 
 * 
 * @author nazareno.
 * @author Vinícius Sousa Azevedo.
 *
 */
public class Agenda {
	
	/**
	 * Constante que define o tamanho da lista de contatos.
	 */
	private static final int TAMANHO_AGENDA = 100;

	/**
	 * Constante que define o tamanho da lista de favoritos.
	 */
	private static final int TAMANHO_FAVORITOS = 10;
	
	/**
	 * Lista de contatos.
	 */
	private Contato[] contatos;

	/**
	 * Lista de favoritos.
	 */
	private Contato[] favoritos;

	
	/**
	 * Constrói a agenda inicializando a lista de contatos com 100 posições e a lista de favoritos com 10 posições.
	 */
	public Agenda() {
		this.contatos = new Contato[TAMANHO_AGENDA];
		this.favoritos = new Contato[TAMANHO_FAVORITOS];
	}

	/**
	 * Analisa se a posição passada como parâmetro é valida para operar na lista de contatos, isto é, entre 0 e 99. Este método é público pois é utilizado na classe principal.
	 * 
	 * @param posicao é o inteiro que representa a posição a ser analisada.
	 * @return um valor booleano que mostra se a posição é válida (true) ou não (false).
	 */
	public boolean isPosicaoValidaContatos(int posicao) {
		boolean retorno = true;
		
		if (posicao < 0 || posicao >= TAMANHO_AGENDA) {
			retorno = false;
		}
		
		return retorno;
	}
	

	/**
	 * Analisa se a posição passada como parâmetro é valida para operar na lista de favoritos, isto é, entre 0 e 9.
	 * 
	 * @param posicao é o inteiro que representa a posição a ser analisada.
	 * @return um valor booleano que mostra se a posição é válida (true) ou não (false).
	 */
	private boolean isPosicaoValidaFavoritos(int posicao) {
		boolean retorno = true;
		
		if (posicao < 0 || posicao >= TAMANHO_FAVORITOS) {
			retorno = false;
		}
		
		return retorno;
	}
	
	/**
	 * Analisa se o contato a ser favoritado já está preente na lista de favoritos.
	 * 
	 * @param posicao é a posição do contato na lita de contatos.
	 * @return um valor booleano que mostra se o contato é repetido (true) ou não (false).
	 */
	private boolean isFavoritoRepetido(int posicao) {
		boolean retorno = false;
		
		for (Contato favorito : this.favoritos) {
			if (favorito != null && this.contatos[posicao].getNomeCompleto().equals(favorito.getNomeCompleto())) {
				retorno = true;
				break;
			}
		}
		
		return retorno;
	}
	

	/**
	 * Analisa se a posição passada como parâmetro contém um valor nulo na lista a ser informada.
	 * 
	 * @param posicao é a posição a ser analisada na lista.
	 * @param listaContato é a lista do tipo Contato a esr analisada, podendo, nesse caso, ser a lista de 100 contatos ou do 10 contatos favoritados.
	 * @return um valor booleano que mostra se o contato é nulo (true) ou não (false).
	 */
	private boolean isContatoNull(int posicao, Contato[] listaContato) {
		boolean retorno = false;
		
		if (listaContato.equals(this.favoritos)) {
			if (this.favoritos[posicao] == null) {
				retorno = true;
			}
		} else {
			if (this.contatos[posicao] == null) {
				retorno = true;
			}
		}
		
		
		return retorno;
	}
	
	/**
	 * Responsável por cadastrar um contato na lista contatos do tipo Contato[]. Caso a poição do contato na lista de contatos seja de um outro contato pré-existente, este deixa de estar cadatrado e dá lugar ao novo sendo registrado.
	 * 
	 * @param posicao a posição na qual o contato deve ser armazenado na lita.
	 * @param nome o nome do contato.
	 * @param sobrenome o sobrenome do contato.
	 * @param prioritario o telefone prioritário.
	 * @param whatsApp o telefone para WhatsApp.
	 * @param adicional o telefone adicional.
	 * @return um valor booleano que mostra se o contato foi cadastrado (true) ou se houve problemas quanto à posição inválida ou contato repetido (false).
	 */
	public boolean cadastraContato(
			int posicao,
			String nome,
			String sobrenome, 
			String prioritario, 
			String whatsApp, 
			String adicional) {
		boolean retorno = true;
		posicao--;
		if (isPosicaoValidaContatos(posicao)) {
			
			String nomeCompleto = nome + " " + sobrenome;
			
			for (Contato contato : this.contatos) {
				if (contato != null && nomeCompleto.equals(contato.getNomeCompleto())) {
					retorno = false;
					break;
				}
			}
			
			if (retorno) {
				Contato novoContato = new Contato(nome, sobrenome, prioritario, whatsApp, adicional);
				this.contatos[posicao] = novoContato;
			}
		} else {
			retorno = false;
		}
		
		
		return retorno;
		
	}
	

	/**
	 * Responsável por cadastrar um contato na lista favoritos do tipo Contato[]. Caso a posição do favorito na lista favoritos seja de um contato pré-existente, este deixa de ser um favorito para dar lugar ao novo sendo cadastrado.
	 * 
	 * @param posicaoContato é a posição do contato a ser favoritado na lista contatos do tipo Contato[].
	 * @param posicaoFavorito é a posição na qual o contato deve ser favoritado na lista favoritos do tipo Contato[].
	 * @return um valor booleano que mostra se o contato foi favoritado (true) ou não (false).
	 */
	public boolean cadastraFavorito(int posicaoContato, int posicaoFavorito) {
		boolean retorno = true;
		posicaoContato--;
		posicaoFavorito--;
		
		if (isPosicaoValidaFavoritos(posicaoFavorito) && !isFavoritoRepetido(posicaoContato)) {
			
			if (!isContatoNull(posicaoFavorito, this.favoritos)) {
				this.favoritos[posicaoFavorito].setIsFavorito(false);
			}
			
			this.favoritos[posicaoFavorito] = this.contatos[posicaoContato];
			this.contatos[posicaoContato].setIsFavorito(true);
		} else {
			retorno = false;
		}
		
		return retorno;
	}
	

	/**
	 * Responsável por exibir os dados do contato com base em sua posição. Caso seja informada uma posição inválida, o método retorna a mensagem de erro referente.
	 * 
	 * @param posicao a posição a ser declarada para exibir o contato em questão.
	 * @return o valor, em String, referente aos dados do contato ou à mensagem de erro.
	 */
	public String exibeContato(int posicao) {
		String retorno;
		posicao--;
		if (isPosicaoValidaContatos(posicao) && !isContatoNull(posicao, this.contatos)) {
			retorno = this.contatos[posicao].toString();
		} else {
			retorno = "POSIÇÃO INVÁLIDA!\n";
		}
		return retorno;
	}
	

	/**
	 * Responsável por exibir os dados do(s) contato(s) com base em seu nome ou sobrenome, exibindo todos os que possuem o nome ou sobrenome em comum. Caso seja informada um nome ou sobrenome inválido, o método retorna a mensagem de erro referente.
	 * 
	 * @param nomeOuSobrenome o nome ou sobrenome para exibir o(s) contato(s) em questão.
	 * @return o valor, em String, referente aos dados do contato ou à mensagem de erro.
	 */
	public String exibeContato(String nomeOuSobrenome) {
		String retorno = "";
		
		for (int i = 0; i < this.contatos.length; i++) {
			if (!isContatoNull(i, this.contatos) && (this.contatos[i].getNome().equals(nomeOuSobrenome) || this.contatos[i].getSobrenome().equals(nomeOuSobrenome))) {
				retorno += this.contatos[i].toString();
			}
		}

		if (retorno.trim().equals("")) {
			retorno = "NÃO HÁ CONTATOS COM ESTE NOME\n";
		}
		
		return retorno;
	}
	
	/**
	 * Responsável por listar todos os contatos cadastrados na lista contatos do tipo Contato[], exibindo seu nome, sobrenome e posição na lista. Caso não haja contatos cadastrado, será retornado apenas uma quebra de linha ("\n").
	 * 
	 * @return o valor, em String, referente à lista dos contatos.
	 */
	public String listaContatos() {
		String retorno = "";
		
		for (int i = 0; i < this.contatos.length; i++) {
			if (!this.isContatoNull(i, this.contatos)) {
				retorno += "\n" + (i+1) + " - " + this.contatos[i].getNomeCompleto();
			}
		}
		
		retorno += "\n";
		return retorno;
	}
	
	/**
	 * Responsável por listar todos os contatos cadastrados na lista favoritos do tipo Contato[], exibindo seu nome, sobrenome e posição na lista. Caso não haja contatos cadastrado, será retornado apenas uma quebra de linha ("\n").
	 * 
	 * @return o valor, em String, referente à lista dos contatos.
	 */
	public String listaFavoritos() {
		String retorno = "";
		
		for (int i = 0; i < this.favoritos.length; i++) {
			if (!isContatoNull(i, this.favoritos)) {
				retorno += "\n" + (i+1) + " - " + this.favoritos[i].getNomeCompleto();
			}
		}
		retorno += "\n";
		return retorno;
	}

	/**
	 * Responsável por permitir a edição do telefone prioritário de um contato identificado por sua posição na lista contatos do tipo Contato[].
	 * 
	 * @param posicao a posição do contato que terá um novo telefone prioritário.
	 * @param novoPrioritario o valor, em String, do novo telefone prioritário.
	 */
	public void editarPrioritario(int posicao, String novoPrioritario) {
		posicao--;
		this.contatos[posicao].setPrioritario(novoPrioritario);
	}

	/**
	 * Responsável por permitir a remoção de um contato favoritado identificado por sua posição na lista favoritos do tipo Contato[].
	 * 
	 * @param posicao a posição do contato que deverá ser excluído.
	 * @return um valor booleano que mostra se o contato foi excluído (true) ou se houve algum erro quanto à posição informada (false).
	 */
	public boolean apagarFavorito(int posicao) {
		posicao--;
		boolean retorno = true;
		
		if (isPosicaoValidaFavoritos(posicao) && !isContatoNull(posicao, this.favoritos)) {
			this.favoritos[posicao].setIsFavorito(false);
			this.favoritos[posicao] = null;
		} else {
			retorno = false;
		}
		
		return retorno;
	}

	/**
	 * Responsável por permitir a remoção de um contato identificado por sua posição na lista contatos do tipo Contato[].
	 * 
	 * @param posicao a posição do contato que deverá ser excluído.
	 * @return um valor booleano que mostra se o contato foi excluído (true) ou se houve algum erro quanto à posição informada (false).
	 */
	public boolean apagarContato(int posicao) {
		posicao--;
		boolean retorno = true;
		
		if (isPosicaoValidaContatos(posicao) && !isContatoNull(posicao, this.contatos)) {
			this.contatos[posicao] = null;
		} else {
			retorno = false;
		}
		
		return retorno;
	}
}
