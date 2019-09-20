package com.jinglitong.shop.service;

import java.util.ArrayList;
import java.util.List;


import com.blink.jtblc.client.Remote;
import com.blink.jtblc.client.Transaction;
import com.blink.jtblc.client.bean.AmountInfo;
import com.blink.jtblc.client.bean.TransactionInfo;
import com.blink.jtblc.connection.Connection;
import com.blink.jtblc.connection.ConnectionFactory;

public class TransactionTestService {

	public static void main(String[] args) {
		 System.out.println(transaction());
		 System.out.println(transaction());
	}
	// static String server = "wss://s.jingtum.com:5020";// 生产环境
	public static int transaction() {
		 String server = "ws://ts5.jingtum.com:5020";// 测试环境
		 Connection conn = ConnectionFactory.getCollection(server);
		 Remote remote = new Remote(conn, true);
		 sendTransaction(remote);
		 System.out.println("remote="+remote.hashCode());
		 return conn.hashCode();
	}
	
	public static String sendTransaction(Remote remote) {
		String account = "j3UcBBbes7HFgmTLmGkEQQShM2jdHbdGAe";
		String to = "jNn89aY84G23onFXupUd7bkMode6aKYMt8";
		String secret = "ssWiEpky7Bgj5GFrexxpKexYkeuUv";
		AmountInfo amount = new AmountInfo();
		// amount.setCurrency("CNY");
		amount.setCurrency("SWT");
		amount.setValue("0.01");
		// amount.setIssuer("jBciDE8Q3uJjf111VeiUNM775AMKHEbBLS");
		Transaction tx = remote.buildPaymentTx(account, to, amount);
		tx.setSecret(secret);
		List<String> memos = new ArrayList<String>();
		memos.add("测试数据111");
		memos.add("测试数据222");
		memos.add("测试数据333");
		tx.addMemo(memos);
		TransactionInfo bean = tx.submit();
		if ("0".equals(bean.getEngineResultCode())) {
			System.out.println("数据上链成功 Hash：" + bean.getTxJson().getHash());
			return bean.getTxJson().getHash();
		} else {
			System.out.println("数据上链失败 Message：" + bean.getEngineResult());
			return bean.getEngineResult();
		}
	}
}
