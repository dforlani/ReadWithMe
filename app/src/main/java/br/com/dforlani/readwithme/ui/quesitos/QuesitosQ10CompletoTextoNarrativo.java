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

public class QuesitosQ10CompletoTextoNarrativo extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoTextoNarrativo.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_texto_narrativo);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Pontos importantes do texto narrativo");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ10_1(viewHolder.q10_1.getText().toString());
            analise.setQ10_2(viewHolder.q10_2.getText().toString());
            analise.setQ10_3(viewHolder.q10_3.getText().toString());
            analise.setQ10_4(viewHolder.q10_4.getText().toString());
            analise.setQ10_5(viewHolder.q10_5.getText().toString());
            analise.setQ10_6(viewHolder.q10_6.getText().toString());
            analise.setQ10_7(viewHolder.q10_7.getText().toString());
            analise.setQ10_8(viewHolder.q10_8.getText().toString());
            analise.setQ10_9(viewHolder.q10_9.getText().toString());
            analise.setQ10_10(getTagListCheck(viewHolder.getIdentificadoresCheckQ10_10()));
            analise.setQ10_11(viewHolder.q10_11.getText().toString());
            analise.setQ10_12(viewHolder.q10_12.getText().toString());

            analise.save(email);
        } else {
            Toast.makeText(QuesitosQ10CompletoTextoNarrativo.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radio_button_completo_2_texto_narrativo_sim:
                if (checked)
                    analise.setQ10_13(Analise.IDENTIFICACOES.SIM);
                break;
            case R.id.radio_button_completo_2_texto_narrativo_nao:
                if (checked)
                    analise.setQ10_13(Analise.IDENTIFICACOES.NAO);
                break;
        }
    }

    /**
     * Salva e seleciona a próxima activity
     */
    protected void salvarContinuarPraProximaActivity() {
        Intent intent = null;
        if (analise.getQ10_13() != null) {
            salvarQuesitos();
            switch (analise.getQ10_13()) {
                case Analise.IDENTIFICACOES.SIM:
                    intent = new Intent(QuesitosQ10CompletoTextoNarrativo.this, Quesitos16CompletoProjetoGrafico.class);
                    break;
                case Analise.IDENTIFICACOES.NAO:
                    intent = new Intent(QuesitosQ10CompletoTextoNarrativo.this, Quesitos12CompletoDificuldadeLeitura.class);
                    break;
            }
            if (intent != null) {
                intent.putExtra("analise", analise);
                startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
            }
        } else {
            Toast.makeText(QuesitosQ10CompletoTextoNarrativo.this, "Selecione se gostaria de analisar ilustrações e o projeto gráfico", Toast.LENGTH_LONG).show();
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

        TextInputEditText q10_1;
        TextInputEditText q10_2;
        TextInputEditText q10_3;
        TextInputEditText q10_4;
        TextInputEditText q10_5;
        TextInputEditText q10_6;
        TextInputEditText q10_7;
        TextInputEditText q10_8;
        TextInputEditText q10_9;

        CheckBox checkAlgumasPalavrasQ10_10;
        CheckBox checkVocabularioQ10_10;
        CheckBox checkAsFigurasQ10_10;
        CheckBox checkOTamanhoQ10_10;
        CheckBox checkAutilizacaosQ10_10;
        CheckBox checkOEquilibrioQ10_10;
        CheckBox checkOutraQ10_10;

        TextInputEditText q10_11;
        TextInputEditText q10_12;

        ViewHolder() {
            super();
            q10_1 = findViewById(R.id.act_input_act_q10_1);
            q10_1.setText(analise.getQ10_1());

            q10_2 = findViewById(R.id.act_input_act_q10_2);
            q10_2.setText(analise.getQ10_2());

            q10_3 = findViewById(R.id.act_input_act_q10_3);
            q10_3.setText(analise.getQ10_3());

            q10_4 = findViewById(R.id.act_input_act_q10_4);
            q10_4.setText(analise.getQ10_4());

            q10_5 = findViewById(R.id.act_input_act_q10_5);
            q10_5.setText(analise.getQ10_5());

            q10_6 = findViewById(R.id.act_input_act_q10_6);
            q10_6.setText(analise.getQ10_6());

            q10_7 = findViewById(R.id.act_input_act_q10_7);
            q10_7.setText(analise.getQ10_7());

            q10_8 = findViewById(R.id.act_input_act_q10_8);
            q10_8.setText(analise.getQ10_8());

            q10_9 = findViewById(R.id.act_input_act_q10_9);
            q10_9.setText(analise.getQ10_9());


            checkAlgumasPalavrasQ10_10 = findViewById(R.id.act_narrativo_algumas_palavrasQ10_10);
            checkVocabularioQ10_10 = findViewById(R.id.act_narrativo_o_vocabularioQ10_10);
            checkAsFigurasQ10_10 = findViewById(R.id.act_narrativo_as_figurasQ10_10);
            checkOTamanhoQ10_10 = findViewById(R.id.act_narrativo_o_tamanhoQ10_10);
            checkAutilizacaosQ10_10 = findViewById(R.id.act_narrativo_a_utilizacaoQ10_10);
            checkOEquilibrioQ10_10 = findViewById(R.id.act_narrativo_o_equilibrioQ10_10);
            checkOutraQ10_10 = findViewById(R.id.act_narrativo_outraQ10_10);
            setCheckBox(analise.getQ10_10(), getIdentificadoresCheckQ10_10());

            q10_11 = findViewById(R.id.act_input_act_q10_11);
            q10_11.setText(analise.getQ10_11());

            q10_12 = findViewById(R.id.act_input_act_q10_12);
            q10_12.setText(analise.getQ10_12());

            if (analise.getQ10_13() != null) {
                switch (analise.getQ10_13()) {
                    case Analise.IDENTIFICACOES.SIM:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_narrativo_sim)).setChecked(true);
                        break;

                    case Analise.IDENTIFICACOES.NAO:
                        ((RadioButton) findViewById(R.id.radio_button_completo_2_texto_narrativo_nao)).setChecked(true);
                        break;
                }
            }
        }

        private List<Integer> getIdentificadoresCheckQ10_10() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_narrativo_algumas_palavrasQ10_10);
            auxListCheck.add(R.id.act_narrativo_o_vocabularioQ10_10);
            auxListCheck.add(R.id.act_narrativo_as_figurasQ10_10);
            auxListCheck.add(R.id.act_narrativo_o_tamanhoQ10_10);
            auxListCheck.add(R.id.act_narrativo_a_utilizacaoQ10_10);
            auxListCheck.add(R.id.act_narrativo_o_equilibrioQ10_10);
            auxListCheck.add(R.id.act_narrativo_outraQ10_10);
            return auxListCheck;

        }
    }
}




