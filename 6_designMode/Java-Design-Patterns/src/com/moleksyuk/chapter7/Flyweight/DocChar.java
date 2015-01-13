package com.moleksyuk.chapter7.Flyweight;

/**
 * Based on: "Patterns in Java", Mark Grand.
 * 
 * Date: Aug 8, 2011
 * 
 * @author moleksyuk
 */
public class DocChar extends DocumentElement {

	private char character;

	DocChar(char c) {
		this.character = c;
	}

	public char getChar() {
		return character;
	}

	@Override
	public int hashCode() {
		return getChar();
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof DocChar && ((DocChar) o).getChar() == getChar());
	}
}
