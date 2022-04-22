package lab2;

/**
 *  Representação da saúde do aluno, sendo possível analisar estado físico, estado mental, estado geral e definição de emoji que represente como o discente está se sentindo.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class Saude {
	
	/**
	 * Representa, em uma String, como o aluno se sente quanto à sua saúde mental, podendo ser "fraca" ou "boa".
	 */
	private String statusMental;

	/**
	 * Representa, em uma String, como o aluno se sente quanto à sua saúde física, podendo ser "fraca" ou "boa".
	 */
	private String statusFisico;

	/**
	 * Representa, em uma String, o emoji que define melhor como o discente está se sentindo.
	 */
	private String emoji;
	
	/**
	 * Constrói a saúde do aluno, definindo, por padrão, o emoji como uma String vazia e a saúde, tanto física quanto mental, como "boa".
	 */
	public Saude() {
		this.statusMental = "boa";
		this.statusFisico = "boa";
		this.emoji = "";
	}
	
	/**
	 * Retorna uma String que representa o status geral da saúde do aluno. Caso a saúde física e mental estejam definidas como "boa", o status de saúde geral também será "boa". Caso a saúde física e mental estejam definidas como "fraca", o status de saúde geral também sera "fraca". Caso os dois estados de saúde estejam em condições diferentes, o status de saúde geral será "ok". Além disso, o status de saúde pode ser acompanhado de emoji, caso ele seja definido e caso não haja nenhuma alteração no status de saúde físico ou mental.
	 * 
	 * @return é a String que contém a definição final do status de saúde geral.
	 */
	public String getStatusGeral() {
		String status;
		
		if (this.statusMental.equals("fraca") && this.statusFisico.equals("fraca")) {
			status = "fraca " + this.emoji;
		} else if (this.statusMental.equals("boa") && this.statusFisico.equals("boa")) {
			status = "boa " + this.emoji;
		} else {
			status = "ok " + this.emoji;
		}
		
		return status;
	}
	
	/**
	 * Permite definir o emoji que representa como o aluno se sente, sendo passado como parâmetro em uma String.
	 * 
	 * @param valor é o valor em String que será atribuído ao emoji.
	 */
	public void definirEmoji(String valor) {
		this.emoji = valor;

	}

	/**
	 * Permite definir um novo estado de saúde mental, além de redefinir o emoji como uma String vazia, já que houve alteração no status de saúde.
	 * 
	 * @param valor é o valor em String que será atribuído ao status de saúde mental.
	 */
	public void defineSaudeMental(String valor) {
		this.statusMental = valor;
		this.emoji = "";
	}
	
	/**
	 * Permite definir um novo estado de saúde física, além de redefinir o emoji como uma String vazia, já que houve alteração no status de saúde.
	 * 
	 * @param valor é o valor em String que será atribuído ao status de saúde física.
	 */
	public void defineSaudeFisica(String valor) {
		this.statusFisico = valor;
		this.emoji = "";
	}
}
