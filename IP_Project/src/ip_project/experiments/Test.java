package ip_project.experiments;

import ip_project.main.MIContainer;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Slider;

public class Test extends MIContainer{
	
	public Test(){
		Slider slider1 = new Slider(0, 10, 0);
		this.addInputs(slider1);
		
		NumberAxis xAxis = new NumberAxis();
		NumberAxis yAxis = new NumberAxis();
		LineChart<Number, Number> graph1 = new LineChart<Number, Number>(xAxis, yAxis);
		this.addGraphs(graph1);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void done() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unpause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void restart() {
		// TODO Auto-generated method stub
		
	}

}
