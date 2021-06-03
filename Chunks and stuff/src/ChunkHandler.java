import java.awt.Graphics;
import java.util.LinkedList;

public class ChunkHandler {

	private LinkedList<Chunk> chunks = new LinkedList<>();
	
	private Player p;
	
	private boolean pInChunk;
	
	public ChunkHandler(Player p) {
		this.p = p;
	}
	
	public void tick() {
		pInChunk = false;
		for(Chunk c : chunks) {
			c.tick();
		}
		
		if(chunks.size() == 0) {
			createChunk(p.getX()-400, p.getY()-400);
		}
		
		for(Chunk c : chunks) {
			if(p.getBounds().intersects(c.getBounds())) {
				pInChunk = true;
				p.setCurrentChunk(c);
			}
		}
		
		if(!pInChunk) {
			if(p.getVelX() < 0) createChunk(p.getCurrentChunk().getX()-p.getCurrentChunk().getRawLength(), p.getCurrentChunk().getY());
			if(p.getVelX() > 0) createChunk(p.getCurrentChunk().getX()+p.getCurrentChunk().getRawLength(), p.getCurrentChunk().getY());
			if(p.getVelY() < 0) createChunk(p.getCurrentChunk().getX(), p.getCurrentChunk().getY()-p.getCurrentChunk().getRawLength());
			if(p.getVelY() > 0)	createChunk(p.getCurrentChunk().getX(), p.getCurrentChunk().getY()+p.getCurrentChunk().getRawLength());
		}
	}
	
	public void render(Graphics g) {
		for(Chunk c : chunks) {
			c.render(g);
		}
	}
	
	public void createChunk(int x, int y) {
		chunks.add(new Chunk(p, x, y, 800 , 800));
	}
	
}
