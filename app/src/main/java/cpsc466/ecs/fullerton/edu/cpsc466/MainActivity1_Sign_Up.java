package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity1_Sign_Up extends AppCompatActivity {
    Database usernameDatabase;
    Database routDatabase;
    EditText username, password, confirmPassword;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        signUp = (Button) findViewById(R.id.signUpButton);
        username = (EditText) findViewById(R.id.newUserName);
        password = (EditText) findViewById(R.id.newPassword);
        confirmPassword = (EditText) findViewById(R.id.confirmNewPassword);
        usernameDatabase = new Database(this);
        routDatabase = new Database(this);
    }

    public void signUpFunction(View view) {
        if (username.getText().toString().equals("") || password.getText().toString().equals("") || confirmPassword.getText().toString().equals(""))
            Toast.makeText(this, "Enter All Required Fields", Toast.LENGTH_SHORT).show();
        else {
            if (!usernameDatabase.checkUsername(username.getText().toString())) {
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    usernameDatabase.insertNewUser(username.getText().toString(), password.getText().toString());
                    Intent i = new Intent(this, MainActivity3_Main.class);
                    i.putExtra("Username", username.getText().toString());
                    username.setText("");
                    password.setText("");
                    confirmPassword.setText("");
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(signUp.getWindowToken(), 0);
                    startActivity(i);
                } else
                    Toast.makeText(this, "Please Reconfirm Password", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(this, "Username is already existed", Toast.LENGTH_SHORT).show();
        }
    }
}
