package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import cpsc466.ecs.fullerton.edu.cpsc466.MainActivity1_Sign_Up;
import cpsc466.ecs.fullerton.edu.cpsc466.MainActivity2_Sign_In;
import cpsc466.ecs.fullerton.edu.cpsc466.R;

public class MainActivity0_Sign_In_Up extends AppCompatActivity {
    Button signUp, signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main0);
        signUp = (Button) findViewById(R.id.signUp);
        signIn = (Button) findViewById(R.id.signIn);
    }

    public void signUp(View v){
        Intent i = new Intent(this,MainActivity1_Sign_Up.class);
        startActivity(i);
    }

    public void signIn(View v){
        Intent j = new Intent(this,MainActivity2_Sign_In.class);
        startActivity(j);
    }
}
