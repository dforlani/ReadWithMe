package br.com.dforlani.readwithme.firestore.livedata;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.firestore.Operation;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Constants;


@SuppressWarnings("ConstantConditions")
public class AnaliseListLiveData extends LiveData<Operation> implements EventListener<QuerySnapshot> {
    private Query query;
    private ListenerRegistration listenerRegistration;
    private OnLastVisibleAnaliseCallback onLastVisibleAnaliseCallback;
    private OnLastAnaliseReachedCallback onLastAnaliseReachedCallback;

    public AnaliseListLiveData(Query query, OnLastVisibleAnaliseCallback onLastVisibleAnaliseCallback, OnLastAnaliseReachedCallback onLastAnaliseReachedCallback) {
        this.query = query;
        this.onLastVisibleAnaliseCallback = onLastVisibleAnaliseCallback;
        this.onLastAnaliseReachedCallback = onLastAnaliseReachedCallback;
    }

    @Override
    protected void onActive() {
        listenerRegistration = query.addSnapshotListener(this);
    }

    @Override
    protected void onInactive() {
        listenerRegistration.remove();
    }

    @Override
    public void onEvent(@Nullable QuerySnapshot querySnapshot, @Nullable FirebaseFirestoreException e) {
        if (e != null) return;

        for (DocumentChange documentChange : querySnapshot.getDocumentChanges()) {
            switch (documentChange.getType()) {
                case ADDED:
                    Analise addedAnalise = documentChange.getDocument().toObject(Analise.class);
                    //adiciona o ID única para permitir a comparação quando ocorre uma alteração no banco
                    addedAnalise.setId(documentChange.getDocument().getId());
                    Operation addOperation = new Operation(addedAnalise, R.string.added);
                    setValue(addOperation);
                    break;

                case MODIFIED:
                    Analise modifiedAnalise = documentChange.getDocument().toObject(Analise.class);
                    //adiciona o ID única para permitir a comparação quando ocorre uma alteração no banco
                    modifiedAnalise.setId(documentChange.getDocument().getId());
                    Operation modifyOperation = new Operation(modifiedAnalise, R.string.modified);
                    setValue(modifyOperation);
                    break;

                case REMOVED:
                    Analise removedAnalise = documentChange.getDocument().toObject(Analise.class);
                    //adiciona o ID única para permitir a comparação quando ocorre uma alteração no banco
                    removedAnalise.setId(documentChange.getDocument().getId());
                    Operation removeOperation = new Operation(removedAnalise, R.string.removed);
                    setValue(removeOperation);
            }
        }

        int querySnapshotSize = querySnapshot.size();
        if (querySnapshotSize < Constants.LIMIT) {
            onLastAnaliseReachedCallback.setLastAnaliseReached(true);
        } else {
            DocumentSnapshot lastVisibleAnalise = querySnapshot.getDocuments().get(querySnapshotSize - 1);
            onLastVisibleAnaliseCallback.setLastVisibleAnalise(lastVisibleAnalise);
        }
    }

    public interface OnLastVisibleAnaliseCallback {
        void setLastVisibleAnalise(DocumentSnapshot lastVisibleAnalise);
    }

    public interface OnLastAnaliseReachedCallback {
        void setLastAnaliseReached(boolean isLastAnaliseReached);
    }
}
