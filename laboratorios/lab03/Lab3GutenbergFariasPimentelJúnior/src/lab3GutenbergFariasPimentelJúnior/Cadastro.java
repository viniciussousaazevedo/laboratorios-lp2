package lab3GutenbergFariasPimentelJúnior;

public class Cadastro {
	
	// Criar a classe Contato
	
	private String[] contatoNome = new String[101];
	private String[] contatoSobrenome = new String[101];
	private String[] contatoTelefone = new String[101];
	private String[] contatoTelefoneAlternativo = new String [101];

	private int indice;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String telefoneAlternativo;
    private boolean contatoExistente;
    
    
    
    public Cadastro(int indice, String nome, String sobrenome, String telefone, String telefoneAlternativo, boolean contatoExistente) {
    	
    }
    
    /*
     * Cadastra um contato na agenda em um índice indicado pelo usuário
     */
    public String cadastraContato (int indice, String nome, String sobrenome, String telefone, String telefoneAlternativo, boolean contatoExistente) {
    	// Verifica se um contato com o mesmo nome já existe
    	for (int i = 1;i < 101; i++) {
    		if (this.nome.equals(contatoNome[i]) && this.sobrenome.equals(contatoSobrenome[i])) {
    			this.contatoExistente = true;
    			break;
    		}
    	}
    	
    	/*
    	 * Cadastra um contato válido
    	 */
    	if (this.contatoExistente == true) {
    		return "Este contato já existe.";    		
    	} else {
    		contatoNome[this.indice] = this.nome;
    		contatoSobrenome[this.indice] = this.sobrenome;
    		contatoTelefone[this.indice] = this.telefone;
    		contatoNome[this.indice] = this.nome;
    		if (telefoneAlternativo.isEmpty()) {
    		} else {
    			contatoTelefoneAlternativo[this.indice] = this.telefoneAlternativo;
    		}
    		return "Contato cadastrado com sucesso!";
    	}
	}
    
    /*
     * Produz uma lista dos contatos cadastrados na agenda.
     */
    public String listaContatos() {
    	int contaVazios = 0;
    	for (int i = 1;i < 101; i++) {
    		if (contatoNome[i].equals(null)) {
    			contaVazios++;
    		} else {
    			if (Favoritos.favoritos.containsKey(i)) {
    				System.out.print("★ ");
    			}
    			System.out.println(contatoNome[i] + contatoSobrenome[i]);
    			System.out.println(contatoTelefone[i]);
    			if (contatoTelefoneAlternativo[i] != null) {
    				System.out.println(contatoTelefoneAlternativo[i]);
    			}
    			
    			if (contatoTags[i] != null) {
    				System.out.println(contatoTags[i]);
    			}
    	if (contaVazios == 100) {
    		return "Lista de contatos vazia";
    	}
    		}
    	}
    }
    
    /* 
    * Exibe os dados de um contato registrado na agenda de acordo com seu índice.
    * 
    * @param indice Um número natural maior ou gual a 1 e menor ou igual a 100
    */
    public String exibeContato (int indice) {
    		String contato = contatoNome[this.indice] + " " + contatoSobrenome[this.indice] + "\n" + contatoTelefone[this.indice];
    		if (contatoTelefoneAlternativo[this.indice] != null) {
    			contato += "\n" + contatoTelefoneAlternativo[this.indice] + "\n";
    		}
    		
    		for (int i = (this.indice*5)-5; i < this.indice*5; i++) {
    			if (Tags.contatoTags[i] != null) {
    				contato += Tags.contatoTags[i] + "  ";
    			} else {
    				continue;
    			}
    		}
    		return contato;
    	}
    }