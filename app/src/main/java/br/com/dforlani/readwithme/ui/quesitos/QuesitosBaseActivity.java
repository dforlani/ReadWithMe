package br.com.dforlani.readwithme.ui.quesitos;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.ActivityCompat;

import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.audiorecorder.AudioRecorderActivity;
import br.com.dforlani.readwithme.ui.barcode.BarCodeActivity;
import br.com.dforlani.readwithme.util.GoogleBooksAPI;
import br.com.dforlani.readwithme.util.Preferencias;

public class QuesitosBaseActivity extends AppCompatActivity {

    private static final String TAG = "QuesitosBase.class";
    private static final int REQUEST_AUDIO_RECORDER =  255;

   // ViewHolder viewHolder;
    protected Analise analise;
    protected ProgressBar progressBar;

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
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

            //retornando a lista de áudios nova
            if(resultCode == Activity.RESULT_OK && requestCode == QuesitosBaseActivity.REQUEST_AUDIO_RECORDER){
                if(data.hasExtra("analise")){
                    Analise aux = (Analise) data.getSerializableExtra("analise");
                    analise.setAudios(aux.getAudios());
                }
            }

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
            case R.id.menu_action_impressoes_em_audio:
                Intent intent = new Intent(QuesitosBaseActivity.this, AudioRecorderActivity.class);
                intent.putExtra("analise", analise);
                startActivityForResult(intent, QuesitosBaseActivity.REQUEST_AUDIO_RECORDER);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
