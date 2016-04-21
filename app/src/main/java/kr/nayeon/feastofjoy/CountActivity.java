package kr.nayeon.feastofjoy;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;


public class CountActivity extends Activity {
    MediaPlayer mp;
    ImageView countimg;
    int menu_num;
    int count=4;
    int arr[]= {R.drawable.count_0,R.drawable.count_0,R.drawable.count_1,R.drawable.count_2, R.drawable.count_3};
    @Override
    public void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);
        Intent intent = getIntent();
        menu_num = intent.getExtras().getInt("menu_num");
        countimg = (ImageView) findViewById(R.id.countdown);
        mp=MediaPlayer.create(this, R.raw.count_down);
        mp.setVolume(100, 50);
        mp.setLooping(true);
        mp.start();
        new CountDownTimer(4500, 1000) {
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub
                countimg.setBackgroundResource(arr[count]);
                count--;
            }
            public void onFinish() {
                Intent myIntent = new Intent(CountActivity.this, ActionActivity.class);
                myIntent.putExtra("menu_num",menu_num);
                startActivity(myIntent);
                mp.stop();
                finish();

            }
        }.start();


    }
}


