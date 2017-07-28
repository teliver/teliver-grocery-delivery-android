package com.telivergrocerydriver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.GsonBuilder;
import com.teliver.sdk.core.Teliver;
import com.teliver.sdk.models.NotificationData;

import java.util.Map;

public class FirebaseMessaging extends FirebaseMessagingService {

    private Application application;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        try {
            application = (Application) getApplicationContext();
            if (Teliver.isTeliverPush(remoteMessage)) {
                Map<String, String> pushData = remoteMessage.getData();
                Log.d("TELIVER::", "PUSH MESSAGE == " + remoteMessage.getData());
                final NotificationData data = new GsonBuilder().create().fromJson(pushData.get("description"), NotificationData.class);
                Log.d("TELIVER::", "PUSH MESSAGE == " + data.getTrackingID() + "message == " + data.getMessage() + "command == "
                        + data.getCommand() + data.getPayload());
                Intent intent = new Intent();
                intent.putExtra("msg", data.getMessage());
                intent.putExtra("payload", data.getPayload());
                intent.setAction("tripId");
                LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

                sendPush(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendPush(NotificationData data) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this);
        notification.setContentTitle("Teliver");
        notification.setContentText(data.getMessage());
        notification.setSmallIcon(R.drawable.ic_notification_icon);
        notification.setStyle(new NotificationCompat.BigTextStyle().bigText(data.getMessage()).setBigContentTitle("Teliver"));
        notification.setAutoCancel(true);
        notification.setPriority(Notification.PRIORITY_MAX);
        notification.setDefaults(Notification.DEFAULT_ALL);

        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        notification.setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(6, notification.build());
    }
}


