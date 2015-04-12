

package ip_project.main;
	
import ip_project.experiments.Test;
import ip_project.experiments.calculus.GeomSeriesContainer;
import ip_project.experiments.calculus.NewSportsBikeContainer;
import ip_project.experiments.mechanics.NewtonContainer;
import ip_project.experiments.mechanics.ProjContainer;
import ip_project.experiments.waves.OpticsContainer;
import ip_project.experiments.waves.RadioActContainer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Main class which contains graphical and structural necessities. 
 */
public class Main extends Application implements EventHandler<ActionEvent>, Resources{
	
	private Stage mStage;
	private Button mStart;
	private Button mReset;
	private Button mDone;
	private Button mContinue;
	private Button mPause;
	private Button mHelp;
	private Button mExit;
	private MIContainer mContainer;
	private VBox mCenteredPane;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			//assign primary stage to private member variable for future access
			mStage = primaryStage;
			
			//set main scene
			mStage.setScene(this.createScene());
			mStage.setMinHeight(WINDOW_HEIGHT);	//TODO should have formula MIN_MAIN_PAIN_SIZE + 100 for a padded effect
			mStage.setMinWidth(WINDOW_WIDTH);
			mStage.show();
			mStage.sizeToScene();
			
			mStage.setMaximized(true);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * populates mMenubar
	 */
	private MenuBar createMenu(){
		//create menu
        MenuBar menubar = new MenuBar();
        
        Menu mechMenu = new Menu("Mechanics");
        Menu wavesMenu = new Menu("Waves");
        Menu calcMenu = new Menu("Calculus");
        Label exitLabel = new Label("Exit");
        Menu exitMenu = new Menu();
        exitMenu.setGraphic(exitLabel);
        
        exitLabel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               System.exit(0);
            }
        });
        
        // TODO needs fixing, can't fucking figure out how to link a path from the git project to here ~a
        //menu items images
        Image apple = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/apple_64.png", 32, 32, false, false);
        Image projectile = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/file01_64.png", 32, 32, false, false);
        Image glasses = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/glasses_64.png", 32, 32, false, false);
        Image decay = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/molecule_64.png", 32, 32, false, false);
        Image ball = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/ball_64.png", 32, 32, false, false);
        Image bike = new Image("https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/abacus_64.png", 32, 32, false, false);
        
        //create menu items
        MenuItem newtonItem = new MenuItem(NEWTON_TITLE, new ImageView(apple));
        MenuItem pMotionItem = new MenuItem(PROJ_TITLE, new ImageView(projectile));
        MenuItem opticsItem = new MenuItem(OPTIC_TITLE, new ImageView(glasses));
        MenuItem rDecayItem = new MenuItem(RAD_ACT_TITLE, new ImageView(decay));
        MenuItem gSeriesItem = new MenuItem(GEOM_SER_TITLE, new ImageView(ball));
        MenuItem nSBikeItem = new MenuItem(BIKE_TITLE, new ImageView(bike));
        
        //handler for menu items
        MenuHandler mHandle = new MenuHandler();
        newtonItem.setOnAction(mHandle);
        pMotionItem.setOnAction(mHandle);
        opticsItem.setOnAction(mHandle);
        rDecayItem.setOnAction(mHandle);
        gSeriesItem.setOnAction(mHandle);
        nSBikeItem.setOnAction(mHandle);
        
        mechMenu.getItems().addAll(newtonItem, pMotionItem);
        wavesMenu.getItems().addAll(opticsItem, rDecayItem);
        calcMenu.getItems().addAll(gSeriesItem, nSBikeItem);
        
        menubar.getMenus().addAll(mechMenu, wavesMenu, calcMenu, exitMenu);
        
        return menubar;
	}
	
	/**
	 * populates mControlbar and control buttons (mStart, mContinue, ect)
	 */
	private StackPane createControl(){
		//setup buttons
		mStart = new Button("Start");	//TODO make constant in Res (all buttons)
		mStart.setStyle(buttonStyle);
		mReset = new Button("Reset");
		mReset.setStyle(buttonStyle);
		mReset.setDisable(true);
		mDone = new Button("Done");
		mDone.setStyle(buttonStyle);
		mDone.setDisable(true);
		mContinue = new Button("Continue");
		mContinue.setStyle(buttonStyle);
		mContinue.setDisable(true);
		mPause = new Button("Pause");
		mPause.setStyle(buttonStyle);
		mPause.setDisable(true);
		mHelp = new Button("Help");
		mHelp.setStyle(buttonStyle);
		mExit = new Button("Exit");
		mExit.setStyle(buttonStyle);
		
		//adds event handler for buttons
		mStart.setOnAction(this);
		mReset.setOnAction(this);
		mDone.setOnAction(this);
		mContinue.setOnAction(this);
		mPause.setOnAction(this);
		mHelp.setOnAction(this);
		mExit.setOnAction(this);
		
		//set up bar
		HBox buttonHolder = new HBox();
	    buttonHolder.setPadding(new Insets(15, 12, 15, 12));
		buttonHolder.setSpacing(50);
        buttonHolder.setAlignment(Pos.CENTER);

		
		buttonHolder.getChildren().addAll(mStart, mReset, mDone, mContinue, mPause, mHelp, mExit);
		
		
		StackPane controlbar = new StackPane();
		controlbar.setAlignment(Pos.CENTER);
		controlbar.getChildren().add(buttonHolder);
		controlbar.setPrefHeight(75);
		controlbar.setStyle("-fx-background-color: darkgrey;");
		
		return controlbar;
	}
	
	private Scene createScene(){
		//root setup
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		
		mCenteredPane = new VBox();
		mCenteredPane.setMinSize(1500, 900);		//TODO <arbitrary> take these values and put them in an interface for general access
		mCenteredPane.setMaxSize(1500, 900);
		
		root.getChildren().add(mCenteredPane);
		
		//create starting container
		mContainer = new Test();	//TODO change to starting experiment
		VBox.setVgrow(mContainer, Priority.ALWAYS);	
//		mContainer.setStyle("-fx-background-color: blue");
		
		mCenteredPane.getChildren().addAll(this.createMenu(), mContainer, this.createControl());
		
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("ip_project.main.css").toExternalForm()); //TODO CSS
		
		return scene;
	}
	
	private void updateContainer(MIContainer experiment){
		mCenteredPane.getChildren().remove(mContainer);
		mContainer = experiment;
		VBox.setVgrow(mContainer, Priority.ALWAYS);
		mCenteredPane.getChildren().add(1, mContainer);
	}
	
	@Override
	public void handle(ActionEvent event) {
			
		switch(((Button)event.getSource()).getText()){
		case "Start"://TODO make constant (all cases)
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(true);
			mPause.setDisable(false);
			mReset.setDisable(false);
			mDone.setDisable(false);
			
			mContainer.start();
			break;
			
		case "Exit":
			System.exit(0);
			break;
		
		case "Reset":
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(false);
			mPause.setDisable(true);
			mReset.setDisable(true);
			mDone.setDisable(false);
			
			mContainer.reset();
			break;
		
		case "Pause":
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(false);
			mPause.setDisable(true);
			mReset.setDisable(false);
			mDone.setDisable(false);
			
			mContainer.pause();
			break;
		
		case "Done":
			//update control bar
			mStart.setDisable(false);
			mContinue.setDisable(true);
			mPause.setDisable(true);
			mReset.setDisable(true);
			mDone.setDisable(true);
			
			mContainer.done();
			
			break;
			
		case "Continue":
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(true);
			mPause.setDisable(false);
			mReset.setDisable(false);
			mDone.setDisable(false);
			
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

			//checks if the the selected option is currently being displayed and doesn't do anything if it is
			if(mContainer.getTitle().equals(((MenuItem)event.getSource()).getText())){
				return;
			}
			
			switch(((MenuItem)event.getSource()).getText()){
			case NEWTON_TITLE:		
				updateContainer(new NewtonContainer());
				break;
			
			case PROJ_TITLE:
				updateContainer(new ProjContainer());
				break;
			
			case OPTIC_TITLE:
				updateContainer(new OpticsContainer());
				break;
				
			case RAD_ACT_TITLE:
				updateContainer(new RadioActContainer());
				break;
				
			case GEOM_SER_TITLE:
				updateContainer(new GeomSeriesContainer());
				break;
				
			case BIKE_TITLE:
				updateContainer(new NewSportsBikeContainer());
				break;
				
			case EXIT: 
				System.exit(0);
				break;
				
			default: 
				return;
			}
		}
		
	}
}
