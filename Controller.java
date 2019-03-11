/**
 * Do not modify this file without permission from your TA.
 **/
public class Controller {

	private Model model;
	private View view;
	
	public Controller(){
		view = new View();
		model = new Model(view.getWidth(), view.getHeight(), view.getImageWidth(), view.getImageHeight());
	}
	
    //run the simulation
	public void start(){
		for(int i = 0; i < 5000; i++)
		{
			//increment the x and y coordinates, alter direction if necessary
			System.out.println("Updating location and direction");
			model.updateLocationAndDirection();
			System.out.println("OLD model, x: " + model.getX() + " y: " + model.getY() + " direct: " + model.getDirect());
			//update the view
			view.update(model.getX(), model.getY(), model.getDirect());
			System.out.println("NEW model, x: " + model.getX() + " y: " + model.getY() + " direct: " + model.getDirect() + "\n");
		}
	}
}
