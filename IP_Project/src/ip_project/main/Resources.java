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
	
	public String NSB_FACE_1 = "ip_project/icons/face-1.png";
	public String NSB_FACE_2 = "ip_project/icons/face-2.png";
	public String NSB_FACE_3 = "ip_project/icons/face-3.png";
	public String NSB_FACE_4 = "ip_project/icons/face-4.png";
	public String NSB_FACE_5 = "ip_project/icons/face-5.png";
	public String NSB_FACE_6 = "ip_project/icons/face-6.png";
	public String NSB_FACE_7 = "ip_project/icons/face-7.png";
	

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
	
	public String NEWTON_SLIDER_1_ID = "Force";
	public String NEWTON_SLIDER_2_ID = "Mass";
	
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
	
	public String GS_SLIDER_ID_1 = "Percentage of Decrease";
	public String GS_SLIDER_ID_2 = "# Periods"; 
	
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
	
	public String PROJ_SLIDER_1_ID = "Initial Velocity";
	public String PROJ_SLIDER_2_ID = "Angle";
	
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
	
	public String OPTICS_SLIDER_1_ID = "Focal Length";
	public String OPTICS_SLIDER_2_ID = "Object Distance";
	
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
    
    public double NSB_SLIDER_MAX_1 = 700000;
	public double NSB_SLIDER_MIN_1 = 400000;
	public double NSB_SLIDER_DEFAULT_1 = 700000;
	
	public double NSB_SLIDER_MAX_2 = 130;
	public double NSB_SLIDER_MIN_2 = 50;
	public double NSB_SLIDER_DEFAULT_2 = 110;
	
	public double NSB_AXIS_X_MIN_1 = 0;
	public double NSB_AXIS_X_MAX_1 = 500;
	public double NSB_AXIS_Y_MIN_1 = -10000000;
	public double NSB_AXIS_Y_MAX_1 = 10000000;
	public double NSB_SPACING_AXIS_X = 100;
	public double NSB_SPACING_AXIS_Y = 1000000;
	
	public double NSB_AXIS_X_MIN_2 = 0;
	public double NSB_AXIS_X_MAX_2 = 500;
	public double NSB_AXIS_Y_MIN_2 = -30000;
	public double NSB_AXIS_Y_MAX_2 = 70000;
	public double NSB_SPACING_AXIS_X_2 = 100;
	public double NSB_SPACING_AXIS_Y_2 = 5000;
	
	public double NSB_X_POSITION_1 = 350;
	public double NSB_Y_POSITION_1 = 100;
	public double NSB_TRANSLATE_FROM_X_1 = 350;
	public double NSB_TRANSLATE_TO_X_1 = 1000;
	
	public double NSB_WIDTH = 150;
	public double NSB_HEIGHT = 150;
	
	public double NSB_X_POSITION_2 = 400;
	public double NSB_Y_POSITION_2 = 200;
	
	public String BIKE_URL = "ip_project/icons/bike.png";
    

	public String RAD_ACT_SLIDER_1_ID = "Half Life";
	public double RAD_ACT_PARTICLE_RADIUS = 20;
	
	public String NSB_SLIDER_1_ID = "Advertisement Costs";
	public String NSB_SLIDER_2_ID = "Cost per bike";

	
	public String NEWTON_HELP = "This experiment aims to simulate Newton's Second Law of Motion. The net force applied" + 
	"to a body produces a proportional acceleration. \nIn other words, if a body is accelerating, then there is a force on it." 
			+ "\n	The first slider is Force\n "
			+ "	The second slider is Mass" ;
			
	public String PROJ_HELP = "Projectile motion is a form of motion in which an object or particle (called a projectile) is "
			+ "thrown near the earth's surface, and it moves along a curved path under the action of gravity only. \nThe only force of"
			+ " significance that acts on the object is gravity, which acts downward to cause a downward acceleration. "
			+ "\nThere are no horizontal forces needed to maintain the horizontal motion � consistent "
			+ "with the concept of inertia." + "\n	The first slider is the Initial Velocity \n "
			+ "	The second slider is the Angle" ;
	public String OPTIC_HELP = "The image formed by a single lens can be located and sized with three principal "
			+ "rays. \nThe lens is converging, and therefore if the object is located to the left of the focal length, then the image will be located to the right of the "
			+ "lense. \nOtherwise, the image is going to be to the left of the lens."
			+ "\n	The first slider is the Focal Length."
			+ "\n	The second slider is the Object Distance.";
	public String RAD_ACT_HELP = "Radioactive Decay";
	public String BIKE_HELP = "New Sports Bike";
	public String GEOM_SER_HELP = "A geometric series is a series with a constant ratio between successive terms. \n	The first slider is Percentage of Decrease \n "
			+ "	The second slider is the Amount of Periods";
}
