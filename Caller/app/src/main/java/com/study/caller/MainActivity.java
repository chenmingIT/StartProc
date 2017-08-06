package com.study.caller;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends BaseActivity {
	private MyServiceConnection connection = new MyServiceConnection();
	private boolean isBindService = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyLog.printD("MainActivity Created!");
	}

	public void startActivity(View view) {
		Intent intent = new Intent();
		intent.setAction("com.study.called.intent.main");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PackageManager packageManager = getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
		if (activities.size() > 0) {
			startActivity(intent);
			MyLog.printD("startActivity called app found!");
		} else {
			Toast.makeText(this, "未安装Called App！", Toast.LENGTH_SHORT).show();
			MyLog.printD("startActivity called app not found!");
		}
	}

	public void startService(View view) {
		Intent intent = new Intent();
		String pkg = "com.study.called";
		String cls = "com.study.called.CalledService";
		intent.setComponent(new ComponentName(pkg, cls));
		int startServiceOper = view.getId();
		switch (startServiceOper) {
			case R.id.start_service:
				ComponentName componentName = startService(intent);
				if (componentName == null) {
					Toast.makeText(this, "找不到该服务！", Toast.LENGTH_SHORT).show();
					MyLog.printD("service not found!");
				} else {
					MyLog.printD("startService "+ componentName.toString());
				}
				break;
			case R.id.stop_service:
				stopService(intent);
				MyLog.printD("stopService!");
				break;
			default:
				break;
		}
	}

	public void bindService(View view) {
		Intent intent = new Intent();
		String pkg = "com.study.called";
		String cls = "com.study.called.CalledService";
		intent.setComponent(new ComponentName(pkg, cls));
		int bindServiceOper = view.getId();
		switch (bindServiceOper) {
			case R.id.bind_service:
				isBindService = bindService(intent, connection, BIND_AUTO_CREATE);
				break;
			case R.id.unbind_service:
				if (isBindService) {
					unbindService(connection);
					MyLog.printD("unbind servic successful!");
					isBindService = false;
				} else {
					Toast.makeText(this, "解绑失败，服务已经解除绑定！", Toast.LENGTH_SHORT).show();
					MyLog.printD("unbind service fail for connection has gone!");
				}
				break;
			default:
				break;
		}
	}

	private class MyServiceConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MyLog.printD("service connected successful");
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			MyLog.printD("service disconnected successful");
		}
	}

	public void sendBroadcast(View view) {
		String broadcastAction = "com.study.called.thirdBrAction";
		Intent intent = new Intent();
		intent.setAction(broadcastAction);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
		MyLog.printD("send broadcast!");
		sendBroadcast(intent);
	}

	public void provide(View view) {
		ContentResolver contentResolver = getContentResolver();
		Uri uri = Uri.parse("content://com.study.called.contentprovide/");
		ContentValues contentValues = new ContentValues();
		contentValues.put("key","value");
		Uri newUri = contentResolver.insert(uri, contentValues);
		MyLog.printD("call app by method insert of provide!");
	}
}
