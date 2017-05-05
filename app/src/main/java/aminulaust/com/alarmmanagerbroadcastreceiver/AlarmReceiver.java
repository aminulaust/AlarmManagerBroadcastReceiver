package aminulaust.com.alarmmanagerbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Raju on 5/5/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARM ON from BroadcastReceiver ", Toast.LENGTH_SHORT).show();

        Intent intent1 = new Intent(context, RingtoneServices.class);
        context.startService(intent1);

    }
}
