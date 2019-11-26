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
    private static final int TAMANHO_DO_CANO = 200;
    private static final int LARGURA_DO_CANO = 100;

   // private  final Bitmap canoInferior;
   // private  final Bitmap canoSuperior;
    private  Bitmap canoInferior;
    private  Bitmap canoSuperior;
    private Tela tela;
    private Passaro passaro;
    private int alturaDoCanoInferior;
    private int pretoplength;
    private int prebottomlength;
    private int alturaDoCanoSuperior;
    private int posicao;
    private int passpipe;
    private int level;
    private final int dpipespeed=30;
    private final int defaultsize=50;
    private int pretoppipesize;
    private int prebottompipesize;
    private int topclock;
    private int bottomclock;
    private int topspeed;
    private int bottomspeed;
    private Context context;


    public Pipe(Tela tela, int posicao,int passpipe, Context context )
    {
        int choicehurlde =(int)(Math.random()*2)+1;
        this.tela = tela;
        this.posicao = posicao;
        this.context = context;
        this.passpipe=passpipe;
        this.prebottompipesize=0;
        this.pretoppipesize=0;
        this.topclock=0;
        this.bottomclock=0;
        this.topspeed= (int)(Math.random()*7) +6;
        this.bottomspeed= (int)(Math.random()*7) +6;


        this.level=(this.passpipe/5)*2;
        Bitmap bp=null;

        valorAleatorio();
        this.pretoplength=defaultsize;
        this.prebottomlength=tela.getAltura()-defaultsize;
        if(choicehurlde==1){
            bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.hurdle1 );
        }else if(choicehurlde==2){
            bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.hurdle2 );
        }

      //  Bitmap bp = BitmapFactory.decodeResource( context.getResources(), R.drawable.cano );
        canoInferior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoInferior, false); //파일 이름, 넓이,높이.이미지선명성(사용할경우 out of memory발생가능)
        canoSuperior = Bitmap.createScaledBitmap(bp, LARGURA_DO_CANO, alturaDoCanoSuperior, false);
    }


    private void valorAleatorio()
    {


        int prelevel=(this.level%8);
        int gap=(10-prelevel)*150;
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
            this.pretoplength=defaultsize;
        }else{
            if(this.level<8) {
                int preheight = -alturaDoCanoSuperior + defaultsize;
                if (preheight + this.pretoppipesize < 0) {
                    this.pretoppipesize += ((this.alturaDoCanoSuperior - defaultsize) / 15);
                    this.pretoplength=defaultsize+ pretoppipesize;
                    canvas.drawBitmap(canoSuperior, posicao, preheight + pretoppipesize, null);
                } else {
                    //this.pretoppipesize=this.alturaDoCanoSuperior;
                    this.pretoplength=alturaDoCanoSuperior+defaultsize;
                    canvas.drawBitmap(canoSuperior, posicao, 0, null);
                }
            }else{
                if(this.topclock==1){
                    int preheight = -alturaDoCanoSuperior + defaultsize;
                    if (preheight + this.pretoppipesize < 0) {
                        this.pretoppipesize += ((this.alturaDoCanoSuperior - defaultsize) / (this.topspeed*5));
                        this.pretoplength=defaultsize+ pretoppipesize;
                        canvas.drawBitmap(canoSuperior, posicao, preheight + pretoppipesize, null);
                    } else {
                        //this.pretoppipesize=this.alturaDoCanoSuperior;
                        this.pretoplength=alturaDoCanoSuperior+defaultsize;
                        canvas.drawBitmap(canoSuperior, posicao, 0, null);
                        this.topclock=0;
                    }
                }else{
                    int preheight = -alturaDoCanoSuperior + defaultsize;
                    if((preheight + this.pretoppipesize) >(-alturaDoCanoSuperior + defaultsize)){
                        this.pretoppipesize -= ((this.alturaDoCanoSuperior - defaultsize) / (this.topspeed*5));
                        this.pretoplength=defaultsize+ pretoppipesize;
                        canvas.drawBitmap(canoSuperior, posicao, preheight + pretoppipesize, null);

                    }else{
                        this.pretoplength=defaultsize;
                        canvas.drawBitmap(canoSuperior, posicao, preheight, null);
                    this.topclock=1;
                    }
                }

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
            this.prebottomlength=tela.getAltura()-defaultsize;
        }else{
            if(this.level<8){
            int preheight=tela.getAltura()-defaultsize;
            if(preheight-this.prebottompipesize>tela.getAltura()-this.alturaDoCanoInferior){
                this.prebottompipesize+=((this.alturaDoCanoInferior-defaultsize)/15);
                this.prebottomlength=tela.getAltura()-defaultsize-this.prebottompipesize;
                canvas.drawBitmap( canoInferior, posicao,preheight-prebottompipesize , null );
            }else{

                //this.pretoppipesize=this.alturaDoCanoSuperior;
                this.prebottomlength=tela.getAltura()-alturaDoCanoInferior;
                canvas.drawBitmap(canoInferior, posicao,tela.getAltura()- alturaDoCanoInferior, null);
            }
            }else{
                if(this.bottomclock==1){
                    int preheight=tela.getAltura()-defaultsize;
                    if(preheight-this.prebottompipesize>tela.getAltura()-this.alturaDoCanoInferior){
                        this.prebottompipesize+=((this.alturaDoCanoInferior-defaultsize)/(this.bottomspeed*5));
                        this.prebottomlength=tela.getAltura()-defaultsize-this.prebottompipesize;
                        canvas.drawBitmap( canoInferior, posicao,preheight-prebottompipesize , null );
                    }else{
                        this.prebottomlength=tela.getAltura()-alturaDoCanoInferior;
                        canvas.drawBitmap(canoInferior, posicao,tela.getAltura()- alturaDoCanoInferior, null);
                        this.bottomclock=0;
                    }

                }else{
                    int preheight=tela.getAltura()-defaultsize;
                    if(preheight-this.prebottompipesize<tela.getAltura()-defaultsize){
                        this.prebottompipesize-=((this.alturaDoCanoInferior-defaultsize)/(this.bottomspeed*5));
                        this.prebottomlength=tela.getAltura()-this.prebottompipesize-defaultsize;
                        canvas.drawBitmap( canoInferior, posicao,preheight-prebottompipesize , null );
                    }else{
                        this.prebottomlength=tela.getAltura()-defaultsize;
                        canvas.drawBitmap( canoInferior, posicao,tela.getAltura()-defaultsize, null );
                        this.bottomclock=1;
                    }

                }
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
        if(passaro.getAltura() - passaro.RAIO < this.pretoplength ||
               passaro.getAltura() + passaro.RAIO > this.prebottomlength){
            return true;
        }else return false;

    }

    public boolean temColisaoHorizontalCom( Passaro passaro )
    {

        if( passaro.getxspot()-passaro.RAIO<=getPosicao()+100 &&
        passaro.getxspot()+passaro.RAIO>=getPosicao()){
            return true;
        }else return false;
        //return this.posicao - passaro.getxspot() < passaro.RAIO;
    }
}
