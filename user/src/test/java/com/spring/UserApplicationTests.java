package com.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserApplicationTests {

	@Test
	public void contextLoads() {

	}

	@Test
	public void testBase(){
		String a="abc";
		String b="abc";
		System.out.println(a==b);
		String c=new String("abc");
		String d=new String("abc");
		System.out.println(c==d);
		Integer e=1;
		Integer f=1;
		System.out.println(e==f);
		Integer g=150;
		Integer h=150;
		System.out.println(g==h);
		System.out.println(Integer.compare(g,h));
		int x=130;
		int y=130;
		System.out.println(x==y);
	}


}
