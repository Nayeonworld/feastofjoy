package kr.nayeon.feastofjoy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ShowRecordActivity extends Activity {
    int menu_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);
        Intent intent = getIntent();
        menu_num = intent.getExtras().getInt("menu_num");
        DBManager R_dbManager=new DBManager(getApplicationContext(),"RRecord.db",null,1);
        DBManager DR_dbManager=new DBManager(getApplicationContext(),"DRRecord.db",null,1);
        DBManager T_dbManager=new DBManager(getApplicationContext(),"TRecord.db",null,1);
        DBManager O_dbManager=new DBManager(getApplicationContext(),"ORecord.db",null,1);
        TextView tv=(TextView)findViewById(R.id.game_record);
        switch(menu_num) {
            case 1:
                //Select
                tv.setText(R_dbManager.PrintRotateData());
                break;
            case 2:
                //Select
                tv.setText(DR_dbManager.PrintDRotateData());
                break;
            case 3:
                //Select
                tv.setText(T_dbManager.PrintTranslateData());
                break;
            case 4:
                //Select
                tv.setText(O_dbManager.PrintOpacityData());
                break;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_record, menu);
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
    public void onBackPressed() {
        Intent intent = new Intent(this, RecordActivity.class);
        startActivity(intent);
        finish();
    }
}
