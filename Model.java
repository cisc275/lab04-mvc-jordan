import java.awt.Color;

/**
 * Model: Contains all the state and logic
 * Does not contain anything about images or graphics, must ask view for that
 *
 * has methods to
 *  detect collision with boundaries
 * decide next direction
 * provide direction
 * provide location
 **/
public class Model {
	
	private int x;
	private int y;
	private int direct;
	
	private int width;
	private int height;
	private int imageWidth;
	private int imageHeight;
	
    final int xIncr = 8;
    final int yIncr = 2;

	public Model(int width, int height, int imageWidth, int imageHeight) {
		this.width = width;
		this.height = height;
		this.imageHeight = imageHeight;
		this.imageWidth = imageWidth;
		direct = 3;
	}

	public void updateLocationAndDirection() {
		boolean rightOob = x >= width - imageWidth;
    	boolean leftOob = x <= 0;
    	boolean topOob = y <= 0;
    	boolean bottomOob = y >= height - imageHeight;
    	
		switch(direct) {
			case 0:
				y-=yIncr;
				break;
			case 1:
				x+=xIncr;
				y-=yIncr;
				break;
			case 2:
				x+=xIncr;
				break;
			case 3:
				x+=xIncr;
				y+=yIncr;
				break;
			case 4:
				y+=yIncr;
				break;
			case 5:
				x-=xIncr;
				y+=yIncr;
				break;
			case 6:
				x-=xIncr;
				break;
			case 7:
				x-=xIncr;
				y-=yIncr;
				break;
		}
	
		
	// depending on which boundary hit and the current direction the orc is looking, the
	// orc will change direction
		if(rightOob) {
			System.out.println("Out of bounds: RIGHT!!!");     		//debug
			switch(direct) {
			case 3:
				direct = 5;
				break;
			case 1:
				direct = 7;
				break;
			case 2:
				direct = 6;
				break;
			} 
		} else if(leftOob) {
			System.out.println("Out of bounds: LEFT!!!");    		//debug
			switch(direct) {
			case 5:
				direct = 3;
				break;
			case 7:
				direct = 1;
				break;
			case 6:
				direct = 2;
				break;
			}
		} else if(topOob) {
			System.out.println("Out of bounds: TOP!!!");    		//debug
			switch(direct) {
			case 7:
				direct = 5;
				break;
			case 1:
				direct = 3;
				break;
			case 0:
				direct = 4;
				break;
			}
		} else if(bottomOob) {
			System.out.println("Out of bounds: BOTTOM!!!");    		//debug
			switch(direct) {
			case 5:
				direct = 7;
				break;
			case 3:
				direct = 1;
				break;
			case 4:
				direct = 0;
				break;
			}
		} else {
			System.out.println("In Bounds :)");    		//debug
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getDirect() {
		return direct;
	}
	
}