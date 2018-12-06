package Game1;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;


public class TileGameCanvas extends Canvas { //our grid will be drawn in a dedicated Panel	
	private int size; //size of the puzzle
	private int nbTiles; //number of tiles
	private int dimension; // Grid UI Dimension
	private static final Color FOREGROUND_COLOR = new Color(238,83,80); // Foreground Color //here I am using arbitrary color 
	private static final  Random RANDOM = new Random(); //Random object to shuffle the tiles
	private int[] tiles; //Storing the tiles in a 1D Array of integers
	private int tileSize; // Size of a tile on UI
	private int blankPos; //Position of the blank tile
	private int margin; // Margin for the grid on the frame
	private int gridSize; //Grid UI Size
	private boolean gameOver; // true if the game is over, false otherwise
	//private JFrame frame;
	
	
	
	
	public TileGameCanvas(int size, int dim, int mar) {
		this.size = size;
		dimension = dim;
		margin = mar;
		
		//init tiles
		nbTiles = size*size - 1;  //it is -1 because we do not count the blank tile
		tiles = new int[size*size];
		
		//calculating grid size and tile size
		gridSize = (dim - 2 * margin);
		tileSize = gridSize / size;
		
		setPreferredSize(new Dimension(dimension, dimension + margin));
		setBackground(Color.GREEN);
		setForeground(FOREGROUND_COLOR);
		setFont(new Font("Calibri", Font.ITALIC, 60));
		
		gameOver= true;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//used to let users to interact on the grid by clicking
				// implemented later
				if (gameOver) {
					newGame();
				} else {
					//get position of the mouse click
					int ex = e.getX() - margin;
					int ey = e.getY() - margin;
					
					//mouse click in the grid
					
					if (ex < 0 || ex > gridSize || ey < 0 || ey> gridSize)
						return;
					
					//getting the position in the grid 
					int c1 = ex / tileSize;
					int r1 = ey / tileSize;
					
					//get position of the blank tile
					int c2 = blankPos % size;
					int r2 = blankPos / size;
					
					//covertting into 1D coord
					int clickPos = r1 * size + c1;
					
					int dir = 0;
							
							//searching for multiple tile moves at an instance
							if (c1 == c2  &&  Math.abs(r1 - r2) > 0)
								dir = (r1 - r2) > 0 ? size : -size;
							else if (r1 == r2 && Math.abs(c1 - c2) > 0)
								dir = (c1 - c2) > 0 ? 1: -1;
								
							if (dir != 0) {
								// moving the tiles in the direction
								do {
									int newBlankPos = blankPos + dir;
									tiles[blankPos] = tiles[newBlankPos];
									blankPos = newBlankPos;
								} while(blankPos != clickPos);
								
								tiles[blankPos] = 0;
							}
								
							//checking if the game is over
							gameOver = isSolved();
					}
				//repainting the panel
				repaint();
			}
		});
		
		newGame();
	
	}

	
	private void newGame() {
		do { 
			reset(); // reset in initial state
			shuffle(); //shuffles
		} while(!isSolvable()); // make it until grid will be solvable	
		
		gameOver = false;
		
	}
	
	private void reset() {
		for (int i = 0; i < tiles.length; i++) {
			tiles[i] = (i + 1) % tiles.length;
		}
		
		//I set the blank cell at the last part
		blankPos = tiles.length - 1;
	}
	
	private void shuffle() {
		//I did not include the blank tile in the shuffle and I will leave in the solved position
		int n = nbTiles;
		
		while(n > 1) {
			int r =RANDOM.nextInt(n--);
			int tmp = tiles[r];
			tiles[r] = tiles[n];
			tiles[n] = tmp;
		}
	}
	/* From the tutorial I learned that half the permutations were solvable. Also whenever a tile is preceded by a tile with higher value it
	 * counts as an inversion. In this particular case, with the blank tile in the solved position, the number of inversions  must be even
	 * for the puzzle to be solvable*/
	private boolean isSolvable() {
		int countInversions = 0;
		
		for(int i=0; i< nbTiles; i++) {
			for(int j = 0; j < i; j++) {
				if (tiles[j] > tiles[i])
					countInversions++;
			}
		}
		
		return countInversions % 2 == 0;
	}
	
	private boolean isSolved() {
		if (tiles[tiles.length -1] != 0) //if the blank tile is not in the solved position, it means that it is not solved and the game continues
			return false;
		
		for(int i = nbTiles - 1; i >= 0; i--) {
			if (tiles[i] != i + 1)
				return false;
		
		}	
		return true;
	}
	
	private void drawGrid(Graphics2D g) {
		for (int i = 0; i < tiles.length; i ++) {
			// you need to convert 1D coords to 2D coords given the size of the D array
			int r = i / size;
			int c = i % size;
			// you need to convert the coords in to the UI
			int x = margin + c*tileSize;
			int y = margin + r*tileSize;
			
			//checking the special case for the blank tile 
			if(tiles[i] == 0) {
				if (gameOver) {
					g.setColor(FOREGROUND_COLOR);
					drawCenteredString(g, "✓", x, y);
				}
				
				continue;
			}
			
			//for other tiles
			g.setColor(getForeground());
			g.fillRoundRect(x, y, tileSize, tileSize, 25, 25);
			g.setColor(Color.BLACK);
			g.drawRoundRect(x, y, tileSize, tileSize, 25, 25);
			g.setColor(Color.BLUE);
			
			drawCenteredString(g, String.valueOf(tiles[i]), x, y);
		}
	}
	
	private void drawStartMessage(Graphics2D g) {
		if (gameOver) {
			g.setFont(getFont().deriveFont(Font.ITALIC, 19));
			g.setColor(FOREGROUND_COLOR);
			String s = "Click to start new game";
			g.drawString(s, (getWidth() - g.getFontMetrics().stringWidth(s)) / 2,
					getHeight() - margin);
		}
	}
	
	private void drawCenteredString(Graphics2D g, String s, int x, int y) {
		//center string s for the given tile (x,y)
		FontMetrics fm = g.getFontMetrics();
		int asc = fm.getAscent();
		int desc = fm.getDescent();
		g.drawString(s, x + (tileSize - fm.stringWidth(s)) / 2,
				y + (asc + (tileSize - (asc + desc)) / 2));
				
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawGrid(g2D);
		drawStartMessage(g2D);
	
	}
	
	/*public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setTitle("Sliding Puzzle");
			frame.setResizable(false);
			frame.add(new SlidingPuzzle(4, 550, 30), BorderLayout.CENTER);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
			
		});
	}*/
	
}


