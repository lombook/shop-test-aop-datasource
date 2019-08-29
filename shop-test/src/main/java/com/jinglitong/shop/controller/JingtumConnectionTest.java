package com.jinglitong.shop.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.blink.jtblc.client.Remote;
import com.blink.jtblc.client.Transaction;
import com.blink.jtblc.client.Wallet;
import com.blink.jtblc.client.bean.Account;
import com.blink.jtblc.client.bean.AccountData;
import com.blink.jtblc.client.bean.AccountInfo;
import com.blink.jtblc.client.bean.AccountOffers;
import com.blink.jtblc.client.bean.AccountRelations;
import com.blink.jtblc.client.bean.AmountInfo;
import com.blink.jtblc.client.bean.Line;
import com.blink.jtblc.client.bean.Memo;
import com.blink.jtblc.client.bean.TransactionInfo;
import com.blink.jtblc.connection.Connection;
import com.blink.jtblc.connection.ConnectionFactory;
import com.jinglitong.shop.jingtum.JingtumConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JingtumConnectionTest {
	@Test
	public void test() {
		/*getWalletAndActive();*/
		/*AmountInfo amount = new AmountInfo();
		amount.setCurrency("SWT");
		amount.setValue("0.00001");
		String hash = sendTransaction(JingtumConnection.getConnection(), "jfg67BeAqJ8PAha691X3bqQt6AwPPN1MaW", "jBFMwiM62fJCwdh6cM1DSoSYDSCdWpezt8", "ssSUQyB5PwLpUFpCtzUK9kMPG7WMo", amount);
		*/
		//getSwtcBleans(JingtumConnection.getConnection(), "jBFMwiM62fJCwdh6cM1DSoSYDSCdWpezt8");
		
		//getTx(JingtumConnection.getConnection(), "44DE4C2E835D4B1E344C71659555B680893BEBD3BE04F7430CC9936EEADE533C");
		
	}
	
	/**
	 * 
	 * 功能说明:生成钱包并激活，返回钱包公钥私钥
	 * @return
	 */
	public Wallet getWalletAndActive () {
		Wallet wallet = Wallet.generate();
		System.out.println("address:" + wallet.getAddress());
		System.out.println("secret:" + wallet.getSecret());
		
		AmountInfo amount = new AmountInfo();
		amount.setCurrency("SWT");
		amount.setValue("25");
		String hash = sendTransaction(JingtumConnection.getConnection(), "jfg67BeAqJ8PAha691X3bqQt6AwPPN1MaW", wallet.getAddress(), "ssSUQyB5PwLpUFpCtzUK9kMPG7WMo", amount);
		System.out.println("激活hash："+hash);
		return wallet;
	}
	/**
	 * 
	 * 功能说明:转账
	 * @param conn 连接
	 * @param from 转账发出方
	 * @param to 收款方
	 * @param secret 发出方秘钥
	 * @param amount 币种 + 金额
	 * @return 返回转账hash
	 */
	public  String sendTransaction(Connection conn,String from,String to,String secret,AmountInfo amount) {
		Remote remote = new Remote(conn, false);
		// from = "jfg67BeAqJ8PAha691X3bqQt6AwPPN1MaW";
		//String to = "jPk5gyeqZmfbpaTtT1RiBgVe1xUb9mPGgE";
		//String secret = "ssSUQyB5PwLpUFpCtzUK9kMPG7WMo";
		/*AmountInfo amount = new AmountInfo();
		// amount.setCurrency("CNY");
		amount.setCurrency("SWT");
		amount.setValue("25");*/
		// amount.setIssuer("jBciDE8Q3uJjf111VeiUNM775AMKHEbBLS");
		Transaction tx = remote.buildPaymentTx(from, to, amount);
		tx.setSecret(secret);
		List<String> memos = new ArrayList<String>();
		memos.add(to);
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
	
	/**
	 * 
	 * 功能说明: 获取余额
	 * @param conn
	 * @param account
	 * @return
	 */
	public  AccountData getSwtcBleans(Connection conn,String account) {
		Remote remote = new Remote(conn, false);
		//String account = "jPk5gyeqZmfbpaTtT1RiBgVe1xUb9mPGgE";
		AccountInfo bean1 = remote.requestAccountInfo(account, null, null);
		System.out.println("SWTC余额:" + bean1.getAccountData().getBalance());
		AccountRelations bean = remote.requestAccountRelations(account, null, "trust");
		for (int i = 0; i < bean.getLines().size(); i++) {
		     Line l = bean.getLines().get(i);
		     System.out.println(l.getCurrency() +"==="+l.getBalance());
		}
		AccountOffers o = remote.requestAccountOffers(account, null);
		return null;
	}
	
	public static Account getTx(Connection conn,String hash) {
		Remote remote = new Remote(conn, false);
		Account bean = remote.requestTx(hash);
		System.out.println("from:" + bean.getAccount());
		System.out.println("to:" + bean.getDestination());
		if (bean.getAmount() != null) {
			System.out.println("currency:" + bean.getAmount().getCurrency());
			System.out.println("value:" + bean.getAmount().getValue());
			System.out.println("issuer:" + bean.getAmount().getIssuer());
		}
		if(bean.getMemos().size() != 0) {
			for (Memo m : bean.getMemos()) {
				System.out.println(m.getMemoData()+"==="+m.getMemoType());
			}
		}
		return bean;
	}
}
