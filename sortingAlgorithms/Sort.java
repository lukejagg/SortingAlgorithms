// © 2018 Lucas Jaggernauth
// Do not copy, cite, or distribute without permission of the author

package sortingAlgorithms;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.scene.Scene;
import javafx.scene.canvas.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.VPos;
import javafx.scene.transform.Rotate;
import javafx.scene.media.Media;

enum SORT_ALGS {
	Bogosort,
	Bozosort,
	Bubblesort,
	Cocktailsort,
	Idiosort,
	Lucasort,
	OddEven,
	
};

// Includes Drawing
public class Sort extends Application {
	
	// VIEW SETTINGS
	static final int WIDTH = 800;
	static final int HEIGHT = 800;
	
	static Random rnd = new Random();
	
	static SORT_ALGS alg = SORT_ALGS.Cocktailsort;
	
	static int[] sorted = new int[30];
	static long cycles = 0;
	static long switches = 0;
	
	static GraphicsContext gc;
	
//	static ArrayList<int[]> t = new ArrayList<int[]>();
	
	static boolean isInOrder() {
		for (int i = 0; i < sorted.length-1; i++) {
			if (sorted[i] > sorted[i+1])
				return false;
		}
		return true;
	}
	
	static void drawArray() {
		gc.clearRect(0, 0, WIDTH, HEIGHT);
		for (int i = 0; i < sorted.length; i++) {
			int height = sorted[i] * HEIGHT / sorted.length;
			gc.fillRect(1 + i * WIDTH / sorted.length, 1 + HEIGHT - height, -2 + WIDTH / sorted.length, -2 + height);
		}
	}
	
	static void drawArradial() {
		for (int i = 0; i < sorted.length; i++) {
            gc.setFill(Color.hsb(sorted[i] * 360.0 / sorted.length,1, 1));
            double[] x = {400, 400 + Math.cos(Math.toRadians(i * 360.0 / sorted.length)) * 400, 400 + Math.cos(Math.toRadians((i + 1) * 360.0 / sorted.length)) * 400}, y = {400, 400 + Math.sin(Math.toRadians(i * 360.0 / sorted.length)) * 400, 400 + Math.sin(Math.toRadians((i + 1) * 360.0 / sorted.length)) * 400};
            gc.fillPolygon(x, y, 3);
        }
	}
	
	static void swap(int i, int j) {
		int ref = sorted[i];
		sorted[i] = sorted[j];
		sorted[j] = ref;
		switches++;
	}
	
	static void printStats() {
		System.out.println("Cycles: " + cycles);
		System.out.println("Switches: " + switches);
	}
	
	///
	/// ALGORITHMS
	///
	
	static void bozosort() {
		int i = rnd.nextInt(sorted.length);
		int j = rnd.nextInt(sorted.length);
		if (i==j)
			bozosort();
		swap(i, j);
	}
	
	static void bogosort() {
		for (int i = 0; i < sorted.length; i++) {
			int id = rnd.nextInt(sorted.length);
			swap(i, id);
		}
	}
	
	///
	/// </>Algorithms</>
	///
	
