package com.sec.app.RilErrorNotifier;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class PhoneErrorReceiver extends BroadcastReceiver {
    public static String RIL_RESET_ACTION = "android.intent.action.RIL_RESET_ACTION";
    private static String LOG_TAG = "RilErrorNotifier";

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action.equals(RIL_RESET_ACTION)) {
            rilReset(context);
        }
    }
    
    private void rilReset(Context context) {
        NotificationManager nm = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        Notification noti = new Notification.Builder(context)
                .setContentTitle("The radio has reset.")
                .setContentText("A restart may be required for connectivity.")
                .setTicker("The phone radio has reset.")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)
                .getNotification();
        nm.notify(1, noti);
    }
}
