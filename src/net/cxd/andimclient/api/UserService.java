package net.cxd.andimclient.api;

import com.nb82.entity.Task;

import net.cxd.im.entity.FriendGroup;
import net.cxd.im.entity.UserFriend;
import net.cxd.im.entity.UserInfo;
import net.cxd.im.service.UserHttpService;
import net.cxd.im.service.impl.UserHttpServiceImpl;

public class UserService {
	private static UserHttpService httpService = new UserHttpServiceImpl();

	private static UserHttpService getHttpService() {
		return httpService == null ? new UserHttpServiceImpl() : httpService;
	}

	private Object login(Task task) throws Exception {
		return getHttpService().login((String) task.params.get("username"),
				(String) task.params.get("password"));
	}

	private Object regist(Task task) throws Exception {
		return getHttpService().regist((String) task.params.get("username"),
				(String) task.params.get("password"));
	}

	private Object changePassword(Task task) throws Exception {
		return getHttpService().changePassword(
				(Integer) task.params.get("uid"),
				(String) task.params.get("password"));
	}

	private Object updateUserInfo(Task task) throws Exception {
		return getHttpService().updateUserInfo(
				(UserInfo) task.params.get("userInfo"));
	}

	private Object addGroup(Task task) throws Exception {
		return getHttpService().addGroup(
				(FriendGroup) task.params.get("friendGroup"));
	}

	private Object deleteFriendGroup(Task task) throws Exception {
		return getHttpService().deleteFriendGroup(
				(FriendGroup) task.params.get("friendGroup"));
	}

	private Object addFriend(Task task) throws Exception {
		return getHttpService().addFriend(
				(UserFriend) task.params.get("userFriend"));
	}

	private Object moveFriendToOtherGroup(Task task) throws Exception {
		return getHttpService()
				.moveFriendToOtherGroup((String) task.params.get("id"),
						(String) task.params.get("gid"));
	}

	private Object removeFriend(Task task) throws Exception {
		return getHttpService().removeFriend((String) task.params.get("id"),
				(String) task.params.get("gid"));
	}

	private Object getAllFriend(Task task) throws Exception {
		return getHttpService().getAllFriend((String) task.params.get("uid"));
	}
}
