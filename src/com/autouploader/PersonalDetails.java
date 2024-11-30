package com.autouploader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import com.eyepacs.ext.connectors.https.HttpsConnectors;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import ext.eyepacs.encryptor.aes.EyePacsEncryptorAES;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class PersonalDetails {
	public VBox vboxforPregnancy;
	private TextField nameTextField;
	private ComboBox<String> genderComboBox;
	private TextField emailTextField;
	private Label nameErrorLabel;
	private TextField mrnTextField;
	private Label errorLabelEmail;
	private HBox skipBox;
	private HBox errorSkippingDob;
	private TextField yearTextField;
	private final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
	private TextField dateTextField;
	private TextField monthTextField;
	private Hyperlink dobSkipHyperLink;
	private Hyperlink mrnSkipLink;
	private boolean mrnSkipped;
	private boolean dobSkipped;
	private CheckBox declineDataSharingCheckBox;
	private boolean isPersonalDetailsChanged;
	private ComboBox<String> ethinictyComboBox;
	private TextField contactTextField;
	private TextField genderTextField;
	private Label genderValue;
	private Label ethnicityLabelForEdit;
	private Label nameLabelForEdit;
	private Label emailForEdit;
	private VBox vboxForNameTextFieldAndErrorLabel;
	private Label mrnLabelForEdit;
	private VBox vboxForMrnTextFieldAndErrorLabel;
	private Label contactNoForEdit;
	private VBox vboxForEmailTextFieldAndErrorLabel;
	private Label dateLabelForEdit;
	private Label monthLabelForEdit;
	private Label yearLabelForEdit;
	private VBox monthVBox;
	private VBox yearVbox;
	private VBox dateVBox;
	private Button clipboardButtonForDateField;
	private Button clipboardButtonForMrnfield;
	private boolean isEditMode = false;
	private Button clipboardButtonFornamefield;
	private Label genderValueForSave;
	private Label mrnValueForSave;
	private Label contactValueForSave;
	private Label ethnicityValueForSave;
	private Label nameValueForSave;
	private Label emailValueForSave;
	private Label monthValueForSave;
	private Label dateValueForSave;
	private Label yearValueForSave;
	private String currentlyClickedId;

	public VBox getVboxforPregnancy() {
		return vboxforPregnancy;
	}

	public void setVboxforPregnancy(VBox vboxforPregnancy) {
		this.vboxforPregnancy = vboxforPregnancy;
	}

	public TextField getNameTextField() {
		return nameTextField;
	}

	public void setNameTextField(TextField nameTextField) {
		this.nameTextField = nameTextField;
	}

	public ComboBox<String> getGenderComboBox() {
		return genderComboBox;
	}

	public void setGenderComboBox(ComboBox<String> genderComboBox) {
		this.genderComboBox = genderComboBox;
	}

	public TextField getEmailTextField() {
		return emailTextField;
	}

	public void setEmailTextField(TextField emailTextField) {
		this.emailTextField = emailTextField;
	}

	public Label getNameErrorLabel() {
		return nameErrorLabel;
	}

	public void setNameErrorLabel(Label nameErrorLabel) {
		this.nameErrorLabel = nameErrorLabel;
	}

	public TextField getMrnTextField() {
		return mrnTextField;
	}

	public void setMrnTextField(TextField mrnTextField) {
		this.mrnTextField = mrnTextField;
	}

	public Label getErrorLabelEmail() {
		return errorLabelEmail;
	}

	public void setErrorLabelEmail(Label errorLabelEmail) {
		this.errorLabelEmail = errorLabelEmail;
	}

	public HBox getSkipBox() {
		return skipBox;
	}

	public void setSkipBox(HBox skipBox) {
		this.skipBox = skipBox;
	}

	public HBox getErrorSkippingDob() {
		return errorSkippingDob;
	}

	public void setErrorSkippingDob(HBox errorSkippingDob) {
		this.errorSkippingDob = errorSkippingDob;
	}

	public TextField getYearTextField() {
		return yearTextField;
	}

	public void setYearTextField(TextField yearTextField) {
		this.yearTextField = yearTextField;
	}

	public TextField getDateTextField() {
		return dateTextField;
	}

	public void setDateTextField(TextField dateTextField) {
		this.dateTextField = dateTextField;
	}

	public TextField getMonthTextField() {
		return monthTextField;
	}

	public void setMonthTextField(TextField monthTextField) {
		this.monthTextField = monthTextField;
	}

	public Hyperlink getDobSkipHyperLink() {
		return dobSkipHyperLink;
	}

	public void setDobSkipHyperLink(Hyperlink dobSkipHyperLink) {
		this.dobSkipHyperLink = dobSkipHyperLink;
	}

	public Hyperlink getMrnSkipLink() {
		return mrnSkipLink;
	}

	public void setMrnSkipLink(Hyperlink mrnSkipLink) {
		this.mrnSkipLink = mrnSkipLink;
	}

	public String getEMAIL_REGEX() {
		return EMAIL_REGEX;
	}

	public GridPane personalGridForUpload(String currentlyclickedlabel, String patientName, String medicalRecordNumber,
			String ethnicity, String phone, String email, String gender, String birthDay, String birthMonth,
			String birthYear) throws FileNotFoundException {
		currentlyClickedId = currentlyclickedlabel;
		GridPane gridPane = new GridPane();
		gridPane.setHgap(10);
		gridPane.setVgap(10);
		double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();

		System.out.println("currently expanded patent id is+" + currentlyclickedlabel);
		Label nameLabel = new Label("NAME"); // Label without asterisk
//Add an asterisk in red color
		Label asteriskLabel = new Label("*");
		asteriskLabel.setStyle("-fx-text-fill: red;");
		HBox nameHBox = new HBox(1);
		
		nameHBox.getChildren().addAll(nameLabel, asteriskLabel);
		nameHBox.setStyle("-fx-background-color:white;");
		nameTextField = new TextField();
		nameLabelForEdit = new Label();
	//	nameLabelForEdit.setPrefWidth(160);
		nameLabelForEdit.setPrefWidth(screenWidth*.10);
		nameLabelForEdit.setText(patientName);
		//nameTextField.setPrefWidth(220);
		nameTextField.setPrefWidth(screenWidth*.15);
		//nameTextField.setPadding(new Insets(0,screenWidth*.01,0,0));
		nameTextField.setPromptText("Type Here");
		if (patientName != null) {

			nameTextField.setText(patientName);
		} else {
			nameTextField.setText("");
		}

		nameErrorLabel = new Label("Please enter full name");
		nameErrorLabel.setVisible(false);
		nameErrorLabel.setManaged(false);
		nameTextField.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		vboxForNameTextFieldAndErrorLabel = new VBox();
		if (patientName != null) {
			vboxForNameTextFieldAndErrorLabel.getChildren().addAll(nameLabelForEdit, nameErrorLabel);
		} else {
			vboxForNameTextFieldAndErrorLabel.getChildren().addAll(nameTextField, nameErrorLabel);
		}

		HBox hboxForNameAndTextBox = new HBox();
		hboxForNameAndTextBox.setPrefWidth(screenWidth*.25);
		clipboardButtonFornamefield = new Button();
		if (patientName != null) {

			clipboardButtonFornamefield.setVisible(true);
			clipboardButtonFornamefield.setManaged(true);
		} else {
			clipboardButtonFornamefield.setVisible(false);
			clipboardButtonFornamefield.setManaged(false);
		}
		Image clipboardImageForName = new Image(new FileInputStream("resources/images/copy-clipboad.png")); // Ensure
																											// you have
																											// this
																											// image in
																											// your
																											// resources
		ImageView clipboardIconForNameField = new ImageView(clipboardImageForName);

		clipboardIconForNameField.setFitWidth(15);
		clipboardIconForNameField.setFitHeight(15);
		clipboardButtonFornamefield.setGraphic(clipboardIconForNameField);
		/*
		 * setting action for clipButton
		 */
		clipboardButtonFornamefield.setOnAction(e -> copyToClipboard(nameTextField, null));
		hboxForNameAndTextBox.getChildren().addAll(nameHBox, vboxForNameTextFieldAndErrorLabel,
				clipboardButtonFornamefield);
		gridPane.add(hboxForNameAndTextBox, 0, 0); // Use the HBox containing nameLabel and asteriskLabel
		Label genderLabel = new Label("GENDER");
		genderLabel.setPadding(new Insets(0, screenWidth*.012, 0, 0));
		//genderLabel.setPadding(new Insets(0, 0, 0, 80));
		////// GENDER
		HBox hboxforGenderLabelAndAstricksymbol = new HBox(1);
		genderComboBox = new ComboBox<>();
		genderValue = new Label();
		genderValue.setText(gender);
		genderComboBox.setValue(genderValue.getText());

		genderComboBox.setPromptText("Select");
		genderComboBox.setStyle(
				"-fx-background-color: white; -fx-border-width: 1;-fx-text-fill: black; -fx-border-color: black;-fx-border-radius: 3;");
		genderComboBox.getItems().addAll("Select", "Male", "Female", "Other", "Unknown");
		if (gender == null) {
			genderComboBox.setValue("Select");
		}
		/*
		 * mapping for gender
		 */

		if (gender != null) {
			if (gender.equals("M")) {
				genderComboBox.setValue("Male");

			} else if (gender.equals("F")) {
				genderComboBox.setValue("Female");

			} else if (gender.equals("N")) {
				genderComboBox.setValue("");

			} else if (gender.equals("O")) {
				genderComboBox.setValue("Other");

			} else if (gender.equals("U")) {
				genderComboBox.setValue("Unknown");

			}

		} else {
			System.out.println("value is null");
		}
		/**
		 * mapping of gender label
		 */
		if (gender != null) {
			if (gender.equals("M")) {

				genderValue.setText("Male");
			} else if (gender.equals("F")) {

				genderValue.setText("Female");
			} else if (gender.equals("N")) {

				genderValue.setText("");
			} else if (gender.equals("O")) {

				genderValue.setText("Other");
			} else if (gender.equals("U")) {

				genderValue.setText("Unknown");
			}

		} else {
			System.out.println("value is null");
		}

		genderComboBox.setPrefWidth(screenWidth*.2); // Adjust the width as needed
		HBox hboxForGenderAndComboBox = new HBox();
		if (patientName != null) {
			hboxForGenderAndComboBox.getChildren().addAll(hboxforGenderLabelAndAstricksymbol, genderValue);
		} else {
			hboxForGenderAndComboBox.getChildren().addAll(hboxforGenderLabelAndAstricksymbol, genderComboBox);
		}
		hboxforGenderLabelAndAstricksymbol.getChildren().addAll(genderLabel);
		HBox hboxFormrnLabelandAstrick = new HBox();
		Label asteriskLabel2 = new Label("*");
		asteriskLabel2.setStyle("-fx-text-fill: red;");
		/// MRN
		Label mrnLabel = new Label("MRN");
		hboxFormrnLabelandAstrick.getChildren().addAll(mrnLabel, asteriskLabel2);
	//	hboxFormrnLabelandAstrick.setPadding(new Insets(0,8, 0, 0));
		mrnTextField = new TextField();
		if (medicalRecordNumber != null) {
			mrnTextField.setText(medicalRecordNumber);
		}
		mrnLabelForEdit = new Label();
		mrnLabelForEdit.setPrefWidth(screenWidth*.10);
		mrnLabelForEdit.setText(medicalRecordNumber);
		/**
		 * creating clipboard for mrn text field
		 */
		clipboardButtonForMrnfield = new Button();
		if (patientName != null) {
			clipboardButtonForMrnfield.setVisible(true);
			clipboardButtonForMrnfield.setManaged(true);
		} else {
			clipboardButtonForMrnfield.setVisible(false);
			clipboardButtonForMrnfield.setManaged(false);
		}
		Image clipboardImageForMrn = new Image(new FileInputStream("resources/images/copy-clipboad.png"));

		/*
		 * Ensure you have this image in your resources
		 */
		ImageView clipboardIconForMrn = new ImageView(clipboardImageForMrn);
		clipboardIconForMrn.setFitWidth(15);
		clipboardIconForMrn.setFitHeight(15);
		clipboardButtonForMrnfield.setGraphic(clipboardIconForMrn);
		clipboardButtonForMrnfield.setOnAction(e -> copyToClipboard(mrnTextField, null));

		//mrnTextField.setPrefWidth(220);
		mrnTextField.setPrefWidth(screenWidth*.15);
		// mrnTextField.setDisable(true);
		// mrnTextField.setEditable(false);
		mrnTextField.setPromptText("Type Here");
		mrnTextField.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");

		// Create the error label
		Label mrnErrorLabel = new Label("Please enter MRN.");
		mrnErrorLabel.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		// Create an HBox to hold the skip message
		Label skipMessage = new Label("To skip,");
		skipMessage.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		mrnSkipLink = new Hyperlink("click here");
		mrnSkipLink.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;-fx-underline: true;");
		skipBox = new HBox(mrnErrorLabel, skipMessage, mrnSkipLink);

		vboxForMrnTextFieldAndErrorLabel = new VBox();
		if (patientName != null) {
			vboxForMrnTextFieldAndErrorLabel.getChildren().addAll(mrnLabelForEdit, skipBox);
		} else {
			vboxForMrnTextFieldAndErrorLabel.getChildren().addAll(mrnTextField, skipBox);
		}
		HBox hboxForMrnLabelAndTextBox = new HBox(5);
		hboxForMrnLabelAndTextBox.getChildren().addAll(hboxFormrnLabelandAstrick, vboxForMrnTextFieldAndErrorLabel,
				clipboardButtonForMrnfield);
		skipBox.setVisible(false);
		skipBox.setManaged(false);
		// Set alignment and padding
		skipBox.setAlignment(Pos.CENTER_LEFT); // Aligns the nodes in the HBox
		gridPane.add(hboxForMrnLabelAndTextBox, 0, 1);
		hboxForMrnLabelAndTextBox.setPrefWidth(screenWidth*.25);
		HBox hboxForEthinicityandComboBox = new HBox(5);
		Label ethinicityLabel = new Label("ETHINCITY");
		//ethinicityLabel.setPadding(new Insets(0, 0, 0, 80));
		ethinictyComboBox = new ComboBox<>();
//		ethinictyComboBox.setDisable(true);
//		ethinictyComboBox.setDisable(true);

		ethinictyComboBox.setStyle(
				"-fx-background-color: white; -fx-border-width: 1;-fx-opacity:1;  -fx-border-color: black;-fx-border-radius: 3;");

		ethinictyComboBox.setPrefWidth(screenWidth*.2);
		ethinictyComboBox.getItems().addAll("Not Specified", "African American/African Descent",
				"Latin American/Hispanic", "Caucasian/White", "Asian/Pacific Islander", "Indian subcontinent origin",
				"Native American/Alaskan Native", "Multi-racial", "Decline to State", "Other");

		if (ethnicity != null) {
			if (ethnicity.isEmpty()) {
				ethinictyComboBox.setValue(null); // Handle empty string by clearing the selection
			} else if (ethnicity.equals("ethnicity not specified")) {
				ethinictyComboBox.setValue("Not Specified");
			} else if (ethnicity.equals("African Descent")) {
				ethinictyComboBox.setValue("African American/African Descent");
			} else if (ethnicity.equals("Latin American")) {
				ethinictyComboBox.setValue("Latin American/Hispanic");
			} else if (ethnicity.equals("Caucasian")) {
				ethinictyComboBox.setValue("Caucasian/White");
			} else if (ethnicity.equals("Asian")) {
				ethinictyComboBox.setValue("Asian/Pacific Islander");
			} else if (ethnicity.equals("Indian subcontinent origin")) {
				ethinictyComboBox.setValue("Indian subcontinent origin");
			} else if (ethnicity.equals("Multi-racial")) {
				ethinictyComboBox.setValue("Multi-racial");
			} else if (ethnicity.equals("Decline to State")) {
				ethinictyComboBox.setValue("Decline to State");
			} else if (ethnicity.equals("Other")) {
				ethinictyComboBox.setValue("Other");
			} else if (ethnicity.equals("Native American")) {
				ethinictyComboBox.setValue("Native American/Alaskan Native");
			}
		} else {
			ethinictyComboBox.setValue("Not Specified");
		}

		ethnicityLabelForEdit = new Label();

		if (ethnicity != null) {

			if (ethnicity.equals("ethnicity not specified")) {
				ethnicityLabelForEdit.setText("Not Specified");
			} else if (ethnicity.equals("African Descent")) {
				ethnicityLabelForEdit.setText("African American/African Descent");
			} else if (ethnicity.equals("Latin American")) {
				ethnicityLabelForEdit.setText("Latin American/Hispanic");
			} else if (ethnicity.equals("Caucasian")) {
				ethnicityLabelForEdit.setText("Caucasian/White");
			} else if (ethnicity.equals("Asian")) {
				ethnicityLabelForEdit.setText("Asian/Pacific Islander");
			} else if (ethnicity.equals("Indian subcontinent origin")) {
				ethnicityLabelForEdit.setText("Indian subcontinent origin");
			} else if (ethnicity.equals("Multi-racial")) {
				ethnicityLabelForEdit.setText("Multi-racial");
			} else if (ethnicity.equals("Decline to State")) {
				ethnicityLabelForEdit.setText("Decline to State");
			} else if (ethnicity.equals("Other")) {
				ethnicityLabelForEdit.setText("Other");
			} else if (ethnicity.equals("Native American")) {
				ethnicityLabelForEdit.setText("Native American/Alaskan Native");
			} else if (ethnicity.isEmpty()) {
				ethnicityLabelForEdit.setText(null);
			}

		} else {
			ethnicityLabelForEdit.setText("Not Specified");
		}
		if (patientName != null) {
			hboxForEthinicityandComboBox.getChildren().addAll(ethinicityLabel, ethnicityLabelForEdit);
		} else {
			hboxForEthinicityandComboBox.getChildren().addAll(ethinicityLabel, ethinictyComboBox);
		}
		gridPane.add(hboxForEthinicityandComboBox, 1, 1);
		hboxForEthinicityandComboBox.setPrefWidth(screenWidth*.27);
		gridPane.add(hboxForGenderAndComboBox, 1, 0);
		hboxForGenderAndComboBox.setPrefWidth(screenWidth*.27);
		Label dobLabel = new Label("DOB:");
		dobLabel.setPadding(new Insets(0, screenWidth*.01, 0, 0));
		dateTextField = new TextField();
		dateLabelForEdit = new Label();
		// dateLabelForEdit.setPrefWidth(15);
		// dateLabelForEdit.setText(dobLabel(birthDay,birthMonth,birthYear));

		dateLabelForEdit.setText(birthDay + " - ");
		dateTextField.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		dateTextField.setPromptText("day");
		//dateTextField.setPrefSize(70, 5);
		dateTextField.setPrefSize(screenWidth*.047, 5);
		if (birthDay != null) {
			dateTextField.setText(birthDay);
		}

		monthTextField = new TextField();
		monthTextField.setPromptText("month");
		monthTextField.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		monthLabelForEdit = new Label();
		// monthLabelForEdit.setPrefWidth(38);
		monthLabelForEdit.setText(birthMonth + " - ");
		//monthTextField.setPrefSize(70, 5);
		monthTextField.setPrefSize(screenWidth*.047, 5);
		if (birthMonth != null) {
			monthTextField.setText(birthMonth);
		}

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
		monthVBox = new VBox(); // Spacing between elements
		if (patientName != null) {
			monthVBox.getChildren().addAll(monthLabelForEdit, errorLabelForMonth);
		} else {
			monthVBox.getChildren().addAll(monthTextField, errorLabelForMonth);
		}
		Label yearErrorLabel = new Label("Invalid Year");
		yearErrorLabel.setStyle(
				"-fx-font-family: 'Arial';-fx-text-fill:red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		yearErrorLabel.setManaged(false);
		yearErrorLabel.setVisible(false);
		yearTextField = new TextField();
		yearLabelForEdit = new Label();
		yearLabelForEdit.setText(birthYear);
		yearLabelForEdit.setPrefWidth(screenWidth*.056);

		// yearTextField.setDisable(true);
		// yearTextField.setEditable(false);
		yearTextField.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		if (birthYear != null) {
			yearTextField.setText(birthYear);
		}

		// Update the yearTextField with the full year

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

					// If the input is a two-digit year, adjust based on the conditions
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

		clipboardButtonForDateField = new Button();
		Image clipboardImageForDate = new Image(new FileInputStream("resources/images/copy-clipboad.png")); // Ensure
		ImageView clipboardIconForDateField = new ImageView(clipboardImageForDate);

		clipboardIconForDateField.setFitWidth(15);
		clipboardIconForDateField.setFitHeight(15);
		clipboardButtonForDateField.setGraphic(clipboardIconForDateField);
		if (patientName != null) {
			clipboardButtonForDateField.setVisible(true);
			clipboardButtonForDateField.setManaged(true);
		} else {
			clipboardButtonForDateField.setVisible(false);
			clipboardButtonForDateField.setManaged(false);
		}

		clipboardButtonForDateField.setOnAction(e -> {

			hadleDobSelectionForClipBoard(dateTextField, monthTextField, yearTextField);
		});

		Label dayLabel = new Label("Invalid Day");
		dayLabel.setStyle(
				"-fx-font-family: 'Arial'; -fx-font-style: italic;-fx-text-fill:red; -fx-font-size: 10; -fx-font-weight: bold;");
		dayLabel.setManaged(false);
		dayLabel.setVisible(false);
//// Add real-time validation for dayTextField
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
		yearVbox = new VBox();
		if (patientName != null) {
			yearVbox.getChildren().addAll(yearLabelForEdit, yearErrorLabel);
		} else {
			yearVbox.getChildren().addAll(yearTextField, yearErrorLabel);
		}

		dateVBox = new VBox();
		if (patientName != null) {
			dateVBox.getChildren().addAll(dateLabelForEdit, dayLabel);
		} else {
			dateVBox.getChildren().addAll(dateTextField, dayLabel);
		}
		yearTextField.setPromptText("year");
		//yearTextField.setPrefSize(70, 5);
		yearTextField.setPrefSize(screenWidth*.047, 5);
		HBox hboxForDob = new HBox(5);
		hboxForDob.getChildren().addAll(monthVBox, dateVBox, yearVbox, clipboardButtonForDateField);
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
		hboxContainingLabelAnddobVbox.setPrefWidth(screenWidth*.27);
		Label contactLabel = new Label("Contact No");
		//contactLabel.setPadding(new Insets(0, 0, 0, 80));
		contactTextField = new TextField();
		// contactTextField.setDisable(true);
		// contactTextField.setEditable(false);
		if (phone != null) {
			contactTextField.setText(phone);
		}

		contactTextField.setPrefWidth(screenWidth*.2);
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
		Label contactNoForEdit = new Label();
		contactNoForEdit.setText(phone);
		if (patientName != null) {
			hboxFoContactField.getChildren().addAll(contactLabel, contactNoForEdit);
		} else {
			hboxFoContactField.getChildren().addAll(contactLabel, contactTextField);
		}
		gridPane.add(hboxFoContactField, 1, 2);
		hboxFoContactField.setPrefWidth(screenWidth*.27);
		Label emailLabel = new Label("Email");
		emailLabel.setPadding(new Insets(0, 15, 0, 0));
		emailTextField = new TextField();
		// emailTextField.setEditable(false);
		if (email != null) {
			emailTextField.setText(email);
		}
		emailForEdit = new Label();
		emailForEdit.setText(email);

		emailTextField.setPromptText("enter email");
		//emailTextField.setPrefWidth(220);
		emailTextField.setPrefWidth(screenWidth*.15);
		emailTextField.setStyle(
				"-fx-background-color:white; -fx-border-width: 1; -fx-border-color: black;-fx-border-radius: 3;");
		errorLabelEmail = new Label("Invalid Email");
		errorLabelEmail.setVisible(false);
		errorLabelEmail.setManaged(false);

		Button editPatientButton = new Button();
		if (patientName == null) {
			editPatientButton.setVisible(false);
			editPatientButton.setManaged(false);
		}
		Image editImage = new Image(new FileInputStream("resources/images/edit_icon.jpg"));
		ImageView editImageView = new ImageView(editImage);

		editImageView.setFitHeight(20);
		editImageView.setFitWidth(20);
		editPatientButton.setGraphic(editImageView);
		/*
		 * setting action for edit button in patient section
		 */

		editPatientButton.setOnAction(e -> {

			genderValueForSave = new Label(genderComboBox.getValue());
			// genderValueForSave.setPrefWidth(150);
			ethnicityValueForSave = new Label(ethinictyComboBox.getValue());
			// ethnicityValueForSave.setPrefWidth(150);
			nameValueForSave = new Label(nameTextField.getText());
			nameValueForSave.setPrefWidth(screenWidth*.10);
			mrnValueForSave = new Label(mrnTextField.getText());
			mrnValueForSave.setPrefWidth(screenWidth*.10);
			emailValueForSave = new Label(emailTextField.getText());
			emailValueForSave.setPrefWidth(screenWidth*.10);
			monthValueForSave = new Label(monthTextField.getText() + " - ");
			// monthValueForSave.setPrefWidth(35);
			dateValueForSave = new Label(dateTextField.getText() + " - ");

			// dateValueForSave.setPrefWidth(35);
			yearValueForSave = new Label(yearTextField.getText());
			yearValueForSave.setPrefWidth(screenWidth*.056);

			contactValueForSave = new Label(contactTextField.getText());
			if (isEditMode) {
				// Save Mode: Replace TextFields and ComboBoxes with Labels, update button to
				// Edit mode
				hboxForGenderAndComboBox.getChildren().set(1, genderValueForSave); // Set with the selected value
				hboxForEthinicityandComboBox.getChildren().set(1, ethnicityValueForSave);
				vboxForNameTextFieldAndErrorLabel.getChildren().set(0, nameValueForSave);
				vboxForMrnTextFieldAndErrorLabel.getChildren().set(0, mrnValueForSave);
				vboxForEmailTextFieldAndErrorLabel.getChildren().set(0, emailValueForSave);
				monthVBox.getChildren().set(0, monthValueForSave);
				dateVBox.getChildren().set(0, dateValueForSave);
				yearVbox.getChildren().set(0, yearValueForSave);
				hboxFoContactField.getChildren().set(1, contactValueForSave);
				// Hide the clipboard and date buttons
				clipboardButtonFornamefield.setManaged(true);
				clipboardButtonFornamefield.setVisible(true);
				clipboardButtonForMrnfield.setManaged(true);
				clipboardButtonForMrnfield.setVisible(true);
				clipboardButtonForDateField.setManaged(true);
				clipboardButtonForDateField.setVisible(true);
				// Disable any editable elements
				declineDataSharingCheckBox.setDisable(true);

				// Change button to Edit mode
				editPatientButton.setGraphic(editImageView);
				String fullName[] = nameValueForSave.getText().split(",");
				String patientJsonString = sendPatientData(currentlyClickedId, fullName[1].trim(), fullName[0].trim(),
						genderValueForSave.getText(), mrnValueForSave.getText(), "", contactValueForSave.getText(), "",
						"", emailValueForSave.getText(), "", ethnicityValueForSave.getText(), dateTextField.getText(),
						monthTextField.getText(), yearTextField.getText(), "");
				sendPostRequestForPatient(patientJsonString, currentlyClickedId);

				System.out.println(patientJsonString);
				isEditMode = false;
			}

			else {
				// Edit Mode: Replace Labels with TextFields and ComboBoxes, update button to
				// Save mode
				hboxForGenderAndComboBox.getChildren().set(1, genderComboBox);
				hboxForEthinicityandComboBox.getChildren().set(1, ethinictyComboBox);
				vboxForNameTextFieldAndErrorLabel.getChildren().set(0, nameTextField);
				vboxForMrnTextFieldAndErrorLabel.getChildren().set(0, mrnTextField);
				vboxForEmailTextFieldAndErrorLabel.getChildren().set(0, emailTextField);
				monthVBox.getChildren().set(0, monthTextField);
				dateVBox.getChildren().set(0, dateTextField);
				yearVbox.getChildren().set(0, yearTextField);
				hboxFoContactField.getChildren().set(1, contactTextField);

				// Show the clipboard and date buttons
				clipboardButtonFornamefield.setManaged(false);
				clipboardButtonFornamefield.setVisible(false);
				clipboardButtonForMrnfield.setManaged(false);
				clipboardButtonForMrnfield.setVisible(false);
				clipboardButtonForDateField.setManaged(false);
				clipboardButtonForDateField.setVisible(false);

				// Enable any editable elements
				declineDataSharingCheckBox.setDisable(false);
// craeting imageview object for save icon
				ImageView saveIconImageView = null;
				try {
					saveIconImageView = new ImageView(
							new Image(new FileInputStream("resources/images/save_icon.jfif")));
					saveIconImageView.setFitHeight(13);
					saveIconImageView.setFitWidth(13);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// Change button to Save mode
				editPatientButton.setGraphic(saveIconImageView);
				isEditMode = true;
			}
		});

		// validateChangeForPatientField(nameTextField, mrnTextField, dateTextField,
		// monthTextField, yearTextField, genderComboBox, ethinictyComboBox,
		// emailTextField, contactTextField, patientName, medicalRecordNumber,
		// ethnicity, phone, email, gender, birthMonth, birthDay, birthYear);
		vboxForEmailTextFieldAndErrorLabel = new VBox();
		if (patientName != null) {
			vboxForEmailTextFieldAndErrorLabel.getChildren().addAll(emailForEdit, errorLabelEmail);
		} else {
			vboxForEmailTextFieldAndErrorLabel.getChildren().addAll(emailTextField, errorLabelEmail);
		}
		HBox hboxContainingEmailLabelAndHbox = new HBox();
		hboxContainingEmailLabelAndHbox.getChildren().addAll(emailLabel, vboxForEmailTextFieldAndErrorLabel);
		declineDataSharingCheckBox = new CheckBox(
				"Patient declines to share de-identified\ndata for research and development");
		declineDataSharingCheckBox.setDisable(false);
		HBox hboxFordeclinedCheckBoxAndEditButton = new HBox(screenWidth*.06);
		hboxFordeclinedCheckBoxAndEditButton.getChildren().addAll(declineDataSharingCheckBox, editPatientButton);

		//declineDataSharingCheckBox.setPadding(new Insets(0, 0, 0, 80));
		gridPane.add(hboxContainingEmailLabelAndHbox, 0, 3);
		hboxContainingEmailLabelAndHbox.setPrefWidth(screenWidth*.27);
		gridPane.add(hboxFordeclinedCheckBoxAndEditButton, 1, 3);
		hboxFordeclinedCheckBoxAndEditButton.setPrefWidth(screenWidth*.27);
		// gridPane.add(editPatientButton, 4, 3);

		return gridPane;

	}

	private void sendPostRequestForPatient(String patientJsonString, String patientIdForClikedPatient) {
		String apiUrl = "/uploader/edit/patient/" + patientIdForClikedPatient;
		// String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(,
		// userPassword);
		// TODO Auto-generated method stub
		JsonObject patientJsonData = null;
		try {
			patientJsonData = (JsonObject) JsonParser.parseString(patientJsonString);

			System.out.println("json data of patient is  " + patientJsonData);

		} catch (Exception e) {
			e.printStackTrace();
		}

		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, apiUrl,
				HttpsConnectors.METHOD.POST);
		connector.doLogin( patientJsonString , "application/json","application/json");
		
		

	}
/***
 * 
 * @param dateTextField2
 * @param monthTextField2
 * @param yearTextField2
 */
	private void hadleDobSelectionForClipBoard(TextField dateTextField2, TextField monthTextField2,
			TextField yearTextField2) {
		System.out.println("date textfield is " + dateTextField2.getText());
		System.out.println("year textfie;d value is " + yearTextField2.getText());
		System.out.println("month textfiel;d value is " + monthTextField2.getText());
		String day = dateTextField.getText().trim();
		String month = monthTextField.getText().trim();
		String year = yearTextField.getText().trim();
		StringBuilder dobForClipBoard = new StringBuilder();

		if (!day.isEmpty() && !month.isEmpty() && !year.isEmpty()) {

			dobForClipBoard.append(dateTextField2.getText()).append("-").append(monthTextField2.getText()).append("-")
					.append(yearTextField2.getText());
			String dobClipp = dobForClipBoard.toString();

			copyToClipboard(null, dobClipp);

			// TODO Auto-generated method stub
		}

	}

	/*
	 * method to handle the clip from the textfield
	 */
	private void copyToClipboard(TextField nameTextField, String dobClipper) {
		String textToCopy;

		// Use the dobClipper if it's not null; otherwise, use the TextField content
		if (dobClipper != null) {
			textToCopy = dobClipper;
			System.out.println("Copied to clipboard for dob: " + dobClipper); // Confirmation for DOB
		} else {
			textToCopy = nameTextField.getText(); // Get text from the TextField
			System.out.println("Copied to clipboard: " + textToCopy); // Confirmation for name
		}

		// Copying to the system clipboard
		Clipboard clipboard = Clipboard.getSystemClipboard();
		ClipboardContent content = new ClipboardContent();
		content.putString(textToCopy); // Set the string content
		clipboard.setContent(content); // Copy to clipboard
	}

	/*
	 * method to validate the form fields
	 */
	public boolean validateFormFields() {
		boolean isValid = true;
		System.out.println(nameTextField.getText());
		System.out.println(mrnTextField.getText());
		System.out.println(genderComboBox.getValue());
		// System.out.println(emailText);
		String validPattern = "^(?:[A-Za-z0-9]+(?: [A-Za-z0-9]+)+|[A-Za-z0-9]+(?:,[ ]?[A-Za-z0-9]+)+)$";
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
		if (mrnSkipLink != null) {
			mrnSkipLink.setOnAction(e -> {
				mrnSkipped = true; // Skip MRN validation
				dobSkipped = true; // Skip DOB validation

				skipBox.setVisible(false);
				skipBox.setManaged(false);
				errorSkippingDob.setVisible(false);
				errorSkippingDob.setManaged(false);
				mrnTextField.setStyle(null); // Clear error style
			});
		}
		if (dobSkipHyperLink != null) {
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
		}

		/// listerner for name
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

//Helper method for DOB validation
	private boolean validateDOB() {
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

	/***
	 * method to validate the chamnges in patient pane
	 * 
	 * @param nameTextField
	 * @param mrnTextField
	 * @param dayTextField
	 * @param monthTextField
	 * @param yearTextField
	 * @param genderComboBox
	 * @param ethnicityCombo
	 * @param emailTextField
	 * @param contactTextField
	 * @param patientName
	 * @param medicalRecordNumber
	 * @param ethnicity
	 * @param phone
	 * @param email
	 * @param gender
	 * @param birthMonth
	 * @param birthDay
	 * @param birthYear
	 * @return
	 * @return
	 */

	public boolean checkForChangesInPersonalDetails(String patientName, String medicalRecordNumber, String ethnicity,
			String phone, String email, String gender, String birthMonth, String birthDay, String birthYear) {
		// Reset the flag initially to false
		isPersonalDetailsChanged = false;
		System.out.println("is personal field change " + isPersonalDetailsChanged);
		System.out.println("ethncityvalue is " + ethnicity);
		System.out.println("gender value is " + gender);
		System.out.println("name textfield before change  is " + nameTextField.getText());
		System.out.println("bith month before change is " + birthMonth);
		System.out.println("birth day before change is " + birthDay);
		// Check if the name text field has changed
		if (!nameTextField.getText().equals(patientName)) {
			isPersonalDetailsChanged = true;
		}
		System.out.println("is personal fields changed after " + isPersonalDetailsChanged);

		// Check if the MRN text field has changed
		if (!mrnTextField.getText().equals(medicalRecordNumber)) {
			isPersonalDetailsChanged = true;
		}

		// Check if the day text field has changed
		if (!dateTextField.getText().equals(birthDay)) {
			isPersonalDetailsChanged = true;
		}

		// Check if the month text field has changed
		if (!monthTextField.getText().equals(birthMonth)) {
			isPersonalDetailsChanged = true;
		}

		// Check if the year text field has changed
		if (!yearTextField.getText().equals(birthYear)) {
			isPersonalDetailsChanged = true;
		}

		if (gender != null) {
			if (gender.equals("M")) {
				if (!genderValue.getText().equals(genderComboBox.getValue())) {
					isPersonalDetailsChanged = true;
				}
			} else if (gender.equals("F")) {
				if (!genderValue.getText().equalsIgnoreCase(genderComboBox.getValue())) {
					isPersonalDetailsChanged = true;
				}
			} else if (gender.equals("N")) {
				if (!genderValue.getText().equalsIgnoreCase(genderComboBox.getValue())) {
					isPersonalDetailsChanged = true;
				}
			} else if (gender.equals("O")) {
				if (!genderValue.getText().equalsIgnoreCase(genderComboBox.getValue())) {
					isPersonalDetailsChanged = true;
				}
			} else if (gender.equals("U")) {
				if (!genderValue.getText().equalsIgnoreCase(genderComboBox.getValue())) {
					isPersonalDetailsChanged = true;
				}
			}
		}

		if (ethnicity.equalsIgnoreCase("ethnicity not specified")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("African Descent")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Latin American")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equals("Caucasian")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Asian")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Indian subcontinent origin")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Multi-racial")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Decline to State")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Other")) {
			if (!ethinictyComboBox.getValue().equals(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}
		} else if (ethnicity.equalsIgnoreCase("Native American")) {
			if (!ethinictyComboBox.getValue().equalsIgnoreCase(ethnicityLabelForEdit.getText())) {
				isPersonalDetailsChanged = true;
			}

//	    } else {
//	        // If no match, check if ComboBox is set to "Not Specified"
//	        if (ethinictyComboBox.getValue() {
//	            isPersonalDetailsChanged = true;
//	        }
		}

		// Check if the email text field has changed
		if (!emailTextField.getText().equals(email)) {
			isPersonalDetailsChanged = true;
		}
//
//	    // Check if the phone contact text field has changed
		if (!contactTextField.getText().equals(phone)) {
			isPersonalDetailsChanged = true;
		}

		// Return the updated flag to indicate if any field was changed
		return isPersonalDetailsChanged;
	}

	/***
	 * method for editin patient data
	 * 
	 * @param currentlyClickedId2
	 * @param givenNames
	 * @param familyNames
	 * @param gender
	 * @param medicalRecordNumber
	 * @param alternateId
	 * @param contactNumber
	 * @param secondaryNumber
	 * @param mobileNumber
	 * @param email
	 * @param address
	 * @param ethnicity
	 * @param string
	 * @param birthMonth
	 * @param string2
	 * @param middleNames
	 */
	public String sendPatientData(String currentlyClickedId2, String givenNames, String familyNames, String gender,
			String medicalRecordNumber, String alternateId, String contactNumber, String secondaryNumber,
			String mobileNumber, String email, String address, String ethnicity, String bithDay, String birthMonth,
			String birthYear, String middleNames) {

		String genderMappedValue = getGenderCode(gender);
		String ethnicityMappedValue = getEthnicityCode(ethnicity);

// Use a Map to create the JSON data
		Map<String, Object> data = new LinkedHashMap<>();
		data.put("patientId", currentlyClickedId2);
		data.put("givenNames", givenNames);
		data.put("familyNames", familyNames);
		data.put("gender", genderMappedValue);
		data.put("medicalRecordNumber", medicalRecordNumber);
		data.put("alternateId", alternateId);
		data.put("contactNumber", contactNumber);
		data.put("secondaryNumber", secondaryNumber);
		data.put("mobileNumber", mobileNumber);
		data.put("email", email);
		data.put("address", address);
		data.put("ethnicity", ethnicityMappedValue);
		data.put("birthDay", bithDay);
		data.put("birthMonth", birthMonth);
		data.put("birthYear", birthYear);
		data.put("middleNames", middleNames);
		// Convert map to JSON string
		try {
			Gson gson = new Gson();
			return gson.toJson(data);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/***
	 * 
	 * @param gender
	 * @return the mapped gender
	 */
	public String getGenderCode(String gender) {
		if (gender == null) {
			System.out.println("value is null");
			return null;
		}

		switch (gender) {
		case "Male":
			return "M";
		case "Female":
			return "F";
		case "":
			return "N";
		case "Other":
			return "O";
		case "Unknown":
			return "U";
		default:
			System.out.println("Unknown gender: " + gender);
			return null;
		}
	}

	/***
	 * 
	 * @param ethnicity
	 * @return mapped ethnicity value
	 */
	public String getEthnicityCode(String ethnicity) {
		if (ethnicity == null || ethnicity.isEmpty()) {
			return "ethnicity not specified"; // Default to "ethnicity not specified" if null or empty
		}

		switch (ethnicity) {
		case "Not Specified":
			return "ethnicity not specified";
		case "African American/African Descent":
			return "African Descent";
		case "Latin American/Hispanic":
			return "Latin American";
		case "Caucasian/White":
			return "Caucasian";
		case "Asian/Pacific Islander":
			return "Asian";
		case "Indian subcontinent origin":
			return "Indian subcontinent origin";
		case "Multi-racial":
			return "Multi-racial";
		case "Decline to State":
			return "Decline to State";
		case "Other":
			return "Other";
		case "Native American/Alaskan Native":
			return "Native American";
		default:
			System.out.println("Unknown ethnicity: " + ethnicity);
			return "ethnicity not specified"; // Default for unknown values
		}
	}

}
