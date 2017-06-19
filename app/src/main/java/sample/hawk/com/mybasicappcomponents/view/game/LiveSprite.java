package sample.hawk.com.mybasicappcomponents.view.game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class LiveSprite {
    private int x;
    private int y;
    private int xSpeed = 5;
    private int ySpeed = 5;
    private GameView gameView;
    private Bitmap bmp;
    private int width;
    private int height;
    private int currentFrame;
    private static final int MAX_SPEED = 5;

    private static final int BMP_ROWS = 4;
    private static final int BMP_COLUMNS = 3;
    // sprites.png(W96*H128) for (3column*4row=12 icons) = 32*32 pixel/icon
    // row icon group 1/2/3/4 for animation1/2/3/4
    public LiveSprite(GameView gameView, Bitmap bmp) {
        this.gameView = gameView;
        this.bmp = bmp;
        this.width = bmp.getWidth() / BMP_COLUMNS;
        this.height = bmp.getHeight() / BMP_ROWS;

        Random rnd = new Random();
        x = rnd.nextInt(gameView.getWidth() - width);
        y = rnd.nextInt(gameView.getHeight() - height);
        xSpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
        ySpeed = rnd.nextInt(MAX_SPEED * 2) - MAX_SPEED;
    }

    private void update() {
        if (x > gameView.getWidth() - width - xSpeed || x + xSpeed < 0) {
            xSpeed = -xSpeed;
        }
        x = x + xSpeed;
        if (y > gameView.getHeight() - height - ySpeed || y + ySpeed < 0) {
            ySpeed = -ySpeed;
        }
        y = y + ySpeed;
        currentFrame = ++currentFrame % BMP_COLUMNS;
    }

    public void onDraw(Canvas canvas) {
        update();
        int srcX = currentFrame * width;
        int srcY = getAnimationRow() * height;
        Rect src = new Rect(srcX, srcY, srcX + width, srcY + height);
        Rect dst = new Rect(x, y, x + width, y + height);
        canvas.drawBitmap(bmp, src, dst, null);
    }

    public boolean isCollision(float x2, float y2) {
        return x2 > x && x2 < x + width && y2 > y && y2 < y + height;
    }

    // return 1, 2, 3 or 4
    private int getAnimationRow() {
        if (xSpeed > 0 && ySpeed > 0) {
            return 2;
        } else if (xSpeed > 0 && ySpeed < 0) {
            return 3;
        } else if (xSpeed < 0 && ySpeed > 0) {
            return 0;
        } else if (xSpeed < 0 && ySpeed < 0) {
            return 1;
        }
        return 0;
    }
}