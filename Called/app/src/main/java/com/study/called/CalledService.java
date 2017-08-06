	package com.study.called;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class CalledService extends Service {
	private final static int NOTIFICATION_ID = 0;
	Context context;
	MyBinder myBinder = new MyBinder();
	public class MyBinder extends Binder {
	}

	public CalledService() {
	}

	@Override
	public void onCreate() {
		super.onCreate();
		context = getApplicationContext();
		MyLog.printD("CalledService created!");
	}

	@Override
	public IBinder onBind(Intent intent) {
		MyLog.printD("onBind called!");
		createNotification();
		return myBinder;
	}

	@Override
	/**
	 * for start service callback
	 */
	public int onStartCommand(Intent intent, int flags, int startId) {
		createNotification();
		MyLog.printD("CalledService started!");
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		cancelNotifycation();
		MyLog.printD("CalledService destroyed!");
	}

	private void createNotification() {
		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		Notification.Builder builder = new Notification.Builder(context);
		builder.setSmallIcon(android.R.drawable.sym_def_app_icon)
				.setContentTitle("called app服务开启中")
				.setWhen(System.currentTimeMillis())
				.setContentIntent(pi);
		Notification notification = builder.getNotification();
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(NOTIFICATION_ID ,notification);
	}

	private void cancelNotifycation() {
		NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(NOTIFICATION_ID);
	}
}
