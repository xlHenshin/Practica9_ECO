package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import processing.core.PApplet;
import processing.core.PImage;

public class Pintar {
	
	private String item;
	private int numPedido;
	private PApplet app;
	private String tiempoactual;
	private PImage imagen;
	
	Calendar c= Calendar.getInstance();
	Date tiempo = c.getTime();
	
	
	public Pintar(String item, int numPedido, Date tiempo, PImage imagen, PApplet app) {
		
		this.item=item;
		this.tiempo=tiempo;
		this.numPedido=numPedido;
		this.app=app;
		this.imagen=imagen;
		
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		tiempoactual = sdf.format(tiempo);
	}
	
	public void pintarPedido(int posX, int posY) {
		
		app.fill(255);
		app.rect(posX, posY, 300, 100);
		app.image(imagen, posX, posY);
		app.fill(0);
		app.text("Pedido #"+numPedido, posX+130, posY+30);
		app.text("Hora:"+tiempoactual, posX+130, posY+60);
		
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}
	
	

}
