package br.com.dforlani.readwithme.ui.quesitos;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.util.Preferencias;

public class Quesitos16CompletoProjetoGrafico extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoProjetoGrafico.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_projeto_grafico);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Análise do projeto gráfico");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ16_1(viewHolder.q16_1.getText().toString());
            analise.setQ16_2(viewHolder.q16_2.getText().toString());
            analise.setQ16_3(viewHolder.q16_3.getText().toString());


            analise.setQ16_4(getPosRadioGroupSelected(viewHolder.radioGroupQ16_4));


            analise.setQ16_5(viewHolder.q16_5.getText().toString());
            analise.setQ16_6(viewHolder.q16_6.getText().toString());
            analise.setQ16_7(viewHolder.q16_7.getText().toString());
            analise.setQ16_8(viewHolder.q16_8.getText().toString());
            analise.setQ16_9(viewHolder.q16_9.getText().toString());
            analise.setQ16_10(viewHolder.q16_10.getText().toString());


            analise.save(email);
        } else {
            Toast.makeText(Quesitos16CompletoProjetoGrafico.this, "Nenhum Email fornecido, não foi possível salvar.",
                    Toast.LENGTH_LONG).show();
        }
    }


    /**
     * Salvar e continuar pra próxima
     *
     * @return
     */
    @Override
    protected Class getNextActivity() {
        return Quesitos12CompletoDificuldadeLeitura.class;
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {
        TextInputEditText q16_1;
        TextInputEditText q16_2;
        TextInputEditText q16_3;

        RadioGroup radioGroupQ16_4;

        TextInputEditText q16_5;
        TextInputEditText q16_6;
        TextInputEditText q16_7;
        TextInputEditText q16_8;
        TextInputEditText q16_9;
        TextInputEditText q16_10;


        ViewHolder() {
            super();
            q16_1 = findViewById(R.id.act_input_act_q16_1);
            q16_1.setText(analise.getQ16_1());

            q16_2 = findViewById(R.id.act_input_act_q16_2);
            q16_2.setText(analise.getQ16_2());

            q16_3 = findViewById(R.id.act_input_act_q16_3);
            q16_3.setText(analise.getQ16_3());

            radioGroupQ16_4 = findViewById(R.id.radio_group_q16_4);
            setRadioByText(radioGroupQ16_4, analise.getQ16_4());

            q16_5 = findViewById(R.id.act_input_act_q16_5);
            q16_5.setText(analise.getQ16_5());

            q16_6 = findViewById(R.id.act_input_act_q16_6);
            q16_6.setText(analise.getQ16_6());

            q16_7 = findViewById(R.id.act_input_act_q16_7);
            q16_7.setText(analise.getQ16_7());

            q16_8 = findViewById(R.id.act_input_act_q16_8);
            q16_8.setText(analise.getQ16_8());

            q16_9 = findViewById(R.id.act_input_act_q16_9);
            q16_9.setText(analise.getQ16_9());

            q16_10 = findViewById(R.id.act_input_act_q16_10);
            q16_10.setText(analise.getQ16_10());


        }

    }
}





