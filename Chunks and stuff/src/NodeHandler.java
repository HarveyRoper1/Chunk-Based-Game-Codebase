import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class NodeHandler {

	private LinkedList<Node> nodes = new LinkedList<>();
	
	Random ran = new Random();
	
	Player p;
	
	public NodeHandler(Player p, int nodeNum) {
		this.p = p;
		
		for(int i = 0 ; i < nodeNum ; i++) {
			nodes.add(new Node(p.getX() + ran.nextInt(1600)-800, p.getY() + ran.nextInt(1600)-800, p));
		}
	}
	
	public void tick() {
		for(int i = 0 ; i < nodes.size() ; i++) {
			nodes.get(i).tick();
		}
		
		removeObsoleteNodes();
		nodesInRange();
		getNearest();
	}
	
	
	public void removeObsoleteNodes() {
		for(int i = 0 ; i < nodes.size() ; i++) {
			if(pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY()) > 1000) {
				nodes.remove(nodes.get(i));
			}
		}
	}
	
	public void nodesInRange() {
		int local_nodesInRange = 0;
		
		for(int i = 0 ; i < nodes.size(); i++) {
			if(pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY()) < 600){
				local_nodesInRange++;
			}
		}
		
		for(int i = 0 ; i < 20-local_nodesInRange; i++) {
			
			if(p.getVelX() < 0) {
				nodes.add(new Node(p.getX() - ran.nextInt(800), p.getY() + ran.nextInt(1600)-800, p));
			}
			if(p.getVelX() > 0) {
				nodes.add(new Node(p.getX() + ran.nextInt(800), p.getY() + ran.nextInt(1600)-800, p));
			}
			if(p.getVelY() < 0) {
				nodes.add(new Node(p.getX() + ran.nextInt(1600)-800, p.getY() - ran.nextInt(800), p));
			}
			if(p.getVelY() > 0) {
				nodes.add(new Node(p.getX() + ran.nextInt(1600)-800, p.getY() + ran.nextInt(800), p));
			}
			
		}
		
	}
	
	public void getNearest() {
		int smallest = 100000000;
		for(int i = 0 ; i < nodes.size() ; i++) {
			if(pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY()) < smallest) {
				smallest = pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY());
			}
		}
		for(int i = 0 ; i < nodes.size() ; i++) {
			if(pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY()) == smallest) {
				nodes.get(i).setSmallest(true);
			}
			else {
				nodes.get(i).setSmallest(false);
			}
		}
	}

	public void render(Graphics g) {
		
		for(int i = 0 ; i < nodes.size() ; i++) {
			if(pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY()) < 810) nodes.get(i).render(g);
			if(pythagoras(nodes.get(i).getX(), p.getX(), nodes.get(i).getY(), p.getY()) < 400) {
				if(nodes.get(i).isSmallest()) {
					g.setColor(Color.green);
					g.drawLine(nodes.get(i).getX()+5, nodes.get(i).getY()+5, p.getX()+16, p.getY()+16);
				}
				else {
					g.setColor(Color.white);
					g.drawLine(nodes.get(i).getX()+5, nodes.get(i).getY()+5, p.getX()+16, p.getY()+16);
				}
			}
		}
		
	}
	
	public int pythagoras(int x1, int x2, int y1, int y2) {
		return (int) Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	
}
