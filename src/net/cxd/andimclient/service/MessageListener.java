package net.cxd.andimclient.service;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

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
		if (buffer[1] != START_BYTE && buffer[buffer.length - 1] != END_CHAR) {
			
			
		} else
			return;// 丢弃该数据包，因为不完整。

	}

	@Override
	public void cachtException(Exception e) throws IOException {
		// TODO Auto-generated method stub

	}
}
