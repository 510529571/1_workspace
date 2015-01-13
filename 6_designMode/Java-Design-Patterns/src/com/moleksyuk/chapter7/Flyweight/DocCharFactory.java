package com.moleksyuk.chapter7.Flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Based on: "Patterns in Java", Mark Grand.
 * 
 * Date: Aug 8, 2011
 * 
 * @author moleksyuk
 */
public class DocCharFactory {

	private MutableDocChar myChar = new MutableDocChar();
	private Map<DocChar, DocChar> docCharPool = new HashMap<DocChar, DocChar>();

	synchronized DocChar getDocChar(char c) {
		myChar.setChar(c);
		DocChar thisChar = (DocChar) docCharPool.get(myChar);
		if (thisChar == null) {
			thisChar = new DocChar(c);
			docCharPool.put(thisChar, thisChar);
		}
		return thisChar;
	}

	private static class MutableDocChar extends DocChar {
		private char character;

		MutableDocChar() {
			super('\u0000');
		}

		@Override
		public char getChar() {
			return character;
		}

		public void setChar(char c) {
			character = c;
		}
	}
}
