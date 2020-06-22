package br.com.dforlani.readwithme;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.Arrays;

import br.com.dforlani.readwithme.model.Usuario;
import br.com.dforlani.readwithme.ui.BaseActivity;
import br.com.dforlani.readwithme.ui.quesitos.Quesitos1Activity;
import br.com.dforlani.readwithme.util.Preferencias;

public class MainActivity extends BaseActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FirebaseAuth mAuth;
    private static String TAG = "Cervejaria Paraíso";
    private TextView mNameLogin;
    private Button mBttDisconect;
    View headerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //******FIREBASE********/
        mAuth = FirebaseAuth.getInstance();
        iniciarFirebase();
        //****** FIM DO FIREBASE********/

        setContentView(R.layout.activity_main);
        //  mNameLogin = findViewById(R.id.nameTextFacebook);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton bttAddAnalise = findViewById(R.id.btt_add_analise);
        bttAddAnalise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, Quesitos1Activity.class);
                // myIntent.putExtra("key", value); //Optional parameters
                startActivity(myIntent);
//                Mostra uma mensagem na base do aplicativo
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        Log.d(TAG, "create");


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
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //facebook login
        callbackManager.onActivityResult(requestCode, resultCode, data);

        super.onActivityResult(requestCode, resultCode, data);

        //google login
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_GOOGLE_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                // [START_EXCLUDE]
                updateUI(null);
                // [END_EXCLUDE]
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);//cria o menu de opção da direita

        // Obtém a referência do layout de navegação
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        // Obtém a referência da view de cabeçalho
        headerView = navigationView.getHeaderView(0);

        mNameLogin = headerView.findViewById(R.id.act_login_name_user);
        mBttDisconect = headerView.findViewById(R.id.act_login_btt_disconect);

        mBttDisconect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });


        mountFacebookLoginInterface();
        mountGoogleLoginInterface();


        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /***********************FIREBASE********************/
    /**
     * Depois que o usuário fizer login pelo Facebook, faz um login no Firebase também
     *
     * @param token
     */
    private void firebaseAuthWithFacebook(AccessToken token) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d(TAG, "signInWithCredential:success" + user.getDisplayName());
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }


                    }
                });

    }


    /*******************FIM DO FIREBASE*****************/

    /**
     * Sempre que faz o login, tenta criar o usuário na tabela usuário
     */
    private void criaUsuario(String email) {
        Usuario.crieUsuarioIfNotExist(email);
    }


    /*********************************FACEBOOK**************************/

    private LoginButton mBttLoginFacebook;
    private ImageView mImageLogin;
    private CallbackManager callbackManager;

    private void mountFacebookLoginInterface() {
        Toast.makeText(MainActivity.this, "oi", Toast.LENGTH_LONG).show();
        mBttLoginFacebook = null;// headerView.findViewById(R.id.act_login_button_facebook);
        mImageLogin = headerView.findViewById(R.id.act_login_image_user);

        callbackManager = CallbackManager.Factory.create();

        if (mBttLoginFacebook != null) {
            mBttLoginFacebook.setReadPermissions(Arrays.asList("email", "public_profile"));

            // Callback registration
            mBttLoginFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    Log.d(TAG, "facebook:onSuccess:" + loginResult);
                    firebaseAuthWithFacebook(loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                    Log.d(TAG, "facebook:onCancel");
                }

                @Override
                public void onError(FacebookException exception) {
                    Log.d(TAG, "facebook:onError", exception);
                }
            });
        }
    }

    /*************FIM DO FACEBOOK**************************/

    /**********************LOGIN GOOGLE**********************/
    private SignInButton bttLoginGoogle;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_GOOGLE_SIGN_IN = 9001;


    private void mountGoogleLoginInterface() {
        setProgressBar(R.id.progressBar);

        // Button listeners

        bttLoginGoogle = headerView.findViewById(R.id.act_login_button_google);
        if (bttLoginGoogle != null) {
            bttLoginGoogle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    signIn();
                }
            });
        }

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

    // [START auth_with_google]
    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());
        // [START_EXCLUDE silent]
        showProgressBar();
        // [END_EXCLUDE]

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // [START_EXCLUDE]
                        hideProgressBar();
                        // [END_EXCLUDE]
                    }
                });
    }
    // [END auth_with_google]

    // [START signin]
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_GOOGLE_SIGN_IN);
    }
    // [END signin]

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
        hideProgressBar();
        if (user != null) {
            //cria o usuário no sistema, caso ele ainda não exista
            criaUsuario(user.getEmail());
            //salva o e-mail num arquivo compartilhado, para posterior acesso em outras activities
            Preferencias pref = new Preferencias(this.getBaseContext());
            pref.salvaEmail(user.getEmail());

            mNameLogin.setText(user.getDisplayName());
            //arredonda a imagem
            Glide.with(MainActivity.this).load(user.getPhotoUrl()).apply(RequestOptions.circleCropTransform()).into(mImageLogin);

            mNameLogin.setVisibility(View.VISIBLE);
            mImageLogin.setVisibility(View.VISIBLE);

            bttLoginGoogle.setVisibility(View.GONE);
            if (mBttLoginFacebook != null)
                mBttLoginFacebook.setVisibility(View.GONE);
            mBttDisconect.setVisibility(View.VISIBLE);

        } else {
            mNameLogin.setVisibility(View.GONE);
            mImageLogin.setVisibility(View.GONE);

            bttLoginGoogle.setVisibility(View.VISIBLE);
            if (mBttLoginFacebook != null)
                mBttLoginFacebook.setVisibility(View.VISIBLE);
            mBttDisconect.setVisibility(View.GONE);

        }
    }


/*****************FIM LOGIN GOOGLE*********************************/

}
