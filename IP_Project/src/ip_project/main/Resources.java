package ip_project.main;

public interface Resources {
	
	
	public int CONTENT_HEIGHT = 900;
	public int CONTENT_WIDTH = 1000;
	public int WINDOW_PADDING = 100;
	public int WINDOW_HEIGHT = CONTENT_HEIGHT + WINDOW_PADDING;
	public int WINDOW_WIDTH = CONTENT_WIDTH + WINDOW_PADDING;
	
	public int MENU_THUMBNAIL_SIZE = 32;
	
	public int CONTROL_BUTTON_PADDING = 15;
	public int CONTROL_BUTTON_SPACING = 50;
	public int CONTROL_HEIGHT = 75;
	
	public String NEWTON_TITLE = "Newton's Second Law";
	public String PROJ_TITLE = "Projectile Motion";
	public String OPTIC_TITLE = "Optics and Lenses";
	public String RAD_ACT_TITLE = "Radioactive Decay";
	public String BIKE_TITLE = "New Sports Bike";
	public String GEOM_SER_TITLE = "Geometric Series";
	public String MECH_MENU = "Mechanics";
	public String WAVES_MENU = "Waves";
	public String CALC_MENU = "Calculus";
	
	public String START_TITLE = "Start";
	public String RESET_TITLE = "Reset";
	public String DONE_TITLE = "Done";
	public String CONTINUE_TITLE = "Continue";
	public String PAUSE_TITLE = "Pause";
	public String HELP_TITLE = "Help";
	
	public String EXIT = "Exit";
	
	public String APPLE_IMAGE_PATH = "ip_project/icons/apple.png";
	public String PROJ_IMAGE_PATH = "ip_project/icons/projectile.png";
	public String GLASSES_IMAGE_PATH = "ip_project/icons/glasses.png";
	public String DECAY_IMAGE_PATH = "ip_project/icons/molecule.png";
	public String BALL_IMAGE_PATH = "ip_project/icons/ball.png";
	public String BIKE_IMAGE_PATH = "ip_project/icons/abacus.png";

	public int MAX_FORCE = 100, MAX_MASS = 100, MIN_FORCE = 5, MIN_MASS = 5;
	
	public double GRAVITY_CONSTANT = 9.8;
	
	
	public double INITIAL_HEIGHT = 50;
	
	
	public double OBJECT_HEIGHT = 100;
	public double OBJECT_WIDTH = 10;
	public double WIDTH_CONSTANT = 0.294140625; 
	public double FOCAL_WIDTH = 5;
	public double FOCAL_HEIGHT = 10;
	
	public double NEWTON_SLIDER_MAX_1 = 10;
	public double NEWTON_SLIDER_MIN_1 = 0;
	public double NEWTON_SLIDER_DEFAULT_1 = 5;
	
	public double NEWTON_AXIS_X_MIN_1 = 0;
	public double NEWTON_AXIS_Y_MIN_1 = 0;
	public double NEWTON_SPACING_AXIS = 0.1;
	
	public double NEWTON_X_POSITION_1 = 10;
	public double NEWTON_Y_POSITION_1 = 400;
	public double NEWTON_X_POSITION_2 = 700;
	public double NEWTON_Y_POSITION_2 = 15;
	public double NEWTON_X_POSITION_3 = 350;
	public double NEWTON_Y_POSITION_3 = 700;
	
	public double NEWTON_TRANSLATE_FROM_X = 10;
	public double NEWTON_TRANSLATE_TO_X = 600;
	
	
	public double GS_SLIDER_MAX_1 = 1;
	public double GS_SLIDER_MIN_1 = 0;
	public double GS_SLIDER_DEFAULT_1 = 0.75;
	
	public double GS_SLIDER_MAX_2 = 10;
	public double GS_SLIDER_MIN_2 = 3;
	public double GS_SLIDER_DEFAULT_2 = 5;
	
	public double GS_AXIS_X_MIN_1 = 0;
	public double GS_AXIS_X_MAX_1 = 1;
	public double GS_AXIS_Y_MIN_1 = -50;
	public double GS_AXIS_Y_MAX_1 = 50;
	public double GS_SPACING_AXIS_X = 0.1;
	public double GS_SPACING_AXIS_Y = 5;
	
