package com.autouploader;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
public class Dashboard {
	private Stage stage;
	private Pane fixedPane;
	private BorderPane borderPane;
	private ComboBox<Integer> templatesCombobox;
	private Rectangle uploadRectangle;
	private Rectangle dashboardRectangle;
	private Rectangle patientRectangle;
	private Rectangle helpRectangle;
	private Button uploadbtn;
	private Button dashboardbtn;
	private Button patientbtn;
	private Button helpbtn;
	private Button addPatientbtn;
	private ImageView uploadImageView;
	private ImageView dashboardImageView;
	private ImageView patientImageview;
	private ImageView helpImageView;
	public String userNameLabel;
	private Button btnForContectMenu;
	private ComboBox<String> sitecomboBox;
	private Timeline idleTimer;
	private Scene scene;
	
	// for image reading property
	public Properties properties = new Properties();
	// for image path reading
	public Properties properties1 = new Properties();
	// for combobox
	public Properties propertyForSiteComboBox = new Properties();
	private VBox vboxForLabelandsiteVbox;
	private HBox hboxForSearchtextFieldAndImage;

	public void OpenDashBoard(Stage dasBoardStage, String username, ObservableList<String> items,String password) throws IOException {
		this.scene=scene;
		this.userNameLabel = username;
		this.stage = dasBoardStage;
		StackPane dashboardStackPane = new StackPane();

		///  Create a BorderPane for the main content
		BorderPane dashboardRoot = new BorderPane();
		this.borderPane = dashboardRoot;

		///  Create an HBox for the username and shortcut
		HBox usernameBox = new HBox(13);
		///  Setting the node in usernameHbox to Centre from Right Screen
		usernameBox.setAlignment(Pos.CENTER_RIGHT);

		///  Create a label for the username
		Label usernameLabel = new Label(userNameLabel(username));

		///  Create a circular box for the shortcut
		Circle shortcutCircle = new Circle(12); // Set the desired radius
		shortcutCircle.setFill(Color.GREY); // Set the desired color

		///  Create a label for the dynamic shortcut text
		Label shortcutLabel = new Label(generateShortcut(username));
		shortcutLabel.setTextFill(Color.WHITE); ///  Set the desired text color

		/// CONTEXT MENU
		Image shortcutImage = new Image(new FileInputStream("resources/images/carrot2x.png"));
		ImageView shortCutImageView = new ImageView(shortcutImage);
		shortCutImageView.setFitWidth(7);
		shortCutImageView.setFitHeight(10);
		btnForContectMenu = new Button();
		btnForContectMenu.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		//setting the graphic for context menu
		btnForContectMenu.setGraphic(shortCutImageView);
		//creating context menu for settings and logout
		ContextMenu contextMenu = new ContextMenu();
		MenuItem settingsItem = new MenuItem();
		MenuItem logoutItem = new MenuItem();
		///  setting action for logout menu
		logoutItem.setOnAction(event -> {
			try {
				handleLogout();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		});
		

///  Create labels for the context menu
		Label settingsLabel = new Label("Settings & Preferences");
		Label logoutLabel = new Label("Logout");
		settingsItem.setOnAction(e -> {
			///  Create a dialog for settings
			Dialog<Void> settingsDialog = new Dialog<>();
			settingsDialog.initStyle(StageStyle.UNDECORATED); // Remove the default close button and dialog icon
			settingsDialog.setHeight(280);
			settingsDialog.setWidth(400);
			settingsDialog.getDialogPane().setStyle(
					"-fx-background-color: white; -fx-border-radius: 5; -fx-border-color: White; -fx-border-width:.5;");

			///  Center the dialog on the scene
			Scene currentScene = dashboardStackPane.getScene();
			double dialogX = (currentScene.getWidth() - settingsDialog.getWidth()) / 2;
			double dialogY = (currentScene.getHeight() - settingsDialog.getHeight()) / 2;
			settingsDialog.setX(dialogX);
			settingsDialog.setY(dialogY);

			///  Create UI elements for the settings dialog/// settings dialog
			HBox hboxForsettingsLabelandCrossButton = null;
			try {
				hboxForsettingsLabelandCrossButton = createHeaderForSettingsDialog();
			} catch (FileNotFoundException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			Label displayLabel = new Label("Display");
			displayLabel.setStyle("-fx-text-fill: orange;");
			displayLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
			/// For the dark mode
			Label darkModeLabel = new Label("Toggle Dark Mode");
			Button darkModeOnButton=new Button("ON");
		//	darkModeOnButton.setPrefWidth(50);
			
			
			Button darkModeOffButton=new Button
					("OFF");
			//darkModeOffButton.setPrefWidth(50);
			
			darkModeOnButton.setOnAction(event->
			{
				darkModeOffButton.setStyle("-fx-background-color:Grey");
				darkModeOnButton.setStyle("-fx-background-color:Orange");
			}
			);
			
			darkModeOffButton.setOnAction(event->
			{
				darkModeOffButton.setStyle("-fx-background-color:Orange");
				darkModeOnButton.setStyle("-fx-background-color:Grey");
			});
			darkModeOffButton.fire();
			HBox hboxContainingButton=new HBox();
			hboxContainingButton.getChildren().addAll(darkModeOffButton,darkModeOnButton);
			
			
			//ToggleSwitch darkModeToggle = new ToggleSwitch();
			HBox hboxForLabelandToggleButton = new HBox(75);
			hboxForLabelandToggleButton.getChildren().addAll(darkModeLabel, hboxContainingButton);
			Label imageDirectoryLabel = new Label("Image Directory");
			imageDirectoryLabel.setStyle("-fx-text-fill: orange;");
			imageDirectoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
			HBox hboxForimageTextfieldandBrowseButton = new HBox(40);
			//create a text field for image directory path
			TextField imagePathTextField = new TextField();
			//load selected image path from properyies file
			String imagePath = Utilty.loadSelectedValue(properties1, "imagepath");
			// setting the image path to textfield
			imagePathTextField.setText(imagePath);
			Button browseButton = new Button("BROWSE");
			browseButton.setStyle("-fx-background-color: orange;-fx-text-fill: white;-fx-background-radius: 5;");

			hboxForimageTextfieldandBrowseButton.getChildren().addAll(imagePathTextField, browseButton);
			Label imageAmountLabel = new Label("Default Image Amount Level");
			imageAmountLabel.setStyle("-fx-text-fill: orange;");
			imageAmountLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
			// Slider imageAmountSlider = new Slider(0, 100, 50);
			Label templatesLabel = new Label("Prepopulated Templates");
			templatesCombobox = new ComboBox<>();
			templatesCombobox.getItems().addAll(2,3,4,5,6,7,8,9,10,11,12);
			// int selectedValueForImageAmount = loadSelectedValue();

			String selectedValueForImageAmount = Utilty.loadSelectedValue(properties, "selectedValueForImage");

			if (selectedValueForImageAmount != null && !selectedValueForImageAmount.isEmpty()) {
				System.out.println(selectedValueForImageAmount);
				try {
					int imageamount = Integer.parseInt(selectedValueForImageAmount);
					templatesCombobox.setValue(imageamount);
				} catch (NumberFormatException e1) {
					// Handle the case where selectedValueForImageAmount is not a valid integer
					System.err.println("Error parsing selected value for image amount: " + selectedValueForImageAmount);
				}
			}

			templatesCombobox.setOnAction(event -> {
				System.out.println("image amount is saved");
				// calling the utility method for saving the value into property file
				Utilty.settingSaving(properties, String.valueOf(templatesCombobox.getValue()), "selectedValueForImage");
			});

			HBox hBoxdefaultImageAmountLabelAndCombobox = new HBox(60);
			VBox vboxForUploadPageOnStartup = new VBox(10);
			HBox hboxForUploadPageOnStartuplabelandButton = new HBox(40);
			Label uploadPageOnStartUpLabel = new Label("Upload page on Startup");
			uploadPageOnStartUpLabel.setStyle("-fx-text-fill: orange;");
			uploadPageOnStartUpLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

			Label labelForUploadPageOnStartup = new Label("Set Upload as StartUp Page");
			ToggleSwitchButton toggleSwitchForSetOnStartUpPage = new ToggleSwitchButton();
			try {
				// Load the properties file
				toggleSwitchForSetOnStartUpPage.properties2.load(new FileInputStream("settings.properties"));
				// Get the property value and print it out
				String selectedPath = toggleSwitchForSetOnStartUpPage.properties2.getProperty("state");
				System.out.println("Selected Value: " + selectedPath);

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			hboxForUploadPageOnStartuplabelandButton.getChildren().addAll(labelForUploadPageOnStartup,
					toggleSwitchForSetOnStartUpPage);
			vboxForUploadPageOnStartup.getChildren().addAll(uploadPageOnStartUpLabel,
					hboxForUploadPageOnStartuplabelandButton);
			hBoxdefaultImageAmountLabelAndCombobox.getChildren().addAll(templatesLabel, templatesCombobox);
			// Set up the layout of the settings dialog
			VBox dialogVBox = new VBox(hboxForsettingsLabelandCrossButton, displayLabel, hboxForLabelandToggleButton,
					imageDirectoryLabel, hboxForimageTextfieldandBrowseButton, imageAmountLabel,
					hBoxdefaultImageAmountLabelAndCombobox, vboxForUploadPageOnStartup

			);
			dialogVBox.setSpacing(10);
			// Set padding for the entire DialogPane
			DialogPane dialogPane = settingsDialog.getDialogPane();
			dialogPane.setContent(dialogVBox);
			dialogPane.setPadding(new Insets(0)); // Adjust the padding as needed
			// Inside the browseButton.setOnAction(...) handler
			browseButton.setOnAction(event -> {
				DirectoryChooser directoryChooser = new DirectoryChooser();
				// Show the directory chooser and get the selected directory
				File selectedDirectory = directoryChooser.showDialog(null);
				if (selectedDirectory != null) {
					imagePathTextField.setText(selectedDirectory.getAbsolutePath());
					// saveSettingForImagePath(selectedDirectory.getAbsolutePath());
					Utilty.settingSaving(properties1, selectedDirectory.getAbsolutePath(), "imagepath");
				}

			});

			///  Create a semi-transparent overlay pane
			Screen screen = Screen.getPrimary();
			// get the visual bounds of the screen
			Rectangle2D bounds = screen.getVisualBounds();
			// careted a stackpane to overlaying the main content
			StackPane overlay = new StackPane();
			overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
			overlay.setPrefSize(bounds.getWidth(), bounds.getHeight());
			// casting the parent node to stackpane
			StackPane root = (StackPane) dasBoardStage.getScene().getRoot();
			// adding the overlay on the top of the root stackpane
			root.getChildren().add(overlay);

			settingsDialog.showAndWait();
			// Remove the overlay pane after the dialog is closed
			root.getChildren().remove(overlay);

		});
		// Set graphic for menu items adding graphic for Menu item
		settingsItem.setGraphic(settingsLabel);
		logoutItem.setGraphic(logoutLabel);
		contextMenu.getItems().addAll(settingsItem, new SeparatorMenuItem(), logoutItem);
		btnForContectMenu.setOnAction(event -> {

			// showing the context menu at the this image view and at specific cordinatefrom
			// x and y
			 Point2D btnLocation = btnForContectMenu.localToScreen(0, 0);

	            // Calculate the position where you want the ContextMenu to appear
	      double x = btnLocation.getX() ; // X offset from the button
	            double y = btnLocation.getY()+30 ;   // Y offset from the button

			contextMenu.show(btnForContectMenu, x, y);
		});

         //     SITE SECTION
		sitecomboBox = new ComboBox<>();
		sitecomboBox.setPrefSize(160, 30);
		sitecomboBox.setEditable(false); // Set to false so it won't work as a text field
		sitecomboBox.setStyle("-fx-background-color: transparent;");
		sitecomboBox.setItems(items);

		sitecomboBox.setValue(Utilty.loadSelectedValue(propertyForSiteComboBox, "selectedsite"));
		VBox vboxForSite = new VBox();
		Label siteLabel = new Label("Clinic Site");
		siteLabel.setPadding(new Insets(0, 0, 0, 8));
		vboxForSite.getChildren().addAll(siteLabel, sitecomboBox);
		/*
		 * setting an action for sitelabel when it is clicked than combobox will be opened
	 */
     siteLabel.setOnMouseClicked(e->
     {
    	 sitecomboBox.show();
     });
	
		// Set the style for the VBox
		vboxForSite.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		vboxForLabelandsiteVbox = new VBox(90);
		// adding padding for the vbox containg vbox for combobox and searchtestfield
		vboxForLabelandsiteVbox.setPadding(new Insets(0, 0, 20, 20));
		// vbox for searchtextfield and vboxforsite
		vboxForLabelandsiteVbox.getChildren().add( vboxForSite);
		vboxForLabelandsiteVbox.setMaxWidth(160);// setting the maximum width of vbox
		dashboardRoot.setBottom(vboxForLabelandsiteVbox);

/*site selection closed*/

		HBox hboxForusernameLabelandimage = new HBox(6);
		hboxForusernameLabelandimage.getChildren().addAll(usernameLabel, btnForContectMenu);

		// Add the components to the usernameBox
		usernameBox.getChildren().addAll(createShortcutPane(shortcutCircle, shortcutLabel), usernameLabel,
				btnForContectMenu);
		usernameBox.setPadding(new Insets(12, 20, 0, 0));

		// Set the top of the BorderPane to usernameBox
		dashboardRoot.setTop(usernameBox);
		// Create an ImageView for the Eyepacs logo
		ImageView eyepacsLogo = new ImageView(
				new Image(new FileInputStream("resources/images/eyepacslogo.png.png")));
		eyepacsLogo.setFitHeight(50);
		eyepacsLogo.setFitWidth(150);
		// Create a VBox to hold the Eyepacs logo and "Add Patient" button
		VBox logoAndButtonVBox = new VBox(32);
		Pane fixedStackPane = new Pane();
		fixedStackPane.setPrefSize(1100, 600);
		this.fixedPane = fixedStackPane;

		// Set the position of the Pane to create a margin from the left and top
		fixedPane.setTranslateX(200); // Left margin
		fixedPane.setTranslateY(50); // Top margin
		fixedPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));

		//// UPLOAD BUTTON
		HBox uploadButtonHbox = new HBox(10);
		uploadButtonHbox.setAlignment(Pos.CENTER_LEFT);
		uploadbtn = new Button("Upload");
		// Adjust the font family, weight, and size as needed
		Font font = Font.font("Arial", FontWeight.BOLD, 12);
		uploadbtn.setFont(font);
		uploadRectangle = new Rectangle();
		uploadRectangle.setHeight(38);
		uploadRectangle.setFill(Color.TRANSPARENT);
		uploadRectangle.setWidth(10);
		uploadbtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		uploadImageView = new ImageView(new Image(new FileInputStream("resources/images/upload_icon.png")));
		uploadImageView.setFitWidth(25); // Set your desired width
		uploadImageView.setFitHeight(25); // Set your desired height
		uploadButtonHbox.getChildren().addAll(uploadRectangle, uploadImageView, uploadbtn);

		/*
		 * setting action for upload button
		 * 
		 */
		uploadbtn.setOnAction(e -> {
			patientRectangle.setFill(Color.TRANSPARENT);
			uploadRectangle.setFill(Color.ORANGE);
			helpRectangle.setFill(Color.TRANSPARENT);
			dashboardbtn.setTextFill(Color.GREY);
			dashboardRectangle.setFill(Color.TRANSPARENT);
			patientbtn.setTextFill(Color.GREY);
			uploadbtn.setTextFill(Color.ORANGE);
			helpbtn.setTextFill(Color.GREY);
			try {
				changeImageforUplaod(uploadImageView);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			UploadResult uploadResult = new UploadResult();
			try {
				uploadResult.displayUpload(stage, borderPane, "upload result page", fixedPane,username,password,sitecomboBox,null,null,null,null,null,null,null,null,null,null,null);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println();

		});
		
		
		/*
		 * 	( Stage stage, BorderPane borderPane, String result, Pane fixedRectangleStackPane,
			String username, String password, ComboBox<String> sitecombo,String patientNameForEdit,Integer PatientId, Rectangle patientRectangle, Rectangle uploadRectangle, Rectangle dashboardRectangle, Button uploadButtonForedit, Button dashBoardButtonForEdit, Button patientButtonForEdit, ImageView uploadImageviewForEdit, ImageView dashboardImageViewForEdit, ImageView patientImageViewForEdit
		 */
		//// DASHBOARDBUTTON
		HBox dashboardButtonHbox = new HBox(10);
		dashboardButtonHbox.setAlignment(Pos.CENTER_LEFT);
		dashboardbtn = new Button("Dashboard");
		Font font1 = Font.font("Arial", FontWeight.BOLD, 12); // Adjust the font family, weight, and size as needed
		dashboardbtn.setFont(font1);
		dashboardRectangle = new Rectangle();
		dashboardRectangle.setHeight(37);
		dashboardRectangle.setWidth(10);
		dashboardRectangle.setFill(Color.TRANSPARENT);
		dashboardbtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		dashboardImageView = new ImageView(
				new Image(new FileInputStream("resources/images/dashboard_icon.png")));
		dashboardImageView.setFitWidth(25); // Set your desired width
		dashboardImageView.setFitHeight(25); // Set your desired height
		dashboardButtonHbox.getChildren().addAll(dashboardRectangle, dashboardImageView, dashboardbtn);

		/*
		 * Adding action for dashboard button
		 */
		dashboardbtn.setOnAction(e -> {

			dashboardRectangle.setFill(Color.ORANGE);
			uploadRectangle.setFill(Color.TRANSPARENT);
			helpRectangle.setFill(Color.TRANSPARENT);
			patientRectangle.setFill(Color.TRANSPARENT);
			dashboardbtn.setTextFill(Color.ORANGE);
			uploadbtn.setTextFill(Color.GREY);
			patientbtn.setTextFill(Color.GREY);
			helpbtn.setTextFill(Color.GREY);
			try {
				changeImageforUplaod(dashboardImageView);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DashBoardResult dashBoardResult = new DashBoardResult();
			try {
				dashBoardResult.displayResult(stage, borderPane, "dashBoard Result page opened", fixedPane, username, password,sitecomboBox,dashboardRectangle,uploadRectangle,patientRectangle,uploadbtn,patientbtn,dashboardbtn,dashboardImageView,uploadImageView,patientImageview);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
/**
 * (Stage stageForDashBoardResult, BorderPane borderPane, String result, Pane fixedRec,
			String userName, String password,ComboBox<String> siteCombo,Rectangle dashBoardRectangle,Rectangle uploadRectangle,Rectangle patientRectangle,Button uploadButton,Button patientButton,Button dasBoardButton,ImageView dashboardImageView,ImageView uploadImageView,ImageView patientImagweView)
 */
		});
		

		// PATIENT BUTTON
		HBox patientButtonHbox = new HBox(10);
		patientbtn = new Button("Patients");
		// setting the all child node allignment to centre left
		patientButtonHbox.setAlignment(Pos.CENTER_LEFT);

		Font font2 = Font.font("Arial", FontWeight.BOLD, 12); // Adjust the font family, weight, and size as needed
		patientbtn.setFont(font2);

		patientRectangle = new Rectangle();
		patientRectangle.setHeight(38);
		patientRectangle.setWidth(10);
		patientRectangle.setFill(Color.TRANSPARENT);

		patientbtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		patientImageview = new ImageView(new Image(new FileInputStream("resources/images/patients_icon.png")));
		patientImageview.setFitWidth(25); // Set your desired width
		patientImageview.setFitHeight(25); // Set your desired height
		patientButtonHbox.getChildren().addAll(patientRectangle, patientImageview, patientbtn);

		/**
		 * adding action for patient Button
		 */

		patientbtn.setOnAction(e -> {
			patientRectangle.setFill(Color.ORANGE);
			uploadRectangle.setFill(Color.TRANSPARENT);
			helpRectangle.setFill(Color.TRANSPARENT);
			dashboardbtn.setTextFill(Color.GREY);
			dashboardRectangle.setFill(Color.TRANSPARENT);
			patientbtn.setTextFill(Color.ORANGE);
			uploadbtn.setTextFill(Color.GREY);
			helpbtn.setTextFill(Color.GREY);
			try {
				changeImageforUplaod(patientImageview);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			PatientsResult patientResult = new PatientsResult();
			try {
				patientResult.displayPatientResult(stage, borderPane, "dashBoard Result page opened", fixedPane,sitecomboBox,username,password,patientRectangle,uploadRectangle,dashboardRectangle,uploadbtn,dashboardbtn,patientbtn,uploadImageView,dashboardImageView,patientImageview);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		// Help Button HBOX
		HBox helpButtonHbox = new HBox(10);
		// setting the all child node allignment to centre left
		helpButtonHbox.setAlignment(Pos.CENTER_LEFT);
		helpbtn = new Button("Help");

		Font font3 = Font.font("Arial", FontWeight.BOLD, 12); // Adjust the font family, weight, and size as needed
		helpbtn.setFont(font3);
		helpRectangle = new Rectangle();
		helpRectangle.setHeight(36);
		helpRectangle.setWidth(10);
		// helpRectangle.setFill(Color.TRANSPARENT);
		helpRectangle.setFill(Color.TRANSPARENT);

		helpbtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		helpImageView = new ImageView(new Image(new FileInputStream("resources/images/help_icons.png")));
		helpImageView.setFitWidth(25); // Set your desired width
		helpImageView.setFitHeight(25); // Set your desired height
		helpButtonHbox.getChildren().addAll(helpRectangle, helpImageView, helpbtn);

		/**
		 * Adding action for help button
		 * 
		 */
		helpbtn.setOnAction(event -> {
			patientRectangle.setFill(Color.TRANSPARENT);
			uploadRectangle.setFill(Color.TRANSPARENT);
			helpRectangle.setFill(Color.ORANGE);
			dashboardbtn.setTextFill(Color.GREY);
			dashboardRectangle.setFill(Color.TRANSPARENT);
			patientbtn.setTextFill(Color.GREY);
			uploadbtn.setTextFill(Color.GREY);
			helpbtn.setTextFill(Color.ORANGE);
			try {
				changeImageforUplaod(helpImageView);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			HelpResult helpResult = new HelpResult();
			try {
				helpResult.displayPatientResult(stage, borderPane, "help Result Page", fixedPane);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		/// Add Patient Button
		addPatientbtn = new Button("+" + "  New Patient");
		addPatientbtn.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;");
		double buttonWidth = 140;
		double buttonHeight = 30;
		addPatientbtn.setMinSize(buttonWidth, buttonHeight);

		/*
		 * Adding action for addPatient Button
		 */
		addPatientbtn.setOnAction(e -> {
			AddNewPatient addNewPatient = new AddNewPatient();
			try {
				addNewPatient.handleAddPatient(stage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		// Vbox containg eypacs logo and button
		VBox vboxContainingeyPacsImageViewAndAddPatientButton = new VBox(20);
		vboxContainingeyPacsImageViewAndAddPatientButton.getChildren().addAll(eyepacsLogo, addPatientbtn);
		vboxContainingeyPacsImageViewAndAddPatientButton.setPadding(new Insets(0, 0, 0, 20));
		logoAndButtonVBox.getChildren().addAll(vboxContainingeyPacsImageViewAndAddPatientButton, uploadButtonHbox,
				dashboardButtonHbox, patientButtonHbox, helpButtonHbox);
		// setting on left to logoAndButtonVBox
		dashboardRoot.setLeft(logoAndButtonVBox);
		// Add the BorderPane to the Dashboard StackPane
		dashboardStackPane.getChildren().addAll(dashboardRoot, fixedStackPane);
		// Get the screen bounds
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		// Calculate the width and height as 90% of the screen size
		double width = screenBounds.getWidth() * 0.99;
		double height = screenBounds.getHeight() * 0.99;

		// FileInputStream inputforUplaodasLandingPage=new FileInputStream();
		ToggleSwitchButton toggleSwitchForSetOnStartUpPage = new ToggleSwitchButton();

		try {
			// Load the properties file
			toggleSwitchForSetOnStartUpPage.properties2.load(new FileInputStream("settings.properties"));
			// Get the property value and print it out
			String selectedPath = toggleSwitchForSetOnStartUpPage.properties2.getProperty("state");
			System.out.println("Selected Value: " + selectedPath);
			if (selectedPath != null) {
				if (selectedPath.equals("true")) {
					uploadbtn.fire();
				}
				else {
					dashboardbtn.fire();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		//Utilty utilty=new Utilty();
		//utilty.setUpSiteInitiazation(username,password,sitecomboBox);
		
		//System.out.println("site id from utility is"+utilty.getSiteIdFromComboBox());
	//	// Create a layout for the Dashboard scene
		Scene dashboardScene = new Scene(dashboardStackPane, width, height);

		dasBoardStage.setScene(dashboardScene);
		dasBoardStage.show();

	}

	/***
	 * method handling logout handling the logout
	 * 
	 * @throws Exception
	 */
	private void handleLogout() throws Exception {
		// Close the current dashboard stage
		stage.close();
		// Create a new Login instance and show the login page
		Login login = new Login();
		Stage loginStage = new Stage();
		login.start(loginStage);
	}

	/***
	 *
	 * @param circle- Circle as passed as the argument
	 * @param label-  label as argimeny
	 * @return- return the stackpane consisisting of circle
	 */

	private StackPane createShortcutPane(Circle circle, Label label) {
		StackPane shortcutPane = new StackPane();

		// Add the circle to the StackPane
		shortcutPane.getChildren().add(circle);

		// Set the label as the center node of the StackPane
		shortcutPane.setAlignment(Pos.CENTER);
		shortcutPane.getChildren().add(label);

		return shortcutPane;
	}

	/***
	 * Method for genearting the shortcut
	 * 
	 * @param username -username is passed as the argument
	 * @return -returning a string consisting of shortcut
	 */
	private String generateShortcut(String username) {
		// Generate a dynamic shortcut based on the first letter of each word in the
		// username
		String[] words = username.split("\\s+");
		StringBuilder shortcutBuilder = new StringBuilder();

		for (String word : words) {
			if (!word.isEmpty()) {
				shortcutBuilder.append(word.charAt(0));
			}
		}

		return shortcutBuilder.toString().toUpperCase(); // Convert to uppercase for consistency
	}

	/**
	 * method for createing the the header for setting dialog including label and
	 * 
	 * @return
	 * @throws FileNotFoundException 
	 */

	private HBox createHeaderForSettingsDialog() throws FileNotFoundException {
		HBox headerHBox = new HBox(200);
		Label addPatientLabel = new Label("Settings");
		addPatientLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14;");
		Button btnForClosingSettingDialog = new Button();
		Image closeImage = new Image(new FileInputStream("resources/images/Exit.png"));
		ImageView imageView = new ImageView(closeImage);
		imageView.setFitWidth(8); // Set your desired width
		imageView.setFitHeight(8); // Set your desired height
		btnForClosingSettingDialog.setGraphic(imageView);
		btnForClosingSettingDialog.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

		// Handle close button click
		btnForClosingSettingDialog.setOnAction(e -> {

			Stage stage = (Stage) btnForClosingSettingDialog.getScene().getWindow();
			stage.close();

		});

		headerHBox.getChildren().addAll(addPatientLabel, btnForClosingSettingDialog);
		return headerHBox;
	}

	/**
	 * method for changing the color of icon in dashbaord
	 * 
	 * @param View
	 * @throws FileNotFoundException 
	 */
	private void changeImageforUplaod(ImageView View) throws FileNotFoundException {
		if (View == uploadImageView) {
			// Load the orange-colored image
			Image orangeImage =
					new Image(new FileInputStream("resources/images/orange_upload_icon.jfif"));
			// Set the orange image to the ImageView
			uploadImageView.setImage(orangeImage);
			uploadImageView.setFitHeight(25);
			uploadImageView.setFitWidth(25);
		} else {
			Image originalImage = new Image(new FileInputStream("resources/images/upload_icon.png"));
			uploadImageView.setImage(originalImage);
			uploadImageView.setFitHeight(25);
			uploadImageView.setFitWidth(25);

		}
		if (View == dashboardImageView) {
			// Load the orange-colored image
			Image orangeImage = new Image(new FileInputStream("resources/images/dashboard_orange.png"));
			// Set the orange image to the ImageView
			dashboardImageView.setImage(orangeImage);
			dashboardImageView.setFitHeight(25);
			dashboardImageView.setFitWidth(25);
		} else {
			Image originalImage = new Image(new FileInputStream("resources/images/dashboard_icon.png"));
			dashboardImageView.setImage(originalImage);
			dashboardImageView.setFitHeight(25);
			dashboardImageView.setFitWidth(25);
		}
		if (View == patientImageview) {
			// Load the orange-colored image
			Image orangeImage = new Image(new FileInputStream("resources/images/patient_orange.png"));
			// Set the orange image to the ImageView
			patientImageview.setImage(orangeImage);
			patientImageview.setFitHeight(25);
			patientImageview.setFitWidth(25);
		} else {
			Image originalImage = new Image(new FileInputStream("resources/images/patients_icon.png"));
			patientImageview.setImage(originalImage);
			patientImageview.setFitHeight(25);
			patientImageview.setFitWidth(25);
		}
		if (View == helpImageView) {
			// Load the orange-colored image
			Image orangeImage = new Image(new FileInputStream("resources/images/help_orange.png"));
			// Set the orange image to the ImageView
			helpImageView.setImage(orangeImage);
			helpImageView.setFitHeight(25);
			helpImageView.setFitWidth(25);
		} else {

			Image originalImage = new Image(new FileInputStream("resources/images/help_icons.png"));
			helpImageView.setImage(originalImage);
			helpImageView.setFitHeight(25);
			helpImageView.setFitWidth(25);
		}

	}

	/**
	 * changing the 1st letter of the userName to capital and
	 * 
	 * @param usernameLabel
	 * @return
	 */
	private String userNameLabel(String usernameLabel) {
		String userName[] = usernameLabel.split(" ");
		StringBuilder username = new StringBuilder();
		for (String user : userName) {
			username.append(Character.toUpperCase(user.charAt(0))).append(user.substring(1).toLowerCase()).append(" ");
		}
		// setting the length of the username to remove the last space
		username.setLength(username.length() - 1);

		return username.toString();

	}

}
