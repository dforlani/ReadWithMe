package br.com.dforlani.readwithme.ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.login.LoginManager;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
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

import java.util.ArrayList;
import java.util.List;

import br.com.dforlani.readwithme.R;
import br.com.dforlani.readwithme.firestore.Operation;
import br.com.dforlani.readwithme.firestore.livedata.AnaliseListLiveData;
import br.com.dforlani.readwithme.firestore.livedata.AnaliseListViewModel;
import br.com.dforlani.readwithme.model.Analise;
import br.com.dforlani.readwithme.ui.adapter.AnaliseAdapter;
import br.com.dforlani.readwithme.ui.quesitos.QuesitosQ1IdentificacaoActivity;
import br.com.dforlani.readwithme.util.Preferencias;

public class MainActivity extends BaseActivity {


    private FirebaseAuth mAuth;
    private static String TAG = "MainActivity";
    ImageView mImageLogin;
    FirebaseUser user;
    private GoogleSignInClient mGoogleSignInClient;

    private List<Analise> analiseArrayList = new ArrayList<>();
    private RecyclerView analiseRecyclerView;
    private AnaliseAdapter analiseAdapter;
    private AnaliseListViewModel analiseListViewModel;
    private boolean isScrolling;
    private AdView mAdView;




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
                Intent myIntent = new Intent(MainActivity.this, QuesitosQ1IdentificacaoActivity.class);
                startActivity(myIntent);
            }
        });


        initADMOB();


    }

    @Override
    public void onStart() {
        super.onStart();
        initAnalisesRecyclerView();
        initAnalisesAdapter();
        if (analiseListViewModel == null) {

            initAnaliseListViewModel();
            initRecyclerViewOnScrollListener();
            getAnalises();
        }
    }


    public void initADMOB() {
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

//    public void initADMOBTeste() {
//        //Incializa o ADMob
//        MobileAds.initialize(this.getBaseContext(), new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//        //RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("52A5BC24BE3C2E91266B673DAE167199"));
//        mAdView = this.findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().addTestDevice("52A5BC24BE3C2E91266B673DAE167199").build();
//        mAdView.loadAd(adRequest);
//
//        mAdView.setAdListener(new AdListener() {
//            @Override
//            public void onAdLoaded() {
//                int o = 0;
//                o++;
//                // Code to be executed when an ad finishes loading.
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                int o = 0;
//                o++;
//                // Code to be executed when an ad request fails.
//            }
//
//            @Override
//            public void onAdOpened() {
//                int o = 0;
//                o++;
//                // Code to be executed when an ad opens an overlay that
//                // covers the screen.
//            }
//
//            @Override
//            public void onAdClicked() {
//                int o = 0;
//                o++;
//                // Code to be executed when the user clicks on an ad.
//            }
//
//            @Override
//            public void onAdLeftApplication() {
//                int o = 0;
//                o++;
//                // Code to be executed when the user has left the app.
//            }
//
//            @Override
//            public void onAdClosed() {
//                int o = 0;
//                o++;
//                // Code to be executed when the user is about to return
//                // to the app after tapping on an ad.
//            }
//        });
//        /*********FIMM DO ABMOB********/
//    }


    private void initAnalisesRecyclerView() {
        if (analiseRecyclerView == null) {
            analiseRecyclerView = this.findViewById(R.id.analises_recycler_view);
        }
    }

    private void initAnalisesAdapter() {
        if (analiseAdapter == null) {
            analiseAdapter = new AnaliseAdapter(analiseArrayList, this, MainActivity.this);
            analiseRecyclerView.setAdapter(analiseAdapter);
        }
    }

    private void initAnaliseListViewModel() {
        if (analiseListViewModel == null) {
            analiseListViewModel = new ViewModelProvider(this).get(AnaliseListViewModel.class);
            //adiciona uma query para retornar todas as análises dos usuário
            Preferencias pref = new Preferencias(this.getBaseContext());
            analiseListViewModel.getAnaliseListRepositoryCallback().setQueryEmailDocument(pref.getEmail());
        }
    }

    private void getAnalises() {
        AnaliseListLiveData analiseListLiveData = analiseListViewModel.getAnaliseListLiveData();

        if (analiseListLiveData != null) {
            analiseListLiveData.observe(this, new Observer<Operation>() {
                @Override
                public void onChanged(Operation operation) {
                    switch (operation.type) {
                        case R.string.added:
                            Analise analiseAdded = operation.analise;
                            addAnalise(analiseAdded);
                            break;

                        case R.string.modified:
                            Analise analiseMod = operation.analise;
                            modifyAnalise(analiseMod);
                            break;

                        case R.string.removed:
                            Analise analiseRemoved = operation.analise;
                            removeAnalise(analiseRemoved);
                    }
                    analiseAdapter.notifyDataSetChanged();
                }
            });
        } else {
            analiseAdapter.notifyDataSetChanged();
        }
    }

    private void addAnalise(Analise analise) {
        boolean encontrou = false;
        //só insere na lista, se a análise ainda não estiver presente
        int i = 0;
        for (Analise analiseAux : analiseArrayList) {

            if (analise.equals(analiseAux)) {
                encontrou = true;
                analiseArrayList.set(i, analise);//troca pelo novo, já que pode ter sido alterado
                break;//encerra a função sem inserir
            }
            i++;
        }
        if (!encontrou)
            analiseArrayList.add(analise);
    }

    private void modifyAnalise(Analise analise) {
        for (int i = 0; i < analiseArrayList.size(); i++) {
            Analise currentAnalise = analiseArrayList.get(i);
            if (currentAnalise.equals(analise)) {
                analiseArrayList.remove(currentAnalise);
                analiseArrayList.add(i, analise);
            }
        }
    }

    private void removeAnalise(Analise analise) {
        for (int i = 0; i < analiseArrayList.size(); i++) {
            Analise currentAnalise = analiseArrayList.get(i);
            if (currentAnalise.equals(analise)) {
                analiseArrayList.remove(currentAnalise);
            }
        }
    }


    private void initRecyclerViewOnScrollListener() {
        RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager layoutManager = ((LinearLayoutManager) recyclerView.getLayoutManager());
                if (layoutManager != null) {
                    int firstVisibleProductPosition = layoutManager.findFirstVisibleItemPosition();
                    int visibleProductCount = layoutManager.getChildCount();
                    int totalProductCount = layoutManager.getItemCount();

                    if (isScrolling && (firstVisibleProductPosition + visibleProductCount == totalProductCount)) {
                        isScrolling = false;
                        getAnalises();
                    }
                }
            }
        };
        analiseRecyclerView.addOnScrollListener(onScrollListener);
    }

    private void iniciarFirebase() {
        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (!task.isSuccessful()) {
                    return;
                }

                String token = "";
                if (task != null && task.getResult() != null) {
                    token = task.getResult().getToken();
                }
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

        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            TextView versionText = dialogUsuario.findViewById(R.id.versionText);
            versionText.setText("Versão do App: " + versionName);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

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
