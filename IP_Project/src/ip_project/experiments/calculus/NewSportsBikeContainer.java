package ip_project.experiments.calculus;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

/**
 * New Sports Bike Calculus Experiment
 */
public class NewSportsBikeContainer extends MIContainer implements Resources{
	
	XYChart.Series<Number, Number> series1, series2;
	Slider mSlider1, mSlider2;
	TranslateTransition anim1, anim2;
	ImageView image;
	LineChart<Number, Number> mGraph1, mGraph2;

	
	final BarChart<String,Number> barGraph;
	
	//= new BarChart<String,Number>(xAxis,yAxis);
	
	
	
	public NewSportsBikeContainer(){
		
		
		
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
		
		
		
		
		String Ystr1 = "Units Sold";
		String Ystr2 = "Profit";
			

		String Xstr = "Cost";
		
		
		
		NumberAxis xAxis1 = new NumberAxis(Xstr,-5,	5, 1);
		NumberAxis yAxis1 =new NumberAxis(Ystr1,-5,	5, 1);
		
		NumberAxis xAxis2 = new NumberAxis(Xstr,-5,	5, 1);
		NumberAxis yAxis2 =new NumberAxis(Ystr2,-5,	5, 1);
		
		
		
		
		
		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
		mGraph2 = new LineChart<Number, Number>(xAxis2, yAxis2);

		
		
		//barGraph =  new BarChart<String,Number>(xAxis,yAxis);
		
		
		/*XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(25601.34, "austria"));
        series1.getData().add(new XYChart.Data(20148.82, "brazil"));
        series1.getData().add(new XYChart.Data(10000, "france"));
        series1.getData().add(new XYChart.Data(35407.15, "italy"));
        series1.getData().add(new XYChart.Data(12000, "usa"));    
		*/
		
        
        
		
		this.addGraphs(mGraph1);
		this.addGraphs(mGraph2);

        
        
        
        
        
		
		
	}
	
	
	
	public double calculateUnitSales(double price) {	
		return  70000  - 200 * price;	
	}
	
	
	public double calculateSales(double price) {	
		return (this.calculateUnitSales(price)) * price;	
	}
	
	
	public double calculateTotalCost (double price){
		
		return mSlider1.getValue() 
	}
	
	public double calculateProfit() {
		
		return ()
		
	}
	
	
	
	
	
	
	
	@Override
	public void updateValues(){
	}
	
	

	public String getTitle(){
		return BIKE_TITLE;
	}
	
	public String getHelp(){
		return BIKE_HELP;
	}
}
