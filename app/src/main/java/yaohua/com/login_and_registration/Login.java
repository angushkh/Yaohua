package yaohua.com.login_and_registration;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import yaohua.com.Homepage;
import yaohua.com.Hot_sells;
import yaohua.com.R;

public class Login extends AppCompatActivity {

    private CheckBox checkBox;
    private EditText password;
    private EditText ID;
    private TextView register;
    private Button login;
    private FirebaseAuth firebaseAuth;
    private TextView reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkBox = (CheckBox)findViewById(R.id.checkbox);
        password = (EditText)findViewById(R.id.etpassword);
        ID = (EditText)findViewById(R.id.etemail);
        login = (Button)findViewById(R.id.btlogin);
        register = (TextView)findViewById(R.id.txRegister);
        reset = (TextView)findViewById(R.id.tv3);

        final FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        //detect user login or not//
        if (firebaseUser != null){
            startActivity(new Intent(Login.this, Hot_sells.class));
        }//detect user login or not//

        //login function//
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signInWithEmailAndPassword(ID.getText().toString(),
                        password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(Login.this, Homepage.class));
                            Toast.makeText(Login.this, "Login successful",
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(Login.this, "Login failed please retry",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });//login function//

        //hide status bar//
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //hide status bar//

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(Login.this, Registration.class);
                startActivity(intent2);
            }
        });



        //show password//
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(b)
                {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                }
                else
                {
                    password.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });//show password//

        //jump to reset password page//
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent re_password = new Intent(Login.this, reset_password.class);
                startActivity(re_password);
            }
        });//jump to reset password page//

    }
}