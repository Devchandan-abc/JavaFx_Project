package com.autouploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
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
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Utilty {
	private static TextField diabaticTypeTextfield;
	public static VBox vboxforPregnancy;
	private static VBox vboxMed;
	private static TextField nameTextField;
	private static ComboBox<String> genderComboBox;
	private static TextField emailTextField;
	private static Label nameErrorLabel;
	private static TextField mrnTextField;
	private static Label errorLabelEmail;
	private static HBox skipBox;
	private static HBox errorSkippingDob;
	private static TextField yearTextField;
	private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private static TextField dateTextField;
	private static TextField monthTextField;
	private static TextField reasonForConsultation;
	private static Label pupilDilationErrorLabel = null;
	private static ComboBox<String> pupilDilationComboBox = null;
	private static ComboBox<String> yearWithDiabatesComboBox = null;
	private static HBox hboxContaingerrorLabelAndHyperLink;
	private static Hyperlink dobSkipHyperLink = null;
	private static Hyperlink mrnSkipLink = null;
	// private static boolean dobSkipped;
	// Define skip flags

	private static HBox hboxContainingErrorlabelaAndHyperLink;
	private static CheckBox selfCheckBox;
	private static CheckBox noCheckBox;
	private static CheckBox parentsCheckBox;
	private static CheckBox siblingsCheckBox;
	private static CheckBox notSureCheckBox;
	private static ComboBox<String> insulinTypeComboBox;
	private static Label insulinerror;
	private static HBox hboxContainingLastEyeExamLabelandHyperLink;
	private static ComboBox<String> lastEyeExamComboBox;
	private static Label errorReasonForConsultLabel;
	private static VBox reasonForConsultVBox;
	private static VBox vboxForLastEyeExam;
	private static Label diabeticTypeErrorlbel;
	private static ComboBox<String> diabeticTypeComboBox;
	private static Label lastEyeExamLabel;
	private static VBox vboxForPupilDilation;
	private static VBox vboxForYearOfDiabates;
	private static VBox vboxForGlaucomaHistory;
	private static VBox insulinVBox;
	private static VBox diabaticTypeVBox;
	private static Hyperlink lastEyeExamHyperLink;
	private static Hyperlink yearOfDiabetesHyperLink;
	private static Hyperlink glaucomaHistoryHyperLink;
	private static boolean mrnSkipped;
	private static boolean dobSkipped;
	private static boolean yearOfDiabSkipped;
	private static boolean lastEyeExamSkipped;
	private static boolean glaucomaHistorySkipped;
	private String userName;
	private String password;
	private ComboBox<String> sitecombo;
	private Map<String, String> siteMapping;
	private String selectedSiteIdFromSiteCombo;
	


	public static Label getErrorReasonForConsultLabel() {
		return errorReasonForConsultLabel;
	}

	public static void setErrorReasonForConsultLabel(Label errorReasonForConsultLabel) {
		Utilty.errorReasonForConsultLabel = errorReasonForConsultLabel;
	}

	public static Label getInsulinerror() {
		return insulinerror;
	}

	public static void setInsulinerror(Label insulinerror) {
		Utilty.insulinerror = insulinerror;
	}

	public static Label getPupilDilationErrorLabel() {
		return pupilDilationErrorLabel;
	}

	public static TextField getReasonForConsultation() {
		return reasonForConsultation;
	}

	public static void setReasonForConsultation(TextField reasonForConsultation) {
		Utilty.reasonForConsultation = reasonForConsultation;
	}

	public static ComboBox<String> getInsulinTypeComboBox() {
		return insulinTypeComboBox;
	}

	public static void setInsulinTypeComboBox(ComboBox<String> insulinTypeComboBox) {
		Utilty.insulinTypeComboBox = insulinTypeComboBox;
	}

	public static ComboBox<String> getLastEyeExamComboBox() {
		return lastEyeExamComboBox;
	}

	public static void setLastEyeExamComboBox(ComboBox<String> lastEyeExamComboBox) {
		Utilty.lastEyeExamComboBox = lastEyeExamComboBox;
	}

	public static VBox getReasonForConsultVBox() {
		return reasonForConsultVBox;
	}

	public static void setReasonForConsultVBox(VBox reasonForConsultVBox) {
		Utilty.reasonForConsultVBox = reasonForConsultVBox;
	}

	public static CheckBox getSelfCheckBox() {
		return selfCheckBox;
	}

	public static CheckBox getNoCheckBox() {
		return noCheckBox;
	}

	public static CheckBox getParentsCheckBox() {
		return parentsCheckBox;
	}

	public static CheckBox getSiblingsCheckBox() {
		return siblingsCheckBox;
	}

	public static CheckBox getNotSureCheckBox() {
		return notSureCheckBox;
	}

	public static HBox getHboxContaingerrorLabelAndHyperLink() {
		return hboxContaingerrorLabelAndHyperLink;
	}

	public static HBox getHboxContainingErrorlabelaAndHyperLink() {
		return hboxContainingErrorlabelaAndHyperLink;
	}

	public static ComboBox<String> getPupilDilationComboBox() {
		return pupilDilationComboBox;
	}

	public static void setPupilDilationComboBox(ComboBox<String> pupilDilationComboBox) {
		Utilty.pupilDilationComboBox = pupilDilationComboBox;
	}

	public static TextField getNameTextField() {
		return nameTextField;
	}

	public static TextField getEmailTextField() {
		return emailTextField;
	}

	public static TextField getMrnTextField() {
		return mrnTextField;
	}

	/**
	 * method for addin rthe overlay when dialog appears
	 * 
	 * @param root
	 * @param width
	 * @param height
	 */
	public static void addOverlay(StackPane root, double width, double height) {
		StackPane overlay = new StackPane();
		overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
		overlay.setPrefSize(width, height);
		root.getChildren().add(overlay);
	}
	

	/**
	 * method for removing the overlay color when dialog diappears
	 * 
	 * @param root
	 * @param overlay
	 */
	public static void removeOverlay(StackPane root, Parent overlay) {
		root.getChildren().remove(overlay);
	}

	/*
	 * method for creating the crating the orangestackpane
	 */
	public static StackPane addPatientCalled(Button btn, Stage stage) {
		// Create a colored circle for the background of the "+"
		Circle plusCircle = new Circle(15, Color.ORANGE);
		Label addPatientPlusSymbol = new Label("+");
		btn.setGraphic(addPatientPlusSymbol);
		btn.setStyle("-fx-background-color: white; -fx-border-color: transparent;");
		addPatientPlusSymbol.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: white;");
		StackPane plusStackPane = new StackPane(plusCircle, btn);
		// plusStackPane.setPadding(new Insets(200, 0, 0, 100));

		btn.setOnAction(e -> {
			AddNewPatient addNewPatient = new AddNewPatient();
			try {
				addNewPatient.handleAddPatient(stage);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		return plusStackPane;
	}

	/***
	 * method for loading the property file
	 * 
	 * @param property
	 * @param key
	 * @return
	 */
	public static String loadSelectedValue(Properties property, String key) {
		ensurePropertiesFileExists();

		System.out.println("load selected path value called");

		try {
			FileInputStream input = new FileInputStream("settings.properties");
			System.out.println(input);
			if (input != null) {
				property.load(input);
			}
			System.out.println("load method called");

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return property.getProperty(key, null);

	}

	/***
	 * To preserve the existing properties, you should first load the existing
	 * properties from the file, add the new key-value pair, and then save all the
	 * properties back to the file. Here's how you can modify your
	 * 
	 * @param property
	 * @param value
	 * @param changedValue
	 */
	public static void settingSaving(Properties property, String value, String changedValue) {
		ensurePropertiesFileExists();

		System.out.println("Save setting method called");

		// Load existing properties from the file
		try (FileInputStream input = new FileInputStream("settings.properties")) {
			property.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Set the new property
		property.setProperty(changedValue, value);

		// Save the updated properties back to the file
		try (FileOutputStream output = new FileOutputStream("settings.properties")) {
			/*
			 * The property.store(output, null); line saves all the properties in the
			 * Properties object to the specified file, overwriting the existing content of
			 * the file. The null parameter indicates that no additional comment should be
			 * included at the top of the file.
			 * 
			 */
			property.store(output, "settings savings in property file");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method for checking the settings file is there or not if not than craete it
	 */
	public static void ensurePropertiesFileExists() {
		// TODO Auto-generated method stub
		System.out.println("craetring the setting fikle");
		File propertiesFile = new File("settings.properties");
		if (!propertiesFile.exists()) {
			try {
				System.out.println("file will be craeted");
				propertiesFile.createNewFile();
				System.out.println("system file is created");
				System.out.println("Properties file created at: " + propertiesFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public static GridPane medicalRecordGRidPane() {
		VBox v1 = new VBox(5);
		VBox v2 = new VBox(5);
		vboxMed = new VBox(5);
		HBox h1 = new HBox(5);
		HBox h2 = new HBox(5);
		GridPane medicalrecordGridPane = new GridPane();
		medicalrecordGridPane.setHgap(3);
		medicalrecordGridPane.setVgap(3);
		/// pupil dilation
		vboxForPupilDilation = new VBox();
		Label pupilDilationLabel = new Label("Pupil Dilation"); // Label without asterisk
		Label asteriskLabelForPupilDilation = new Label("*");
		asteriskLabelForPupilDilation.setStyle("-fx-text-fill: red;");
		HBox pupilDilationHBox = new HBox(1);
		pupilDilationHBox.getChildren().addAll(pupilDilationLabel, asteriskLabelForPupilDilation);
		pupilDilationLabel.setPadding(new Insets(0, 0, 0, 7));
		pupilDilationComboBox = new ComboBox<>();
		pupilDilationComboBox.getItems().addAll("-------", "Not Necessary",
				"Declined (please note reason drop were declined)", "1 gtt. tropicamide 0.5 %",
				"1 gtt. tropicamide 1 %", "Othes Dilating Agents (please note dilating agents used)");
		pupilDilationComboBox.setValue("-------");
		pupilDilationComboBox.setPrefSize(240, 5);
		pupilDilationComboBox.setStyle("-fx-background-color: white");
		pupilDilationErrorLabel = new Label("Please Select pupil Dilation");
		pupilDilationErrorLabel.setPadding(new Insets(0, 0, 0, 7));
		pupilDilationErrorLabel.setVisible(false);
		pupilDilationErrorLabel.setManaged(false);
		// Adding the listener to pupilDilationComboBox
		pupilDilationComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!"-------".equals(newValue)) { // If the new value is not "-------"
				pupilDilationErrorLabel.setVisible(false); // Hide the error label
				pupilDilationErrorLabel.setManaged(false); // Exclude it from layout
				vboxForPupilDilation.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}
		});

		vboxForPupilDilation.getChildren().addAll(pupilDilationHBox, pupilDilationComboBox, pupilDilationErrorLabel);
		vboxForPupilDilation.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
///  HAEMOGLOBIN
		VBox vboxForHaemoglobin = new VBox();
		vboxForHaemoglobin.setPrefSize(240, 30);
		vboxForHaemoglobin.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		Label haemoglobinLabel = new Label("Hemoglobin A1C");
		haemoglobinLabel.setPadding(new Insets(0, 0, 0, 7));
		Label errorLabel = new Label();
		HBox hemoglobinErrorLabel = new HBox(2);
		hemoglobinErrorLabel.getChildren().addAll(haemoglobinLabel, errorLabel);
		TextField haemoglobinTextField = new TextField();

		errorLabel.setStyle("-fx-text-fill: red;"); // Set the error message color to red

		haemoglobinTextField.setPromptText("Type Here");
		haemoglobinTextField
				.setStyle("-fx-background-color:white;-fx-border-color:white;-fx-border-radius:0;-fx-border-width:0;");
		vboxForHaemoglobin.getChildren().addAll(hemoglobinErrorLabel, haemoglobinTextField);
		addRealTimeValidation(haemoglobinTextField, errorLabel, vboxForHaemoglobin, true);
		lastEyeExamLabel = new Label("Please select eye exam.To skip,");
		lastEyeExamLabel.setStyle(
				"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 8; -fx-font-weight: bold;");
		lastEyeExamHyperLink = new Hyperlink("click here");
		lastEyeExamHyperLink.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic; -fx-font-size: 8; -fx-font-weight: bold;-fx-underline: true;");
		hboxContainingLastEyeExamLabelandHyperLink = new HBox(1);
		hboxContainingLastEyeExamLabelandHyperLink.setPadding(new Insets(0, 0, 0, 7));
		hboxContainingLastEyeExamLabelandHyperLink.setAlignment(Pos.CENTER_LEFT);
		hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
		hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
		hboxContainingLastEyeExamLabelandHyperLink.getChildren().addAll(lastEyeExamLabel, lastEyeExamHyperLink);

		// last eye exam
		vboxForLastEyeExam = new VBox(5);
		vboxForLastEyeExam.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		Label lastEyeExamLabel = new Label("Last Eye Exam"); // Label without asterisk
		// Add an asterisk in red color
		Label lasteyeexamasteriskLabel = new Label("*");
		lasteyeexamasteriskLabel.setStyle("-fx-text-fill: red;");
		HBox lastEyeExamHBox = new HBox(1);
		lastEyeExamHBox.setPadding(new Insets(0, 0, 0, 7));
		lastEyeExamHBox.getChildren().addAll(lastEyeExamLabel, lasteyeexamasteriskLabel);
		lastEyeExamComboBox = new ComboBox<>();

		/***
		 * Listeener is added for last eye combo box
		 */
		/// listener for medical details section
		lastEyeExamComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!"---Select---".equals(newValue)) {
				// Show the error HBox
				hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
				hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
				vboxForLastEyeExam.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}
			// else {
//		        // Hide the error HBox if the value is not "---Select---"
//		        hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
//		        hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
//		    }

		});
		lastEyeExamComboBox.setStyle("-fx-background-color: white");
//Add items to the ComboBox
		lastEyeExamComboBox.getItems().addAll("---Select---", "Within 9 Month", "9 to 15 Months",
				"15 Months to 2 Years", "2 to 5 Years", "More Than 5 Years", "Never");
		// lastEyeExamComboBox.setPromptText("Select");
		lastEyeExamComboBox.setValue("---Select---");
		lastEyeExamComboBox.setPrefSize(240, 5);
		// lastEyeExamComboBox.setStyle("-fx-background-color: transparent;");
		vboxForLastEyeExam.getChildren().addAll(lastEyeExamHBox, lastEyeExamComboBox,
				hboxContainingLastEyeExamLabelandHyperLink);
		// clickComboBox(lastEyeExamComboBox, "---Select---");
		// vboxForLastEyeExam.setPrefSize(240, 30);

		///// for USUAL BLOOD SUGAR LEVEL
		Label usualBloodSugalrLabel = new Label("Usual Blood Sugar label");
		Label errorLabelUsualBloodSugarLabel = new Label();
		HBox hboxUsualBloodSugarLabel = new HBox(2);
		hboxUsualBloodSugarLabel.getChildren().addAll(usualBloodSugalrLabel, errorLabelUsualBloodSugarLabel);
		TextField highUsualBloodSugar = new TextField();
		highUsualBloodSugar.setPromptText("High");
		highUsualBloodSugar.setPrefWidth(60);
		TextField lowUsualBloodSugar = new TextField();
		lowUsualBloodSugar.setPromptText("Low");
		TextField averageUsualBloodSugar = new TextField();
		lowUsualBloodSugar.setPrefWidth(60);
		averageUsualBloodSugar.setPromptText("Average");
		errorLabelUsualBloodSugarLabel.setVisible(false);
		averageUsualBloodSugar.setPrefWidth(65);
		HBox hboxforUsualBloodSugarlabel = new HBox(2);
		hboxforUsualBloodSugarlabel.getChildren().addAll(highUsualBloodSugar, lowUsualBloodSugar,
				averageUsualBloodSugar);
		VBox vboxUsualBloodSugarLabel = new VBox(3);
		vboxUsualBloodSugarLabel.getChildren().addAll(hboxUsualBloodSugarLabel, hboxforUsualBloodSugarlabel);

		vboxUsualBloodSugarLabel
				.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:5;-fx-border-width:1;");
		vboxUsualBloodSugarLabel.setPadding(new Insets(5));
		addRealTimeValidation(highUsualBloodSugar, errorLabelUsualBloodSugarLabel, vboxUsualBloodSugarLabel, false);
		addRealTimeValidation(averageUsualBloodSugar, errorLabelUsualBloodSugarLabel, vboxUsualBloodSugarLabel, false);
		addRealTimeValidation(lowUsualBloodSugar, errorLabelUsualBloodSugarLabel, vboxUsualBloodSugarLabel, false);

		//// year of diabates
		vboxForYearOfDiabates = new VBox();
		Label yearWithDiabetesLabel = new Label("Year with diabetes"); // Label without asterisk
		// Add an asterisk in red color
		Label asteriskLabelForYearWithDiabetes = new Label("*");
		asteriskLabelForYearWithDiabetes.setStyle("-fx-text-fill: red;");
		HBox yearWithDiabetesHBox = new HBox(1);
		yearWithDiabetesHBox.setPadding(new Insets(0, 0, 0, 7));
		yearWithDiabetesHBox.getChildren().addAll(yearWithDiabetesLabel, asteriskLabelForYearWithDiabetes);
		yearWithDiabatesComboBox = new ComboBox<String>();

		yearWithDiabatesComboBox.getItems().addAll("--Select--", "Not diabetic", "Borderline", "Borderline Diabetic ",
				"1 year or Less", "2 Years", "3 Years", "4 Years", "5 Years", "6-10 Years", "11-15 Years",
				"16-20 Years", "More Than 20 Years");
		yearWithDiabatesComboBox.setValue("--Select--");
		yearWithDiabatesComboBox.setPrefSize(240, 5);
		yearWithDiabatesComboBox.setStyle("-fx-background-color: white");
		Label yearOfDiabErrorLabel = new Label("Please select year.To skip,");
		yearOfDiabErrorLabel.setStyle(
				"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 8; -fx-font-weight: bold;");
		yearOfDiabetesHyperLink = new Hyperlink("click here");
		yearOfDiabetesHyperLink.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic; -fx-font-size: 8; -fx-font-weight: bold;-fx-underline: true;");

		hboxContaingerrorLabelAndHyperLink = new HBox();
		hboxContaingerrorLabelAndHyperLink.setPadding(new Insets(0, 0, 0, 7));
		hboxContaingerrorLabelAndHyperLink.getChildren().addAll(yearOfDiabErrorLabel, yearOfDiabetesHyperLink);
		hboxContaingerrorLabelAndHyperLink.setAlignment(Pos.CENTER_LEFT);
		hboxContaingerrorLabelAndHyperLink.setManaged(false);
		hboxContaingerrorLabelAndHyperLink.setVisible(false);

		vboxForYearOfDiabates.getChildren().addAll(yearWithDiabetesHBox, yearWithDiabatesComboBox,
				hboxContaingerrorLabelAndHyperLink);
		// adding listener for year of diabertes combo box
		yearWithDiabatesComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!"---Select---".equals(newValue)) {
				// Show the error HBox
				hboxContaingerrorLabelAndHyperLink.setVisible(false);
				hboxContaingerrorLabelAndHyperLink.setManaged(false);
				vboxForYearOfDiabates.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}

		});
		reasonForConsultation = new TextField();
		reasonForConsultation.setVisible(false);
		reasonForConsultation.setManaged(false);
		reasonForConsultation.setPromptText("Reason for Consultantion");
		reasonForConsultVBox = new VBox();
		reasonForConsultVBox.setVisible(false);
		reasonForConsultVBox.setManaged(false);
		errorReasonForConsultLabel = new Label("Please select Reason");
		errorReasonForConsultLabel.setPadding(new Insets(0, 0, 0, 7));
		errorReasonForConsultLabel.setVisible(false);
		errorReasonForConsultLabel.setManaged(false);
		reasonForConsultVBox.getChildren().addAll(reasonForConsultation, errorReasonForConsultLabel);
		// validation for this text field
		reasonForConsultation.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.trim().isEmpty()) {
				// Hide error label when text is entered
				errorReasonForConsultLabel.setVisible(false);
				errorReasonForConsultLabel.setManaged(false);
				reasonForConsultation.setStyle("-fx-border-color:black;-fx-border-radius:3;");
			}
		});

		reasonForConsultation.setPrefSize(200, 45);
		vboxForYearOfDiabates.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		vboxForYearOfDiabates.setPrefSize(240, 30);

		// diabetic type vbox
		diabaticTypeVBox = new VBox(5);
		diabaticTypeVBox.setVisible(false);
		diabaticTypeVBox.setManaged(false);
		Label diabeticTypeLabel = new Label("Diabetic Type"); // Label without asterisk
		// Add an asterisk in red color
		Label diabeticasteriskLabel = new Label("*");
		diabeticasteriskLabel.setStyle("-fx-text-fill: red;");
		HBox diabeticTypeHBox = new HBox(1);
		diabeticTypeHBox.setPadding(new Insets(0, 0, 0, 7));
		diabeticTypeHBox.getChildren().addAll(diabeticTypeLabel, diabeticasteriskLabel);
		diabeticTypeComboBox = new ComboBox<>();
		diabeticTypeComboBox.setStyle("-fx-border-width: 0;-fx-border-color:white;-fx-background-color:white;");

		// Add a listener for diabeticTypeComboBox
		diabeticTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("Select diabetic type")) {
				// Hide the error label if a valid option is selected
				diabeticTypeErrorlbel.setVisible(false);
				diabeticTypeErrorlbel.setManaged(false);
				diabaticTypeVBox.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}

		});
		diabeticTypeComboBox.setPrefSize(240, 5);
		// diabaticTypeVBox.setVisible(true);
		diabeticTypeComboBox.getItems().addAll("Type-1", "Type-2", "Other");
		// diabeticTypeComboBox.setPromptText("Select Diabetic Type");
		diabeticTypeComboBox.setValue("Select diabetic type");
		diabeticTypeErrorlbel = new Label("Please select diabetic type");
		diabeticTypeErrorlbel.setPadding(new Insets(0, 0, 0, 7));
		diabeticTypeErrorlbel.setManaged(false);
		diabeticTypeErrorlbel.setVisible(false);
		diabeticTypeErrorlbel.setStyle(
				"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		diabaticTypeVBox.getChildren().addAll(diabeticTypeHBox, diabeticTypeComboBox, diabeticTypeErrorlbel);
		diabaticTypeVBox
				.setStyle(" -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;-fx-border-radius: 5;");
		// diabeticGridPane.add(diabaticTypeVBox, 1, 1); // Add StackPane for secondary
		// controls
		diabeticTypeComboBox.setOnAction(e -> {

			if (diabeticTypeComboBox.getValue().equals("Other")) {
				diabaticTypeTextfield.setVisible(true);
				diabaticTypeTextfield.setManaged(true);
			} else {
				diabaticTypeTextfield.setVisible(false);
			}

		});

		insulinVBox = new VBox(5);
		insulinVBox.setVisible(false);
		insulinVBox.setManaged(false);
		Label insulinTypelabel = new Label("Insulin Duration");
		// Add an asterisk in red color
		Label asteriskLabelForInsulinDuration = new Label("*");
		asteriskLabelForInsulinDuration.setStyle("-fx-text-fill: red;");
		HBox insulinDurationHBox = new HBox(1);
		insulinDurationHBox.getChildren().addAll(insulinTypelabel, asteriskLabelForInsulinDuration);
		insulinDurationHBox.setPadding(new Insets(0, 0, 0, 7));

		insulinerror = new Label("Please select insulin duration");
		insulinerror.setVisible(false);
		insulinerror.setManaged(false);

		insulinTypeComboBox = new ComboBox<>();
		insulinTypeComboBox.setValue("---Select---");
		insulinTypeComboBox.setStyle("-fx-border-width: 0;-fx-border-color:white;-fx-background-color: white;");
		insulinTypeComboBox.setPrefSize(240, 5);

		insulinTypeComboBox.getItems().addAll("---Select---", "1 year or Less", "2 Years", "3 Years", "4 Years",
				"5 Years", "6-10 Years", "11-15 Years", "16-20 Years", "More Than 20 Years");
		// clickComboBox(insulinTypeComboBox, "---Select---");
		insulinVBox.getChildren().addAll(insulinDurationHBox, insulinTypeComboBox, insulinerror);
		// Add a listener for insulinTypeComboBox
		insulinTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("---Select---")) {
				// Hide the error label if the value is valid (not "---Select---")
				insulinerror.setVisible(false);
				insulinerror.setManaged(false);
				insulinVBox.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}

		});
		yearWithDiabatesComboBox.setValue("---Select---");
		insulinVBox
				.setStyle(" -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;-fx-border-radius: 5;");
		diabaticTypeTextfield = new TextField();
		// diabaticTypeTextfield.setPadding(new Insets(50,0,0,0));
		diabaticTypeTextfield.setPrefSize(190, 50);
		diabaticTypeTextfield.setVisible(false);
		diabaticTypeTextfield.setPromptText("Please Mention Type");
		diabaticTypeTextfield.setManaged(false);
		diabaticTypeTextfield
				.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;-fx-border-radius: 5;");
		yearWithDiabatesComboBox.setOnAction(e -> {
			String selectedValue = yearWithDiabatesComboBox.getValue();

			if (selectedValue.equals("Not diabetic")) {
				insulinVBox.setVisible(false);
				insulinVBox.setManaged(false); // Does not take up space when hidden
				diabaticTypeVBox.setVisible(false);
				diabaticTypeVBox.setManaged(false); // Does not take up space when hidden
				reasonForConsultation.setVisible(true);
				reasonForConsultation.setManaged(true);
				reasonForConsultVBox.setVisible(true);
				reasonForConsultVBox.setManaged(true);
				diabaticTypeTextfield.setVisible(false);
				diabaticTypeTextfield.setManaged(false);
			} else if (selectedValue.equals("--Select--")) {
				insulinVBox.setVisible(false);
				insulinVBox.setManaged(false); // Does not take up space when hidden
				diabaticTypeVBox.setVisible(false);
				diabaticTypeVBox.setManaged(false); // Does not take up space when hidden
				diabaticTypeTextfield.setVisible(false);
				// reasonForConsultation.setVisible(false);
				// reasonForConsultation.setManaged(false);
				reasonForConsultVBox.setVisible(false);
				reasonForConsultVBox.setManaged(false);
				diabaticTypeTextfield.setManaged(false);
			} else {
				insulinVBox.setVisible(true);
				insulinVBox.setManaged(true); // Takes up space when visible
				diabaticTypeVBox.setVisible(true);
				diabaticTypeVBox.setManaged(true); // Takes up space when visible
				reasonForConsultVBox.setVisible(false);
				reasonForConsultVBox.setManaged(false);
			}
		});
