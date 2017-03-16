package app.ceomeyei.com.ceomeyei;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class perfil_usuario extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!= null){
            String nombre = user.getDisplayName();

        }

    }
}
