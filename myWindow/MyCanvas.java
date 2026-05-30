import java.awt.*;
import java.awt.event.*;
import java.lang.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class MyCanvas extends Canvas implements KeyListener, Runnable {

	private static int BORDERSPACE = 20;
	private static int TICKSIZE = 3;
	private static int INTERVAL = 25;
	private int maxSecondsLastDigit = 0;
	private int maxSecondsFirstDigit = 0;
	private int width = this.getWidth();
	private int height = this.getHeight();
	// private int status = 1;
	private boolean spacePressed = false;


	LocalDateTime zeit = LocalDateTime.now();

	public MyCanvas() {
		this.setFocusable(true);
		this.addKeyListener(this);
	}
	

	public void paint(java.awt.Graphics g) {

		width = this.getWidth();
		height = this.getHeight();
		int maxsize = Math.max(width / 2, height / 2);

		// System.out.println("Width is: " + width + ", Height is: " + height);

		int centerpointX = width / 2;
		int centerpointY = height / 2;

		/* g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
	
		if (status == 1) {
			colorCircles(g,width,height);
		} 
		else if (status == 2) {
			whiteCircle(g, width, height);
		} 
		else if (status == 3) {
			rectangle(g,width, height);
		*/

		drawCoordinatesystem(centerpointX, centerpointY, g, maxsize, width, height);
		drawParabola(g, width, height,INTERVAL);

	}
		
	

	public void drawCoordinatesystem(int centerpointX, int centerpointY, java.awt.Graphics g, int maxsize, int width,
			int height) {
		// draw vertical axis
		g.drawLine(width / 2, BORDERSPACE, width / 2, height - BORDERSPACE);
		// draw horizontal axis
		g.drawLine(BORDERSPACE, height / 2, width - BORDERSPACE, height / 2);
		// draw tickmarks
		for (int i = 0; i <= maxsize; i += INTERVAL) {
			// top
			g.drawLine(centerpointX + TICKSIZE, centerpointY - i, centerpointX - TICKSIZE, centerpointY - i);
			// bottom
			g.drawLine(centerpointX + TICKSIZE, centerpointY + i, centerpointX - TICKSIZE, centerpointY + i);
			// right side
			g.drawLine(centerpointX + i, centerpointY + TICKSIZE, centerpointX + i, centerpointY - TICKSIZE);
			// left side
			g.drawLine(centerpointX - i, centerpointY + TICKSIZE, centerpointX - i, centerpointY - TICKSIZE);

		}
		// draw arrows
		// upper arrow left half
		g.drawLine(centerpointX, BORDERSPACE, centerpointX - TICKSIZE, 25);
		// upper arrow right half
		g.drawLine(centerpointX, BORDERSPACE, centerpointX + TICKSIZE, 25);
		// right arrow upper half
		g.drawLine(width - 25, centerpointY - TICKSIZE, width - BORDERSPACE, centerpointY);
		// right arrow lower half
		g.drawLine(width - 25, centerpointY + TICKSIZE, width - BORDERSPACE, centerpointY);

	}

	public void drawParabola(java.awt.Graphics g, int width, int height, int INTERVAL) {
		// origin of Parabola
		int xOrigin = width / 2;
		int yOrigin = height / 2;
		// Scale for parabola
		int scaleX = INTERVAL;
		int scaleY = INTERVAL;

		double oldScreenX = 0.0;
		double oldScreenY = 0.0;
		// calculate paperX and paperY from x²
		for (double x = -5.0; x <= 5.0; x += 0.01) {
			double paperX = x;
			double paperY = paperX * paperX;
			// calculate screenX, screenY from paperX and paperY
			double screenX = xOrigin + (paperX * scaleX);
			double screenY = yOrigin - (paperY * scaleY);
			if (oldScreenX != 0) {
				g.drawLine((int) oldScreenX, (int) oldScreenY, (int) screenX, (int) screenY);
			}
			oldScreenX = screenX;
			oldScreenY = screenY;

		}

	}

	public void rectangle(java.awt.Graphics g, int width, int height) {
		int cubeIntervalX = (width - 450) / 10;
		int cubeIntervalY = (height - 350) / 8;
		Color color1 = Color.GRAY;
		Color color2 = Color.RED;
		zeit = LocalDateTime.now();
		int seconds = zeit.getSecond();
		int secondsFirstDigit = (seconds / 10) - 1;
		int secondsLastDigit = seconds % 10;

		System.out.println("Time is: " + seconds);

		// Cubes on X-Axis, 1 cube = 1 second
		int rightmostX = width - cubeIntervalX - 50; // X position of the vertical line blocks
		for (int i = 1; i <= 9; i++) {
			g.setColor(color1);
			g.fillRect(rightmostX - (9 - i) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);

			if ((int) secondsLastDigit >= 1) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 1) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 2) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 2) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 3) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 3) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 4) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 4) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 5) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 5) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 6) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 6) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 7) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 7) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 8) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 8) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}
			if ((int) secondsLastDigit >= 9) {
				g.setColor(color2);
				g.fillRect(rightmostX - (9 - 9) * (cubeIntervalX + 50), height - cubeIntervalY - 50, 50, 50);
			}

		}

		// Cubes on Y-Axis, 1 cube = 10 second
		for (int i = 0; i <= 4; i++) {
			g.setColor(color1);
			g.fillRect(width - cubeIntervalX - 50, cubeIntervalY + cubeIntervalY * i + 50 * i, 50, 50);

			if (secondsFirstDigit >= 0) {
				g.setColor(color2);
				g.fillRect(width - cubeIntervalX - 50, cubeIntervalY + cubeIntervalY * 0 + 50 * 0, 50, 50);

			}
			if (secondsFirstDigit >= 1) {
				g.setColor(color2);
				g.fillRect(width - cubeIntervalX - 50, cubeIntervalY + cubeIntervalY * 1 + 50 * 1, 50, 50);

			}
			if (secondsFirstDigit >= 2) {
				g.setColor(color2);
				g.fillRect(width - cubeIntervalX - 50, cubeIntervalY + cubeIntervalY * 2 + 50 * 2, 50, 50);

			}
			if (secondsFirstDigit >= 3) {
				g.setColor(color2);
				g.fillRect(width - cubeIntervalX - 50, cubeIntervalY + cubeIntervalY * 3 + 50 * 3, 50, 50);

			}
			if (secondsFirstDigit >= 4) {
				g.setColor(color2);
				g.fillRect(width - cubeIntervalX - 50, cubeIntervalY + cubeIntervalY * 4 + 50 * 4, 50, 50);

			}

		}
		// Yeartext in upper left corner
		int year = zeit.getYear(); // Get current year
		g.setColor(color1);
		g.setFont(new Font("Serif", Font.PLAIN, 24)); // Set font
		g.drawString("Years: " + year, 10, 30); // Display the message on the canvas

		// Monthtext in upper left corner
		int month = zeit.getMonthValue(); // Get current month
		g.setColor(color1);
		g.setFont(new Font("Serif", Font.PLAIN, 24));
		g.drawString("Months: " + month, 10, 50);

		// Weektext in upper left corner
		int day = zeit.getDayOfYear(); // Get current day
		g.setColor(color1);
		g.setFont(new Font("Serif", Font.PLAIN, 24));
		g.drawString("Days: " + day, 10, 70);

		// Hourtext in the middle
		int hours = zeit.getHour(); // Get current hour
		g.setColor(color1);
		g.setFont(new Font("Serif", Font.PLAIN, 50));
		g.drawString("Hours: " + hours, (width / 2) - 50, height / 2);

		// Minutetext in the middle
		int minutes = zeit.getMinute(); // Get current minute
		g.setColor(color1);
		g.setFont(new Font("Serif", Font.PLAIN, 50));
		g.drawString("Minutes: " + minutes, (width / 2) - 50, (height / 2) + 50);

		// Full timetext in upper left corner
		g.setColor(color1);
		g.setFont(new Font("Serif", Font.PLAIN, 24));
		g.drawString(zeit.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)),10, 90);
		g.drawString(zeit.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)),10, 110);

	}

	public void run() {
		int letzteSekunde = -1;
		while (true) {
			try {
				Thread.sleep(250);
				if(this.status == 3){
				zeit = LocalDateTime.now();
				
				if (letzteSekunde != zeit.getSecond()) {
					letzteSekunde = zeit.getSecond();
					this.repaint();
					// System.out.print(".");
				}
				} 
				if(this.status == 1) {
					for(int i=1; i<= 20; i++){
					this.colorCircles(this.getGraphics(), width, height);
					Thread.sleep(500 / i);
					if(spacePressed == true){
						break;
					}
					}
					Thread.sleep(1000);
					if(spacePressed == false){
						this.status = 2;
					}


				
				}

				if(this.status == 2) {
					for(int i=1; i<= 500; i++){
					this.whiteCircle(this.getGraphics(),width, height);
					Thread.sleep(500 / i);
					if(spacePressed == true){
						break;
					}
					}
					this.status = 3;
				}
				// Thread.sleep(250);
			} catch (InterruptedException xcptn) {
			}


		}

	}

	public int getRandomNumX(int width) {
		return (int) (Math.random() * (width - 50));
	}

	public int getRandomNumY(int height) {
		return (int) (Math.random() * (height - 50));
	}

	public void colorCircles(Graphics g, int width, int height) {

		for(int i=0; i<=50; i++){
		
			int R = (int) (Math.random() * 255);
			int G = (int) (Math.random() * 255);
			int B = (int) (Math.random() * 255);

			Color c = new Color(R, G, B);

			// Generate random positions dynamically
			int randomX = getRandomNumX(width);
			int randomY = getRandomNumY(height);

			// Draw a circle at the random position
			g.setColor(c);
			g.fillOval(randomX, randomY, 50, 50);
		}
	}

	public void whiteCircle(java.awt.Graphics g,int width,int height){
		for(int i=0; i<=50; i++){
		// Generate random positions dynamically
			int randomX = getRandomNumX(width);
			int randomY = getRandomNumY(height);

			// Draw a circle at the random position
			g.setColor(Color.white);
			g.fillOval(randomX, randomY, 50, 50);
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_SPACE) {
			this.status = 3;
			this.repaint();
			spacePressed = true;
		}
	}
    
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e){}

}
