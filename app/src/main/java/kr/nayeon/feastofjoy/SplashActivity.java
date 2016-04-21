package kr.nayeon.feastofjoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;

public class SplashActivity extends Activity {
    static Handler handler;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

             Intent intent = new Intent(SplashActivity.this, MainActivity.class);
             startActivity(intent);
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0, 4000);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
           return false;
        }
        return super.onKeyDown(keyCode, event);
    }
}

