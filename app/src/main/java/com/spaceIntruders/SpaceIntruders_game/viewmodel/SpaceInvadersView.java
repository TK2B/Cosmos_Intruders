package com.spaceIntruders.SpaceIntruders_game.viewmodel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.spaceIntruders.SpaceIntruders_game.model.Bullet;
import com.spaceIntruders.SpaceIntruders_game.model.DefenceBrick;
import com.spaceIntruders.SpaceIntruders_game.model.Invader;
import com.spaceIntruders.SpaceIntruders_game.model.PlayerShip;
import com.spaceIntruders.basicAplication.R;
//import com.spaceIntruders.SpaceIntruders_game.model.Highscore;


public class SpaceInvadersView extends SurfaceView implements Runnable {

    Context context;

    // This is our thread
    private Thread gameThread = null;

    // Our SurfaceHolder to lock the surface before we draw our graphics
    private SurfaceHolder ourHolder;

    // A boolean which we will set and unset
    // when the game is running- or not.
    private volatile boolean playing;

    // Game is paused at the start
    private boolean paused = true;

    // A Canvas and a Paint object
    private Canvas canvas;
    private Paint paint;

    // This variable tracks the game frame rate
    private long fps;

    // This is used to help calculate the fps
    private long timeThisFrame;

    // The size of the screen in pixels
    private int screenX;
    private int screenY;

    // The players ship
    private PlayerShip playerShip;

    // The player's bullet
    private Bullet bullet;

    // The invaders bullets
    private Bullet[] invadersBullets = new Bullet[200];
    private int nextBullet;
    private int maxInvaderBullets = 10; //TODO INFO maxInvaderBullets

    // Up to 60 invaders
    Invader[] invaders = new Invader[60];
    int numInvaders = 0;

    // The player's shelters are built from bricks
    private DefenceBrick[] bricks = new DefenceBrick[400];
    private int numBricks;

    // For sound FX
    private SoundPool soundPool;
    private int playerExplodeID = -1;
    private int invaderExplodeID = -1;
    private int shootID = -1;
    private int damageShelterID = -1;
    private int uhID = -1;
    private int ohID = -1;
    private boolean loaded;

    // The score
    int score = 0;

    // Lives
    private int lives = 5;

    // How menacing should the sound be?
    private long menaceInterval = 1000;
    // Which menace sound should play next
    private boolean uhOrOh;
    // When did we last play a menacing sound
    private long lastMenaceTime = System.currentTimeMillis();


