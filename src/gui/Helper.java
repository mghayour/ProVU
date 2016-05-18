/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ghayour
 */
public class Helper {
    
    

    public static void setFullScreen (Stage stage) {
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setX(bounds.getMinX());
        stage.setY(bounds.getMinY());
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());        
    }
      
    public static void addBackground(Pane rooter) {
        addBackground(rooter, 0.59, 0.95);
    }
    public static void addBackground(Pane rooter,double saturation, double brightness) {
        
        System.out.println(rooter);
        ObservableList<Node> childs = rooter.getChildren();
        int randomHue = 10;
        for (int i=7;i>-3;i--) {
            randomHue += 20;
            Rectangle c1 = new Rectangle(1500,500,Color.hsb(randomHue, saturation, brightness));
            
            int y = 100*i;
            setXY(c1,-50,y);
            int rotate = (i%2==0?1:-1)*5;
            c1.setRotate(rotate);
            c1.getStyleClass().add("material-elem");
            childs.add(0,c1);
            dragable(c1);
            /*
            CachedTimelineTransition moveAnimate = new FadeInRightTransition(c1);
            //moveAnimate.setAutoReverse(true);
            moveAnimate.setCycleCount(Animation.INDEFINITE);
            moveAnimate.playFrom(Duration.seconds(i*12));
            
            /**/
            Timeline moveAnimate = new Timeline(
              new KeyFrame(
                Duration.seconds(30),
                new KeyValue(
                  c1.layoutYProperty(),
                  y+150,
                  Interpolator.EASE_BOTH
                )
              )
            );
            
            
            
            moveAnimate.setAutoReverse(true);
            moveAnimate.setCycleCount(Animation.INDEFINITE);
            moveAnimate.playFrom(Duration.seconds(i*12));
                    /**/
            
        }

    }
    
    static void dragable (final Node ctrl) {
        // allow the clock background to be used to drag the clock around.
        final double[] diff = new double[2];
        
        
        ctrl.setOnMousePressed(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            // record a delta distance for the drag and drop operation.
            diff[0] = ctrl.getLayoutX() - mouseEvent.getScreenX();
            diff[1] = ctrl.getLayoutY() - mouseEvent.getScreenY();
          }
        });
        ctrl.setOnMouseDragged(new EventHandler<MouseEvent>() {
          @Override public void handle(MouseEvent mouseEvent) {
            setXY( ctrl,
            mouseEvent.getScreenX() + diff[0],
            mouseEvent.getScreenY() + diff[1]);
          }
        });
    }
    
    static void setXY (Node ctrl, double x, double y) {
            System.out.println("SetXY: "+x+", "+y); // auto copy function to clip board...
            //copy("setXY(c,"+x+","+y+");");
            ctrl.setLayoutX(x);
            ctrl.setLayoutY(y);    
    }

    static String getResource(String path) {
        return Login.class.getResource(path).toExternalForm();
    }
    
    static void copy(String get) {
        try {
            StringSelection selec= new StringSelection(get);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selec, selec);
        } catch(Exception e) {
            System.out.println("ERROR:"+e);
        }
    }


}
