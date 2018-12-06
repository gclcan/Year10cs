package Game1;


import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import sun.audio.*;

public class BattleCanvas extends Canvas implements KeyListener {
	
	// global variables - accessible in all methods
	Character pika = new Character(10,10,50,50,"Images/pikachu_right.png");
	LinkedList enemies = new LinkedList();
	LinkedList bolts = new LinkedList();
	Image map = Toolkit.getDefaultToolkit().getImage("Images/pokemon_gym.jpg");
	

	public BattleCanvas() {
		this.setSize(860,535); // set same size as MyScreen
		this.addKeyListener(this); // add the listener to your canvas
		//playIt("Images/PrayForMeInstrumental.wav");
		
		TimerTask repeatedTask = new TimerTask() {

            public void run() {
                for(int i = 0; i < enemies.size(); i++) {// draw bad guys
                    Enemy bg = (Enemy) enemies.get(i);
                    bg.setxCoord(bg.getxCoord() - 30);

                }

                repaint();

            }

        };
        Timer timer = new Timer("Timer");

        long delay  = 1000L;

        long period = 1000L;

        timer.scheduleAtFixedRate(repeatedTask, delay, period);
		
		Random rand = new Random();
		int winwidth = this.getWidth();
		int winheight = this.getHeight();
		for(int i = 0; i< 30; i++) {
			Enemy foe = new Enemy(rand.nextInt(winwidth)+600, rand.nextInt(winheight)+30,50,50,"Images/Meowth.png");
			Rectangle r = new Rectangle(100,100,30,30);
			if (r.contains(pika.getxCoord(),pika.getyCoord())) { // check to see if badguy spawns on link
				System.out.println("badguy on top of link");
				continue;
			}
			enemies.add(foe);
		}
	}


	public void playIt(String filename) {
		
		try {
			InputStream in = new FileInputStream(filename);
			AudioStream as = new AudioStream(in);
			AudioPlayer.player.start(as);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(map, 0, 0, this);
		g.drawImage(pika.getImg(), pika.getxCoord(), pika.getyCoord(), pika.getWidth(), pika.getHeight(), this); // draw good guy
		
		for(int i = 0; i < enemies.size(); i++) {// draw bad guys
			Enemy bg = (Enemy) enemies.get(i);
			g.drawImage(bg.getImg(), bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight(), this); 
			Rectangle r = new Rectangle(bg.getxCoord(), bg.getyCoord(), bg.getWidth(), bg.getHeight());
			
			for(int j = 0; j < bolts.size(); j++) {
				Lightning z =(Lightning) bolts.get(j);
				if (z.getxCoord() > this.getWidth()) { bolts.remove(z); }
				z.setxCoord((float) (z.getxCoord() + 0.05));
				g.drawImage(z.getImg(), (int) z.getxCoord(), z.getyCoord(), z.getWidth(),z.getHeight(), this);
				
				Rectangle kr = new Rectangle((int) z.getxCoord(), z.getyCoord(), z.getWidth(), z.getHeight());
				if (kr.intersects(r)) {
					enemies.remove(i);
					bolts.remove(j);
				}
			}   repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println(e);
		
		if(e.getKeyCode() == 81) {
			Lightning bolt = new Lightning(pika.getxCoord(), pika.getyCoord(), 30, 30, "Images/bolt.png");
			bolts.add(bolt);
		}
		
		pika.moveIt(e.getKeyCode(),this.getWidth(),this.getHeight()); // move link in response to keypress
			
		for(int i = 0; i < enemies.size(); i++) { // check if badguys hit 
			Enemy bg = (Enemy) enemies.get(i); // convert generic 
			Rectangle r = new Rectangle(bg.getxCoord(),bg.getyCoord(),bg.getWidth(),bg.getHeight());
			if (r.contains(pika.getxCoord(),pika.getyCoord())) { 
				System.out.println("badguy hit by link");
				enemies.remove(i);
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//System.out.println(e);
	}

}