///// CHOLESTORAL

		VBox vboxCholerstrol = new VBox();
		vboxCholerstrol.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

//Create a Label for "Haemoglobin"
		Label cholestaorolLabel = new Label("Cholesterol");
		cholestaorolLabel.setPadding(new Insets(0, 0, 0, 7));
		Label cholestorolLabel = new Label();

		HBox cholestaorolErrorLabel = new HBox(2);
		cholestaorolErrorLabel.getChildren().addAll(cholestaorolLabel, cholestorolLabel);
//Create a TextField with a placeholder text
		TextField cholestorolTextField = new TextField();
		cholestorolTextField.setPromptText("Type Here");

		cholestorolTextField
				.setStyle("-fx-background-color:white;-fx-border-color:white;-fx-border-radius:0;-fx-border-width:0;");
		vboxCholerstrol.getChildren().addAll(cholestaorolErrorLabel, cholestorolTextField);
		addRealTimeValidation(cholestorolTextField, cholestorolLabel, vboxCholerstrol, false);

		VBox vboxtrigy = new VBox();
		vboxtrigy.setStyle(
				"-fx-background-color: white;-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		Label trigyLabel = new Label("Triglycerides");
		Label trigyerrorLabel = new Label();
		HBox hboxTriglyError = new HBox(2);
		hboxTriglyError.getChildren().addAll(trigyLabel, trigyerrorLabel);
		trigyLabel.setPadding(new Insets(0, 0, 0, 7));

		TextField trigyTextField = new TextField();
		trigyTextField.setPromptText("Type Here");

		vboxtrigy.getChildren().addAll(hboxTriglyError, trigyTextField);

		trigyTextField.setStyle(
				"-fx-background-color:white;-fx-border-color:transparent;-fx-border-radius:5;-fx-border-width:1;");

		addRealTimeValidation(trigyTextField, trigyerrorLabel, vboxtrigy, false);

		vboxForGlaucomaHistory = new VBox(5);

		// Create CheckBoxes
		noCheckBox = new CheckBox("No");

		selfCheckBox = new CheckBox("Self");
		noCheckBox.setPadding(new Insets(0, 0, 0, 27));
		parentsCheckBox = new CheckBox("Parents");
		parentsCheckBox.setPadding(new Insets(0, 0, 0, 27));
		siblingsCheckBox = new CheckBox("Siblings");
		notSureCheckBox = new CheckBox("Not Sure");
		// Event handler for the "No" checkbox
		noCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue) {
				// Disable all other checkboxes if "No" is selected
				selfCheckBox.setDisable(true);
				selfCheckBox.setSelected(false);

				parentsCheckBox.setDisable(true);
				parentsCheckBox.setSelected(false);
				siblingsCheckBox.setDisable(true);
				siblingsCheckBox.setSelected(false);
				notSureCheckBox.setDisable(true);
				notSureCheckBox.setSelected(false);
			} else {
				// Enable all other checkboxes if "No" is unselected
				selfCheckBox.setDisable(false);
				parentsCheckBox.setDisable(false);
				siblingsCheckBox.setDisable(false);
				notSureCheckBox.setDisable(false);
			}
		});

		// Create HBoxes for the lines with spacing and padding
		HBox firstLine = new HBox(20); // Adding 10px spacing between checkboxes in the first line
		firstLine.setPadding(new Insets(0, 0, 0, 7)); // Adding padding around the first line
		HBox secondLine = new HBox(20); // Adding 10px spacing between checkboxes in the second line
		secondLine.setPadding(new Insets(0, 0, 0, 7)); // Adding padding around the second line

		// Add CheckBoxes to the HBoxes
		firstLine.getChildren().addAll(selfCheckBox, noCheckBox, parentsCheckBox);
		secondLine.getChildren().addAll(notSureCheckBox, siblingsCheckBox);

		// Create a VBox to hold the HBoxes with spacing between lines
		VBox checkBoxesVBox = new VBox(2); // Adding 10px spacing between lines
		checkBoxesVBox.getChildren().addAll(firstLine, secondLine);
		Label glaucomaHistoryLabel = new Label("Glaucoma History"); // Label without asterisk
		// Add an asterisk in red color
		Label glaucomaHistoryAsteriskLabel = new Label("*");
		glaucomaHistoryAsteriskLabel.setStyle("-fx-text-fill: red;");
		HBox glaucomaHistoryHBox = new HBox(1);
		glaucomaHistoryHBox.setPadding(new Insets(0, 0, 0, 7));
		glaucomaHistoryHBox.getChildren().addAll(glaucomaHistoryLabel, glaucomaHistoryAsteriskLabel);
		Label errorglaucomaHistory = new Label("Please select Glaucoma.To skip,");
		errorglaucomaHistory.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic;-fx-text-fill:red; -fx-font-size: 8; -fx-font-weight: bold;");
		glaucomaHistoryHyperLink = new Hyperlink("click here");
		glaucomaHistoryHyperLink.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic; -fx-font-size: 8; -fx-font-weight: bold;-fx-underline: true;");
		// glaucomaHistoryHyperLink.setStyle("-fx-underline:true");

		hboxContainingErrorlabelaAndHyperLink = new HBox();
		hboxContainingErrorlabelaAndHyperLink.setPadding(new Insets(0, 0, 0, 7));

		hboxContainingErrorlabelaAndHyperLink.setAlignment(Pos.CENTER_LEFT);
		hboxContainingErrorlabelaAndHyperLink.getChildren().addAll(errorglaucomaHistory, glaucomaHistoryHyperLink);

		// Add Label and CheckBoxes VBox to the main VBox
		vboxForGlaucomaHistory.getChildren().addAll(glaucomaHistoryHBox, checkBoxesVBox,
				hboxContainingErrorlabelaAndHyperLink);

		hboxContainingErrorlabelaAndHyperLink.setVisible(false);
		hboxContainingErrorlabelaAndHyperLink.setManaged(false);
		vboxForGlaucomaHistory.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		// Adding a listener for the checkboxes
		ChangeListener<Boolean> checkBoxListener = (observable, oldValue, newValue) -> {
			if (anyCheckBoxesSelected(noCheckBox, selfCheckBox, parentsCheckBox, siblingsCheckBox, notSureCheckBox)) {
				// Hide the error HBox if any checkbox is selected
				hboxContainingErrorlabelaAndHyperLink.setVisible(false);
				hboxContainingErrorlabelaAndHyperLink.setManaged(false);
				vboxForGlaucomaHistory.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}
		};

		// Attach listeners to each checkbox
		noCheckBox.selectedProperty().addListener(checkBoxListener);
		selfCheckBox.selectedProperty().addListener(checkBoxListener);
		parentsCheckBox.selectedProperty().addListener(checkBoxListener);
		siblingsCheckBox.selectedProperty().addListener(checkBoxListener);
		notSureCheckBox.selectedProperty().addListener(checkBoxListener);
		/*
		 * Pregnancy Field
		 */
		Label pregnantLabel = new Label("Pregnant Now/Within 6 Months");
		RadioButton unKnownButton = new RadioButton("UnKnown");
		RadioButton yesButton = new RadioButton("Yes");
		RadioButton noButton = new RadioButton("No");
		// Create a ToggleGroup to make the RadioButtons mutually exclusive
		ToggleGroup toggleGroup = new ToggleGroup();
		yesButton.setToggleGroup(toggleGroup);
		noButton.setToggleGroup(toggleGroup);
		unKnownButton.setToggleGroup(toggleGroup);
		HBox hboxForpregnancy = new HBox(5);
		hboxForpregnancy.getChildren().addAll(unKnownButton, yesButton, noButton);
		// Create a VBox and add the Label and RadioButtons to it
		vboxforPregnancy = new VBox(5); // 10 is the spacing between elements
		vboxforPregnancy.setVisible(false);
		vboxforPregnancy.setPadding(new Insets(2));
		vboxforPregnancy.getChildren().addAll(pregnantLabel, hboxForpregnancy);
		vboxforPregnancy.setPrefSize(190, 50);
		vboxforPregnancy.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		/*
		 * HyperTension
		 */

		VBox vboxForHyperTension = new VBox();
		Label hyperTensionLabel = new Label("Hypertension");
		hyperTensionLabel.setPadding(new Insets(0, 0, 0, 7));

		ComboBox<String> hyperTensionComboBox = new ComboBox<>();
		hyperTensionComboBox.setPrefWidth(240);
		hyperTensionComboBox.getItems().addAll("---Select---", "No", "Controlled", "Uncontrolled", "Unknown");

		hyperTensionComboBox.setValue("---Select---");
		hyperTensionComboBox.setStyle("-fx-background-color: white;");
		vboxForHyperTension.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		vboxForHyperTension.getChildren().addAll(hyperTensionLabel, hyperTensionComboBox);
		VBox vboxmedication = new VBox();
		Label medicationLabel = new Label("Medications");
		medicationLabel.setPadding(new Insets(0, 0, 0, 7));
		// Create a TextArea with placeholder text
		TextArea medicationTextArea = new TextArea();
		medicationTextArea.setPrefWidth(240);
		medicationTextArea.setPrefHeight(60);
		medicationTextArea.setPromptText("Type Here");
		medicationTextArea.setWrapText(true); // Ensure text wraps to the next line
		medicationTextArea.setStyle("-fx-background-color:white; -fx-border-color: transparent;");

		vboxmedication.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		vboxmedication.getChildren().addAll(medicationLabel, medicationTextArea);
