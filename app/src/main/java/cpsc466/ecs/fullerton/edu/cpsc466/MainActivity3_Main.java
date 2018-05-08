package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MainActivity3_Main extends AppCompatActivity implements View.OnLongClickListener {

    String username;
    Database routData;
    Cursor cursor;
    ListView routView;
    ArrayList<Rout> routList;
    Rout rout;
    Database db = new Database(this);
    PlanView_ListAdapter adapter;
    Context context = this;

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
        adapter = new PlanView_ListAdapter(this, R.layout.list_adapter_view,routList);
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

    public void planDelete(View view){
        final View nView = view;
        if(view instanceof RelativeLayout){
            AlertDialog.Builder showDelete = new AlertDialog.Builder(this);
            showDelete.setMessage("Delete this plan?");
            showDelete.setTitle("Attention!!!!");

            showDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface saveResult, int i) {
                    RelativeLayout selectedPlan = (RelativeLayout) nView;
                    int position = Integer.valueOf(selectedPlan.getTag().toString());
                    db.deleteRout(routList.get(position).getName());
                    routList.remove(position);
                    adapter.notifyDataSetChanged();

                    AlertDialog.Builder confirmDeletion = new AlertDialog.Builder(context);
                    confirmDeletion.setMessage("");

                    confirmDeletion.setPositiveButton("The plan has been deleted", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface saveResult, int i) {}
                    });

                    AlertDialog dia = confirmDeletion.create();
                    dia.show();
                }
            });

            showDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            AlertDialog dialog = showDelete.create();
            dialog.show();
        }
    }

    @Override
    public boolean onLongClick(View v) {
        planDelete(v);
        return true;
    }
}
