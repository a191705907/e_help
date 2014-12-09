package routeplan;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Process;
import android.util.Log;
import cn.smssdk.SMSSDK;
import com.baidu.mapapi.SDKInitializer;
import com.xiaomi.channel.commonutils.logger.LoggerInterface;
import com.xiaomi.mipush.sdk.Logger;
import com.xiaomi.mipush.sdk.MiPushClient;
import lbs.LocationService;

import java.util.List;

public class HelpApplication extends Application {
    public static final String APP_ID = "2882303761517274441";
    public static final String APP_KEY = "5841727484441";
    public static final String TAG = "help_mipush";

	@Override
	public void onCreate() {
		super.onCreate();
		
		Intent intent = new Intent(this, LocationService.class);
		startService(intent);
		
		SDKInitializer.initialize(this);
		// 初始化push推送服务
		if (shouldInit()) {
			MiPushClient.registerPush(this, APP_ID, APP_KEY);
		}
		SMSSDK.initSDK(this, "4a0d041cc7be", "030ab5f5a5363e4fb03399bdea4b522d");
		// 打开Log
		LoggerInterface newLogger = new LoggerInterface() {

			@Override
			public void setTag(String tag) {
				// ignore
			}

			@Override
			public void log(String content, Throwable t) {
				Log.d(TAG, content, t);
			}

			@Override
			public void log(String content) {
				Log.d(TAG, content);
			}
		};
		Logger.setLogger(this, newLogger);
	}

	private boolean shouldInit() {
		ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
		List<RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = getPackageName();
		int myPid = Process.myPid();
		for (RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}

}