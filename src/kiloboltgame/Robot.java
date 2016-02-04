package kiloboltgame;
import java.awt.Rectangle;
import java.util.ArrayList; 

public class Robot {
	
	// Constants are Here
    final int JUMPSPEED = -15;
    final int MOVESPEED = 5;
    //final int GROUND = 382;
	
	//make Class Variables private so that only its methods can change them.
	//we do not want other classes to change these variables !!
		//define center of robot's body here
		private int centerX = 100;//initial X position of center of robot
		private int centerY = 377; // initial Y position of center of Robot
		private boolean jumped = false;//keep track of jumping 
		private boolean movingLeft = false;
		private boolean movingRight = false;
		private boolean ducked = false;
		private boolean readyToFire = true;

		//reset values of bg1 and bg2
		private static Background bg1 = StartingClass.getBg1();                 
        private static Background bg2 = StartingClass.getBg2();
        
		//create variables to keep track of speed !
		private int speedX = 0;
		private int speedY = 0;
		public static Rectangle rect = new Rectangle(0,0,0,0);//rectangle bounding upper body of robot 
		public static Rectangle rect2 = new Rectangle(0,0,0,0);//rectable bounding lower body of robot
		public static Rectangle rect3 = new Rectangle(0, 0, 0, 0);//rectangle bounding left hand of robot
		public static Rectangle rect4 = new Rectangle(0, 0, 0, 0);//rectangle boudning rght hand of robot
		public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);//rectangle checking for tiles immediately around robot
		public static Rectangle footleft = new Rectangle(0,0,0,0);
		public static Rectangle footright = new Rectangle(0,0,0,0);
		
		//create an array list to store our Projectiles objects !!
		private ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); 
		
		//create a method to update position of robot !
		public void update(){
			
			//Moves character or scroll the background accordingly
			if (speedX < 0) {
				centerX += speedX;
			}  
			//moving to the left or stopping
			if (speedX == 0 || speedX <0) {
				//if character is not moving, we don't scroll the background !!
				//System.out.println("Do not scroll the background.");
				bg1.setSpeedX(0);//set speed to zero to stop scrolling background
				bg2.setSpeedX(0);

			} 
			//If the character's centerX is in the left 200 pixels and moving to the right
			if (centerX <= 200 && speedX >0) { 
				centerX += speedX;//Change centerX by adding speedX.
			} 
			
			//moving right and position is more than 200px, 
			//set background speed in negative direction to scroll background!!
			//this means our character is moving and we need to start scrolling background! 
			if(speedX >0 && centerX > 200){
				System.out.println("Scroll Background Here");
				bg1.setSpeedX(-MOVESPEED/5);
				bg2.setSpeedX(-MOVESPEED/5);
			}
			
			//UPdates Y position
			centerY += speedY;
			
			//Handles Jumping
			speedY += 1; 
			
			//this if statement prevents fluctuations in speedY from being registered as jump  
			if (speedY > 3){
				jumped = true;
			}
			
						
			// Prevents going beyond X coordinate of 0
			if (centerX + speedX <= 60) {
				centerX = 61;
			}
		
			rect.setRect(centerX - 34, centerY - 63	, 68, 63);
			rect2.setRect(rect.getX(), rect.getY() + 63, 68, 64);
			rect3.setRect(rect.getX() - 26, rect.getY()+32, 26, 20);
			rect4.setRect(rect.getX() + 68, rect.getY()+32, 26, 20);
			yellowRed.setRect(centerX - 110, centerY - 110, 180, 180);
			
			footleft.setRect(centerX - 50, centerY + 20, 50, 15);
			footright.setRect(centerX, centerY + 20, 50, 15);
		}
		
		//now we define out robot movement !! move right, left and stop !
		public void moveRight() {
			//if we are not crouching or ducking, we can move right at movespeed!! 
			if (ducked == false){
				speedX = MOVESPEED; 
			}
		}

		public void moveLeft() {
			if (ducked == false){
				speedX = -MOVESPEED;
			}
		}

		//set stop moving right and left methods!!
		public void stopRight() {
			setMovingRight(false);
			stop(); 
		}
		
		public void stopLeft(){
			setMovingLeft(false);
			stop(); 
		}
		
		// set our stop method !!
		private void stop(){
			//if character is not moving at all
			if (isMovingRight() == false && isMovingLeft()== false){
				speedX = 0; 
			}
			//if only left button is pressed
			if (isMovingRight() == false && isMovingLeft()== true){
				moveLeft();  
			}
			//if only right button is pressed 
			if (isMovingRight() ==true && isMovingLeft()== false){
				moveRight();  
			}
		}
		
		
		public void jump() {
			if (jumped == false) {
				speedY = JUMPSPEED;
				jumped = true;
			}
		}
		
		//add the shoot method here !!
		public void shoot(){
			if(readyToFire){
				Projectile p = new Projectile(centerX + 50, centerY - 25);
				projectiles.add(p);
			}
		}
		
		//create getter method for projectiles so that other classes can access this projectile!!
		public ArrayList<Projectile> getProjectiles() {
			return projectiles;
		}
		
		//create helper methods to help retrieve and change value of 
		//variables for other classes because we made them private here !!
		//all the following methods are getters and setter methods
		public int getCenterX() {
			return centerX;
		}

		public int getCenterY() {
			return centerY;
		}

		public boolean isJumped() {
			return jumped;
		}

		public int getSpeedX() {
			return speedX;
		}

		public int getSpeedY() {
			return speedY;
		}

		public void setCenterX(int centerX) {
			this.centerX = centerX;
		}

		public void setCenterY(int centerY) {
			this.centerY = centerY;
		}

		public void setJumped(boolean jumped) {
			this.jumped = jumped;
		}

		public void setSpeedX(int speedX) {
			this.speedX = speedX;
		}

		public void setSpeedY(int speedY) {
			this.speedY = speedY;
		}
		
		//boolean methods are here to keep track of movement! 
		public boolean isDucked() {
	        return ducked;
	    }

	    public void setDucked(boolean ducked) {
	        this.ducked = ducked;
	    }
	    
	    //boolean methods !! default value is false !!
	    public boolean isMovingRight() {
	        return movingRight;
	    }

	    public void setMovingRight(boolean movingRight) {
	        this.movingRight = movingRight;
	    }

	    public boolean isMovingLeft() {
	        return movingLeft;
	    }

	    public void setMovingLeft(boolean movingLeft) {
	        this.movingLeft = movingLeft;
	    }
	    
	    public boolean isReadyToFire() {
			return readyToFire;
		}

		public void setReadyToFire(boolean readyToFire) {
			this.readyToFire = readyToFire;
		}
}
