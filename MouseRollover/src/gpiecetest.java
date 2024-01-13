import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.geometry.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.input.*;
import gpiece_test.gpiece;
import gpiece_test.ginput;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;

/**
 *
 * @author jenre
 */
public class gpiecetest extends Application {
    
    int cwidth = 800;
    int cheight = 600;
    Group root;
    
    public void update(long timestamp){
        gpiece temp;
        for(Node node: root.getChildren()){
            if(node instanceof gpiece){
                temp = (gpiece) node;
                temp.update(this.cwidth, this.cheight);
            }
        }
    }
    
    @Override
    public void start(Stage stage){
        gpiece first_piece = new gpiece(100,100,50,50);
        gpiece second_piece = new gpiece(new Random().nextDouble(0, 800-50),new Random().nextDouble(0,600-50),50,50);
        
        Button button_add = new Button("Add");
        Button button_remove = new Button("Remove");
        
        Label label = new Label("How many? ");
        ginput input_qty = new ginput();
        Label label2 = new Label("Press A to make them run, and B to stop");
        
        HBox hb = new HBox(label2,label,input_qty, button_add, button_remove);
        hb.setPadding(new Insets(15,12,15,12));
        hb.setAlignment(Pos.TOP_LEFT);
        hb.setSpacing(10);
        
        root = new Group(hb);
        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Displaying Image");
        stage.setScene(scene);
        
        EventHandler<ActionEvent> eventadd = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                
                //read textfield to convert to int...
                int pieces = Integer.parseInt(input_qty.getText());
                
                //for each int pieces, add a child
                for(int i = 0; i < pieces; i++){
                    double width = new Random().nextDouble(5,100);
                    double lx = new Random().nextDouble(0, 800-width);
                    double ly = new Random().nextDouble(0, 600-width);
                    gpiece gtemp = new gpiece(lx, ly, width, width);
                    gtemp.setvelocity(new Random().nextDouble(0, 2.5), new Random().nextDouble(0,2.5));
                    root.getChildren().add(gtemp);
                }
                
            }
        };
        
        EventHandler<ActionEvent> eventremove = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
            //read textfield to convert to int...
            int pieces = Integer.parseInt(input_qty.getText());
            
            //for each int pieces, remove an equal amount of children
            ObservableList<Node> children = root.getChildren();
                for (int i = 0; i < pieces; i++) {
                    if (children.size() > 1) {
                        children.remove(children.size() - 1);
                    }
               }
            }
        };
        
        AnimationTimer timer = new AnimationTimer(){
            @Override
            public void handle(long arg0){
                update(arg0);
            }
        };
        
       

        scene.setOnKeyPressed(event ->{
            if(event.getCode() == KeyCode.A){
                timer.start();
            }
            if(event.getCode() == KeyCode.B){
                timer.stop();
            }
        });
        
        
        
        button_add.setOnAction(eventadd);
        button_remove.setOnAction(eventremove);
        
        stage.show();
    }
    
    public static void main(String[] args){
        launch(args);
    }
}