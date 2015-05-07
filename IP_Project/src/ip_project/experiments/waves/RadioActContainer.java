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
		
		NumberAxis xAxis = new NumberAxis(xAxisLabel, 0, 1, 0.1);
		NumberAxis yAxis = new NumberAxis(yAxisLabel, 0, 0.0001, 0.000001);
		
//		xAxis.setAutoRanging(false);
//		yAxis.setAutoRanging(false);
		
		mGraph = new LineChart<Number, Number>(xAxis, yAxis);
		
		this.addGraphs(mGraph);
		
		//set up animations
		mParticleHolder = new GridPane();
		
		TranslateTransition timeKeep = new TranslateTransition(Duration.INDEFINITE, mParticleHolder);
		timeKeep.setCycleCount(1);
		timeKeep.setInterpolator(new RadioActInterpolator() );
		
		this.addAnimations(timeKeep);
		this.addAnimationElements(mParticleHolder);
	}
	
	
	@Override
	public void updateValues(){
		
		//populates the particles based on alowed size of canvas
		int count = 0;
		int i = 0;
		int j = 0;
		
//		mParticleHolder.setMaxWidth(this.mCanvas.getWidth());
//		mParticleHolder.setMaxHeight(this.mCanvas.getHeight());
		
		while(true){
			Circle circle = new Circle(RAD_ACT_PARTICLE_RADIUS, (count%2 == 0+(int)(count/Math.sqrt(NUMBER_OF_PARTICLES))%2)?Color.BLUE : Color.RED);
			
			if((count+1)*2*RAD_ACT_PARTICLE_RADIUS > this.mCanvas.getHeight()){
				break;
			}else{
				count++;
			}
			
			GridPane.setColumnIndex(circle, ((i*RAD_ACT_PARTICLE_RADIUS*2 < this.mCanvas.getWidth())? i++ : ++i ));
			GridPane.setRowIndex(circle, j );
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
	
	
	private class RadioActInterpolator extends Interpolator{
		
		private final int mOriginalNumParticles;
		private double mOldValue;
		
		public RadioActInterpolator(){
			mOriginalNumParticles = mParticleHolder.getChildren().size();
			mOldValue = mOriginalNumParticles;
		}
		
		@Override
		protected double curve(double t){
			double value = (mOriginalNumParticles*Math.pow(mHalfLifeSlider.getValue()*60*60*24*7*52*1E17, -t*60*60*24*7*52*1E15/(mHalfLifeSlider.getValue()*1E10)));
			
			XYChart.Series<Number, Number> series = mGraph.getData().get(mGraph.getData().size()-1);
			//System.out.println(series.toString());
			series.getData().add(new Data<Number, Number>(t, value));
			
			if(mOldValue > value){
				for(int i = 0; i < mOldValue-value; i++){
					FadeTransition ft = new FadeTransition(Duration.millis(50),
					mParticleHolder.getChildren().get((int)(Math.random()*mParticleHolder.getChildren().size())));
					ft.setCycleCount(1);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.play();
				}
				mOldValue = value;
			}
			return value;
		}
	}
	
	
}
