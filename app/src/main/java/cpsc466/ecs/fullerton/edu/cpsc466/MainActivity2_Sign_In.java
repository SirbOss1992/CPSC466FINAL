package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity2_Sign_In extends AppCompatActivity {
    Database usernameDatabase;
    Database routDatabase;
    EditText username, password;
    Button signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        signIn = (Button) findViewById(R.id.signInButton);
        username = (EditText) findViewById(R.id.userName);
        password = (EditText) findViewById(R.id.password);
        usernameDatabase = new Database(this);
        routDatabase = new Database(this);
    }

    public void signInFunction(View view) {
        if (username.getText().toString().equals("") || password.getText().toString().equals(""))
            Toast.makeText(this, "Enter All Required Fields", Toast.LENGTH_SHORT).show();
        else {
            if (usernameDatabase.validateLogin(username.getText().toString(), password.getText().toString())) {
                Intent i = new Intent(this, MainActivity3_Main.class);
                i.putExtra("Username", username.getText().toString());
                username.setText("");
                password.setText("");
                startActivity(i);
            }
            else {
                Toast.makeText(this, "Invalid Login. Try Again", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
