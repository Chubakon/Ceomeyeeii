package app.ceomeyei.com.ceomeyei;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity{
    private static final int Cont_Sing_in = 0;

    private FirebaseAuth auth;
    private DrawerLayout nMenu_Lateral;
    private ActionBarDrawerToggle nDibujar_Menu_Lateral;

    private Toolbar nBarra_superior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            //El usuario ya esta con sesion iniciada
            Log.d("AUTH", auth.getCurrentUser().getEmail());
        } else {
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setProviders(
                            AuthUI.FACEBOOK_PROVIDER,
                            AuthUI.EMAIL_PROVIDER,
                            AuthUI.GOOGLE_PROVIDER)
                    .build(), Cont_Sing_in);
        }
        //Implementacion barra superior
        nBarra_superior = (Toolbar) findViewById(R.id.barra_superior);
        setSupportActionBar(nBarra_superior);

        //Seccion de el menu lateral
        nMenu_Lateral = (DrawerLayout) findViewById(R.id.id_menu);
        nDibujar_Menu_Lateral = new ActionBarDrawerToggle(this, nMenu_Lateral, R.string.open, R.string.close);

        nMenu_Lateral.addDrawerListener(nDibujar_Menu_Lateral);
        nDibujar_Menu_Lateral.syncState();

        //Metodo de seleccion de menu lateral

        NavigationView seleccion = (NavigationView)findViewById(R.id.menu_lateral_principal);
        seleccion.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){

                    case(R.id.menu_principal):
                        Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(MainActivity);
                        return true;

                    case(R.id.opciones_comida):
                        Intent comida_main = new Intent(getApplicationContext(), comida_main.class);
                        startActivity(comida_main);
                        return true;

                    case(R.id.opciones_ejercicios):
                        Intent ejercicios_main = new Intent(getApplicationContext(), ejercicios_main.class);
                        startActivity(ejercicios_main);
                        return true;

                    case(R.id.perfil_bas):
                        Intent perfil_usuario = new Intent(getApplicationContext(), perfil_usuario.class);
                        startActivity(perfil_usuario);
                        return true;

                    case(R.id.config):
                        Intent configuracion = new Intent(getApplicationContext(), configuracion.class);
                        startActivity(configuracion);
                        return true;

                    case(R.id.suscripcion):
                        Intent Subscripcion= new Intent(getApplicationContext(), Subscripcion.class);
                        startActivity(Subscripcion);
                        return true;

                    case(R.id.cerrar_sesion):
                        AuthUI.getInstance()
                                .signOut(MainActivity.this)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Log.d("AUTH", "EL USUARIO SE HA DESCONECTADO");
                                        finish();
                                    }
                                });
                        return true;

                    default:
                        return true;

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Cont_Sing_in){
            if(resultCode == RESULT_OK){
                //Ususario inicion sesion
                Log.d("AUTH", auth.getCurrentUser().getEmail());
            }
            else{
                //El ususario no ha podido iniciar sesion
                Log.d("AUTH", "NO SE PUDO AUTENTIFICAR");
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (nDibujar_Menu_Lateral.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
/*
    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.logout){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Log.d("AUTH", "EL USUARIO SE HA DESCONECTADO");
                            finish();
                        }
                    });
        }
    }*/

    }
