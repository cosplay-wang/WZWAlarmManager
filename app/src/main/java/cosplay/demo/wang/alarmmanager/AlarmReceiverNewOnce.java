package cosplay.demo.wang.alarmmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by zhiwei.wang on 2016/7/12.
 */
public class AlarmReceiverNewOnce extends BroadcastReceiver {

    public  final  String TAG = "WZWcalender";
    String transmition;
    Calendar calender;
    @Override
    public void onReceive(Context context, Intent intent) {

        calender = Calendar.getInstance();
        int year = calender.get(Calendar.YEAR);
        int month = calender.get(Calendar.MONTH);//得到的月份是-1的
        int day = calender.get(Calendar.DAY_OF_MONTH);
        int hour = calender.get(Calendar.HOUR_OF_DAY);
        int min = calender.get(Calendar.MINUTE);
        int second = calender.get(Calendar.SECOND);
        Log.i(TAG,"触发时间:"+year + "年" + month + "月" + day + "天" + hour + "时" + min + "分" + second + "秒");
        transmition = intent.getStringExtra("start");
        Toast.makeText(context,transmition,Toast.LENGTH_SHORT).show();;
        transmition = "";

    }


}
