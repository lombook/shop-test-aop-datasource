package com.jinglitong.shop.controller;

import java.nio.ByteBuffer;

public class Test002 {

	public static void main(String[] args) {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		String str = "abcd1";
		buf.put(str.getBytes());
		// 开启读取模式
		buf.flip();
		byte[] dst = new byte[buf.limit()];
		buf.get(dst, 0, 2);
		buf.mark();
		System.out.println(new String(dst, 0, 2));
		System.out.println(buf.position());
		buf.get(dst, 2, 2);
		System.out.println(new String(dst, 2, 2));
		System.out.println(buf.position());
		buf.reset();
		System.out.println("重置恢复到mark位置..");
		System.out.println(buf.position());
	}

}