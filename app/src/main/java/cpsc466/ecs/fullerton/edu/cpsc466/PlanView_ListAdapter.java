package cpsc466.ecs.fullerton.edu.cpsc466;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanView_ListAdapter  extends ArrayAdapter<Rout>{
    private LayoutInflater mInflater;
    private ArrayList<Rout> routs;
    private int mViewResourceId;

    public PlanView_ListAdapter(Context context, int textViewResourceId, ArrayList<Rout> routs){
        super(context,textViewResourceId,routs);
        this.routs = routs;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        convertView = mInflater.inflate(mViewResourceId,null);
        Rout rout = routs.get(position);

        if(rout != null){
            TextView nameAndDate = (TextView) convertView.findViewById(R.id.planNameDate);
            TextView calculatedRout = (TextView) convertView.findViewById(R.id.planRout);
            TextView distance = (TextView) convertView.findViewById(R.id.planDistanceAndTime);

            if(nameAndDate != null && calculatedRout != null && distance != null){
                String nameDate = rout.getName() + "\n" + rout.getTime();
                nameAndDate.setText(nameDate);
                calculatedRout.setText(rout.getRout());
                String distanceAndTime = rout.getDistance() + " mile" + "\n" + rout.getTime() + " minutes";
                distance.setText(distanceAndTime);
            }
        }
        return convertView;
    }
}



