/*******************************************************************************
 * Copyright (c) 2011, 2013  - Daniel, Aguil Mallea.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v3.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 * 
 * Contributors:
 *     Daniel, Aguil Mallea - initial API and implementation
 ******************************************************************************/
package edu.davinci.lenguaje;

@SuppressWarnings("all")
public class SimpleNode extends CustomNode implements Node {
	protected Node parent;
	protected Node[] children;
	protected int id;
	protected Object value;
	protected Parser parser;
	protected Token firstToken;
	protected Token lastToken;

	public SimpleNode(int i) {
		id = i;
	}

	public SimpleNode(Parser p, int i) {
		this(i);
		parser = p;
	}

	public void dump(String prefix) {
		System.out.println(toString(prefix));
		if (children != null) {
			for (int i = 0; i < children.length; ++i) {
				SimpleNode n = (SimpleNode) children[i];
				if (n != null) {
					n.dump(prefix + " ");
				}
			}
		}
	}

	public Node[] getChildren() {
		return children;
	}

	public void jjtAddChild(Node n, int i) {
		if (children == null) {
			children = new Node[i + 1];
		} else if (i >= children.length) {
			Node c[] = new Node[i + 1];
			System.arraycopy(children, 0, c, 0, children.length);
			children = c;
		}
		children[i] = n;
	}

	public void jjtClose() {
	}

	public Node jjtGetChild(int i) {
		return children[i];
	}

	public Token jjtGetFirstToken() {
		return firstToken;
	}

	public Token jjtGetLastToken() {
		return lastToken;
	}

	public int jjtGetNumChildren() {
		return (children == null) ? 0 : children.length;
	}

	public Node jjtGetParent() {
		return parent;
	}

	public Object jjtGetValue() {
		return value;
	}

	public void jjtOpen() {
	}

	public void jjtSetFirstToken(Token token) {
		firstToken = token;
	}

	public void jjtSetLastToken(Token token) {
		lastToken = token;
	}

	/*
	 * You can override these two methods in subclasses of SimpleNode to customize the way the node appears when the tree is dumped. If your output uses more than one line you
	 * should override toString(String), otherwise overriding toString() is probably all you need to do.
	 */

	public void jjtSetParent(Node n) {
		parent = n;
	}

	public void jjtSetValue(Object value) {
		this.value = value;
	}

	/*
	 * Override this method if you want to customize how the node dumps out its children.
	 */

	public String toString() {
		return ParserTreeConstants.jjtNodeName[id];
	}

	public String toString(String prefix) {
		return prefix + toString();
	}

}
