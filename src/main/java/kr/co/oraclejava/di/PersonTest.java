package kr.co.oraclejava.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonTest {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("sample01.xml");
		
		Person p = (Person)context.getBean("gnd");
		
		System.out.println(p.getId());
		System.out.println(p.getName());
		System.out.println(p.getAge());
	}

}
