package main;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;

import events.OnItemListener;
import processing.core.PApplet;
import processing.core.PImage;

public class Main extends PApplet implements OnItemListener{

	private UDPConnection udp;
	private ArrayList<Pintar> pedido;
	private int posX, posY, numPedido;
	private Pintar itemPintar;
	private PImage jugo, sandwich, yogurt, hotdog;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PApplet.main("main.Main");
	}

	public void settings() {
		size(400,800);
	}
	
	public void setup() {
		
		posX=50;
		posY=50;
		numPedido=0;
		pedido = new ArrayList<Pintar>();
		udp = new UDPConnection();
		udp.setObserver(this);
		udp.start();
		
		jugo = loadImage("../resources/jugo.png");
		sandwich = loadImage("../resources/sandwich.png");
		yogurt = loadImage("../resources/yogurt.png");
		hotdog = loadImage("../resources/hotdog.png");
	}
	
	public void draw() {
		
		background(255);
		
		
		
		for (int i = 0; i < pedido.size(); i++) {
			
			pedido.get(i).pintarPedido(posX, posY+(120*i));
			
		}
	}
	
	public void mousePressed() {
		
		for (int i = 0; i < pedido.size(); i++) {
			
			if (mouseX>posX && mouseX<posX+300 && mouseY>posY+(120*i) && mouseY<posY+(120*i)+100) {
				udp.sendMessage("Su "+ pedido.get(i).getItem()+ " ya fue despachado");
				numPedido=numPedido-1;
				System.out.println(numPedido);
				pedido.remove(i);
			}
			
		}
		
	}

	public void onItemReceived(String item) {
		
		if (numPedido<6) {
			
			numPedido++;
			System.out.println(numPedido);
			int numero = numPedido;


			Calendar c= Calendar.getInstance();
			Date tiempo = c.getTime();
			
			switch (item) {
			case "JUGO":
				pedido.add(new Pintar(item, numero, tiempo, jugo, this));
				break;
				
			case "SANDWICH":
				pedido.add(new Pintar(item, numero, tiempo, sandwich, this));
				break;
			
			case "YOGURT":
				pedido.add(new Pintar(item, numero, tiempo, yogurt, this));
				break;
				
			case "HOTDOG":
				pedido.add(new Pintar(item, numero, tiempo, hotdog, this));
				break;

			default:
				break;
			}
		
		}else {
			System.out.println("Demasiados pedidos en cola");
		}
		
	}
	
}
