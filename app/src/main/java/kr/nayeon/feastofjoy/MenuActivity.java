package kr.nayeon.feastofjoy;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MenuActivity extends Activity{
    ImageButton rotate_button;
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        rotate_button = (ImageButton) findViewById(R.id.rotate);
        mp = MediaPlayer.create(this, R.raw.bike_rides);
        mp.setVolume(100, 50);
        mp.setLooping(true);
        mp.start();
    }

    public void rotate(View v){
        Intent myIntent=new Intent(this, CountActivity.class);
        myIntent.putExtra("menu_num",1);
        startActivity(myIntent);
        mp.stop();
        finish();
    }
    public void rotate_3d(View v){
        Intent myIntent=new Intent(this, CountActivity.class);
        myIntent.putExtra("menu_num",2);
        startActivity(myIntent);
        mp.stop();
        finish();
    }
    public void translate(View v){
        Intent myIntent=new Intent(this, CountActivity.class);
        myIntent.putExtra("menu_num",3);
        startActivity(myIntent);
        mp.stop();
        finish();
    }
    public void opacity(View v){
        Intent myIntent=new Intent(this, CountActivity.class);
        myIntent.putExtra("menu_num",4);
        startActivity(myIntent);
        mp.stop();
        finish();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        mp.stop();
        finish();
    }
}