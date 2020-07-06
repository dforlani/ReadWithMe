package br.com.dforlani.readwithme;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import br.com.dforlani.readwithme.ui.BaseActivity;
import br.com.dforlani.readwithme.ui.LoginActivity;
import br.com.dforlani.readwithme.ui.quesitos.QuesitosIdentificacaoActivity;

public class MainActivity extends BaseActivity {


    private FirebaseAuth mAuth;
    private static String TAG = "MainActivity";
    ImageView mImageLogin;
    FirebaseUser user;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageLogin = findViewById(R.id.act_main_image_user);


        //******FIREBASE********/
        mAuth = FirebaseAuth.getInstance();
        iniciarFirebase();
        mountGoogleLoginInterface();
        //****** FIM DO FIREBASE********/


        FloatingActionButton bttAddAnalise = findViewById(R.id.btt_add_analise);
        bttAddAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, QuesitosIdentificacaoActivity.class);
                startActivity(myIntent);
//                Mostra uma mensagem na base do aplicativo
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
    }


    private void iniciarFirebase() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    return;
                }
                String token = task.getResult().getToken();
                Log.d(TAG, token);
            }
        });
        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();
        LoginManager.getInstance().logOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void revokeAccess() {
        // Firebase sign out
        mAuth.signOut();

        // Google revoke access
        mGoogleSignInClient.revokeAccess().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        this.user = user;
        hideProgressBar();
        if (user != null) {
            //arredonda a imagem
            Glide.with(MainActivity.this).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(mImageLogin);

        }
    }

    private void mountGoogleLoginInterface() {
        // [START config_signin]
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        // [START initialize_auth]
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]
    }

    public void abreModalUsuario(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        // Get the layout inflater
        LayoutInflater inflater = this.getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogUsuario = inflater.inflate(R.layout.dialog_usuario, null);

        ImageView dialogImagerUser = dialogUsuario.findViewById(R.id.dialog_usuario_image_user);
        Glide.with(MainActivity.this).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(dialogImagerUser);

        TextView dialogNameLogin = dialogUsuario.findViewById(R.id.dialog_usuario_name_user);
        dialogNameLogin.setText(user.getDisplayName());

        Button dialogBttDisconect = dialogUsuario.findViewById(R.id.dialog_usuario_disconect_user);
        dialogBttDisconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setView(dialogUsuario);
        builder.create().show();
    }

}
