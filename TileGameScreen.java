package Game1;

import java.awt.BorderLayout;
import javax.swing.JFrame;

public class TileGameScreen extends JFrame {


	public TileGameScreen() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tile Game");
		frame.setResizable(false);
		frame.add(new TileGameCanvas(4, 550, 30), BorderLayout.CENTER);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	// main method is required to run the program
	public static void main(String[] args) {
		// instantiate an individual instance of MyScreen by calling on constructor
		TileGameScreen screen = new TileGameScreen();
		TileGameCanvas canvas = new TileGameCanvas(4, 550, 30);
		screen.getContentPane().add(canvas);
//		JFrame frame = new JFrame();
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setTitle("Tile Game");
//		frame.setResizable(false);
//		frame.add(new TileGameCanvas(4, 550, 30), BorderLayout.CENTER);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);	
		
	}
	
}
