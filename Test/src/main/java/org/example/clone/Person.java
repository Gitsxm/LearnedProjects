package org.example.clone;

import java.io.Serializable;

public class Person implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String name = new String("kangkang");

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

}