    // When the we initialize (call new()) on gameView
// This special constructor method runs
    public SpaceInvadersView(Context context, int x, int y) {  // TODO INFO place lvel int in contructor to make it harder (Level) and build some logic that make sure it starts with the level set - etc increase the level after succsess

        // The next line of code asks the
        // SurfaceView class to set up our object.
        // How kind.
        super(context);

        // Make a globally available copy of the context so we can use it in another method
        this.context = context;

        // Initialize ourHolder and paint objects
        ourHolder = getHolder();
        paint = new Paint();

        screenX = x;
        screenY = y;

        // This SoundPool is deprecated  // TODO there is a "better " newer way maybe you could read the refference ? Thats the way i knew (not shure on which android version it´´ running right  https://developer.android.com/reference/android/media/SoundPool.Builder
        // soundPool= new SoundPool.Builder(10, 0, STREAM_MUSIC);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            soundPool = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        }

        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int mySoundId, int status) {
                loaded = true;
                //descriptor = assetManager.openFd("shoot.ogg");
                // Load our fx in memory ready for use

            }
        });


        shootID = soundPool.load (getContext() , R.raw.shoot,0 );
        Log.e("soothid", String.valueOf(shootID));

        //descriptor = assetManager.openFd("invaderexplode.ogg");
        invaderExplodeID = soundPool.load(context , R.raw.invaderexplode, 0);

        //descriptor = assetManager.openFd("damageshelter.ogg");
        damageShelterID = soundPool.load(context , R.raw.damageshelter, 0);

        //descriptor = assetManager.openFd("playerexplode.ogg");
        playerExplodeID = soundPool.load(context , R.raw.playerexplode, 0);

        //descriptor = assetManager.openFd("uh.ogg");
        uhID = soundPool.load(context , R.raw.uh , 0);

        //descriptor = assetManager.openFd("oh.ogg");
        ohID = soundPool.load(context , R.raw.oh, 0);



        prepareLevel();
    }


    private void prepareLevel() {

        // Here we will initialize all the game objects

        // Reset the menace level
        menaceInterval = 1000;  // TODO check if playable on mobile device

        // Make a new player space ship
        playerShip = new PlayerShip(context, screenX, screenY);


        // Prepare the players bullet
        bullet = new Bullet(screenY);

        // Initialize the invadersBullets array
        for(int i = 0; i < invadersBullets.length; i++){
            invadersBullets[i] = new Bullet(screenY);
        }


        // Build an army of invaders
        numInvaders = 0;
        for(int column = 0; column < 6; column ++ ){
            for(int row = 0; row < 3; row ++ ){   //TODO INFO changed from 5 to 3 - have a look at this line for more levels or setting page
                invaders[numInvaders] = new Invader(context, row, column, screenX, screenY);
                numInvaders ++;
            }
        }

        // Build the shelters
        numBricks = 0;
        for(int shelterNumber = 0; shelterNumber < 4; shelterNumber++){
            for(int column = 0; column < 10; column ++ ) {
                for (int row = 0; row < 5; row++) {
                    bricks[numBricks] = new DefenceBrick(row, column, shelterNumber, screenX, screenY);
                    numBricks++;
                }
            }
        }

    }


    @Override
    public void run() {
        while (playing) {

            // Capture the current time in milliseconds in startFrameTime
            long startFrameTime = System.currentTimeMillis();

            // Update the frame
            if (!paused) {
                update();
            }

            // Draw the frame
            draw();

            // Calculate the fps this frame
            // We can then use the result to
            // time animations and more.
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                fps = 1000 / timeThisFrame;
            }

            // We will do something new here towards the end of the project
            // Play a sound based on the menace level
            if(!paused) {
                if ((startFrameTime - lastMenaceTime) > menaceInterval) {
                    if (uhOrOh) {
                        // Play Uh
                        soundPool.play(uhID, 1, 1, 0, 0, 1);

                    } else {
                        // Play Oh
                        soundPool.play(ohID, 1, 1, 0, 0, 1);
                    }

                    // Reset the last menace time
                    lastMenaceTime = System.currentTimeMillis();
                    // Alter value of uhOrOh
                    uhOrOh = !uhOrOh;
                }
            }

        }





    }

        private void update() {

            // Did an invader bump into the side of the screen
            boolean bumped = false;

            // Has the player lost
            boolean lost = false;

            // Move the player's ship
            playerShip.update(fps);

            // Update all the invaders if visible
            for(int i = 0; i < numInvaders; i++){

                if(invaders[i].getVisibility()) {
                    // Move the next invader
                    invaders[i].update(fps);

                    // Does he want to take a shot?
                    if(invaders[i].takeAim(playerShip.getX(),
                            playerShip.getLength())){

                        // If so try and spawn a bullet
                        if(invadersBullets[nextBullet].shoot(invaders[i].getX()
                                        + invaders[i].getLength() / 2,
                                invaders[i].getY(), bullet.DOWN)) {

                            // Shot fired
                            // Prepare for the next shot
                            nextBullet++;

                            // Loop back to the first one if we have reached the last
                            if (nextBullet == maxInvaderBullets) {
                                // This stops the firing of another bullet until one completes its journey
                                // Because if bullet 0 is still active shoot returns false.
                                nextBullet = 0;
                            }
                        }
                    }

                    // If that move caused them to bump the screen change bumped to true
                    if (invaders[i].getX() > screenX - invaders[i].getLength()
                            || invaders[i].getX() < 0){

                        bumped = true;

                    }
                }

            }

            // Update all the invaders bullets if active
            for(int i = 0; i < invadersBullets.length; i++){
                if(invadersBullets[i].getStatus()) {
                    invadersBullets[i].update(fps);
                }
            }

            // Did an invader bump into the edge of the screen
            if(bumped){

                // Move all the invaders down and change direction
                for(int i = 0; i < numInvaders; i++){
                    invaders[i].dropDownAndReverse();
                    // Have the invaders landed
                    if(invaders[i].getY() > screenY - screenY / 10){
                        lost = true;
                    }
                }

                // Increase the menace level
                // By making the sounds more frequent
                menaceInterval = menaceInterval - 80;
            }


            if (lost) {
                prepareLevel();
            }

            // Update the players bullet
            if(bullet.getStatus()){
                bullet.update(fps);
            }


            // Has the player's bullet hit the top of the screen
            if(bullet.getImpactPointY() < 0){
                bullet.setInactive();
            }

            // Has an invaders bullet hit the bottom of the screen
            for(int i = 0; i < invadersBullets.length; i++){
                if(invadersBullets[i].getImpactPointY() > screenY){
                    invadersBullets[i].setInactive();
                }
            }

            // Has the player's bullet hit an invader
            if(bullet.getStatus()) {
                for (int i = 0; i < numInvaders; i++) {
                    if (invaders[i].getVisibility()) {
                        if (RectF.intersects(bullet.getRect(), invaders[i].getRect())) {
                            invaders[i].setInvisible();
                            soundPool.play(invaderExplodeID, 1, 1, 0, 0, 1);
                            bullet.setInactive();
                            score = score + 10;

                            // Has the player won
                            if(score == numInvaders * 10){
                                paused = true;
                                score = 0;
                                lives = 5;
                                prepareLevel();
                            }
                        }
                    }
                }
            }

            // Has an alien bullet hit a shelter brick
            for(int i = 0; i < invadersBullets.length; i++){
                if(invadersBullets[i].getStatus()){
                    for(int j = 0; j < numBricks; j++){
                        if(bricks[j].getVisibility()){
                            if(RectF.intersects(invadersBullets[i].getRect(), bricks[j].getRect())){
                                // A collision has occurred
                                invadersBullets[i].setInactive();
                                bricks[j].setInvisible();
                                soundPool.play(damageShelterID, 1, 1, 0, 0, 1);
                            }
                        }
                    }
                }

            }

            // Has a player bullet hit a shelter brick
            if(bullet.getStatus()){
                for(int i = 0; i < numBricks; i++){
                    if(bricks[i].getVisibility()){
                        if(RectF.intersects(bullet.getRect(), bricks[i].getRect())){
                            // A collision has occurred
                            bullet.setInactive();
                            bricks[i].setInvisible();
                            soundPool.play(damageShelterID, 1, 1, 0, 0, 1);
                        }
                    }
                }
            }

            // Has an invader bullet hit the player ship
            for(int i = 0; i < invadersBullets.length; i++){
                if(invadersBullets[i].getStatus()){
                    if(RectF.intersects(playerShip.getRect(), invadersBullets[i].getRect())){
                        invadersBullets[i].setInactive();
                        lives --;
                        soundPool.play(playerExplodeID, 1, 1, 0, 0, 1);

                        // Is it game over?
                        if(lives == 0){
                            //TODO write method to write to database and go to higscore


                            paused = true;
                            lives = 3;
                            score = 0;
                            prepareLevel();


                        }
                    }
                }
            }


        }

        private void draw () {
        //TODO set colors if you want to use bitmaps you need to apply colorfilters https://stackoverflow.com/questions/5699810/how-to-change-bitmap-image-color-in-android
            // Make sure our drawing surface is valid or we crash
            if (ourHolder.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = ourHolder.lockCanvas();

                // Draw the background color
                canvas.drawColor(Color.argb(255, 5, 5, 5));

                // Choose the brush color for drawing
                //paint.setColor(Color.argb(255, 85, 0, 51)); // overwrite in line 451

                // Now draw the player spaceship
                canvas.drawBitmap(playerShip.getBitmap(), playerShip.getX(), screenY - 120, paint);   //TODO have a look at screenY it might be neccesarry to set lower/higher not sure because using an emulation of android in QEMU

                // Draw the invaders


                for(int i = 0; i < numInvaders; i++){
                    if(invaders[i].getVisibility()) {
                        if(uhOrOh) {
                            canvas.drawBitmap(invaders[i].getBitmap(), invaders[i].getX(), invaders[i].getY(), paint);
                        }
                    }
                }

                // Draw the bricks if visible
                paint.setColor(Color.argb(255, 0, 0, 91));
                for(int i = 0; i < numBricks; i++){
                    if(bricks[i].getVisibility()) {
                        canvas.drawRect(bricks[i].getRect(), paint);
                    }
                }

                // Draw the players bullet if active
                paint.setColor(Color.argb(255, 232, 222, 0));
                if(bullet.getStatus()){
                    canvas.drawRect(bullet.getRect(), paint);
                }



                // Draw the invaders bullets if active
                paint.setColor(Color.argb(255, 0, 232, 232));
                // Update all the invader's bullets if active
                for(int i = 0; i < invadersBullets.length; i++){
                    if(invadersBullets[i].getStatus()) {
                        canvas.drawRect(invadersBullets[i].getRect(), paint);
                    }
                }

                // Draw the score and remaining lives
                // Change the brush color
                //TODO you may change it to a Textview i choose to draw it for consitency of design
                paint.setColor(Color.argb(255, 216, 0, 131));
                paint.setTextSize(40);
                canvas.drawText("Score: " + score + "   Lives: " + lives, 10, 50, paint);

                // Draw everything to the screen
                ourHolder.unlockCanvasAndPost(canvas);
            }
        }

        // If SpaceInvadersActivity is paused/stopped
        // shutdown our thread.
        public void pause () {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }

        }

        // If SpaceInvadersActivity is started then
        // start our thread.
        public void resume () {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

        // The SurfaceView class implements onTouchListener
        // So we can override this method and detect screen touches.
        @Override
        public boolean onTouchEvent(MotionEvent motionEvent) {

            switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

                // Player has touched the screen
                case MotionEvent.ACTION_DOWN:

                    paused = false;

                    if(motionEvent.getY() > screenY - screenY / 8) {
                        if (motionEvent.getX() > screenX / 2) {
                            playerShip.setMovementState(playerShip.RIGHT);
                        } else {
                            playerShip.setMovementState(playerShip.LEFT);
                        }

                    }

                    if(motionEvent.getY() < screenY - screenY / 8) {   // TODO Please check out if that is a good idea otherwise we have to add a fire button
                        // Shots fired
                        if(bullet.shoot(playerShip.getX()+
                                playerShip.getLength()/2,screenY,bullet.UP)){
                            soundPool.play(shootID, 1, 1, 0, 0, 1);
                            Log.e ("soundplayed", "shoot");
                        }
                    }
                    break;

                // Player has removed finger from screen
                case MotionEvent.ACTION_UP:

                    if(motionEvent.getY() > screenY - screenY / 10) {
                        playerShip.setMovementState(playerShip.STOPPED);
                    }

                    break;

            }

            return true;
        }

}
