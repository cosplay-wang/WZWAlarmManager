package cosplay.demo.wang.alarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.security.acl.AclEntry;
import java.util.Calendar;

public class MainActivity extends Activity implements View.OnClickListener{
    Button oldOnceBt,oldRepeatBt,newOnceBt,newOnceBt2,newRepeatBt,closeOldOnceBt,closeOldRepeatBt,closeNewOnceBt,closeNewOnceBt2,closeNewRepeatBt;
    public  final  String TAG = "WZWcalender";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        oldOnceBt = (Button) findViewById(R.id.old_once_bt);
        oldRepeatBt = (Button) findViewById(R.id.old_repeat_bt);
        newOnceBt = (Button) findViewById(R.id.new_once_bt);
        newOnceBt2 = (Button) findViewById(R.id.new_once2_bt);
        newRepeatBt = (Button) findViewById(R.id.new_repeat_bt);

        closeOldOnceBt = (Button) findViewById(R.id.close_old_once_bt);
        closeOldRepeatBt = (Button) findViewById(R.id.close_old_repeat_bt);
        closeNewOnceBt = (Button) findViewById(R.id.close_new_once_bt);
        closeNewOnceBt2 = (Button) findViewById(R.id.close_new_once2_bt);
        closeNewRepeatBt = (Button) findViewById(R.id.close_new_repeat_bt);

        oldOnceBt.setOnClickListener(this);
        oldRepeatBt.setOnClickListener(this);
        newOnceBt.setOnClickListener(this);
        newOnceBt2.setOnClickListener(this);
        newRepeatBt.setOnClickListener(this);

        closeOldOnceBt.setOnClickListener(this);
        closeOldRepeatBt.setOnClickListener(this);
        closeNewOnceBt.setOnClickListener(this);
        closeNewOnceBt2.setOnClickListener(this);
        closeNewRepeatBt.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        Calendar calender = Calendar.getInstance();
        //calender.setTimeInMillis(System.currentTimeMillis());
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);//得到的月份是-1的
        int day = calender.get(Calendar.DAY_OF_MONTH);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        int min = calender.get(Calendar.MINUTE);
        int second = calender.get(Calendar.SECOND);
        Log.i(TAG,year + "年" + month + "月" + day + "天" + hour + "时" + min + "分" + second + "秒");
        switch(view.getId()){
            case R.id.old_once_bt:

            {
                Intent intent = new Intent(MainActivity.this,AlarmReceiverOldOnce.class);
                intent.setAction("AlarmReceiverOldOnce");
                intent.putExtra("start","api19以下，单次,延时5秒 ");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis()+5*1000,pintent);
                break;
            }
            case R.id.old_repeat_bt:

            {
                Intent intent = new Intent(MainActivity.this,AlarmReceiverOldRepeat.class);
                intent.setAction("AlarmReceiverOldRepeat");
                intent.putExtra("start","api19以下，重复,间隔时间10秒");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                am.setRepeating(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),10*1000,pintent);
                Toast.makeText(getApplicationContext(),"在api19以上的不生效",Toast.LENGTH_SHORT).show();;
                break;
            }
            case R.id.new_once_bt:

            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    Intent intent = new Intent(MainActivity.this,AlarmReceiverNewOnce.class);
                    intent.setAction("AlarmReceiverNewOnce");
                    intent.putExtra("start","api19之后，单次");
                    PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                    AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                    am.setExact(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis()+5*1000,pintent);
                }else{
                    Toast.makeText(MainActivity.this,"api版本小与19，不生效",Toast.LENGTH_LONG).show();
                }

                break;
            }
            case R.id.new_once2_bt:

            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    Intent intent = new Intent(MainActivity.this,AlarmReceiverNewOnce2.class);
                    intent.setAction("AlarmReceiverNewOnce2");
                    intent.putExtra("start","api19之后，单次222");
                    PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                    AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                    am.setWindow(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),0,pintent);
                }else{
                    Toast.makeText(MainActivity.this,"api版本小与19，不生效",Toast.LENGTH_LONG).show();
                }

                break;
            }
            case R.id.new_repeat_bt:

            {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
                    Intent intent = new Intent(MainActivity.this,AlarmReceiverNewRepeat.class);
                   intent.setAction("AlarmReceiverNewRepeat");
                    intent.putExtra("start","api19之后，重复");
                    PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                    AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                    am.setWindow(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),0,pintent);
                }else{
                    Toast.makeText(MainActivity.this,"api版本小与19，不生效",Toast.LENGTH_LONG).show();
                }

                break;
            }




            case R.id.close_old_once_bt:
            {
                /**
                 * 取消的，action要一样
                 */
                Intent intent = new Intent(MainActivity.this,AlarmReceiverOldOnce.class);
                intent.setAction("AlarmReceiverOldOnce");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pintent);
                am.cancel(pintent);
                break;
            }



            case R.id.close_old_repeat_bt:
            {
                /**
                 * 取消的，action要一样
                 */
                Intent intent = new Intent(MainActivity.this,AlarmReceiverOldRepeat.class);
                 intent.setAction("AlarmReceiverOldRepeat");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pintent);
                am.cancel(pintent);
                break;
            }



            case R.id.close_new_once_bt:
            {
                /**
                 * 取消的，action要一样
                 */
                Intent intent = new Intent(MainActivity.this,AlarmReceiverNewOnce.class);
                 intent.setAction("AlarmReceiverNewOnce");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pintent);
                am.cancel(pintent);
                break;
            }


            case R.id.close_new_once2_bt:
            {
                /**
                 * 取消的，action要一样
                 */
                Intent intent = new Intent(MainActivity.this,AlarmReceiverNewOnce2.class);
                 intent.setAction("AlarmReceiverNewOnce2");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pintent);
                am.cancel(pintent);
                break;
            }


            case R.id.close_new_repeat_bt:
            {
                /**
                 * 取消的，action要一样
                 */
                Intent intent = new Intent(MainActivity.this,AlarmReceiverNewRepeat.class);
                 intent.setAction("AlarmReceiverNewRepeat");
                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
                // am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pintent);
                am.cancel(pintent);
                break;
            }


//            case R.id.close_bt:
//            {
//                /**
//                 * 取消的，action要一样
//                 */
//                Intent intent = new Intent(MainActivity.this,AlarmReceiver.class);
//               // intent.setAction("开始");
//                PendingIntent pintent = PendingIntent.getBroadcast(MainActivity.this,0,intent,0);
//                AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(ALARM_SERVICE);
//               // am.set(AlarmManager.RTC_WAKEUP,calender.getTimeInMillis(),pintent);
//                am.cancel(pintent);
//                break;
//            }
        }


    }
}
