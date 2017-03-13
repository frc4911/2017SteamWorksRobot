import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

// TODO: clean-up redundant code

public class Box {
		private final int LINE_THICKNESS = 5;
		
		private final int WIDTH_THRES = 6;
		private final int MAX_TRIES = 4;
		
		private int winWidth;
		private int winHeight;
	
		private int topY = -1;
		private int bottomY = -1;
	
		private int[] leftX;
		private int[] rightX;
		
		private int validLines = 0;
		private int currBoxWidth = 0;
		private int widestBoxWidth = 0;
		private int index = 0;
		
		private Color boxColor = Color.MAGENTA;
				
		public Box(int windowWidth, int windowHeight) {
			initializeValues(windowWidth, windowHeight);
		}
		
		// initializes the main values
		public void initializeValues(int windowWidth, int windowHeight) {
			this.winWidth = windowWidth;
			this.winHeight = windowHeight;
			
			leftX = initializeArray(new int[windowWidth]);
			rightX = initializeArray(new int[windowWidth]);
		}
		
		// sets all of the indices of an array to -1
		private int[] initializeArray(int[] numArray) {
			for(int i = 0; i < numArray.length; i++) {
				numArray[i] = -1;
			}
			return numArray;
		}
		
		// draws a rectangle
		public BufferedImage drawRect(BufferedImage img) {
			final Color c = Color.BLUE; 
			
			// top and bottom
			for(int i = leftX[0]; i < leftX[0] + avgWidth(); i++) {
				for(int lineWidth = 0; lineWidth < LINE_THICKNESS; lineWidth++) {
					img.setRGB(i, topY + lineWidth, c.getRGB());
					img.setRGB(i, bottomY - lineWidth, c.getRGB());
				}
			}
			
			// left and right sides
			for(int i = topY; i <= bottomY; i++) {
				for(int lineWidth = 0; lineWidth < LINE_THICKNESS; lineWidth++) {
					img.setRGB(leftX[0] + lineWidth, i, c.getRGB());
					img.setRGB(leftX[0] + lineWidth + avgWidth(), i, c.getRGB());
				}
			}

			return img;
		}
		
		// draws the box to be displayed on the screen
		// and returns the new image
		public BufferedImage drawBox(BufferedImage img) {
			if(!(topY < 0) && !(bottomY < 0)) {
				int validLines = bottomY - topY - 1;
				
				if(bottomY >= winHeight - LINE_THICKNESS) {
					bottomY = winHeight - LINE_THICKNESS;
				}
				
				if(topY >= winHeight) {
					topY= winHeight - bottomY - (LINE_THICKNESS + 1);
				}
								
				img = drawSides(img);
				img = drawTop(img, validLines);
				img = drawBottom(img, validLines);				
			}
			return img;
		}
		
		// draws the left and right side of the box
		private BufferedImage drawSides(BufferedImage img) {
			int i = 0;
			for(int y = topY; y < bottomY; y++) {
				// Checks to see if the line is within
				// the dimensions of the image.
				if(rightX[i] >= winWidth - LINE_THICKNESS) {
					rightX[i] = winWidth - LINE_THICKNESS;
				} else if(rightX[i] < 0) {
					rightX[i] = 0;
				}
				
				if(leftX[i] >= winWidth) {
					leftX[i] = winWidth - rightX[i];
				} else if(leftX[i] < 0) {
					leftX[i] = 0;
				}
				
				for(int lineWidth = 0; lineWidth < LINE_THICKNESS; lineWidth++) {
					img.setRGB(leftX[i] + lineWidth, y, boxColor.getRGB());					
					img.setRGB(rightX[i] + lineWidth, y, boxColor.getRGB());
				}
				i++;
			}
			
			return img;
		}
		
		
		// draws the top of the box
		private BufferedImage drawTop(BufferedImage img, int validLines) {
			int i = 0;
			
			for(int x = leftX[i]; x < rightX[i]; x++) {
				for(int lineHeight = 0; lineHeight < LINE_THICKNESS; lineHeight++) {
					// TODO: had a "Coordinate out of bounds!" error once
					img.setRGB(x, topY + lineHeight, boxColor.getRGB());
				}
			}
			i++;
			
			for(int y = topY + 1; y < bottomY; y++) {
				if(i < validLines) {
					for(int x = rightX[i - 1]; x < rightX[i]; x++) {
						for(int lineHeight = 0; lineHeight < LINE_THICKNESS; lineHeight++) {
							img.setRGB(x, y + lineHeight, boxColor.getRGB());
						}
					}
					i++;
				} else {
					break;
				}
			}
			
			return img;
		}
		

		// draws the bottom of the box
		private BufferedImage drawBottom(BufferedImage img, int validLines) {
			int i = validLines;

			for(int x = leftX[i]; x < rightX[i]; x++) {
				for(int lineHeight = 0; lineHeight < LINE_THICKNESS; lineHeight++) {
						img.setRGB(x, bottomY - lineHeight, boxColor.getRGB());
				}
			}
			i--;
			
			for(int y = bottomY; y > topY; y--) {
				
				if((i >= 0) && (i < validLines)) {
					// major drawing
					for(int x = leftX[i]; x < leftX[i + 1]; x++) {
						for(int lineHeight = 0; lineHeight < LINE_THICKNESS; lineHeight++) {
							img.setRGB(x, y - lineHeight, boxColor.getRGB());
						}
					}
					
					i--;
				} else {
					break;
				}
			}
			
			return img;
		}
		
