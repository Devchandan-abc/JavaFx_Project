package com.autouploader;

import javafx.animation.KeyFrame;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import com.eyepacs.ext.connectors.https.HttpsConnectors;
import com.eyepacs.ext.connectors.https.HttpsConnectors.RESPONSE;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import ext.eyepacs.encryptor.aes.EyePacsEncryptorAES;

public class UploadResult {
	private static TitledPane patientDetailsPane;
	private TitledPane medicalDetailsPane;
	private static TitledPane uploadTitledPane;
	private VBox vboxforaccordianandLabel;
	private VBox vboxforPateintLabel;
	private VBox vboxForMainContent;
	private ScrollPane scrollPane;
	private StackPane uploadStackPane;
	private Label previouslyClickedLabel;
	private ImageView validaionimageView;
	private ImageView uploadImageView;
	private ImageView medicalImageView;
	private Rectangle previouslyClickedRectangle;
	private String userName;
	private String userPassword;
	private PersonalDetails personalDetaails;
	private ComboBox<String> siteComboBoxForUpload;
	private Map<String, String> siteIdMap;
	private Map<String, String> siteMapping;
	public String selectedSiteIdFromSiteCombo;
	private Button btnForThisMonth;
	private Button btnForAllTime;
	private boolean isBtnForAllTimeClicked;
	private boolean isTodayButtonClicked;
	private HBox hboxForOrderId;
	private boolean isChangingProgrammatically;
	private boolean isProgrammaticClick;
	private ImageView loadingImageView;
	private String currentlyExpandedPatientId;
	private ImageView uploadImageViewForEdit;
	private ImageView patientImageViewForEdit;
	private ImageView dashBoardImageViewForEdit;
	private MedicalDetails medicalDetails;
	private Label previouslyClickedLablforEdit;
	private Rectangle previouslyClickedRectangleForEdit;
	private String currentlyClickedPatientId;

	public void displayUpload(Stage stage, BorderPane borderPane, String result, Pane fixedRectangleStackPane,
			String username, String password, ComboBox<String> sitecombo, String patientNameForEdit, Integer PatientId,
			Rectangle patientRectangle, Rectangle uploadRectangle, Rectangle dashboardRectangle,
			Button uploadButtonForedit, Button dashBoardButtonForEdit, Button patientButtonForEdit,
			ImageView uploadImageviewForEdit, ImageView dashboardImageViewForEdit, ImageView patientImageViewForEdit)
			throws FileNotFoundException {

		System.out.println(patientRectangle);
		System.out.println(dashboardRectangle);
		System.out.println(uploadRectangle);
		this.userName = username;
		this.userPassword = password;
		this.uploadImageViewForEdit = uploadImageviewForEdit;
		this.dashBoardImageViewForEdit = dashboardImageViewForEdit;
		this.patientImageViewForEdit = patientImageViewForEdit;
		this.siteComboBoxForUpload = sitecombo;
		double screenWidth = Screen.getPrimary().getBounds().getWidth();
		double screenHeight = Screen.getPrimary().getBounds().getHeight();
		Map<String, String> siteInitialization = initializeSiteComboBox();
		System.out.println("patirnt name for edit is " + patientNameForEdit);
		System.out.println("patirnt id for edit is " + PatientId);
		String selectedSiteName = sitecombo.getValue(); // Get the currently selected value from ComboBox
		/**
		 * Initialize the site ID based on the selected value from ComboBox ,get the
		 * site id from the combobox value
		 */

		selectedSiteIdFromSiteCombo = null;
		for (Map.Entry<String, String> entry : siteInitialization.entrySet()) {
			if (entry.getValue().equals(selectedSiteName)) {
				selectedSiteIdFromSiteCombo = entry.getKey(); // Get the site ID for the selected site name
				break;
			}
		}
		/***
		 * adding the listner for site value change from the site combobox and updating
		 * the record based on site changes
		 */
// intially we set the isChangingProgrammatically variable as false to prevent further action  from the combobox
		isChangingProgrammatically = false;
		sitecombo.setOnAction(event -> {
			/**
			 * if isisChangingProgrammatically== true than prevent further action of
			 * combobox
			 */
			if (isChangingProgrammatically) {
				return; // Prevent action from being triggered programmatically
			}
			String oldValue = (String) sitecombo.getUserData(); // Store old value
			String newValue = sitecombo.getValue(); // Get new value from ComboBox

			if (previouslyClickedLabel == null) {
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Fetch new patient from new site");
				alert.setHeaderText(null);
				alert.setContentText("A new patient list will be fetched for the site - '" + newValue
						+ "'. Do you want to proceed?");
				// Define OK and Cancel buttons
				ButtonType okButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
				ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(okButton, cancelButton); // Add OK and Cancel buttons to alert
				// Show the alert and wait for user's response
				Optional<ButtonType> resultForButtonType = alert.showAndWait();
				if (vboxforaccordianandLabel != null) {
					if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
						vboxforaccordianandLabel.getChildren().remove(0);
					}
				}
				// Handle user selection
				if (resultForButtonType.isPresent() && resultForButtonType.get() == okButton) {
					// OK button was clicked, proceed with fetching the new patient list
					String selectedSiteNameFromCombo = newValue;
					// Find the corresponding site ID for the selected site name
					selectedSiteIdFromSiteCombo = null;
					for (Map.Entry<String, String> entry : siteInitialization.entrySet()) {
						if (entry.getValue().equals(selectedSiteNameFromCombo)) {
							selectedSiteIdFromSiteCombo = entry.getKey();
							break;
						}
					}

					if (selectedSiteIdFromSiteCombo != null) {
						// Update button styles and fetch new patient list
						btnForThisMonth.setStyle("-fx-background-color:white;-fx-font-weight: bold;");
						btnForAllTime
								.setStyle("-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");
						System.out.println("Selected Site ID from ComboBox: " + selectedSiteIdFromSiteCombo);

						// Optionally, you can show some loading indicator here (like a loading gif)
						loadingImageView.setVisible(true);
						loadingImageView.setManaged(true);
						updatePatientListForAllTime(selectedSiteIdFromSiteCombo);
						// Optionally hide the loading indicator after the task is done
						loadingImageView.setVisible(false);
						loadingImageView.setManaged(false);

						// Mark the button as clicked for all time and making it as unclicked for is
						// Today button
						isBtnForAllTimeClicked = true;
						isTodayButtonClicked = false;
					} else {
						System.out.println("No matching site ID found for the selected site name.");
					}
				} else {
					// User clicked Cancel, revert to the previous value ,and prevents to futher
					// action for combobox
					isChangingProgrammatically = true;
					sitecombo.setValue(oldValue);
					isChangingProgrammatically = false;
				}
			} else {
				// previouslyClickedLabel is not null, show different message
				String patientName = previouslyClickedLabel.getText(); // Fetch the patient name dynamically

				// Clear previouslyClickedLabel
				previouslyClickedLabel = null;
				hboxForOrderId.setVisible(false);

				// Show alert with the new message
				Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
				alert.setTitle("Fetch new patient from new site");
				alert.setHeaderText(null);
				alert.setHeight(300);

				alert.setContentText("A new patient list will be fetched for the selected site: " + newValue
						+ ". Any changes made to the details of " + patientName
						+ " will be discarded. Do you want to proceed?");

				// Define OK and Cancel buttons
				ButtonType okButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
				ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
				alert.getButtonTypes().setAll(okButton, cancelButton); // Set OK and Cancel buttons

				Optional<ButtonType> siteSelectionResult = alert.showAndWait();
				if (vboxforaccordianandLabel != null) {
					vboxforaccordianandLabel.getChildren().remove(0);
				}
				if (siteSelectionResult.isPresent() && siteSelectionResult.get() == okButton) {
					// Proceed with fetching the new patient list
					String selectedSiteNameFromCombo = newValue;

					// Find the corresponding site ID for the selected site name
					selectedSiteIdFromSiteCombo = null;
					for (Map.Entry<String, String> entry : siteInitialization.entrySet()) {
						if (entry.getValue().equals(selectedSiteNameFromCombo)) {
							selectedSiteIdFromSiteCombo = entry.getKey();
							break;
						}
					}

					if (selectedSiteIdFromSiteCombo != null) {
						// Update button styles
						btnForThisMonth.setStyle("-fx-background-color:white;-fx-font-weight: bold;");
						btnForAllTime
								.setStyle("-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");
						System.out.println("Selected Site ID from ComboBox: " + selectedSiteIdFromSiteCombo);

						loadingImageView.setVisible(true);
						loadingImageView.setManaged(true);

						updatePatientListForAllTime(selectedSiteIdFromSiteCombo);

						// Optionally hide the loading indicator after the task is done
						loadingImageView.setVisible(false);
						loadingImageView.setManaged(false);

						// Mark the button as clicked
						isBtnForAllTimeClicked = true;
						isTodayButtonClicked = false;

					} else {
						System.out.println("No matching site ID found for the selected site name.");
					}
				} else {
					// User clicked Cancel, revert to the previous value
					isChangingProgrammatically = true;
					sitecombo.setValue(oldValue);
					isChangingProgrammatically = false;
				}
			}

			// Store the new value for future comparisons
			sitecombo.setUserData(newValue);
		});

