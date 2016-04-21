package kr.nayeon.feastofjoy;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ActionActivity extends Activity {
    private ObjectAnimator anim;
    MediaPlayer mp;
    String sentence;
    ImageView img;
    int value=0, sum_score=0;
    int menu_num;
    int level=1; //레벨
    int speed=800;//스피드
    int temp_level=0;
    String level_str=" LEVEL";
    boolean stop_touch =false, play_touch=false, level_check=false;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        Intent intent = getIntent();
        menu_num = intent.getIntExtra("menu_num", 0);
        //Toast.makeText(this, ""+menu_num, Toast.LENGTH_SHORT).show();

        img = (ImageView) findViewById(R.id.myImage);
        final ImageButton end=(ImageButton)findViewById(R.id.stop);
        final ImageButton play=(ImageButton)findViewById(R.id.play);
        final TextView t_score=(TextView)findViewById(R.id.sum_score);//누적점수
        switch(menu_num) {
            case 1:
                anim = ObjectAnimator.ofFloat(img, "rotation", 0, 360);
                break;
            case 2:
                anim = ObjectAnimator.ofFloat(img, "rotationY", 0.0f, 360f);
                break;
            case 3:
                anim = ObjectAnimator.ofFloat(img, "translationX", -800, 800);
                break;
            case 4:
                anim = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
                break;
            default:
                anim = ObjectAnimator.ofFloat(img, "alpha", 1f, 0f);
                break;
        }
        anim.setDuration(speed);
        anim.setRepeatCount(-1);
        anim.setRepeatMode(ObjectAnimator.RESTART);
        anim.start();
        play_touch=true;
        //final TextView t_level=(TextView)findViewById(R.id.level);//누적단계
        play.setPressed(false);
        end.setPressed(false);
        //배경음악
        mp=MediaPlayer.create(this, R.raw.bama_country_country);
        mp.setVolume(100, 50);
        mp.setLooping(true);
        mp.start();

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(play_touch==true){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "두번은 안됏!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else if(play_touch==false||stop_touch==true) {
                    play_touch=true;
                    stop_touch=false;
                    play.setPressed(true);
                    end.setPressed(false);
                    anim.setDuration(speed);
                    anim.setRepeatCount(-1);
                    anim.setRepeatMode(ObjectAnimator.RESTART);
                    anim.resume();
                }
            }

        });
        end.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                end.setPressed(true);
                play.setPressed(false);
                if(stop_touch==true){
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "두번은 안됏!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                else if (stop_touch == false||play_touch==true) {
                    if (sum_score >= 1500) {
                        speed = 200;
                        level = 5;

                    } else if (sum_score >= 1000) {
                        speed = 300;
                        level = 4;

                    } else if (sum_score >= 700) {
                        speed = 400;
                        level = 3;

                    } else if (sum_score >= 500) {
                        speed = 600;
                        level = 2;
                    } else {
                        speed = 800;
                        level = 1;
                    }
                    if(temp_level!=level) {
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                level + level_str, Toast.LENGTH_SHORT);
                        toast1.setGravity(Gravity.CENTER,0,50);
                        toast1.show();
                    }
                    stop_touch=true;
                    play_touch=false;
                    anim.pause();
                    Float save = (Float) anim.getAnimatedValue();
                    switch (menu_num) {
                        case 1:
                            value = Math.round(save);
                            if (value > 357 || value < 3) {
                                sentence = "대바아아악(100점)";
                                sum_score += 100;
                            } else if (value > 355 || value < 5) {
                                sentence = "좋아(75점)";
                                sum_score += 75;
                            } else if (value > 350 || value < 10) {
                                sentence = "굳(50점)";
                                sum_score += 50;
                            } else if (value > 340 || value < 20) {
                                sentence = "나쁘지 않아(25점)";
                                sum_score += 25;
                            } else {
                                sentence = "다시 시도해봐";
                            }
                            break;
                        case 2:
                            value = Math.round(save);
                            if (value > 350 || value < 10) {
                                sentence = "대바아아악(100점)";
                                sum_score += 100;
                            } else if (value > 330 || value < 30) {
                                sentence = "좋아(75점)";
                                sum_score += 75;
                            } else if (value > 270 || value < 80) {
                                sentence = "굳(50점)";
                                sum_score += 50;
                            } else if (value > 220 || value < 130) {
                                sentence = "나쁘지 않아(25점)";
                                sum_score += 25;
                            } else {
                                sentence = "다시 시도해봐";
                            }
                            break;
                        case 3:
                            value = Math.round(save);
                            if (value >= 0) {
                                if (value < 50) {
                                    sentence = "대바아아악(100점)";
                                    sum_score += 100;
                                } else if (value < 150) {
                                    sentence = "좋아(75점)";
                                    sum_score += 75;
                                } else if (value < 300) {
                                    sentence = "굳(50점)";
                                    sum_score += 50;
                                } else if (value < 500) {
                                    sentence = "나쁘지 않아(25점)";
                                    sum_score += 25;
                                } else sentence = "다시 시도해봐";

                            } else if (value < 0) {
                                if (value > -50) {
                                    sentence = "대바아아악(100점)";
                                    sum_score += 100;
                                } else if (value > -150) {
                                    sentence = "좋아(75점)";
                                    sum_score += 75;
                                } else if (value > -300) {
                                    sentence = "굳(50점)";
                                    sum_score += 50;
                                } else if (value > -500) {
                                    sentence = "나쁘지 않아(25점)";
                                    sum_score += 25;
                                } else sentence = "다시 시도해봐";
                            } else sentence = "다시 시도해봐";
                            break;
                        case 4:
                            if (save > 0.8) {
                                sentence = "대바아아악(100점)";
                                sum_score += 100;
                            } else if (save > 0.7) {
                                sentence = "좋아(75점)";
                                sum_score += 75;
                            } else if (save > 0.5) {
                                sentence = "굳(50점)";
                                sum_score += 50;
                            } else if (save > 0.4) {
                                sentence = "나쁘지 않아(25점)";
                                sum_score += 25;
                            } else {
                                sentence = "다시 시도해봐";
                            }
                            break;
                    }
                    Toast toast = Toast.makeText(getApplicationContext(),
                            sentence, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();

                    t_score.setText(String.valueOf(sum_score));

                }
            }
        });
    }

    public void onBackPressed() {
        //Insert
        int score=sum_score;
        int total_level=level;
        DBManager R_dbManager=new DBManager(getApplicationContext(),"RRecord.db",null,1);
        DBManager DR_dbManager=new DBManager(getApplicationContext(),"DRRecord.db",null,1);
        DBManager T_dbManager=new DBManager(getApplicationContext(),"TRecord.db",null,1);
        DBManager O_dbManager=new DBManager(getApplicationContext(),"ORecord.db",null,1);
        switch(menu_num)
        {
            case 1:
                R_dbManager.insert("insert into ROTATERECORD values('"+score+"', "+total_level+");");
                break;
            case 2:
                DR_dbManager.insert("insert into DROTATERECORD values('"+score+"', "+total_level+");");
                break;
            case 3:
                T_dbManager.insert("insert into TRANSLATERECORD values('"+score+"', "+total_level+");");
                break;
            case 4:
                O_dbManager.insert("insert into OPACITYRECORD values('"+score+"', "+total_level+");");
                break;
        }
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
        mp.stop();
        finish();
    }


}