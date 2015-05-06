package ip_project.experiments.calculus;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Geometric Series Experiment
 */
public class GeomSeriesContainer extends MIContainer implements Resources {

	XYChart.Series<Number, Number> series1;
	TranslateTransition anim1, anim2;
	Slider mSlider1, mSlider2;
	LineChart<Number, Number> mGraph1, mGraph2;
	ImageView image;

	public GeomSeriesContainer() {

		// example of slider set up
		mSlider1 = new Slider(0, 1, 0.75);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(0.1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);

		mSlider2 = new Slider(3, 10, 5);
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
		NumberAxis xAxis1 = new NumberAxis(Xstr1, 0, 1, 0.1);
		NumberAxis yAxis1 = new NumberAxis(Ystr1, -50, 50, 5);

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
		image.setFitHeight(100);
		image.setFitWidth(100);

		image.setTranslateX(10);
		image.setTranslateY(700);

		anim1 = new TranslateTransition(Duration.seconds(5), image);
		anim1.setInterpolator(new GeomSeriesXInterpolator());
		anim1.setFromX(15);
		anim1.setCycleCount(1);

		anim1.setToX(356);

		anim2 = new TranslateTransition(Duration.seconds(5), image);
		anim2.setInterpolator(Interpolator.LINEAR); // this is where you put in
													// the custom interpolator
		anim2.setFromY(100);
		anim2.setCycleCount(1);

		anim2.setToY(700);

		// set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1, anim2);

		this.addAnimations(comboAnim);
		this.addAnimationElements(image);


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

		image.setFitHeight(100);
		image.setFitHeight(80);
	}

	public String getTitle() {
		return GEOM_SER_TITLE;
	}

	public String getHelp() {
		return GEOM_SER_HELP;
	}

}
