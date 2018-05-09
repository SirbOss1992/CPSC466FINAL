package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity3_Main extends AppCompatActivity implements View.OnLongClickListener {

    String username;
    Database routData;
    ListView routView;
    ArrayList<Rout> routList;
    Database db = new Database(this);
    PlanView_ListAdapter adapter;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        Intent getUsername = getIntent();
        username = getUsername.getStringExtra("Username");

        routData = new Database(this);
        routList = db.getListContents(username);

        adapter = new PlanView_ListAdapter(this, R.layout.list_adapter_view,routList);
        routView = (ListView) findViewById(R.id.savedPlanView);
        routView.setAdapter(adapter);

        AlertDialog.Builder showInstruction = new AlertDialog.Builder(this);
        showInstruction.setMessage("To add new travel plan, please tap the button at the top right of this screen."
                + "\nTo delete existed plan, tap and hold on the plan which you want to delete"
                + "\nIf you're a turtle, tap the the other button below.");
        showInstruction.setTitle("Basic instruction!! Please Read!!");

        showInstruction.setPositiveButton("Got It", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface saveResult, int i) {}
        });

        showInstruction.setNegativeButton("I'm a turtle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AlertDialog.Builder greetingTurtle = new AlertDialog.Builder(context);
                greetingTurtle.setMessage("Hello Donatello!!");

                greetingTurtle.setPositiveButton("COWABUNGA!!!!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface saveResult, int i) {}
                });

                AlertDialog dia = greetingTurtle.create();
                dia.show();
            }
        });
        AlertDialog dialog = showInstruction.create();
        dialog.show();

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

                    Toast.makeText(context, "THE PLAN HAS BEEN DELETED", Toast.LENGTH_SHORT).show();
                }
            });

            showDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {}
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
