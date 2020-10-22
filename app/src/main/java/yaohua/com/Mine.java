package yaohua.com;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import yaohua.com.login_and_registration.Login;

public class Mine extends AppCompatActivity {

    private TextView useremail;
    private Button logout;
    private ImageView test;

    BottomNavigationView bottomNavigationView;

    private FirebaseAuth mfirebaseAuth;
    private FirebaseUser mfirebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        useremail = (TextView)findViewById(R.id.TVEmail);
        logout = (Button)findViewById(R.id.btlogout);

        //bottom navigation//
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottomNAV3);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);
        //bottom navigation//

        //get firebase instance//
        mfirebaseAuth = FirebaseAuth.getInstance();
        mfirebaseUser = mfirebaseAuth.getCurrentUser();
        //get firebase instance//

        //logout function//
        useremail.setText(mfirebaseUser.getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Mine.this, Login.class));
            }
        }); //logout function//

        //hide status bar//
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //hide status bar//
    }

    //bottom navigation//
    private BottomNavigationView.OnNavigationItemSelectedListener navigation =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                    switch (item.getItemId()){
                        case R.id.Homepage:
                            startActivity(new Intent(Mine.this, Homepage.class));
                            break;

                        case R.id.Hot_sells:
                            startActivity(new Intent(Mine.this, Hot_sells.class));
                            break;

                        case R.id.PersonalPage:
                            Toast.makeText(Mine.this, "Mine", Toast.LENGTH_SHORT).show();
                            break;
                    }

                    return true;
                }
            };//bottom navigation//

}