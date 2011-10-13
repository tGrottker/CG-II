package cg2.warmup;

import cg2.vecmath.Color;


public class Checkerboard implements Painter {
	
  @Override
  public Color pixelColorAt(int i, int j, int width, int height) {
	  
	  // this is just a little hint -
	  // but not yet the solution to paint an 8x8 checkerboard for any given resolution
	  if(i % 2 + j % 2 == 1) {
		  return new Color(0, 0, 0);
	  } else {
		  return new Color(1, 1, 1);
	  }
    
  }

}
