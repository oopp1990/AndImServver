package net.cxd.andimclient.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class LocalService extends Service {
	private static final String tag = "LocalService >>> ";

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public boolean onUnbind(Intent intent) {
		return super.onUnbind(intent);
	}

	public interface MyBinder {
		Service getService();
	}

	public class LocalBinder extends Binder implements MyBinder {
		@Override
		public Service getService() {
			return LocalService.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return new LocalBinder();
	}

	class NetBroadCastReceiver extends BroadcastReceiver {
		private ConnectivityManager connectivityManager;
		private NetworkInfo info;

		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				connectivityManager = (ConnectivityManager) context
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				info = connectivityManager.getActiveNetworkInfo();
				if (info != null && info.isAvailable()) {
					Log.i(tag, "当前有可用网络！");
				} else {
					Log.i(tag, "当前没有可用网络！");
				}
			}
		}
	}

}
