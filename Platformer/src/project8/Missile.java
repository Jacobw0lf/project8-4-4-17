package project8;

public class Missile extends Sprite {
	// shares code with sprite

    private final int BOARD_WIDTH = 4000;
    private final int MISSILE_SPEED = 2;

    public Missile(int x, int y) {
        super(x, y);

        initMissile();
    }
    
    private void initMissile() {
        
        loadImage("Images/missile.png");
        // loads the image of a Soldier
        getImageDimensions();  
        // gets the dimensions of an image
    }
    
    
  
    
    /*@Override
    public int getY() {
    	return super.getY();
    }*/

    public void move() {
        
        x += MISSILE_SPEED;
        
        if (x > BOARD_WIDTH)
            vis = false;
    }
}