package br.com.dforlani.readwithme.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;

public class AnaliseAdapter extends RecyclerView.Adapter<AnaliseAdapter.AnaliseViewHolder> {
    private List<Analise> analiseList;


    public AnaliseAdapter(List<Analise> productList) {
        this.analiseList = productList;
    }

    @NonNull
    @Override
    public AnaliseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_analise_adapter, parent, false);
        return new AnaliseViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AnaliseViewHolder holder, int position) {
        Analise analise = analiseList.get(position);
        holder.bindProduct(analise);
    }

    @Override
    public int getItemCount() {
        return analiseList.size();
    }

    class AnaliseViewHolder extends RecyclerView.ViewHolder {
        TextView textViewNome, textViewIBU, textViewTeorAlcoolico, textViewDescricao;
        RecyclerView precoRecyclerView;
        ImageView imageViewProduto;
        View itemView;

        AnaliseViewHolder(View itemView) {
            super(itemView);

            this.itemView = itemView;
            textViewNome = itemView.findViewById(R.id.adapter_analise_titulo);


        }

        void bindProduct(Analise analise) {
            textViewNome.setText(analise.getQ1_1());
        }
    }
}