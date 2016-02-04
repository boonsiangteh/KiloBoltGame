package kiloboltgame;

//child class of enemy !!
public class Heliboy extends Enemy {

	//constructor inherited from parent class!!
	//we only need x and y position of Heliboy to insert into game!!  
	public Heliboy(int centerX, int centerY ) {
		setCenterX(centerX);
		setCenterY(centerY);
	}

}
