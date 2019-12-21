package com.example.wlf.jumper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.content.DialogInterface;
import com.example.wlf.jumper.engine.Game;

public class MainActivity extends AppCompatActivity {

    Game game;
    Button retry;
    Button start;
    Button exit;

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
        exit =findViewById(R.id.exit_b);

        retry.setVisibility(View.GONE);
        //startbg.setVisibility(View.VISIBLE);
        start.setVisibility(View.VISIBLE);
        exit.setVisibility(View.VISIBLE);

        final Thread thread = new Thread(game);
        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
		        container.addView(game);
                thread.start();
                start.setVisibility(View.GONE);
                exit.setVisibility(View.GONE);
            }

        });
        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("정말로 종료하시겠습니까?");
                builder.setTitle("종료 알림창")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert=builder.create();
                alert.setTitle("종료 알림창");
                alert.show();


            }
        });


    }

    Handler mainHandler =  new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 0){
              //  Bitmap bg = BitmapFactory.decodeResource(getResources(),R.drawable.startbackground);
               // Bitmap BG = Bitmap.createScaledBitmap( bg, bg.getWidth(), bg.getHeight(), false );

                retry.setVisibility(View.VISIBLE);
                exit.setVisibility(View.VISIBLE);
                //startbg.setVisibility(View.VISIBLE);
                retry.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        game.resetGame();
                        new Thread(game).start();
                        retry.setVisibility(View.GONE);
                        exit.setVisibility(View.GONE);
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
