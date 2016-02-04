package kiloboltgame;

import java.awt.Image;
import java.awt.Rectangle;

public class Tile {

	private int tileX, tileY, speedX, type; 
	public Image tileImage;
	private Rectangle r; //bounding rectangle for tile
	private Robot robot = StartingClass.getRobot(); //get the robot from starting class for us to work with here!
	private Background bg = StartingClass.getBg1(); 
			
	public Tile(int x, int y, int typeInt) {

		tileX = x*40; 
		tileY= y*40; 
		type = typeInt;
		r = new Rectangle(); 
		
		if (type == 5) {
            tileImage = StartingClass.tiledirt;
        } else if (type == 8) {
            tileImage = StartingClass.tilegrassTop;
        } else if (type == 4) {
            tileImage = StartingClass.tilegrassLeft;

        } else if (type == 6) {
            tileImage = StartingClass.tilegrassRight;

        } else if (type == 2) {
            tileImage = StartingClass.tilegrassBot;
        }else{
            type = 0;//set empty tiles (tiles without pictures) to type 0
        }

	}
	
	public void update() {
        
			speedX = (bg.getSpeedX())*5;
			tileX += speedX; 
			r.setBounds(tileX, tileY, 40, 40);
	        
			if (r.intersects(Robot.yellowRed) && type != 0) {
				checkVerticalCollision(Robot.rect, Robot.rect2);
				checkSideCollision(Robot.rect3, Robot.rect4, Robot.footleft, Robot.footright);
			}
	        
//        if (type == 1) {//ocean tile 
//            if (bg.getSpeedX() == 0){
//                speedX = -1;//move ocean tile slower when background stops
//            }else{
//                speedX = -2;//move ocean tile faster when background moves
//            }
//
//        } else {
//            speedX = bg.getSpeedX()*5;//dirt tile moves faster 
//        }
//
//        tileX += speedX;
    }
	
	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot){
    	if (rtop.intersects(r)){
    	}
    	
    	if (rbot.intersects(r)&& type ==8){//collision with grasstop!!
    		robot.setJumped(false);//on the ground, robot is not in jumped stated
            robot.setSpeedY(0);//once we hit the ground, no more speed!!
            robot.setCenterY(tileY - 63);//set center of robot to be its normal position on the ground !!
    	}
    }
	
	//check for side collision !!
	public void checkSideCollision(Rectangle rleft, Rectangle rright, Rectangle leftfoot, Rectangle rightfoot) {
        if (type != 5 && type != 2 && type != 0){//if not Tiledirt, grassbot and empty tiles ie grasstop or grass left!!
            if (rleft.intersects(r)) {//intersect with left side rectangle of grass
                robot.setCenterX(tileX + 102);
                robot.setSpeedX(0);
    
            }else if (leftfoot.intersects(r)) {//left foot intersects with rectangle
                robot.setCenterX(tileX + 85);
                robot.setSpeedX(0);
            }
            
            if (rright.intersects(r)) {//right foot intersects with rectangle
                robot.setCenterX(tileX - 62);
    
                robot.setSpeedX(0);
            }
            
            else if (rightfoot.intersects(r)) {
                robot.setCenterX(tileX - 45);
                robot.setSpeedX(0);
            }
        }
    }
	public int getTileX() {
		return tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	
}
