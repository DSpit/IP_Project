

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
			mStage.setMinHeight(WINDOW_HEIGHT);
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
        
        Menu mechMenu = new Menu(MECH_MENU);
        Menu wavesMenu = new Menu(WAVES_MENU);
        Menu calcMenu = new Menu(CALC_MENU);
        Label exitLabel = new Label(EXIT);
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
        Image apple = new Image(APPLE_IMAGE_URL, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image projectile = new Image(PROJ_IMAGE_URL, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image glasses = new Image(GLASSES_IMAGE_URL, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image decay = new Image(DECAY_IMAGE_URL, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image ball = new Image(BALL_IMAGE_URL, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image bike = new Image(BIKE_IMAGE_URL, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        
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
		mStart = new Button(START_TITLE);
		mStart.setStyle(buttonStyle);
		mReset = new Button(RESET_TITLE);
		mReset.setStyle(buttonStyle);
		mReset.setDisable(true);
		mDone = new Button(DONE_TITLE);
		mDone.setStyle(buttonStyle);
		mDone.setDisable(true);
		mContinue = new Button(CONTINUE_TITLE);
		mContinue.setStyle(buttonStyle);
		mContinue.setDisable(true);
		mPause = new Button(PAUSE_TITLE);
		mPause.setStyle(buttonStyle);
		mPause.setDisable(true);
		mHelp = new Button(HELP_TITLE);
		mHelp.setStyle(buttonStyle);
		mExit = new Button(EXIT);
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
	    buttonHolder.setPadding(new Insets(CONTROL_BUTTON_PADDING));
		buttonHolder.setSpacing(CONTROL_BUTTON_SPACING);
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
		mCenteredPane.setMinSize(CONTENT_WIDTH, CONTENT_HEIGHT);
		mCenteredPane.setMaxSize(CONTENT_WIDTH, CONTENT_HEIGHT);
		
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
		case START_TITLE:
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(true);
			mPause.setDisable(false);
			mReset.setDisable(false);
			mDone.setDisable(false);
			
			mContainer.start();
			break;
			
		case EXIT:
			System.exit(0);
			break;
		
		case RESET_TITLE:
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(false);
			mPause.setDisable(true);
			mReset.setDisable(true);
			mDone.setDisable(false);
			
			mContainer.reset();
			break;
		
		case PAUSE_TITLE:
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(false);
			mPause.setDisable(true);
			mReset.setDisable(false);
			mDone.setDisable(false);
			
			mContainer.pause();
			break;
		
		case DONE_TITLE:
			//update control bar
			mStart.setDisable(false);
			mContinue.setDisable(true);
			mPause.setDisable(true);
			mReset.setDisable(true);
			mDone.setDisable(true);
			
			mContainer.done();
			
			break;
			
		case CONTINUE_TITLE:
			//update control bar
			mStart.setDisable(true);
			mContinue.setDisable(true);
			mPause.setDisable(false);
			mReset.setDisable(false);
			mDone.setDisable(false);
			
			mContainer.unpause();
			break;
			
		case HELP_TITLE:
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
