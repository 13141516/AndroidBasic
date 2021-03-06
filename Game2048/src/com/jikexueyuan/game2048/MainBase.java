package com.jikexueyuan.game2048;

import com.jikexueyuan.service.RWService;
import com.jikexueyuan.service.RWService.GetMyBinder;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class MainBase {
	public RWService rwService = null;
	public Boolean isBound = false;
	private Context context;
	public MainBase(Context mContext) {
		// TODO Auto-generated constructor stub
		context = mContext;
	}
	public ServiceConnection myConnection = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
			Log.i("test", "exe ...");
			GetMyBinder gb = (GetMyBinder) service;
			rwService = gb.getRWService();
			isBound = true;
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			rwService = null;
			isBound = false;
		}

	};

	public void bindMyService() {
		if (!isBound) {
			Intent intent = new Intent(context, RWService.class);
			context.bindService(intent, myConnection, Context.BIND_AUTO_CREATE);
			isBound = true;
		}
	}

	public void unBindMyService() {
		if (isBound) {
			context.unbindService(myConnection);
			isBound = false;
		}
	}
}
