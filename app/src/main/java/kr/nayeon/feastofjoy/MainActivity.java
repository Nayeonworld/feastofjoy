package kr.nayeon.feastofjoy;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;




public class MainActivity extends Activity {
    ImageButton play_button;
    ImageButton help_button;
    ImageButton record_button;
    public static MediaPlayer mp;
    private BackPressCloseHandler backPressCloseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTheme(android.R.style.Theme_NoTitleBar_Fullscreen);
        backPressCloseHandler = new BackPressCloseHandler(this);
        play_button = (ImageButton)findViewById(R.id.btn_play);
        play_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(), MenuActivity.class);
                startActivity(intent);
                mp.stop();

                finish();

            }
        });
        record_button=(ImageButton)findViewById(R.id.btn_record);
        record_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(v.getContext(),RecordActivity.class);
                startActivity(intent);
                mp.stop();
                finish();
            }
        });
        help_button = (ImageButton)findViewById(R.id.btn_help);
        help_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), HelpActivity.class);
                startActivity(intent);
                mp.stop();
                finish();

            }
        });

        mp = MediaPlayer.create(this, R.raw.bike_rides);
        mp.setVolume(100, 50);
        mp.setLooping(true);
        mp.start();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
        mp.stop();
    }
    public static void mp_stop(){
        mp.stop();
    }

}