import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Tile {
	
	private int x;
	private int y;
	private Player p;
	
	private int width,height;
	
	private TileHandler handler;

	Random ran = new Random();
	
	Color color;
	
	private int z;
	
	public Tile(int x, int y, int width, int height, Player p, TileHandler handler) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.handler = handler;
		this.width = width;
		this.height = height;
		
		z = ran.nextInt(4);
		//Generate z with perlin noise
		
		color = new Color(ran.nextInt(255), ran.nextInt(255), ran.nextInt(255));
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
		if(z == 0) g.setColor(Color.gray);
		if(z == 1) g.setColor(Color.GREEN);
		if(z == 2) g.setColor(Color.cyan);
		if(z == 3) g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
	
	
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
}
