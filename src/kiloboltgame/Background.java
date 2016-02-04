package kiloboltgame;

public class Background {

	private int bgX,bgY,speedX; 
	
	//background constructor 
	public Background(int x, int y){
		bgX = x; //x coordinate of upper left corner
		bgY = y;  //y coordinate of upper left corner
		speedX = 0; //set speedX to 0 because we want to start static
	}
	
	// this statement functions to update our background !!
	public void update(){
		bgX +=speedX; 
		//our resolution is 2160 x 480
		if (bgX<=-2160){// resolution of our output window !!
			bgX += 4320; //4320; 
		}
	}

	public int getBgX() {
		return bgX;
	}

	public int getBgY() {
		return bgY;
	}

	public int getSpeedX() {
		return speedX;
	}

	public void setBgX(int bgX) {
		this.bgX = bgX;
	}

	public void setBgY(int bgY) {
		this.bgY = bgY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}
	
	
	
	
}
