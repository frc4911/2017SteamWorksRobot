import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Box {
		private int winWidth;
		private int winHeight;
	
		private int topY = -1;
		private int bottomY = -1;
	
		private int[] leftX;
		private int[] rightX;
		
		private int validLines = 0;
		private int currBoxWidth = 0;
		
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
		
		// draws the box to be displayed on the screen
		// and returns the new image
		public BufferedImage drawBox(BufferedImage img) {
			if(!(topY < 0) && !(bottomY < 0)) {
				int validLines = bottomY - topY - 1;
				
				if(bottomY >= winHeight - 5) {
					bottomY = winHeight - 5;
				}
				if(topY>= winHeight) {
					topY= winHeight - bottomY - 6;
				}
				
				// TODO: fix and find critical out of bounds error
				
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
				if(rightX[i] >= winWidth) {
					rightX[i] = winWidth - 1;
				}
				if(leftX[i] >= winWidth) {
					leftX[i] = winWidth - rightX[i];
				}
				
				for(int lineWidth = 0; lineWidth < 5; lineWidth++) {
					img.setRGB(leftX[i] + lineWidth, y, boxColor.getRGB());
					img.setRGB(rightX[i] - lineWidth, y, boxColor.getRGB());
				}
				i++;
			}
			
			return img;
		}
		
		// draws the top of the box
		private BufferedImage drawTop(BufferedImage img, int validLines) {
			int i = 0;
			
			for(int x = leftX[i]; x < rightX[i]; x++) {
				for(int lineHeight = 0; lineHeight < 5; lineHeight++) {
					img.setRGB(x, topY + lineHeight, boxColor.getRGB());
				}
			}
			i++;
			
			for(int y = topY + 1; y < bottomY; y++) {
				if(i < validLines) {
					// minor drawing
					for(int x = leftX[i]; x < leftX[i - 1]; x++) {
						for(int lineHeight = 0; lineHeight < 5; lineHeight++) {
							img.setRGB(x, y + lineHeight, boxColor.getRGB());
						}
					}
				
					// major drawing
					for(int x = rightX[i - 1]; x < rightX[i]; x++) {
						for(int lineHeight = 0; lineHeight < 5; lineHeight++) {
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
			
			if(i > 1) {
				i--;
			}

			for(int x = leftX[i]; x < rightX[i]; x++) {
				for(int lineHeight = 0; lineHeight < 5; lineHeight++) {
					img.setRGB(x, bottomY - lineHeight, boxColor.getRGB());
				}
			}
			i--;
			
			for(int y = bottomY; y > topY; y--) {
				
				if((i >= 0) && (i < validLines)) {
					// major drawing
					for(int x = leftX[i]; x < leftX[i + 1]; x++) {
						for(int lineHeight = 0; lineHeight < 5; lineHeight++) {
							img.setRGB(x, y - lineHeight, boxColor.getRGB());
						}
					}
				
					// minor drawing
					for(int x = rightX[i + 1]; x > rightX[i]; x--) {
						for(int lineHeight = 0; lineHeight < 5; lineHeight++) {
							try {
								img.setRGB(x, y - lineHeight, boxColor.getRGB());
							} catch(ArrayIndexOutOfBoundsException e) {
								// TODO find out what is causing this exception to be caught
								e.printStackTrace();
							}
						}
					}
					
					i--;
				} else {
					break;
				}
			}
			
			return img;
		}
		
		/*				****add methods****				*/
		
		// add a given amount to validLines
		public void addToValidLines(int addAmount) {
			this.validLines += addAmount;
		}
		
		// add a given amount to the boxes current width at a given yAxis
		public void addToCurrWidth(int currWidth) {
			this.currBoxWidth += currWidth;
		}
		
		
		/*				****set methods****				*/
		
		// sets the top yAxis of the box
		public void setTopY(int topY) {
			this.topY = topY;
		}
		
		// sets the bottom yAxis of the box
		public void setBottomY(int bottomY) {
			this.bottomY = bottomY;
		}
		
		// sets the array of leftX for the box
		public void setLeftX(int[] leftX) {
			this.leftX = leftX;
		}
		
		// sets the array of rightX for the box
		public void setRightX(int[] rightX) {
			this.rightX = rightX;
		}
		
		// sets the leftX on a given yAxis
		public void setLeftX(int leftX, int yAxis) {
			this.leftX[yAxis] = leftX;
		}
		
		// sets the rightX on a given yAxis
		public void setRightX(int rightX, int yAxis) {
			this.rightX[yAxis] = rightX;
		}
		
		// set the validLines (height) of the box
		public void setValidLines(int validLines) {
			this.validLines = validLines;
		}
		
		// the current box width at a given yAxis
		public void setCurrentBoxWidth(int boxWidth) {
			this.currBoxWidth = boxWidth;
		}
		
		// sets the color of the box
		// the default color is pink
		public void setBoxColor(String color) {
			color.toLowerCase();
			
			if(Objects.equals(color, "magenta")) {
				this.boxColor = Color.MAGENTA;
				
			} else if(Objects.equals(color, "red")) {
				this.boxColor = Color.RED;
				
			} else if(Objects.equals(color, "orange")) {
				this.boxColor = Color.ORANGE;
				
			} else if(Objects.equals(color, "yellow")) {
				this.boxColor = Color.YELLOW;
				
			} else if(Objects.equals(color, "green")) {
				this.boxColor = Color.GREEN;
			
			} else if(Objects.equals(color, "blue")) {
				this.boxColor = Color.BLUE;
			
			} else {
				// if none of the colors provided are valid, then
				// the color will be set to its default color
				this.boxColor = Color.MAGENTA;
			}
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
			
		public int getValidLines() {
			return validLines;
		}
		
		public int getCurrentBoxWidth() {
			return currBoxWidth;
		}
}
