package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity5_Choose_Destinations extends AppCompatActivity {
    Database savedRoutDatabase = new Database(this);
    Location_Data locationData = new Location_Data();

    ArrayList<String> locationIndex = new ArrayList<>();
    ArrayList<String> checkFlag = new ArrayList<>();
    ArrayList<String> sortedLocations = new ArrayList<>();

    String username;
    String planName;
    String planDate;

    Button discardButton;
    Button calculateButton;

    CheckBox robot4Less,
            westCoastRecyclingCenter,
            fullertonGolfCourse,
            starBucks,
            stJudeMedicalCenter,
            fullertonElksLodge,
            kashmirApartment,
            fullertonMetroCenter,
            fullertonCollege,
            fullertonTownCenter,
            fullertonCommunityCenter,
            calStateFullerton,
            amerigeHeightsTownCenter;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        calculateButton = (Button) findViewById(R.id.calculateNewPlan);
        discardButton = (Button) findViewById(R.id.discardAllChanges);
        Intent getName = getIntent();
        Intent getDate = getIntent();
        Intent getUsername = getIntent();
        planName = getName.getStringExtra("Name");
        planDate = getDate.getStringExtra("Date");
        username = getUsername.getStringExtra("Username");

        robot4Less = (CheckBox) findViewById(R.id.robot4Less);
        westCoastRecyclingCenter = (CheckBox) findViewById(R.id.westCoastRecyclingCenter);
        fullertonGolfCourse = (CheckBox) findViewById(R.id.fullertonGolfCourse);
        starBucks = (CheckBox) findViewById(R.id.starBucks);
        stJudeMedicalCenter = (CheckBox) findViewById(R.id.stJudeMedicalCenter);
        fullertonElksLodge = (CheckBox) findViewById(R.id.fullertonElksLodge);
        kashmirApartment = (CheckBox) findViewById(R.id.kashmirApartment);
        fullertonMetroCenter = (CheckBox) findViewById(R.id.fullertonMetroCenter);
        fullertonCollege = (CheckBox) findViewById(R.id.fullertonCollege);
        fullertonTownCenter = (CheckBox) findViewById(R.id.fullertonTownCenter);
        fullertonCommunityCenter = (CheckBox) findViewById(R.id.fullertonCommunityCenter);
        calStateFullerton = (CheckBox) findViewById(R.id.calStateFullerton);
        amerigeHeightsTownCenter = (CheckBox) findViewById(R.id.amerigeHeightsTownCenter);
    }

    public void ClickMe(View view) {
        CheckBox button = (CheckBox) view;
        String checkBoxTag = button.getTag().toString();
        if (button.isChecked()) {
            locationIndex.add(checkBoxTag);
            checkFlag.add("NC");
        }
        else {
            if(locationIndex.size() != 0) {
                boolean found = false;
                int removeIndex = 0;
                while (!found) {
                    if (locationIndex.get(removeIndex).equals(checkBoxTag)) {
                        found = true;
                        locationIndex.remove(removeIndex);
                        checkFlag.remove(removeIndex);
                    }
                    ++removeIndex;
                }
            }
        }
    }

    public void calculateAndDisplay(View view) {
        if(locationIndex.size() != 0){
            //Finding TSP and save it
            int index = 0;
            int tempIndex;
            double minDistance;
            double travelDistance = 0.0;
            int minTime;
            int travelTime = 0;
            sortedLocations.add(locationIndex.get(index));
            int previousLocationIndex = Integer.valueOf(locationIndex.get(index));
            int newPreviousLocationIndex;
            checkFlag.set(index, "C");
            String foundIndex;
            ++index;

            while (index < locationIndex.size()) {
                if (!checkFlag.get(index).equals("C")) {
                    minDistance = locationData.distance[previousLocationIndex][Integer.valueOf(locationIndex.get(index))];
                    minTime = locationData.time[previousLocationIndex][Integer.valueOf(locationIndex.get(index))];
                    newPreviousLocationIndex = Integer.valueOf(locationIndex.get(index));
                    tempIndex = index;
                    ++index;
                    while (index < locationIndex.size()) {
                        if (!checkFlag.get(index).equals("C")) {
                            if (minDistance > locationData.distance[previousLocationIndex][Integer.valueOf(locationIndex.get(index))]) {
                                minDistance = locationData.distance[previousLocationIndex][Integer.valueOf(locationIndex.get(index))];
                                minTime = locationData.time[previousLocationIndex][Integer.valueOf(locationIndex.get(index))];
                                newPreviousLocationIndex = Integer.valueOf(locationIndex.get(index));
                                tempIndex = index;
                            }
                            ++index;
                        }
                        else
                            ++index;
                    }

                    sortedLocations.add(locationIndex.get(tempIndex));

                    travelDistance += minDistance;
                    travelTime += minTime;
                    checkFlag.set(tempIndex, "C");
                    previousLocationIndex = newPreviousLocationIndex;
                    index = 0;
                }
                else
                    ++index;
            }

            String temp = "";
            index = 0;
            if (sortedLocations.size() == 1)
                temp += locationData.locations[Integer.valueOf(sortedLocations.get(index))];
            else {
                for (index = 0; index < sortedLocations.size(); ++index) {
                    if (index == 0)
                        temp += "Start: " + locationData.locations[Integer.valueOf(sortedLocations.get(index))] + '\n';
                    else if (index == sortedLocations.size() - 1)
                        temp += "End:   " + locationData.locations[Integer.valueOf(sortedLocations.get(index))];
                    else
                        temp += "           " + locationData.locations[Integer.valueOf(sortedLocations.get(index))] + '\n';
                }
            }
            final String Result = temp;

            DecimalFormat df = new DecimalFormat("#.##");
            double newDistance = Double.valueOf(df.format(travelDistance));
            final String totalTravelDistance = String.valueOf(newDistance);

            final String totalTravelTime = String.valueOf(travelTime);

            //Calculate button dialog
            AlertDialog.Builder showCalculation = new AlertDialog.Builder(this);
            showCalculation.setMessage(Result);
            showCalculation.setTitle(R.string.calculate_dialog_title);

            showCalculation.setPositiveButton(R.string.save_button, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface saveResult, int i) {
                    //DO SOMETHING ABOUT THIS ASAP
                    Intent j = new Intent(context, MainActivity3_Main.class);
                    savedRoutDatabase.insertNewRout(planName, planDate, Result, totalTravelDistance, totalTravelTime, username);
                    j.putExtra("Username", username);
                    startActivity(j);
                }
            });

            showCalculation.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sortedLocations.clear();
                    for (int resetIndex = 0; resetIndex < checkFlag.size(); ++resetIndex){
                        checkFlag.set(resetIndex, "UC");
                    }
                }
            });
            AlertDialog dialog = showCalculation.create();
            dialog.show();
        }
        else
        {
            Toast.makeText(this,"Please Choose a Location", Toast.LENGTH_SHORT).show();
        }
    }

    public void discardAllOptions(View v){
        AlertDialog.Builder discard = new AlertDialog.Builder(this);
        discard.setMessage("Want to discard all?");
        discard.setTitle(R.string.discard);

        discard.setPositiveButton(R.string.discard, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface saveResult, int i) {
                //DO SOMETHING ABOUT THIS ASAP
                Intent z = new Intent(context, MainActivity3_Main.class);
                z.putExtra("Username", username);
                startActivity(z);
            }
        });

        discard.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog dialog = discard.create();
        dialog.show();
    }

    public void reset (View v){
        AlertDialog.Builder reset = new AlertDialog.Builder(this);
        reset.setMessage("Want to reset?");
        reset.setTitle(R.string.reset);

        reset.setPositiveButton(R.string.reset, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface saveResult, int i) {
                //DO SOMETHING ABOUT THIS ASAP
                Intent z = new Intent(context, MainActivity5_Choose_Destinations.class);
                z.putExtra("Username", username);
                startActivity(z);
            }
        });

        reset.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {}
        });
        AlertDialog dialog = reset.create();
        dialog.show();
    }

}

