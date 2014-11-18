
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BGpanel extends JPanel{

	Image img;
	boolean day=true;
	BGpanel(){
		if(day)this.img=(new ImageIcon("srcimages//backgroundd.jpg").getImage());
		else this.img=(new ImageIcon("srcimages//backgroundn.jpg").getImage());
		this.setLocation(0,0);
		this.setLayout(null);
		Dimension size=new Dimension(getMaximumSize());
		this.setSize(size);
	}
	void changetime(){
		day=!day;
		if(day)this.img=(new ImageIcon("srcimages//backgroundd.jpg").getImage());
		else this.img=(new ImageIcon("srcimages//backgroundn.jpg").getImage());}
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
}
