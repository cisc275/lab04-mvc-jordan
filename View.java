import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View: Contains everything about graphics and images
 * Know size of world, which images to load etc
 *
 * has methods to
 * provide boundaries
 * use proper images for direction
 * load images for all direction (an image should only be loaded once!!! why?)
 **/
public class View extends JPanel {
	
	private final static int width = 500;
	private final static int height = 300;
	private final int imageWidth = 165;
	private final int imageHeight = 165;
	static JFrame frame = new JFrame();
	
	private final int frameCount = 10;
	private int picNum = 0;
	private BufferedImage[][] pics;
	
	private int x;
	private int y;
	private int direct = 3;
	
	public View() {
		setPics();
	}

	// MAIN 
	// set up frame and initialize controller and start
	public static void main(String[] args) {
		//JFrame frame = new JFrame();
    	frame.getContentPane().add(new View()); //
    	frame.setBackground(Color.gray);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setSize(width, height);
    	frame.setVisible(true);
    	
		Controller c = new Controller();
		c.start();
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getImageWidth() {
		return imageWidth;
	}

	public int getImageHeight() {
		return imageHeight;
	}
	
	//Read image from file and return
    private BufferedImage createImage(String s){
    	BufferedImage bufferedImage;
    	try {
    		bufferedImage = ImageIO.read(new File(s));
    		return bufferedImage;
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	return null;
    	
    	// TODO: Change this method so you can load other orc animation bitmaps (pictures)
    }
    
    // loads orc pictures and frames into 2d array ready to be loaded
    private void setPics() {
    	String[] imgs = new String[8];
    	imgs[0] = "orc_forward_north.png";
    	imgs[1] = "orc_forward_northeast.png";
    	imgs[2] = "orc_forward_east.png";
    	imgs[3] = "orc_forward_southeast.png";
    	imgs[4] = "orc_forward_south.png";
    	imgs[5] = "orc_forward_southwest.png";
    	imgs[6] = "orc_forward_west.png";
    	imgs[7] = "orc_forward_northwest.png";
    	
    	pics = new BufferedImage[imgs.length][frameCount];
    		
    	for(int i = 0; i < imgs.length; i++) {
   			BufferedImage img = createImage(imgs[i]);
    		for(int j = 0; j < frameCount; j++) {
    			pics[i][j] = img.getSubimage(imageWidth*j, 0, imageWidth, imageHeight);
   			}
   		}
    	
    	// TODO: Change this constructor so that at least eight orc animation pngs are loaded
    }  

    // draws new image 
    @Override
    public void paint(Graphics g) { 
    	picNum = (picNum + 1) % frameCount;
    	BufferedImage cImg = pics[direct][picNum];
    	
    	System.out.println("paint, direct: " + direct + ", picNum: " + picNum);
    	g.drawImage(cImg, x, y, Color.gray, this);
    }
    
    // updates the view given current image position and direction
	public void update(int x, int y, int direct) {
		//System.out.println("* " + this.x);
		this.x = x;		
		//System.out.println("** " + this.x);
		this.y = y;
		this.direct = direct;
		System.out.println("update, x: " + x + " y: " + y + " direct: " + direct);
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*
		picNum = (picNum + 1) % frameCount;
    	BufferedImage cImg = pics[direct][picNum];
    	
    	//System.out.println("paint, direct: " + direct + ", picNum: " + picNum);
    	Graphics g = new Graphics();
    	g.drawImage(cImg, x, y, Color.gray, this);
    	*/
		
    	frame.repaint();
	}
	
}