import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class mainFrame extends JFrame{

	public Swarm suru;
	int nBoundary=Toolkit.getDefaultToolkit().getScreenSize().height-400;
	BGpanel bg=new BGpanel();
	public List <AnimalV> animals=new ArrayList <AnimalV> ();
	public Dimension screenSize=Toolkit.getDefaultToolkit().getScreenSize();
	int speedf=33;
	
	
	int getSpeed(){int a=speedf;return a;}
	
	mainFrame(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(screenSize);
		this.setResizable(false);
		this.setLocation(0,0);
		this.setAlwaysOnTop(true);
		this.setVisible(true);
		int boundary=nBoundary;
		suru=new Swarm(boundary);
		this.add(bg);

		bg.addMouseListener(new MouseListener(){
			public void mouseClicked(MouseEvent e) {
			    switch(e.getModifiers()) {
			      case 16: {
			    	    Point starpoint=new Point(e.getX()-15,e.getY()-15);
				        suru.Boids.add(new Animal(false, starpoint));
				        animals.add(new AnimalV(false, starpoint));
				        bg.add(animals.get(animals.size()-1));
			        break;
			        }
			      case 8: {
			        System.out.println(e.getX()+","+e.getY());
			        break;
			        }
			      case 4: {
			    	  	Point starpoint=new Point(e.getX()-15,e.getY()-15);
				        suru.Boids.add(new Animal(true, starpoint));
				        animals.add(new AnimalV(true, starpoint));
				        bg.add(animals.get(animals.size()-1));
				        
			        break;
			        }
			      }				
			}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {}
			public void mouseReleased(MouseEvent e) {}});
			
		
		//-----sun start
		Sun s=new Sun();
		bg.add(s);
		int sunx=0;
		
		//-----sun end
		
		setAnimalArray();
		createAnimals();
		while(true){
			//---sun movement start
			if(sunx<getWidth()){s.move(sunx+=10);}
			else{sunx=0;s.move(sunx);s.changetype();bg.changetime();}
			//---sun movement end
			
			
			try {
			Thread.sleep(getSpeed());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			//flashstars();
			moveSwarm();
			moveAnimalVs();
			setDirection();
			rain();
			repaint();
		}
	}
	
	void setAnimalArray(){
		for( Animal boid : suru.Boids){
			Point starpoint=new Point(boid.Position);
			if(boid.Zombie){animals.add(new AnimalV(true,starpoint));}
			else {animals.add(new AnimalV(false,starpoint));}
		}
	}
	void createAnimals(){
		for( AnimalV animal : animals){
			bg.add(animal);
		}
	}
	
	void setDirection(){
		for (int i=0; i<animals.size() ; i++){
			animals.get(i).changedirection(suru.Boids.get(i).direction);
		}
	}
	
	int gravity=5;
	void rain(){
		
		for( Animal boid : suru.Boids){
			if(boid.Position.y< screenSize.height-450){boid.Position.y+=gravity; gravity+=2;}
			
		}
		
	}
	
	void moveSwarm(){suru.MoveBoids();}
	void moveAnimalVs(){
		for( int i=0; i< animals.size(); i++){
			animals.get(i).moveto(suru.Boids.get(i).Position.x, suru.Boids.get(i).Position.y);
		}
	}
	public static void main(String args []){mainFrame a=new mainFrame();a.repaint();}
}
