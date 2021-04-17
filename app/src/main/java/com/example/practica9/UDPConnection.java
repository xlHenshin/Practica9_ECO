package com.example.practica9;

import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPConnection extends Thread{

    private DatagramSocket socket;
    private MainActivity main;
    private String mensaje;
    private OnMessageListener observer;

    public void setObserver(OnMessageListener observer) {
        this.observer=observer;
    }

    public void run(){

        try {
            socket = new DatagramSocket(6000);

            while(true) {

                byte[] buffer = new byte[100];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                Log.e(">>>", "Esperando datagrama...");
                socket.receive(packet);

                mensaje = new String(packet.getData()).trim();
                observer.onMessage(mensaje);
                Log.e(">>>", "Datagrama recibido: "+mensaje);

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

                        InetAddress ip = InetAddress.getByName("192.168.1.7");
                        DatagramPacket packet = new DatagramPacket(mensaje.getBytes(), mensaje.getBytes().length, ip, 5000);
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
