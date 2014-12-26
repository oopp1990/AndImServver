package net.cxd.andimclient.app;

import net.cxd.andimclient.service.LocalService;
import net.cxd.im.service.UserHttpService;
import net.cxd.im.service.impl.UserHttpServiceImpl;
import android.content.Intent;

import com.nb82.bean.db.CFrameDb;
import com.nb82.core.AppContextControll;
import com.nb82.view.bitmap.CBitmap;

public class Application extends AppContextControll {

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		ex.printStackTrace();
		Intent intent = new Intent(this, LocalService.class);
		stopService(intent);
		
			
		
		
	}

	@Override
	public void onCreate() {
		cache.put("cbitmap", CBitmap.create(this));
		CFrameDb cFrameDb = CFrameDb.create(this, "ImClient.db", true);
		cache.put("cFrameDb", cFrameDb);
		

		
		UserHttpService httpService = new UserHttpServiceImpl();
		cache.put("httpService", httpService);
		
		Intent intent = new Intent(this, LocalService.class);
		startService(intent);
		super.onCreate();
	}
}
