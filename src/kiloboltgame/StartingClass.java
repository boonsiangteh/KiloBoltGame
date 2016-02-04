package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

//here, we import our own framework just like we did from java libraries !!
import kiloboltgame.framework.Animation;

public class StartingClass extends Applet implements Runnable, KeyListener {

	//some comments by tutor
//	As a general rule:
//	Whenever we add a new object to our game, we have to do the following:
//	0. Create a class for it so that we have a blueprint for it. (We did this for our background).
//	1. Within the StartingClass, we must create objects using this class (first by declaring 
	//them as variables below the class declaration and second by assigning them values in the start() method).
//	2. Within the run() method, we must call the object's update() method.
//	3. We must paint the new object in the paint() method.
//	
	
	private static Robot robot;
	public static Heliboy hb,hb2; 
	public static int score = 0;
    private Font font = new Font(null, Font.BOLD, 30);//create our font and set it to bold with size 30

	private Image image, 
				  character,
				  character2, 
				  character3,
				  currentSprite,
				  characterDown,
				  characterJumped, 
				  background,
				  heliboy,
				  heliboy2,
				  heliboy3,
				  heliboy4,
				  heliboy5;
	
	 public static Image tilegrassTop, tilegrassBot, tilegrassLeft, tilegrassRight, tiledirt;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	
	//we want to create 2 objects: Anim for main character and hanim for heliboy 
	private Animation anim, hanim;  
	private URL base; 
	private Graphics second;  
	private static Background bg1,bg2; 
	//define bg2 to ensure continuation of background!! 
	//sort of like a second background painting to ensure no black background!!
	
	//add enum type variables cause we want to represent these as constants!!
	//2 states to our character!! (Running / dead)
	enum GameState {
		Running, Dead
	}
	
	//set initial state of our game to Running 
	GameState state = GameState.Running;

	
	@Override
	public void init() {
		//set the size and background color of the screen!
		setSize(800,480);
		setBackground(Color.BLACK);
		addKeyListener(this);
		//This statement makes sure that when the game starts, 
		//the applet takes focus and that our input goes directly into it. 
		//If this is not enabled, then you would have to click inside the 
		//applet before it starts handling keyboard events.
		setFocusable(true);
		//import Frame to create a Frame object called frame
		//assigns the applet window to the frame variable
		Frame frame = (Frame)this.getParent().getParent();
		frame.setTitle("Q-bot Alpha");
		
		try {
			base = getDocumentBase(); 
		}catch (Exception e){
			// TODO: handle exception
		}
		
		// Image Setups (specify where we get the image )
		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");
		
		characterDown = getImage(base, "data/characterDown.png");
		characterJumped = getImage(base, "data/characterJumped.png");
		
		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");

		background = getImage(base, "data/background.png");
		tiledirt = getImage(base, "data/tiledirt.png");
        tilegrassTop = getImage(base, "data/tilegrasstop.png");
        tilegrassBot = getImage(base, "data/tilegrassbot.png");
        tilegrassLeft = getImage(base, "data/tilegrassleft.png");
        tilegrassRight = getImage(base, "data/tilegrassright.png");
		
		//create our main character animation images and assign duration of display!!
		anim = new Animation(); 
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);
		
