package br.com.dforlani.readwithme.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.firestore.Operation;
import br.com.dforlani.readwithme.firestore.livedata.AnaliseListLiveData;
import br.com.dforlani.readwithme.firestore.livedata.AnaliseListViewModel;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.adapter.AnaliseAdapter;
import br.com.dforlani.readwithme.util.Preferencias;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    View root;
    private List<Analise> analiseArrayList = new ArrayList<>();
    private RecyclerView analiseRecyclerView;
    private AnaliseAdapter analiseAdapter;
    private AnaliseListViewModel analiseListViewModel;
    private boolean isScrolling;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
       root = inflater.inflate(R.layout.fragment_home, container, false);

      //  Preferencias pref = new Preferencias(root.getContext());
//        analiseArrayList = Analise.findAll(  pref.getEmail()  );

        initAnalisesRecyclerView();
        initAnalisesAdapter();
        initAnaliseListViewModel();
        getAnalises();
        initRecyclerViewOnScrollListener();

        return root;
    }





//    @Override
//    protected void onResume() {
//        super.onResume();
//
//
//    }

    private void initAnalisesRecyclerView(){
        analiseRecyclerView = root.findViewById(R.id.analises_recycler_view);
    }

    private void initAnalisesAdapter() {
        analiseAdapter = new AnaliseAdapter(analiseArrayList);
        analiseRecyclerView.setAdapter(analiseAdapter);
    }

    private void initAnaliseListViewModel() {
        analiseListViewModel = new ViewModelProvider(this).get(AnaliseListViewModel.class);
        //adiciona uma query para retornar todas as análises dos usuário
        Preferencias pref = new Preferencias(root.getContext());
        analiseListViewModel.getAnaliseListRepositoryCallback().setQueryEmailDocument(  pref.getEmail() );

    }

    private void getAnalises() {
        AnaliseListLiveData analiseListLiveData = analiseListViewModel.getAnaliseListLiveData();
        if (analiseListLiveData != null) {
            analiseListLiveData.observe(getViewLifecycleOwner(), new Observer<Operation>() {
                @Override
                public void onChanged(Operation operation) {
                    switch (operation.type) {
                        case R.string.added:
                            Analise analiseAdded = operation.analise;
                            addAnalise(analiseAdded);
                            break;

                        case R.string.modified:
                            Analise analiseMod = operation.analise;
                            modifyAnalise(analiseMod);
                            break;

                        case R.string.removed:
                            Analise analiseRemoved = operation.analise;
                            removeAnalise(analiseRemoved);
                    }
                    analiseAdapter.notifyDataSetChanged();
                }
            });

        }
    }

    private void addAnalise(Analise analise) {
        boolean encontrou = false;
        //só insere na lista, se a análise ainda não estiver presente
        for (Analise analiseAux: analiseArrayList) {
            if(analise.equals(analiseAux)){
                encontrou = true;
                break;//encerra a função sem inserir
            }
        }
        if(!encontrou)
            analiseArrayList.add(analise);
    }

    private void modifyAnalise(Analise analise) {
        for (int i = 0; i < analiseArrayList.size(); i++) {
            Analise currentAnalise = analiseArrayList.get(i);
            if (currentAnalise.equals(analise)) {
                analiseArrayList.remove(currentAnalise);
                analiseArrayList.add(i, analise);
            }
        }
    }

    private void removeAnalise(Analise analise) {
        for (int i = 0; i < analiseArrayList.size(); i++) {
            Analise currentAnalise = analiseArrayList.get(i);
            if (currentAnalise.equals(analise)) {
                analiseArrayList.remove(currentAnalise);
            }
        }
    }

    private void initRecyclerViewOnScrollListener() {
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                if (layoutManager != null) {
                    int firstVisibleProductPosition = layoutManager.findFirstVisibleItemPosition();
                    int visibleProductCount = layoutManager.getChildCount();
                    int totalProductCount = layoutManager.getItemCount();

                    if (isScrolling && (firstVisibleProductPosition + visibleProductCount == totalProductCount)) {
                        isScrolling = false;
                        getAnalises();
                    }
                }
            }
        };
        analiseRecyclerView.addOnScrollListener(onScrollListener);
    }
    /********************FIM DA LISTA DE ANÁLISES******************************/
}
