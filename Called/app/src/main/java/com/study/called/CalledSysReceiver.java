package com.study.called;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class CalledSysReceiver extends BroadcastReceiver {
	public CalledSysReceiver() {
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		MyLog.printD("receive system broadcast！");
		MainActivity.startMainActivity(context);
	}
}
