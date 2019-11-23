package com.example.wlf.jumper.elements;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.example.wlf.jumper.R;
import com.example.wlf.jumper.graphics.Cores;
import com.example.wlf.jumper.graphics.Tela;

public class Pipe {

    private final Paint VERDE = Cores.getCorDoCano();
    private static final int TAMANHO_DO_CANO = 100;
    private static final int LARGURA_DO_CANO = 100;

   // private  final Bitmap canoInferior;
   // private  final Bitmap canoSuperior;
    private  Bitmap canoInferior;
    private  Bitmap canoSuperior;
    private Tela tela;
    private Passaro passaro;
    private int alturaDoCanoInferior;
    private int alturaDoCanoSuperior;
    private int posicao;
    private int passpipe;
    private int level;
    private final int dpipespeed=30;
    private final int defaultsize=50;
    private int pretoppipesize;
    private int prebottompipesize;
    private Context context;


    public Pipe(Tela tela, int posicao,int passpipe, Context context )
    {
        this.tela = tela;
        this.posicao = posicao;
        this.context = context;
        this.passpipe=passpipe;
        this.prebottompipesize=0;
        this.pretoppipesize=0;

        this.level=(this.passpipe/5);

        valorAleatorio();

        Bitmap bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cano );
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false); //파일 이름, 넓이,높이.이미지선명성(사용할경우 out of memory발생가능)
        canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }


    private void valorAleatorio()
    {

        ///if(this.passpipe>0&&this.passpipe%5==0){
         //   this.level+=(2*(this.passpipe/5));
        //}
        int gap=(10-level)*150;
        int range =(int)(Math.random()*(tela.getAltura()-gap-TAMANHO_DO_CANO*2))+TAMANHO_DO_CANO;

        int topspot=range;
        int bottomspot=tela.getAltura()-(range+gap);

        this.alturaDoCanoInferior = bottomspot;
        this.alturaDoCanoSuperior = topspot; //길이


    }

    public void desenhaNo( Canvas canvas )
    {
        desenhaCanoInferiorNo(canvas);
        desenhaCanoSuperiorNo(canvas);
    }

    private void desenhaCanoSuperiorNo( Canvas canvas )
    {
       // canvas.drawRect(posicao, 0,  posicao + LARGURA_DO_CANO,alturaDoCanoSuperior, VERDE);
        int width=tela.getLargura();
        if(posicao>=(width-(width/5))){
            canvas.drawBitmap( canoSuperior, posicao, 0-alturaDoCanoSuperior+defaultsize, null );
        }else{
            int preheight=-alturaDoCanoSuperior+defaultsize;
            if(preheight+this.pretoppipesize<0){
                this.pretoppipesize+=((this.alturaDoCanoSuperior-defaultsize)/20);
                canvas.drawBitmap( canoSuperior, posicao, preheight+pretoppipesize, null );
            }else{
                //this.pretoppipesize=this.alturaDoCanoSuperior;
                canvas.drawBitmap( canoSuperior, posicao, 0, null );
            }


        }


        //canvas.drawBitmap( canoSuperior, posicao, 0, null );

    }

    private void desenhaCanoInferiorNo( Canvas canvas )
    {
        //canvas.drawRect(posicao, alturaDoCanoInferior,posicao + LARGURA_DO_CANO, tela.getAltura(), VERDE );
        int width=tela.getLargura();
        if(posicao>=(width-(width/5))){
            canvas.drawBitmap( canoInferior, posicao,tela.getAltura()-defaultsize, null );
        }else{
            int preheight=tela.getAltura()-defaultsize;
            if(preheight-this.prebottompipesize>tela.getAltura()-this.alturaDoCanoInferior){
                this.prebottompipesize+=((this.alturaDoCanoInferior-defaultsize)/20);
                canvas.drawBitmap( canoInferior, posicao,preheight-prebottompipesize , null );
            }else{
                //this.pretoppipesize=this.alturaDoCanoSuperior;
                canvas.drawBitmap(canoInferior, posicao,tela.getAltura()- alturaDoCanoInferior, null);
            }

        }

      //  canvas.drawBitmap(canoInferior, posicao, alturaDoCanoInferior, null);
    }

    public void move()
    {
        posicao -=10;
    }

    public boolean saiuDaTela()
    {
        return posicao + LARGURA_DO_CANO < 0;
    }

    public int getPosicao()
    {
        return posicao;
    }

    public boolean temColisaoVerticalCom( Passaro passaro )
    {
        return false;// passaro.getAltura() - passaro.RAIO < this.alturaDoCanoSuperior ||
                //passaro.getAltura() + passaro.RAIO > this.alturaDoCanoInferior;
    }

    public boolean temColisaoHorizontalCom( Passaro passaro )
    {
        return false;//this.posicao - passaro.X < passaro.RAIO;
    }
}
