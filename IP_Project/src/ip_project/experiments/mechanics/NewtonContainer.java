package ip_project.experiments.mechanics;

//import ip_project.experiments.Test.TestInterpolator;
import java.io.File;
import java.net.URL;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Newton's Second Law Experiment
 */
public class NewtonContainer extends MIContainer implements Resources {

	// private ObservableList<XYChart.Series> XYlist;

	protected XYChart.Series<Number, Number> series1, series2;
	protected LineChart<Number, Number> mGraph1, mGraph2;
	protected Slider mSlider1, mSlider2;
	protected Animation gAnim;
	protected TranslateTransition anim1;

	private ImageView image, imageflag, imagetext;
	private FadeTransition fadetransition1, fadetransition2;
	private TranslateTransition bobbing, bobbing2;

	public NewtonContainer() {

		// example of slider set up
		mSlider1 = new Slider(NEWTON_SLIDER_MIN_1, NEWTON_SLIDER_MAX_1,
				NEWTON_SLIDER_DEFAULT_1);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);

		mSlider2 = new Slider(NEWTON_SLIDER_MIN_1, NEWTON_SLIDER_MAX_1,
				NEWTON_SLIDER_DEFAULT_1);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(1);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);

		String Xstr1 = "Time";
		String Ystr1 = "Position";

		String Xstr2 = "Time";
		String Ystr2 = "Velocity";

		// example of graph set up
		NumberAxis xAxis1 = new NumberAxis(Xstr1, NEWTON_AXIS_X_MIN_1,
				calculate(calculateAcceleration(), 1), NEWTON_SPACING_AXIS);

		NumberAxis yAxis1 = new NumberAxis(Ystr1, NEWTON_AXIS_Y_MIN_1,
				calculate(calculateAcceleration(), 1), NEWTON_SPACING_AXIS);

		NumberAxis xAxis2 = new NumberAxis(Xstr2, NEWTON_AXIS_X_MIN_1,
				calculate(calculateAcceleration(), 1), NEWTON_SPACING_AXIS);

		NumberAxis yAxis2 = new NumberAxis(Ystr2, NEWTON_AXIS_Y_MIN_1,
				calculate((double) 1, (double) 1), NEWTON_SPACING_AXIS);

		Image image1 = new Image("ip_project/icons/tank.png");
		image = new ImageView();
		image.setImage(image1);
		image.setTranslateX(NEWTON_X_POSITION_1);
		image.setTranslateY(NEWTON_Y_POSITION_1);
		image.setVisible(false);

		Image image2 = new Image("ip_project/icons/soviet-flag.png");
		imageflag = new ImageView();
		imageflag.setImage(image2);
		imageflag.setTranslateX(NEWTON_X_POSITION_2);
		imageflag.setTranslateY(NEWTON_Y_POSITION_2);
		imageflag.setVisible(false);

		Image image3 = new Image("ip_project/icons/meanwhile.png");
		imagetext = new ImageView();
		imagetext.setImage(image3);
		imagetext.setTranslateX(NEWTON_X_POSITION_3);
		imagetext.setTranslateY(NEWTON_Y_POSITION_3);
		imagetext.setVisible(false);

		yAxis1.setAutoRanging(false);
		xAxis1.setAutoRanging(false);
		yAxis2.setAutoRanging(false);
		xAxis2.setAutoRanging(false);

		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
		mGraph2 = new LineChart<Number, Number>(xAxis2, yAxis2);

		this.addGraphs(mGraph1);
		this.addGraphs(mGraph2);

		anim1 = new TranslateTransition(Duration.seconds(10), image);
		anim1.setInterpolator(new NewtonInterpolator());
		anim1.setFromX(NEWTON_TRANSLATE_FROM_X);
		anim1.setCycleCount(1);
		anim1.setToX(NEWTON_TRANSLATE_TO_X);

		fadetransition1 = new FadeTransition(Duration.seconds(3), imageflag);
		fadetransition1.setInterpolator(Interpolator.LINEAR);
		fadetransition1.setCycleCount(1);
		fadetransition1.setFromValue(0);
		fadetransition1.setToValue(1);

		fadetransition2 = new FadeTransition(Duration.seconds(3), imagetext);
		fadetransition2.setInterpolator(Interpolator.LINEAR);
		fadetransition2.setCycleCount(1);
		fadetransition2.setFromValue(0);
		fadetransition2.setToValue(1);

		bobbing = new TranslateTransition(Duration.millis(DurationBobbing1),
				image);
		bobbing2 = new TranslateTransition(Duration.millis(DurationBobbing2),
				image);

		bobbing.setFromX(BobbingFromX);
		bobbing.setToX(BobbingToX);
		bobbing.setCycleCount(Timeline.INDEFINITE);
		bobbing.setAutoReverse(true);
		bobbing2.setByY(BobbingByX);
		bobbing2.setCycleCount(Timeline.INDEFINITE);
		bobbing2.setAutoReverse(true);

		bobbing.play();
		bobbing2.play();

		// set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1);

		this.addAnimations(comboAnim);
		this.addAnimationElements(image);

		mCanvas.getChildren().addAll(imageflag, imagetext);

		mGraph1.setAnimated(false);

		this.getStyleClass().add("newtons-second-law-canvas");

	}

	@Override
	public void updateValues() {

		String kalinkaS = new File("src/ip_project/sounds/kalinka.mp3").toURI()
				.toString();

		Media pick = new Media(kalinkaS);
		MediaPlayer player = new MediaPlayer(pick);

		player.play();

		fadetransition1.play();
		fadetransition2.play();

		image.setVisible(true);
		imagetext.setVisible(true);
		imageflag.setVisible(true);
		anim1.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				player.stop();

			}
		});
	}

	private double calculateAcceleration() {
		return (mSlider1.getValue() / mSlider2.getValue());
	}

	// @Override
	public double calculate(double value, double time) {
		return value * Math.pow(time, 2);
	}
	
	public String getTitle() {
		return NEWTON_TITLE;
	}

	public String getHelp() {
		return NEWTON_HELP;
	}

	private class NewtonInterpolator extends Interpolator {

		@Override
		protected double curve(double t) {

			double value = calculate(calculateAcceleration(), t);

			// get top level series
			XYChart.Series<Number, Number> series1 = mGraph1.getData().get(
					mGraph1.getData().size() - 1);
			XYChart.Series<Number, Number> series2 = mGraph2.getData().get(
					mGraph2.getData().size() - 1);

			if (value < 1) {
				series1.getData().add(new Data<Number, Number>(t, value));
				series2.getData()
						.add(new Data<Number, Number>(t,
								calculateAcceleration() * t));

				return value;
			} else
				// series.getData().add(new Data<Number, Number>(t, 1));
				return 1;
		}

	}
}
