
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class Sun extends JLabel{

	boolean type=true;//true sun
	
	File sun=new File("srcimages//sun.png");
	File moon=new File("srcimages//moon.png");
	
	BufferedImage img;
	
 
	public Sun(){
		this.setVisible(true);
		this.type=type;
		setSize(100,100);
		setLocation(0, 100);
		
		
		 try {
			 if(type ){ img=ImageIO.read(sun);}
			 else {img=ImageIO.read(moon);}
		  		} catch (IOException e) {System.out.println("resim bulunamadý");}
	}
	
	public void move(int x){this.setLocation(x, 100);}
	
	public void paint(Graphics g){g.drawImage(img, 0,0, null );}
	
	
	void changetype(){
		try {
			this.type=!this.type;
			if(type ){ img=ImageIO.read(sun);}
			 else {img=ImageIO.read(moon);}
			 } catch (IOException e) {System.out.println("resim bulunamadý");}
		 
	}
}