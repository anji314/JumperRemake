package com.example.wlf.jumper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.wlf.jumper.engine.Game;

public class MainActivity extends AppCompatActivity {

    Game game;
    Button retry;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FrameLayout container = (FrameLayout) findViewById(R.id.container);

        game = new Game(this, mainHandler);
        //container.addView(game);

       // startbg=(ImageView) findViewById(R.id.startbg);
        retry = findViewById(R.id.retry);
        start = findViewById(R.id.start);

        retry.setVisibility(View.GONE);
        //startbg.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);

        final Thread thread = new Thread(game);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
		        container.addView(game);
                thread.start();
                start.setVisibility(View.GONE);
            }
        });
    }

    Handler mainHandler =  new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 0){
              //  Bitmap bg = BitmapFactory.decodeResource(getResources(),R.drawable.startbackground);
               // Bitmap BG = Bitmap.createScaledBitmap( bg, bg.getWidth(), bg.getHeight(), false );

                retry.setVisibility(View.VISIBLE);
                //startbg.setVisibility(View.VISIBLE);
                retry.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        game.resetGame();
                        new Thread(game).start();
                        retry.setVisibility(View.GONE);
                    }
                });
            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        game.inicia();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.cancela();
    }
}
