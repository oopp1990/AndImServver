package net.cxd.andimclient.view;

import net.cxd.andimclient.R;
import net.cxd.andimclient.app.Application;
import net.cxd.andimclient.service.UserService;
import net.cxd.andimclient.util.TaskId;
import net.cxd.im.entity.User;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.nb82.bean.db.CFrameDb;
import com.nb82.bean.db.sqlite.DbModel;
import com.nb82.core.KennerControll;
import com.nb82.entity.BaseActivity;
import com.nb82.entity.Task;

public class StartAc extends BaseActivity{
	KennerControll kennerControll;
	Application app;
	String name;
	String password;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.im_start);
		init(null);
	}
	
	@Override
	public void init(Object object) {
		app= ((Application)getApplication());
		kennerControll = (KennerControll) app.cache.get("kennerControll");
		try {
			DbModel model = ((CFrameDb)app.cache.get("cFrameDb")).findDbModelBySQL( " select * from user where lastLogin=(select max(lastLogin) from user)");
			if (model != null) {
				name = model.get("name").toString();
				password = model.get("password").toString();
				setContentView(R.layout.im_start);
			}else{
				setContentView(R.layout.im_login);
			}
		} catch (Exception e) {
			e.printStackTrace();
			setContentView(R.layout.im_login);
		}
		if (app.cache.get("user") != null) {
			login(null);
		}
	}
	
	@Override
	public void freash(Object object) {
		
		
		
	}
	
	public void login(View view){
		Task task = new Task(TaskId.login,UserService.class, "login",handler, null);
		task.params.put("name", name);
		task.params.put("password", password);
		task.obj = app.cache.get("httpService");
		kennerControll.doTask(task);
	}
	private Handler handler = new Handler(getMainLooper()){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case TaskId.login:
				if (msg.obj != null) {
					freash(msg.obj);
				}
				break;
			case TaskId.regist:
				
				
				break;
			default:
				break;
			}
			
		}
	};
}
