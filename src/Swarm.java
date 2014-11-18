
import java.util.ArrayList;
import java.util.List;
public class Swarm {

	public List <Animal> Boids= new ArrayList <Animal>();
	
	public Swarm(int boundary){
		for(int i=0; i<0; i++){
			Boids.add(new Animal(i >19, boundary));	
		}
	}
	public void MoveBoids(){
        for (Animal boid : Boids){
            boid.Move(Boids);
        }
    }
	
}