	public double GS_IMAGE_WIDTH = 100;
	public double GS_IMAGE_HEIGHT = 100;
	
	public double GS_X_POSITION_1 = 10;
	public double GS_Y_POSITION_1 = 700;
	public double GS_X_POSITION_2 = -10;
	public double GS_Y_POSITION_2 = 400;
	public double GS_X_POSITION_3 = 350;
	public double GS_Y_POSITION_3 = 700;
	
	public double GS_TRANSLATE_FROM_X_1 = 15;
	public double GS_TRANSLATE_TO_X_1 = 356;		
	public double GS_TRANSLATE_FROM_Y_2 = 100;
	public double GS_TRANSLATE_TO_Y_2 = 700;	
	
	
	public double PROJ_SLIDER_MAX_1 = 5;
	public double PROJ_SLIDER_MIN_1 = 1;
	public double PROJ_SLIDER_DEFAULT_1 = 5;
	
	public double PROJ_SLIDER_MAX_2 = 90;
	public double PROJ_SLIDER_MIN_2 = 0;
	public double PROJ_SLIDER_DEFAULT_2 = 45;
	
	
	public double PROJ_AXIS_X_MIN_1 = 0;
	public double PROJ_AXIS_X_MAX_1 = 1;
	public double PROJ_AXIS_Y_MIN_1 = -5;
	public double PROJ_AXIS_Y_MAX_1 = 5;
	public double PROJ_SPACING_AXIS_X = 0.1;
	public double PROJ_SPACING_AXIS_Y = 1;
	
	public double PROJ_AXIS_X_MIN_2 = 0;
	public double PROJ_AXIS_X_MAX_2 = 1;
	public double PROJ_AXIS_Y_MIN_2 = -1;
	public double PROJ_AXIS_Y_MAX_2 = 1;
	public double PROJ_SPACING_AXIS_X_2 = 0.1;
	public double PROJ_SPACING_AXIS_Y_2 = 1;
	
	public double PROJ_X_POSITION_1 = 10;
	public double PROJ_Y_POSITION_1 = 500;
	public double PROJ_TRANSLATE_FROM_X_1 = 10;
	public double PROJ_TRANSLATE_TO_X_1 = 500;
	public double PROJ_TRANSLATE_TO_Y_1 = 700;
	
	public double OPTICS_SLIDER_MAX_1 = 100;
	public double OPTICS_SLIDER_MIN_1 = 30;
	public double OPTICS_SLIDER_DEFAULT_1 = 50;
	
	public double OPTICS_SLIDER_MAX_2 = 300;
	public double OPTICS_SLIDER_MIN_2 = 50;
	public double OPTICS_SLIDER_DEFAULT_2 = 150;
	
	public double OPTICS_AXIS_X_MIN_1 = 0;
	public double OPTICS_AXIS_X_MAX_1 = 1;
	public double OPTICS_AXIS_Y_MIN_1 = -350;
	public double OPTICS_AXIS_Y_MAX_1 = 350;
	public double OPTICS_SPACING_AXIS_X = 0.1;
	public double OPTICS_SPACING_AXIS_Y = 50;
	
	public double OPTICS_MAX_HEIGHT = 350;
	public double OPTICS_MAX_WIDTH = 600;
	
    public double DurationBobbing1 = 500;
    public double DurationBobbing2 = 100;
    public double BobbingFromX = 0;
    public double BobbingToX = 10;
    public double BobbingByX = 3;
	
	
	public String NEWTON_HELP = "The first Slider is Force \n "
			+ "The second Slider is Mass  " ;
			
	public String PROJ_HELP = "The first Slider is Initial velocity \n "
			+ "The second Slider is Angle  " ;
	public String OPTIC_HELP = "Optics and Lenses";
	public String RAD_ACT_HELP = "Radioactive Decay";
	public String BIKE_HELP = "New Sports Bike";
	public String GEOM_SER_HELP = "The first Slider is Percentaje? \n "
			+ "The second Slider is Bounces  " ;
}
