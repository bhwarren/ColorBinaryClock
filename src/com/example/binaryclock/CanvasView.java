package com.example.binaryclock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.format.Time;
import android.view.View;

public class CanvasView extends View{
	
	Paint paint = new Paint();
	int width;
	int height;
	int cx;
	int cy;
	int center;
	double mod;
	Time now = new Time();

	
	Boolean gotSizes = false;
	Boolean itsPM;
	
	
	
	public void drawBits(int color, int timeSpec, Canvas canvas, int switcher){
		paint.setColor(color);
        minutes:
        for(int x=6; x>=1; x--){
        	
        	mod= (timeSpec%(Math.pow(2, x-1)));
        
        	if(mod==(double)timeSpec){
            	//do nothing b/c the mod is too high
        	}
        	else{
    			canvas.drawCircle(cx-switcher*x*45, cy-x*25, 20, paint);
        	        	
	        	if(mod==0){
	        		break minutes;
	        	}
	        	else{
	        		if(mod>0){
	        			timeSpec-=(int)Math.pow(2,x-1);
	        		}
	        	}
        	}
        }

		
	}


    public CanvasView (Context context) {
        super(context);
        
    }
    

    public void onDraw(Canvas canvas) {
    	
    	if(!gotSizes){
    		width = canvas.getWidth();
    		height = canvas.getHeight();
    		cx=width/2;
    		cy=height/2;
    		gotSizes=true;
    	}
    	
    	
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        canvas.drawRect(10, 10, width-10, height-10, paint);
        
    	now.setToNow();
    	
    	Integer seconds=now.second;
    	Integer minutes=now.minute;
    	Integer hours=now.hour;
    	
    	if(hours>12){
    		hours-=12; //convert to am/pm time
    		
    		//draw the pm bit
            paint.setColor(Color.YELLOW);
            canvas.drawCircle(cx, cy, 20, paint);
    		
    	}
    	
        
        
        //draw the minutes
        drawBits(Color.BLUE, minutes, canvas,1);
	    
        
        //draw the seconds
        drawBits(Color.GREEN, seconds, canvas,-1);

        
        
        //draw the hours
        paint.setColor(Color.RED);
        hours:
        for(int x=4; x>=1; x--){
        	
        	mod= (hours%(Math.pow(2, x-1)));
            
        	if(mod==(double)hours){
            	//do nothing b/c the mod is too high
        	}
        	else{
    			canvas.drawCircle(cx, cy+x*45, 20, paint);
        	        	
	        	if(mod==0){
	        		break hours;
	        	}
	        	else{
	        		if(mod>0){
	        			hours-=(int)Math.pow(2,x-1);
	        		}
	        	}
        	}
        	
	    }	

    }

}