////SUBJECTIVE DIABATIC CONTROL
		VBox vboxForSubjectiveDiabaticControl = new VBox();
		Label subjectiveDiabaticControllLabel = new Label("Subjective Diabetic Control");
		subjectiveDiabaticControllLabel.setPadding(new Insets(0, 0, 0, 7));
		ComboBox<String> subjectiveDiabticControlComboBox = new ComboBox<>();
		subjectiveDiabticControlComboBox.setValue("---Select---");
		subjectiveDiabticControlComboBox.setStyle("-fx-background-color: white;");
//Add items to the ComboBox
		subjectiveDiabticControlComboBox.getItems().addAll("---Select---", "Excellent", "Good", "Moderate", "Fair",
				"Poor");
		subjectiveDiabticControlComboBox.setPrefSize(240, 5);
		subjectiveDiabticControlComboBox.setStyle("-fx-background-color: white;");

		vboxForSubjectiveDiabaticControl.getChildren().addAll(subjectiveDiabaticControllLabel,
				subjectiveDiabticControlComboBox);

		vboxForSubjectiveDiabaticControl.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		/*
		 * most recent blood test
		 */
		Label mostRecentLabel = new Label("Most Recent Blood Test");
		ComboBox<String> yearMostRecentBloodTest = new ComboBox<>();
		yearMostRecentBloodTest.getItems().addAll("2021", "2022", "2023", "2024");
		ComboBox<String> monthRecentBloodTest = new ComboBox<>();
		monthRecentBloodTest.getItems().addAll("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
				"Nov", "Dec");
		HBox hboxForMostRecentBoodTest = new HBox(5);
		hboxForMostRecentBoodTest.getChildren().addAll(yearMostRecentBloodTest, monthRecentBloodTest);
		VBox vboxForMostRecentBloodTest = new VBox(5);
		vboxForMostRecentBloodTest.setPadding(new Insets(2));
		vboxForMostRecentBloodTest.getChildren().addAll(mostRecentLabel, hboxForMostRecentBoodTest);
		vboxForMostRecentBloodTest.setStyle(
				"-fx-background-color: white; -fx-border-color: black; -fx-border-radius: 5; -fx-border-width: 1; -fx-text-fill: black;");

		/*
		 * other patient history or information
		 */
		VBox otherInformationVbox = new VBox();
		Label otherInformationLabel = new Label(
				"Other  history Or Information including symptoms, onset, and duration");
		otherInformationLabel.setPadding(new Insets(0, 0, 0, 0));
		TextArea otherHistoryTextField = new TextArea();
		otherHistoryTextField.setPrefWidth(350);
		otherHistoryTextField.setPrefHeight(75);
		otherHistoryTextField.setPromptText("Type Here");
		otherHistoryTextField.setWrapText(true);
		otherHistoryTextField.setStyle("-fx-background-color:white;-fx-border-color:white;-fx-border-radius: 5;");
		otherInformationVbox.getChildren().addAll(otherInformationLabel, otherHistoryTextField);
		otherInformationVbox.setStyle("-fx-border-color:black;-fx-border-radius:5;");

		/*
		 * IOP
		 */

		Label ioplabel = new Label("IOP");
		Label iopErrorLabel = new Label();
		HBox hboxioperror = new HBox(2);
		hboxioperror.getChildren().addAll(ioplabel, iopErrorLabel);
		ioplabel.setStyle("-fx-font-weight: bold;");
		Label rightEyeIopLabel = new Label("Right Eye");
		TextField rightIopTextField = new TextField();
		rightIopTextField.setPrefWidth(40);
		// Error label for both text fields

		iopErrorLabel.setStyle("-fx-text-fill: red;");
		iopErrorLabel.setVisible(false); // Initially hidden
		Label leftEyeIop = new Label("Left Eye");
		leftEyeIop.setPadding(new Insets(0, 9, 0, 0));
		TextField leftEyeTextField = new TextField();
		leftEyeTextField.setPrefWidth(40);
		HBox hboxForIop = new HBox(8);
		hboxForIop.getChildren().addAll(rightEyeIopLabel, rightIopTextField);
		HBox leftEyeIopHbox = new HBox(8);
		leftEyeIopHbox.getChildren().addAll(leftEyeIop, leftEyeTextField);
		VBox vboxForIop = new VBox(8);
		vboxForIop.getChildren().addAll(hboxioperror, hboxForIop, leftEyeIopHbox);
		addRealTimeValidation(rightIopTextField, iopErrorLabel, vboxForIop, false);
		addRealTimeValidation(leftEyeTextField, iopErrorLabel, vboxForIop, false);

		vboxForIop.setStyle("-fx-border-color:black;-fx-border-radius:0;-fx-border-radius:5;");
		vboxForIop.setPadding(new Insets(3));

		/*
		 * Visual Aquity
		 */

		Label visualAcuityLabel = new Label("Visual Acuity");
		Label iopErrorLabelVisualAquity = new Label();
		visualAcuityLabel.setStyle("-fx-font-weight:bold;");
		HBox hboxVisualAquity = new HBox(2);
		hboxVisualAquity.getChildren().addAll(visualAcuityLabel, iopErrorLabelVisualAquity);

		Label rightEyeLabel = new Label("Right Eye");
		ComboBox<String> visualAquityRightEyeComboBox = new ComboBox<String>();
		visualAquityRightEyeComboBox.getItems().addAll("20/10", "20/15", "20/20", "20/25", "20/30", "20/40", "20/50",
				"20/60", "20/70", "20/80", "20/100", "20/200", "20/400", "CF 1'", "CF 2'", "CF 3'", "CF 5'", "CF 10'",
				"HM", "LP", "NLP", "6/3", "6/4.8", "6/5", "6/6", "6/7.5", "6/8", "6/9.5", "6/10", "6/12", "6/15",
				"6/19", "6/24", "6/30", "6/38", "6/48", "6/60", "6/190", "-0.30 LogMAR", "-0.20 LogMAR", "-0.10 LogMAR",
				"0.00 LogMAR", "0.10 LogMAR", "0.20 LogMAR", "0.30 LogMAR", "0.40 LogMAR", "0.50 LogMAR", "0.60 LogMAR",
				"0.70 LogMAR", "0.80 LogMAR", "0.90 LogMAR", "1.00 LogMAR", "1.5 LogMAR", "2.0 LogMAR");

		Label leftEyeLabel = new Label("Left Eye");
		leftEyeLabel.setPadding(new Insets(0, 10, 0, 0));
		ComboBox<String> visualAquityLeftEyeComboBox = new ComboBox<String>();
		visualAquityLeftEyeComboBox.getItems().addAll("20/10", "20/15", "20/20", "20/25", "20/30", "20/40", "20/50",
				"20/60", "20/70", "20/80", "20/100", "20/200", "20/400", "CF 1'", "CF 2'", "CF 3'", "CF 5'", "CF 10'",
				"HM", "LP", "NLP", "6/3", "6/4.8", "6/5", "6/6", "6/7.5", "6/8", "6/9.5", "6/10", "6/12", "6/15",
				"6/19", "6/24", "6/30", "6/38", "6/48", "6/60", "6/190", "-0.30 LogMAR", "-0.20 LogMAR", "-0.10 LogMAR",
				"0.00 LogMAR", "0.10 LogMAR", "0.20 LogMAR", "0.30 LogMAR", "0.40 LogMAR", "0.50 LogMAR", "0.60 LogMAR",
				"0.70 LogMAR", "0.80 LogMAR", "0.90 LogMAR", "1.00 LogMAR", "1.5 LogMAR", "2.0 LogMAR");

		HBox righteyeaquity = new HBox(4);
		iopErrorLabelVisualAquity.setStyle("-fx-text-fill: red;");
		iopErrorLabelVisualAquity.setVisible(false); // Initially hidden
		righteyeaquity.getChildren().addAll(rightEyeLabel, visualAquityRightEyeComboBox);

		HBox leftEyeEquity = new HBox(4);
		leftEyeEquity.getChildren().addAll(leftEyeLabel, visualAquityLeftEyeComboBox);

