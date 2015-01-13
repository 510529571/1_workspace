package com.moleksyuk.chapter7.Iterator;

/**
 * Based on: "Patterns in Java", Mark Grand.
 * 
 * Date: Aug 7, 2011
 * 
 * @author moleksyuk
 */
public class Main {

	public static void main(String[] args) {
		CustomArrayList<String> list = new CustomArrayList<String>();
		Iterator<String> iterator = list.getIterator();

		System.out.println(iterator.getClass());
	}

}
