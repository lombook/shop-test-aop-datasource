package com.jinglitong.shop.jingtum;

import com.blink.jtblc.connection.Connection;
import com.blink.jtblc.connection.ConnectionFactory;

public class JingtumConnection {

	private static Connection con = null;

	private static String server = "ws://ts5.jingtum.com:5020";// 测试环境
	//private static String server = "wss://s.jingtum.com:5020";// 生产环境

	public static Connection getConnection() {

		if (con == null) {
			synchronized (JingtumConnection.class) {
				if (con == null) {
					con = ConnectionFactory.getCollection(server);
				}
			}
		}
		return con;
	}

}
