package ip_project.main;

public interface Resources {
	
	
	//temporary until added to CSS
	String buttonStyle = "-fx-background-color:  #000000, linear-gradient(#7ebcea, #2f4b8f), linear-gradient(#426ab7, #263e75), linear-gradient(#395cab, #223768); "
							+ "-fx-background-insets: 0,1,2,3;"
						    + "-fx-background-radius: 3,2,2,2;"
						    + "-fx-padding: 12 30 12 30;"
						    + "-fx-text-fill: white;"
						    + "-fx-font-size: 12px;"
							+ "-fx-pref-width: 150px;";
	
	public int CONTENT_HEIGHT = 900;
	public int CONTENT_WIDTH = 1500;
	public int WINDOW_PADDING = 100;
	public int WINDOW_HEIGHT = CONTENT_HEIGHT + WINDOW_PADDING;
	public int WINDOW_WIDTH = CONTENT_WIDTH + WINDOW_PADDING;
	
	public int MENU_THUMBNAIL_SIZE = 32;
	
	public int CONTROL_BUTTON_PADDING = 15;
	public int CONTROL_BUTTON_SPACING = 50;
	
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
	
	public String APPLE_IMAGE_URL = "https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/apple_64.png";
	public String PROJ_IMAGE_URL = "https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/file01_64.png";
	public String GLASSES_IMAGE_URL = "https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/glasses_64.png";
	public String DECAY_IMAGE_URL = "https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/molecule_64.png";
	public String BALL_IMAGE_URL = "https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/ball_64.png";
	public String BIKE_IMAGE_URL = "https://cdn4.iconfinder.com/data/icons/brainy-icons-free-36-science-and-education-icons/64/abacus_64.png";
}
