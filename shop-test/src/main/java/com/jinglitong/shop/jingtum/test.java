package com.jinglitong.shop.jingtum;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import com.blink.jtblc.client.Remote;
import com.blink.jtblc.client.Transaction;
import com.blink.jtblc.client.bean.Account;
import com.blink.jtblc.client.bean.AmountInfo;
import com.blink.jtblc.client.bean.Memo;
import com.blink.jtblc.client.bean.TransactionInfo;
import com.blink.jtblc.connection.Connection;
import com.blink.jtblc.connection.ConnectionFactory;

public class test{

 
	
	/*public static void main(String[] args) {
		getTx();
	}*/

	public static int transactionConnection(Connection conn) {
		//Connection conn = JingtumConnection.getConnection();
		Remote remote = new Remote(conn, true);
		sendTransaction(remote);
		System.out.println("remote=" + remote.hashCode());
		return conn.hashCode();
	}
	

	public static String sendTransaction(Remote remote) {
		String account = "j3UcBBbes7HFgmTLmGkEQQShM2jdHbdGAe";
		String to = "jNn89aY84G23onFXupUd7bkMode6aKYMt8";
		String secret = "ssWiEpky7Bgj5GFrexxpKexYkeuUv";
		AmountInfo amount = new AmountInfo();
		// amount.setCurrency("CNY");
		amount.setCurrency("SWT");
		amount.setValue("0.00001");
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
	
	public void getTx() {
		String server = "wss://hc.jingtum.com:5020";
		Connection conn = ConnectionFactory.getCollection(server);
		Remote remote = new Remote(conn, false);
		String hash = "0DDBFAC8DA993BEA3652F10AC274AA32383669D3DE5A5446A43A895902C50301";
		Account bean = remote.requestTx(hash);
		System.out.println("from:" + bean.getAccount());
		System.out.println("to:" + bean.getDestination());
	}
}
