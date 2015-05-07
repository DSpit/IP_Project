package ip_project.experiments.calculus;

import com.sun.javafx.geom.Rectangle;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * New Sports Bike Calculus Experiment
 */

public class NewSportsBikeContainer extends MIContainer implements Resources {

	// XYChart.Series<Number, Number> series1, series2;

	XYChart.Series<String, Number> series3; 
	Slider mSlider1, mSlider2;
	TranslateTransition anim1;
	Timeline anim2;
	Image[] imgs = new Image[7];
	ImageView Face = new ImageView();
	LineChart<Number, Number> mGraph1, mGraph2;

	public NewSportsBikeContainer() {

		// example of slider set up
		mSlider1 = new Slider(NSB_SLIDER_MIN_1, NSB_SLIDER_MAX_1, NSB_SLIDER_DEFAULT_1);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1000);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);

		mSlider2 = new Slider(NSB_SLIDER_MIN_2, NSB_SLIDER_MAX_2, NSB_SLIDER_DEFAULT_2);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(5);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);

		imgs[0] = new Image(NSB_FACE_1);
		imgs[1] = new Image(NSB_FACE_2);
		imgs[2] = new Image(NSB_FACE_3);
		imgs[3] = new Image(NSB_FACE_4);
		imgs[4] = new Image(NSB_FACE_5);
		imgs[5] = new Image(NSB_FACE_6);
		imgs[6] = new Image(NSB_FACE_7);

		String Ystr2 = "Units Sold";
		String Ystr1 = "Profit";

		String Xstr = "Price";

		NumberAxis xAxis1 = new NumberAxis(Xstr, NSB_AXIS_X_MIN_1, NSB_AXIS_X_MAX_1, NSB_SPACING_AXIS_X);
		NumberAxis yAxis1 = new NumberAxis(Ystr1, NSB_AXIS_Y_MIN_1, NSB_AXIS_Y_MAX_1, NSB_SPACING_AXIS_Y);

		NumberAxis xAxis2 = new NumberAxis(Xstr, NSB_AXIS_X_MIN_2, NSB_AXIS_X_MAX_2, NSB_SPACING_AXIS_X_2);
		NumberAxis yAxis2 = new NumberAxis(Ystr2, NSB_AXIS_Y_MIN_2, NSB_AXIS_Y_MAX_2, NSB_SPACING_AXIS_Y_2);

		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
		mGraph2 = new LineChart<Number, Number>(xAxis2, yAxis2);

		this.addGraphs(mGraph1);
		this.addGraphs(mGraph2);

		Image image1 = new Image(BIKE_URL);
		ImageView bike = new ImageView();
		bike.setImage(image1);

		bike.setTranslateX(NSB_X_POSITION_1);
		bike.setTranslateY(NSB_Y_POSITION_1);
		
		bike.setFitWidth(NSB_WIDTH);
		bike.setFitHeight(NSB_HEIGHT);

		Face.setTranslateX(NSB_X_POSITION_2);
		Face.setTranslateY(NSB_Y_POSITION_2);

		anim2 = new Timeline(new KeyFrame(Duration.ZERO, new KeyValue(
				Face.imageProperty(), imgs[0])), new KeyFrame(
				Duration.seconds(1),
				new KeyValue(Face.imageProperty(), imgs[1])), new KeyFrame(
				Duration.seconds(3),
				new KeyValue(Face.imageProperty(), imgs[2])), new KeyFrame(
				Duration.seconds(5),
				new KeyValue(Face.imageProperty(), imgs[3])), new KeyFrame(
				Duration.seconds(7),
				new KeyValue(Face.imageProperty(), imgs[4])), new KeyFrame(
				Duration.seconds(9),
				new KeyValue(Face.imageProperty(), imgs[5])), new KeyFrame(
				Duration.seconds(10), new KeyValue(Face.imageProperty(),
						imgs[6]))

		);

		anim1 = new TranslateTransition(Duration.seconds(10), bike);
		anim1.setInterpolator(new NewSportsInterpolator());
		anim1.setFromX(NSB_TRANSLATE_FROM_X_1);
		anim1.setCycleCount(1);
		

		anim1.setToX(NSB_TRANSLATE_TO_X_1);

		this.addAnimations(anim1);
		this.addAnimationElements(Face);
		this.addAnimationElements(bike);

	}

	private class NewSportsInterpolator extends Interpolator {

		@Override
		protected double curve(double t) {

			double maxPrice = calculateBestPrice() * 2;

			// get top level series
			XYChart.Series<Number, Number> series1 = mGraph1.getData().get(
					mGraph1.getData().size() - 1);

			XYChart.Series<Number, Number> series2 = mGraph2.getData().get(
					mGraph2.getData().size() - 1);

			series1.getData().add(
					new Data<Number, Number>(t * maxPrice, calculateProfit(t
							* maxPrice)));

			series2.getData().add(
					new Data<Number, Number>(t * maxPrice, calculateUnitSales(t
							* maxPrice)));

			return t;

		}
	}

	public double calculateUnitSales(double price) {
		return 70000 - 200 * price;
	}

	public double calculateSales(double price) {
		return (this.calculateUnitSales(price)) * price;
	}

	public double calculateTotalCost(double price) {

		return mSlider1.getValue() + mSlider2.getValue()
				* calculateUnitSales(price);

	}

	public double calculateProfit(double price) {
		return (this.calculateSales(price) - this.calculateTotalCost(price));

	}

	public double calculateBestPrice() {
		return (350 + mSlider2.getValue()) / 2;
	}

	public void stopAnimation() {
		anim2.stop();
	}

	@Override
	public void updateValues() {

		anim2.play();

	}

	public String getTitle() {
		return BIKE_TITLE;
	}

	public String getHelp() {
		return BIKE_HELP;
	}
}
