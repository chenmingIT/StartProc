package com.study.called;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
	private int jobScheduleId;
	private boolean isJobSchedule = false;
	private static final int JOB_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MyLog.printD("MainActivity Created!");
	}

	public static void startMainActivity (Context context) {
		Intent intent = new Intent();
		intent.setAction("com.study.called.intent.main");
		intent.addCategory("android.intent.category.DEFAULT");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	private void startJobScheduler() {
		JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
		ComponentName componentName = new ComponentName(getPackageName(), CalledJobService.class.getName());
		JobInfo jobInfo = new JobInfo.Builder(JOB_ID, componentName)
				.setPeriodic(5000).build();
		isJobSchedule = jobScheduler.schedule(jobInfo) == JobScheduler.RESULT_SUCCESS;
		if (isJobSchedule) {
			MyLog.printD("JobSchedule began!");
		}
	}

	private void stopJobScheduler() {
		JobScheduler jobScheduler = (JobScheduler) getSystemService(JOB_SCHEDULER_SERVICE);
		jobScheduler.cancel(JOB_ID);
		MyLog.printD("JobSchedule ended!");
	}

	public void jobScheduleControl(View view) {
		int actionId = view.getId();
		switch (actionId) {
			case R.id.startJob:
				if (!isJobSchedule) {
					startJobScheduler();
				}
				break;
			case R.id.stopJob:
				if (isJobSchedule) {
					stopJobScheduler();
				}
				break;
			default:
				break;
		}
	}
}