/*
 * pin hole radio button
 */
		Label pinholeLabel = new Label("Pinhole");
		pinholeLabel.setPadding(new Insets(0, 10, 0, 0));
		ToggleGroup pinholeGroup = new ToggleGroup();
		RadioButton pinholeYes = new RadioButton("Yes");
		pinholeYes.setToggleGroup(pinholeGroup);
		RadioButton pinholeNo = new RadioButton("No");
		pinholeNo.setToggleGroup(pinholeGroup);
		HBox pinHoleHBox = new HBox(3);
		pinHoleHBox.getChildren().addAll(pinholeLabel, pinholeYes, pinholeNo);

/*
 * withrx radio button
 */
		Label withRxLabel = new Label("With Rx");
		withRxLabel.setPadding(new Insets(0, 9, 0, 0));
		ToggleGroup withRxGroup = new ToggleGroup();
		RadioButton withRxYes = new RadioButton("Yes");
		withRxYes.setToggleGroup(withRxGroup);
		RadioButton withRxNo = new RadioButton("No");
		withRxNo.setToggleGroup(withRxGroup);
		HBox rxLabel = new HBox(4);
		rxLabel.getChildren().addAll(withRxLabel, withRxYes, withRxNo);
		VBox visualaquityVbox = new VBox(3);
		visualaquityVbox.getChildren().addAll(hboxVisualAquity, righteyeaquity, leftEyeEquity, pinHoleHBox, rxLabel);
		HBox hboxContainingIopAndOtherInformation = new HBox(5);
		hboxContainingIopAndOtherInformation.getChildren().addAll(vboxForIop, otherInformationVbox);
		h1.getChildren().addAll(vboxForPupilDilation, vboxForYearOfDiabates, reasonForConsultVBox);
		h2.getChildren().addAll(insulinVBox, diabaticTypeVBox, diabaticTypeTextfield);
		diabaticTypeTextfield.setVisible(false);
		diabaticTypeTextfield.setManaged(false);
		visualaquityVbox.setPadding(new Insets(4));
		visualaquityVbox.setStyle("-fx-border-color:black;-fx-border-radius:2;");
		v1.getChildren().addAll(h1, h2);
		medicalrecordGridPane.add(v1, 0, 1);
		GridPane.setColumnSpan(v1, 3);
		medicalrecordGridPane.add(vboxForHaemoglobin, 0, 4);
		medicalrecordGridPane.add(vboxForLastEyeExam, 1, 2);
		medicalrecordGridPane.add(vboxUsualBloodSugarLabel, 0, 3);
		medicalrecordGridPane.add(vboxCholerstrol, 1, 4);
		medicalrecordGridPane.add(vboxtrigy, 1, 3);
		medicalrecordGridPane.add(vboxForGlaucomaHistory, 0, 2);
		medicalrecordGridPane.add(vboxforPregnancy, 2, 4);
		medicalrecordGridPane.add(vboxForHyperTension, 0, 5);
		medicalrecordGridPane.add(vboxmedication, 0, 7);
		medicalrecordGridPane.add(vboxForSubjectiveDiabaticControl, 0, 6);
		medicalrecordGridPane.add(visualaquityVbox, 1, 6);
		medicalrecordGridPane.add(vboxForMostRecentBloodTest, 1, 5);
		medicalrecordGridPane.add(hboxContainingIopAndOtherInformation, 0, 8);
		GridPane.setColumnSpan(hboxContainingIopAndOtherInformation, 2);
		GridPane.setRowSpan(visualaquityVbox, 2);
		return medicalrecordGridPane;
	}

	public static ComboBox<String> getYearWithDiabatesComboBox() {
		return yearWithDiabatesComboBox;
	}

	public static void setYearWithDiabatesComboBox(ComboBox<String> yearWithDiabatesComboBox) {
		Utilty.yearWithDiabatesComboBox = yearWithDiabatesComboBox;
	}

	public static GridPane personalGridForUpload(Label label) {
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		Label nameLabel = new Label("NAME"); // Label without asterisk
		Label asteriskLabel = new Label("*");
		asteriskLabel.setStyle("-fx-text-fill: red;");
		HBox nameHBox = new HBox(1);
		nameHBox.getChildren().addAll(nameLabel, asteriskLabel);
		nameHBox.setStyle("-fx-background-color:white;");
		nameTextField = new TextField();
		nameTextField.setPrefWidth(220);
		nameTextField.setPromptText("Type Here");
		// If the label is not null and has text, set the text in the text field
		if (label != null && label.getText() != null && !label.getText().isEmpty()) {
			nameTextField.setText(label.getText());
		} else {
			nameTextField.setText("");
		}

		nameErrorLabel = new Label("Please enter full name");
		nameErrorLabel.setVisible(false);
		nameErrorLabel.setManaged(false);
		nameTextField.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		VBox vboxForNameTextFieldAndErrorLabel = new VBox();
		vboxForNameTextFieldAndErrorLabel.getChildren().addAll(nameTextField, nameErrorLabel);
		HBox hboxForNameAndTextBox = new HBox(5);
		hboxForNameAndTextBox.getChildren().addAll(nameHBox, vboxForNameTextFieldAndErrorLabel);

		gridPane.add(hboxForNameAndTextBox, 0, 0); // Use the HBox containing nameLabel and asteriskLabel
		Label genderLabel = new Label("GENDER");
		genderLabel.setPadding(new Insets(0, 10, 0, 0));
		/*
		 * Gender
		 */
		HBox hboxforGenderLabelAndAstricksymbol = new HBox(1);
		genderComboBox = new ComboBox<>();
		genderComboBox.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		genderComboBox.setValue("Select");
		genderComboBox.getItems().addAll("Select", "Male", "Female", "Others", "Unknown");
		/// setting action for gender combobox
		genderComboBox.setOnAction(e -> {

			if (genderComboBox.getValue().equals("Female")) {
				vboxforPregnancy.setVisible(true);
			} else {
				vboxforPregnancy.setVisible(false);
			}

		});
		genderComboBox.setPrefWidth(240); // Adjust the width as needed
		HBox hboxForGenderAndComboBox = new HBox(5);
		hboxForGenderAndComboBox.getChildren().addAll(hboxforGenderLabelAndAstricksymbol, genderComboBox);
		hboxforGenderLabelAndAstricksymbol.getChildren().addAll(genderLabel);
		HBox hboxFormrnLabelandAstrick = new HBox();
		Label asteriskLabel2 = new Label("*");
		asteriskLabel2.setStyle("-fx-text-fill: red;");
		/*
		 * MRN
		 */
		Label mrnLabel = new Label("MRN");
		hboxFormrnLabelandAstrick.getChildren().addAll(mrnLabel, asteriskLabel2);
		hboxFormrnLabelandAstrick.setPadding(new Insets(0, 8, 0, 0));
		mrnTextField = new TextField();
		mrnTextField.setPrefWidth(220);
		mrnTextField.setPromptText("Type Here");
		mrnTextField.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		Label mrnErrorLabel = new Label("Please enter MRN.");
		mrnErrorLabel.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		Label skipMessage = new Label("To skip,");
		skipMessage.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		mrnSkipLink = new Hyperlink("click here");
		mrnSkipLink.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;-fx-underline: true;");
		skipBox = new HBox(mrnErrorLabel, skipMessage, mrnSkipLink);

		VBox vboxForMrnTextFieldAndErrorLabel = new VBox();
		vboxForMrnTextFieldAndErrorLabel.getChildren().addAll(mrnTextField, skipBox);
		HBox hboxForMrnLabelAndTextBox = new HBox(5);
		hboxForMrnLabelAndTextBox.getChildren().addAll(hboxFormrnLabelandAstrick, vboxForMrnTextFieldAndErrorLabel);
		skipBox.setVisible(false);
		skipBox.setManaged(false);
		skipBox.setAlignment(Pos.CENTER_LEFT); // Aligns the nodes in the HBox
		gridPane.add(hboxForMrnLabelAndTextBox, 0, 1);
		HBox hboxForEthinicityandComboBox = new HBox(5);
		Label ethinicityLabel = new Label("ETHINCITY");
		ComboBox<String> ethinictyComboBox = new ComboBox<>();
		ethinictyComboBox.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		ethinictyComboBox.setValue("Not Specified");
		ethinictyComboBox.setPrefWidth(240);
		ethinictyComboBox.getItems().addAll("Not Specified", "African American/African Descent",
				"Latin American/Hispanic", "Caucasian/White", "Asian/Pacific Islander", "Indian subcontinent origin",
				"Native American/Alaskan Native", "Multi-racial", "Decline To State", "Other");
		hboxForEthinicityandComboBox.getChildren().addAll(ethinicityLabel, ethinictyComboBox);
		gridPane.add(hboxForEthinicityandComboBox, 1, 1);
		gridPane.add(hboxForGenderAndComboBox, 1, 0);
		ethinictyComboBox.setOnMouseClicked(e ->

		{
			if (ethinictyComboBox.getValue().equals("Not Specified")) {
				ethinictyComboBox.setValue("");
				ethinictyComboBox.show();
			}
		});

//	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Label dobLabel = new Label("DOB:");
		dobLabel.setPadding(new Insets(0, 17, 0, 0));
		dateTextField = new TextField();
		dateTextField.setPromptText("day");
		dateTextField.setPrefSize(70, 5);
		monthTextField = new TextField();
		monthTextField.setPromptText("month");
		monthTextField.setPrefSize(70, 5);
//Create a Label for error messages
		Label errorLabelForMonth = new Label("Invalid Month");
		errorLabelForMonth.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;"); // Red
																																	// text
																																	// for
																																	// error
		errorLabelForMonth.setVisible(false);
		errorLabelForMonth.setManaged(false);

// Add real-time validation for monthTextField
		monthTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {
				String monthValueDob = monthTextField.getText();
				if (!monthValueDob.isEmpty()) {
					try {
						int monthValue = Integer.parseInt(monthValueDob);
						if (monthValue > 12 || monthValue < 1) {
							// errorLabel.setText("Invalid Month");
							errorLabelForMonth.setVisible(true);
							errorLabelForMonth.setManaged(true);
							// dayTextField.setDisable(true); // Disable day field

							monthTextField.setStyle("-fx-border-color: red;-fx-border-radius: 3;");
						} else {
							errorLabelForMonth.setVisible(false);
							errorLabelForMonth.setManaged(false);
							// errorLabel.setText(""); // Clear error message
							monthTextField.setStyle(null); // Reset style
						}
					} catch (NumberFormatException e) {
						errorLabelForMonth.setVisible(true);
						errorLabelForMonth.setManaged(true);
						monthTextField.setStyle("-fx-border-color: red;-fx-border-radius: 3;");
					}
				}
			} else {
				errorLabelForMonth.setVisible(false);
				errorLabelForMonth.setManaged(false);
				monthTextField.setStyle(null); // Reset style
			}
		});
		VBox vbox1 = new VBox(); // Spacing between elements
		vbox1.getChildren().addAll(monthTextField, errorLabelForMonth);
		Label yearErrorLabel = new Label("Invalid Year");
		yearErrorLabel.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		yearErrorLabel.setManaged(false);
		yearErrorLabel.setVisible(false);
		yearTextField = new TextField();
		/*
		 * adding validtaion for year text field
		 */
		yearTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) { // Focus lost, trigger validation
				String input = yearTextField.getText().trim();
				// If input is empty, clear any previous error styles and return without
				// validation
				if (input.isEmpty()) {
					yearErrorLabel.setVisible(false);
					yearErrorLabel.setManaged(false);
					yearTextField.setStyle(null); // Clear any error styles
					return;
				}

				try {
					int year = Integer.parseInt(input);

					// Hide error label initially
					yearErrorLabel.setVisible(false);
					yearErrorLabel.setManaged(false);
					yearTextField.setStyle(null); // Clear error styles

					// If the input is a four-digit year, check if it's valid
					if (input.length() == 4) {
						if (year < 1900) {
							yearErrorLabel.setVisible(true);
							yearErrorLabel.setManaged(true);
							yearTextField.setStyle("-fx-border-color: red;");
							return;
						}
					}

					if (input.length() == 2) {
						int currentYear = java.time.Year.now().getValue();
						int currentCentury = currentYear / 100 * 100;
						int inputYear = Integer.parseInt(input);

						// If less than 24, assume it's part of the current century
						if (inputYear <= 24) {
							year = currentCentury + inputYear;
						} else {
							// If greater than 24, assume it's from the previous century
							year = (currentCentury - 100) + inputYear;
						}

						// Ensure the corrected year is not less than 1900
						if (year < 1900) {
							// Show error
							yearErrorLabel.setText("Invlid Year");
							yearErrorLabel.setVisible(true);
							yearErrorLabel.setManaged(true);
							yearTextField.setStyle("-fx-border-color: red; -fx-border-radius:3;");
							return;
						}

						// Set the corrected year back to the text field
						yearTextField.setText(String.valueOf(year));
					}
				} catch (NumberFormatException e) {
					// Handle non-numeric input

					yearErrorLabel.setVisible(true);
					yearErrorLabel.setManaged(true);
					yearTextField.setStyle("-fx-border-color: red;");
				}
			}
		});

		Label dayLabel = new Label("Invalid Day");
		dayLabel.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic;-fx-text-fill:red; -fx-font-size: 10; -fx-font-weight: bold;");
		dayLabel.setManaged(false);
		dayLabel.setVisible(false);
