package br.com.dforlani.readwithme.model;

import android.content.Context;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analise extends ModelFirestore implements Serializable {
    public static final String COLECAO = "Analise";


    public static final String COLUMN_AUDIO_NOME = "nome";
    public static final String COLUMN_AUDIO_DATA = "data";
    public static final String COLUMN_DT_CRIACAO = "dt_criacao";

    Map<String, String> mapaQuesitos;

    /**
     * ID vai guardar a identificação única dada a coleção pelo FIrebase
     */
    private String id;

    private String ISBN;

    /**
     * Data de criação da análise, vai ser atribuído automaticamente
     */
    public Date dt_criacao;


    //tela 1
    //Informações importantes sobre o texto
    /**
     * Título do texto
     */
    private String q1_1;

    /**
     * Autor ou autores
     */
    private String q1_2;

    /**
     * Você já conhecia o autor do texto? Já leu outros textos dele? O que achou? O que é importante registrar?
     */
    private String q1_3;

    /**
     * Referência Bibliográfica
     */
    private String q1_4;

    /**
     * Qual o seu objetivo ao ler o texto?
     */
    private String q1_5;

    /**
     * Assunto principal do texto"
     */
    private String q1_6;

    /**
     * Outros assuntos do texto
     */
    private String q1_7;

    /**
     * Como você chegou até o texto? O texto foi indicado por alguém? Quem? Essa informação é importante para a leitura? Por quê?
     */
    private String q1_8;

    /**
     * Data de início da leitura
     */
    private String q1_9;

    /**
     * Data de término da leitura
     */
    private String q1_10;

    /**
     * Você quer fazer uma análise completa? *
     */
    private String q1_11;

    /**
     * Como você classificaria o seu interesse após 5 minutos de leitura?
     */
    private String q2_1;

    /**
     * Justifique a resposta, é importante para você se conhecer como leitor:
     */
    private String q2_2;

    /**
     * E na metade de leitura, como você se sentia?
     */
    private String q2_3;

    /**
     * Justifique a resposta, é importante para você se conhecer como leitor:
     */
    private String q2_4;

    /**
     * Como você classificaria o prazer ao final da leitura?
     */
    private String q2_5;

    /**
     * Justifique a resposta, é importante para você se conhecer como leitor:
     */
    private String q2_6;

    /**
     * Seu objetivo ao ler o texto foi cumprido?
     */
    private String q2_7;

    /**
     * Caso a leitura não tenha sido satisfatória para o objetivo, o que aconteceu?
     */
    private List<String> q2_8;

    /**
     * Que outra coisa?
     */
    private String q2_9;

    /**
     * As ideias do autor lhe agradam?
     */
    private String q2_10;

    /**
     * Com que partes do texto você concorda mais? Faça citações ou paráfrases:
     */
    private String q2_11;

    /**
     * Com que parte(s) do texto você discorda? Faça citações, paráfrases, discuta e apresente argumentos:
     */
    private String q2_12;

    /**
     * O estilo de escrita do autor lhe agrada?
     */
    private String q2_13;

    /**
     * Justifique a resposta, é importante para você se conhecer como leitor:
     */
    private String q2_14;

    /**
     * O gênero desse livro lhe agrada?
     */
    private String q2_15;


    /**
     * Justifique a resposta, é importante para você se conhecer como leitor:
     */
    private String q2_16;

    /**
     * Você acredita que a leitura desse texto contribuiu em alguma dessas áreas?
     */
    private List<String> q2_17;

    /**
     * Justifique a resposta, citando trechos ou fazendo paráfrases:
     */
    private String q2_18;

    /**
     * Você acredita que a leitura do texto contribuiria para a vida de outras pessoas? Quais? Como?
     */
    private String q2_19;

    /**
     * Anotações Livres
     */
    private String q3_1;


    /**
     * Resumo geral
     */
    private String q4_1;

    /**
     * Resumo de capítulos
     */
    private String q4_2;

    /**
     * Paráfrases (não esqueça de marcar as páginas!)
     */
    private String q4_3;

    /**
     * Citações diretas (não esqueça de marcar as páginas!)
     */
    private String q4_4;

    /**
     * Há algo interessante sobre as capas, a orelha ou o título do livro que você queira registrar?
     */
    private String q5_1;

    /**
     * Há algo interessante sobre o projeto gráfico do livro? (formato, papel, cores, ilustrações) - em caso de texto narrativo ilustrado, haverá um guia detalhado para análise posterior.
     */
    private String q5_2;

    /**
     * Há algo interessante a respeito da editora, da série ou coleção a que o livro pertence que você queira registrar?
     */
    private String q5_3;

    /**
     * A edição e a data de publicação do livro são importantes para seu objetivo de leitura? Por quê? Como?
     */
    private String q5_4;

    /**
     * Há apresentação, notas, apêndices? Eles são importantes para compreensão do assunto? Quem escreveu esses textos? O que é importante anotar?
     */
    private String q5_5;

    /**
     * Qual tipo de texto você está lendo?
     */
    private String q6_1;


    //******TELA Pontos importantes do texto informativo *******/

    /**
     * Qual o assunto principal do texto? Sintetize e/ou registre trechos importantes
     */
    private String q7_1;

    /**
     * Qual o assunto principal do texto? Sintetize e/ou registre trechos importantes
     */
    private String q7_2;


    /**
     * Quais as fontes utilizadas? Elas são confiáveis? É possível resgatá-las e lê-las no original?
     */
    private String q7_3;

    /**
     * As informações estão completas? Há lacunas?
     */
    private String q7_4;

    /**
     * Ficaram questões a ser compreendidas?
     */
    private String q7_5;

    /**
     * Há pontos importantes a anotar sobre a bibliografia utilizada pelo autor? *
     */
    private String q7_6;

    /**
     * Qual a tese central do texto? Sintetize e/ou registre trechos importantes
     */
    private String q8_1;

    /**
     * Quais os argumentos utilizados? Sintetize e/ou registre trechos importantes
     */
    private String q8_2;

    /**
     * Há ilustrações, tabelas ou gráficos acompanhando o texto? São importantes? Há algo a registrar sobre elas?
     */
    private String q8_3;

    /**
     * Há outros pontos importantes a ser registrados?
     */
    private String q8_4;

    /**
     * Você concorda ou não com a tese? Por quê?
     */
    private String q8_5;

    /**
     * Os argumentos utilizados são verdadeiros? Há fontes? Você confia nas fontes apresentadas? Você pode apresentar fontes ou argumentos contrários?
     */
    private String q8_6;

    /**
     * Os argumentos estão bem construídos e concatenados? Eles suportam a tese ou não tem relação com ela?
     */
    private String q8_7;

    /**
     * Há pontos importantes a anotar sobre a bibliografia utilizada pelo autor?
     */
    private String q8_8;

    /**
     * A leitura foi prazerosa e interessante? Qual a impressão mais vívida provocada pela leitura?
     */
    private String q9_1;

    /**
     * Há um tema central no(s) poema(s)? Em caso de coletânea, há um eixo temático que amarre os poemas entre si? Isso é importante?
     */
    private String q9_2;

    /**
     * Como é a rima do poema?
     */
    private String q9_3;

    /**
     * Como é a métrica do poema?
     */
    private String q9_4;

    /**
     * O eu lírico se apresenta de alguma forma? Há algo a relatar?
     */
    private String q9_5;

    /**
     * Questões de linguagem que podem aparecer no texto:
     */
    private List<String> q9_6;

    /**
     * Quais figuras de linguagem estão presentes no poema?
     */
    private String q9_7;

    /**
     * Descreva de forma mais detalhada o que lhe chamou atenção na questão anterior, fazendo citações
     */
    private String q9_8;

    /**
     * A forma como o poema se distribui na página é importante? É um poema concreto?
     */
    private String q9_9;

    /**
     * O texto é um poema épico ou narrativo? Você gostaria de analisar também a história existente no poema? *
     */
    private String q9_10;


    /**
     * A leitura foi prazerosa e interessante? Qual a impressão mais vívida provocada pela leitura?
     */
    private String q10_1;

    /**
     * O que acontece na história? Qual o enredo?
     */
    private String q10_2;

    /**
     * Quem são os personagens, como são, como se relacionam? Quais são mais importantes para a história, quais são coadjuvantes?
     */
    private String q10_3;

    /**
     * O que se pode registrar sobre o espaço da narrativa? Algum elemento espacial se apresenta com mais destaque?
     */
    private String q10_4;

    /**
     * O que se pode registrar sobre a sequência temporal dos acontecimentos narrados? O tempo é cronológico, psicológico? Os fatos são narrados na sequência em que acontecem? Há algo específico sobre o tempo que seja interessante registrar?
     */
    private String q10_5;

    /**
     * Há descrições? Como são?
     */
    private String q10_6;

    /**
     * Qual o gênero literário escolhido? Isso é importante?
     */
    private String q10_7;

    /**
     * Qual o tipo do narrador? Isso é importante?
     */
    private String q10_8;

    /**
     * Qual o foco narrativo predominante: mostrar ou contar? Isso é importante?
     */
    private String q10_9;

    /**
     * Questões de linguagem que podem aparecer no texto:
     */
    private List<String> q10_10;

    /**
     * Descreva de forma mais detalhada o que lhe chamou atenção na questão anterior, fazendo citações
     */
    private String q10_11;

    /**
     * Há ilustrações acompanhando o texto? Como se relacionam com o texto? São importantes? Há algo a registrar sobre elas?
     */
    private String q10_12;

    /**
     * Você gostaria de analisar as ilustrações e o projeto do gráfico mais detalhadamente? *
     */
    private String q10_13;


    /**
     * Você conhece alguma obra relacionada?
     */
    private String q11_1;

    /**
     * São obras atuais?
     */
    private String q11_2;

    /**
     * Você conhece alguma obra relacionada?
     */
    private String q11_3;

    /**
     * Você considera a bibliografia adequada ao assunto do texto?
     */
    private String q11_4;

    /**
     * Qual lacuna? Qual a relevância dessa lacuna para seu objetivo em ler o texto?
     */
    private String q11_5;

    /**
     * Pode-se perceber algum enfoque político a partir dessas obras?
     */
    private String q11_6;

    /**
     * Deseja comentar algo sobre elas? *
     */
    private String q12_1;

    /**
     * Marque as dificuldades encontradas:
     */
    private List<String> q13_1;

    /**
     * Descreva de forma mais detalhada suas dificuldades, com citações:
     */
    private String q13_2;

    /**
     * Como você resolveu suas dificuldades?
     */
    private String q13_3;

    /**
     * Você pretende resolvê-las com alguma estratégia? Qual?
     */
    private String q13_4;

    /**
     * Você consegue relacionar o texto com alguma dessas experiências?
     */
    private List<String> q14_1;

    /**
     * Descreva as experiências que acreditar mais importantes:
     */
    private String q14_2;

    /**
     * Você consegue relacionar o texto com algum desses objetos culturais?
     */
    private List<String> q14_3;

    /**
     * Descreva com mais detalhes as relações marcadas na questão anterior:
     */
    private String q14_4;

    /**
     * Você consegue relacionar o texto com algum texto acadêmico cujo autor concorde ou complemente as ideias apresentadas? Qual?
     */
    private String q14_5;

    /**
     * Você consegue relacionar o texto com algum texto acadêmico cujo autor discorde das ideais apresentadas? Qual?
     */
    private String q14_6;

    private List<Map<String, String>> audios = new ArrayList<>();


    public Analise() {
        generateMap();
    }


    public void generateMap() {
        mapaQuesitos = new HashMap<>();
    }

    public static List<Analise> findAll(String email) {
        List<QueryDocumentSnapshot> aux = ModelFirestore.findAllSubDocumentsByParentDocument(Usuario.COLECAO, email, Analise.COLECAO);
        List<Analise> lista = new ArrayList<>();
        if (aux != null) {
            for (QueryDocumentSnapshot doc : aux) {
                lista.add(doc.toObject(Analise.class));
            }
            return lista;
        } else {
            return null;
        }
    }

    public List<Map<String, String>> getAudios() {
        return audios;
    }


    public void setAudios(List<Map<String, String>> audios) {
        this.audios = audios;
    }

    public Map<String, String> getMapaQuesitos() {
        return mapaQuesitos;
    }

    public void setMapaQuesitos(Map<String, String> mapaQuesitos) {
        this.mapaQuesitos = mapaQuesitos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Date dt_criacao) {
        this.dt_criacao = dt_criacao;
    }

    public String getQ1_1() {
        return q1_1;
    }

    public void setQ1_1(String q1_1) {
        this.q1_1 = q1_1;
    }

    public String getQ11_1() {
        return q11_1;
    }

    public String getQ12_1() {
        return q12_1;
    }

    public void setQ12_1(String q12_1) {
        this.q12_1 = q12_1;
    }

    public void setQ11_1(String q11_1) {
        this.q11_1 = q11_1;
    }

    public String getQ11_2() {
        return q11_2;
    }

    public void setQ11_2(String q11_2) {
        this.q11_2 = q11_2;
    }

    public String getQ11_3() {
        return q11_3;
    }

    public void setQ11_3(String q11_3) {
        this.q11_3 = q11_3;
    }

    public List<String> getQ13_1() {
        return q13_1;
    }

    public void setQ13_1(List<String> q13_1) {
        this.q13_1 = q13_1;
    }

    public List<String> getQ14_1() {
        return q14_1;
    }

    public void setQ14_1(List<String> q14_1) {
        this.q14_1 = q14_1;
    }

    public String getQ14_2() {
        return q14_2;
    }

    public void setQ14_2(String q14_2) {
        this.q14_2 = q14_2;
    }

    public List<String> getQ14_3() {
        return q14_3;
    }

    public void setQ14_3(List<String> q14_3) {
        this.q14_3 = q14_3;
    }

    public String getQ14_4() {
        return q14_4;
    }

    public void setQ14_4(String q14_4) {
        this.q14_4 = q14_4;
    }

    public String getQ14_5() {
        return q14_5;
    }

    public void setQ14_5(String q14_5) {
        this.q14_5 = q14_5;
    }

    public String getQ14_6() {
        return q14_6;
    }

    public void setQ14_6(String q14_6) {
        this.q14_6 = q14_6;
    }

    public String getQ13_2() {
        return q13_2;
    }

    public void setQ13_2(String q13_2) {
        this.q13_2 = q13_2;
    }

    public String getQ13_3() {
        return q13_3;
    }

    public void setQ13_3(String q13_3) {
        this.q13_3 = q13_3;
    }

    public String getQ13_4() {
        return q13_4;
    }

    public void setQ13_4(String q13_4) {
        this.q13_4 = q13_4;
    }

    public String getQ11_4() {
        return q11_4;
    }

    public void setQ11_4(String q11_4) {
        this.q11_4 = q11_4;
    }

    public String getQ11_5() {
        return q11_5;
    }

    public void setQ11_5(String q11_5) {
        this.q11_5 = q11_5;
    }

    public String getQ11_6() {
        return q11_6;
    }

    public void setQ11_6(String q11_6) {
        this.q11_6 = q11_6;
    }

    public String getQ1_2() {
        return q1_2;
    }

    public void setQ1_2(String q1_2) {
        this.q1_2 = q1_2;
    }

    public String getQ1_3() {
        return q1_3;
    }

    public String getQ7_1() {
        return q7_1;
    }

    public void setQ7_1(String q7_1) {
        this.q7_1 = q7_1;
    }

    public String getQ7_2() {
        return q7_2;
    }

    public void setQ7_2(String q7_2) {
        this.q7_2 = q7_2;
    }

    public String getQ7_3() {
        return q7_3;
    }

    public void setQ7_3(String q7_3) {
        this.q7_3 = q7_3;
    }

    public String getQ8_1() {
        return q8_1;
    }

    public void setQ8_1(String q8_1) {
        this.q8_1 = q8_1;
    }

    public String getQ8_2() {
        return q8_2;
    }

    public void setQ8_2(String q8_2) {
        this.q8_2 = q8_2;
    }

    public String getQ9_1() {
        return q9_1;
    }

    public void setQ9_1(String q9_1) {
        this.q9_1 = q9_1;
    }

    public String getQ9_2() {
        return q9_2;
    }

    public void setQ9_2(String q9_2) {
        this.q9_2 = q9_2;
    }

    public String getQ9_3() {
        return q9_3;
    }

    public void setQ9_3(String q9_3) {
        this.q9_3 = q9_3;
    }

    public String getQ9_4() {
        return q9_4;
    }

    public void setQ9_4(String q9_4) {
        this.q9_4 = q9_4;
    }

    public String getQ9_5() {
        return q9_5;
    }

    public void setQ9_5(String q9_5) {
        this.q9_5 = q9_5;
    }

    public List<String> getQ9_6() {
        return q9_6;
    }

    public void setQ9_6(List<String> q9_6) {
        this.q9_6 = q9_6;
    }

    public String getQ9_7() {
        return q9_7;
    }

    public void setQ9_7(String q9_7) {
        this.q9_7 = q9_7;
    }

    public String getQ9_8() {
        return q9_8;
    }

    public void setQ9_8(String q9_8) {
        this.q9_8 = q9_8;
    }

    public String getQ9_9() {
        return q9_9;
    }

    public void setQ9_9(String q9_9) {
        this.q9_9 = q9_9;
    }

    public String getQ9_10() {
        return q9_10;
    }

    public void setQ9_10(String q9_10) {
        this.q9_10 = q9_10;
    }

    public String getQ8_3() {
        return q8_3;
    }

    public void setQ8_3(String q8_3) {
        this.q8_3 = q8_3;
    }

    public String getQ8_4() {
        return q8_4;
    }

    public void setQ8_4(String q8_4) {
        this.q8_4 = q8_4;
    }

    public String getQ8_5() {
        return q8_5;
    }

    public void setQ8_5(String q8_5) {
        this.q8_5 = q8_5;
    }

    public String getQ8_6() {
        return q8_6;
    }

    public void setQ8_6(String q8_6) {
        this.q8_6 = q8_6;
    }

    public String getQ8_7() {
        return q8_7;
    }

    public void setQ8_7(String q8_7) {
        this.q8_7 = q8_7;
    }

    public String getQ8_8() {
        return q8_8;
    }

    public void setQ8_8(String q8_8) {
        this.q8_8 = q8_8;
    }

    public String getQ7_4() {
        return q7_4;
    }

    public void setQ7_4(String q7_4) {
        this.q7_4 = q7_4;
    }

    public String getQ7_5() {
        return q7_5;
    }

    public void setQ7_5(String q7_5) {
        this.q7_5 = q7_5;
    }

    public String getQ7_6() {
        return q7_6;
    }

    public void setQ7_6(String q7_6) {
        this.q7_6 = q7_6;
    }

    public void setQ1_3(String q1_3) {
        this.q1_3 = q1_3;
    }

    public String getQ1_4() {
        return q1_4;
    }

    public void setQ1_4(String q1_4) {
        this.q1_4 = q1_4;
    }

    public String getQ1_5() {
        return q1_5;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setQ1_5(String q1_5) {
        this.q1_5 = q1_5;
    }

    public String getQ1_6() {
        return q1_6;
    }

    public String getQ6_1() {
        return q6_1;
    }

    public void setQ6_1(String q6_1) {
        this.q6_1 = q6_1;
    }

    public void setQ1_6(String q1_6) {
        this.q1_6 = q1_6;
    }

    public String getQ1_7() {
        return q1_7;
    }

    public void setQ1_7(String q1_7) {
        this.q1_7 = q1_7;
    }

    public String getQ1_8() {
        return q1_8;
    }

    public void setQ1_8(String q1_8) {
        this.q1_8 = q1_8;
    }

    public String getQ1_9() {
        return q1_9;
    }

    public void setQ1_9(String q1_9) {
        this.q1_9 = q1_9;
    }

    public String getQ1_10() {
        return q1_10;
    }

    public void setQ1_10(String q1_10) {
        this.q1_10 = q1_10;
    }

    public String getQ1_11() {
        return q1_11;
    }

    public void setQ1_11(String q1_11) {
        this.q1_11 = q1_11;
    }

    public String getQ2_1() {
        return q2_1;
    }

    public void setQ2_1(String q2_1) {
        this.q2_1 = q2_1;
    }

    public String getQ2_2() {
        return q2_2;
    }

    public void setQ2_2(String q2_2) {
        this.q2_2 = q2_2;
    }

    public String getQ2_3() {
        return q2_3;
    }

    public void setQ2_3(String q2_3) {
        this.q2_3 = q2_3;
    }

    public String getQ2_4() {
        return q2_4;
    }

    public void setQ2_4(String q2_4) {
        this.q2_4 = q2_4;
    }

    public String getQ2_5() {
        return q2_5;
    }

    public void setQ2_5(String q2_5) {
        this.q2_5 = q2_5;
    }

    public String getQ2_6() {
        return q2_6;
    }

    public void setQ2_6(String q2_6) {
        this.q2_6 = q2_6;
    }

    public String getQ2_7() {
        return q2_7;
    }

    public void setQ2_7(String q2_7) {
        this.q2_7 = q2_7;
    }

    public List<String> getQ2_8() {
        return q2_8;
    }

    public void setQ2_8(List<String> q2_8) {
        this.q2_8 = q2_8;
    }

    public String getQ2_9() {
        return q2_9;
    }

    public void setQ2_9(String q2_9) {
        this.q2_9 = q2_9;
    }

    public String getQ2_10() {
        return q2_10;
    }

    public void setQ2_10(String q2_10) {
        this.q2_10 = q2_10;
    }

    public String getQ2_11() {
        return q2_11;
    }

    public void setQ2_11(String q2_11) {
        this.q2_11 = q2_11;
    }

    public String getQ2_12() {
        return q2_12;
    }

    public void setQ2_12(String q2_12) {
        this.q2_12 = q2_12;
    }

    public String getQ2_13() {
        return q2_13;
    }

    public void setQ2_13(String q2_13) {
        this.q2_13 = q2_13;
    }

    public String getQ2_14() {
        return q2_14;
    }

    public void setQ2_14(String q2_14) {
        this.q2_14 = q2_14;
    }

    public String getQ2_15() {
        return q2_15;
    }

    public void setQ2_15(String q2_15) {
        this.q2_15 = q2_15;
    }

    public String getQ2_16() {
        return q2_16;
    }

    public void setQ2_16(String q2_16) {
        this.q2_16 = q2_16;
    }

    public List<String> getQ2_17() {
        return q2_17;
    }

    public void setQ2_17(List<String> q2_17) {
        this.q2_17 = q2_17;
    }

    public String getQ2_18() {
        return q2_18;
    }

    public void setQ2_18(String q2_18) {
        this.q2_18 = q2_18;
    }

    public String getQ2_19() {
        return q2_19;
    }

    public void setQ2_19(String q2_19) {
        this.q2_19 = q2_19;
    }

    public String getQ3_1() {
        return q3_1;
    }

    public void setQ3_1(String q3_1) {
        this.q3_1 = q3_1;
    }

    public String getQ4_1() {
        return q4_1;
    }

    public void setQ4_1(String q4_1) {
        this.q4_1 = q4_1;
    }

    public String getQ4_2() {
        return q4_2;
    }

    public void setQ4_2(String q4_2) {
        this.q4_2 = q4_2;
    }

    public String getQ4_3() {
        return q4_3;
    }

    public void setQ4_3(String q4_3) {
        this.q4_3 = q4_3;
    }

    public String getQ4_4() {
        return q4_4;
    }

    public void setQ4_4(String q4_4) {
        this.q4_4 = q4_4;
    }

    public String getQ5_1() {
        return q5_1;
    }

    public void setQ5_1(String q5_1) {
        this.q5_1 = q5_1;
    }

    public String getQ5_2() {
        return q5_2;
    }

    public String getQ10_1() {
        return q10_1;
    }

    public void setQ10_1(String q10_1) {
        this.q10_1 = q10_1;
    }

    public String getQ10_2() {
        return q10_2;
    }

    public void setQ10_2(String q10_2) {
        this.q10_2 = q10_2;
    }

    public String getQ10_3() {
        return q10_3;
    }

    public void setQ10_3(String q10_3) {
        this.q10_3 = q10_3;
    }

    public String getQ10_4() {
        return q10_4;
    }

    public void setQ10_4(String q10_4) {
        this.q10_4 = q10_4;
    }

    public String getQ10_5() {
        return q10_5;
    }

    public void setQ10_5(String q10_5) {
        this.q10_5 = q10_5;
    }

    public String getQ10_6() {
        return q10_6;
    }

    public void setQ10_6(String q10_6) {
        this.q10_6 = q10_6;
    }

    public String getQ10_7() {
        return q10_7;
    }

    public void setQ10_7(String q10_7) {
        this.q10_7 = q10_7;
    }

    public String getQ10_8() {
        return q10_8;
    }

    public void setQ10_8(String q10_8) {
        this.q10_8 = q10_8;
    }

    public String getQ10_9() {
        return q10_9;
    }

    public void setQ10_9(String q10_9) {
        this.q10_9 = q10_9;
    }

    public List<String> getQ10_10() {
        return q10_10;
    }

    public void setQ10_10(List<String> q10_10) {
        this.q10_10 = q10_10;
    }

    public String getQ10_11() {
        return q10_11;
    }

    public void setQ10_11(String q10_11) {
        this.q10_11 = q10_11;
    }

    public String getQ10_12() {
        return q10_12;
    }

    public void setQ10_12(String q10_12) {
        this.q10_12 = q10_12;
    }

    public String getQ10_13() {
        return q10_13;
    }

    public void setQ10_13(String q10_13) {
        this.q10_13 = q10_13;
    }

    public void setQ5_2(String q5_2) {
        this.q5_2 = q5_2;
    }

    public String getQ5_3() {
        return q5_3;
    }

    public void setQ5_3(String q5_3) {
        this.q5_3 = q5_3;
    }

    public String getQ5_4() {
        return q5_4;
    }

    public void setQ5_4(String q5_4) {
        this.q5_4 = q5_4;
    }

    public String getQ5_5() {
        return q5_5;
    }

    public void setQ5_5(String q5_5) {
        this.q5_5 = q5_5;
    }

    public String getTipoAnalise() {
        if (this.q1_11 != null)
            switch (this.q1_11) {
                case ConstsIdentificacoes.APENAS_ANOTACAOES:
                    return ConstsIdentificacoes.APENAS_ANOTACAOES_COMPLETE;

                case ConstsIdentificacoes.APENAS_REACOES:
                    return ConstsIdentificacoes.APENAS_REACOES_COMPLETE;

                case ConstsIdentificacoes.APENAS_RESUMOS:
                    return ConstsIdentificacoes.APENAS_RESUMOS_COMPLETE;

                case ConstsIdentificacoes.ENCERRAR:
                    return ConstsIdentificacoes.ENCERRAR_COMPLETO;

                case ConstsIdentificacoes.ANALISE_COMPLETA:
                    return ConstsIdentificacoes.ANALISE_COMPLETA_COMPLETO;
            }
        return "";
    }

    public boolean isEncerrar() {
        return this.q1_11.compareTo(ConstsIdentificacoes.ENCERRAR) == 0;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if ((this.id.equals(((Analise) obj).id)))// && (this.descricao == ((Produto)obj).descricao) && (this.preco == ((Produto)obj).preco) )
            return true;
        else
            return false;
    }


    public void save(String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());

        map.put("ISBN", this.getISBN());

        map.put("q1_1", this.q1_1);
        map.put("q1_2", this.q1_2);
        map.put("q1_3", this.q1_3);
        map.put("q1_4", this.q1_4);
        map.put("q1_5", this.q1_5);
        map.put("q1_6", this.q1_6);
        map.put("q1_7", this.q1_7);
        map.put("q1_8", this.q1_8);
        map.put("q1_9", this.q1_9);
        map.put("q1_10", this.q1_10);
        map.put("q1_11", this.q1_11);

        map.put("q2_1", this.q2_1);
        map.put("q2_2", this.q2_2);
        map.put("q2_3", this.q2_3);
        map.put("q2_4", this.q2_4);
        map.put("q2_5", this.q2_5);
        map.put("q2_6", this.q2_6);
        map.put("q2_7", this.q2_7);
        map.put("q2_8", this.q2_8);
        map.put("q2_9", this.q2_9);
        map.put("q2_10", this.q2_10);
        map.put("q2_11", this.q2_11);
        map.put("q2_12", this.q2_12);
        map.put("q2_13", this.q2_13);
        map.put("q2_14", this.q2_14);
        map.put("q2_15", this.q2_15);
        map.put("q2_16", this.q2_16);
        map.put("q2_17", this.q2_17);
        map.put("q2_18", this.q2_18);
        map.put("q2_19", this.q2_19);

        map.put("q3_1", this.q3_1);

        map.put("q4_1", this.q4_1);
        map.put("q4_2", this.q4_2);
        map.put("q4_3", this.q4_3);
        map.put("q4_4", this.q4_4);

        map.put("q5_1", this.q5_1);
        map.put("q5_2", this.q5_2);
        map.put("q5_3", this.q5_3);
        map.put("q5_4", this.q5_4);
        map.put("q5_5", this.q5_5);

        //tela quesitos completo 2
        map.put("q6_1", this.q6_1);

        //******TELA Pontos importantes do texto informativo *******/
        map.put("q7_1", this.q7_1);
        map.put("q7_2", this.q7_2);
        map.put("q7_3", this.q7_3);
        map.put("q7_4", this.q7_4);
        map.put("q7_5", this.q7_5);
        map.put("q7_6", this.q7_6);

        //******TELA Pontos importantes do texto argumentativo *******/
        map.put("q8_1", this.q8_1);
        map.put("q8_2", this.q8_2);
        map.put("q8_3", this.q8_3);
        map.put("q8_4", this.q8_4);
        map.put("q8_5", this.q8_5);
        map.put("q8_6", this.q8_6);
        map.put("q8_7", this.q8_5);
        map.put("q8_8", this.q8_6);

        //******TELA Pontos importantes do texto poesia *******/
        map.put("q9_1", this.q9_1);
        map.put("q9_2", this.q9_2);
        map.put("q9_3", this.q9_3);
        map.put("q9_4", this.q9_4);
        map.put("q9_5", this.q9_5);
        map.put("q9_6", this.q9_6);
        map.put("q9_7", this.q9_7);
        map.put("q9_8", this.q9_8);
        map.put("q9_9", this.q9_9);
        map.put("q9_10", this.q9_10);

        //******TELA Pontos importantes do texto narrativo *******/
        map.put("q10_1", this.q10_1);
        map.put("q10_2", this.q10_2);
        map.put("q10_3", this.q10_3);
        map.put("q10_4", this.q10_4);
        map.put("q10_5", this.q10_5);
        map.put("q10_6", this.q10_6);
        map.put("q10_7", this.q10_7);
        map.put("q10_8", this.q10_8);
        map.put("q10_9", this.q10_9);
        map.put("q10_10", this.q10_10);
        map.put("q10_11", this.q10_11);
        map.put("q10_12", this.q10_12);
        map.put("q10_13", this.q10_13);

        //******TELA Analise Bibliografia *******/
        map.put("q11_1", this.q11_1);
        map.put("q11_2", this.q11_2);
        map.put("q11_3", this.q11_3);
        map.put("q11_4", this.q11_4);
        map.put("q11_5", this.q11_5);
        map.put("q11_6", this.q11_6);


        //******TELA Analise teve dificuldade leitura *******/
        map.put("q12_1", this.q12_1);

        //******TELA Complementar Dificuldade de Leitura *******/
        map.put("q13_1", this.q13_1);
        map.put("q13_2", this.q13_2);
        map.put("q13_3", this.q13_3);
        map.put("q13_4", this.q13_4);

        //******TELA Relações do texto com outros conteúdos *******/
        map.put("q14_1", this.q14_1);
        map.put("q14_2", this.q14_2);
        map.put("q14_3", this.q14_3);
        map.put("q14_4", this.q14_4);
        map.put("q14_5", this.q14_5);
        map.put("q14_6", this.q14_6);

        map.put("audios", this.getAudios());

        if (this.dt_criacao == null)
            map.put("dt_criacao", new Date());
        else
            map.put("dt_criacao", this.dt_criacao);
        this.setSubDocument(Usuario.COLECAO, email, Analise.COLECAO, this.id, map);
    }

    public class ConstsIdentificacoes {

        public static final String SIM = "SIM";
        public static final String NAO = "NAO";

        public static final String SIM_MINUSCULAS = "Sim";
        public static final String NAO_MINUSCULAS = "Não";


        public static final String TEXTO_INFORMATIVO = "informativo";
        public static final String TEXTO_ARGUMENTATIVO = "argumentativo";
        public static final String TEXTO_POESIA = "poesia";
        public static final String TEXTO_NARRATIVO = "narrativo";

        public static final String APENAS_ANOTACAOES = "apenas_anotacoes";

        private static final String APENAS_ANOTACAOES_COMPLETE = "Anotações livres";
        public static final String APENAS_REACOES = "apenas_reacoes";
        private static final String APENAS_REACOES_COMPLETE = "Reações";
        public static final String APENAS_RESUMOS = "apenas_resumos";
        private static final String APENAS_RESUMOS_COMPLETE = "Resumos, citações e paráfrases";
        public static final String ENCERRAR = "encerrar";
        private static final String ENCERRAR_COMPLETO = "Análise simples";
        public static final String ANALISE_COMPLETA = "analise_completa";
        private static final String ANALISE_COMPLETA_COMPLETO = "Análise completa";
    }

    public void remover(String email, Context context) {
        this.deleteSubDocument(Usuario.COLECAO, email, Analise.COLECAO, this.id, context);
    }
}