		uploadStackPane = new StackPane();
		Label queueLabel = new Label("Queue");
		queueLabel.setPrefWidth(screenWidth * .08);
		queueLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");
		ImageView imageViewRefresh = new ImageView(new Image(new FileInputStream("resources/images/Refresh.png")));
		imageViewRefresh.setFitHeight(13);
		imageViewRefresh.setFitWidth(13);
		Button refreshbtn = new Button();
		refreshbtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		refreshbtn.setGraphic(imageViewRefresh);
		// Create a colored circle for the background of the "+"
		Circle refreshCircle = new Circle(10, Color.ORANGE);
		StackPane refreshStackPane = new StackPane();
		// refreshStackPane.setPrefWidth(screenWidth*.08);
		refreshStackPane.getChildren().addAll(refreshCircle, refreshbtn);

		/**
		 * adding action for the refresh button when it is clicke and the specific
		 * button is selected
		 */

		refreshbtn.setOnAction(e -> {

			System.out.println("Refresh button is clicked");
			System.out.println("Is button all time clicked: " + isBtnForAllTimeClicked);
			System.out.println("Is button today clicked: " + isTodayButtonClicked);

			int numberOfNewPatients = 0; // This should be dynamically calculated based on your logic

			// Check if btnForAllTime is selected or true
			if (isBtnForAllTimeClicked) {
				// Update the patient list for all time
//				if (vboxforaccordianandLabel != null) {
//				    if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
//				        // Remove the first child or whichever child you intend to remove
//				        vboxforaccordianandLabel.getChildren().remove(0); // Example: Remove first child
//				    }
//				    
//				    // Set previouslyClickedLabel to null after removing
//				    if (previouslyClickedLabel != null) {
//				        previouslyClickedLabel = null;
//				    }
//				}
				patientDetailsPane.setVisible(false);
				uploadTitledPane.setVisible(false);
				medicalDetailsPane.setVisible(false);

				numberOfNewPatients = updatePatientListForAllTime(selectedSiteIdFromSiteCombo);
				System.out.println("Update patient list is called");
				System.out.println("Is all time button clicked: " + isBtnForAllTimeClicked);

				// Show alert based on the number of new patients
				showPatientRefreshAlert(numberOfNewPatients);

				// Check if vboxforaccordianandLabel is not null and has children
//				if (vboxforaccordianandLabel != null) {
//					if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
//						// Remove the first child or clear as needed
//						vboxforaccordianandLabel.getChildren().remove(0);
//					}
//				}
			} else if (isTodayButtonClicked) {
				System.out.println("Refresh button is clicked for today!");
				System.out.println("Is today button clicked: " + isTodayButtonClicked);
				System.out.println("Is all time button clicked: " + isBtnForAllTimeClicked);
//				if (vboxforaccordianandLabel != null) {
//				    if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
//				        // Remove the first child or whichever child you intend to remove
//				        vboxforaccordianandLabel.getChildren().remove(0); // Example: Remove first child
//				    }
//				    
//				    // Set previouslyClickedLabel to null after removing
//				    if (previouslyClickedLabel != null) {
//				        previouslyClickedLabel = null;
//				    }
//				}

				// Update the patient list for today
				numberOfNewPatients = updatePatientListForToday(selectedSiteIdFromSiteCombo);
				System.out.println("Update patient for today list is called");
				System.out.println("Is all time button clicked: " + isBtnForAllTimeClicked);

				// Show alert based on the number of new patients
				showPatientRefreshAlert(numberOfNewPatients);

				// Check if vboxforaccordianandLabel is not null and has children
//				if (vboxforaccordianandLabel != null) {
//					if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
//						// Remove the first child or clear as needed
//						vboxforaccordianandLabel.getChildren().remove(0);
//						
//					}
//				}
			}

			// Set previouslyClickedLabel to null after processing
			if (previouslyClickedLabel != null) {
				previouslyClickedLabel = null;
			}
		});

		// button for adding patient
		Button btnForAddPatient = new Button();
		StackPane plusStackPane = Utilty.addPatientCalled(btnForAddPatient, stage);
		plusStackPane.setPadding(new Insets(0, 0, 0, 115));
		HBox hboxForQueseLaBelAndRefresh = new HBox(queueLabel, refreshStackPane);
		hboxForQueseLaBelAndRefresh.setSpacing(screenWidth * .1);
		scrollPane = new ScrollPane();
		vboxforPateintLabel = new VBox(30);
		vboxforPateintLabel.setStyle("-fx-background-color: white;");
		HBox hboxforsearchTextFieldandImage = new HBox(screenWidth * .10);

