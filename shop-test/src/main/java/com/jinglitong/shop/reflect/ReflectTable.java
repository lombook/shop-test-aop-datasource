package com.jinglitong.shop.reflect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;

@Table(value = "it_user")
class User {
	@ProPerty(value = "it_id", leng = 10)
	private String id;
	@ProPerty(value = "it_name", leng = 10)
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface Table {
	String value();
}
@Retention(RetentionPolicy.RUNTIME)
@interface ProPerty {
	String value();
	int leng();
}
public class ReflectTable {
	public static void main(String[] args) throws ClassNotFoundException {
		Class<?> forName = Class.forName("com.jinglitong.shop.reflect.User");
		StringBuffer sf = new StringBuffer();
		sf.append(" select ");
		// 获取当前的所有的属性
		Field[] declaredFields = forName.getDeclaredFields();
		for (int i = 0; i < declaredFields.length; i++) {
			Field field = declaredFields[i];
			ProPerty proPertyAnnota = field.getDeclaredAnnotation(ProPerty.class);
			String proPertyName = proPertyAnnota.value();
			sf.append(" " + proPertyName);
			if (i < declaredFields.length - 1) {
				sf.append(" , ");
			}
		}
		Table tableAnnota = forName.getDeclaredAnnotation(Table.class);
		// 表的名称
		String tableName = tableAnnota.value();
		sf.append(" from " + tableName);
		System.out.println(sf.toString());
	}
} 