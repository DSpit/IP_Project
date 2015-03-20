

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
			
			//create menu
	        MenuBar menuBar = new MenuBar();
	        
	        Menu mechMenu = new Menu("Mechanics");
	        Menu wavesMenu = new Menu("Waves");
	        Menu calcMenu = new Menu("Calculus");
	        Menu exitMenu = new Menu("Exit");
	        
	        // needs fixing, can't fucking figure out how to link a path from the git project to here ~a
	        //menu items images
	        Image apple = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/apple_64.png", 32, 32, false, false);
	        Image projectile = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/file01_64.png", 32, 32, false, false);
	        Image glasses = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/glasses_64.png", 32, 32, false, false);
	        Image decay = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/molecule_64.png", 32, 32, false, false);
	        Image ball = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/ball_64.png", 32, 32, false, false);
	        Image bike = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/abacus_64.png", 32, 32, false, false);
	        
	        //create menu items
	        MenuItem newtonItem = new MenuItem("Newton's Second Law", new ImageView(apple));
	        MenuItem pMotionItem = new MenuItem("Projectile Motion", new ImageView(projectile));
	        MenuItem opticsItem = new MenuItem("Optics and Lenses", new ImageView(glasses));
	        MenuItem rDecayItem = new MenuItem("Radioactive Decay", new ImageView(decay));
	        MenuItem gSeriesItem = new MenuItem("Geometric Series", new ImageView(ball));
	        MenuItem nSBikeItem = new MenuItem("New Sports Bike", new ImageView(bike));
	        MenuItem exitItem = new MenuItem("Exit");
	        
	        //handler for menu items
	        MenuHandler mHandle = new MenuHandler();
	        newtonItem.setOnAction(mHandle);
	        // DO THIS FOR EVERY MENU ITEM ~d
	        exitItem.setOnAction(mHandle);
	        
	        
	        mechMenu.getItems().addAll(newtonItem, pMotionItem);
	        wavesMenu.getItems().addAll(opticsItem, rDecayItem);
	        calcMenu.getItems().addAll(gSeriesItem, nSBikeItem);
	        exitMenu.getItems().addAll(exitItem);
	        
	        menuBar.getMenus().addAll(mechMenu, wavesMenu, calcMenu, exitMenu);
	        
	        // CHANGED TO VBOX INSTEAD OF PANE ~a
			
			//TODO create starting container
			mContainer = new Test();		//TODO change Test class to whatever container is opened first 
			VBox.setVgrow(mContainer, Priority.ALWAYS);
			mContainer.setStyle("-fx-background-color: blue");
			
			//TODO create buttonBar (consider ToolBar (just saw something about it while looking for something else))
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
			
			//temporary pane to represent button bar
			Pane tempBBar = new Pane();
			tempBBar.getChildren().add(new Label("Button Bar"));
			tempBBar.setPrefHeight(75);
			tempBBar.setStyle("-fx-background-color: darkgrey;");
			
			
			centeredPane.getChildren().addAll(menuBar, mContainer, tempBBar);
			
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
	
	private class MenuHandler implements EventHandler<ActionEvent>{

		@Override
		public void handle(ActionEvent event) {
			
			switch(((MenuItem)event.getSource()).getText()){
			case "Newton's Second Law":
				mContainer = new Test();	//TODO change to newtonsExperiment
				break;
				
			case "Exit": 
				System.exit(0);
				break;
			}
		}
		
	}
}