/**
 * adding validation for day text field
 */
		dateTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) {

				String inputValue = dateTextField.getText();
				if (!inputValue.isEmpty()) {
					try {
						int dayValue = Integer.parseInt(inputValue);
						if (dayValue > 31 || dayValue < 1) {
							dayLabel.setManaged(true);
							dayLabel.setVisible(true);
							dateTextField.setStyle("-fx-border-color: red;-fx-border-radius: 3;");
						} else {
							dayLabel.setManaged(false);
							dayLabel.setVisible(false);
							dateTextField.setStyle(null);

						}
					} catch (NumberFormatException e) {
						dayLabel.setManaged(true);
						dayLabel.setVisible(true);
						dateTextField.setStyle("-fx-border-color: red;-fx-border-radius: 3;");

					}
				}
			} else {
				dayLabel.setManaged(false);
				dayLabel.setVisible(false);
				dateTextField.setStyle(null);
			}
		});
		VBox yearVbox = new VBox();
		yearVbox.getChildren().addAll(yearTextField, yearErrorLabel);
		VBox dateVBox = new VBox();
		dateVBox.getChildren().addAll(dateTextField, dayLabel);
		yearTextField.setPromptText("year");
		yearTextField.setPrefSize(70, 5);
		HBox hboxForDob = new HBox(5);
		hboxForDob.getChildren().addAll(vbox1, dateVBox, yearVbox);
		Label dobErrorLabel = new Label("Please enter DOB.");
		dobErrorLabel.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic;-fx-text-fill:red; -fx-font-size: 10; -fx-font-weight: bold;");
		errorSkippingDob = new HBox();
		Label skipLabelDob = new Label("To skip,");
		skipLabelDob.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic;-fx-text-fill:red; -fx-font-size: 10; -fx-font-weight: bold;");
		dobSkipHyperLink = new Hyperlink("click here");
		dobSkipHyperLink.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic;-fx-font-size: 10; -fx-font-weight: bold;-fx-underline: true;");
		errorSkippingDob.getChildren().addAll(dobErrorLabel, skipLabelDob, dobSkipHyperLink);
		errorSkippingDob.setVisible(false);
		errorSkippingDob.setManaged(false);
		VBox dobVBox = new VBox();
		dobVBox.getChildren().addAll(hboxForDob, errorSkippingDob);
		errorSkippingDob.setSpacing(5);
		HBox hboxContainingLabelAnddobVbox = new HBox();
		hboxContainingLabelAnddobVbox.getChildren().addAll(dobLabel, dobVBox);
		gridPane.add(hboxContainingLabelAnddobVbox, 0, 2);
		Label contactLabel = new Label("Contact No");
		TextField contactTextField = new TextField();
		contactTextField.setPrefWidth(240);
		dateTextField.setOnMouseClicked(event -> {
			// Make dateTextField invisible when clicked
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);

		});
		monthTextField.setOnMouseClicked(e -> {
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);

		});
		yearTextField.setOnMouseClicked(e -> {
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);
		});
		contactTextField.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		HBox hboxFoContactField = new HBox(4);
		hboxFoContactField.getChildren().addAll(contactLabel, contactTextField);
		gridPane.add(hboxFoContactField, 1, 2);
		Label emailLabel = new Label("Email");
		emailLabel.setPadding(new Insets(0, 15, 0, 0));
		emailTextField = new TextField();
		emailTextField.setPromptText("enter email");
		emailTextField.setPrefWidth(220);
		emailTextField.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		errorLabelEmail = new Label("Invalid Email");
		errorLabelEmail.setVisible(false);
		errorLabelEmail.setManaged(false);
		VBox vboxForEmailTextFieldAndErrorLabel = new VBox();
		vboxForEmailTextFieldAndErrorLabel.getChildren().addAll(emailTextField, errorLabelEmail);
		HBox hboxContainingEmailLabelAndHbox = new HBox();
		hboxContainingEmailLabelAndHbox.getChildren().addAll(emailLabel, vboxForEmailTextFieldAndErrorLabel);
		CheckBox declineDataSharingCheckBox = new CheckBox(
				"Patient declines to share de-identified\ndata for research and development");
		declineDataSharingCheckBox.setPadding(new Insets(0, 0, 0, 70));
		gridPane.add(hboxContainingEmailLabelAndHbox, 0, 3);
		gridPane.add(declineDataSharingCheckBox, 1, 3);

		return gridPane;
	}