		// finds the actual width of the box excluding outliers
		public int normalWidth() {
			int biggestWidth = -1;
			int numTries = 0;
			
			int width = -1;
			index = -1;
			for(int i = 0; i < validLines; i++) {
				width = rightX[i] - leftX[i];
//				System.out.print(width);
				
				if(		   (width > biggestWidth + WIDTH_THRES)
						&& (numTries < MAX_TRIES)) {
					biggestWidth = width;
//					System.out.print("_" + biggestWidth);
					index = i;
					numTries = 0;
				} else {
					numTries++;
				}
//				System.out.print(", ");
			}

			return biggestWidth;
		}
		
		// the average width of the box
		private int avgWidth() {
			double avg = -1;
			
			// adds up all of the widths
			for(int i = 0; i < validLines; i++) {
				avg += rightX[i] - leftX[i];
			}
			
			// finds the average of the widths
			avg = avg / (validLines - 1);
			
			
			return (int) avg;
		}
		/*				****adding methods****				*/
		
		// add a given amount to validLines
		public void addToValidLines(int addAmount) {
			this.validLines += addAmount;
		}
		
		// add a given amount to the boxes current width at a given yAxis
		public void addToCurrWidth(int currWidth) {
			this.currBoxWidth += currWidth;
		}
		
		
		/*				****set methods****				*/
		
		// set the top yAxis of the box
		public void setTopY(int topY) {
			this.topY = topY;
		}
		
		// set the bottom yAxis of the box
		public void setBottomY(int bottomY) {
			this.bottomY = bottomY;
		}
		
		// set the array of leftX for the box
		public void setLeftX(int[] leftX) {
			this.leftX = leftX;
		}
		
		// set the array of rightX for the box
		public void setRightX(int[] rightX) {
			this.rightX = rightX;
		}
		
		// set the leftX on a given yAxis
		public void setLeftX(int leftX, int yAxis) {
			this.leftX[yAxis] = leftX;
		}
		
		// set the rightX on a given yAxis
		public void setRightX(int rightX, int yAxis) {
			this.rightX[yAxis] = rightX;
		}
		
		// set the validLines (height) of the box
		public void setValidLines(int validLines) {
			this.validLines = validLines;
		}
		
		// set the current box width
		public void setCurrentBoxWidth(int boxWidth) {
			this.currBoxWidth = boxWidth;
		}
		
		public void setWidestBoxWidth(int boxWidth) {
			this.widestBoxWidth = boxWidth;
		}
		
		// sets the color of the box
		// the default color is pink
		public void setBoxColor(Color newBoxColor) {
			this.boxColor = newBoxColor;
		}
		
		
		/*				****get	methods****					*/
		
		// returns the top Y axis of the box
		public int getTopY() {
			return topY;
		}
		
		// returns the bottom Y axis of the box
		public int getBottomY() {
			return bottomY;
		}
		
		// returns the array of leftX's
		public int[] getLeftX() {
			return leftX;
		}
		
		// returns the array of rightX's
		public int[] getRightX() {
			return rightX;
		}
		
		// returns a leftX on a given yAxis
		public int getLeftX(int yAxis) {
			return leftX[yAxis];
		}
		
		// returns an rightX on a given yAxis
		public int getRightX(int yAxis) {
			return rightX[yAxis];
		}
			
		// returns the height of the box
		public int getValidLines() {
			return validLines;
		}
		
		// returns the last known box width
		public int getCurrentBoxWidth() {
			return currBoxWidth;
		}
		
		public int getWidestBoxWidth() {
			return widestBoxWidth;
		}
		
		// returns all of the corners
		public Point[] getCorners() {
//			System.out.println("Height: " + (validLines - 1));
			normalWidth();
//			System.out.println("Index: " + index);

			return new Point[] {
				getTopLeftCorner(),
				getTopRightCorner(),
				getBottLeftCorner(),
				getBottRightCorner()
			};
		}
		// returns top left corner
		public Point getTopLeftCorner() {
			int x = leftX[0];
			int y = topY;
						
			if(index > 0) {
//				System.out.println("LeftX[index]: " + leftX[index]);
//				System.out.println("LeftX[index - 1]: " + leftX[index - 1]);

				if(leftX[index] < leftX[index - 1]) {
					return new Point(leftX[index], topY + index);
				} 
			}
			return new Point(x, y);
		}
		
		// returns top right corner
		public Point getTopRightCorner() {
			int x = rightX[0];
			int y = topY;
				
			if(index > 0) {
//				System.out.println("RightX[index]: " + rightX[index]);
//				System.out.println("RightX[index - 1]: " + rightX[index - 1]);

				if(rightX[index] > rightX[index - 1]) {
					return new Point(rightX[index], topY + index);
				}
			}
			
			return new Point(x, y);
		}
		
		// returns bottom left corner
		public Point getBottLeftCorner() {
			int x = leftX[validLines - 1];
			int y = bottomY;
			
			return new Point(x, y);
		}
		
		// returns bottom right corner
		public Point getBottRightCorner() {
			int x = rightX[validLines - 1];
			int y = bottomY;
			
			return new Point(x, y);
		}
}
