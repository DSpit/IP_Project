package ip_project.experiments.waves;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * The Radio Activity Experiment
 */
public class RadioActContainer extends MIContainer implements Resources{
	
	
	Slider mHalfLifeSlider;
	LineChart<Number, Number> mGraph;
	GridPane mParticleHolder;
	TranslateTransition mTimeKeep;
	
	
	public RadioActContainer(){
		super();
		
		mHalfLifeSlider = new Slider(RAD_ACT_SLIDER_MIN,
				RAD_ACT_SLIDER_MAX, RAD_ACT_SLIDER_DEFAULT);
		mHalfLifeSlider.setShowTickMarks(true);
		mHalfLifeSlider.setShowTickLabels(true);
		mHalfLifeSlider.setMajorTickUnit(TICK_UNIT_1);
		mHalfLifeSlider.setMinorTickCount(ZERO_DEFAULT);
		mHalfLifeSlider.setSnapToTicks(true);
		mHalfLifeSlider.setId(RAD_ACT_SLIDER_1_ID);
		
		this.addInputs(mHalfLifeSlider);
		
		
		//Graph set up
		String xAxisLabel = RAD_ACT_X_AXIS_LABEL;
		String yAxisLabel = RAD_ACT_Y_AXIS_LABEL;
		
		NumberAxis xAxis = new NumberAxis(xAxisLabel, RAD_ACT_X_AXIS_MIN, RAD_ACT_X_AXIS_MAX, RAD_ACT_X_AXIS_TICK);
		NumberAxis yAxis = new NumberAxis(yAxisLabel, RAD_ACT_Y_AXIS_MIN, RAD_ACT_Y_AXIS_MAX , RAD_ACT_Y_AXIS_TICK);
		
		mGraph = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.addGraphs(mGraph);
		
		//set up animations
		mParticleHolder = new GridPane();
		
		mTimeKeep = new TranslateTransition(Duration.INDEFINITE, mParticleHolder);
		mTimeKeep.setCycleCount(ONE_DEFAULT);
		mTimeKeep.setInterpolator(new RadioActInterpolator() );
		
		this.addAnimations(mTimeKeep);
		this.addAnimationElements(mParticleHolder);
	}
	
	
	@Override
	public void updateValues(){
		
		mParticleHolder.setMaxWidth(this.mCanvas.getWidth());
		mParticleHolder.setMaxHeight(this.mCanvas.getHeight());
		
		for(int i = ZERO_DEFAULT, j = ZERO_DEFAULT, a = ZERO_DEFAULT ;(i+ONE_DEFAULT)*HALF_FACTOR*RAD_ACT_PARTICLE_RADIUS < this.mCanvas.getWidth() 
				|| (j+ONE_DEFAULT)*2*RAD_ACT_PARTICLE_RADIUS < this.mCanvas.getHeight(); i++, a++){
			Circle circle = new Circle(RAD_ACT_PARTICLE_RADIUS, (a%HALF_FACTOR == 0+j%HALF_FACTOR)?Color.BLUE : Color.RED);
			
			if(i*HALF_FACTOR*RAD_ACT_PARTICLE_RADIUS > this.mCanvas.getWidth()){
				i = ZERO_DEFAULT;
				j++;
			}
			
			//set and update index values
			GridPane.setColumnIndex(circle, i);
			GridPane.setRowIndex(circle, j);
			
			mParticleHolder.getChildren().add(circle);
			
			TranslateTransition wigglingAnim = new TranslateTransition(Duration.INDEFINITE, circle);
			wigglingAnim.setCycleCount(Animation.INDEFINITE);
			wigglingAnim.setByX(RAD_ACT_WIGGLE_DISTANCE);
			
			wigglingAnim.play();
			
		}
		
		
		
	}
	
	
	public String getTitle(){
		return RAD_ACT_TITLE;
	}
	
	
	public String getHelp(){
		return RAD_ACT_HELP;
	}
	
	@Override
	public void done(){
		super.done();
		
		mParticleHolder.getChildren().removeAll(mParticleHolder.getChildren());
	}
	private class RadioActInterpolator extends Interpolator{
		
		private double mOldValue;
		
		@Override
		protected double curve(double t){
			mOldValue = mParticleHolder.getChildren().size();
			double value = (int)(mParticleHolder.getChildren().size()*Math.pow(Math.E, -t*RAD_ACT_VISUAL_CONSTANT/(mHalfLifeSlider.getValue())));
			
			XYChart.Series<Number, Number> series = mGraph.getData().get(
					mGraph.getData().size() - ONE_DEFAULT);

			series.getData().add(new Data<Number, Number>(t*RAD_ACT_VISUAL_CONSTANT_2, value));
						
			
			if(mOldValue > value){
				for(int i = ZERO_DEFAULT; i < mOldValue-value; i++){
					
					Circle temp = (Circle)mParticleHolder.getChildren().get((int)(Math.random()*mParticleHolder.getChildren().size()));
					
					
					FadeTransition ft = new FadeTransition(Duration.millis(RAD_ACT_FADE_TIME), temp);
					
					ft.setCycleCount(ONE_DEFAULT);
					ft.setFromValue(ONE_DEFAULT);
					ft.setToValue(ZERO_DEFAULT);
					ft.play();
				}
				mOldValue = value;
				
				if(value < ONE_DEFAULT){
					value = ZERO_DEFAULT;
					mTimeKeep.stop();
				}
			}
			return value;
		}
	}
	
	
}