/**
 * adding valudation for the numerical not allowed in combobox
 * @param textField
 * @param errorLabel
 * @param vbox
 * @param allowDecimals
 */

	private static void addRealTimeValidation(TextField textField, Label errorLabel, VBox vbox, boolean allowDecimals) {
		textField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (allowDecimals) {
				// Allow whole numbers and decimals for haemoglobinTextField
				if (!newValue.matches("\\d*(\\.\\d*)?")) {
					showAlert("Invalid Input",
							"Please use the clinical history field to enter any additional information.\nOnly numeric values and decimals are allowed in this field.");
					textField.setText(oldValue); // Revert to the last valid value

					errorLabel.setText("Characters not allowed");
					errorLabel.setStyle("-fx-font-size: 10px; -fx-font-style: italic; -fx-text-fill: red;");
					errorLabel.setVisible(true);
					vbox.setStyle("-fx-border-color: red;-fx-border-radius: 5;");
				} else {
					errorLabel.setVisible(false);
					vbox.setStyle("-fx-border-color: black;-fx-border-radius: 5;-fx-border-width: 1;");
				}
			} else {
				// Allow only whole numbers for other text fields
				if (!newValue.matches("\\d*")) {
					showAlert("Invalid Input",
							"Please use the clinical history field to enter any additional information.\nOnly numeric values are allowed in this field.");
					textField.setText(oldValue); // Revert to the last valid value

					errorLabel.setText("Only numbers allowed");
					errorLabel.setStyle("-fx-font-size: 10px; -fx-font-style: italic; -fx-text-fill: red;");
					errorLabel.setVisible(true);
					vbox.setStyle("-fx-border-color: red;-fx-border-radius: 5;");
				} else {
					errorLabel.setVisible(false);
					vbox.setStyle("-fx-border-color: black;-fx-border-radius: 5;-fx-border-width: 1;");
				}
			}
		});

		// Add focus listener to clear error on focus
		textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue) { // If focused
				errorLabel.setVisible(false); // Hide error label
				vbox.setStyle("-fx-border-color: black;-fx-border-radius: 5;-fx-border-width: 1;"); // Reset border
																									// style
			}
		});
	}

