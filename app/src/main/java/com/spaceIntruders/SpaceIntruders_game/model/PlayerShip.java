package com.spaceIntruders.SpaceIntruders_game.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.shapes.Shape;

import com.spaceIntruders.basicAplication.R;

public class PlayerShip {

    RectF rect;

    // The player ship will be represented by a Bitmap
    private Bitmap bitmap;

    // Color Array for Ships
    Integer [] arrayWithShipShapes = {
            R.drawable.blueship, R.drawable.dirtywhiteship, R.drawable.greenship, R.drawable.lightblueship, R.drawable.lightpurpleship, R.drawable.orangeship, R.drawable.pinkship, R.drawable.purpleship, R.drawable.redship, R.drawable.yellowship
    };

    // How long and high our ship will be
    private float length;
    private float height;

    // X is the far left of the rectangle which forms our ship
    private float x;

    // Y is the top coordinate
    private float y;

    // This will hold the pixels per second speed that the ship will move
    private float shipSpeed;

    // Which ways can the ship move
    public final int STOPPED = 0;
    public final int LEFT = 1;
    public final int RIGHT = 2;

    // Is the ship moving and in which direction
    private int shipMoving = STOPPED;

    // This the the constructor method
    // When we create an object from this class we will pass
    // in the screen width and height
    public PlayerShip(Context context, int screenX, int screenY, int color  ){

        // Initialize a blank RectF
        rect = new RectF();

        length = (float) (screenX/10 *0.65); //TODO (SOLVED ) Check final size (now dependign on screen size ) hardcoded Fix We have to make retc graphics
        height = screenY/10;

        // Start ship in roughly the screen centre
        x = screenX / 2;
        y = screenY - 20;

        // Initialize the bitmap
        //Drawable shape = context.getResources().getDrawable(R.drawable.game_ship_darkpurp);
        //Bitmap bitmap = Bitmap.createBitmap( shape.getIntrinsicWidth(), shape.getIntrinsicHeight(),Bitmap.Config.ARGB_8888);
        // TODO Make array of ships and extend Constructor to take Color (by number from calling class) This can be done later
        /*
        Drawable [] arrayWithShipShapes = {
                R.drawable.dirtywhiteship, R.drawable.lightblueship, R.drawable.orangeship.......
        };
        */

        bitmap = BitmapFactory.decodeResource(context.getResources(),arrayWithShipShapes[color]);  //TODO Change to Vector shape implement method (or grab from screen that leads to this acitivity ) to get color please think about to declare the var on the right spot because of scope


        // stretch the bitmap to a size appropriate for the screen resolution
        bitmap = Bitmap.createScaledBitmap(bitmap,
                (int) (length),
                (int) (height),
                false);

        // How fast is the spaceship in pixels per second
        shipSpeed = 350;
    }

    public RectF getRect(){
        return rect;
    }

    // This is a getter method to make the rectangle that
    // defines our ship available in SpaceInvadersView class
    public Bitmap getBitmap(){
        return bitmap;
    }

    public float getX(){
        return x;
    }

    public float getLength(){
        return length;
    }

    // This method will be used to change/set if the ship is going left, right or nowhere
    public void setMovementState(int state){
        shipMoving = state;
    }

    // This update method will be called from update in SpaceInvadersView
    // It determines if the player ship needs to move and changes the coordinates
    // contained in x if necessary
    public void update(long fps){
        if(shipMoving == LEFT){
            x = x - shipSpeed / fps;
        }

        if(shipMoving == RIGHT){
            x = x + shipSpeed / fps;
        }

        // Update rect which is used to detect hits
        rect.top = y;
        rect.bottom = y + height;
        rect.left = x;
        rect.right = x + length;

    }






}
