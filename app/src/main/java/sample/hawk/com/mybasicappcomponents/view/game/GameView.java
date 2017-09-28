package sample.hawk.com.mybasicappcomponents.view.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import sample.hawk.com.mybasicappcomponents.R;
import sample.hawk.com.mybasicappcomponents.utils.SMLog;

public class GameView extends SurfaceView {

    private Bitmap bmp;
    private Bitmap bmpBlood;
    private SurfaceHolder holder;
    private GameLoopThread gameLoopThread;
    private List<LiveSprite> sprites = new CopyOnWriteArrayList<LiveSprite>();
    private List<DeadSprite> temps = new CopyOnWriteArrayList<DeadSprite>();
    GameMap mGameMap;
    Drawable mBackground;
    private Context mContext;

    public GameView(Context context) {
        super(context);
        mContext = context;
        holder = getHolder();
        gameLoopThread = new GameLoopThread(this);
        holder.addCallback(new Callback() {

            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
                SMLog.i("surface Changed");
            }

            public void surfaceCreated(SurfaceHolder holder) {
                SMLog.i("surface Created");
                mGameMap = new GameMap(mContext); // create a ASCII map for output System.out.print console window.
                GameMap.Tile[][] mapTile = mGameMap.createRandomMap(mGameMap);
                mBackground = mGameMap.convertMapToDrawable(mapTile);
                createSprites(10);
                gameLoopThread.setRunning(true);
                gameLoopThread.start();
            }

            public void surfaceDestroyed(SurfaceHolder holder) {
                SMLog.i("surface Destroyed");
                gameLoopThread.setRunning(false);
            }
        });
        // bmp = BitmapFactory.decodeResource(getResources(), R.drawable.sprites); // Hawk: unused
        bmpBlood = BitmapFactory.decodeResource(getResources(), R.drawable.blood1);
    }

    private LiveSprite createSprite(int resouce) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), resouce);
        return new LiveSprite(this, bmp);
    }

    private void createSprites(int max) {
        for (int i = 0; i < max; ++i) {
            sprites.add(createSprite(R.drawable.sprites));
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        for (int i = sprites.size() - 1; i >= 0; i--) {
            LiveSprite sprite = sprites.get(i);
            if (sprite.isCollision(x, y)) {
                sprites.remove(sprite);
                temps.add(new DeadSprite(temps, this, x, y, bmpBlood));
                createSprites(1);
                break;
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);
        // mBackground.draw(canvas);
        // canvas.drawBitmap(mBackground, 0, 0, null);
        for (int i = temps.size() - 1; i >= 0; i--) {
            temps.get(i).onDraw(canvas);
        }
        for (LiveSprite sprite : sprites) {
            sprite.onDraw(canvas);
        }
    }

}