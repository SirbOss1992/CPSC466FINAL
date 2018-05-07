package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity3_Main extends AppCompatActivity {
    String username;
    Database routData;
    Cursor cursor;
    ListView routView;
    ArrayList<Rout> routList;
    Rout rout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        routData = new Database(this);
        cursor = routData.getListContents();
        int numRows = cursor.getCount();
        routList = new ArrayList<>();
        while(cursor.moveToNext()){
            rout = new Rout(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            routList.add(rout);
        }
        PlanView_ListAdapter adapter = new PlanView_ListAdapter(this, R.layout.list_adapter_view,routList);
        routView = (ListView) findViewById(R.id.savedPlanView);
        routView.setAdapter(adapter);

        Intent getUsername = getIntent();
        username = getUsername.getStringExtra("Username");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.plans_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addPlan:
                Intent i = new Intent(this, MainActivity4_Name_Plan.class);
                i.putExtra("Username", username);
                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
