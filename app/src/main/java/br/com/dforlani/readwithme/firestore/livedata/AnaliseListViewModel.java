package br.com.dforlani.readwithme.firestore.livedata;

import androidx.lifecycle.ViewModel;

import br.com.dforlani.readwithme.firestore.FirestoreAnaliseListRepositoryCallback;

@SuppressWarnings("WeakerAccess")
public class AnaliseListViewModel extends ViewModel {
    private AnaliseListRepository analiseListRepositoryCallback = new FirestoreAnaliseListRepositoryCallback();

    public FirestoreAnaliseListRepositoryCallback getAnaliseListRepositoryCallback() {
        return (FirestoreAnaliseListRepositoryCallback)analiseListRepositoryCallback;
    }

    public AnaliseListLiveData getAnaliseListLiveData() {
        return analiseListRepositoryCallback.getAnaliseListLiveData();
    }

    public interface AnaliseListRepository {
        AnaliseListLiveData getAnaliseListLiveData();
    }
}