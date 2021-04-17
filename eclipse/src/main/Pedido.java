package main;

import processing.core.PApplet;

public class Pedido {
	
	private String item;

    public Pedido(String item){
        this.item=item;
    }

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
}
