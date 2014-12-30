package net.cxd.andimclient.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

import com.alibaba.fastjson.JSON;
import com.nb82.util.AcUtil;

import net.cxd.im.entity.UserMsg;
import net.cxd.im.server.MessageLister;

public class MessageListener extends MessageLister {

	@Override
	public void open(SocketChannel channel) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void openComplate(SocketChannel channel) throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public void write(SocketChannel channel, ByteBuffer receivebuffer)
			throws IOException {
		// TODO Auto-generated method stub

	}

	public void read(SocketChannel channel, byte[] buffer) throws IOException {
		if (buffer[0] == 0) {
			return;
		}
		if (buffer[1] == START_BYTE && buffer[buffer.length - 1] == END_CHAR) {
			//完整数据包，进行解析
			UserMsg msg =JSON.parseObject(new String(buffer, 1,buffer.length -1),UserMsg.class);
//			msg.setIsRead(1);
			
			
			
			//TODO 判断当前界面 是否是聊天界面， 如果不是则进行notifyaction   如果是， 则更新界面
			
			
			
		} else
			return;// 丢弃该数据包，因为不完整。

	}

	@Override
	public void cachtException(Exception e) throws IOException {
		// TODO Auto-generated method stub

	}
}