		Image searchImageTextBox = new Image(new FileInputStream("resources/images/search.png"));
		ImageView searchImageView = new ImageView(searchImageTextBox);
		searchImageView.setFitWidth(10);
		searchImageView.setFitHeight(10);
		Button btnContaingSearch = new Button();
		btnContaingSearch.setGraphic(searchImageView);
		btnContaingSearch.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		TextField searchTextField = new TextField();
		searchTextField.setPromptText(" search ");
		searchTextField.setStyle("-fx-background-color: transparent; -fx-border-width: 0 0 0 0; ");
		hboxforsearchTextFieldandImage.setStyle("-fx-border-radius: 2;" + "-fx-border-color: black;");
		hboxforsearchTextFieldandImage.getChildren().addAll(searchTextField, btnContaingSearch);
		// Set action on the search button
		btnContaingSearch.setOnAction(event -> {
			String searchText = searchTextField.getText();

			if (!searchText.isEmpty()) {
				updatePatientListBasedOnSearch(searchText);
				if (previouslyClickedLabel != null) {
					previouslyClickedLabel = null;
				}

			} else {

				if (isBtnForAllTimeClicked) {

					if (vboxforaccordianandLabel != null) {
						if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
							// Remove the first child or whichever child you intend to remove
							vboxforaccordianandLabel.getChildren().remove(0); // Example: Remove first child
						}

						// Set previouslyClickedLabel to null after removing
						if (previouslyClickedLabel != null) {
							previouslyClickedLabel = null;
						}
					}

					updatePatientListForAllTime(selectedSiteIdFromSiteCombo);

				} else if (isTodayButtonClicked) {
					if (vboxforaccordianandLabel != null) {
						if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
							// Remove the first child or whichever child you intend to remove
							vboxforaccordianandLabel.getChildren().remove(0); // Example: Remove first child
						}

						// Set previouslyClickedLabel to null after removing
						if (previouslyClickedLabel != null) {
							previouslyClickedLabel = null;
						}
					}
					updatePatientListForToday(selectedSiteIdFromSiteCombo);
//					if (vboxforaccordianandLabel != null)
//						if (!vboxforaccordianandLabel.getChildren().isEmpty()) {
//							{
//								vboxforaccordianandLabel.getChildren().remove(0);
//							}
//						}
//					if (previouslyClickedLabel != null) {
//						previouslyClickedLabel = null;
//					}
				}

			}
		});

		/*
		 * textProperty() of the searchTextField. In JavaFX, many properties of UI
		 * elements are observable, meaning you can register listeners to be notified
		 * when their values change. The textProperty() of a TextField represents its
		 * current text content.
		 */
		searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.isEmpty()) {
				// If search text is cleared, restore the original vbox inside the scroll pane
				scrollPane.setContent(vboxforPateintLabel);
			}
		});

		HBox hboxForPatientFiltering = new HBox(10);
		loadingImageView = new ImageView(new Image(new FileInputStream("resources/images/loading_gif.gif")));
		loadingImageView.setFitHeight(20);
		loadingImageView.setFitWidth(20);
		loadingImageView.setVisible(false);
		loadingImageView.setManaged(false);
		btnForThisMonth = new Button("Show Today's");
		btnForThisMonth.setPrefWidth(screenWidth * .08);

		btnForThisMonth.setStyle(
				"-fx-background-color: transparent; -fx-border-width: 0 0 0 0; -fx-border-radius:0; -fx-background-radius: 0;-fx-font-weight:bold;");
		btnForAllTime = new Button("All Patients");
		btnForAllTime.setPrefWidth(screenWidth * .08);
		btnForAllTime.setStyle("-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");
		hboxForPatientFiltering.getChildren().addAll(btnForThisMonth, btnForAllTime);
		// hboxForPatientFiltering.setAlignment(Pos.CENTER);
		// hboxForPatientFiltering.setMaxWidth(200);
		if (!isTodayButtonClicked) {
			btnForThisMonth.setOnAction(e -> {
				// Hide hboxForOrderId if it exists
				if (hboxForOrderId != null) {
					hboxForOrderId.setVisible(false);
				}

				String selectedSite = sitecombo.getValue(); // Get the selected site

				if (previouslyClickedLabel != null) {
					// Show alert if previouslyClickedLabel is not null
					Alert alertIfPatientIsClicked = new Alert(Alert.AlertType.CONFIRMATION);
					alertIfPatientIsClicked.setHeight(300);
					alertIfPatientIsClicked.setTitle("Discard Changes");
					alertIfPatientIsClicked.setHeaderText(null);
					alertIfPatientIsClicked.setContentText("A new patient list will be fetched for the site: '"
							+ selectedSite + "'. " + "Any changes made to the patient '"
							+ previouslyClickedLabel.getText() + "' will be discarded. " + "Do you want to proceed?");

					ButtonType okButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
					ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
					alertIfPatientIsClicked.getButtonTypes().setAll(okButton, cancelButton);

					Optional<ButtonType> resultForPatient = alertIfPatientIsClicked.showAndWait();
					btnForAllTime.setStyle("-fx-background-color: white;-fx-border-radius:0;-fx-font-weight: bold;");
					btnForThisMonth.setStyle("-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");
					if (resultForPatient.isPresent() && resultForPatient.get() == okButton) {
						// OK pressed, proceed with fetching the new patient list
						System.out.println("OK clicked: Fetching new patient list...");
						loadingImageView.setVisible(true);
						loadingImageView.setManaged(true);
						updatePatientListForToday(selectedSiteIdFromSiteCombo);
						// Optionally hide the loading indicator after the task is done
						loadingImageView.setVisible(false);
						loadingImageView.setManaged(false);

						// Update button states
						isBtnForAllTimeClicked = false;
						isTodayButtonClicked = true;
						previouslyClickedLabel = null;

						// Clear VBox children if present
						if (vboxforaccordianandLabel != null && !vboxforaccordianandLabel.getChildren().isEmpty()) {
							vboxforaccordianandLabel.getChildren().remove(0);
						}
					} else {
						// Cancel or close pressed
						System.out.println("Alert was cancelled or closed.");
					}
				} else {
					// Show alert if previouslyClickedLabel is null
					Alert alertForNewList = new Alert(Alert.AlertType.CONFIRMATION);
					alertForNewList.setTitle("Fetch New Patient List");
					alertForNewList.setHeaderText(null);
					alertForNewList.setContentText("A new patient list will be fetched for the site: '" + selectedSite
							+ "'. Do you want to proceed?");

					ButtonType okButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
					ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
					alertForNewList.getButtonTypes().setAll(okButton, cancelButton);
					Optional<ButtonType> resultForAllTime = alertForNewList.showAndWait();

					if (resultForAllTime.isPresent() && resultForAllTime.get() == okButton) {
						// OK pressed, proceed with fetching the new patient list
						System.out.println("OK clicked: Fetching new patient list for selected site...");
						btnForThisMonth
								.setStyle("-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");
						btnForAllTime.setStyle("-fx-background-color: white;-fx-font-weight: bold;");

						// Show loading indicator
						loadingImageView.setVisible(true);
						loadingImageView.setManaged(true);
						updatePatientListForToday(selectedSiteIdFromSiteCombo);

						// Hide loading indicator after the task is done
						loadingImageView.setVisible(false);
						loadingImageView.setManaged(false);

						// Update button states
						isTodayButtonClicked = true;
						isBtnForAllTimeClicked = false;
						System.out.println("Is All-Time button clicked: " + isBtnForAllTimeClicked);

						// Clear VBox children if present
						if (vboxforaccordianandLabel != null && !vboxforaccordianandLabel.getChildren().isEmpty()) {
							vboxforaccordianandLabel.getChildren().remove(0);
						}
					} else {
						// Cancel or close pressed
						System.out.println("Alert was cancelled or closed.");
					}
				}
			});
		}
		if (!isBtnForAllTimeClicked) {

			btnForAllTime.setOnAction(e -> {
				String selectedSite = sitecombo.getValue();
				// Ensure it's not a programmatic click and the button hasn't been clicked yet
				if (!isProgrammaticClick && !isBtnForAllTimeClicked) {
					// Check if previouslyClickedLabel is not null, and show a specific alert for it
					if (previouslyClickedLabel != null) {
						// Alert for previouslyClickedLabel
						Alert alertIfPatientIsClicked = new Alert(Alert.AlertType.CONFIRMATION);
						alertIfPatientIsClicked.setTitle("Discard Changes");
						alertIfPatientIsClicked.setHeaderText(null);
						alertIfPatientIsClicked.setHeight(300);
						alertIfPatientIsClicked
								.setContentText("A new patient list will be fetched for the site: '" + selectedSite
										+ "'. " + "Any changes made to the patient '" + previouslyClickedLabel.getText()
										+ "' will be discarded. " + "Do you want to proceed?");

						ButtonType okButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
						ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
						alertIfPatientIsClicked.getButtonTypes().setAll(okButton, cancelButton);

						Optional<ButtonType> resultForPatient = alertIfPatientIsClicked.showAndWait();

						if (resultForPatient.isPresent() && resultForPatient.get() == okButton) {
							// OK clicked, proceed with the action and set previouslyClickedLabel to null
							System.out.println("OK clicked: Fetching new patient list...");

							loadingImageView.setVisible(true);
							loadingImageView.setManaged(true);

							fetchPatientsForAllTime(selectedSiteIdFromSiteCombo);

							// Optionally hide the loading indicator after the task is done
							loadingImageView.setVisible(false);
							loadingImageView.setManaged(false);
							isBtnForAllTimeClicked = true;
							isTodayButtonClicked = false;
							btnForAllTime.setStyle(
									"-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");

							btnForThisMonth
									.setStyle("-fx-background-color: white;-fx-border-radius:0;-fx-font-weight: bold;");
							previouslyClickedLabel = null;
						} else {
							// Cancel clicked, do nothing, previouslyClickedLabel remains unchanged
							System.out.println("Alert was cancelled or closed.");
							return; // Exit the method without updating the patient list
						}
					}

					else {

						// Alert when previouslyClickedLabel is null
						Alert alertForNewList = new Alert(Alert.AlertType.CONFIRMATION);
						alertForNewList.setTitle("Fetch New Patient List");
						alertForNewList.setHeaderText(null);

						alertForNewList.setContentText("A new patient list will be fetched for the site: '"
								+ selectedSite + "'. Do you want to proceed?");

						ButtonType okButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
						ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
						alertForNewList.getButtonTypes().setAll(okButton, cancelButton);

						Optional<ButtonType> resultForAllTime = alertForNewList.showAndWait();

						if (resultForAllTime.isPresent() && resultForAllTime.get() == okButton) {
							loadingImageView.setVisible(true);
							loadingImageView.setManaged(true);
							fetchPatientsForAllTime(selectedSiteIdFromSiteCombo);

							// Optionally hide the loading indicator after the task is done
							loadingImageView.setVisible(false);
							loadingImageView.setManaged(false);
							btnForAllTime.setStyle(
									"-fx-background-color: orange;-fx-border-radius:0;-fx-font-weight: bold;");

							btnForThisMonth
									.setStyle("-fx-background-color: white;-fx-border-radius:0;-fx-font-weight: bold;");
							isBtnForAllTimeClicked = true;
							isTodayButtonClicked = false;
						} else {
							// Cancel clicked, do nothing
							System.out.println("Alert was cancelled or closed.");
							return; // Exit the method without updating the patient list
						}
					}
				} else {

				}

				// Clear VBox children if present
				if (vboxforaccordianandLabel != null && !vboxforaccordianandLabel.getChildren().isEmpty()) {
					vboxforaccordianandLabel.getChildren().remove(0);
				}

				// Update the patient list for all time if the button is clicked
				updatePatientListForAllTime(selectedSiteIdFromSiteCombo);
			});

			// we have setting the isProgrammaticClick =true means it is fired programticlly
			// after that fires bu user click
			// At the start of your application, use this to fire the button
			// programmatically:
			isProgrammaticClick = true;
			btnForAllTime.fire();
			isBtnForAllTimeClicked = true;
			isProgrammaticClick = false;
			vboxForMainContent = new VBox(hboxForQueseLaBelAndRefresh, hboxforsearchTextFieldandImage,
					hboxForPatientFiltering, scrollPane, plusStackPane);
			vboxForMainContent.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
					new CornerRadii(5), new BorderWidths(.7))));
			vboxForMainContent.setStyle("-fx-background-color:white");
			vboxForMainContent.setPadding(new Insets(20));
			// vboxForMainContent.setPrefSize(250, 500);

			vboxForMainContent.setPrefSize(screenWidth * .2, screenHeight * .8);
			vboxForMainContent.setSpacing(30);
			uploadStackPane.getChildren().add(vboxForMainContent);
			StackPane.setMargin(vboxForMainContent, new Insets(10));
			vboxforaccordianandLabel = new VBox();
			vboxforaccordianandLabel.setStyle("-fx-background-color:white");
			vboxforaccordianandLabel.setPadding(new Insets(20, 0, 0, 0));
			vboxForMainContent.setStyle("-fx-background-color:white");
			// vboxforaccordianandLabel.setPrefSize(750, 550);
			vboxforaccordianandLabel.setPrefSize(screenWidth * .6, screenHeight * .6);

			Label lablelforSelectingpatient = new Label();
			lablelforSelectingpatient.setPadding(new Insets(250, 0, 0, 300));
			lablelforSelectingpatient.setText("Select a patient from the Queue to Continue");
			vboxforaccordianandLabel.getChildren().add(lablelforSelectingpatient);
			vboxforaccordianandLabel.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID,
					new CornerRadii(5), new BorderWidths(.7))));
			StackPane stackPaneforLabelAndAccordiaon = new StackPane();
			stackPaneforLabelAndAccordiaon.getChildren().add(vboxforaccordianandLabel);
			vboxforaccordianandLabel.setPadding(new Insets(10));
			stackPaneforLabelAndAccordiaon.setPadding(new Insets(10));
			HBox hboxForPatientandAccordian = new HBox(15);
			hboxForPatientandAccordian.getChildren().addAll(uploadStackPane, stackPaneforLabelAndAccordiaon);
			fixedRectangleStackPane.getChildren().clear();
			fixedRectangleStackPane.getChildren().add(hboxForPatientandAccordian);
			if (patientNameForEdit != null && PatientId != null) {
				HBox patientLabelContainer = labelForPatient(patientNameForEdit, String.valueOf(PatientId), result);

				// Change the background color of the HBox to orange
				patientLabelContainer.setStyle("-fx-background-color: orange;");
				Rectangle patientRectangle1 = (Rectangle) patientLabelContainer.getChildren().get(0);
				Label patientLabel = (Label) patientLabelContainer.getChildren().get(1);
				// String[] patientLabel1= patientLabel1.getText().split(" ");
				// Assuming patientLabel1 is a Label object
				String labelText = patientLabel.getText();
				String[] parts = labelText.split(" ");

				// Check if the split results in at least two parts (first name and last name)
				if (parts.length >= 2) {
					// Reverse the order and join with a comma and a space
					String formattedText = parts[1] + ", " + parts[0];
					patientLabel.setText(formattedText);
				}
				scrollToLabel(patientLabel);
				// If you want the click handler to execute immediately
				patientLabel.getOnMouseClicked().handle(new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0,
						MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null));
				uploadRectangle.setFill(Color.ORANGE);
				dashboardRectangle.setFill(Color.TRANSPARENT);
				uploadButtonForedit.setTextFill(Color.ORANGE);
				dashBoardButtonForEdit.setTextFill(Color.GREY);
				patientButtonForEdit.setTextFill(Color.GREY);
				patientRectangle.setFill(Color.TRANSPARENT);

				// Update images and other components
				changeImageforUplaod(uploadImageviewForEdit);
				changeImageforUplaod(dashboardImageViewForEdit);

			}

		}

	}

