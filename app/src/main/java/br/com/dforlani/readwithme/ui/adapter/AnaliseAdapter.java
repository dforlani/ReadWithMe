package br.com.dforlani.readwithme.ui.adapter;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.quesitos.QuesitosIdentificacaoActivity;
import br.com.dforlani.readwithme.util.Preferencias;

public class AnaliseAdapter extends RecyclerView.Adapter<AnaliseAdapter.AnaliseViewHolder> {
    private List<Analise> analiseList;
    private Fragment fragment;
    private ActionMode actionMode;

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


                    Intent intent = new Intent(fragment.getContext(), QuesitosIdentificacaoActivity.class);
                    intent.putExtra("analise", analise);
                    fragment.startActivity(intent);
                }
            });
//            card.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if (actionMode != null) {
//                        return false;
//                    }
//
//                    // Start the CAB using the ActionMode.Callback defined above
//                    actionMode = fragment.getActivity().startActionMode(actionModeCallback);
//                    v.setSelected(true);
//
////                    Preferencias pref = new Preferencias(fragment.getContext());
////                    String email = pref.getEmail();
////                    analise.deleteSubDocument(Usuario.COLECAO, email, Analise.COLECAO, analise.getId(), fragment.getContext());
//                    return true;
//                }
//            });
            //registra o item para chamar o menu de contexto no click long que está implementado na Activity
            card.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
                @Override
                public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                    MenuInflater inflater = fragment.getActivity().getMenuInflater();
                    inflater.inflate(R.menu.context_menu, menu);
                    MenuItem item = menu.findItem(R.id.menu_context_delete_analise);
                    item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            removerAnalise();
                            return true;
                        }
                    });
                }

            });


        }

        private void removerAnalise() {
            //dialog de confirmação para remoção
            AlertDialog.Builder alert = new AlertDialog.Builder(fragment.getContext());
            alert.setTitle("Remover Análise");
            alert.setMessage("Deseja realmente remover esta análise?");
            alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    Preferencias pref = new Preferencias(fragment.getContext());
                    String email = pref.getEmail();
                    analise.remover(email, fragment.getContext());

                }
            });
            alert.setNegativeButton(R.string.nao, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // close dialog
                    dialog.cancel();
                }
            });
            alert.show();
        }

        void bindProduct(Analise analise) {
            this.analise = analise;
            textTitulo.setText(analise.getQ1_1());
            textTipoAnalise.setText((analise.getTipoAnalise()));

        }
    }

}