package com.moleksyuk.chapter7.Flyweight;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class DocumentContainer extends DocumentElement {

	private List<DocumentElement> children = Collections
			.synchronizedList(new ArrayList<DocumentElement>());
	private Font font;
	DocumentContainer parent;

	public DocumentElement getChild(int index) {
		return children.get(index);
	}

	public synchronized void addChild(DocumentElement child) {
		synchronized (child) {
			children.add(child);
			if (child instanceof DocumentContainer) {
				((DocumentContainer) child).parent = this;
			}
		}
	}

	public synchronized void removeChild(DocumentElement child) {
		synchronized (child) {
			if (child instanceof DocumentContainer
					&& this == ((DocumentContainer) child).parent) {
				((DocumentContainer) child).parent = null;
			}
			children.remove(child);
		}
	}

	public DocumentContainer getParent() {
		return parent;
	}

	public Font getFont() {
		if (font != null) {
			return font;
		} else if (parent != null) {
			return parent.getFont();
		} else {
			return null;
		}
	}

	public void setFont(Font font) {
		this.font = font;
	}
}
