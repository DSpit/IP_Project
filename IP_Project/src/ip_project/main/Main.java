

package ip_project.main;
	
import ip_project.experiments.Test;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class which contains graphical and structural necessities. 
 */
public class Main extends Application implements EventHandler<ActionEvent>{
	
	private Button mStart;
	private Button mRestart;
	private Button mDone;
	private Button mContinue;
	private Button mPause;
	private Button mHelp;
	private Button mExit;
	private MIContainer mContainer;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//root setup
			StackPane root = new StackPane();
			root.setAlignment(Pos.CENTER);
			
			VBox centeredPane = new VBox();
			centeredPane.setMinSize(1500, 900);		//TODO <arbitrary> take these values and put them in an interface for general access
			centeredPane.setMaxSize(1500, 900);
			
			root.getChildren().add(centeredPane);
			
			
/*			//DEBug
			Rectangle test = new Rectangle();
			test.setStyle("-fx-background-color: red;");
			test.setWidth(500);
			test.setHeight(700);
			
			Rectangle test2 = new Rectangle();
			test2.setStyle("-fx-background-color: red;");
			test2.setHeight(100);
			test2.setWidth(1000);
			root.getChildren().addAll(test, test2);*/
			
			//setup buttons
			mStart = new Button("Start");	//TODO make constant in Res (all buttons)
			mRestart = new Button("Restart");
			mDone = new Button("Done");
			mContinue = new Button("Continue");
			mPause = new Button("Pause");
			mHelp = new Button("Help");
			mExit = new Button("Exit");
			mStart.setOnAction(this);
			mRestart.setOnAction(this);
			mDone.setOnAction(this);
			mContinue.setOnAction(this);
			mPause.setOnAction(this);
			mHelp.setOnAction(this);
			mExit.setOnAction(this);
			
			//TODO create menu
			
	        MenuBar menuBar = new MenuBar();
	        
	        Menu mechMenu = new Menu("Mechanics");
	        Menu wavesMenu = new Menu("Waves");
	        Menu calcMenu = new Menu("Calculus");
	        Menu exitMenu = new Menu("Exit");
	        
	        MenuItem Newton = new MenuItem("Newton's Second Law");
	        MenuItem pMotion = new MenuItem("Projectile Motion");
	        MenuItem Optics = new MenuItem("Optics and Lenses");
	        MenuItem rDecay = new MenuItem("Radioactive Decay");
	        MenuItem gSeries = new MenuItem("Geometric Series");
	        MenuItem NSB = new MenuItem("New Sports Bike");
	        
	        mechMenu.getItems().addAll(Newton, pMotion);
	        wavesMenu.getItems().addAll(Optics, rDecay);
	        calcMenu.getItems().addAll(gSeries, NSB);
	        
	        // needs fixing ~a 
	        
	        exitMenu.setOnAction(new EventHandler<ActionEvent>() {
	            public void handle(ActionEvent t) {
	                System.exit(0);
	            }
	        });
	        
	        menuBar.getMenus().addAll(mechMenu, wavesMenu, calcMenu, exitMenu);
	        
	        // CHANGED TO VBOX INSTEAD OF PANE ~a
	        
			VBox tempMenu = new VBox();
			tempMenu.getChildren().add(menuBar);
			tempMenu.setPrefHeight(50);
			tempMenu.setStyle("-fx-background-color: darkgrey;");
			        
		
			
			//TODO create buttonBar (consider ToolBar (just saw something about it while looking for something else))
			Pane tempBBar = new Pane();
			tempBBar.getChildren().add(new Label("Button Bar"));
			tempBBar.setPrefHeight(75);
			tempBBar.setStyle("-fx-background-color: darkgrey;");
			
			//TODO create starting container
			mContainer = new Test();		//TODO change Test class to whatever container is opened first 
			VBox.setVgrow(mContainer, Priority.ALWAYS);
			mContainer.setStyle("-fx-background-color: blue");
			
			
			centeredPane.getChildren().addAll(tempMenu, mContainer, tempBBar);
			
			//set main scene
			Scene scene = new Scene(root);
			//scene.getStylesheets().add(getClass().getResource("ip_project.main.css").toExternalForm()); //TODO CSS
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(1000);	//TODO should have formula MIN_MAIN_PAIN_SIZE + 100 for a padded effect
			primaryStage.setMinWidth(1600);
			primaryStage.show();
			primaryStage.sizeToScene();
			
			primaryStage.setMaximized(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void handle(ActionEvent event) {
			
		switch(((Button)event.getSource()).getText()){
		case "Start"://TODO make constant (all cases)
			System.out.println(event.toString()); //DEBUG TODO remove
			mContainer.start();
			break;
			
		case "Exit":
			System.exit(0);
			break;
		
		case "Restart":
			mContainer.restart();
			break;
			
		case "Done":
			mContainer.done();
			break;
			
		case "Continue":
			mContainer.unpause();
			break;
			
		case "Help":
			mContainer.help();
			break;
			
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
