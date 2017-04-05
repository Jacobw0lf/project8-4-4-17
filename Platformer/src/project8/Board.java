// Platformer
// 8th Grade Project
// Jacob Wolf


// 4/5/17

package project8;

import java.awt.Color;
// allows color

import java.awt.Dimension;
import java.awt.Font;
// Allows the use of different font
import java.awt.FontMetrics;
import java.awt.Graphics;
// allows graphics
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// listens for keystrokes
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
// Imports a timer

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    // Uses timer import
    private Soldier craft;
    private ArrayList<Zombie> aliens;
    private boolean ingame;
    // creates a true or false statement for whether or not game is running
    private final int ICRAFT_X = 40;
    // Soldier X Pos
    private final int ICRAFT_Y = 600;
    // Soldier Y Pos
    private final int B_WIDTH = 1600;
    // Board Width
    private final int B_HEIGHT = 800;
    // Board Height
    private final int DELAY = 7;
    // DELAY = 7: Uses timer to delay and repaint board every 7 milisecs
    
    

    private final int[][] pos = {
    		{3620, 600},{3501, 600},{3500, 600},{3100, 600},{3000, 600},{2700, 600},{2500,600},{2300,600},{2200,600},{1600, 600},{1500,600}, {1300, 600}, {1000, 600}, {950, 600},
        // Zombie spawn positions
    };

    public Board() {

        initBoard();
        // initiates board
    }

    private void initBoard() {
    	// Method's set when board is initalized

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.WHITE);
        ingame = true;
        // Uses true or false to set ingame state

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        craft = new Soldier(ICRAFT_X, ICRAFT_Y);
        // Spawns Soldier 

        initAliens();
        //

        timer = new Timer(DELAY, this);
        timer.start();
        // starts timer
    }

    public void initAliens() {
    	// intitializes aliens
        aliens = new ArrayList<>();

        for (int[] p : pos) {
            aliens.add(new Zombie(p[0], p[1]));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
        	// asks if state is ingame

            drawObjects(g);
            // is is ingame; draw objects

        } else {
        	//otherwise:

            drawGameOver(g);
            // Endgame
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {
    	// Draw objects method

        if (craft.isVisible()) {
            g.drawImage(craft.getImage(), craft.getX(), craft.getY() - craft.lift,
                    this);
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {
            if (m.isVisible()) {
                g.drawImage(m.getImage(), m.getX(), m.getY(), this);
            }
        }

        for (Zombie a : aliens) {
            if (a.isVisible()) {
                g.drawImage(a.getImage(), a.getX(), a.getY(), this);
            }
        }

        g.setColor(Color.BLACK);
        g.drawString("Score:" + ticks/ 65 ,5, 15);
        // creates Label and displays score count
    }
    
    
    
    private void drawGameOver(Graphics g) {

        String msg = "Game Over(" + ticks/ 65 + ")";
       
                                                                                        
        if (ticks/65 <= 10){
        	
        	Font small = new Font("Helvatica", Font.BOLD, 15);
        	FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);
        	g.drawString("Not Bad, But Keep Trying!", 705, 420);
        	
        	
        }
        
        if (ticks/65 >= 11 && ticks/65 <= 25){
        	
        	
        	
        	Font small = new Font("Helvatica", Font.BOLD, 15);
        	FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);
        	g.drawString("Wow!", 785, 420);
        	
        	     	
        }
        
            if (ticks/65 >=26  && ticks/65 <= 50){
        	
        	
        	
        	Font small = new Font("Helvatica", Font.BOLD, 15);
        	FontMetrics fm = getFontMetrics(small);

            g.setColor(Color.black);
            g.setFont(small);
        	g.drawString("Good!", 780, 420);
        	
        	
        	
        	
        }
            
            if (ticks/65 >=51  && ticks/65 <= 75){
            	
            	
            	
            	Font small = new Font("Helvatica", Font.BOLD, 15);
            	FontMetrics fm = getFontMetrics(small);

                g.setColor(Color.black);
                g.setFont(small);
            	g.drawString("Great!", 780, 420);
            	
            	
            	
            	
            }
            
              if (ticks/65 >=76 && ticks/65 <= 100){
            	
            	
            	
            	Font small = new Font("Helvatica", Font.BOLD, 15);
            	FontMetrics fm = getFontMetrics(small);

                g.setColor(Color.black);
                g.setFont(small);
            	g.drawString("AMAZING!", 780, 420);
            	
            	
            	
              }
              
              if (ticks/65 >= 101){
              	
              	
              	
              	Font small = new Font("Helvatica", Font.BOLD, 15);
              	FontMetrics fm = getFontMetrics(small);

                g.setColor(Color.black);
                g.setFont(small);
              	g.drawString("YOU A WINNER!", 795, 420);
              	
              	
              	
                }
        
        
       
        
        Font small = new Font("Helvetica", Font.BOLD, 40);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.black);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

     int ticks;
    // creates int that displays timer count ( which is counting every 7 mili)
    
    @Override
    public void actionPerformed(ActionEvent e) {
    	// Checks if action listener has found performed actions ( is updated every 7 mili's)
    	
    	++ticks;
    	// adds ticks

        inGame();
        //sets to ingame

        updateCraft();
        // updates craft
        updateMissiles();
        // updates missiles
        updateAliens();
        // updates aliens

        checkCollisions();
        // checks if missiles, aliens. or soldiers have collidied

        repaint();
        //repaints board
    }

    private void inGame() {
        
        if (!ingame) {
            timer.stop();
        }
    }

    private void updateCraft() {
    	// When craft is updated 

        if (craft.isVisible()) {
        	// asks if craft is visible
            craft.move();
            // if it is; allow craft to move
        }
    }

    private void updateMissiles() {

        ArrayList<Missile> ms = craft.getMissiles();

        for (int i = 0; i < ms.size(); i++) {

            Missile m = ms.get(i);

            if (m.isVisible()) {
                m.move();
            } else {
                ms.remove(i);
            }
        }
    }

    private void updateAliens() {

        if (aliens.isEmpty()) {

            ingame = false;
            return;
        }

        for (int i = 0; i < aliens.size(); i++) {

            Zombie a = aliens.get(i);
            if (a.isVisible()) {
                a.move();
            } else {
                aliens.remove(i);
            }
        }
    }

    public void checkCollisions() {

        Rectangle r3 = craft.getBounds();
        // asigns rectangle 3 (r3) 

        for (Zombie alien : aliens) {
            Rectangle r2 = alien.getBounds();
            // asigns alien to rectangle (r2)

            if (r3.intersects(r2)) {
            	// asks if the aliens rectangle interescts the Soldiers rectangle
            	// if it dies
                craft.setVisible(false);
                // set craft to invisible
                alien.setVisible(false);
                // sets alien to invisible
                ingame = false;
                // sets ingame to false
                
            }
        }

        ArrayList<Missile> ms = craft.getMissiles();

        for (Missile m : ms) {

            Rectangle r1 = m.getBounds();

            for (Zombie alien : aliens) {

                Rectangle r2 = alien.getBounds();

                if (r1.intersects(r2)) {
                    m.setVisible(false);
                    alien.setVisible(false);
                }
            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            craft.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            craft.keyPressed(e);
        }
    }
}
