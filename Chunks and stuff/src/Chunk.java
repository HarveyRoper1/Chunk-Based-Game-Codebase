import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Chunk {

	private Player p;
	
	private int x;
	private int y;
	private int width,height;
	
	private TileHandler th;
	private int rawLength;
	
	public Chunk(Player p, int x, int y, int width, int height) {
		this.p = p;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		th = new TileHandler(p, x, y, this);
		
		rawLength = width;
	}
	
	public void tick() {
		th.tick();
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.magenta);
		g2d.draw(getBounds());
		//g.drawRect(x, y, width*th.getDensity(), height*th.getDensity());
		th.render(g);
	}
	
	public TileHandler getTh() {
		return th;
	}

	public void setTh(TileHandler th) {
		this.th = th;
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
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, rawLength, rawLength);
	}

	public int getRawLength() {
		return rawLength;
	}
	
	
	
}
