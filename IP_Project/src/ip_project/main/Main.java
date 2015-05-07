

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
import javafx.geometry.Rectangle2D;
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
import javafx.stage.Screen;
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
			
			Screen screen = Screen.getPrimary();
			
			Rectangle2D bounds = screen.getVisualBounds();
			
			//assign primary stage to private member variable for future access
			mStage = primaryStage;
			
			//set main scene

			mStage.setX((int) ZERO_DEFAULT);
			mStage.setY((int) ZERO_DEFAULT);
//			mStage.setMinHeight(WINDOW_HEIGHT);
//			mStage.setMinWidth(WINDOW_WIDTH);
			mStage.setWidth(bounds.getWidth()/HALF_FACTOR);
			mStage.setHeight(bounds.getHeight()/HALF_FACTOR);
			mStage.show();
			mStage.sizeToScene();
			mStage.setScene(this.createScene());
			
	        mStage.getIcons().add(new Image(ICON_PATH));
			mStage.setTitle("IP Project"); 
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
               System.exit((int) ZERO_DEFAULT);
            }
        });
        
        //menu items images
        Image apple = new Image(APPLE_IMAGE_PATH, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image projectile = new Image(PROJ_IMAGE_PATH, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image glasses = new Image(GLASSES_IMAGE_PATH, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image decay = new Image(DECAY_IMAGE_PATH, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image ball = new Image(BALL_IMAGE_PATH, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        Image bike = new Image(BIKE_IMAGE_PATH, MENU_THUMBNAIL_SIZE, MENU_THUMBNAIL_SIZE, false, false);
        
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
		mReset = new Button(RESET_TITLE);
		mReset.setDisable(true);
		mDone = new Button(DONE_TITLE);
		mDone.setDisable(true);
		mContinue = new Button(CONTINUE_TITLE);
		mContinue.setDisable(true);
		mPause = new Button(PAUSE_TITLE);
		mPause.setDisable(true);
		mHelp = new Button(HELP_TITLE);
		mExit = new Button(EXIT);
		
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
		controlbar.setPrefHeight(CONTROL_HEIGHT);
		controlbar.setPadding(new Insets(10, 10, 10, 10));
		controlbar.getStyleClass().add("button-container");
		
		return controlbar;
	}
	
	private Scene createScene(){
		
		mCenteredPane = new VBox();
//		mCenteredPane.setMinSize(CONTENT_WIDTH, CONTENT_HEIGHT);
//		mCenteredPane.setMaxSize(CONTENT_WIDTH, CONTENT_HEIGHT);
		
		//root setup
		mCenteredPane.setAlignment(Pos.CENTER);
		
		//create starting container
		mContainer = new NewtonContainer();
		VBox.setVgrow(mContainer, Priority.ALWAYS);	
		
		mCenteredPane.getChildren().addAll(this.createMenu(), mContainer, this.createControl());
		
		Scene scene = new Scene(mCenteredPane);
		scene.getStylesheets().add("ip_project/main/application.css");
		
		return scene;
	}
	
	private void updateContainer(MIContainer experiment){
		mDone.fire();
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
