package ip_project.experiments.waves;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.util.Duration;
import ip_project.main.MIContainer;
import ip_project.main.Resources;

/**
 * Optics Lens Experiment
 */
public class OpticsContainer extends MIContainer implements Resources {

	public String getTitle() {
		return OPTIC_TITLE;
	}

	protected LineChart<Number, Number> mGraph1;
	protected Slider mSlider1, mSlider2;
	private Rectangle object, image, focalpoint1, focalpoint2;
	private TranslateTransition anim1, anim2, anim3;
	private JFrame frame;

	public OpticsContainer() {
		// example of slider set up
		mSlider1 = new Slider(OPTICS_SLIDER_MIN_1, OPTICS_SLIDER_MAX_1, OPTICS_SLIDER_DEFAULT_1);
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);

		mSlider2 = new Slider(OPTICS_SLIDER_MIN_2, OPTICS_SLIDER_MAX_2, OPTICS_SLIDER_DEFAULT_2);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(0.5);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);

		String Xstr1 = "Time";
		String Ystr1 = "Image Height";

		NumberAxis xAxis = new NumberAxis(Xstr1, OPTICS_AXIS_X_MIN_1, OPTICS_AXIS_X_MAX_1, OPTICS_SPACING_AXIS_X );
		NumberAxis yAxis = new NumberAxis(Ystr1, OPTICS_AXIS_Y_MIN_1, OPTICS_AXIS_Y_MAX_1, OPTICS_SPACING_AXIS_Y);

		mGraph1 = new LineChart<Number, Number>(xAxis, yAxis);

		this.addGraphs(mGraph1);

		Screen screen = Screen.getPrimary();

		Rectangle2D bounds = screen.getVisualBounds();

		image = new Rectangle(OBJECT_WIDTH, OBJECT_HEIGHT);
		image.setFill(Color.ALICEBLUE);
		image.setTranslateX(bounds.getWidth() * WIDTH_CONSTANT);
		image.setTranslateY((bounds.getHeight() / 2) - OBJECT_HEIGHT);
		image.setVisible(true);
		image.setStroke(Color.BLACK);
		image.getStyleClass().add("opacity-shapes");

		// specify object- look and location
		object = new Rectangle(OBJECT_WIDTH, OBJECT_HEIGHT);
		object.setFill(Color.SILVER);
		object.setTranslateX(bounds.getWidth() * WIDTH_CONSTANT);
		object.setTranslateY((bounds.getHeight() / 2) - OBJECT_HEIGHT);
		object.setStroke(Color.BLACK);
		object.setVisible(false);

		object.getStyleClass().add("opacity-shapes");

		Ellipse ellipse = new Ellipse();
		ellipse.setTranslateX(bounds.getWidth() * WIDTH_CONSTANT);
		ellipse.setTranslateY(bounds.getHeight() / 2);
		ellipse.setFill(Color.LIGHTSTEELBLUE);
		ellipse.setStroke(Color.BLACK);
		ellipse.setRadiusX(15f);
		ellipse.setRadiusY(150f);
		ellipse.getStyleClass().add("opacity-shapes");

		focalpoint1 = new Rectangle(FOCAL_WIDTH, FOCAL_HEIGHT);
		focalpoint1.setTranslateX(bounds.getWidth() * WIDTH_CONSTANT);
		focalpoint1.setTranslateY((bounds.getHeight() / 2));

		mCanvas.getChildren().add(ellipse);

		anim1 = new TranslateTransition(Duration.seconds(4), image);
		anim1.setInterpolator(Interpolator.LINEAR); // this is where you put in
													// the custom interpolator
		anim1.setFromX(bounds.getWidth() * WIDTH_CONSTANT);
		anim1.setCycleCount(1);

		anim2 = new TranslateTransition(Duration.seconds(3), focalpoint1);
		anim2.setInterpolator(new OpticsInterpolator()); // this is where you
															// put in the custom
															// interpolator
		anim2.setFromX(bounds.getWidth() * WIDTH_CONSTANT);
		anim2.setCycleCount(1);

		anim3 = new TranslateTransition(Duration.seconds(4), object);
		anim3.setInterpolator(Interpolator.LINEAR); // this is where you put in
													// the custom interpolator
		anim3.setFromX(bounds.getWidth() * WIDTH_CONSTANT);
		anim3.setToX(calculateImageDistance(mSlider1.getValue(),
				mSlider2.getValue()));
		anim3.setCycleCount(1);

		// TODO fix the width of the line

		Line line1 = new Line(0, bounds.getHeight() / 2, 1400,
				bounds.getHeight() / 2);
		line1.setStroke(Color.BLACK);
		mCanvas.getChildren().addAll(line1);

		//
		// set up the animation
		ParallelTransition comboAnim = new ParallelTransition();
		comboAnim.getChildren().addAll(anim1);

		this.addAnimations(comboAnim, anim2, anim3);
		this.addAnimationElements(image, focalpoint1, object);

		this.getStyleClass().add("optics-canvas");

	}

	// @Override
	/**
	 * Calculates the distance of the image from the lens
	 * 
	 * @param focal
	 *            focal distance
	 * @param object
	 *            object distance
	 * @return
	 */
	private double calculateImageDistance(double focal, double object) {
		return 1 / (1 / focal - 1 / object);
	}

	/**
	 * Calculates the height of the image based on the height of the object
	 * 
	 * @param focal
	 *            focal distance
	 * @param object
	 *            object distance
	 * @param objectHeight
	 *            height of the object
	 * @return
	 */
	private double calculateImageHeight(double focal, double object,
			double objectHeight) {
		return (calculateImageDistance(focal, object) / object) * objectHeight;
	}

	private class OpticsInterpolator extends Interpolator {

		@Override
		protected double curve(double t) {
			// calculations
			double value = calculateImageDistance(mSlider1.getValue(),
					mSlider2.getValue());
			double value2 = calculateImageHeight(mSlider1.getValue(),
					mSlider2.getValue(), OBJECT_HEIGHT);
			// get top level series
			XYChart.Series<Number, Number> series = mGraph1.getData().get(
					mGraph1.getData().size() - 1);
			series.getData().add(new Data<Number, Number>(t, value2 * t));

			return t;
		}

	}

	@Override
	public void updateValues() {

		Screen screen = Screen.getPrimary();

		Rectangle2D bounds = screen.getVisualBounds();

		object.setVisible(false);

		anim1.setToX((bounds.getWidth() * WIDTH_CONSTANT) - mSlider2.getValue());
		anim2.setToX((bounds.getWidth() * WIDTH_CONSTANT) - mSlider1.getValue());
		anim3.setToX((bounds.getWidth() * WIDTH_CONSTANT)
				+ calculateImageDistance(mSlider1.getValue(),
						mSlider2.getValue()));

		anim1.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if ((Math.abs(calculateImageDistance(mSlider1.getValue(),
						mSlider2.getValue())) > OPTICS_MAX_WIDTH)
						|| (Math.abs(calculateImageHeight(mSlider1.getValue(),
								mSlider2.getValue(), OBJECT_HEIGHT)) > OPTICS_MAX_HEIGHT)) {

					JOptionPane
							.showMessageDialog(
									frame,
									"The image is going to be out of the bounds of the screen! Try entering new values.",
									"Inane error", JOptionPane.ERROR_MESSAGE);

					object.setVisible(false);

				}

				else {

					if (calculateImageHeight(mSlider1.getValue(),
							mSlider2.getValue(), OBJECT_HEIGHT) < 0) {

						anim3.setToY((bounds.getHeight() / 2)
								+ calculateImageHeight(mSlider1.getValue(),
										mSlider2.getValue(), OBJECT_HEIGHT));
						object.setHeight(Math.abs(calculateImageHeight(
								mSlider1.getValue(), mSlider2.getValue(),
								OBJECT_HEIGHT)));
						object.setTranslateY((bounds.getHeight() / 2)
								+ calculateImageHeight(mSlider1.getValue(),
										mSlider2.getValue(), OBJECT_HEIGHT));

						double testvalue = (bounds.getHeight() / 2)
								- calculateImageHeight(mSlider1.getValue(),
										mSlider2.getValue(), OBJECT_HEIGHT);

						object.setVisible(true);

					}

					else {

						object.setHeight(Math.abs(calculateImageHeight(
								mSlider1.getValue(), mSlider2.getValue(),
								OBJECT_HEIGHT)));
						object.setTranslateY((bounds.getHeight() / 2));

						double value = (bounds.getHeight() / 2)
								- calculateImageHeight(mSlider1.getValue(),
										mSlider2.getValue(), OBJECT_HEIGHT);

						object.setVisible(true);

					}
				}
			}
		});

	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return OPTIC_HELP;
	}
}
