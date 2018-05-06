package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity4_Name_Plan extends AppCompatActivity {
    Button next;
    String username;
    private EditText planName;
    private EditText planDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        next = (Button) findViewById(R.id.next);
        planName = (EditText) findViewById(R.id.planNameEditText);
        planDate = (EditText) findViewById(R.id.planNameDateEditText);
        Intent getUsername = getIntent();
        username = getUsername.getStringExtra("Username");
    }

    public void goToAddDestinations (View v){
        if (!(planName.getText().toString().equalsIgnoreCase("")) && !(planDate.getText().toString().equalsIgnoreCase(""))) {
            Intent j = new Intent(this, MainActivity5_Choose_Destinations.class);
            String planNameString = planName.getText().toString();
            String planDateString = planDate.getText().toString();
            j.putExtra("Username", username);
            j.putExtra("Name", planNameString);
            j.putExtra("Date", planDateString);
            startActivity(j);
        }
        else{
            Toast.makeText(this,"Enter Name and Date for Plan", Toast.LENGTH_SHORT).show();
        }
    }
}
