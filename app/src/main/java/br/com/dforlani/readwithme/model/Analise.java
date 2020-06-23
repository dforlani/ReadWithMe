package br.com.dforlani.readwithme.model;

import androidx.annotation.Nullable;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Analise extends ModelFirestore implements Serializable {
    public static final String COLECAO = "Analise";

    public static final String APENAS_ANOTACAOES = "apenas_anotacoes";
    private static final String APENAS_ANOTACAOES_COMPLETE = "Apenas anotações livres";
    public static final String APENAS_REACOES = "apenas_reacoes";
    private static final String APENAS_REACOES_COMPLETE = "Apenas reações";
    public static final String APENAS_RESUMOS = "apenas_resumos";
    private static final String APENAS_RESUMOS_COMPLETE = "Apenas resumos, citações e paráfrases";
    public static final String ENCERRAR = "encerrar";
    private static final String ENCERRAR_COMPLETO = "Análise simples";
    public static final String ANALISE_COMPLETA = "analise_completa";
    private static final String ANALISE_COMPLETA_COMPLETO = "Análise completa";

    public static final String COLUMN_AUDIO_NOME = "nome";
    public static final String COLUMN_AUDIO_DATA = "data";

    Map<String, String> mapaQuesitos;

    /**
     * ID vai guardar a identificação única dada a coleção pelo FIrebase
     */
    private String id;

    private String ISBN;

    Timestamp dt_criacao;
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

    private List<Map<String,String>> audios = new ArrayList<>();

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

    public Timestamp getDt_criacao() {
        return dt_criacao;
    }

    public void setDt_criacao(Timestamp dt_criacao) {
        this.dt_criacao = dt_criacao;
    }

    public String getQ1_1() {
        return q1_1;
    }

    public void setQ1_1(String q1_1) {
        this.q1_1 = q1_1;
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


    public String getTipoAnalise() {
        switch (this.q1_11) {
            case Analise.APENAS_ANOTACAOES:
                return Analise.APENAS_ANOTACAOES_COMPLETE;

            case Analise.APENAS_REACOES:
                return Analise.APENAS_REACOES_COMPLETE;

            case Analise.APENAS_RESUMOS:
                return Analise.APENAS_RESUMOS_COMPLETE;

            case Analise.ENCERRAR:
                return Analise.ENCERRAR_COMPLETO;

            case Analise.ANALISE_COMPLETA:
                return Analise.ANALISE_COMPLETA_COMPLETO;
        }
        return "";
    }

    public boolean isEncerrar() {
        return this.q1_11.compareTo(Analise.ENCERRAR) == 0;
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

        map.put("audios", this.getAudios());

        if (this.id != null && this.id.trim().length() > 0)//edição
        {
            Analise.updateSubDocument(Usuario.COLECAO, email, Analise.COLECAO, this.id, map);
        } else {//salvar um novo
            Analise.addSubDocument(Usuario.COLECAO, email, Analise.COLECAO, map);
        }

    }
}