//	 public void scrollToLabel(Label labelForEdit) 
//	 {
//		 System.out.println("scroll to label method called !!!");
//		 System.out.println("vboxforPateintLabel lable size is "+vboxforPateintLabel.getChildren().size());
//	        for (int i = 0; i < vboxforPateintLabel.getChildren().size(); i++) {
//	            if (vboxforPateintLabel.getChildren().get(i) instanceof HBox) {
//	            	System.out.println("vboxforPateintLabel is  instance of hbox" );
//	                HBox hbox = (HBox) vboxforPateintLabel.getChildren().get(i);
//	                System.out.println("vboxforPateintLabel.getChildren().get(i)"+hbox);
//	                for (Node node : hbox.getChildren()) {
//	                    if (node instanceof Label) {
//	                        Label label = (Label) node;
//	                        if (label.getText().equals(labelForEdit.getText())) {
//	                            // Calculate the position to scroll to
//	                            double position = (double) i / (vboxforPateintLabel.getChildren().size() - 1);
//	                            System.out.println("position to scroll is "+position);
//	                            scrollPane.setVvalue(position);
//	                            System.out.println("scroll pane value is "+position);
//	                           label.setStyle("-fx-text-fill:orange");
//	                            // Set HBox background color to orange
//	                            hbox.setStyle("-fx-background-color: orange;");
//	                            // label.setStyle("-fx-background-color: orange;");  // Optional: highlight the label
//	                            break;
//	                        }
//	                    }
//	                }
//	            }
//	        }
//	    }
//	public void scrollToLabel(Label labelForEdit) {
//		System.out.println("scroll to label method called!!!");
//		System.out.println("vboxforPateintLabel label size is " + vboxforPateintLabel.getChildren().size());
//
//		for (int i = 0; i < vboxforPateintLabel.getChildren().size(); i++) {
//			if (vboxforPateintLabel.getChildren().get(i) instanceof HBox) {
//				HBox hbox = (HBox) vboxforPateintLabel.getChildren().get(i);
//				System.out.println("vboxforPateintLabel.getChildren().get(i): " + hbox);
//
//				for (Node node : hbox.getChildren()) {
//					if (node instanceof Label) {
//						Label label = (Label) node;
//						if (label.getText().equals(labelForEdit.getText())) {
//							// Calculate the position to scroll to
//							double position = (double) i / (vboxforPateintLabel.getChildren().size() - 1);
//							System.out.println("Position to scroll is " + position);
//							scrollPane.setVvalue(position);
//							System.out.println("Scroll pane value is " + position);
//						//	previouslyClickedLabel = labelForEdit;
//
//							// Change colors for the specific HBox iterate over each loop
//							for (Node hboxNode : hbox.getChildren()) {
//								if (hboxNode instanceof Rectangle) {
//									((Rectangle) hboxNode).setFill(javafx.scene.paint.Color.ORANGE); // Set Rectangle
//																										// color to
//																										// orange
//								} else if (hboxNode instanceof Label) {
//									((Label) hboxNode).setStyle("-fx-text-fill: orange;"); // Set Label text color to
//																							// orange
//								}
//							}
//							break;
//						}
//					}
//				}
//			}
//		}
//	}
	// Global variables to keep track of the previously clicked Rectangle and Label
