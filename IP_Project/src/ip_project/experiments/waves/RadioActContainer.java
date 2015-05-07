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
	
	public static final int NUMBER_OF_PARTICLES = 100;
	
	Slider mHalfLifeSlider;
	LineChart<Number, Number> mGraph;
	GridPane mParticleHolder;
	TranslateTransition mTimeKeep;
	
	
	public RadioActContainer(){
		super();
		
		mHalfLifeSlider = new Slider(0, 1, 0.5);
		mHalfLifeSlider.setShowTickMarks(true);
		mHalfLifeSlider.setShowTickLabels(true);
		mHalfLifeSlider.setMajorTickUnit(0.1);
		mHalfLifeSlider.setMinorTickCount(0);
		mHalfLifeSlider.setSnapToTicks(true);
		mHalfLifeSlider.setId(RAD_ACT_SLIDER_1_ID);
		
		this.addInputs(mHalfLifeSlider);
		
		
		//Graph set up
		String xAxisLabel = "Time";
		String yAxisLabel = "Number of Protons";
		
		NumberAxis xAxis = new NumberAxis(xAxisLabel, 0, 1000, 100);
		NumberAxis yAxis = new NumberAxis(yAxisLabel, 700, 800 , 10);
		
//		xAxis.setAutoRanging(false);
//		yAxis.setAutoRanging(false);
		
		mGraph = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.addGraphs(mGraph);
		
		//set up animations
		mParticleHolder = new GridPane();
		
		mTimeKeep = new TranslateTransition(Duration.INDEFINITE, mParticleHolder);
		mTimeKeep.setCycleCount(1);
		mTimeKeep.setInterpolator(new RadioActInterpolator() );
		
		this.addAnimations(mTimeKeep);
		this.addAnimationElements(mParticleHolder);
	}
	
	
	@Override
	public void updateValues(){
		
		mParticleHolder.setMaxWidth(this.mCanvas.getWidth());
		mParticleHolder.setMaxHeight(this.mCanvas.getHeight());
		
		for(int i = 0, j = 0, a = 0 ;(i+1)*2*RAD_ACT_PARTICLE_RADIUS < this.mCanvas.getWidth() 
				|| (j+1)*2*RAD_ACT_PARTICLE_RADIUS < this.mCanvas.getHeight(); i++, a++){
			Circle circle = new Circle(RAD_ACT_PARTICLE_RADIUS, (a%2 == 0+j%2)?Color.BLUE : Color.RED);
			
			if(i*2*RAD_ACT_PARTICLE_RADIUS > this.mCanvas.getWidth()){
				i = 0;
				j++;
			}
			
			//set and update index values
			GridPane.setColumnIndex(circle, i);
			GridPane.setRowIndex(circle, j);
			
			mParticleHolder.getChildren().add(circle);
			
			TranslateTransition wigglingAnim = new TranslateTransition(Duration.millis(10), circle);
			wigglingAnim.setCycleCount(Animation.INDEFINITE);
			wigglingAnim.setByX(4);
			
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
			double value = (int)(mParticleHolder.getChildren().size()*Math.pow(Math.E, -t*1E12/(mHalfLifeSlider.getValue())));
			
			/*XYChart.Series<Number, Number> series = mGraph.getData().get(mGraph.getData().size()-1);
			series.getData().add(new Data<Number, Number>(t, t));
			*/
			
			XYChart.Series<Number, Number> series = mGraph.getData().get(
					mGraph.getData().size() - 1);

			series.getData().add(new Data<Number, Number>(t*10E16, value));
			
			
			
			//System.out.println(t + " + " + value);
			
			
			if(mOldValue > value){
				for(int i = 0; i < mOldValue-value; i++){
					
					Circle temp = (Circle)mParticleHolder.getChildren().get((int)(Math.random()*mParticleHolder.getChildren().size()));
					
//					int count = 0;
//					do{
//						temp = (Circle)mParticleHolder.getChildren().get((int)(Math.random()*mParticleHolder.getChildren().size()));
//						if(count > 7){
//							break;
//						}else{
//							count++;
//						}
//					}while(temp.getOpacity() == 0);
					
					FadeTransition ft = new FadeTransition(Duration.millis(50), temp);
					
					ft.setCycleCount(1);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
				}
				mOldValue = value;
				
				if(value < 1){
					value = 0;
					mTimeKeep.stop();
				}
			}
			return value;
		}
	}
	
	
}
