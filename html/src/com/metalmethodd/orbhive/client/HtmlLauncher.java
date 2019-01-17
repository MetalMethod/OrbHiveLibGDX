package com.metalmethodd.orbhive.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.metalmethodd.orbhive.Constants;
import com.metalmethodd.orbhive.OrbHiveGame;

public class HtmlLauncher extends GwtApplication {

    // USE THIS CODE FOR A FIXED SIZE APPLICATION
    // @Override
    // public GwtApplicationConfiguration getConfig() {
    //    return new GwtApplicationConfiguration(Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
    // }
    // END CODE FOR FIXED SIZE APPLICATION

     //UNCOMMENT THIS CODE FOR A RESIZABLE APPLICATION
     //PADDING is to avoid scrolling in iframes, set to 20 if you have problems
     private static final int PADDING = 0;
     private GwtApplicationConfiguration cfg;

     @Override
     public GwtApplicationConfiguration getConfig() {

         int width = Constants.GAME_WIDTH;
         int height = Constants.GAME_HEIGHT;

         int dif = width - height;
         int h = width - dif;

         int prop = width / dif;

         int sdif = Window.getClientWidth() - (Window.getClientWidth() / prop);

         int screenWidth = Window.getClientWidth();
         int screenHeight = Window.getClientHeight();

         // Trying to match a squasre in all window sizes
         if(screenHeight < screenWidth){
             screenWidth = screenHeight;
         }else{
             screenHeight = screenWidth;
         }

         cfg = new GwtApplicationConfiguration(screenWidth, screenHeight);
         Window.enableScrolling(false);
         Window.setMargin("0");
         Window.addResizeHandler(new ResizeListener());
         cfg.preferFlash = false;
         return cfg;
     }

     class ResizeListener implements ResizeHandler {
         @Override
         public void onResize(ResizeEvent event) {

             int width = event.getWidth();
             int height = event.getHeight();

             // Trying to match a squasre in all window sizes
             if(height < width){
                 width = height;
             }else{
                 height = width;
             }

             getRootPanel().setWidth("" + width + "px");
             getRootPanel().setHeight("" + height + "px");
             getApplicationListener().resize(width, height);
             Gdx.graphics.setWindowedMode(width, height);
         }
     }
     // END OF CODE FOR RESIZABLE APPLICATION

    @Override
     public ApplicationListener createApplicationListener() {
        return new OrbHiveGame();
    }
}