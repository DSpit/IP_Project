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
	
	
	final BarChart<String,Number> barGraph;
	
	//= new BarChart<String,Number>(xAxis,yAxis);
	
	
	
	public NewSportsBikeContainer(){
		
		
		String Ystr1 = "$$$";
		
		Axis<String> xAxis = new CategoryAxis();
		NumberAxis yAxis =new NumberAxis(Ystr1,-5,	5, 1);
		
		
		
		barGraph =  new BarChart<String,Number>(xAxis,yAxis);
		
		
		XYChart.Series series1 = new XYChart.Series();
        series1.setName("2003");       
        series1.getData().add(new XYChart.Data(25601.34, "austria"));
        series1.getData().add(new XYChart.Data(20148.82, "brazil"));
        series1.getData().add(new XYChart.Data(10000, "france"));
        series1.getData().add(new XYChart.Data(35407.15, "italy"));
        series1.getData().add(new XYChart.Data(12000, "usa"));    
		
		
        
        
		
		this.addGraphs(barGraph);
		
		
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
