package com.jinglitong.shop.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

// udp服务器端
public class UdpClient {

	 public static void main(String[] args) throws IOException {
		 System.out.println("udp客户端启动连接....");
		 DatagramSocket ds = new DatagramSocket();
		 String str="蚂蚁课堂";
		 byte[] bytes= str.getBytes();
		 DatagramPacket dp= new DatagramPacket(bytes, bytes.length,InetAddress.getByName("127.0.0.1"),8080);
		 ds.send(dp);
		 ds.close();
	}
	
}

//socket客户端代码
class UdpSocketServer {

	public static void main(String[] args) throws IOException {
		System.out.println("udp服务器端启动连接....");
		DatagramSocket ds = new DatagramSocket(8080);
		byte[] bytes = new byte[1024];
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
		// 阻塞,等待接受客户端发送请求
		ds.receive(dp);
		System.out.println("来源:"+dp.getAddress()+",端口号:"+dp.getPort());
		// 获取客户端请求内容
		String str=new String(dp.getData(),0,dp.getLength());
		System.out.println("str:"+str);
		ds.close();
	}

}