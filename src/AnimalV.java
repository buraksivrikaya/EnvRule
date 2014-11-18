
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;

public class AnimalV extends JLabel{

	boolean type;//true wolf- false sheep
	boolean direction;//true right- false left
	
	File sheepl=new File("srcimages//sheepl.png");
	File sheepr=new File("srcimages//sheepr.png");
	File wolfl=new File("srcimages//wolfl.png");
	File wolfr=new File("srcimages//wolfr.png");
	
	BufferedImage img;
	
	
	public AnimalV(){new AnimalV(true,new Point(0,0));}
	public AnimalV(boolean type, Point location){
		this.setVisible(true);
		this.type=type;
		setSize(142,105);
		setLocation(location.x, location.y);
		
		
		 try {
			 if(type && !direction){
			  img=ImageIO.read(wolfl);}
			 else if (type && direction){img=ImageIO.read(wolfr);}
			 else if(!type && !direction){img=ImageIO.read(sheepl);}
			 else if(!type && direction){img=ImageIO.read(sheepr);}
		  		} catch (IOException e) {System.out.println("resim bulunamadý");}
	}
	
	public void moveto(int x, int y){this.setLocation(x, y);}
	
	public void paint(Graphics g){g.drawImage(img, 0,0, null );}
	
	
	void changedirection(boolean direction){
		try {
			this.direction=direction;
			if(type && !direction){img=ImageIO.read(wolfl);}
			else if (type && direction){img=ImageIO.read(wolfr);}
		    else if(!type && !direction){img=ImageIO.read(sheepl);}
			else if(!type && direction){img=ImageIO.read(sheepr);}
			 } catch (IOException e) {System.out.println("resim bulunamadý");}
		 
	}
}
