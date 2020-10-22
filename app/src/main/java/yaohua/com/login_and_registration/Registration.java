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

import yaohua.com.R;

public class Registration extends AppCompatActivity {

   private EditText userID;
   private EditText password;
   private EditText contact;
   private Button create;
   private TextView login;
   private FirebaseAuth firebaseAuth;
   private CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setupUIViews();

        //hide status bar//
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        //hide status bar//

        firebaseAuth = FirebaseAuth.getInstance();

        //checkBox//
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
        });//checkBox//

        //create user//
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if (validate()){
                    // upload data to database
                    String user_ID = userID.getText().toString().trim();
                    String user_password = password.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_ID, user_password)
                            //tell the user create account success or not
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(Registration.this,
                                                Login.class));
                                        Toast.makeText(Registration.this,
                                                "Registration Successful", Toast.LENGTH_SHORT).show();
                                    }else{Toast.makeText(Registration.this,
                                            "Registration Failed", Toast.LENGTH_SHORT).show();

                                    }

                                }
                            });
                }
            }
        });//create user//

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Registration.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void setupUIViews(){
        userID = (EditText)findViewById(R.id.etemail);
        password = (EditText)findViewById(R.id.etpassword);
        contact = (EditText)findViewById(R.id.etinfor);
        create = (Button)findViewById(R.id.btcreate);
        login = (TextView)findViewById(R.id.txlogin);
        checkBox = (CheckBox)findViewById(R.id.checkbox);
    }

    //validate user information//
    private Boolean validate(){
        Boolean result = false;

        String ID = userID.getText().toString();
        String userPassword = password.getText().toString();
        String contactInfor = contact.getText().toString();

        if (ID.isEmpty() && userPassword.isEmpty() && contactInfor.isEmpty()){
            Toast.makeText(this, "Please enter all information", Toast.LENGTH_SHORT).show();
        }else {
            result = true;
        }

        return result;
    } //validate user information//

}