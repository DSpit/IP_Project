package ip_project.experiments.calculus;

import java.io.File;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Geometric Series Experiment
 */
public class GeomSeriesContainer extends MIContainer implements Resources {

	protected XYChart.Series<Number, Number> series1;
	protected TranslateTransition anim1, anim2;
	private FadeTransition anim3;
	protected Slider mSlider1, mSlider2;
	protected LineChart<Number, Number> mGraph1, mGraph2;
	private ImageView image, gif;

	public GeomSeriesContainer() {

		// example of slider set up
		mSlider1 = new Slider(GS_SLIDER_MIN_1, GS_SLIDER_MAX_1, GS_SLIDER_DEFAULT_1);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(0.1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);

		mSlider2 = new Slider(GS_SLIDER_MIN_2, GS_SLIDER_MAX_2, GS_SLIDER_DEFAULT_2);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(1);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);

		String Xstr1 = "Time";
		String Ystr1 = "X Position";

		// example of graph set up
		NumberAxis xAxis1 = new NumberAxis(Xstr1, GS_AXIS_X_MIN_1, GS_AXIS_X_MAX_1, GS_SPACING_AXIS_X);
		NumberAxis yAxis1 = new NumberAxis(Ystr1, GS_AXIS_Y_MIN_1, GS_AXIS_Y_MAX_1, GS_SPACING_AXIS_Y);

		yAxis1.setAutoRanging(false);
		xAxis1.setAutoRanging(false);

		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);

		this.addGraphs(mGraph1);

		// specify object- look and location
		Circle object1 = new Circle(10);
		object1.setFill(Color.BLUE);
		Image image1 = new Image("ip_project/icons/leaf.gif");
		image = new ImageView();

		image.setImage(image1);
		image.setFitHeight(GS_IMAGE_WIDTH);
		image.setFitWidth(GS_IMAGE_HEIGHT);
		image.setVisible(false);

		image.setTranslateX(GS_X_POSITION_1);
		image.setTranslateY(GS_Y_POSITION_1);
		
		Image image2 = new Image("ip_project/icons/snoopdog.gif");
		gif = new ImageView();
		gif.setImage(image2);
		gif.setTranslateX(GS_X_POSITION_2);
		gif.setTranslateY(GS_Y_POSITION_2);
		gif.setVisible(false);

		anim1 = new TranslateTransition(Duration.seconds(10), image);
		anim1.setInterpolator(new GeomSeriesXInterpolator());
		anim1.setFromX(GS_TRANSLATE_FROM_X_1);
		anim1.setCycleCount(1);
		anim1.setToX(GS_TRANSLATE_TO_X_1);

		anim2 = new TranslateTransition(Duration.seconds(10), image);
		anim2.setInterpolator(Interpolator.LINEAR); 
		anim2.setFromY(GS_TRANSLATE_FROM_Y_2);
		anim2.setToY(GS_TRANSLATE_TO_Y_2);
		
		anim3 = new FadeTransition(Duration.seconds(3), image);
		anim3.setInterpolator(Interpolator.LINEAR);
		anim3.setCycleCount(1);
		anim3.setFromValue(0);
		anim3.setToValue(1);

		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1, anim2, anim3);

		this.addAnimations(comboAnim);
		this.addAnimationElements(image);
		
		mCanvas.getChildren().add(gif);
		
		this.getStyleClass().add("geometric-series-canvas-1");


	}

	public double calculateYPosition(double percentage, double bounces,
			double time) {
		return INITIAL_HEIGHT * Math.pow(percentage, bounces)
				* Math.sin(Math.toRadians(360 * time * mSlider2.getValue()));
	}

	private class GeomSeriesXInterpolator extends Interpolator {

		@Override
		protected double curve(double t) {

			double fraction = 1 / mSlider2.getValue();

			double bounces = (int) (t / fraction);

			double value = calculateYPosition(mSlider1.getValue(), bounces, t);

			// get top level series
			XYChart.Series<Number, Number> series1 = mGraph1.getData().get(
					mGraph1.getData().size() - 1);

			series1.getData().add(new Data<Number, Number>(t, value));
			return value;
		}
	}

	public void updateValues() {
		anim1.setFromX((this.mCanvas.getWidth() / 2) - 15);
		anim1.setToX((this.mCanvas.getWidth() / 2) - 10);

		anim2.setToY(this.mCanvas.getHeight() - 100);
		
		image.setVisible(true);
		gif.setVisible(true);
		this.getStyleClass().add("geometric-series-canvas-2");
		
		String psyS = new File("src/ip_project/sounds/stilldre.mp3").toURI().toString(); 

        Media pick = new Media(psyS);
        MediaPlayer player = new MediaPlayer(pick);

        
        player.play();
		
		anim2.setOnFinished(new EventHandler<ActionEvent>() {
			
			 @Override
			 public void handle(ActionEvent event) {
				 
				 player.stop();

			 }
		 });
		
		
	}

	public String getTitle() {
		return GEOM_SER_TITLE;
	}

	public String getHelp() {
		return GEOM_SER_HELP;
	}

}
