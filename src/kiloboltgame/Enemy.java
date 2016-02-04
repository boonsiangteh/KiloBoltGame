package kiloboltgame;

import java.awt.Rectangle;

//this enemy class defines the enemy in the game in terms of state and behavior
//this is the parent class of all enemy characters in the game!!
public class Enemy {

	//enemy will have 6 things here
	//1) max health
	//2) current health
	//3) damage output
	//4) position (in x and y coordinate)
	//	a) x coordinate position
	//	b) y coordinate position
	//5) speed at which enemy moves !!
	//note: enemy shall move with the background when background is scrolling!!
	//need to call bg1 here !! 
	
	private int power, speedX, centerX, centerY;
	//initialize health to initial value of 5
	public int health =5; 
	private Background bg = StartingClass.getBg1();
	public Rectangle r = new Rectangle(0,0,0,0);
	//create variable for movement speed of enemy(trying to make an A.I. here)
	private int movementSpeed;
	private Robot robot = StartingClass.getRobot(); //get the robot from StartingClass
	
	//create method to update position of enemy such as x and y coordinate!!
	public void update(){
		//create enemies that follow the main character
		follow();
		centerX += speedX; 
		//speed of our enemy is now faster than speed of background and its own movement speed (think relative speed!)
		speedX = bg.getSpeedX()*5 + movementSpeed;  
		r.setBounds(centerX - 25, centerY-25, 50, 60);
		
		if (r.intersects(Robot.yellowRed)){
			checkCollision();
		}
	}
	
	private void follow() {
		//this if statement stops enemies form following robot if it is too far ahead or too far behind
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}
		//tell enemies to stop once they catch up to robot (within 5 pixels of distance !!)
		else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {
			//this is the code that makes the enemies follow the robot !!
			if (robot.getCenterX() >= centerX) {
				//heliboys move forward if robot is ahead
				movementSpeed = 1;
			} else {
				//move backwards if robot is behind 
				movementSpeed = -1;
			}
		}
		
	}

	//check for collision of robot with enemy !!
	private void checkCollision() {
		//if condition says that if any of the bounding boxes of the robot collides with enemy!
		if (r.intersects(Robot.rect) || r.intersects(Robot.rect2) || r.intersects(Robot.rect3) || r.intersects(Robot.rect4)){
			System.out.println("collision");
			
			}
	}

	//create method to give enemy an attack behavior
	public void attack(){}
	
	//create die to give enemy a die behavior
	public void die(){}

	//generate getter and setter methods to get health, power, speed and position of enemy and modify them !!
	

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}
	
	
}
