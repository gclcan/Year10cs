package Game1;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JFrame;
public class TitleScreen extends JPanel implements ActionListener {
	private String name = "2 in 1 Game"; //titlescreen name
	private Container c;
	private JFrame  titlescreen;
	private JButton button1; //sliding puzzle
	private JButton button2; //another puzzle
	private JButton button3; //another puzzle
	public TitleScreen(){
		titlescreen = new JFrame("2 in 1 Game");
		button1 = new JButton("Tile Game"); 
		button1.setPreferredSize(new Dimension(50,50));
		button1.setBounds(180, 110, 100 , 100);
		button1.addActionListener(this);
		button2 = new JButton("Battle Game"); 
		button2.setPreferredSize(new Dimension(50,50));
		button2.setBounds(80, 110, 100 , 100);
		button2.addActionListener(this);
		titlescreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c = titlescreen.getContentPane();
		this.setLayout(null);
		this.add(button1);
		this.add(button2);
		c.add(this) ;
		this.setPreferredSize(new Dimension(300,300));	
		titlescreen.pack();
		titlescreen.setVisible(true);
		
	}
	public static void main (String[] args) {
		new TitleScreen();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setFont(new Font(name, 10, 18) );
		g.setColor(Color.black);
		g.drawString(name, 100, 20);
		
	}
	@Override
	public void actionPerformed(ActionEvent x) {
		Object o = x.getSource(); //this gets operated when the button gets clicked 
		if(o == button2 ) { // comparing the object to the button
			new MazeScreen();
			this.titlescreen.dispose(); // giving up the resources of the frame 
		}
		else if(o == button1 ) { // comparing the object to the button
			new TileGameScreen();
			this.titlescreen.dispose(); // giving up the resources of the frame 
		}
	
	}
}
