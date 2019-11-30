package com.example.wlf.jumper.elements;

import android.content.Context;
import android.graphics.*;
import com.example.wlf.jumper.R;
import android.graphics.Bitmap;
import com.example.wlf.jumper.graphics.Tela;

public class back_ground {
    static final int Scroll_Speed=-10;
    private float x_scroll=0;
    private int x=0,y=0;

    public Bitmap BG;

    public back_ground(Tela tela,Context context){
         Bitmap bg =BitmapFactory.decodeResource(context.getResources(),R.drawable.back);
         this.BG = Bitmap.createScaledBitmap( bg, bg.getWidth(), tela.getAltura(), false );
    }

    public void drawbg( Canvas canvas )
    {
        //canvas.drawCircle(X, getAltura(), RAIO, vermelho);
        canvas.drawBitmap( this.BG,x, y, null );
    }
    public void update(){
        this.x=this.x+Scroll_Speed;

    }


}
