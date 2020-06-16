package br.com.dforlani.readwithme.firestore;

import br.com.dforlani.readwithme.model.Analise;

public class Operation {
    public Analise analise;
    public int type;

    public Operation(Analise analise, int type) {
        this.analise = analise;
        this.type = type;
    }
}