
public class Camera {

	private float x,y;
	
	
	public Camera(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public void tick(Player player) {
		x = -player.getX() + Main.WIDTH/2 - 16;
		y = -player.getY() + Main.HEIGHT/2 - 16;
		
	}
	
	
	public void setX(float x) {
		this.x = x;
	}
	
	public void setY(float y) {
		this.y = y;
	}
	
	
	public float getX() {
		return x;
	}
	
	
	public float getY() {
		return y;
	}
	
}
