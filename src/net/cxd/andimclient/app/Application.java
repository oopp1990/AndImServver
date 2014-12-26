package net.cxd.andimclient.app;

import net.cxd.andimclient.service.LocalService;
import net.cxd.im.service.UserHttpService;
import net.cxd.im.service.impl.UserHttpServiceImpl;
import android.content.Intent;
import android.database.SQLException;
import android.os.UserHandle;

import com.nb82.bean.NIO.im.entity.User;
import com.nb82.bean.db.CFrameDb;
import com.nb82.core.AppContextControll;
import com.nb82.util.DbException;
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
		try {
			cFrameDb.findAllBySql(User.class, " select * from user where id=(select max(id) from user)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		UserHttpService httpService = new UserHttpServiceImpl();
		cache.put("httpService", httpService);
		//TODO   find last login user 
		
		
		
		Intent intent = new Intent(this, LocalService.class);
		startService(intent);
		super.onCreate();
	}
}
