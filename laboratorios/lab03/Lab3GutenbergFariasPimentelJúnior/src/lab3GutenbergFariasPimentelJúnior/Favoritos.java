package lab3GutenbergFariasPimentelJúnior;

public class Favoritos {
	/* Recebe o índice de um contato na classe Contatos, e armazena como um favorito em um índice da lista de favoritos
	 * Quantidade máxima de favoritos é igual a 10.
	 */
    private int[] favoritos = new int [11];
    private int indiceContato;
    private int indiceFavorito;

    /* Adiciona um contato preexistente à lista de favoritos.
     * 
     * @param indiceContato Informa qual contato da agenda telefônica será favoritado.
     * @param indiceFavorito Informa qual índice da lista de favoritos será utilizado.
     */
    public void adicionaFavorito(int indiceContato, int indiceFavorito){
    	favoritos[this.indiceFavorito] = this.indiceContato;
    }
        
    /* 
     * Produz uma lista de todos os contatos favoritos e dos dados respectivos armazenados
     */
    public  String listaFavoritos() {
    	int favoritosVazios = 0;
    	for (int fav = 1; fav < 11; fav++) {
    		if (favoritos[fav] == 0) {
    			favoritosVazios++;
    		} else {
	    		System.out.println("★ "+Cadastro.contatoNome[favorito]+Cadastro.contatoSobrenome[favorito]);
	    		System.out.println(Contatos.contatoTelefone[favorito]);
	    		if (Contatos.contatoTelefoneAlternativo[favorito] != null) {
	    			System.out.println(Contatos.contatoTelefoneAlternativo[favorito]);
	    		}
    		}
    	}
    	if (favoritosVazios == 10) {
    }
}    