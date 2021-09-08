package com.wjl.stream;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

public class TestTransaction {
	
	List<Transaction> transactions = null;
	
	@Before
	public void before(){
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		
		transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950)
		);
	}
	
	//1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
	@Test
	public void test1(){
		transactions.stream()
				.filter(x -> x.getYear() == 2011)
				.sorted(Comparator.comparingInt(Transaction::getValue))
				.forEach(System.out::println);
	}
	
	//2. 交易员都在哪些不同的城市工作过？
	@Test
	public void test2(){
		transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getCity)
				.distinct()
				.forEach(System.out::println);
	}
	
	//3. 查找所有来自剑桥的交易员，并按姓名排序
	@Test
	public void test3(){
		transactions.stream()
				.map(Transaction::getTrader)
				.filter( x -> x.getCity().equals("Cambridge"))
				.sorted(Comparator.comparing(Trader::getName))
				.forEach(System.out::println);
	}

	//4. 返回所有交易员的姓名字符串，按字母顺序排序
	@Test
	public void test4(){
		Optional<String> result = transactions.stream()
				.map(Transaction::getTrader)
				.map(Trader::getName)
				.sorted()
				.reduce(String::concat);
		System.out.println(result.get());
	}
	//5. 有没有交易员是在米兰工作的？
	@Test
	public void test5(){
		boolean result = transactions.stream()
				.map(Transaction::getTrader)
				.anyMatch(x -> x.getCity().equals("Milan"));
		System.out.println(result);
	}
	
	
	//6. 打印生活在剑桥的交易员的所有交易额
	@Test
	public void test6(){
		Optional<Integer> sum = transactions.stream()
				.filter(x -> x.getTrader().getCity().equals("Cambridge"))
				.map(Transaction::getValue)
				.reduce(Integer::sum);
		System.out.println(sum);
	}
	
	
	//7. 所有交易中，最高的交易额是多少
	@Test
	public void test7(){
		Optional<Integer> max = transactions.stream()
				.map(Transaction::getValue)
				.max(Integer::compareTo);
		System.out.println(max.get());
	}
	
	//8. 找到交易额最小的交易
	@Test
	public void test8(){
		Optional<Integer> max = transactions.stream()
				.map(Transaction::getValue)
				.min(Integer::compareTo);
		System.out.println(max.get());
	}

}