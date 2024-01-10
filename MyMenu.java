/* 
    Simple menu application 
    Menu options include: display and save date/time, change background color and exit appplication 
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MyMenu extends Application  {

    // Creating application's main stage
    @Override public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Main Menu");

        // Creating menu items
        MenuItem menuItem1 = new MenuItem("Display Date & Time");
        MenuItem menuItem2 = new MenuItem("Save Date & Time");
        MenuItem menuItem3 = new MenuItem("Change Background Color");
        MenuItem menuItem4 = new MenuItem("Exit Menu");

        // Adding the menu button using a HBox display
        MenuButton menuButton = new MenuButton("Menu", null, menuItem1, menuItem2, menuItem3, menuItem4);
        HBox hbox = new HBox(menuButton);
        Scene scene = new Scene(hbox, 200, 200);

        // Getting and storing current date/time value
        DateTimeFormatter dateTime = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
        LocalDateTime currentDate = LocalDateTime.now();  
        
        // Event handler for menu option 1 - display current date and time
        menuItem1.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
            
            @Override public void handle(ActionEvent event) { 

                // Adding label and textfield
                Label label1= new Label("Current Date & Time:");
                TextField textField = new TextField (dateTime.format(currentDate));
                textField.setEditable(false);

                // Adding button to return to menu options
                Button button1= new Button("Back to Menu");
                button1.setOnAction(e -> primaryStage.setScene(scene));  

                // Adding label, textfield and button to scene
                VBox layout1 = new VBox(20);     
                layout1.getChildren().addAll(label1, textField, button1);
                Scene scene2 = new Scene(layout1, 300, 250);
                primaryStage.setScene(scene2);

            }

        });


        // Event handler for menu option 2 - printing current date/time to log.txt file
        menuItem2.setOnAction(event -> {

            try (PrintWriter out = new PrintWriter(new BufferedWriter( 
            new FileWriter("log.txt", true)))) {
                    out.println(dateTime.format(currentDate));
            } 
            catch (IOException ioe) {
                ioe.printStackTrace();
            }

        });


        // Event handler for menu option 3 - randomly change background color
        menuItem3.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {    
            @Override public void handle(ActionEvent event) {

                // Adding new BorderPane
                BorderPane pane = new BorderPane();

                // Getting random hue color of green (120)
                Random random = new Random();
               int randIntGreen = random.nextInt(255);

                // Adding button to return to menu options
                Button button2 = new Button("Back to Menu");
                button2.setOnAction(e -> primaryStage.setScene(scene)); 
                pane.setCenter(button2);

                // Displaying the changed background color in BorderPane
                pane.setStyle( 
                    String.format("-fx-background-color: rgb(%d,%d,%d)",
                    0,
                    randIntGreen,
                    0
                    ) 
                );

                // Setting the scene
                Scene scene3 = new Scene(pane, 250, 250);
                primaryStage.setScene(scene3);
            }
        });


        // Event handler for menu option 4 - close application
        menuItem4.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
           
            @Override public void handle(ActionEvent event) {
                System.exit(0);
            }

        });
        
        // Setting primary scene and displaying it
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

        // Launch application
        Application.launch(args);
    }
}