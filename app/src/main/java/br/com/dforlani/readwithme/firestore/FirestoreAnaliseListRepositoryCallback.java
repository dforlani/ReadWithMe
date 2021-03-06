package br.com.dforlani.readwithme.firestore;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import br.com.dforlani.readwithme.firestore.livedata.AnaliseListLiveData;
import br.com.dforlani.readwithme.firestore.livedata.AnaliseListViewModel;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.model.Usuario;
import br.com.dforlani.readwithme.util.Constants;

public class FirestoreAnaliseListRepositoryCallback implements AnaliseListViewModel.AnaliseListRepository,
        AnaliseListLiveData.OnLastVisibleAnaliseCallback, AnaliseListLiveData.OnLastAnaliseReachedCallback {
    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private CollectionReference analisesRef;// = firebaseFirestore.collection(Analise.COLECAO).document("dforlani@gmail.com").collection(Usuario.COLECAO);
   // private Query query = productsRef.orderBy(Constants.PRODUCT_NAME_PROPERTY, ASCENDING).limit(Constants.LIMIT);
   //private Query query = firebaseFirestore.collectionGroup(Usuario.COLECAO);
    private Query query;// = analisesRef.limit(Constants.LIMIT);//limita em 15 o retorno
    private DocumentSnapshot lastVisibleAnalise;
    private boolean isLastAnaliseReached;



    /**
     * Como o Id do documento é o e-mail do usuário, seta a query
     * @param email
     */
    public void setQueryEmailDocument(String email){
        if(email != null && email.trim().length() > 0) {
            analisesRef = firebaseFirestore.collection(Usuario.COLECAO).document(email).collection(Analise.COLECAO);
        }else{
            analisesRef = firebaseFirestore.collection(Usuario.COLECAO).document("naoexiste9955*").collection(Analise.COLECAO);
        }
        query = analisesRef.orderBy(Analise.COLUMN_DT_CRIACAO, Query.Direction.DESCENDING).limit(Constants.LIMIT);
    }

    @Override
    public AnaliseListLiveData getAnaliseListLiveData() {
//        só executa nova consustase se não chegou no último
//        if (isLastAnaliseReached) {
//            return null;
//        }
        //executa consultas após o limitador
//        if (lastVisibleAnalise != null) {
//            query = query.startAfter(lastVisibleAnalise);
//        }
        return new AnaliseListLiveData(query, this, this);
    }

    @Override
    public void setLastVisibleAnalise(DocumentSnapshot lastVisibleAnalise) {
        this.lastVisibleAnalise = lastVisibleAnalise;
    }

    @Override
    public void setLastAnaliseReached(boolean isLastAnaliseReached) {
        this.isLastAnaliseReached = isLastAnaliseReached;
    }
}
