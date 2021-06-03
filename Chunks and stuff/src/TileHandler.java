import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class TileHandler {

	private LinkedList<Tile> tiles = new LinkedList<>();
	private Player p;
	Random ran = new Random();
	
	private int density;
	
	
	
	public TileHandler(Player p, int startx, int starty, Chunk parentChunk) {
		this.p = p;
		this.density = density;
		
		for(int i = 0 ; i < (parentChunk.getWidth()+1)/8; i+=10) {
			for(int j = 0 ; j < (parentChunk.getHeight()+1)/8; j+=10) {
				tiles.add(new Tile(parentChunk.getX()+(i*8),parentChunk.getY()+(j*8),80,80,p,this));
			}
		}
	}
	
	public void tick() {
		for(Tile t : tiles) {
			t.tick();
		}
	}
	
	
	public int getDensity() {
		return density;
	}

	public void setDensity(int density) {
		this.density = density;
	}

	public void render(Graphics g) {
		for(int i = 0 ; i < tiles.size() ; i++) {
			tiles.get(i).render(g);
		}
	}
		
	
	public int pythagoras(int x1, int x2, int y1, int y2) {
		return (int) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	
}
