package lab3GutenbergFariasPimentelJúnior;

public class Tags {

		private String[] contatoTags = new String[500];
		
		private int indiceContato;
		private int posicaoTag;
		private String tag;
		private int indiceTag;
		
		/* Adiciona uma tag a um contato
		 * 
		 * @param indiceContato Índice do contato na agenda; 1<=indiceContato<=100
		 * @param posicaoTag Posição da tag em uma das cinco posições para tags que um contato pode usar
		 * @param tag Uma tag a ser associada ao contato
		 */
		public void adicionaTag(int indiceContato, int posicaoTag, String tag) {
			this.indiceTag = (this.indiceContato * 5) + this.posicaoTag - 1;
			contatoTags[indiceTag] = this.tag;
		}
	
		/* Exibe todas as tags associadas a um contato
		 * 
		 * @param indiceContato Índice do contato na agenda; 1<=indiceContato<=100
		 */
		public String exibeTags(int indiceContato) {
			String tagsContato;
			for (int i = (this.indiceContato*5)-5; i == this.indiceContato*5;i++) {
				if (contatoTags.equals(null)) {
					continue;
				} else {
					tagsContato += contatoTags[i] + "  ";
				}
			return tagsContato.strip();
		}	
	}
}