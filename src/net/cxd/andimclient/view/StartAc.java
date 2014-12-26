package net.cxd.andimclient.view;

import net.cxd.andimclient.app.Application;
import net.cxd.andimclient.service.UserService;
import net.cxd.andimclient.util.TaskId;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;

import com.nb82.core.KennerControll;
import com.nb82.entity.BaseActivity;
import com.nb82.entity.Task;

public class StartAc extends BaseActivity{
	KennerControll kennerControll;
	Application app;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app= ((Application)getApplication());
		kennerControll = (KennerControll) app.cache.get("kennerControll");
	}
	@Override
	public void freash(Object object) {
		
		
		
	}
	public void login(View view){
		String name="";
		String password="";
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
