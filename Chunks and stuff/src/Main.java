import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
 
public class Main extends Canvas implements Runnable, KeyListener{
 
    public static int WIDTH = 800;//1920;
    public static int HEIGHT = 800;//1080;
    public String title = "...";
   
    private Thread thread;
   
    private boolean isRunning = false;
   
    //Instances

    private Player player;
    private KeyInput input;
    private Camera cam;
    private NodeHandler nodeHandler;
    //private TileHandler tileHandler;
    private ChunkHandler chunkHandler;
    
    public Main() {
       
        init();
        new Window(WIDTH, HEIGHT, title, this);
        start();
        
        
    }
   
    public void init() {
    	input = new KeyInput();
    	this.addKeyListener(input);
    	
    	player = new Player(400,400, input, cam);
    	cam = new Camera(player.getX(), player.getY());
    	player.setCamera(cam);
    	
    	nodeHandler = new NodeHandler(player, 20);
    	//tileHandler = new TileHandler(player, 8);
    	chunkHandler = new ChunkHandler(player);
    }
   
    private synchronized void start() {
    	
        if(isRunning) return;
       
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }
   
    private synchronized void stop() {
        if(!isRunning) return;
       
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        isRunning = false;
    }
   
    //gameloop
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int updates = 0;
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
                   
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames + " TICKS: " + updates);
                frames = 0;
                updates = 0;
            }
        }
        stop();
    }
 
    public void tick() {
    
    	player.tick();
    	cam.tick(player);
    	nodeHandler.tick();
    	//tileHandler.tick();
    	chunkHandler.tick();
    }
   
    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
       
        ///
        
	    g.setColor(Color.black);
	    g.fillRect(0, 0, WIDTH, HEIGHT);
	                
	        g2d.translate(cam.getX(), cam.getY()); //Begin of camera
	        
	        	nodeHandler.render(g);
	        	//tileHandler.render(g);
	        	chunkHandler.render(g);
	        	player.render(g);
	        
	        g2d.translate(-cam.getX(), -cam.getY()); //end of camera
	        
	       
        
        ///
        bs.show();
        g.dispose();
       
    }
   
    public static void main(String[] args) {
        new Main();
        
    }
 
    public void keyPressed(KeyEvent arg0) {
       
    }
 
    public void keyReleased(KeyEvent e) {
       
    }
 
    public void keyTyped(KeyEvent e) {
       
    }
   
}