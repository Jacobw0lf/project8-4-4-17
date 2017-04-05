package project8;

public class Zombie extends Sprite {

	private int initialX;
    
	private int zombieCount = 1;
    
    public Zombie(int x, int y) {
        super(x, y);
        initialX = x;
        

        initAlien();
    }

    private void initAlien() {

        loadImage("Images/Zombie250.png");
        getImageDimensions();
    }

    public void move() {

        if (x < 0) {
            x = initialX;
            ++zombieCount;
        }

        // Figure out some math to make this every few zombies?
        x -= zombieCount;
    }
}
