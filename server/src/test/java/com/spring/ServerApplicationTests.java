package com.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

	public  void printList1(List<Object> list) {
		list.add(new Object());
		for (Object elem : list)
			System.out.println(elem + " ");
		System.out.println();
	}


	@Test
	public void contextLoads() {
		ArrayList<? extends Number> list=new ArrayList<Number>();
	}

}
