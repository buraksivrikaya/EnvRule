import java.awt.Point;
import java.awt.Toolkit;
import java.util.List;
import java.util.Random;




public class Animal {
	static float border=100f;
	static float sight=80f;
	static float space=35f;
	float speed=12f;
	
	float boundary;
	float dX;
	float dY=5f;
	public boolean Zombie;
	
	Point Position;
	boolean direction;//right true-left false
	
	static Random rnd=new Random();
	
	
	public Animal(boolean zombie , int boundary){
		
		Position= new Point(rnd.nextInt(boundary), rnd.nextInt(boundary));
		this.boundary=boundary;
		Zombie= zombie;
	}
	
	public Animal(boolean zombie, Point Position){
		this.boundary=Toolkit.getDefaultToolkit().getScreenSize().width;
		this.Position = Position;
		Zombie=zombie;
	}
	
	public void Move(List <Animal> boids){
		
		if (!Zombie) {Flock (boids);}
		else {Hunt(boids);}
		
		CheckBounds();
		CheckSpeed();
		CheckDirection();
		Position.x +=dX;
		//Position.y +=dY;
		
		System.out.println("position: "+Position.x+","+Position.y+"      speed :"+dX+","+dY);
	}
	
	 private void Flock(List<Animal> boids){
		 for (Animal boid : boids){
             float distance = Distance(Position, boid.Position);
             if (boid != this && !boid.Zombie){
                 if (distance < space){
                     // Create space.
                     dX += Position.x - boid.Position.x+5f;
                 }
                 else if (distance < sight){
                     // birlikte uç
                	 dX += (boid.Position.x - Position.x) * /*0.07f*/0.8f;
                 }
                 if (distance < sight){
                     // uçuþu hizala
                     dX += boid.dX * 0.5f;
                 }
             }
             if (boid.Zombie && distance < sight){
                 // Zombiden kaç
                 dX += Position.x - boid.Position.x;
             }
         }
     }

     private void Hunt(List<Animal> boids){
         float range =Float.MAX_VALUE;
         Animal prey = null;
         for (Animal boid : boids){
             if (!boid.Zombie){
                 float distance = Distance(Position, boid.Position);
                 if (distance < sight && distance < range){
                	 range = distance;
                	 prey = boid;
                 }
             }
         }
         if (prey != null){
             // en yakýn sürüye git
             dX += (prey.Position.x - Position.x);
         }
     }
     private static float Distance(Point p1, Point p2){
         double val = Math.pow(p1.x - p2.x, 2) + Math.pow(p1.x - p2.x, 2);
         return (float)Math.sqrt(val);
     }

     
     private void CheckDirection(){
    	 
    	 if (dX==Math.abs(dX)){direction=true;}
    	 else {direction=false;}
    	 
     }

     private void CheckBounds(){

    	 float val = boundary - border;

         if (Position.x < border) dX += border - Position.x;

         if (Position.x > val) dX += val - Position.x;
         
         System.out.println(Position.x +","+ Position.y);
     }

     private void CheckSpeed(){
         float s;
         if (!Zombie) {s = speed;}
         else {s = speed / 3f;}
         float val = Distance(new Point(0, 0), new Point((int)dX, (int)dY));
         if (val > s){

             dX = (dX * s) / val;
         }
     }
	
}

