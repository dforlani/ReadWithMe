package br.com.dforlani.readwithme.ui.adapter;

import android.app.Activity;
import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.quesitos.QuesitosIdentificacaoActivity;
import br.com.dforlani.readwithme.util.Preferencias;

public class AnaliseAdapter extends RecyclerView.Adapter<AnaliseAdapter.AnaliseViewHolder> {
    private List<Analise> analiseList;
    Context context;
    private ActionMode actionMode;
    private Activity act;
    private View teste;

    public AnaliseAdapter(List<Analise> productList, Activity act, Context context) {
        this.analiseList = productList;
        this.act = act;
        this.context = context;
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


                    Intent intent = new Intent(act.getBaseContext(), QuesitosIdentificacaoActivity.class);
                    intent.putExtra("analise", analise);
                    act.startActivity(intent);
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
                    MenuInflater inflater = act.getMenuInflater();
                    inflater.inflate(R.menu.context_menu, menu);
                    MenuItem itemDelete = menu.findItem(R.id.menu_context_delete_analise);
                    itemDelete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            removerAnalise();
                            return true;
                        }
                    });
                    MenuItem itemCompartilhar = menu.findItem(R.id.menu_context_compartilhar);
                    itemCompartilhar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            Intent sendIntent = new Intent();
                            sendIntent.setAction(Intent.ACTION_SEND);
                            sendIntent.setType("text/html");
                            sendIntent.putExtra(Intent.EXTRA_TEXT, analise.getTextToShare(context));
                            Intent shareIntent = Intent.createChooser(sendIntent, null);
                            context.startActivity(shareIntent);
                            return true;
                        }
                    });
                }

            });


        }

        private void removerAnalise() {
            //dialog de confirmação para remoção
            AlertDialog.Builder alert = new AlertDialog.Builder(context);
            alert.setTitle("Remover Análise");
            alert.setMessage("Deseja realmente remover esta análise?");
            alert.setPositiveButton(R.string.sim, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    // continue with delete
                    Preferencias pref = new Preferencias(act.getBaseContext());
                    String email = pref.getEmail();
                    analise.remover(email, act.getBaseContext());

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