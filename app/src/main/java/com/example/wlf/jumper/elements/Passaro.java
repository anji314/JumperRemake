package com.example.wlf.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import com.example.wlf.jumper.R;
import com.example.wlf.jumper.engine.Som;
import com.example.wlf.jumper.graphics.Tela;

public class Passaro {

    public static final int X = 100;
    public static final  int RAIO = 80;
    private final int J=-35;
    private Tela tela;
    private Bitmap passaro;
    private Som som;
    private int gravity=2;
    private int vy=J;

    private int altura;
    private int xspot;

    public Passaro( Tela tela, Context context )
    {
        this.tela = tela;
        this.setAltura( tela.getAltura()/2 );
        this.setXspot(80);

        Bitmap bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.passaro );

        passaro = bp.createScaledBitmap( bp, RAIO*2, RAIO*2, false );
        som = new Som(context);
    }

    public void desenhaNo( Canvas canvas )
    {
        //canvas.drawCircle(X, getAltura(), RAIO, vermelho);
        canvas.drawBitmap( passaro, xspot - RAIO, altura - RAIO, null );
    }
    public void xmove(){
        boolean checkcenter=getxspot()+RAIO<=(tela.getLargura()/2);
        if(checkcenter){
            setXspot(getxspot()+3);
        }
    }

    public void cai()
    {
        boolean checouNoChao = getAltura() + RAIO > tela.getAltura();

        if ( ! checouNoChao )
        {
            this.vy+=this.gravity;
            setAltura(getAltura() + this.vy);

        }
    }
    public void cai2()
    {
        boolean checouNoChao = getAltura() + RAIO > tela.getAltura();

        if ( ! checouNoChao )
        {
            setAltura(getAltura() + 15);

        }
    }

    public void  pula()
    {
        if(getAltura() > RAIO) {
            this.vy=J;
            cai();
            //setAltura(getAltura() - 150);
            som.tocaSom(Som.PULO);
        }
    }

    public int getAltura() {
        return altura;
    }
    public int getxspot(){return xspot;}

    public void setAltura( int altura ) {
        this.altura = altura;
    }
    public void setXspot(int xspot){
        this.xspot=xspot;
    }
}
