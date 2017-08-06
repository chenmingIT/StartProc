package com.study.called;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CalledThirdReceiver extends BroadcastReceiver {
	public CalledThirdReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		MyLog.printD("receive third broadcast!");
		MainActivity.startMainActivity(context);
	}
}
