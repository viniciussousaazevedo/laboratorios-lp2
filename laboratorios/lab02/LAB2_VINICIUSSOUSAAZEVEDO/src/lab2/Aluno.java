package lab2;

import java.util.ArrayList;

/**
 * Representação de um estudante quanto à: Disciplinas em curso (com análises voltadas para notas e registros de tempo online dedicado e esperado), registro de finanças e estado de saúde. Para cada um desses aspectos, foram criados objetos que já estão documentados e que processam os dados passados, retornando os valores devidos.
 * 
 * @author Vinícius Sousa Azevedo
 */
public class Aluno {
    
    /**
     * Representação, em ArrayList do tipo Disciplina, da lista de disciplinas nas quais o aluno possui, sendo esse objeto responsável pela parte de atribuição de notas e média do aluno. Como se trata de um ArrayList, é possível adicionar quantas disciplinas for preciso.
     */
    private ArrayList<Disciplina> disciplinas;

    /**
     * Representação, em ArrayList do tipo RegistroTempoOnline, da lista de disciplinas nas quais o aluno possui, sendo esse objeto responsável pela parte de tempo online dedicado e esperado do discente. Como se trata de um ArrayList, é possível adicionar quantos registros for preciso.
     */
    private ArrayList<RegistroTempoOnline> registrosTempoOnline;

    /**
     * Representação, do tipo RegistroFinancas, da parte financeira do aluno, sendo esse objeto responsável pela parte voltada à tratamento de receitas e despesas.
     */
    private RegistroFinancas financas;

    /**
     * Representação, do tipo Saude, da parte de saúde do aluno, sendo esse objeto responsável pela parte voltada à definição de emojis que o representem, além de estado físico e mental.
     */
    private Saude saude;

    /**
     * Constrói o aluno, inicializando lista do tipo Disciplina, lista do tipo RegistroTempoOnline, o objeto do tipo RegistroFinancas (com a receita inicial do tipo familiar igual ao valor inteiro de zero centavos) e o objeto do tipo Saude.
     */
    public Aluno() {
        this.disciplinas = new ArrayList<Disciplina>();
        this.registrosTempoOnline = new ArrayList<RegistroTempoOnline>();
        this.financas = new RegistroFinancas(0);
        this.saude = new Saude();
    }

    /**
	 * Constrói o registro de tempo online de uma disciplina do aluno a partir do nome da disciplina declarada nos parâmetros e o armazena na lista do tipo RegistroTempoOnline. Além disso, possui 120 horas de dedicação esperada pela disciplina (Definido por padrão). Como o registro acabou de ser criado, o tempo de dedicação atual do aluno é definido como zero horas, podendo ser incrementado.
	 * 
	 * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 */
    public void registroTempoOnline(String nomeDisciplina) {
        RegistroTempoOnline registro = new RegistroTempoOnline(nomeDisciplina);
        this.registrosTempoOnline.add(registro);
    }

    /**
	 * Constrói o registro de tempo online do aluno a partir do nome e do tempo online esperado de uma disciplina. Como o registro acabou de ser criado, o tempo de dedicação atual do aluno é definido como zero horas, podendo ser incrementado.
	 * 
	 * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 * @param TempoOnlineEsperado define, em um valor inteiro de horas, o tempo online esperado pela disciplina.
	 */
    public void registroTempoOnline(String nomeDisciplina, int TempoOnlineEsperado) {
        RegistroTempoOnline registro = new RegistroTempoOnline(nomeDisciplina, TempoOnlineEsperado);
        this.registrosTempoOnline.add(registro);
    }

    /**
	 * Incrementa o tempo dedicado pelo aluno no decorrer da disciplina.
	 * 
     * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 * @param tempo indica, em um valor inteiro de horas, quanto deve ser incrementado à quantidade de tempo total dedicada pelo aluno na disciplina.
	 */
    public void adicionaTempoOnline(String nomeDisciplina, int tempo) {
        for (RegistroTempoOnline registro : this.registrosTempoOnline) {
            if (registro.getNomeDisciplina().equals(nomeDisciplina)) {
                registro.adicionaTempoOnline(tempo);
            }
        }
    }

