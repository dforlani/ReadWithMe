package br.com.dforlani.readwithme.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.quesitos.Quesitos1Activity;

public class AnaliseAdapter extends RecyclerView.Adapter<AnaliseAdapter.AnaliseViewHolder> {
    private List<Analise> analiseList;
    private Fragment fragment;

    public AnaliseAdapter(List<Analise> productList, Fragment fragment) {
        this.analiseList = productList;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AnaliseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_item_analise, parent, false);
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
        TextView textTitulo, textTipoAnalise;
        View itemView;
        CardView card;
        Analise analise;


        AnaliseViewHolder(final View itemView) {
            super(itemView);

            this.itemView = itemView;
            textTitulo = itemView.findViewById(R.id.adapter_analise_titulo);
            textTipoAnalise = itemView.findViewById(R.id.adapter_analise_tipo_analise);
            card = itemView.findViewById(R.id.adapter_analiser_card);
            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(fragment.getContext(), Quesitos1Activity.class);
                    intent.putExtra("analise", analise);
                    fragment.startActivity(intent);
                }
            });

        }

        void bindProduct(Analise analise) {
            this.analise = analise;
            textTitulo.setText(analise.getQ1_1());
            textTipoAnalise.setText((analise.getTipoAnalise()));

        }
    }
}