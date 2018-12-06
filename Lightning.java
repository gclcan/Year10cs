package Game1;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

public class Lightning {
	private float xCoord = 0;
	private int yCoord = 0;
	private int width = 2;
	private int height = 2;
	private Image img;

		
		/**
		 * Goodguy default constructor
		 */
		public Lightning() {
			setxCoord(10);
			setyCoord(10);
			setWidth(30);
			setHeight(30);
			setImg("Images/bolt.png");
		}
		
		/**
		 * Badguy overloaded constructor
		 * @param x initial x location
		 * @param y initial y location
		 * @param w initial width
		 * @param h initial height
		 */
		public Lightning(int x, int y, int w, int h, String imgpath) {
			setxCoord(x);
			setyCoord(y);
			setWidth(w);
			setHeight(h);
			setImg(imgpath);
		}
		
		/*public void moveIt2 (int direction) {
			int speed = 20;
			int x = getxCoord();
			int y = getyCoord();
			if (direction == 68) {
				x = x + speed;
				setxCoord(x);
				setImg("files/JohnProctor.jpg");
			} else if (direction == 65) {
				x = x - speed;
				setxCoord(x);
				setImg("files/JohnProctor.jpg");
				}
			  else if (direction == 87) { //going up
				  y = y - speed;
				  setyCoord(y);
				  setImg("files/JohnProctor.jpg");}
			  else if (direction == 83) { //going down
				  y = y + speed;
				  setyCoord(y);
				  setImg("files/JohnProctor.jpg");
				  }
		}*/
		public void setImg(Image img) {
			this.img = img;
		}
		
		public void setImg(String imgpath) {
			this.img = Toolkit.getDefaultToolkit().getImage(imgpath);
		}

		public float getxCoord() {
			return this.xCoord;
		}

		public void setxCoord(float xCoord) {
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

		public void setWidth(int width) {
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
		


