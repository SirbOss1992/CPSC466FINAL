package cpsc466.ecs.fullerton.edu.cpsc466;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity_Spash extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_spash_screen);

        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(MainActivity_Spash.this,MainActivity0_Sign_In_Up.class);
                MainActivity_Spash.this.startActivity(mainIntent);
                MainActivity_Spash.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
