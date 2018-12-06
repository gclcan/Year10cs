package Game1;

import java.awt.Image;
import java.awt.Toolkit;


public class Character {
	
	private int xCoord = 0;
	private int yCoord = 0;
	private int width = 10;
	private int height = 10;
	private Image img;
	
	

	public Character() {
		setxCoord(0);
		setyCoord(0);
		setWidth(0);
		setHeight(0);
		setImg("Images/pikachu-right.png");
	} 
	
	/**
	 * Character overloaded constructor
	 * @param x initial x location
	 * @param y initial y location
	 * @param w initial width
	 * @param h initial height
	 */
	public Character(int x, int y, int w, int h, String imgpath) {
		setxCoord(x);
		setyCoord(y);
		setWidth(w);
		setHeight(h);
		setImg(imgpath);
	}
	
	public void moveIt(int direction, int w, int h) {
		int speed = 20;
		int x = getxCoord();
		int y = getyCoord();
		if (direction == 39) {
			x = x + speed;
			if (x > 900 ) { x = x - speed * 1; } 
			setxCoord(x);
			setImg("Images/pikachu_right.png");
		} else if (direction == 37) {
			if (x < 0) { x = x + speed*1;}
			x = x - speed;
			setxCoord(x);
			setImg("Images/pikachu-left.png");
			}
		  else if (direction == 38) { //going up
			  if (y < 0) { y = y + speed * 1; }
			  y = y - speed;
			  setyCoord(y);
			  }
		  else if (direction == 40) { //going down
			if (y > 490) { y = y - speed * 1 ;}
			  y = y + speed;
			  setyCoord(y);
			  }
		}

	public void setImg(String imgpath) {
		this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
	}

	public int getxCoord() {
		return this.xCoord;
	}

	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	public int getyCoord() {
		return this.yCoord;
	}

	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}
	
	public int getWidth() {
		return this.width;
	}

	public void setWidth(int width ) {
		this.width = width;
	}

	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public Image getImg() {
		return img;
	}
		
}