//	private Rectangle previouslyClickedRectangle = null;
//	private Label previouslyClickedLabel = null;

	/**
	 * 
	 * @param name
	 * @return Hox containing rectagle indicator and name label
	 */
	public HBox labelForPatient(String name, String patientId, String status) {
		System.out.println("name for edit is " + name);
		System.out.println("PATIENT ID FOR EDIT IS " + patientId);
		System.out.println("sTATUS IS " + status);
		// Create a label for the patient's name
		Label label = new Label(name);
		// Create a rectangle to indicate the selection
		Rectangle selectionIndicator = new Rectangle(10, 25); // Width 10, Height 25
		selectionIndicator.setFill(Color.TRANSPARENT); // Initially transparent

		// Create an HBox to hold both the label and the rectangle
		HBox labelContainer = new HBox(5); // Spacing of 5 pixels between label and rectangle
		labelContainer.setAlignment(Pos.CENTER_LEFT);
		labelContainer.getChildren().addAll(selectionIndicator, label);

		label.setOnMouseClicked(e -> {
			System.out.println("patirntidfor click is " + patientId);
			currentlyClickedPatientId = patientId;

			if (previouslyClickedLabel != null && !previouslyClickedLabel.equals(label)) {
				if (previouslyClickedRectangleForEdit != null) {
					previouslyClickedRectangleForEdit.setFill(null);
					// previouslyClickedRectangleForEdit.setStyle(null);
				}
				if (previouslyClickedLablforEdit != null) {
					previouslyClickedLablforEdit.setTextFill(Color.BLACK);
					// previouslyClickedLablforEdit.setStyle(null);
				}
//				previouslyClickedLabel.setStyle(null);
				// Fetch current patient details
				Map<HttpsConnectors.RESPONSE, String> response = getPatientDetails(currentlyExpandedPatientId);
				System.out.println("patient response is +" + response);

				if ("200".equals(response.get(HttpsConnectors.RESPONSE.CODE))) {
					String responseBody = response.get(HttpsConnectors.RESPONSE.VALUE);
					JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
					String caseId = jsonObject.get("caseId").getAsString();

					// Compare current form values with the fetched data

					boolean hasChangesPatientform = personalDetaails.checkForChangesInPersonalDetails(
							jsonObject.get("patientName").getAsString(),
							jsonObject.get("medicalRecordNumber").getAsString(),
							jsonObject.get("ethnicity").getAsString(), jsonObject.get("phone").getAsString(),
							jsonObject.get("email").getAsString(), jsonObject.get("gender").getAsString(),
							jsonObject.get("birthMonth").getAsString(), jsonObject.get("birthDay").getAsString(),
							jsonObject.get("birthYear").getAsString());
					Map<RESPONSE, String> medicalResponse = getMedicalRecord(caseId);
					System.out.println("medical respnse selected patient is : " + medicalResponse);
					// if ("200".equals(medicalResponse.get(HttpsConnectors.RESPONSE.CODE))) {
					String responseBodyForMaedicalRecord = medicalResponse.get(HttpsConnectors.RESPONSE.VALUE);
					JsonObject jsonObjectForMedicalRecord = JsonParser.parseString(responseBodyForMaedicalRecord)
							.getAsJsonObject();
					System.out.println("response body for medical reord change is " + responseBodyForMaedicalRecord);
					System.out.println("is changed persinal detrails " + hasChangesPatientform);
					// medicalDetails.checkForMedicalDetailschangeForCurrentPatientClicked(jsonObjectForMedicalRecord.get("pupilDilation").getAsString(),jsonObjectForMedicalRecord.get("yearsWithDiabeties").getAsString(),jsonObjectForMedicalRecord.get("duration").getAsString(),jsonObjectForMedicalRecord.get("reasonRetinalConsult").getAsString(),jsonObjectForMedicalRecord.get("glaucomaHistory").getAsString(),jsonObjectForMedicalRecord.get("lastEyeExam").getAsString(),jsonObjectForMedicalRecord.get("bloodSugarLevelHigh").getAsString(),jsonObjectForMedicalRecord.get("bloodSugarLevelLow").getAsString(),jsonObjectForMedicalRecord.get("bloodSugarLevelAvg").getAsString(),jsonObjectForMedicalRecord.get("triglycerides").getAsString(),jsonObjectForMedicalRecord.get("hemoglobin").getAsString(),jsonObjectForMedicalRecord.get("cholestrol").getAsString(),jsonObjectForMedicalRecord.get("pregnant").getAsString(),jsonObjectForMedicalRecord.get("hypertension").getAsString(),jsonObjectForMedicalRecord.get("bloodDrawMonth").getAsString(),jsonObjectForMedicalRecord.get("bloodDrawYear").getAsString(),jsonObjectForMedicalRecord.get("subjectiveDiabeticControl").getAsString(),jsonObjectForMedicalRecord.get("medications").getAsString(),jsonObjectForMedicalRecord.get("otherHistory").getAsString(),jsonObjectForMedicalRecord.get("visualAcuityRight").getAsString(),jsonObjectForMedicalRecord.get("pinhole").getAsString(),jsonObjectForMedicalRecord.get("withRx").getAsString(),jsonObjectForMedicalRecord.get("iopRight").getAsString(),jsonObjectForMedicalRecord.get("iopLeft").getAsString(),jsonObjectForMedicalRecord.get("reasonRetinalConsult").getAsString());
					// Accessing JSON fields safely
					String pupilDilation = safeGetAsString(jsonObjectForMedicalRecord.get("pupilDilation"));
					String yearsWithDiabeties = safeGetAsString(jsonObjectForMedicalRecord.get("yearsWithDiabeties"));
					String duration = safeGetAsString(jsonObjectForMedicalRecord.get("duration"));
					String reasonRetinalConsult = safeGetAsString(
							jsonObjectForMedicalRecord.get("reasonRetinalConsult"));
					String glaucomaHistory = safeGetAsString(jsonObjectForMedicalRecord.get("glaucomaHistory"));
					String lastEyeExam = safeGetAsString(jsonObjectForMedicalRecord.get("lastEyeExam"));
					String bloodSugarLevelHigh = safeGetAsString(jsonObjectForMedicalRecord.get("bloodSugarLevelHigh"));
					String bloodSugarLevelLow = safeGetAsString(jsonObjectForMedicalRecord.get("bloodSugarLevelLow"));
					String bloodSugarLevelAvg = safeGetAsString(jsonObjectForMedicalRecord.get("bloodSugarLevelAvg"));
					String triglycerides = safeGetAsString(jsonObjectForMedicalRecord.get("triglycerides"));
					String hemoglobin = safeGetAsString(jsonObjectForMedicalRecord.get("hemoglobin"));
					String cholestrol = safeGetAsString(jsonObjectForMedicalRecord.get("cholestrol"));
					String pregnant = safeGetAsString(jsonObjectForMedicalRecord.get("pregnant"));
					String hypertension = safeGetAsString(jsonObjectForMedicalRecord.get("hypertension"));
					String bloodDrawMonth = safeGetAsString(jsonObjectForMedicalRecord.get("bloodDrawMonth"));
					String bloodDrawYear = safeGetAsString(jsonObjectForMedicalRecord.get("bloodDrawYear"));
					String subjectiveDiabeticControl = safeGetAsString(
							jsonObjectForMedicalRecord.get("subjectiveDiabeticControl"));
					String medications = safeGetAsString(jsonObjectForMedicalRecord.get("medications"));
					String otherHistory = safeGetAsString(jsonObjectForMedicalRecord.get("otherHistory"));
					String visualAcuityRight = safeGetAsString(jsonObjectForMedicalRecord.get("visualAcuityRight"));
					String visualAcuityLeft = safeGetAsString(jsonObjectForMedicalRecord.get("visualAcuityLeft"));
					String pinhole = safeGetAsString(jsonObjectForMedicalRecord.get("pinhole"));
					String withRx = safeGetAsString(jsonObjectForMedicalRecord.get("withRx"));
					String iopRight = safeGetAsString(jsonObjectForMedicalRecord.get("iopRight"));
					String iopLeft = safeGetAsString(jsonObjectForMedicalRecord.get("iopLeft"));
					boolean hasChangesMedicalForm = medicalDetails.checkForMedicalDetailschangeForCurrentPatientClicked(
							pupilDilation, yearsWithDiabeties, duration, reasonRetinalConsult, glaucomaHistory,
							lastEyeExam, bloodSugarLevelHigh, bloodSugarLevelLow, bloodSugarLevelAvg, triglycerides,
							hemoglobin, cholestrol, pregnant, hypertension, bloodDrawMonth, bloodDrawYear,
							subjectiveDiabeticControl, medications, otherHistory, visualAcuityRight, pinhole, withRx,
							iopRight, iopLeft, visualAcuityLeft);
					System.out.println("visual aquiity left is " + visualAcuityLeft);
					System.out.println("has changes medical form " + hasChangesMedicalForm);
					if (hasChangesPatientform || hasChangesMedicalForm) {
						Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
						alert.setTitle("Unsaved Changes");
						alert.setHeaderText(null);
						alert.setContentText("A new patient will be selected for the site - '"
								+ siteComboBoxForUpload.getValue() + "'. Any changes made to the details of "
								+ previouslyClickedLabel.getText() + " will be discarded.\nDo you want to proceed?");
						alert.setHeight(300);
						ButtonType proceedButton = new ButtonType("Proceed", ButtonBar.ButtonData.OK_DONE);
						ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
						alert.getButtonTypes().setAll(proceedButton, cancelButton);

						Optional<ButtonType> resultButton = alert.showAndWait();
						if (resultButton.isPresent() && resultButton.get() == cancelButton) {
							return; // If cancelled, exit
						}
					}
				}

			}
			label.setTextFill(Color.ORANGE);
			selectionIndicator.setFill(Color.ORANGE);

			boolean isNewPatient = !patientId.equals(currentlyExpandedPatientId);

			if (isNewPatient) {
				// currentlyClickedPatientId=patientId;

				// Update the label and rectangle for the currently clicked patient
				if (previouslyClickedLabel != null) {
					previouslyClickedLabel.setTextFill(Color.BLACK);
					previouslyClickedRectangle.setFill(Color.TRANSPARENT);
				}
				// if new patient clear the accordiaon
				vboxforaccordianandLabel.getChildren().clear();

				Map<HttpsConnectors.RESPONSE, String> response = getPatientDetails(patientId);
				if ("200".equals(response.get(HttpsConnectors.RESPONSE.CODE))) {
					String responseBody = response.get(HttpsConnectors.RESPONSE.VALUE);
					if (responseBody != null) {
						try {
							JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
							String patientName = jsonObject.get("patientName").getAsString();
							String medicalRecordNumber = jsonObject.get("medicalRecordNumber").getAsString();
							String ethnicity = jsonObject.get("ethnicity").getAsString();
							String phone = jsonObject.get("phone").getAsString();
							String email = jsonObject.get("email").getAsString();
							String gender = jsonObject.get("gender").getAsString();
							String birthMonth = jsonObject.get("birthMonth").getAsString();
							String birthDay = jsonObject.get("birthDay").getAsString();
							String birthYear = jsonObject.get("birthYear").getAsString();
							String caseId = jsonObject.get("caseId").getAsString();
							String orderId = jsonObject.get("orderId").getAsString();

							vboxforaccordianandLabel.getChildren()
									.add(createAccordion(currentlyClickedPatientId, patientName, medicalRecordNumber,
											ethnicity, phone, email, gender, birthDay, birthMonth, birthYear, caseId,
											orderId));

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}

				currentlyExpandedPatientId = patientId;
			}

			previouslyClickedLabel = label;
			previouslyClickedRectangle = selectionIndicator;

			Platform.runLater(() -> {
				if (status != null) {
					if (status.equals("uploadPatient") || status.equals("uploadPatientFromDashBoard")) {
						uploadTitledPane.setExpanded(true);
						patientDetailsPane.setExpanded(false);
					} else if (isNewPatient || !patientDetailsPane.isExpanded()) {
						patientDetailsPane.setExpanded(true);
					}
				} else {
					patientDetailsPane.setExpanded(true);
				}
			});
		});
		return labelContainer;

	}

	/**
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @param gender
	 * @param email
	 * @param phone
	 * @param ethnicity
	 * @param medicalRecordNumber
	 * @param patientName
	 * @param name
	 * @return
	 * @throws FileNotFoundException
	 */

	public Accordion createAccordion(String currentlyClickedPatrientId, String patientName, String medicalRecordNumber,
			String ethnicity, String phone, String email, String gender, String birthDay, String birthMonth,
			String birthYear, String caseId, String orderId) throws FileNotFoundException {
		double screenWidth = Screen.getPrimary().getBounds().getWidth();
		Accordion accordion = new Accordion();
		accordion.setStyle("-fx-background-color:#ffffff !important;");
		Image icon = new Image(new FileInputStream("resources/images/validationImage.png")); // Update the path to your
		validaionimageView = new ImageView(icon);
		validaionimageView.setFitHeight(20); // Set desired height
		validaionimageView.setFitWidth(20); // Set desired width
		Label titleLabel = new Label("Patient Details");
		titleLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;-fx-font-family: 'Arial';");
		Label orderIdLabel = new Label(orderId);
		orderIdLabel.setStyle("-fx-font-weight:bold;");
		Label labelForOrderId = new Label("Order Id:");
		labelForOrderId.setStyle("-fx-font-weight:bold;");
		hboxForOrderId = new HBox();
		hboxForOrderId.setManaged(true);
		hboxForOrderId.setVisible(true);
		hboxForOrderId.getChildren().addAll(labelForOrderId, orderIdLabel);
		HBox hboxContainingOrderIdAndvalidationImage = new HBox();
		hboxContainingOrderIdAndvalidationImage.getChildren().addAll(hboxForOrderId, validaionimageView);
		HBox headerHbox = new HBox(titleLabel, hboxContainingOrderIdAndvalidationImage);
		headerHbox.setSpacing(screenWidth * .32);
		titleLabel.setPrefWidth(screenWidth * .10);
		headerHbox.setPadding(new Insets(7));
		hboxContainingOrderIdAndvalidationImage.setPrefWidth(screenWidth * .10);
		headerHbox.setAlignment(Pos.CENTER_LEFT);
		headerHbox.setStyle("-fx-background-color:#ffffff;");
		validaionimageView.setVisible(false);
		patientDetailsPane = new TitledPane();
		patientDetailsPane.setGraphic(headerHbox); // Set custom header
		patientDetailsPane.setStyle("-fx-background-color: white; -fx-text-fill: black;");
		/*
		 * setting the conetnt for upload pane
		 */
		patientDetailsPane.setContent(createPatientDetailsForm(currentlyClickedPatientId, patientName,
				medicalRecordNumber, ethnicity, phone, email, gender, birthDay, birthMonth, birthYear));
		uploadTitledPane = new TitledPane();
		Label uploadLabel = new Label("Upload");

		headerHbox.setPadding(new Insets(7));

		uploadLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;-fx-font-family: 'Arial';");
		Image iconForUpload = new Image(new FileInputStream("resources/images/validationImage.png"));
		uploadImageView = new ImageView(iconForUpload);
		uploadImageView.setFitHeight(20); // Set desired height
		uploadImageView.setFitWidth(screenWidth*.02); // Set desired width
		
		
		HBox uploadLabelHbOx = new HBox(uploadLabel, uploadImageView);
		uploadLabelHbOx.setSpacing(screenWidth * .4);
		uploadLabel.setPrefWidth(screenWidth * .10);
		uploadLabelHbOx.setPadding(new Insets(7));
		uploadLabelHbOx.setStyle("-fx-background-color:#ffffff;");
		headerHbox.setAlignment(Pos.CENTER_LEFT);
		uploadImageView.setVisible(false);
		uploadTitledPane.setGraphic(uploadLabelHbOx); // Set custom header
		UploadImages uploadImage = new UploadImages();
		
		uploadTitledPane.setContent(uploadImage.createUpload());
//		if(!uploadTitledPane.isExpanded()) {
//			uploadImageView.setVisible(true);
//		}
		
		medicalDetailsPane = new TitledPane();
		Image iconFormedicalRecord = new Image(new FileInputStream("resources/images/validationImage.png"));
		medicalImageView = new ImageView(iconFormedicalRecord);
		medicalImageView.setFitHeight(20); // Set desired height
		medicalImageView.setFitWidth(screenWidth*.02); // Set desired width
		Label medicalDetailLabel = new Label("Medical Details");
		medicalDetailLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12px;-fx-font-family: 'Arial';");
		HBox medicalDetailsHBox = new HBox(medicalDetailLabel, medicalImageView);
		medicalDetailsHBox.setSpacing(screenWidth * .4);
		medicalDetailLabel.setPrefWidth(screenWidth * .10);
		medicalDetailsHBox.setStyle("-fx-background-color:#ffffff;");
		medicalImageView.setVisible(false);
		medicalDetailsHBox.setPadding(new Insets(7));
		medicalDetailsHBox.setAlignment(Pos.CENTER_LEFT);
		medicalDetailsPane.setGraphic(medicalDetailsHBox); // Set custom header
		// gettin gthe medical response
		Map<RESPONSE, String> medicalResponse = getMedicalRecord(caseId);
		System.out.println("redical respnse is " + medicalResponse);
		// if ("200".equals(medicalResponse.get(HttpsConnectors.RESPONSE.CODE))) {
		String responseBody = medicalResponse.get(HttpsConnectors.RESPONSE.VALUE);
		System.out.println("medical response body is:" + responseBody);
		System.out.println(responseBody);
		JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
		String caseId1 = jsonObject.get("caseId").isJsonNull() ? null : jsonObject.get("caseId").getAsString();
		System.out.println("Case ID: " + caseId1);

		/*
		 * Handling null Values: If the element is JsonNull, it assigns null to the
		 * corresponding variable; otherwise, it converts the value to a String using
		 * getAsString().
		 */
		String medications = jsonObject.get("medications").isJsonNull() ? null
				: jsonObject.get("medications").getAsString();
		System.out.println("Medication is " + medications);

		// Safely extract the "hypertension" field
		String hypertension = jsonObject.get("hypertension").isJsonNull() ? null
				: jsonObject.get("hypertension").getAsString();
		System.out.println("Hypertension is " + hypertension);
		// Safely extract the "otherHistory" field
		String otherHistory = jsonObject.get("otherHistory").isJsonNull() ? null
				: jsonObject.get("otherHistory").getAsString();
		System.out.println("Other history is " + otherHistory);

		// Safely extract the "hemoglobin" field
		String hemoglobin = jsonObject.get("hemoglobin").isJsonNull() ? null
				: jsonObject.get("hemoglobin").getAsString();
		System.out.println("Hemoglobin is " + hemoglobin);
		String bloodYearMonth = jsonObject.get("bloodDrawMonth").isJsonNull() ? null
				: jsonObject.get("bloodDrawMonth").getAsString();
		String bloodYear = jsonObject.get("bloodDrawYear").isJsonNull() ? null
				: jsonObject.get("bloodDrawYear").getAsString();
		String cholestrol = jsonObject.get("cholestrol").isJsonNull() ? null
				: jsonObject.get("cholestrol").getAsString();
		String triglycerides = jsonObject.get("triglycerides").isJsonNull() ? null
				: jsonObject.get("triglycerides").getAsString();
		String subjectiveDiabeticControl = jsonObject.get("subjectiveDiabeticControl").isJsonNull() ? null
				: jsonObject.get("subjectiveDiabeticControl").getAsString();

		String yearsWithDiabeties = jsonObject.get("yearsWithDiabeties").isJsonNull() ? null
				: jsonObject.get("yearsWithDiabeties").getAsString();
		String insulinDependent = jsonObject.get("insulinDependent").isJsonNull() ? null
				: jsonObject.get("insulinDependent").getAsString();
		String reasonRetinalConsult = jsonObject.get("reasonRetinalConsult").isJsonNull() ? null
				: jsonObject.get("reasonRetinalConsult").getAsString();
		String duration = jsonObject.get("duration").isJsonNull() ? null : jsonObject.get("duration").getAsString();
		String lastEyeExam = jsonObject.get("lastEyeExam").isJsonNull() ? null
				: jsonObject.get("lastEyeExam").getAsString();
		String glaucomaHistory = jsonObject.get("glaucomaHistory").isJsonNull() ? null
				: jsonObject.get("glaucomaHistory").getAsString();
		String pregnant = jsonObject.get("pregnant").isJsonNull() ? null : jsonObject.get("pregnant").getAsString();
		String visualAcuityRight = jsonObject.get("visualAcuityRight").isJsonNull() ? null
				: jsonObject.get("visualAcuityRight").getAsString();
		String pinhole = jsonObject.get("pinhole").isJsonNull() ? null : jsonObject.get("pinhole").getAsString();

		String withRx = jsonObject.get("withRx").isJsonNull() ? null : jsonObject.get("withRx").getAsString();
		String iopRight = jsonObject.get("iopRight").isJsonNull() ? null : jsonObject.get("iopRight").getAsString();
		String iopLeft = jsonObject.get("iopLeft").isJsonNull() ? null : jsonObject.get("iopLeft").getAsString();
		String pupilDilation = jsonObject.get("pupilDilation").isJsonNull() ? null
				: jsonObject.get("pupilDilation").getAsString();
		System.out.println(pupilDilation);
		String bloodSugarLevelHigh = jsonObject.get("bloodSugarLevelHigh").isJsonNull() ? null
				: jsonObject.get("bloodSugarLevelHigh").getAsString();
		String bloodSugarLevelLow = jsonObject.get("bloodSugarLevelLow").isJsonNull() ? null
				: jsonObject.get("bloodSugarLevelLow").getAsString();
		String bloodSugarLevelAvg = jsonObject.get("bloodSugarLevelAvg").isJsonNull() ? null
				: jsonObject.get("bloodSugarLevelAvg").getAsString();
		System.out.println(pregnant);
		System.out.println(yearsWithDiabeties);
		medicalDetailsPane.setContent(showMedicalDetailsForm(medications, hypertension, otherHistory, hemoglobin,
				bloodYearMonth, bloodYear, cholestrol, triglycerides, pupilDilation, bloodSugarLevelAvg,
				bloodSugarLevelHigh, bloodSugarLevelLow, iopLeft, iopRight, visualAcuityRight, glaucomaHistory,
				reasonRetinalConsult, insulinDependent, yearsWithDiabeties, duration, lastEyeExam,
				subjectiveDiabeticControl, pregnant, pinhole, withRx));
		accordion.getPanes().addAll(patientDetailsPane, uploadTitledPane, medicalDetailsPane);

		for (TitledPane pane : accordion.getPanes()) {
			pane.setStyle("-fx-background-color:#ffffff !important;-fx-background:white;");
			// Set the background color for the content inside each TitledPane
			pane.getContent().setStyle("-fx-background-color:#ffffff !important;-fx-background:white;");
		}

		return accordion;
	}

	/***
	 * creating a form
	 * 
	 * @param birthYear
	 * @param birthMonth
	 * @param birthDay
	 * @param gender
	 * @param email
	 * @param phone
	 * @param ethnicity
	 * @param medicalRecordNumber
	 * @param patientName
	 * 
	 * @return Grid pane
	 * @throws FileNotFoundException
	 */

	private VBox createPatientDetailsForm(String currentlyClickedPatientId, String patientName,
			String medicalRecordNumber, String ethnicity, String phone, String email, String gender, String birthDay,
			String birthMonth, String birthYear) throws FileNotFoundException {
		VBox personalDetailsVBox = new VBox(3);
		personalDetaails = new PersonalDetails();
		GridPane personalGridPane = personalDetaails.personalGridForUpload(currentlyClickedPatientId, patientName,
				medicalRecordNumber, ethnicity, phone, email, gender, birthDay, birthMonth, birthYear);
		System.out.println("currently clicked label id is " + currentlyClickedPatientId);
		// isPersonalDetailsChangedForPatientDetails=
		// personalDetaails.checkForChangesInPersonalDetails(patientName,
		// medicalRecordNumber, ethnicity, phone, email, gender, birthMonth, birthDay,
		// birthYear);

		// -------------- ADDING SUBMIT BUTTON -----------------
		// getPatientDetailsForpatinetChange(null, personalDetaails);

		VBox vboxContinueButton = new VBox();
		Button uploadButton = new Button("CONTINUE");
		vboxContinueButton.getChildren().add(uploadButton);
		vboxContinueButton.setAlignment(Pos.CENTER);
		uploadButton.setPrefSize(550, 30);
		uploadButton.setStyle("-fx-background-color: orange; " + // Set the background color to orange
				"-fx-text-fill: white; " + // Set the text color to white
				"-fx-font-weight: bold; " + // Set the font weight to bold
				" -fx-border-radius: 10px;" + "-fx-background-radius: 10px;" // Set the border radius (adjust the value
																				// as needed)
		);
		// Set up the button action and validation
		setupContinueButton(uploadButton, personalDetaails);
		personalDetailsVBox.getChildren().addAll(personalGridPane, vboxContinueButton);
		return personalDetailsVBox;
	}

	/**
	 * creating medical record form
	 * 
	 * @param hemoglobin
	 * @param otherHistory
	 * @param hypertension
	 * @param medications
	 * @param bloodYear
	 * @param bloodYearMonth
	 * @param yearsWithDiabeties
	 * @param insulinDependent
	 * @param reasonRetinalConsult
	 * @param glaucomaHistory
	 * @param visualAcuityRight
	 * @param iopRight
	 * @param iopLeft
	 * @param bloodSugarLevelLow
	 * @param bloodSugarLevelHigh
	 * @param bloodSugarLevelAvg
	 * @param pupilDilation
	 * @param triglycerides
	 * @param cholestrol
	 * @param lastEyeExam
	 * @param duration
	 * @param pregnant
	 * @param subjectiveDiabeticControl
	 * @param
	 * @param iopRight2
	 * @param withRx
	 * @param pinhole
	 * @return gridpane
	 */
	private ScrollPane showMedicalDetailsForm(String medications, String hypertension, String otherHistory,
			String hemoglobin, String bloodYearMonth, String bloodYear, String cholestrol, String triglycerides,
			String pupilDilation, String bloodSugarLevelAvg, String bloodSugarLevelHigh, String bloodSugarLevelLow,
			String iopLeft, String iopRight, String visualAcuityRight, String glaucomaHistory,
			String reasonRetinalConsult, String insulinDependent, String yearsWithDiabeties, String duration,
			String lastEyeExam, String subjectiveDiabeticControl, String pregnant, String pinhole, String withRx) {

		medicalDetails = new MedicalDetails();
		GridPane v1 = medicalDetails.medicalRecordGRidPane(personalDetaails, medications, hypertension, otherHistory,
				hemoglobin, bloodYearMonth, bloodYear, cholestrol, triglycerides, pupilDilation, bloodSugarLevelAvg,
				bloodSugarLevelHigh, bloodSugarLevelLow, iopLeft, iopRight, visualAcuityRight, glaucomaHistory,
				reasonRetinalConsult, insulinDependent, duration, yearsWithDiabeties, lastEyeExam,
				subjectiveDiabeticControl, pregnant, withRx, pinhole, null);
		VBox medVbox = new VBox();
		Button submitButton = new Button("SUBMIT");
		submitButton.setPrefSize(500, 35);
		submitButton.setStyle("-fx-background-color: orange; " + // Set the background color to orange
				"-fx-text-fill: white; " + // Set the text color to white
				"-fx-font-weight: bold; " + // Set the font weight to bold
				"-fx-border-radius: 10;" + "-fx-background-radius:10;" // Set the border radius (adjust the value as
																		// needed)
		);
		submitButton.setOnAction(e -> {

			boolean medidicalDetilsValid = handleSubmit(medicalDetails);
			if (medidicalDetilsValid) {
				medicalImageView.setVisible(true);
			}
		});
		GridPane.setColumnSpan(submitButton, 2);
		medVbox.getChildren().addAll(v1, submitButton);
		medVbox.setSpacing(5);

		ScrollPane medicalScpane = new ScrollPane(medVbox);

		return medicalScpane;
	}

	/***
	 * method to validate the the medical record
	 * 
	 * @param medicalDetails
	 * @return
	 */
	private boolean handleSubmit(MedicalDetails medicalDetails) {

		if (medicalDetails.validateMedicalField(medicalDetails)) {
			return true;
		}
		return false;

	}

	/***
	 * method to call the validating
	 * 
	 * @param uploadButton
	 * @param personalDetaails
	 */

	public void setupContinueButton(Button uploadButton, PersonalDetails personalDetaails) {
		uploadButton.setOnAction(event -> {
			System.out.println("name value is " + Utilty.getNameTextField());
			System.out.println("mrn value is" + Utilty.getMrnTextField());

			if (personalDetaails.validateFormFields()) {
				validaionimageView.setVisible(true);
				patientDetailsPane.setExpanded(false); // Assuming patientDetailsPane is defined elsewhere
				uploadTitledPane.setExpanded(true); // Assuming uploadTitledPane is defined elsewhere
			}

		});
	}

	/***
	 * method to fetch Patient List for All time based on site id
	 * 
	 * @param siteId
	 * @return
	 */

	public Map<RESPONSE, String> fetchPatientsForAllTime(String siteId)

	{
		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, userPassword);
		String apiUrl = "/uploader/patient/list/" + siteId;
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, apiUrl,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect("all", credString, "text/plain",
				"application/json");
		return response;
	}

	/***
	 * method to fetch pateint list for today case
	 * 
	 * @param siteId
	 * @return
	 */

	public Map<RESPONSE, String> fetchPatientsForToday(String siteId)

	{
		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, userPassword);
		String apiUrl = "/uploader/patient/list/" + siteId;
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, apiUrl,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect("today", credString, "text/plain",
				"application/json");
		return response;
	}

	public ScrollPane getTodayPatientRecord() {

		return scrollPane;

	}

	/**
	 * get patirnt details based on on the patient id
	 * 
	 * @param patientId
	 * @return Map containg response and response body
	 */
	public Map<RESPONSE, String> getPatientDetails(String patientId) {
		System.out.println("getPatientDetails method called");
		System.out.println(patientId);
		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, userPassword);
		System.out.println(selectedSiteIdFromSiteCombo);
		String apiUrl = "/uploader/patient/id/" + selectedSiteIdFromSiteCombo + "/" + patientId;
		System.out.println(patientId);
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, apiUrl,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect("", credString, "text/plain",
				"application/json");
		return response;
	}

	/***
	 * method to updatePatientForAllTime based on site id
	 * 
	 * @param siteId
	 */

	private int updatePatientListForAllTime(String siteId) {
		System.out.println("Selected site ID: " + siteId);
		int oldNoOfPatient = vboxforPateintLabel.getChildren().size();

		vboxforPateintLabel.getChildren().clear();
		// Fetch the patient data based on the selected filter and siteId
		Map<HttpsConnectors.RESPONSE, String> response = fetchPatientsForAllTime(siteId);
		int noOfPatientAdded = 0;
		System.out.println("updated respnse for all time is " + response);
		if (response.get(HttpsConnectors.RESPONSE.CODE).equals("200")) {
			String responseBody = response.get(HttpsConnectors.RESPONSE.VALUE);
			System.out.println(responseBody);
			if (responseBody != null) {
				try {
					// Parse the JSON response
					JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

					// Iterate over the entries in the JSON object
					for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
						String patientId = entry.getKey(); // Get the patient ID
						String patientName = entry.getValue().getAsString(); // Get the patient name
						// Create a label for each patient
						HBox patientLabel = labelForPatient(patientName, patientId, null);

						// Add the patient label to the VBox
						vboxforPateintLabel.getChildren().add(patientLabel);
						noOfPatientAdded++;

					}

					// Update the ScrollPane content
					scrollPane.setContent(vboxforPateintLabel);
					scrollPane.setStyle("-fx-background-color: white; -fx-background: white;");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Response body is null.");
			}
		} else {
			System.out
					.println("Failed to retrieve data. Response Code: " + response.get(HttpsConnectors.RESPONSE.CODE));
		}
		return (noOfPatientAdded - oldNoOfPatient);
	}

	/**
	 * method to get the updated list for today
	 * 
	 * @param siteId
	 */

	private int updatePatientListForToday(String siteId) {
		System.out.println("Selected site for upload: " + siteId);
		int oldNoOfPatientfortodaycase = vboxforPateintLabel.getChildren().size();
		// Clear the existing labels
		vboxforPateintLabel.getChildren().clear();
		int noOfNewPatientAddedForTodayCase = 0;
		// Fetch the patient data based on the selected filter and siteId
		Map<HttpsConnectors.RESPONSE, String> response = fetchPatientsForToday(siteId);
		System.out.println(response);

		if (response.get(HttpsConnectors.RESPONSE.CODE).equals("200")) {
			String responseBody = response.get(HttpsConnectors.RESPONSE.VALUE);
			System.out.println(responseBody);
			if (responseBody != null) {
				try {
					// Parse the JSON response
					JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

					// Iterate over the entries in the JSON object
					for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
						String patientId = entry.getKey(); // Get the patient ID
						String patientName = entry.getValue().getAsString(); // Get the patient name

						// Create a label for each patient
						HBox patientLabel = labelForPatient(patientName, patientId, null);

						// Add the patient label to the VBox
						vboxforPateintLabel.getChildren().add(patientLabel);
						noOfNewPatientAddedForTodayCase++;
					}
					// Update the ScrollPane content
					scrollPane.setContent(vboxforPateintLabel);
					scrollPane.setStyle("-fx-background-color: white; -fx-background: white;");

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				System.out.println("Response body is null.");
			}
		} else {
			System.out
					.println("Failed to retrieve data. Response Code: " + response.get(HttpsConnectors.RESPONSE.CODE));
		}
		return noOfNewPatientAddedForTodayCase - oldNoOfPatientfortodaycase;
	}

	/**
	 * method to get the medical record based on caseId
	 * 
	 * @return Map of response and response body
	 */
	public Map<RESPONSE, String> getMedicalRecord(String caseId) {

		// Construct the API URL with the dynamic siteId
		String apiUrl = "/uploader/patient/case/" + caseId;
		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, userPassword);
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, apiUrl,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect("", credString, "text/plain",
				"application/json");
		return response;
	}

	/**
	 * method to fetch the site based on user name and password provided
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

	/***
	 * method to initialize the sitecombobox
	 * 
	 * @return
	 */

	public Map<String, String> initializeSiteComboBox() {
		Map<HttpsConnectors.RESPONSE, String> response = getSite(userName, userPassword);
		siteMapping = new HashMap<>();
		String responseBody = response.get(HttpsConnectors.RESPONSE.VALUE);
		System.out.println("Response Body: " + responseBody);

		try {
			JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
			for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
				String siteId = entry.getKey(); // Get the site ID
				String siteName = entry.getValue().getAsString(); // Get the site name
				siteMapping.put(siteId, siteName); // Store in siteIdMap
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return siteMapping;
	}

	/***
	 * method to show the alert for refresh button is clicked
	 * 
	 * @param numberOfNewPatients
	 */
	private void showPatientRefreshAlert(int numberOfNewPatients) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Patient List Refreshed");
		alert.setHeaderText(null);

		if (numberOfNewPatients > 0) {
			alert.setContentText("Patient list refreshed. Found " + numberOfNewPatients + " new patients.");
		} else {
			alert.setContentText("Patient list refreshed. No new patients found.");
		}
		alert.showAndWait(); // Show the alert
	}

	/***
	 * method to update List Based On SearchText
	 * 
	 * @param searchText
	 * @return Vbox containg the searched hbox
	 */
	// Variable to store the original content of vboxforPateintLabel
	private List<Node> originalVBoxContent = new ArrayList<>();

	private VBox updatePatientListBasedOnSearch(String searchText) {
		// Check if this is the first time the method is called, store original content
		if (originalVBoxContent.isEmpty()) {
			originalVBoxContent.addAll(vboxforPateintLabel.getChildren());
		}
		System.out.println("updatePatientListBasedOnSearch method is called");

		// Create a temporary list to store the matching HBoxes
		List<HBox> matchingHBoxes = new ArrayList<>();

		// Clean up the search text by trimming and removing unnecessary characters like
		// ,
		String cleanSearchText = searchText.trim().replaceAll("[,\\s]+", "").toLowerCase();
		System.out.println("Cleaned Search text: " + cleanSearchText);

		// Check if the cleaned search text is not empty
		if (!cleanSearchText.isEmpty()) {

			// Iterate through all HBox elements in the original VBox
			for (Node node : vboxforPateintLabel.getChildren()) {
				if (node instanceof HBox) {
					HBox hbox = (HBox) node;

					// Assume that the second child of HBox is the Label
					if (!hbox.getChildren().isEmpty() && hbox.getChildren().get(1) instanceof Label) {
						Label label = (Label) hbox.getChildren().get(1);
						System.out.println("Label text: " + label.getText());
						// Clean up the label text similarly to the search text
						String cleanLabelText = label.getText().trim().replaceAll("[,\\s]+", "").toLowerCase();

						// Perform the comparison
						if (cleanLabelText.contains(cleanSearchText)) {
							// Add matching HBox to the temporary list
							matchingHBoxes.add(hbox);
							System.out.println("Added to results: " + label.getText());
						}
					}
				}
			}

			// Clear the original VBox and add the filtered HBoxes to the results VBox
			vboxforPateintLabel.getChildren().clear();
			vboxforPateintLabel.getChildren().addAll(matchingHBoxes);

		} else {
			// If the search text is empty, restore the original VBox
			// scrollPane.setContent(vboxforPateintLabel);

			// No results found, log message and restore original content
			System.out.println("No matching results found. Restoring original VBox content.");
			// vboxforPateintLabel.getChildren().clear();
			vboxforPateintLabel.getChildren().addAll(originalVBoxContent);

		}

		// Return the filtered VBox
		return vboxforPateintLabel;
	}

	/***
	 * method for changing then replaceing the original image with orangec color
	 * image
	 * 
	 * @param View
	 * @throws FileNotFoundException
	 */
	private void changeImageforUplaod(ImageView View) throws FileNotFoundException {
		if (View == uploadImageViewForEdit) {
			Image orangeImage = new Image(new FileInputStream("resources/images/orange_upload_icon.jfif"));
			// Set the orange image to the ImageView
			uploadImageViewForEdit.setImage(orangeImage);
			uploadImageViewForEdit.setFitHeight(25);
			uploadImageViewForEdit.setFitWidth(25);
			Image originalImage2 = new Image(new FileInputStream("resources/images/patients_icon.png"));
			patientImageViewForEdit.setImage(originalImage2);
			patientImageViewForEdit.setFitHeight(25);
			patientImageViewForEdit.setFitWidth(25);
		} else {
			Image orangeImage = new Image(new FileInputStream("resources/images/orange_upload_icon.jfif"));
			// Set the orange image to the ImageView
			uploadImageViewForEdit.setImage(orangeImage);
			uploadImageViewForEdit.setFitHeight(25);
			uploadImageViewForEdit.setFitWidth(25);
			Image originalImage2 = new Image(new FileInputStream("resources/images/patients_icon.png"));
			dashBoardImageViewForEdit.setImage(originalImage2);
			dashBoardImageViewForEdit.setFitHeight(25);
			dashBoardImageViewForEdit.setFitWidth(25);

		}
	}

	// Helper method to safely get String from JsonElement
	private String safeGetAsString(JsonElement element) {
		return (element != null && !element.isJsonNull()) ? element.getAsString() : "";
	}

	public void scrollToLabel(Label labelForEdit) {
		System.out.println("scroll to label method called!!!");
		System.out.println("vboxforPateintLabel label size is " + vboxforPateintLabel.getChildren().size());

		for (int i = 0; i < vboxforPateintLabel.getChildren().size(); i++) {
			if (vboxforPateintLabel.getChildren().get(i) instanceof HBox) {
				HBox hbox = (HBox) vboxforPateintLabel.getChildren().get(i);
				System.out.println("vboxforPateintLabel.getChildren().get(i): " + hbox);

				for (Node node : hbox.getChildren()) {
					if (node instanceof Label) {
						Label label = (Label) node;
						if (label.getText().equals(labelForEdit.getText())) {
							// Reset previously clicked elements' colors, if any
//	                        if (previouslyClickedRectangle != null) {
//	                            previouslyClickedRectangle.setFill(javafx.scene.paint.Color.TRANSPARENT);
//	                        }
//	                        if (previouslyClickedLabel != null) {
//	                            previouslyClickedLabel.setStyle("-fx-text-fill: black;");
//	                        }

							// Calculate the position to scroll to
							double position = (double) i / (vboxforPateintLabel.getChildren().size() - 1);
							System.out.println("Position to scroll is " + position);
							scrollPane.setVvalue(position);
							System.out.println("Scroll pane value is " + position);

							// Change colors for the specific HBox
							for (Node hboxNode : hbox.getChildren()) {
								if (hboxNode instanceof Rectangle) {
									// ((Rectangle) hboxNode).setFill(javafx.scene.paint.Color.ORANGE); // Set
									// Rectangle color to orange
									previouslyClickedRectangleForEdit = (Rectangle) hboxNode; // Set as previously
																								// clicked
									previouslyClickedRectangleForEdit.setFill(javafx.scene.paint.Color.ORANGE);
								} else if (hboxNode instanceof Label) {
									// previouslyClickedLablforEdit= ((Label) hboxNode).setStyle("-fx-text-fill:
									// orange;"); // Set Label text color to orange
									previouslyClickedLablforEdit = (Label) hboxNode; // Set as previously clicked
									previouslyClickedLablforEdit.setStyle("-fx-text-fill: orange;");
								}
							}
							break;
						}
					}
				}
			}
		}
	}

}
//}