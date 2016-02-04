package kiloboltgame;

import java.awt.Rectangle;

public class Projectile {

	private int x,y, speedX; 
	private boolean visible; 
	private Rectangle r; 
	
	public Projectile(int startX, int startY) {
		x = startX;
		y = startY;
		speedX = 7;
		visible = true;
		r = new Rectangle(0, 0, 0, 0);
	}

	public void update(){
		x+= speedX; 
		//set bound for bullets according to their current x and y coordinate 
		r.setBounds(x, y, 10, 5);
		if(x > 800){
			visible = false; 
			r= null; //make the bullets disappear if out of page
		}
		if (x < 800){
			//check for collision when bullets are inside the window!!
			checkCollision(); 
		}
	}

	private void checkCollision() {
		//if bullets collide with first heliboy rectangle
		if(r.intersects(StartingClass.hb.r)){
			//set visibility of rectangle to false (hide the bullet!)
			visible = false; 
			//deal damage to the heliboy 1 if bullets hit the enemy!!
			if (StartingClass.hb.health > 0) {
				StartingClass.hb.health -= 1;
			}
			if (StartingClass.hb.health == 0) {
				StartingClass.hb.setCenterX(-100);
				StartingClass.score += 5;
			}  
		}
		//check if bullet collides with heliboy 2 rectangle
		if (r.intersects(StartingClass.hb2.r)){
			visible = false;
			//deal damage to heliboy 2
			if (StartingClass.hb2.health > 0) {
				StartingClass.hb2.health -= 1;
			}
			if (StartingClass.hb2.health == 0) {
				StartingClass.hb2.setCenterX(-100);
				StartingClass.score += 5;
			}

		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	

}
