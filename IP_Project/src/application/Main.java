

package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
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
			BorderPane root = new BorderPane();	//maybe make AnchorPane

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
			
			//TODO add buttons to root
			
			//TODO create menu
			
			//TODO create buttonBar
			
			//TODO create starting container
			
			//set main scene
			Scene scene = new Scene(root,400,400);		//TODO set to full size
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm()); //TODO CSS
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
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
