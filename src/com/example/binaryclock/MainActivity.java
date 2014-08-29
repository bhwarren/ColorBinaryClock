package com.example.binaryclock;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager.LayoutParams;

public class MainActivity extends Activity {
	
	CanvasView canvas;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(LayoutParams.FLAG_KEEP_SCREEN_ON);

        
        
        canvas = new CanvasView (this);
        canvas.setBackgroundColor(Color.WHITE);
        
		setContentView(canvas);
		
		Timer t=new Timer();
		t.scheduleAtFixedRate(new TimerTask(){ 
	        public void run() 
	        { 
	        	runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
			            canvas.invalidate();  // display the data
					}
				});
	        } 
	    }, (long)1000,(long)1000); 

	}

	
}