    /**
	 * Retorna um valor booleano que informa se o aluno conseguiu atingir a quantidade de horas de dedicação esperadas pela disciplina. É importante afirmar que o aluno não tem limite máximo de dedicação.
	 * 
     * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 * @return é o próprio valor booleano, retornando "true" se o aluno conseguiu atingir a meta de tempo online e "false" caso contrário.
	 */
    public boolean atingiuMetaOnline(String nomeDisciplina) {
        boolean retorno = false;
        for (RegistroTempoOnline registro : this.registrosTempoOnline) {
            if (registro.getNomeDisciplina().equals(nomeDisciplina)) {
                retorno = registro.atingiuMetaTempoOnline();
            }
        }

        return retorno;
    }

    /**
	 * Retorna a String que resume as informações gerais do registro de tempo online do aluno pela disciplina.
	 * 
     * @param nomeDisciplina é o nome da disciplina, que serve como base para identificação da disciplina a ser acompanhada.
	 * @return é a String que contém as informações principais da análise, sendo elas o nome da disciplina e a comparação entre o tempo dedicado pelo aluno e o tempo online esperado pela disciplina.
	 */
    public String registroOnlineToString(String nomeDisciplina) {
        String retorno = "";
        for (RegistroTempoOnline registro : this.registrosTempoOnline) {
            if (registro.getNomeDisciplina().equals(nomeDisciplina)) {
                retorno = registro.toString();
            }
        }
        return retorno;
    }

    /**
	 * Constrói a disciplina com base apenas em seu nome e a adiciona na lista do tipo Disciplina. Como não são declarados detalhes como número de notas e pesos de cada nota, é considerado o padrão de 4 notas com pesos iguais (ou seja, a média final será do tipo aritmética).
	 * 
	 * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 */
    public void cadastraDisciplina(String nomeDisciplina) {
        Disciplina d = new Disciplina(nomeDisciplina);
        disciplinas.add(d);
    }

    /**
	 * Incrementa a quantidade de horas dedicada pelo aluno na disciplina em questão.
	 * 
     * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 * @param horas é, em um valor inteiro, a quantidade de horas a ser incrementada.
	 */
    public void cadastraHoras(String nomeDisciplina, int horas) {
        for (Disciplina d : this.disciplinas) {
            if (d.getNomeDisciplina().equals(nomeDisciplina)) {
                d.cadastraHoras(horas);
            }
        }
    }

    /**
	 * Cadastra uma das notas do aluno, sendo necessário informar, em double, o valor da nota e, em inteiro, o número da nota na ordem de aplicação (isto é, se é a primeira nota, segunda, terceira, etc.). Caso alguma nota seja cadastrada mais de uma vez (no mesmo número na ordem de aplicação), ela será substituída pela última informada.
	 * 
     * @param nomeDisciplina é, em String, a representação do nome da disciplina.
     * @param nota é, em um valor inteiro, o número da nota na ordem de aplicação.
	 * @param valorNota é, em double, o valor da nota a ser cadastrada.
	 */
    public void cadastraNota(String nomeDisciplina, int nota, double valorNota) {
        for (Disciplina d : this.disciplinas) {
            if (d.getNomeDisciplina().equals(nomeDisciplina)) {
                d.cadastraNota(nota, valorNota);
            }
        }
    }

    /**
	 * Retorna um valor booleano referente à aprovação do aluno, que precisa ter sua média avaliada em, pelo menos, 7.0 para que seja considerado aprovado.
	 * 
     * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 * @return é o valor booleano a ser retornando, atribuindo "false" caso o aluno não esteja aprovado e "true" caso contrário.
	 */
    public boolean aprovado(String nomeDisciplina) {
        boolean retorno = false;
        for (Disciplina d : this.disciplinas) {
            if (d.getNomeDisciplina().equals(nomeDisciplina)) {
                retorno = d.aprovado();
            }
        }

        return retorno;
    }

