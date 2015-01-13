package com.moleksyuk.chapter7.Iterator;

/**
 * Based on: "Patterns in Java", Mark Grand.
 * 
 * Date: Aug 7, 2011
 * 
 * @author moleksyuk
 */
public interface Iterator<T> {

	public T getNext();

	public boolean hasNext();

}
