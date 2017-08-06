package com.study.called;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.widget.Toast;

	public class CalledJobService extends JobService {
	public CalledJobService() {
	}

	@Override
	public boolean onStartJob(JobParameters params) {
		Toast.makeText(this, "job 执行！", Toast.LENGTH_SHORT).show();
		MyLog.printD("startJob!");
		return false;
	}

	@Override
	public boolean onStopJob(JobParameters params) {
		MyLog.printD("stopJob!");
		return false;
	}
}
