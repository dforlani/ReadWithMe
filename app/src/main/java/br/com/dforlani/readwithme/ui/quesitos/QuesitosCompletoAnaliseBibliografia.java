
package br.com.dforlani.readwithme.ui.quesitos;

import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosCompletoAnaliseBibliografia extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoAnaliseBibliografia.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_completo_analise_bibliografia);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Análise da Bibliografia utilizada\n");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ11_1(getPosRadioGroupSelected(viewHolder.radioGroupQ11_1));
            analise.setQ11_2(getPosRadioGroupSelected(viewHolder.radioGroupQ11_2));
            analise.setQ11_3(getPosRadioGroupSelected(viewHolder.radioGroupQ11_3));


            analise.setQ11_4(viewHolder.q11_4.getText().toString());
            analise.setQ11_5(getPosRadioGroupSelected(viewHolder.radioGroupQ11_5));
            analise.setQ11_6(viewHolder.q11_6.getText().toString());

            analise.save(email);
        } else {
            Toast.makeText(QuesitosCompletoTextoNarrativo.this, "Nenhum Email fornecido, não foi possível salvar.",
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
        return QuesitosCompletoDificuldadeLeitura.class;
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {
        RadioGroup radioGroupQ11_1;
        RadioGroup radioGroupQ11_2;
        RadioGroup radioGroupQ11_3;
        TextInputEditText q11_4;
        RadioGroup radioGroupQ11_5;
        TextInputEditText q11_6;


        ViewHolder() {
            super();
            radioGroupQ11_1 = findViewById(R.id.radio_group_q11_1);
            setRadio(radioGroupQ11_1, analise.getQ11_1());

            radioGroupQ11_2 = findViewById(R.id.radio_group_q11_2);
            setRadio(radioGroupQ11_2, analise.getQ11_2());

            radioGroupQ11_3 = findViewById(R.id.radio_group_q11_3);
            setRadio(radioGroupQ11_3, analise.getQ11_3());


            q11_4 = findViewById(R.id.act_input_act_q11_4);
            q11_4.setText(analise.getQ11_4());

            radioGroupQ11_5 = findViewById(R.id.radio_group_q11_5);
            setRadio(radioGroupQ11_5, analise.getQ11_5());

            q11_6 = findViewById(R.id.act_input_act_q11_6);
            q11_6.setText(analise.getQ11_6());

        }

    }
}