// Method to show an alert
	private static void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeight(200);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	/***
	 * method for validation of personal details
	 * 
	 * @param nameTextField
	 * @param mrnTextField1
	 * @param emailText
	 * @return
	 */
	public static boolean validateFormFields() {

		boolean isValid = true;
		System.out.println(nameTextField);
		System.out.println(mrnTextField);

		/*
		 * validation for name text field
		 */
		// Define regex patterns for valid name formats
		String validPattern = "^(?:[A-Za-z]+(?: [A-Za-z]+)+|[A-Za-z]+(?:,[ ]?[A-Za-z]+)+)$";
		if (nameTextField != null) {
			String text = nameTextField.getText().trim();
			if (text.isEmpty() || !text.matches(validPattern)) {
				nameErrorLabel.setVisible(true);
				nameErrorLabel.setManaged(true);
				nameTextField.setStyle("-fx-border-color: red; -fx-border-radius: 3;");
				nameErrorLabel.setStyle(
						"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
				// Indicate invalid state
				isValid = false;
			} else {
				// Clear error style if valid
				nameTextField.setStyle(null);
				nameErrorLabel.setVisible(false);
				nameErrorLabel.setManaged(false);
			}
		}

		// Action for MRN skip link
		mrnSkipLink.setOnAction(e -> {
			mrnSkipped = true; // Skip MRN validation
			dobSkipped = true; // Skip DOB validation

			skipBox.setVisible(false);
			skipBox.setManaged(false);
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);
			mrnTextField.setStyle(null); // Clear error style
		});

		// Action for DOB skip link
		dobSkipHyperLink.setOnAction(e -> {
			dobSkipped = true; // Skip DOB validation
			mrnSkipped = true; // Skip MRN validation
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);
			skipBox.setVisible(false);
			skipBox.setManaged(false);
			dateTextField.setText("");
			monthTextField.setText("");
			yearTextField.setText("");
		});

		/**
		 * listener is adeed to name text field
		 */
		nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.isEmpty()) {
				// Hide the skipBox and remove the red border when the user starts typing
				if (nameErrorLabel != null) {
					nameErrorLabel.setVisible(false);
				}
				nameTextField.setStyle(null); // Clear error style
			} else {
				// If the field is cleared again, show the skipBox and the red border
				if (nameErrorLabel != null) {
					// skipBox.setVisible(true);
				}
				nameTextField.setStyle(null);
			}
		});

		mrnTextField.setOnMouseClicked(e -> {
			skipBox.setVisible(false);
			skipBox.setManaged(false);
		});

		// Validate MRN only if it's not skipped
		if (!mrnSkipped) {
			if (mrnTextField != null && mrnTextField.getText().isEmpty()) {
				skipBox.setVisible(true);
				skipBox.setManaged(true);
				// mrnTextField1.setStyle("-fx-border-color: black; -fx-border-radius: 3;");
				isValid = false;
			} else {
				mrnTextField.setStyle(null);
			}
		} else {
			// Ensure skipBox remains invisible when MRN is skipped
			skipBox.setVisible(false);
			skipBox.setManaged(false);
		}

		// Validate DOB only if it's not skipped and validateDOB() method return false
		if (!dobSkipped) {
			if (!validateDOB()) {
				isValid = false;
			}
		} else {
			// Ensure errorSkippingDob remains invisible when DOB is skipped
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);
		}
		// validating email
		String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
		String email = emailTextField.getText().trim();
		// Check if email matches the pattern
		// Check if email matches the pattern
		if (!email.isEmpty() && !email.matches(emailPattern)) {
			// Show error label for invalid email
			errorLabelEmail.setText("Invalid Email");
			errorLabelEmail.setVisible(true);
			errorLabelEmail.setManaged(true);

			// Apply styling to error label and text field
			emailTextField.setStyle("-fx-border-color: red; -fx-border-radius: 3;");
			errorLabelEmail.setStyle("-fx-font-family: 'Arial';" + "-fx-text-fill: red;" + "-fx-font-style: italic;"
					+ "-fx-font-size: 10;" + "-fx-font-weight: bold;");

			isValid = false;
		} else {
			// Clear error state if email is valid or field is empty
			errorLabelEmail.setVisible(false);
			errorLabelEmail.setManaged(false);
			emailTextField.setStyle(null); // Clear any previous error styling
		}

		return isValid;
	}

	// Helper method for DOB validation
	private static boolean validateDOB() {
		String day = dateTextField.getText();
		String month = monthTextField.getText();
		String year = yearTextField.getText();

		boolean isDobValid = !day.isEmpty() && !month.isEmpty() && !year.isEmpty();

		if (!isDobValid) {
			errorSkippingDob.setVisible(true);
			errorSkippingDob.setManaged(true);
			return false;
		} else {
			errorSkippingDob.setVisible(false);
			errorSkippingDob.setManaged(false);
			return true;
		}
	}

	// validating medical fields
	public static boolean validateMedicalField(ComboBox<String> combo, ComboBox<String> yearOfDiabComboBox) {
		boolean isValid = true;

		lastEyeExamHyperLink.setOnAction(event -> {
			hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
			hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
			hboxContaingerrorLabelAndHyperLink.setManaged(false);
			hboxContaingerrorLabelAndHyperLink.setVisible(false);

			hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
			hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
			hboxContainingErrorlabelaAndHyperLink.setManaged(false);
			hboxContainingErrorlabelaAndHyperLink.setVisible(false);
			vboxForGlaucomaHistory.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			vboxForLastEyeExam.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			vboxForYearOfDiabates.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			yearOfDiabSkipped = true;
			glaucomaHistorySkipped = true;
			lastEyeExamSkipped = true;

		});
		
		yearOfDiabetesHyperLink.setOnAction(event -> {
			hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
			hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
			hboxContaingerrorLabelAndHyperLink.setManaged(false);
			hboxContaingerrorLabelAndHyperLink.setVisible(false);

			hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
			hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
			hboxContainingErrorlabelaAndHyperLink.setManaged(false);
			hboxContainingErrorlabelaAndHyperLink.setVisible(false);
			vboxForGlaucomaHistory.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			vboxForLastEyeExam.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			vboxForYearOfDiabates.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			yearOfDiabSkipped = true;
			glaucomaHistorySkipped = true;
			lastEyeExamSkipped = true;
		});

		// Event handler for Glaucoma History HyperLink
		glaucomaHistoryHyperLink.setOnAction(event -> {
			hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
			hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
			hboxContaingerrorLabelAndHyperLink.setManaged(false);
			hboxContaingerrorLabelAndHyperLink.setVisible(false);

			hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
			hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
			hboxContainingErrorlabelaAndHyperLink.setManaged(false);
			hboxContainingErrorlabelaAndHyperLink.setVisible(false);
			vboxForGlaucomaHistory.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			vboxForLastEyeExam.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			vboxForYearOfDiabates.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			yearOfDiabSkipped = true;
			glaucomaHistorySkipped = true;
			lastEyeExamSkipped = true;
			// Clear any related inputs if necessary
		});
		if (yearOfDiabSkipped && lastEyeExamSkipped && glaucomaHistorySkipped) {
			return isValid; // All skip flags are true, skip further validation
		}

		// Validate the first ComboBox
		if (combo.getValue().equals("-------")) {
			pupilDilationErrorLabel.setVisible(true);
			pupilDilationErrorLabel.setManaged(true);

			pupilDilationErrorLabel.setStyle(
					"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
			vboxForPupilDilation.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			isValid = false;
		}

		// Validate the second ComboBox only if it's not skipped
		if (!yearOfDiabSkipped) {
			if (yearOfDiabComboBox.getValue() == null || yearOfDiabComboBox.getValue().equals("---Select---")) {
				hboxContaingerrorLabelAndHyperLink.setVisible(true);
				hboxContaingerrorLabelAndHyperLink.setManaged(true);
				vboxForYearOfDiabates.setStyle("-fx-border-color:red;-fx-border-radius:5;");
				isValid = false;
			}
		}

		// Validate CheckBoxes
		if (!glaucomaHistorySkipped) {
			if (!anyCheckBoxesSelected(noCheckBox, selfCheckBox, parentsCheckBox, siblingsCheckBox, notSureCheckBox)) {
				hboxContainingErrorlabelaAndHyperLink.setVisible(true);
				hboxContainingErrorlabelaAndHyperLink.setManaged(true);
				vboxForGlaucomaHistory.setStyle("-fx-border-color:red;-fx-border-radius:5;");
				isValid = false;
			}
		}

		if (insulinTypeComboBox.getValue().equals("---Select---")) {
			insulinerror.setVisible(true);
			insulinerror.setManaged(true);
			insulinVBox.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			insulinerror.setStyle(
					"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
			isValid = false;
		}
		// if lastEyeexam is not skipped
		if (!lastEyeExamSkipped) {
			if (lastEyeExamComboBox.getValue().equals("---Select---")) {
				hboxContainingLastEyeExamLabelandHyperLink.setVisible(true);
				hboxContainingLastEyeExamLabelandHyperLink.setManaged(true);
				vboxForLastEyeExam.setStyle("-fx-border-color:red;-fx-border-radius:5;");
				isValid = false;
			}
			// Check if reasonForConsultation text field is empty
			if (reasonForConsultation.isVisible()) {
				// Show the error label
				if (reasonForConsultation.getText().isEmpty()) {
					// Show the error label
					errorReasonForConsultLabel.setVisible(true);
					errorReasonForConsultLabel.setManaged(true);
					reasonForConsultation.setStyle("-fx-border-color:red;-fx-border-radius:3;");
					errorReasonForConsultLabel.setStyle(
							"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
				} else {
					// Hide the error label if the text is not empty
					errorReasonForConsultLabel.setVisible(false);
					errorReasonForConsultLabel.setManaged(false);
				}
			} else {
				// If the selected value is not "Not diabetic", hide the error label
				errorReasonForConsultLabel.setVisible(false);
				errorReasonForConsultLabel.setManaged(false);
			}
		}

		if (diabeticTypeComboBox.getValue().equals("Select diabetic type")) {
			diabeticTypeErrorlbel.setVisible(true);
			diabeticTypeErrorlbel.setManaged(true);
			diabaticTypeVBox.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			isValid = false;
		}

		return isValid;
	}

	/***
	 * 
	 * @param checkBoxes
	 * @return
	 */
	private static boolean anyCheckBoxesSelected(CheckBox... checkBoxes) {
		for (CheckBox check : checkBoxes) {
			if (check.isSelected()) {
				return true;
			}
		}
		return false;
	}

	
	/**
	 * method to fetch the site based on username and password
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
	/**
	 * these are the format will be accepted
	 */
	 private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
		        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"),
		        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS"),
		        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"),
		        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
		        DateTimeFormatter.ofPattern("yyyy-MM-dd")
		    );
	 /***
	  * 
	  * @param dateTimeString
	  * @return LocalDate
	  */

		    public static LocalDate parseDate(String dateTimeString) {
		        if (dateTimeString == null || dateTimeString.isEmpty() || "null".equals(dateTimeString)) {
		            return null;
		        }

		        // First, try to parse with the custom formatters
		        for (DateTimeFormatter formatter : FORMATTERS) {
		            try {
		            	// this will return a Local date
		                LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter);
		                return dateTime.toLocalDate();
		            } catch (DateTimeParseException e) {
		                // Continue to the next formatter
		            }
		        }

		        // If custom formatters fail, try a more lenient approach
		        try {
		            // This will handle formats like "2024-09-10 14:43:57.26"
		            DateTimeFormatter lenientFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss[.SSS][.SS][.S]");
		            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, lenientFormatter);
		            return dateTime.toLocalDate();
		        } catch (DateTimeParseException e) {
		            // If all parsing attempts fail, throw an exception
		            throw new IllegalArgumentException("Unable to parse date: " + dateTimeString, e);
		        }
		    }
/***
 * 
 * @param date 
 * @return string
 */
		    public static String formatDate(LocalDate date) {
		        if (date == null) {
		            return "";
		        }
		        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		    }


public Map<String, String> initializeSiteComboBox(String username2, String password2) {
	Map<HttpsConnectors.RESPONSE, String> response = getSiteDetails(username2, password2);
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
public Map<RESPONSE, String> getSiteDetails(String userName, String password) {
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
//Method to get the site ID based on the selected site name from the ComboBox
public  String getSiteIdFromComboBox(String username,String password,ComboBox<String> siteCombo) {
	System.out.println("site initalixzation value is" +initializeSiteComboBox(username,password));
	//initializeSiteComboBox();
	String siteValue=siteCombo.getValue();
	System.out.println(siteValue);
 for (Map.Entry<String, String> entry : initializeSiteComboBox(username,password).entrySet()) {
     if (entry.getValue().equals(siteValue)) {
       //  return entry.getKey(); // Return the site ID for the selected site name
         selectedSiteIdFromSiteCombo = entry.getKey(); // Get the site ID for the selected site name
         return selectedSiteIdFromSiteCombo;
			//break;
   //  break;
 }
 }
// return null; // Return null if no matching site name is found
 return "site id not found";
}

		    
}



