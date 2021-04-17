package main;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import events.OnItemListener; 

public class UDPConnection extends Thread{

	private DatagramSocket socket;
	private Pedido pedido;
	private OnItemListener observer;
	
	public void setObserver(OnItemListener observer) {
		this.observer=observer;
	}
	
	public void run() {
		
		try {
			socket = new DatagramSocket(5000);
			
			while(true) {
				
				byte[] buffer = new byte[100];
				DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
				System.out.println("Esperando datagrama...");
				socket.receive(packet);
				
				String mensaje = new String(packet.getData()).trim();
				
				Gson gson = new Gson();
				Generic generic = gson.fromJson(mensaje, Generic.class);
				
				System.out.println("Datagrama recibido: " + generic.item);
				
				switch (generic.item) {
				case "JUGO":
					
					pedido = gson.fromJson(mensaje, Pedido.class);
					System.out.println("El pedido es: " + pedido.getItem());
					observer.onItemReceived(pedido.getItem());
					break;
				
				case "SANDWICH":
					
					pedido = gson.fromJson(mensaje, Pedido.class);
					System.out.println("El pedido es: " + pedido.getItem());
					observer.onItemReceived(pedido.getItem());
					break;
				
				case "YOGURT":
					
					pedido = gson.fromJson(mensaje, Pedido.class);
					System.out.println("El pedido es: " + pedido.getItem());
					observer.onItemReceived(pedido.getItem());
					break;
					
				case "HOTDOG":
					
					pedido = gson.fromJson(mensaje, Pedido.class);
					System.out.println("El pedido es: " + pedido.getItem());
					observer.onItemReceived(pedido.getItem());
					break;
				}
				
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String mensaje) {
		
		new Thread(
				
				()->{
					
					try {
						
						InetAddress ip = InetAddress.getByName("192.168.1.2");
						DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip, 6000);
						socket.send(packet);
						
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				).start();
		
	}
}
