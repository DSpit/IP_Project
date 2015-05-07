package ip_project.experiments.calculus;

import com.sun.javafx.geom.Rectangle;

import ip_project.main.MIContainer;
import ip_project.main.Resources;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.scene.chart.Axis;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * New Sports Bike Calculus Experiment
 */
public class NewSportsBikeContainer extends MIContainer implements Resources{
	
	//XYChart.Series<Number, Number> series1, series2;
	
	 XYChart.Series<String, Number> series3; // = new XYChart.Series();
	Slider mSlider1, mSlider2;
	TranslateTransition anim1, anim2;
	ImageView image;
	LineChart<Number, Number> mGraph1, mGraph2;

	
	BarChart<String,Number> barGraph;
	
	//= new BarChart<String,Number>(xAxis,yAxis);
	
	
	
	public NewSportsBikeContainer(){
		
		
		
		// example of slider set up
		mSlider1 = new Slider(400000, 700000, 700000 );
		mSlider1.setShowTickMarks(true);
		mSlider1.setShowTickLabels(true);
		mSlider1.setMajorTickUnit(1000);
		mSlider1.setMinorTickCount(0);
		mSlider1.setSnapToTicks(true);
		mSlider1.setId("Slider 1");
		this.addInputs(mSlider1);

		mSlider2 = new Slider(50, 130, 110);
		mSlider2.setShowTickMarks(true);
		mSlider2.setShowTickLabels(true);
		mSlider2.setMajorTickUnit(5);
		mSlider2.setMinorTickCount(0);
		mSlider2.setSnapToTicks(true);
		mSlider2.setId("Slider 2");
		this.addInputs(mSlider2);
		
		
		
		
		String Ystr2 = "Units Sold";
		String Ystr1 = "Profit";
			

		String Xstr = "Price";
		
		
		
		NumberAxis xAxis1 = new NumberAxis(Xstr,0 ,	500, 100);
		NumberAxis yAxis1 =new NumberAxis(Ystr1,-10000000,10000000, 1000000);
		
		NumberAxis xAxis2 = new NumberAxis(Xstr,0,	500, 100);
		NumberAxis yAxis2 =new NumberAxis(Ystr2,-30000,	70000, 5000);
		CategoryAxis xAxis3 = new CategoryAxis();
        NumberAxis yAxis3 = new NumberAxis();
		
		
		
		
		mGraph1 = new LineChart<Number, Number>(xAxis1, yAxis1);
		mGraph2 = new LineChart<Number, Number>(xAxis2, yAxis2);

		
		 
		barGraph =  new BarChart<String,Number>(xAxis3,yAxis3);
		barGraph.setAnimated(true);
		
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

        
        Circle circulo = new Circle(20);
        
        
        circulo.setTranslateX(15);
		circulo.setTranslateY(100);
        
		
    
		
		
        
        
        
       /* 
		object1.setFill(Color.BLUE);
        
		object1.setTranslateX(15);
		object1.setTranslateY(400);
		
		*/
		
		
		anim1 = new TranslateTransition(Duration.seconds(5), circulo);
		anim1.setInterpolator(new NewSportsInterpolator());		
		anim1.setFromX(15);
		anim1.setCycleCount(1);

		anim1.setToX(356);
		
		
		
		this.addAnimations(anim1);
		this.addAnimationElements(circulo);
		
		//this.addAnimationElements(barGraph);
			
	}
	
	
	
	
	
	private class NewSportsInterpolator extends Interpolator{

		@Override
		protected double curve(double t) {
			
			double maxPrice = calculateBestPrice() * 2;	
				
			
			
			//get top level series
			XYChart.Series<Number, Number> series1 
			= mGraph1.getData().get(mGraph1.getData().size() - 1);
	
			XYChart.Series<Number, Number> series2 
			= mGraph2.getData().get(mGraph2.getData().size() - 1);
	
		/*	XYChart.Series<String, Number> series3 = 
					series3.getData().add(new Data<String, Number>
					("Costs", calculateProfit(t*maxPrice))); 
			
			*/
			
			
			//lineChart.getData().remove(/*(lineChart.getData().size()-1)*/0);

			
			
			series1.getData().add(new Data<Number, Number>
			(t*maxPrice, calculateProfit(t*maxPrice)));
			
			series2.getData().add(new Data<Number, Number>
			(t*maxPrice, calculateUnitSales(t*maxPrice)));
			
			series3 =  new Series<String, Number>();
			
			//barGraph.
			/*System.out.println(t);
			if(t>0.01)
				barGraph.getData().removeAll();
			*/
			
	        series3.getData().add(new XYChart.Data<String, Number>
	        ("Costs", calculateTotalCost(t*maxPrice)));
	        
	        //barGraph.dataItemChanged(barGraph.getData().)
	      /*  series3.getData().add(new XYChart.Data<String, Number>
	        ("Sales", calculateUnitSales(t*maxPrice)));
	        
	        series3.getData().add(new XYChart.Data<String, Number>
	        ("Profit", calculateProfit(t*maxPrice)));*/
	        
	        barGraph.getData().addAll(series3);
	        
			//barGraph.getData().remove(0);


			
			
			//System.out.println( calculateProfit(t*maxPrice)  + " + " +  calculateUnitSales(t*maxPrice) );
			
			
			
			return t;
				
			}		
		}
	
	
	
	public double calculateUnitSales(double price) {	
		return  70000  - 200 * price;	
	}
	
	
	public double calculateSales(double price) {	
		return (this.calculateUnitSales(price)) * price;	
	}
	
	
	public double calculateTotalCost (double price){
		
		return mSlider1.getValue() + mSlider2.getValue() * calculateUnitSales(price);
		
	}
	
	public double calculateProfit(double price) {
		return (this.calculateSales(price) - this.calculateTotalCost(price));
		
	}
	
	public double calculateBestPrice() {	
		return (350 + mSlider2.getValue())/2 ;
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