    /**
	 * Retorna a String referente às informações principais da disciplina no tocante a quantidade de horas dedicadas e notas obtidas pelo aluno.
	 * 
     * @param nomeDisciplina é, em String, a representação do nome da disciplina.
	 * @return é a String que contém as principais informações, sendo elas o nome da disciplina, quantidade de horas dedicadas, média obtida pelo aluno e a lista de notas obtidas ao longo da disciplina.
	 */
    public String disciplinaToString(String nomeDisciplina) {
        String retorno = "";
        for (Disciplina d : this.disciplinas) {
            if (d.getNomeDisciplina().equals(nomeDisciplina)) {
                retorno = d.toString();
            }
        }
        return retorno;
    }

    /**
	 * Consrói o registro financeiro com base na receita inicial do aluno, que se encaixa obrigatoriamente na receita de primeiro tipo, isto é, familiar. Além disso, as despesas são consideradas como zero, podendo ser incrementadas.
	 * 
	 * @param receitaInicial representa, em um valor inteiro de centavos, a receita inicial do aluno, de origem familiar.
	 */
    public void cadastraFinancas(int receitaInicial) {
        RegistroFinancas rg = new RegistroFinancas(receitaInicial);
        this.financas = rg;
    }

    /**
	 * Aumenta o valor total e atual da receita do aluno, além de incrementar o valor relacionado à receita específica do tipo de fonte declarada.
	 * 
	 * @param tipoFonte representa, em um valor inteiro de 1 a 4, qual o tipo de origem dessa receita.
	 * @param valorCentavos representa, em um valor inteiro de centavos, a quantidade financeira a ser incrementada
	 */
    public void aumentaReceita(int tipoFonte, int valorCentavos) {
        this.financas.aumentaReceita(valorCentavos, tipoFonte);
    }

    /**
	 * Aumenta a despesa do aluno em uma quantia específica declarada, diminuindo, portanto, o valor da receita atual.
	 * 
	 * @param valorCentavos representa, em um valor inteiro de centavos, a quantidade a ser incrementada nas despesas.
	 */
    public void pagaDespesa(int valorCentavos) {
        this.financas.pagaDespesa(valorCentavos);
    }

    /**
	 * Retorna uma String que mostra, em linhas diferentes, como anda a receita total de cada fonte financeira do aluno. Caso alguma ainda não tenha sido incrementada, o valor a ser retornado será zero.
	 * 
	 * @return é a String que mostra a receita total de cada fonte financeira.
	 */
    public String exibeFontes() {
        String retorno = this.financas.exibeFontes();
        return retorno;
    }

    /**
	 * Retorna uma String referente às informações gerais da situação financeira do aluno, mostrando valores como a receita total, a receita atual e despesas totais.
	 * 
	 * @return é a String referente às informações gerais do financeiro do aluno.
	 */
    public String financasToString() {
        String retorno = this.financas.toString();
        return retorno;
    }

    /**
	 * Permite definir um novo estado de saúde mental, além de redefinir o emoji como uma String vazia, já que houve alteração no status de saúde.
	 * 
	 * @param valor é o valor em String que será atribuído ao status de saúde mental.
	 */
    public void defineSaudeMental(String valor) {
        this.saude.defineSaudeMental(valor);
    }

    /**
	 * Permite definir um novo estado de saúde física, além de redefinir o emoji como uma String vazia, já que houve alteração no status de saúde.
	 * 
	 * @param valor é o valor em String que será atribuído ao status de saúde física.
	 */
    public void defineSaudeFisica(String valor) {
        this.saude.defineSaudeFisica(valor);
    }

    /**
	 * Retorna uma String que representa o status geral da saúde do aluno. Caso a saúde física e mental estejam definidas como "boa", o status de saúde geral também será "boa". Caso a saúde física e mental estejam definidas como "fraca", o status de saúde geral também sera "fraca". Caso os dois estados de saúde estejam em condições diferentes, o status de saúde geral será "ok". Além disso, o status de saúde pode ser acompanhado de emoji, caso ele seja definido e caso não haja nenhuma alteração no status de saúde físico ou mental.
	 * 
	 * @return é a String que contém a definição final do status de saúde geral.
	 */
    public String getStatusGeral() {
        String retorno = this.saude.getStatusGeral();
        return retorno;
    }
}
