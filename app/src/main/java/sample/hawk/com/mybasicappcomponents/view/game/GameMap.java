package sample.hawk.com.mybasicappcomponents.view.game;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static sample.hawk.com.mybasicappcomponents.graphic.utils.DrawableUtils.drawableToBitmap;

/**
 * Created by ha271 on 2017/9/28.
 */

public class GameMap {

    private static int mScreenWidth;
    private static int mScreenHeight;
    private static int mScreenWidthTile;
    private static int mScreenHeightTile;
    private final int MATERIAL_TYPES = 5;
    private final int ROW_COUNT = 20;
    private final int COL_COUNT = 10;
    private Drawable[] drawableTypes;

    public enum Tile {
        Water("~"), Sand("."), Plain("\""), Forest("#"), Hill("#");

        public String tile;

        Tile(String tile) {
            this.tile = tile;
        }

    }

    public GameMap(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        mScreenHeight = displayMetrics.heightPixels;
        mScreenWidth  = displayMetrics.widthPixels;
        mScreenHeightTile = displayMetrics.heightPixels / ROW_COUNT;
        mScreenWidthTile  = displayMetrics.widthPixels  / COL_COUNT;

        drawableTypes = new Drawable[MATERIAL_TYPES];
        drawableTypes[0] = new ColorDrawable(Color.BLUE); // Water
        drawableTypes[1] = new ColorDrawable(Color.YELLOW); // Sand
        drawableTypes[2] = new ColorDrawable(Color.RED); // Plain
        drawableTypes[3] = new ColorDrawable(Color.GREEN); // Forest
        drawableTypes[4] = new ColorDrawable(Color.CYAN); // Hill

    }

    Map<Tile, List<Tile>> constrains = new HashMap<>();
    {
        constrains.put(Tile.Water,
                Arrays.asList(Tile.Water, Tile.Sand, Tile.Plain));
        constrains.put(Tile.Sand,
                Arrays.asList(Tile.Water, Tile.Sand, Tile.Plain));
        constrains.put(Tile.Plain,
                Arrays.asList(Tile.Water, Tile.Sand, Tile.Plain, Tile.Forest));
        constrains.put(Tile.Forest, Arrays.asList(Tile.Hill, Tile.Plain));
        constrains.put(Tile.Hill, Arrays.asList(Tile.Hill, Tile.Forest));
    }


    public Tile[][] generate(int x, int y) {

        Random rand = new Random();
        Tile[][] map = new Tile[x][y];

        for (int i = 0; i < x; i++) {
            map[i][0] = Tile.Water;
            map[i][y - 1] = Tile.Water;
        }

        for (int i = 0; i < y; i++) {
            map[0][i] = Tile.Water;
            map[x - 1][i] = Tile.Water;
        }

        for (int i = 1; i < x - 1; i++) {
            for (int j = 1; j < y - 1; j++) {
                List<Tile> con1 = constrains.get(map[i][j-1]);
                List<Tile> con2 = constrains.get(map[i-1][j]);
                List<Tile> con = new ArrayList<Tile>();
                for (Tile tile : con1){
                    if (con2.contains(tile)){
                        con.add(tile);
                    }
                }
                if (con.isEmpty()){
                    con.add(Tile.Plain);
                }

                map[i][j] = con.get(rand.nextInt(con.size()));

            }
        }

        return map;
    }

    public Tile[][] createRandomMap(GameMap p) {
        Tile[][] map = p.generate(ROW_COUNT, COL_COUNT);
        // output to Console.
        System.out.print("\n");
        for (Tile[] line : map) {
            for (Tile tile : line) {
                System.out.print(tile.tile);
            }
            System.out.print("\n");
        }
        return map;
    }

    public Drawable convertMapToDrawable(Tile[][] map){
        Drawable[][] tileDrawable = new Drawable[ROW_COUNT][COL_COUNT];
        // TODO: implement map to Drawable.
        Bitmap   tileBitmap = Bitmap.createBitmap(mScreenWidthTile, mScreenHeightTile, Bitmap.Config.ARGB_8888);
        Bitmap combineBitmap = Bitmap.createBitmap(mScreenWidth, mScreenHeight, Bitmap.Config.ARGB_8888);
        Canvas combineCanvas = new Canvas(combineBitmap);

        for (int i=0; i < COL_COUNT; i++) {
            for (int j=0; j < COL_COUNT; j++) {
                tileDrawable[i][j] = tileToDrawable(map[i][j]);
                tileBitmap = drawableToBitmap(tileDrawable[i][j]);
                combineCanvas.drawBitmap(tileBitmap, mScreenWidthTile*i, mScreenHeightTile*j, null);
            }
        }
        return new BitmapDrawable(combineBitmap);
    }

    public Bitmap createMapBitmap(Tile[][] map){
        Drawable[][] tileDrawable = new Drawable[ROW_COUNT][COL_COUNT];
        // TODO: implement map to Drawable.
        Bitmap   tileBitmap = Bitmap.createBitmap(mScreenWidthTile, mScreenHeightTile, Bitmap.Config.ARGB_8888);
        Bitmap combineBitmap = Bitmap.createBitmap(mScreenWidth, mScreenHeight, Bitmap.Config.ARGB_8888);
        Canvas combineCanvas = new Canvas(combineBitmap);

        for (int i=0; i < COL_COUNT; i++) {
            for (int j=0; j < COL_COUNT; j++) {
                tileDrawable[i][j] = tileToDrawable(map[i][j]);
                tileBitmap = drawableToBitmap(tileDrawable[i][j]);
                combineCanvas.drawBitmap(tileBitmap, mScreenWidthTile*i, mScreenHeightTile*j, null);
            }
        }
        return combineBitmap;
    }

    private Drawable tileToDrawable(Tile tile){
        Drawable tileDrawable = null;
        switch (tile) {
            case Water:
                tileDrawable = drawableTypes[0];
                break;
            case Sand:
                tileDrawable = drawableTypes[1];
                break;
            case Plain:
                tileDrawable = drawableTypes[2];
                break;
            case Forest:
                tileDrawable = drawableTypes[3];
                break;
            case Hill:
                tileDrawable = drawableTypes[4];
                break;
            default:
        }
        return tileDrawable;
    }


}