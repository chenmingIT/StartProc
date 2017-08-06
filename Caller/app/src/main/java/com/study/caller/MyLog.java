package com.study.caller;

import android.util.Log;

/**
 * Created by ChenMing on 2017/8/5.
 */

public class MyLog {
	private final static String TAG = "Caller App";
	private final static boolean DEBUG = true;

	public static void printD(String info) {
		if (DEBUG) {
			Log.d(TAG, info);
		}
	}
}