	@Override
    public void start(Stage primaryStage) {
		
//		for (int i = 0; i < sorted.length; i++) {
//			sorted[i] = sorted.length - i;
//		}
//		swap(0,1);
		
		for (int i = 0; i < sorted.length; i++) {
			sorted[i] = i+1;
		}
//		swap(0,1);
//		
		for (int i = 0; i < sorted.length; i++) {
			int id = rnd.nextInt(sorted.length);
			swap(i, id);
		}
//		
//		t.add(sorted.clone());
		
	    Group root = new Group();
    	Scene s = new Scene(root, WIDTH, HEIGHT, Color.BLACK);

    	final Canvas canvas = new Canvas(WIDTH, HEIGHT);
    	gc = canvas.getGraphicsContext2D();
    	gc.setStroke(Color.WHITE);
    	gc.setFill(Color.WHITE);
    	 
    	root.getChildren().add(canvas);
    
    	primaryStage.initStyle(StageStyle.UNDECORATED);
    	primaryStage.setTitle("Sort");
    	primaryStage.setScene(s);
    	primaryStage.setResizable(false);
    	primaryStage.show();
    	
    	canvas.setFocusTraversable(true);
	
    	primaryStage.setOnCloseRequest(e -> {
	    	
	    	System.exit(0);
	    	
    	});
    	
    	AnimationTimer timer = new AnimationTimer() {
    		
    		int x = 0;
    		int y = 0;
    		int z = 0;
    		int w = 0;
    		int state = 0;
    		long t;
    		double lt = 0;
    		
    	    @Override
    	    public void handle(long dt) 
    	    {
    	    	if (t > 0) {
    	    		lt += (dt - t) / 1000000000.0;
    	    	if (lt > 0) {
    	    		lt -= 0;
    	    	for (int lol = 0; lol < 1; lol++) {
    	    		cycles++;
    	    		switch(alg) {
    	    		
    	    		
    	    		
	    	    		case Bogosort:
			    	    	if (!isInOrder()) {
			    	    		bogosort();
			    	    	}
			    	    	else {
			    	    		printStats();
			    	    		drawArray();
			    	    		this.stop();
			    	    		return;
			    	    	}
			    	    	break;
    	    		
			    	    	
			    	    	
	    	    		case Bozosort:
			    	    	if (!isInOrder()) {
			    	    		bozosort();
			    	    	}
			    	    	else {
			    	    		printStats();
			    	    		drawArray();
			    	    		this.stop();
			    	    		return;
			    	    	}
			    	    	break;
			    	    	
			    	    	
			    	    	
	    	    		case Bubblesort:
	    	    			if (sorted[x] > sorted[x+1]) {
	    	    				swap(x, x+1);
	    	    			}
	    	    			drawArray();
	    	    			if (++x >= sorted.length-1-y) {
	    	    				x = 0;
	    	    				if (++y >= sorted.length) {
	    	    					printStats();
	    	    					this.stop();
	    	    					drawArray();
	    	    					return;
	    	    				}
	    	    			}
	    	    			break;
	    	    		
	    	    			
	    	    			
	    	    		case Cocktailsort:
	    	    			if (y%2==0) {
		    	    			if (sorted[x] > sorted[x+1]) {
		    	    				swap(x, x+1);
		    	    			}
		    	    			x++;
	    	    			}
	    	    			else {
	    	    				if (sorted[x] < sorted[x-1]) {
		    	    				swap(x, x-1);
		    	    			}
	    	    				x--;
	    	    			}
	    	    			drawArray();
	    	    			if ((y%2==0 && x >= sorted.length-y/2-1) || (y%2 == 1 && x <= y/2)) {
	    	    				y++;
	    	    				if (y >= sorted.length-1 || isInOrder()) {
	    	    					printStats();
	    	    					this.stop();
	    	    					drawArray();
	    	    					return;
	    	    				}
	    	    			}
	    	    			break;
	    	    			
	    	    			
	    	    			
	    	    		case Idiosort:
//	    	    			swap(x, (y*z+(x < sorted.length-1 ? (sorted[x] < sorted[x+1] ? 1 : x) : 0))%sorted.length);
	    	    			swap(x,(y^z)%sorted.length);
	    	    			for (int i = 0; i < x; i++) {
	    	    				swap(i, y*z%(x+1));
	    	    			}
//	    	    			for (int[] l : t) {
//	    	    				if (l.equals(sorted))
//	    	    					System.out.println("HEY");
//	    	    			}
//	    	    			t.add(sorted.clone());
//	    	    			drawArray();
	    	    			if (isInOrder()) {
    	    					printStats();
    	    					this.stop();
    	    					drawArradial();
    	    					return;
    	    				}
	    	    			if (++x >= sorted.length-1) {
	    	    				x = 0;
	    	    				if (++y >= sorted.length-1) {
	    	    					y=0;
	    	    					z++;
//	    	    					if (z++ > sorted.length*2) {
//	    	    						z ^= w;
//	    	    					}
	    	    				}
	    	    			}
	    	    			break;
	    	    			
	    	    			
	    	    		
	    	    		case Lucasort:
	    	    			swap(0,x);
	    	    			if (x > 0 && y%2==0) {
	    	    				int swap = rnd.nextInt(x);
	    	    				swap(swap,x%sorted.length/3);
	    	    			}
	    	    			for (int i = 0; i < sorted.length; i++) {
	    	    				swap(i,x);
	    	    				y++;
	    	    			}
	    	    			drawArray();
	    	    			if (isInOrder()) {
    	    					printStats();
    	    					this.stop();
    	    					drawArray();
    	    					return;
    	    				}
	    	    			if (++x >= sorted.length) {
	    	    				x = 0;
	    	    			}
	    	    			break;
	    	    			
	    	    			
	    	    			
	    	    		case OddEven:
	    	    			if (sorted[x] > sorted[x+1]) {
	    	    				swap(x, x+1);
	    	    			}
	    	    			drawArray();
	    	    			if ((x+=2) > sorted.length-2) {
	    	    				y++;
	    	    				x = (y%2==0 ? 0 : 1);
	    	    				if (y >= sorted.length) {
	    	    					printStats();
	    	    					this.stop();
	    	    					drawArray();
	    	    					return;
	    	    				}
	    	    			}
	    	    			break;
    	    		}
    	    		
    	    	}
//    	    	rnd = new Random();
//    	    	drawArradial();
    	    	drawArray();
    	    	
    	    	}
    	    }
    	    	t = dt;
    	    }
    	    
    	};
    	
    	timer.start();
	    	
	}
	
	public static void main(String[] args) {
		
		launch(args);
		
	}
	
}
