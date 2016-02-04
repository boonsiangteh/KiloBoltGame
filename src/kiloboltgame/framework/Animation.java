package kiloboltgame.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	  private ArrayList frames; //create variale frames of type ArrayList!!
	  private int currentFrame;
	  private long animTime; //long takes up more memory than int but can hold more accurate numbers.
	  private long totalDuration;
	   
	public Animation() {
		
		frames = new ArrayList();//create an ArrayList object call it frames as declared in the variables! 
		totalDuration = 0; 
		
		synchronized(this){
			animTime=0; 
			currentFrame=0; 
		}
	}
	
	//create an AnimFrame class within a class (Nested Class!!)
	// this class allows AnimFrame objects to be created !
	private class AnimFrame {

		   Image image;
		   long endTime;

		   public AnimFrame(Image image, long endTime) {
		      this.image = image;
		      this.endTime = endTime;
		   }
		}

	
	//add frames to our "frames" ArrayList!!!
	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		//adds new Image OBJECTS to our ArrayList
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	//update our frame !!
	public synchronized void update (long elapsedTime){
		if(frames.size() > 1){
			animTime += elapsedTime; 
			if(animTime>= totalDuration){
				animTime = animTime % totalDuration; 
				currentFrame= 0; 
			}
			
			while (animTime > getFrame(currentFrame).endTime) {
				currentFrame++;

			}	
		}
	}
	
	//returns the ID of the image of the current frame !!
	public synchronized Image getImage(){
		if (frames.size() ==0){
			//return nothing if we have no images in our ArrayList
			return null; 
		} else {
			//return the ID of the image if we have something!!
			return getFrame(currentFrame).image; 
		}
	}
	
	//return the current AnimFrame of our animation sequence!
	private AnimFrame getFrame(int i){
		return (AnimFrame)frames.get(i);
	}
	
	
}
