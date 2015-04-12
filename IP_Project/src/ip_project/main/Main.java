

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
	private Label mTag;
	private MIContainer mContainer;
	private MenuBar mMenubar;
	private StackPane mControlbar;
	
	@Override
	public void start(Stage primaryStage) {
		
		try {
			
			this.createMenu();
			this.createControl();
			
			//assign primary stage to private member variable for future access
			mStage = primaryStage;
			
			//set main scene
			mStage.setScene(this.createScene(new Test()));
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
	private void createMenu(){
		//create menu
        mMenubar = new MenuBar();
        
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
        MenuItem newtonItem = new MenuItem("Newton's Second Law", new ImageView(apple));
        MenuItem pMotionItem = new MenuItem("Projectile Motion", new ImageView(projectile));
        MenuItem opticsItem = new MenuItem("Optics and Lenses", new ImageView(glasses));
        MenuItem rDecayItem = new MenuItem("Radioactive Decay", new ImageView(decay));
        MenuItem gSeriesItem = new MenuItem("Geometric Series", new ImageView(ball));
        MenuItem nSBikeItem = new MenuItem("New Sports Bike", new ImageView(bike));
        
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
        
        mMenubar.getMenus().addAll(mechMenu, wavesMenu, calcMenu, exitMenu);
	}
	
	/**
	 * populates mControlbar and control buttons (mStart, mContinue, ect)
	 */
	private void createControl(){
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
		
		
		mControlbar = new StackPane();
		mControlbar.setAlignment(Pos.CENTER);
		mControlbar.getChildren().add(buttonHolder);
		mControlbar.setPrefHeight(75);
		mControlbar.setStyle("-fx-background-color: darkgrey;");
	}
	
	private Scene createScene(MIContainer experiments){
		//root setup
		StackPane root = new StackPane();
		root.setAlignment(Pos.CENTER);
		
		VBox centeredPane = new VBox();
		centeredPane.setMinSize(1500, 900);		//TODO <arbitrary> take these values and put them in an interface for general access
		centeredPane.setMaxSize(1500, 900);
		
		root.getChildren().add(centeredPane);
		
		//create starting container
		mContainer = experiments;
		VBox.setVgrow(mContainer, Priority.ALWAYS);
//		mContainer.setStyle("-fx-background-color: blue");
		
		centeredPane.getChildren().addAll(mMenubar, mContainer, mControlbar);
		
		Scene scene = new Scene(root);
		//scene.getStylesheets().add(getClass().getResource("ip_project.main.css").toExternalForm()); //TODO CSS
		
		return scene;
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

			boolean isMax = mStage.isMaximized();
			Scene newScene = null;
			
			switch(((MenuItem)event.getSource()).getText()){
			case "Newton's Second Law":		
				newScene = createScene(new NewtonContainer());
				break;
			
			case "Projectile Motion":
				newScene = createScene(new ProjContainer());
				break;
			
			case "Optics and Lenses":
				newScene = createScene(new OpticsContainer());
				break;
				
			case "Radioactive Decay":
				newScene = createScene(new RadioActContainer());
				break;
				
			case "Geometric Series":
				newScene = createScene(new GeomSeriesContainer());
				break;
				
			case "New Sports Bike":
				newScene = createScene(new NewSportsBikeContainer());
				break;
				
			case "Exit": 
				System.exit(0);
				break;
				
			default: 
				return;
			}
			
			if()
			
			//scene switching un-maximizing stage bug workaround
			mStage.setMaximized(isMax);
		}
		
	}
}
