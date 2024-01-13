package gpiece_test;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;



/**
 *
 * @author jenre
 */
public class gpiece extends ImageView{
    String f_image = "unicorn.png";
    String b_image = "unicorn2.png";
    int cimage = 0;
    private double accelerate_x = 0.0;
    private double accelerate_y = 0.0;
    private double velocity_x = 0.0;
    private double velocity_y = 0.0;
  
    
    public void update(double width, double height){
        
        
        if(this.getX() > width || this.getX() < 0){
            this.velocity_x*= -1;
        }
        
        if(this.getY() > height || this.getY() < 0){
            this.velocity_y*= -1;
        }
        
        this.velocity_x = this.velocity_x + accelerate_x;
        this.velocity_y = this.velocity_y + accelerate_y;
        
        this.setX(this.getX() + this.velocity_x);
        this.setY(this.getY() + this.velocity_y);
        this.accelerate_x = 0;
        this.accelerate_y = 0;
    }
    
    public void setvelocity(double vx, double vy){
        this.velocity_x = vx;
        this.velocity_y = vy;
    }
    
    public void addacceleration(double vx, double vy){
        this.accelerate_x += vx;
        this.accelerate_y += vy;
    }
    
    public gpiece(){
        super(new Image("unicorn.png"));
        this.setX(0);
        this.setY(0);
        this.setFitHeight((double)50);
        this.setFitWidth((double)50);
        this.setPreserveRatio(true);
        this.velocity_x = 0;
        this.velocity_y = 0;
        f_image = "unicorn.png";
        b_image = "unicorn2.png";
       
    }
    
    public gpiece(double x, double y, double width, double height){
        super(new Image("unicorn.png"));
        this.setX(x);
        this.setY(y);
        this.setFitHeight(height);
        this.setFitWidth(width);
        this.setPreserveRatio(true);
        f_image = "unicorn.png";
        b_image = "unicorn2.png";
        this.velocity_x = 0;
        this.velocity_y = 0;
      
        
        this.setOnMouseEntered(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                gpiece temp = (gpiece)me.getTarget();
                temp.setImage(new Image(b_image));
       
            }
        });
        
        this.setOnMouseExited(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                gpiece temp = (gpiece)me.getTarget();
                temp.setImage(new Image(f_image));
       
            }
        });
        
    
             
        this.setOnMouseDragged(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent me){
                gpiece temp = (gpiece)me.getTarget();
                temp.setX(me.getSceneX());
                temp.setY(me.getSceneY());
            }
        });
    }
    
    
}