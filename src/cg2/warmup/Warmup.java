package cg2.warmup;

import warmup.SimpleCheckerBoard;
import warmup.SimpleDisc;

public class Warmup {

	  public static void main(String[] args) {
		  
		  // get the user's home directory - should work on all operating systems
		  String path = System.getProperty("user.home");
		  
		  // ************ test painting a checkerboard ************ 
		  {
			  String filename = path + "/" + "checkerboard.png";
			  new ImageGenerator(new SimpleCheckerBoard(), 750, 750, filename, "png");
			  //ImageGenerator.showImage(filename);
		  }

          // ************ test painting a disk ************
		  {
              String filename = path + "/" + "disc.png";
              new ImageGenerator(new SimpleDisc(), 750, 750, filename, "png");
              //ImageGenerator.showImage(filename);
          }
		  
	  }

}
