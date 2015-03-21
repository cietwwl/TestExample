package cn.dyz.map;

import java.util.TreeMap;

public class TestMap {

	public static void main(String[] args) {
		TreeMap<Integer, String> t = new TreeMap<Integer, String>();
		t.put(1,"1");
		t.put(6,"6");
		t.put(2,"2");
		t.put(5,"5");
		System.out.println(t.higherEntry(2).getValue());
	}

}
