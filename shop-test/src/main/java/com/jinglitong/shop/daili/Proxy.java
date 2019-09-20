package com.jinglitong.shop.daili;
public class Proxy  implements Hose {
	private XiaoMing xiaoMing;
	
	public Proxy(XiaoMing xiaoMing) {
		this.xiaoMing = xiaoMing;
	}
	public void mai() {
		System.out.println("我是中介 看你买房开始啦!");
		xiaoMing.mai();
		System.out.println("我是中介 看你买房结束啦!");
	}
	public static void main(String[] args) {
		Hose proxy = new Proxy(new XiaoMing());
		proxy.mai();
	}
	
	@Override
	public void mai2() {
		// TODO Auto-generated method stub
		
	}
}