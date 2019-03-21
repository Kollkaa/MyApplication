package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {
int scores=0;
    private GameThread gameThread;

    private final List<ChibiCharacter> chibiList = new ArrayList<>();
    private final List<Tree> treesList = new ArrayList<>();
    private Castle castle;
    private Shop shop;
    private final List<Explosion> explosionList = new ArrayList<>();

    private static final int MAX_STREAMS=100;
    private int soundIdExplosion;
    private int soundIdBackground;

    private boolean soundPoolLoaded;
    private SoundPool soundPool;

    public List<ChibiCharacter> getChibiList() {
        return chibiList;
    }

    public GameSurface(Context context)  {
        super(context);


        // Make Game Surface focusable so it can handle events.
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        this.initSoundPool();
    }

    private void initSoundPool()  {
        // With Android API >= 21.
        if (Build.VERSION.SDK_INT >= 21 ) {

            AudioAttributes audioAttrib = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            SoundPool.Builder builder= new SoundPool.Builder();
            builder.setAudioAttributes(audioAttrib).setMaxStreams(MAX_STREAMS);

            this.soundPool = builder.build();
        }
        // With Android API < 21
        else {
            // SoundPool(int maxStreams, int streamType, int srcQuality)
            this.soundPool = new SoundPool(MAX_STREAMS, AudioManager.STREAM_MUSIC, 0);
        }

        // When SoundPool load complete.
        this.soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPoolLoaded = true;

                // Playing background sound.
                playSoundBackground();
            }
        });

        // Load the sound background.mp3 into SoundPool
        this.soundIdBackground= this.soundPool.load(this.getContext(), R.raw.background,1);

        // Load the sound explosion.wav into SoundPool
        this.soundIdExplosion = this.soundPool.load(this.getContext(), R.raw.background,1);


    }

    public void playSoundExplosion()  {
        if(this.soundPoolLoaded) {
            float leftVolumn = 0.8f;
            float rightVolumn =  0.8f;
            // Play sound explosion.wav
            int streamId = this.soundPool.play(this.soundIdExplosion,leftVolumn, rightVolumn, 1, 0, 1f);
        }
    }

    public void playSoundBackground()  {
        if(this.soundPoolLoaded) {
            float leftVolumn = 0.8f;
            float rightVolumn =  0.8f;
            // Play sound background.mp3
            int streamId = this.soundPool.play(this.soundIdBackground,leftVolumn, rightVolumn, 1, -1, 1f);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d("position",String.valueOf("x: "+event.getX()+" y: "+event.getY()));
            int x=  (int)event.getX();
            int y = (int)event.getY();

            Iterator<ChibiCharacter> iterator= this.chibiList.iterator();
            if( castle.getX() < x && x < castle.getX() + castle.getWidth()
                    && castle.getY() < y && y < castle.getY()+ castle.getHeight())
            {
                //TODO
            }
            if ( shop.getX() < x && x < shop.getX() + shop.getWidth()
                        && shop.getY() < y && y < shop.getY()+ shop.getHeight())
            {
                //TODO
            }

            while(iterator.hasNext()) {
                ChibiCharacter chibi = iterator.next();
                if( chibi.getX() < x && x < chibi.getX() + chibi.getWidth()
                        && chibi.getY() < y && y < chibi.getY()+ chibi.getHeight())  {
                    // Remove the current element from the iterator and the list.
                    iterator.remove();

                    // Create Explosion object.
                    Bitmap bitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.explosinion);
                    Explosion explosion = new Explosion(this, bitmap,chibi.getX(),chibi.getY());

                    this.explosionList.add(explosion);
                }
            }


            for(ChibiCharacter chibi: chibiList) {
                int movingVectorX =x-  chibi.getX() ;
                int movingVectorY =y-  chibi.getY() ;
                chibi.setMovingVector(movingVectorX, movingVectorY);
            }
            return true;
        }
        return false;
    }

    public void update()  {

        for(ChibiCharacter chibi: chibiList) {
            chibi.update();

        }
        for(Explosion explosion: this.explosionList)  {
            explosion.update();
        }

        Iterator<Explosion> iterator= this.explosionList.iterator();
        while(iterator.hasNext())  {
            Explosion explosion = iterator.next();


            if(explosion.isFinish()) {
                scores++;
                Log.d("scores",""+scores);

                // If explosion finish, Remove the current element from the iterator & list.
                iterator.remove();

            }
        }
    }

    @Override
    public void draw(Canvas canvas)  {
        super.draw(canvas);
        try {
            Paint paint = new Paint();
            paint.setColor(Color.RED);
            paint.setTextSize(100);
            canvas.drawText(String.valueOf(scores), 1700, 70, paint);
        }
        catch (Exception e) {
            Log.e("123", "run() lockCanvas()", e);
        } finally {

        }

        castle.draw(canvas);
        shop.draw(canvas);
        for(ChibiCharacter chibi: chibiList) {
            chibi.draw(canvas);
        }
        for(Tree tree: treesList) {
            tree.draw(canvas);
        }
        for(Explosion explosion: this.explosionList)  {
            explosion.draw(canvas);
        }




    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
       try {
           Bitmap castleBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.castle);

           castle = new Castle(this, castleBitmap, 20, 200);
           Bitmap shopBitmap = BitmapFactory.decodeResource(this.getResources(), R.drawable.shop);
           shop = new Shop(this, castleBitmap, 1600, 150);
       }
       catch (Exception e)
       {}
       try {
           for(int i=0;i<15;i++)
           {
               Random ran=new Random();

               Bitmap  tree = BitmapFactory.decodeResource(this.getResources(),R.drawable.tree);
               Tree tree1 = new Tree(this,tree,ran.nextInt(1600),ran.nextInt(900));
               treesList.add(tree1);
           }
       }
       catch (Exception e)
       {}
        for(int i=0;i<6;i++)
       {
           Bitmap  chibiBitmap = BitmapFactory.decodeResource(this.getResources(),R.drawable.chibi1);
           ChibiCharacter chibi = new ChibiCharacter(this,chibiBitmap,100+i*10,+i*5);
this.chibiList.add(chibi);

       }

        Bitmap chibiBitmap2 = BitmapFactory.decodeResource(this.getResources(),R.drawable.chibi2);
        ChibiCharacter chibi2 = new ChibiCharacter(this,chibiBitmap2,300,150);


        this.chibiList.add(chibi2);


        this.gameThread = new GameThread(this,holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry= true;
        while(retry) {
            try {
                this.gameThread.setRunning(false);

                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            }catch(InterruptedException e)  {
                e.printStackTrace();
            }
            retry= true;
        }
    }

}