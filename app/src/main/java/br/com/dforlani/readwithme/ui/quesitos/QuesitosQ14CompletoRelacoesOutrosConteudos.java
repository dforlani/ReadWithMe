package br.com.dforlani.readwithme.ui.quesitos;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosQ14CompletoRelacoesOutrosConteudos extends QuesitosBaseActivity {

    private static final String TAG = "QuesitosCompletoRelacoesOutrosConteudos.class";
    ViewHolder viewHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quesitos_relacoes_outros_conteudos);
        inflateMenu();

        TextView title = findViewById(R.id.title_app_bar_quesitos);
        title.setText("Relações do texto com outros conteúdos");
        viewHolder = new ViewHolder();
    }

    @Override
    protected void salvarQuesitos() {
        String email = "";
        Preferencias pref = new Preferencias(this.getBaseContext());
        email = pref.getEmail();
        if (email != null) {
            analise.setQ14_1(getTagListCheck(viewHolder.getIdentificadoresCheckQ14_1()));

            analise.setQ14_2(viewHolder.q14_2.getText().toString());
            analise.setQ14_3(getTagListCheck(viewHolder.getIdentificadoresCheckQ14_3()));
            analise.setQ14_4(viewHolder.q14_4.getText().toString());
            analise.setQ14_5(viewHolder.q14_5.getText().toString());
            analise.setQ14_6(viewHolder.q14_5.getText().toString());

            analise.save(email);
        } else {
            Toast.makeText(QuesitosQ14CompletoRelacoesOutrosConteudos.this, "Nenhum Email fornecido, não foi possível salvar.",
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
        return QuesitosQ2ReacoesActivity.class;
    }

    class ViewHolder extends QuesitosBaseActivity.ViewHolder {

        CheckBox check1Q14_1;
        CheckBox check2Q14_1;
        CheckBox check3Q14_1;
        CheckBox check4Q14_1;
        CheckBox check5Q14_1;
        CheckBox check6Q14_1;


        TextInputEditText q14_2;

        CheckBox check1Q14_3;
        CheckBox check2Q14_3;
        CheckBox check3Q14_3;
        CheckBox check4Q14_3;
        CheckBox check5Q14_3;
        CheckBox check6Q14_3;
        CheckBox check7Q14_3;
        CheckBox check8Q14_3;


        TextInputEditText q14_4;
        TextInputEditText q14_5;
        TextInputEditText q14_6;


        ViewHolder() {
            super();
            check1Q14_1 = findViewById(R.id.act_check_1Q14_1);
            check2Q14_1 = findViewById(R.id.act_check_2Q14_1);
            check3Q14_1 = findViewById(R.id.act_check_3Q14_1);
            check4Q14_1 = findViewById(R.id.act_check_4Q14_1);
            check5Q14_1 = findViewById(R.id.act_check_5Q14_1);
            check6Q14_1 = findViewById(R.id.act_check_6Q14_1);
            setCheckBox(analise.getQ14_1(), getIdentificadoresCheckQ14_1());


            q14_2 = findViewById(R.id.act_input_act_q14_2);
            q14_2.setText(analise.getQ14_2());

            check1Q14_3 = findViewById(R.id.act_check_1Q14_3);
            check2Q14_3 = findViewById(R.id.act_check_2Q14_3);
            check3Q14_3 = findViewById(R.id.act_check_3Q14_3);
            check4Q14_3 = findViewById(R.id.act_check42Q14_3);
            check5Q14_3 = findViewById(R.id.act_check52Q14_3);
            check6Q14_3 = findViewById(R.id.act_check62Q14_3);
            check7Q14_3 = findViewById(R.id.act_check72Q14_3);
            check8Q14_3 = findViewById(R.id.act_check82Q14_3);
            setCheckBox(analise.getQ14_3(), getIdentificadoresCheckQ14_3());

            q14_4 = findViewById(R.id.act_input_act_q14_4);
            q14_4.setText(analise.getQ14_4());

            q14_5 = findViewById(R.id.act_input_act_q15_5);
            q14_5.setText(analise.getQ14_5());

            q14_6 = findViewById(R.id.act_input_act_q14_6);
            q14_6.setText(analise.getQ14_6());
        }

        private List<Integer> getIdentificadoresCheckQ14_1() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_check_1Q14_1);
            auxListCheck.add(R.id.act_check_2Q14_1);
            auxListCheck.add(R.id.act_check_3Q14_1);
            auxListCheck.add(R.id.act_check_4Q14_1);
            auxListCheck.add(R.id.act_check_5Q14_1);
            auxListCheck.add(R.id.act_check_6Q14_1);

            return auxListCheck;

        }

        private List<Integer> getIdentificadoresCheckQ14_3() {
            List<Integer> auxListCheck = new ArrayList<>();
            auxListCheck.add(R.id.act_check_1Q14_3);
            auxListCheck.add(R.id.act_check_2Q14_3);
            auxListCheck.add(R.id.act_check_3Q14_3);
            auxListCheck.add(R.id.act_check42Q14_3);
            auxListCheck.add(R.id.act_check52Q14_3);
            auxListCheck.add(R.id.act_check62Q14_3);
            auxListCheck.add(R.id.act_check72Q14_3);
            auxListCheck.add(R.id.act_check82Q14_3);

            return auxListCheck;

        }
    }
}




