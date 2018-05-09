package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity4_Name_Plan extends AppCompatActivity {
    Database planNameDatabase;
    Button next;
    String username;
    private EditText planName;
    private EditText inPlanMonth;
    private EditText inPlanDate;
    private EditText inPlanYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        planNameDatabase = new Database(this);
        next = (Button) findViewById(R.id.next);
        planName = (EditText) findViewById(R.id.planNameEditText);
        inPlanMonth = (EditText) findViewById(R.id.planMonth);
        inPlanDate = (EditText) findViewById(R.id.planDate);
        inPlanYear = (EditText) findViewById(R.id.planYear);
        Intent getUsername = getIntent();
        username = getUsername.getStringExtra("Username");
    }

    public void goToAddDestinations(View v) {
        if (!(planName.getText().toString().equals("")) && !(inPlanMonth.getText().toString().equals("")) &&
                !(inPlanDate.getText().toString().equals("")) && !(inPlanYear.getText().toString().equals(""))) {
            if (!planNameDatabase.checkRoutName(planName.getText().toString())) {
                if (((Integer.valueOf(inPlanMonth.getText().toString()) > 0 && Integer.valueOf(inPlanMonth.getText().toString()) <= 12) &&
                        Integer.valueOf(inPlanDate.getText().toString()) <= 31 && inPlanYear.getText().length() <= 4) ||
                        (Integer.valueOf(inPlanMonth.getText().toString()) == 2 && Integer.valueOf(inPlanDate.getText().toString()) <= 29)) {
                    Intent j = new Intent(this, MainActivity5_Choose_Destinations.class);
                    String planNameString = planName.getText().toString();
                    String planDateString = inPlanMonth.getText().toString() + "/" + inPlanDate.getText().toString() + "/" + inPlanYear.getText().toString();
                    j.putExtra("Username", username);
                    j.putExtra("Name", planNameString);
                    j.putExtra("Date", planDateString);
                    startActivity(j);
                } else {
                    Toast.makeText(this, "Invalid Date.", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Name is in used.", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this, "Enter All Required Fields", Toast.LENGTH_SHORT).show();
        }
    }
}