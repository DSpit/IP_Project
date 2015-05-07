

package ip_project.main;

import ip_project.experiments.calculus.NewSportsBikeContainer;
import ip_project.experiments.mechanics.ProjContainer;

import java.util.ArrayList;

import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 */
public abstract class MIContainer extends VBox{
	
	private VBox mGContainer = new VBox();
	private HBox mIContainer = new HBox();
	protected AnchorPane mCanvas = new AnchorPane();
	private ArrayList<Slider> mInputs = new ArrayList<Slider>();
	private ArrayList<Transition> mAnimation = new ArrayList<Transition>();
	private ArrayList<LineChart<Number, Number>> mGraphs = new ArrayList<LineChart<Number,Number>>();
	private boolean mIsChanged = false;
	private int mExperimentCount = 1;
	private Button btn;
	
	public MIContainer(){
		super();
		
		StackPane titlebar = new StackPane();
		titlebar.setAlignment(Pos.CENTER_RIGHT);
		
		Label title = new Label(this.getTitle());
		
		titlebar.getChildren().add(title);
		
		TranslateTransition st = new TranslateTransition (Duration.millis(5000), title);
	    st.setToX(-java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);
	    st.setCycleCount(Timeline.INDEFINITE);
	    st.setAutoReverse(true);
	 
	    st.play();
		
		HBox content = new HBox();
		//create graph and input bar
		VBox bar = new VBox();
		bar.setPrefWidth(500);
		
		//create graph container
		mGContainer.getStyleClass().add("graph-container");
		mGContainer.setAlignment(Pos.CENTER);
		VBox.setVgrow(mGContainer, Priority.ALWAYS);
		
		//create input container
		mIContainer.setPrefHeight(150);
		mIContainer.setAlignment(Pos.CENTER);
		mIContainer.getStyleClass().add("linear-grad-repeat2");
		
		
		//add elements to bar
		bar.getChildren().addAll(mGContainer, mIContainer);
		
		//create animation pane
		mCanvas.getStyleClass().add("main-container");
		mCanvas.setId("canvas");
		HBox.setHgrow(mCanvas, Priority.ALWAYS);
		
		//add elements to the container
		content.getChildren().addAll(mCanvas, bar);
		VBox.setVgrow(content, Priority.ALWAYS);
		
		content.setPadding(new Insets(10, 10, 10, 10));
		content.setSpacing(10);
		bar.setSpacing(10);
		
		this.getChildren().addAll(titlebar, content);
	}
	
	public void addInputs(Slider... inputs){
		for(Slider s : inputs){
			mInputs.add(s);
			HBox c = new HBox();
			Label l = new Label(String.valueOf(s.getValue()));
			s.valueProperty().addListener(new ValueChangeListener(l));
			
			c.getChildren().addAll(s, l);
			c.setAlignment(Pos.CENTER);
			
			mIContainer.getChildren().add(c);
		}
	}
	
	public void addGraphs(LineChart<Number, Number>... graphs){
		for(LineChart<Number, Number> c : graphs){
			mGraphs.add(c);
			c.setCreateSymbols(false);
			
		}
		mGContainer.getChildren().addAll(graphs);
	}
	
	public void addAnimationElements(Node... node){
		mCanvas.getChildren().addAll(node);
	}
	
	public void addAnimations(Transition... animations){
		for(Transition animation: animations){
			mAnimation.add(animation);
		}
	}
	
	public void help(){
		
		
		btn = new Button("Ok");
		
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create().
		    children(new Text(this.getHelp()), btn).
		    alignment(Pos.CENTER).padding(new Insets(5)).build()));
		

		   btn.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent event) {
	                dialogStage.close();
	            }
	        });
		
		
		dialogStage.show();
		
		
	}
	
	public void start(){
		
		//create new series of points
		for(int i = 0; i < mGraphs.size(); i++){
			XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
			series.setName("Exp: " + mExperimentCount);
			mGraphs.get(i).getData().add(series);
		}
		
		for(Slider input: mInputs){
			input.setDisable(true);		
		}
		
		for(Transition animation : mAnimation){
			
			this.updateValues();

			
			animation.playFromStart();
		}
		
		//makes sure that change flag is set to false
		mIsChanged = false;
	}
	
	public void done(){
		
		((NewSportsBikeContainer)this).stopAnimation();

		
		for(Slider input : mInputs){
			input.setDisable(false);
			input.setOnMouseReleased(null);
		}
		for(Transition animation: mAnimation){
			animation.playFromStart();
			animation.stop();
			
			
		
		}
		
		//removes all the previous experiments
		for(LineChart<Number, Number> graph : mGraphs){
			int dataSetCount = graph.getData().size();
			for(int i = 0; i < dataSetCount; ++i){
				graph.getData().remove(0);
			}
		}
		
		//resets the experiment count
		mExperimentCount = 1;
		
		//makes sure that change flag is set to false
		mIsChanged = false;
	}
	
	public void unpause(){	//Note: Could not use "Continue" because clash with reserved syntax
		for(Slider input: mInputs){
			input.setDisable(true);
			input.setOnMouseReleased(null);
		}
		
		if(mIsChanged){
			//TODO create new trial in graphs
			this.start();
		}else{
			for(Transition animation : mAnimation){
				
				this.updateValues();

				animation.play();
			}
		}
	}
		
	public void pause(){
		
		((NewSportsBikeContainer)this).stopAnimation();

		
		for(Slider input: mInputs){
			input.setDisable(true);
		}
		
		for(Transition animation : mAnimation){
			animation.pause();
		}
		
		//makes sure that change flag is set to false
		mIsChanged = false;
	}
	
	public void reset(){ 
		for(Slider input : mInputs){
			input.setDisable(false);
			input.setOnMouseReleased(new InputChangeHandler(input.getValue()));
		}
		for(Transition animation : mAnimation){
			animation.pause();
		}
		
		//increment experiment
		mExperimentCount++;
	}
	
	abstract public String getTitle();
	abstract public void  updateValues();
	abstract public String getHelp();
	
	
	/**
	 * Listener which watches for changes in input slider values
	 */
	private class InputChangeHandler implements EventHandler<MouseEvent>{

		private double mOriginal;
		
		public InputChangeHandler(double original){
			mOriginal = original;
		}
		
		@Override
		public void handle(MouseEvent event) {
			//if the slider value is different from the original value then set the changed flag to true
			if(((Slider)event.getSource()).getValue() != mOriginal){
				mIsChanged = true;
			}else{
				mIsChanged = false;
			}
		}
	}
	
	
	
	
	private class ValueChangeListener implements ChangeListener<Number>{
		
		private Label mValueDisplay;
		
		public ValueChangeListener(Label l){
			mValueDisplay = l;
		}

		@Override
		public void changed(ObservableValue<? extends Number> ov,
				Number oldNum, Number newNum) {
			mValueDisplay.setText(String.valueOf((double)Math.round(10 * newNum.doubleValue()) / 10));
		}
	}
}
