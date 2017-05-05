package aminulaust.com.alarmmanagerbroadcastreceiver;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.timePicker)
    TimePicker alarmTimePicker;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    public void startAlarm(View view) {
        Toast.makeText(this, "ALARM IS ON", Toast.LENGTH_SHORT).show();
        java.util.Calendar calendar = java.util.Calendar.getInstance();

        if(Build.VERSION.SDK_INT >= 23) {
            calendar.set(java.util.Calendar.HOUR_OF_DAY, alarmTimePicker.getHour());
            calendar.set(java.util.Calendar.MINUTE, alarmTimePicker.getMinute());
        }else {
            calendar.set(java.util.Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(java.util.Calendar.MINUTE, alarmTimePicker.getCurrentMinute());
        }

        intent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP
                , calendar.getTimeInMillis(), 1000 * 60, pendingIntent);
    }

    public void cancelAlarm(View view) {
        Intent i = new Intent(this, RingtoneServices.class);
        stopService(i);

        intent = new Intent(this, AlarmReceiver.class);

        pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.cancel(pendingIntent);
    }
}