package com.example.practica9;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity implements OnMessageListener {

    private UDPConnection udp;
    private ImageView jugo, sandwich, yogurt, hotdog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugo=findViewById(R.id.jugo);
        sandwich=findViewById(R.id.sandwich);
        yogurt=findViewById(R.id.yogurt);
        hotdog=findViewById(R.id.hotdog);

        udp = new UDPConnection();
        udp.setObserver(this);
        udp.start();

        jugoClick();
        sandwichClick();
        yogurtClick();
        hotdogClick();
    }

    public void jugoClick(){

        Pedido pedido = new Pedido("JUGO");
        Gson gson = new Gson();

        jugo.setOnClickListener(

                v->{
                    Log.e(">>>", "Datagrama enviado");
                    String json = gson.toJson(pedido);
                    udp.sendMessage(json);
                }

        );

    }

    public void sandwichClick(){
        Pedido pedido = new Pedido("SANDWICH");
        Gson gson = new Gson();

        sandwich.setOnClickListener(

                v->{
                    Log.e(">>>", "Datagrama enviado");
                    String json = gson.toJson(pedido);
                    udp.sendMessage(json);
                }

        );
    }

    public void yogurtClick(){
        Pedido pedido = new Pedido("YOGURT");
        Gson gson = new Gson();

        yogurt.setOnClickListener(

                v->{
                    Log.e(">>>", "Datagrama enviado");
                    String json = gson.toJson(pedido);
                    udp.sendMessage(json);
                }

        );
    }

    public void hotdogClick(){
        Pedido pedido = new Pedido("HOTDOG");
        Gson gson = new Gson();

        hotdog.setOnClickListener(

                v->{
                    Log.e(">>>", "Datagrama enviado");
                    String json = gson.toJson(pedido);
                    udp.sendMessage(json);
                }

        );
    }

    @Override
    public void onMessage(String mensaje) {
        runOnUiThread(
                ()->{
                    Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
                }

        );
    }
}