package br.com.dforlani.readwithme.ui.quesitos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.MainActivity;
import br.com.dforlani.readwithme.ui.audiorecorder.AudioRecorderActivity;

public abstract class QuesitosBaseActivity extends AppCompatActivity {

    private static final String TAG = "QuesitosBase.class";
    private static final int REQUEST_AUDIO_RECORDER = 255;
    protected static final int RETURN_FROM_INNER_QUESITOS_ACTIVITY = 143;

    // ViewHolder viewHolder;
    protected Analise analise;
    protected ProgressBar progressBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //pega informações de uma Analise sendo editada, se for o caso
        Intent i = getIntent();
        Object aux = i.getSerializableExtra("analise");
        if (aux != null) {
            analise = (Analise) aux;
        } else {//se não for pra edição, inicia um novo objeto Análise
            analise = new Analise();
            //adicionada uma ID randomica pois o sistema estava se perdendo sem saber se uma análise era pra alteração ou era nova
            //uma vez que a ID do Firebase só retorna depois de ter salvo e de forma assíncrona
            analise.setId(UUID.randomUUID().toString());
        }

        //alterrar a cor da barra superior do Android
        Window window = this.getWindow();
// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
// add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
// finally change the color
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //retornando a lista de áudios nova
        if (resultCode == Activity.RESULT_OK && requestCode == QuesitosBaseActivity.REQUEST_AUDIO_RECORDER) {
            if (data.hasExtra("analise")) {
                Analise aux = (Analise) data.getSerializableExtra("analise");
                analise.setAudios(aux.getAudios());
            }
        }

        //retornando de uma activity de quesitos interna, precisa atualizar o objeto analise
        if (requestCode == RETURN_FROM_INNER_QUESITOS_ACTIVITY) {
            if (data != null && data.hasExtra("analise")) {
                analise = (Analise) data.getSerializableExtra("analise");
            }
        }
    }

    protected void salvaEFecha() {
        salvarQuesitos();
        finish();
    }

    @Override
    public void onBackPressed() {
        salvarVoltarAnaliseActAnteriorAndFinish();
    }

    /**
     * Limpa a pilha de activities e retona para a MainActivity
     *
     * @param context
     */
    protected void voltarToMainActivity(Context context) {
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(newIntent);
    }

    protected void inflateMenu() {
        Toolbar toolbar = findViewById(R.id.toolbar_quesitos);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_quesitos_impressoes_em_audio:
                        Intent intent = new Intent(QuesitosBaseActivity.this, AudioRecorderActivity.class);
                        intent.putExtra("analise", analise);
                        startActivityForResult(intent, QuesitosBaseActivity.REQUEST_AUDIO_RECORDER);
                        return true;
                }
                return false;
            }
        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_quesitos, menu);//cria o menu de opção da direita

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_quesitos_impressoes_em_audio:
                Intent intent = new Intent(QuesitosBaseActivity.this, AudioRecorderActivity.class);
                intent.putExtra("analise", analise);
                startActivityForResult(intent, QuesitosBaseActivity.REQUEST_AUDIO_RECORDER);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Seta os ckeckbox que possuem uma das tags da lista de tags
     *
     * @param tagsList
     * @param identificadoresList
     */
    protected void setCheckBox(List<String> tagsList, List<Integer> identificadoresList) {
        CheckBox auxCheckBox = null;
        if (identificadoresList != null && tagsList != null)
            for (Integer identificador : identificadoresList
            ) {
                auxCheckBox = findViewById(identificador);
                for (String tag : tagsList
                ) {
                    if (((String) auxCheckBox.getTag()).compareTo(tag) == 0) {
                        auxCheckBox.setChecked(true);
                    }
                }
            }
    }

    /**
     * A partir de um RadioGroup, localiza o que está na posição certa e o seta
     *
     * @param group
     * @param posicao
     */
    protected void setRadioByPos(RadioGroup group, String posicao) {
        if (posicao != null) {
            int pos = Integer.parseInt(posicao);
            if (pos >= 0) {
                try {
                    RadioButton rBtt = (RadioButton) group.getChildAt(pos);
                    rBtt.setChecked(true);
                } catch (Exception e) {
                    Log.d(TAG, "Não foi encontrado o radio button");
                }
            }
        }
    }

    /**
     * A partir de um RadioGroup, localiza o que está na posição certa e o seta
     *
     * @param group
     * @param posicao
     */
    protected void setRadioByText(RadioGroup group, String texto) {
        if (texto != null) {
            int quant = group.getChildCount();
            for (int pos = 0; pos < quant; pos++) {
                RadioButton rBtt = (RadioButton) group.getChildAt(pos);
                if (rBtt.getText().toString().contentEquals(texto))
                    rBtt.setChecked(true);
            }
        }
    }


    /**
     * retorna a posição do raadioButton selecionado
     *
     * @param group
     */
    protected String getPosRadioGroupSelected(RadioGroup group) {
        RadioButton radioButtonAux;
        int idRadioButtonAux = group.getCheckedRadioButtonId();
        if (idRadioButtonAux >= 0) {
            radioButtonAux = findViewById(idRadioButtonAux);
            return radioButtonAux.getText().toString();
        } else {
            return "-1";
        }
    }

    /**
     * Percorre uma lista de checkbox e retorna os id dos objetos que estão checados
     *
     * @param auxListCheck
     * @return
     */
    protected List<String> getTagListCheck(List<Integer> auxListCheck) {
        List<String> tags = new ArrayList<>();
        CheckBox checkAux;
        for (Integer identificador : auxListCheck
        ) {
            checkAux = findViewById(identificador);
            if (checkAux.isChecked()) {
                tags.add((String) checkAux.getTag());
            }
        }
        return tags;
    }

    /**
     * Salva os quesitos e retorna para a activity anterior
     */
    protected void salvarVoltarAnaliseActAnteriorAndFinish() {
        salvarQuesitos();
        Intent intent = new Intent();
        intent.putExtra("analise", analise);
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * Salva os quesitos e retorna para a activity anterior
     */
    protected void salvarContinuarPraProximaActivity() {
        salvarQuesitos();
        Intent intent = new Intent(QuesitosBaseActivity.this, getNextActivity());
        intent.putExtra("analise", analise);
        startActivityForResult(intent, RETURN_FROM_INNER_QUESITOS_ACTIVITY);
    }

    protected abstract Class getNextActivity();


    protected abstract void salvarQuesitos();

    public abstract class ViewHolder {
        Button voltar;
        Button continuar;

        ViewHolder() {
            continuar = findViewById(R.id.act_buttons_continuar);
            continuar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salvarContinuarPraProximaActivity();
                }
            });

            voltar = findViewById(R.id.act_buttons_voltar);
            voltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    salvarVoltarAnaliseActAnteriorAndFinish();
                }
            });
        }


    }
}
