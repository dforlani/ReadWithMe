package br.com.dforlani.readwithme.ui.quesitos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosCompletoTextoPoesia extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoTextoPoesia.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_texto_poesia);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Pontos importantes na leitura de poesias");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ9_1(viewHolder.q9_1.getText().toString());
            analise.setQ9_2(viewHolder.q9_2.getText().toString());
            analise.setQ9_3(viewHolder.q9_3.getText().toString());
            analise.setQ9_4(viewHolder.q9_4.getText().toString());
            analise.setQ9_5(viewHolder.q9_5.getText().toString());
            analise.setQ9_6(getTagListCheck(viewHolder.getIdentificadoresCheckQ9_6()));
            analise.setQ9_7(viewHolder.q9_7.getText().toString());
            analise.setQ9_8(viewHolder.q9_8.getText().toString());
            analise.setQ9_9(viewHolder.q9_9.getText().toString());


            analise.save(email);
        } else {
            Toast.makeText(QuesitosCompletoTextoPoesia.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_completo_2_texto_poesia_sim:
                if (checked)
                    analise.setQ9_10(Analise.IDENTIFICACOES.SIM);
                break;
            case R.id.radio_button_completo_2_texto_poesia_nao:
                if (checked)
                    analise.setQ9_10(Analise.IDENTIFICACOES.NAO);
                break;
        }
    }

    /**
     * Salva e seleciona a próxima activity
     */
    protected void salvarContinuarPraProximaActivity() {
        Intent intent = null;
        if (analise.getQ9_10() != null) {
            salvarQuesitos();
            switch (analise.getQ9_10()) {
                case Analise.IDENTIFICACOES.SIM:
                    intent = new Intent(QuesitosCompletoTextoPoesia.this, QuesitosCompletoTextoNarrativo.class);
                    break;
                case Analise.IDENTIFICACOES.NAO:
                    intent = new Intent(QuesitosCompletoTextoPoesia.this, QuesitosCompletoGostariaAnalisarIlustracoesPoesia.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra("analise", analise);
                startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
            }
        } else {
            Toast.makeText(QuesitosCompletoTextoPoesia.this, "Selecione se gostaria de analisar a história existente no poema", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * Salvar e continuar pra próxima
     *
     * @return
     */
    @Override
    protected Class getNextActivity() {
        return null;
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {

        TextInputEditText q9_1;
        TextInputEditText q9_2;
        TextInputEditText q9_3;
        TextInputEditText q9_4;
        TextInputEditText q9_5;

        TextInputEditText q9_7;
        TextInputEditText q9_8;
        TextInputEditText q9_9;

        CheckBox checkAlgumasPalavrasQ9_6;
        CheckBox checkVocabularioQ9_6;
        CheckBox checkAsFigurasQ9_6;
        CheckBox checkOTamanhoQ9_6;
        CheckBox checkAutilizacaosQ9_6;
        CheckBox checkOEquilibrioQ9_6;
        CheckBox checkOutraQ9_6;


        ViewHolder() {
            super();
            q9_1 = findViewById(R.id.act_input_act_q9_1);
            q9_1.setText(analise.getQ9_1());

            q9_2 = findViewById(R.id.act_input_act_q9_2);
            q9_2.setText(analise.getQ9_2());

            q9_3 = findViewById(R.id.act_input_act_q9_3);
            q9_3.setText(analise.getQ9_3());

            q9_4 = findViewById(R.id.act_input_act_q9_4);
            q9_4.setText(analise.getQ9_4());

            q9_5 = findViewById(R.id.act_input_act_q9_5);
            q9_5.setText(analise.getQ9_5());

            checkAlgumasPalavrasQ9_6 = findViewById(R.id.act_poesia_algumas_palavras);
            checkVocabularioQ9_6 = findViewById(R.id.act_poesia_o_vocabulario);
            checkAsFigurasQ9_6 = findViewById(R.id.act_poesia_as_figuras);
            checkOTamanhoQ9_6 = findViewById(R.id.act_poesia_o_tamanho);
            checkAutilizacaosQ9_6 = findViewById(R.id.act_poesia_a_utilizacao);
            checkOEquilibrioQ9_6 = findViewById(R.id.act_poesia_o_equilibrio);
            checkOutraQ9_6 = findViewById(R.id.act_poesia_outra);
            setCheckBox(analise.getQ9_6(), getIdentificadoresCheckQ9_6());

            q9_7 = findViewById(R.id.act_input_act_q9_7);
            q9_7.setText(analise.getQ9_7());

            q9_8 = findViewById(R.id.act_input_act_q9_8);
            q9_8.setText(analise.getQ9_8());

            q9_9 = findViewById(R.id.act_input_act_q9_9);
            q9_9.setText(analise.getQ9_9());

            if (analise.getQ9_10() != null) {
                switch (analise.getQ9_10()) {
                    case Analise.IDENTIFICACOES.SIM:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_poesia_sim)).setChecked(true);
                        break;

                    case Analise.IDENTIFICACOES.NAO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_poesia_nao)).setChecked(true);
                        break;
                }
            }
        }

        private List<Integer> getIdentificadoresCheckQ9_6() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_poesia_algumas_palavras);
            auxListCheck.add(R.id.act_poesia_o_vocabulario);
            auxListCheck.add(R.id.act_poesia_as_figuras);
            auxListCheck.add(R.id.act_poesia_o_tamanho);
            auxListCheck.add(R.id.act_poesia_a_utilizacao);
            auxListCheck.add(R.id.act_poesia_o_equilibrio);
            auxListCheck.add(R.id.act_poesia_outra);
            return auxListCheck;

        }
    }
}



