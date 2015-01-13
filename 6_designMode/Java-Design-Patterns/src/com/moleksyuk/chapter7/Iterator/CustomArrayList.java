package com.moleksyuk.chapter7.Iterator;

import java.util.ArrayList;

/**
 * Based on: "Patterns in Java", Mark Grand.
 * 
 * Date: Aug 7, 2011
 * 
 * @author moleksyuk
 */
public class CustomArrayList<T> {
	private ArrayList<T> list;

	public CustomArrayList() {
		list = new ArrayList<T>();
	}

	public CustomArrayList(int capacity) {
		list = new ArrayList<T>(capacity);
	}

	public Iterator<T> getIterator() {
		return new CustromArrayListIterator();
	}

	public int getSize() {
		return list.size();
	}

	// And other methods...

	private class CustromArrayListIterator implements Iterator<T> {

		@Override
		public T getNext() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

	}
}
