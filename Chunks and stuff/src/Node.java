import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Node {

	private int x;
	private int y;
	
	private int velX = 1;
	private int velY = 1;
	
	private boolean smallest = false;
	
	private Player p;
	
	Random r = new Random();

	public Node(int x, int y, Player p) {
		this.p = p;
		this.x = x;
		this.y = y;
	}
	public void tick() {
		//double angle = Math.atan2((double)p.getY() - y,(double)p.getX() - x);
	    //x += r.nextInt(6)-3;
	   //y += r.nextInt(6)-3;
	}
	
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(x, y, 10, 10);
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
	
	public boolean isSmallest() {
		return smallest;
	}
	public void setSmallest(boolean smallest) {
		this.smallest = smallest;
	}
	
	
}