		//create our heliboy animation images and assign duration of display !!
		hanim = new Animation(); 
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);
		
		currentSprite = anim.getImage(); 
	};

	@Override
	public void start() {
		//create 2 new background objectS
		//initialize background variables and assign their positions when we start the program!
		bg1 = new Background(0,0);
		bg2 = new Background(2160, 0);
		//initialize Robot object called robot!
		robot = new Robot();
		//we are now creating 2 heliboy enemies!! 
		try {
            loadMap("data/map1.txt");//read our map file here!!
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		hb = new Heliboy(340,360); 
		hb2 = new Heliboy(700,360); 		 
		//create  new thread for StartingClass 
		Thread thread = new Thread(this); 
		thread.start();
		
		//initialize tiles
		
		
	};

	private void loadMap(String filename) throws IOException {
		 ArrayList lines = new ArrayList();
	        int width = 0;
	        int height = 0;
	        
	      //use a filereader to read the file form filename and use bufferedreader to 
	        BufferedReader reader = new BufferedReader(new FileReader(filename));
	        while (true) {
	            String line = reader.readLine();
	            // no more lines to read
	            if (line == null) {
	                reader.close();
	                break;
	            }

	            if (!line.startsWith("!")) {//check for lines in the text file that starts with "!"
	                lines.add(line);//if line starts with "!", add line !!
	                width = Math.max(width, line.length());
	            }
	        }
	        height = lines.size();
	        for (int j = 0; j < 12; j++) {
	            String line = (String) lines.get(j);
	            for (int i = 0; i < width; i++) {
	                
	                if (i < line.length()) {
	                    char ch = line.charAt(i);
	                    Tile t = new Tile(i, j, Character.getNumericValue(ch));
	                    tilearray.add(t);
	                }

	            }
	        }
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	};
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	};
	
	
	// this run method extracts the run method from the thread we created !!
	@Override
	public void run() {
		//if the game is still running(i.e robot is not dead, continue running the game), we update our robot
		if(state == GameState.Running){
		//create a game loop here 
			while (true){
				robot.update();
				//this if else statement allows us to display 
				//out robot character accordingly ie jump, ducked or character 
				//based on the current state of our robot !!
				if(robot.isJumped()){
					currentSprite = characterJumped; 
				} else if (robot.isJumped()==false && robot.isDucked()==false){
					//set our currentSprite to current image of our animation sequence!
					currentSprite = anim.getImage(); 
				}
				
				//create a new ArrayList called projectiles variable and set to 
				ArrayList projectiles = robot.getProjectiles(); 
				for(int i=0; i< projectiles.size(); i++){
					Projectile p = (Projectile)projectiles.get(i); 
					if(p.isVisible()==true){
						p.update();
					}else {
						projectiles.remove(i); 
					}
				}
				
				updateTiles(); 
				hb.update();
				hb2.update();
				bg1.update();
				bg2.update();
				animate(); 
				repaint(); 
				
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//stop running the game if the character falls through the gap and dies.   
				if (robot.getCenterY() > 500) {
					state = GameState.Dead;
				}
			}
		}	
	}
	
	public void animate() {
		// TODO Auto-generated method stub
		anim.update(10);
		hanim.update(50);
	}

	@Override
	 public void update(Graphics g) {
		if (image == null) {
			image = createImage(getWidth(),getHeight());
			second = image.getGraphics();
		}
		
		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
		
	};
	
	//this functions serves to paint the applet window !! i.e character and background !
	public void paint(Graphics g) {
		
		if(state == GameState.Running){
		
			//order of lines matter here ! draw background first before character so that character appers on top of background!
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this); 
			paintTiles(g);
			
			ArrayList projectiles = robot.getProjectiles();
			for (int i = 0; i < projectiles.size(); i++) {
				Projectile p = (Projectile) projectiles.get(i);
				g.setColor(Color.YELLOW);
				g.fillRect(p.getX(), p.getY(), 10, 5);
			}
			//g.drawRect((int)robot.rect.getX(), (int)robot.rect.getY(), (int)robot.rect.getWidth(), (int)robot.rect.getHeight());
			//g.drawRect((int)robot.rect2.getX(), (int)robot.rect2.getY(), (int)robot.rect2.getWidth(), (int)robot.rect2.getHeight());
			g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);
			g.drawImage(hanim.getImage(),hb.getCenterX()-48, hb.getCenterY()-48, this);
			g.drawImage(hanim.getImage(), hb2.getCenterX()-48, hb2.getCenterY()-48, this); 
			
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(Integer.toString(score), 740, 30);
			//set condition if we are dead !!
			//display "dead" with a black background!!
		} else if (state == GameState.Dead) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("Dead", 360, 240);
		}
	};
	
	private void updateTiles() {
		
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}
	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		//check for cases to execute appropriate responses for each button pressed
		//e.getKeyCode() actually returns the code of the button we press
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP: //up arrow key 
			System.out.println("Move up!");
			break; 
		case KeyEvent.VK_DOWN: //down arrow key
			currentSprite = characterDown;
			if(robot.isJumped()==false){
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break; 
		case KeyEvent.VK_LEFT: //left arrow key
			robot.moveLeft();
			robot.setMovingLeft(true);
			break; 
		case KeyEvent.VK_RIGHT: //right arrow key
			robot.moveRight();
			robot.setMovingRight(true);
			break; 
		case KeyEvent.VK_SPACE: //spacebar
			robot.jump();
			break; 
			//use control button to shoot but we only do this if robot is neither crouching nor jumping
		case KeyEvent.VK_CONTROL:
			if(robot.isDucked()== false && robot.isJumped()==false){
				robot.shoot();
				robot.setReadyToFire(false);
			}
			break; 
		}
		
	}

	//do the same and get the code for the button every time we release it!!
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()){
		case KeyEvent.VK_UP: //up arrow key
			System.out.println("Stop moving up!");
			break; 
		case KeyEvent.VK_DOWN: //down arrow key
			currentSprite = anim.getImage(); 
			robot.setDucked(false);
			break; 
		case KeyEvent.VK_LEFT: //left arrow key
			robot.stopLeft();
			break; 
		case KeyEvent.VK_RIGHT: //right arrow key
			robot.stopRight();
			break; 
		case KeyEvent.VK_SPACE: //spacebar
			break;
		case KeyEvent.VK_CONTROL:
			robot.setReadyToFire(true);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static Background getBg1() {
		return bg1;
	}

	public static Background getBg2() {
		return bg2;
	}
	public static Robot getRobot(){
		return robot;
	}

}
