import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;

public class Player{

	private int width=32, height=32;
	
	private int x;
	private int y;
	private int velX;
	private int velY;
	

	private KeyInput input;
	private Camera camera;
	
	private Chunk currentChunk;
	
	public Player(int x, int y, KeyInput input, Camera camera) {
		this.x = x;
		this.y = y;
		this.input = input;

		this.camera = camera;
	
		
	}
	
	public void tick() {
	
		x += velX;
		y += velY;
		keyHandler();
		
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.blue);
		g.fillOval(x, y, width, height);
		g2d.draw(getBounds());
		
		
	  
	}

	
	public void keyHandler() {
		if(input.keys[0]) velX = 6;
        else if(input.keys[1]) velX  = -6;
        else if(!input.keys[0] && !input.keys[1]) {
            if(velX > 0 ) velX = 0;
            else if(velX < 0) velX = 0;
        }
		
		
		if(input.keys[2]) velY = -6;
		else if(input.keys[3]) velY = 6;
		else if(!input.keys[2] && !input.keys[3]) {
			velY = 0;
		}
		
	}
	public void setCurrentChunk(Chunk currentChunk) {
		this.currentChunk = currentChunk;
	}
	
	public Chunk getCurrentChunk() {
		return currentChunk;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(x,y,width,height);
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}
	
	}

