package com.jinglitong.shop.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		System.out.println("socket tcp 客户端启动....");
		Socket socket = new Socket("127.0.0.1", 8080);
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write("我是蚂蚁课堂".getBytes());
		socket.close();
	}
}

class TcpServer {

	public static void main(String[] args) throws IOException {
		System.out.println("socket tcp服务器端启动....");
		ServerSocket serverSocket = new ServerSocket(8080);
		// 等待客户端请求
		Socket accept = serverSocket.accept();
		accept.setKeepAlive(true);
		InputStream inputStream = accept.getInputStream();
		// 转换成string类型
		byte[] buf = new byte[1024];
		int len = inputStream.read(buf);
		String str = new String(buf, 0, len);
		System.out.println("服务器接受客户端内容:" + str);
		serverSocket.close();
	}
}