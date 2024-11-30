package com.autouploader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import com.eyepacs.ext.connectors.https.HttpsConnectors;
import com.eyepacs.ext.connectors.https.HttpsConnectors.RESPONSE;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ext.eyepacs.encryptor.aes.EyePacsEncryptorAES;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.skin.ComboBoxListViewSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Login extends Application {
	public static ComboBox<String> siteComboBox;
	private Button submitButton;
	private VBox root;
	private Button loginButton;
	private Hyperlink forgetPasswordLink;
	protected static TextField userNameField;
	protected static PasswordField passwordField;
	public Properties propertyForSiteComboBox = new Properties();
	private VBox vboxForSiteAndlabel;
	private HBox hboxForsubmitAndLogin;
	private HBox usernameHBox;
	private HBox passwordHBox;
	private ImageView eyepacslogo;
	private final String defaultValue = "--Select site--";
	private Screen screen;
	private ObservableList<String> items;
	private Label loginerror;
	private Label siteErrorLabel;
	protected static Map<String, String> siteMap;
	private ImageView refreshImageView;
	private Timeline idleTimer; // Timeline for tracking idle time

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create a StackPane to enclose the content
		StackPane stackPane = new StackPane();
		// Create a Rectangle as the background
		Rectangle background = new Rectangle(400, 400); // Set your desired dimensions
		background.setFill(Color.WHITE);

		root = new VBox(22);
		eyepacslogo = new ImageView(new Image(new FileInputStream("resources/images/eyepacslogo.png.png")));
		eyepacslogo.setFitWidth(140);
		eyepacslogo.setFitHeight(50);
		screen = Screen.getPrimary();

		// Create the username field with icon
		usernameHBox = new HBox(12);

		double screenWidthPOfUserName = screen.getVisualBounds().getWidth();
		usernameHBox.setPadding(new Insets(0, 0, 0, screenWidthPOfUserName * .42));
		ImageView userIcon = new ImageView(new Image(new FileInputStream("resources/images/user.png.png")));
		userIcon.setFitHeight(20);
		userIcon.setFitWidth(20);
		userNameField = new TextField();
		userNameField.setStyle("-fx-background-color: transparent;");
		userNameField.setPromptText("Username");
		ObservableList<Node> children = usernameHBox.getChildren();
		children.addAll(userIcon, userNameField);
		// Create the password field with icon
		passwordHBox = new HBox(10);
		double screenWidthPOfPassword = screen.getVisualBounds().getWidth();
		double percentagePadding = 0.42 * screenWidthPOfPassword; // 20% padding
		// Apply the padding to the HBox
		passwordHBox.setPadding(new Insets(0, 0, 0, percentagePadding));
		ImageView passwordIcon = new ImageView(new Image(new FileInputStream("resources/images/pass-icon.png.jpg")));
		passwordIcon.setFitWidth(20); // Set your desired width
		passwordIcon.setFitHeight(20); // Set your desired height
		passwordField = new PasswordField();
		passwordField.setPromptText("Password");
		passwordField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		loginButton = new Button("Submit");
		HBox buttonAndImageViewHBox = new HBox();
		refreshImageView = new ImageView(new Image(new FileInputStream("resources/images/loading_gif.gif")));
		refreshImageView.setFitHeight(15);
		refreshImageView.setFitWidth(15);
		refreshImageView.setManaged(false);
		refreshImageView.setVisible(false);
		buttonAndImageViewHBox.getChildren().addAll(loginButton, refreshImageView);
		Button cancelButton = new Button("Cancel");
		loginerror = new Label("Invalid username or password");
		loginerror.setVisible(false);
		loginerror.setManaged(false);
		loginButton.setPrefSize(120, 25);
		cancelButton.setPrefSize(70, 25);
		HBox loginHbox = new HBox(3);
		loginHbox.getChildren().addAll(loginButton, cancelButton);
		loginButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;"); // Orange color
		forgetPasswordLink = new Hyperlink(" Forgot your password ?");

		forgetPasswordLink.setStyle("-fx-underline: true;");
		/**
		 * adding action for forgot your password page
		 */

		forgetPasswordLink.setOnAction(e -> {
			// Create a dialog
			Dialog<Void> dialog = new Dialog<>();
			dialog.setTitle("Forgot Your Password ?");

			dialog.initStyle(StageStyle.UNDECORATED);

			// Create a VBox for the layout
			VBox dialogPane = new VBox();
			dialogPane.setSpacing(10);
			dialogPane.setPrefHeight(100);
			dialogPane.setAlignment(Pos.CENTER); // Center the contents vertically

			// Create the label
			Label label = new Label("Enter your email to reset your password:");
			label.setStyle("-fx-font-weight: bold;");
			label.setStyle("-fx-text-fill: orange;"); // Set text color to orange

			// Create a label for additional information
			Label labelForEmail = new Label(
					"The email you enter should match the email stored in our system. We will then send you a link to reset your username and password.");

			// Create the text field for the email
			TextField emailField = new TextField();
			emailField.setPromptText("Email");

			// Create the submit button
			Button submitButton = new Button("Submit");
			HBox hboxForEmailAndSubmitButton = new HBox(10);
			hboxForEmailAndSubmitButton.getChildren().addAll(emailField, submitButton);
			hboxForEmailAndSubmitButton.setAlignment(Pos.CENTER);

			// Handle email submission
			submitButton.setOnAction(event -> {
				String email = emailField.getText();
				// Handle email submission logic here
				System.out.println("Email submitted: " + email);
				// dialog.close(); // Close the dialog after submission
			});

			// Add components to the VBox
			try {
				dialogPane.getChildren().addAll(createHeaderHBox(), label, labelForEmail, hboxForEmailAndSubmitButton);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Set the dialog pane
			dialog.getDialogPane().setContent(dialogPane);
			dialog.getDialogPane().setStyle("-fx-background-color: transparent;");

			// Show the dialog
			dialog.showAndWait(); // Use showAndWait to wait for the dialog to close
		});

		passwordHBox.getChildren().addAll(passwordIcon, passwordField, forgetPasswordLink);
		root.getChildren().addAll(eyepacslogo, loginerror, usernameHBox, passwordHBox, loginButton, refreshImageView,
				forgetPasswordLink);
		// positionling on top from the vertical and horizom
		root.setAlignment(Pos.CENTER);
		stackPane.getChildren().addAll(background, root); // Add the Rectangle and VBox to the StackPane
		loginButton.setOnAction(event -> {
			// Disable the login button to prevent multiple clicks

			// Show the refreshImageView
			refreshImageView.setVisible(true);
			refreshImageView.setManaged(true);
			loginButton.setDisable(true);

			// Create a pause transition
			// PauseTransition pause = new PauseTransition(Duration.millis(200)); // Adjust
			// time as needed

			// pause.setOnFinished(e -> {
			// try {
			// Perform the login operation
			try {
				handleLogin(userNameField.getText(), passwordField.getText(), primaryStage);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			refreshImageView.setVisible(false);
			refreshImageView.setManaged(false);
			
		});
		// Get the screen bounds
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		// Calculate the width and height as 90% of the screen size
		double width = screenBounds.getWidth() * 0.99;
		double height = screenBounds.getHeight() * 0.99;
		// Set up the scene
		Scene scene = new Scene(stackPane, width, height);
		primaryStage.setScene(scene);
		primaryStage.show();// show the stage

		// Start the idle timer
		startIdleTimer(primaryStage);

		// Reset timer on user interaction
		scene.setOnMouseMoved(event -> resetIdleTimer());
		scene.setOnKeyPressed(event -> resetIdleTimer());
	}

	/****
	 * The startIdleTimer method sets up an automatic logout mechanism by initiating an idle timer. This feature enhances security by logging out users after a period of inactivity.
	 * @param primaryStage2
	 */
	private void startIdleTimer(Stage primaryStage2) {
		idleTimer = new Timeline(new KeyFrame(Duration.minutes(60), event -> {
			try {
				handleLogout(primaryStage2); // Automatically logout
			} catch (Exception e) {
				e.printStackTrace();
			}
		}));
		idleTimer.setCycleCount(Timeline.INDEFINITE);
		idleTimer.play(); // Start the timer
	}

	/***
	 * The method creates an HBox layout that serves as a customizable header section. In this example, it displays a label for "Forgot Your Password?" along with a button to close the dialog, which is represented by an "X" icon. 
	 * @return Hbox containing header and closing button
	 * @throws FileNotFoundException
	 */
	private HBox createHeaderHBox() throws FileNotFoundException {
		HBox headerHBox = new HBox(600);
		headerHBox.setStyle("-fx-background-color: lightgrey;");
		Label addPatientLabel = new Label("Forgot Your Password ?");
		addPatientLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 14;");
		// Create a close button with a cross symbol
		Button btnForClosingDialog = new Button();

		btnForClosingDialog.setStyle("-fx-background-color: rgba(0, 0, 0, 0);  -fx-border-color: rgba(0, 0, 0, 0);");
		Image closeImage = new Image(new FileInputStream("resources/images/Exit.png"));
		ImageView imageView = new ImageView(closeImage);
		imageView.setFitWidth(8); // Set your desired width
		imageView.setFitHeight(8); // Set your desired height

		btnForClosingDialog.setGraphic(imageView);
		btnForClosingDialog.setOnAction(e -> {
			Stage stage = (Stage) btnForClosingDialog.getScene().getWindow();
			stage.close();
		});

		// Add components to the HBox
		headerHBox.getChildren().addAll(addPatientLabel, btnForClosingDialog);
		return headerHBox;
	}
/***
 * Handles the logout process by closing the current application window (primaryStage2) and reopening a new login window. This method closes the active session and creates a new Stage to restart the application at the login screen. 
 * @param primaryStage2
 * @throws Exception
 */
	private void handleLogout(Stage primaryStage2) throws Exception {
		// TODO Auto-generated method stub

		primaryStage2.close();
		Stage loginStage = new Stage();
		start(loginStage);

	}
/***
 * Resets the idle timer by restarting it from the beginning. This method is typically called upon user interaction (e.g., mouse movement or key press) to prevent automatic logout due to inactivity. 
 */
	private void resetIdleTimer() {
		idleTimer.playFromStart(); // Reset the timer to the beginning
	}

	/***
	 * 
	 * @param s1
	 * @param userPassword
	 * @param primaryStage
	 * @throws IOException handling the login and creating the site section
	 */

	public void handleLogin(String userName, String userPassword, Stage primaryStage) throws IOException {

		siteErrorLabel = new Label("Please select site to proceed");
		siteErrorLabel.setVisible(false);
		siteErrorLabel.setManaged(false);
		// Fetch site data from API
		Map<HttpsConnectors.RESPONSE, String> response = getSite(userName, userPassword);
		// Create a map to hold site IDs and site names
		siteMap = new HashMap<>();
		System.out.println(response);
		// Check if response contains valid data
		if (response.get(HttpsConnectors.RESPONSE.CODE).equals("200")) {
			userNameField.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
			passwordField.setStyle("-fx-background-color: transparent;");
			loginerror.setVisible(false);
			loginerror.setManaged(false);
			userNameField.setEditable(false);
			passwordField.setEditable(false);
			// Process the response
			String responseBody = response.get(HttpsConnectors.RESPONSE.VALUE);
			System.out.println(responseBody);
			if (responseBody != null) {
				// Parse the JSON to extract site names
				try {
					JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
					// Create an observable list for site names
					ObservableList<String> items = FXCollections.observableArrayList();
					for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
						String siteId = entry.getKey(); // Get the site ID (key)
						String siteName = entry.getValue().getAsString(); // Get the site name (value)
						// Optionally, print or log the siteId and siteName
						System.out.println("Site ID: " + siteId + ", Site Name: " + siteName);
						// Map the site name to its corresponding site ID
						siteMap.put(siteName, siteId);
						// Add the site name to the items list
						items.add(siteName);
					}
					System.out.println(siteMap);
					// System.out.println(getAllPatients());

					// Pass site data to createSiteSection
					VBox vbox = createSiteSection(primaryStage, userName, userPassword, items);
					root.getChildren().removeAll(loginButton, forgetPasswordLink);
					root.getChildren().add(vbox);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Response body is null.");
			}
		} else {

			loginerror.setVisible(true);
			loginerror.setManaged(true);
			loginButton.setDisable(false);
			loginerror.setStyle("-fx-text-fill:red");
			userNameField.setStyle("-fx-border-color:red;-fx-border-radius:5;-fx-background-radius:5;");
			passwordField.setStyle("-fx-border-color:red;-fx-border-radius:5;-fx-background-radius:5;");

		}
	}

	/**
	 * 
	 * @param stage
	 * @param s1
	 * @return vbox
	 * 
	 *         creating the site section if user enter the correct credentials
	 */
	private VBox createSiteSection(Stage stage, String userName, String password, ObservableList<String> siteItems) {
		VBox vboxForsiteAndButton = new VBox(10);
		vboxForSiteAndlabel = new VBox(10);
		vboxForSiteAndlabel.setAlignment(Pos.CENTER);
		items = siteItems;
		siteComboBox = new ComboBox<>();
		siteComboBox.setItems(items);
		siteComboBox.show();
		// Button fireButton=new Button();
//		fireButton.setVisible(false);
//		fireButton.setManaged(false);
		// siteComboBox.setPrefWidth(120);
		siteComboBox.setStyle(
				"-fx-background-color: white;-fx-border-color: black;-fx-border-width: 1px;-fx-border-radius:5;");
		// siteComboBox.setEditable(true); // Set editable to allow text input
		vboxForSiteAndlabel.getChildren().addAll(siteComboBox);
		// Add a listener to adjust the dropdown width to match the ComboBox width
//		fireButton.setOnAction(e->
//		{
//			siteComboBox.show();
//		});

//		TextField editor = siteComboBox.getEditor();
//		editor.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
//			String input = editor.getText();
//			int caretPosition = editor.getCaretPosition(); // Save the caret position
//			if (input.isEmpty()) {
//				siteComboBox.setItems(items);
//			} else {
//				ObservableList<String> filteredItems = FXCollections.observableArrayList();
//				for (String item : items) {
//					if (item.toLowerCase().contains(input.toLowerCase())) {
//						filteredItems.add(item);
//					}
//				}
//				siteComboBox.setItems(filteredItems);
//				siteComboBox.show();
//			}
//
//			editor.setText(input); // Reset the text
//			editor.positionCaret(caretPosition); // Restore the caret position
//		//	adjustComboBoxHeight(siteComboBox);
//		});
		// Update TextField when ComboBox selection changes
//		siteComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
//			if (newValue != null) {
//				editor.setText(newValue);
//				editor.positionCaret(newValue.length()); // Move the caret to the end
//			}
//		});
//		// Show ComboBox dropdown when TextField is clicked
//		editor.setOnMouseClicked(event -> {
//			if (siteComboBox.getValue().equals(defaultValue)) {
//				// Remove default value from editor
//				editor.setText("");
//				siteComboBox.show();
//
//			}
//		});
		// Manually show ComboBox dropdown when it gains focus
//	    siteComboBox.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//	        if (isNowFocused) {
//	        	if (siteComboBox.getItems() != null && !siteComboBox.getItems().isEmpty()) {
//	        		if(siteComboBox.getEditor().getText().equals(defaultValue))
//	        		{
//                    siteComboBox.show(); // Show the dropdown explicitly
//	        		}
//                }
//	        }
//	        
//	    });
//	 // Manually show ComboBox dropdown when it gains focus
//	    siteComboBox.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//	        if (isNowFocused) {
//	            if (siteComboBox.getItems() != null && !siteComboBox.getItems().isEmpty()) {
//	                if (!siteComboBox.isShowing()) {
//	                    siteComboBox.show();
//	                }
//	            }
//	        }
//	    });
//
//	    // Handle key events for better dropdown visibility
//	    siteComboBox.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
//	        if (event.getCode() == KeyCode.TAB) {
//	            if (!siteComboBox.isShowing()) {
//	                siteComboBox.show();
//	            }
//	        }
//	    });
		// Filtering logic
//	    editor.textProperty().addListener((observable, oldValue, newValue) -> {
//	        if (!siteComboBox.isFocused()) return; // Only filter when focused
//	        
//	        String input = newValue.toLowerCase();
//	        if (input.isEmpty()) {
//	            siteComboBox.setItems(items);
//	        } else {
//	            ObservableList<String> filteredItems = items.filtered(
//	                item -> item.toLowerCase().contains(input)
//	            );
//	            siteComboBox.setItems(filteredItems);
//	        }
//	        siteComboBox.show();
//	    });
//
//	    // Focus behavior
//	    siteComboBox.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
//	        if (isNowFocused) {
//	            siteComboBox.setItems(items); // Reset to full list
//	            if (!siteComboBox.isShowing()) {
//	                siteComboBox.show();
//	            }
//	        }
//	    });
//
//	    // Handle Tab key
//	    siteComboBox.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
//	        if (event.getCode() == KeyCode.TAB) {
//	        	siteComboBox.getEditor().setText("");
//	            siteComboBox.setItems(items); // Reset to full list
//	            if (!siteComboBox.isShowing()) {
//	                siteComboBox.show();
//	            }
//	        }
//	    });
//
//	    // Show ComboBox dropdown when TextField is clicked
//	    editor.setOnMouseClicked(event -> {
//	        if (siteComboBox.getValue().equals(defaultValue)) {
//	            editor.setText("");
//	            siteComboBox.setItems(items); // Reset to full list
//	            siteComboBox.show();
//	        }
//	    });

		// setting an action for site combo box
		siteComboBox.setOnAction(e -> {
			Utilty.settingSaving(propertyForSiteComboBox, siteComboBox.getValue(), "selectedsite");
		});
		submitButton = new Button("Login");
		submitButton.setPrefSize(120, 25);
		Button cancelButton = new Button("Cancel");
		cancelButton.setPrefSize(120, 25);
		cancelButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;");
		submitButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white;");
		hboxForsubmitAndLogin = new HBox(4);
		hboxForsubmitAndLogin.getChildren().addAll(submitButton, cancelButton);
		hboxForsubmitAndLogin.setAlignment(Pos.CENTER);
		vboxForsiteAndButton.getChildren().addAll(vboxForSiteAndlabel, siteErrorLabel, hboxForsubmitAndLogin);
		vboxForsiteAndButton.setSpacing(30);
		vboxForsiteAndButton.setAlignment(Pos.CENTER);

		/**
		 * setting action for submit button
		 */
		submitButton.setOnAction(e -> {
			try {
				handleSubmit(stage, userName, password);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});
		cancelButton.setOnAction(e -> {

			resetLoginForm();

		});

		return vboxForsiteAndButton;
	}

	/**
	 * 
	 * @param stage
	 * @param username
	 * @throws IOException handling the submit if credential are correct
	 */
	private void handleSubmit(Stage stage, String username, String password) throws IOException {
		// Check if the user has selected a site
		// String selectedSite = siteComboBox.getValue();
		// if (selectedSite.equals(defaultValue) ||
		// !siteComboBox.getItems().contains(selectedSite)) {
		if (siteComboBox.getValue() == null) {
			siteErrorLabel.setVisible(true);
			siteErrorLabel.setManaged(true);
			siteErrorLabel.setStyle("-fx-text-fill:red;");
			siteComboBox.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			return;
		}
		Dashboard dashboard = new Dashboard();
		System.out.println(dashboard);
		// Open the dashboard with the new Stage
		dashboard.OpenDashBoard(stage, username, items, password);
	}

	

	/**
	 * method to reset the login form
	 */

	private void resetLoginForm() {
		root.getChildren().clear();
		loginButton.setDisable(false);
		userNameField.setEditable(true);
		passwordField.setEditable(true);
		root.getChildren().addAll(eyepacslogo, usernameHBox, passwordHBox, loginButton, refreshImageView,
				forgetPasswordLink);
	}

	/**
	 * get site name
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public Map<RESPONSE, String> getSite(String userName, String password) {
		System.out.println(userName);
		System.out.println(password);
		// Encrypt credentials
		EyePacsEncryptorAES encryptor = new EyePacsEncryptorAES();
		String credentialString = encryptor.encryptEyePacsCredentials(userName, password);

		// Initialize HttpsConnectors
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, "uploader/auth/sites",
				HttpsConnectors.METHOD.POST);

		// Call the API
		Map<HttpsConnectors.RESPONSE, String> response = connector.doLogin(credentialString, "text/plain",
				"application/json");
		return response;

	}

	public ComboBox<String> getSiteComboBox() {
		return siteComboBox;
	}

	public void setSiteComboBox(ComboBox<String> siteComboBox) {
		this.siteComboBox = siteComboBox;
	}

	public TextField getUserNameField() {
		return userNameField;
	}

	public void setUserNameField(TextField userNameField) {
		this.userNameField = userNameField;
	}

	public PasswordField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(PasswordField passwordField) {
		this.passwordField = passwordField;
	}

	public Map<String, String> getSiteMap() {
		return siteMap;
	}

	public void setSiteMap(Map<String, String> siteMap) {
		this.siteMap = siteMap;
	}

}
