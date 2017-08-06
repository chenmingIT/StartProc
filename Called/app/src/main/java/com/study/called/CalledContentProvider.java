package com.study.called;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class CalledContentProvider extends ContentProvider {
	Context context;
	public CalledContentProvider() {
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		MyLog.printD("content provide delete method called!");
		return 0;
	}

	@Override
	public String getType(Uri uri) {
		MyLog.printD("content provide getType method called!");
		return null;
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		MyLog.printD("content provide insert method called!");
		MainActivity.startMainActivity(context);
		return null;
	}

	@Override
	public boolean onCreate() {
		context = getContext();
		MyLog.printD("content provide created!");
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
	                    String[] selectionArgs, String sortOrder) {
		MyLog.printD("content provide query method called!");
		return null;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
	                  String[] selectionArgs) {
		MyLog.printD("content provide update method called!");
		return 0;
	}
}
