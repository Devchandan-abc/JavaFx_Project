package com.autouploader;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;

public class MedicalDetails {
	private TextField diabaticTypeTextfield;
	public VBox vboxforPregnancy;
	private VBox vboxMed;
	private TextField reasonForConsultation;
	private Label pupilDilationErrorLabel;
	private ComboBox<String> pupilDilationComboBox;
	private ComboBox<String> yearWithDiabatesComboBox;
	private HBox hboxContaingerrorLabelAndHyperLink;
	private HBox hboxContainingErrorlabelaAndHyperLink;
	private CheckBox selfCheckBox;
	private CheckBox noCheckBox;
	private CheckBox parentsCheckBox;
	private CheckBox siblingsCheckBox;
	private CheckBox notSureCheckBox;
	private ComboBox<String> insulinTypeComboBox;
	private Label insulinerror;
	private HBox hboxContainingLastEyeExamLabelandHyperLink;
	private ComboBox<String> lastEyeExamComboBox;
	private Label errorReasonForConsultLabel;
	private VBox reasonForConsultVBox;
	private VBox vboxForLastEyeExam;
	private Label diabeticTypeErrorlbel;
	private ComboBox<String> diabeticTypeComboBox;
	private Label lastEyeExamLabel;
	private VBox vboxForPupilDilation;
	private VBox vboxForYearOfDiabates;
	private VBox vboxForGlaucomaHistory;
	private VBox insulinVBox;
	private VBox diabaticTypeVBox;
	private Hyperlink lastEyeExamHyperLink;
	private Hyperlink yearOfDiabetesHyperLink;
	private Hyperlink glaucomaHistoryHyperLink;
	private boolean yearOfDiabSkipped;
	private boolean lastEyeExamSkipped;
	private boolean glaucomaHistorySkipped;
	private TextField averageUsualBloodSugar;
	private TextField lowUsualBloodSugar;
	private TextField highUsualBloodSugar;
	private TextField trigyTextField;
	private TextField haemoglobinTextField;
	private TextField cholestorolTextField;
	private ComboBox<String> hyperTensionComboBox;
	private ComboBox<String> yearMostRecentBloodTest;
	private ComboBox<String> monthRecentBloodTest;
	private ComboBox<String> subjectiveDiabticControlComboBox ;
	private TextArea medicationTextArea;
	private 	TextField rightIopTextField;
	private TextField leftEyeTextField ;
	private TextArea otherHistoryTextField;
	private ComboBox<String> visualAquityRightEyeComboBox;
	private ComboBox<String> visualAquityLeftEyeComboBox ;
	private RadioButton pinholeYes;
	private RadioButton pinholeNo;
	private RadioButton withRxYes;
	private RadioButton withRxNo;

	public GridPane medicalRecordGRidPane(PersonalDetails personaldetails, String medications, String hypertension,
			String otherHistory, String hemoglobin, String bloodYearMonth, String bloodYear, String cholestrol,
			String triglycerides, String pupilDilation, String bloodSugarLevelAvg, String bloodSugarLevelHigh,
			String bloodSugarLevelLow, String iopLeft, String iopRight, String visualAcuityRight,
			String glaucomaHistory, String reasonRetinalConsult, String insulinDependent, String duration,
			String yearsWithDiabeties, String lastEyeExam, String subjectiveDiabeticControl, String pregnany,
			String withRx, String pinhole, PersonalDetails personalDetailsToAddPAtient) {
		double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double screenHeight = Screen.getPrimary().getBounds().getHeight();
		System.out.println("personal details for patient is " + personaldetails);
		VBox v1 = new VBox(10);
		vboxMed = new VBox(5);
		HBox h1 = new HBox();
		HBox h2 = new HBox();
		GridPane medicalrecordGridPane = new GridPane();
		medicalrecordGridPane.setHgap(5);
		medicalrecordGridPane.setVgap(5);
		/// pupil dilation
		vboxForPupilDilation = new VBox();
		Label pupilDilationLabel = new Label("Pupil Dilation"); // Label without asterisk
		pupilDilationLabel.setStyle("-fx-font-weight:bold");
		Label asteriskLabelForPupilDilation = new Label("*");
		asteriskLabelForPupilDilation.setStyle("-fx-text-fill: red;");
		HBox pupilDilationHBox = new HBox();
		pupilDilationHBox.getChildren().addAll(pupilDilationLabel, asteriskLabelForPupilDilation);
		pupilDilationLabel.setPadding(new Insets(0, 0, 0, 7));
		pupilDilationComboBox = new ComboBox<>();

		// Add items to the ComboBox
		pupilDilationComboBox.getItems().addAll("-------", "Not Necessary",
				"Declined (please note reason drop were declined)", "1 gtt. tropicamide 0.5%", "1 gtt. tropicamide 1%",
				"Other Dilating Agents (please note dilating agents used)");
		//pupilDilationComboBox.setPrefSize(240, 5);
		pupilDilationComboBox.setPrefWidth(screenWidth*.18);
		pupilDilationComboBox.setStyle("-fx-background-color: white");
		pupilDilationErrorLabel = new Label("Please Select pupil Dilation");
		pupilDilationErrorLabel.setPadding(new Insets(0, 0, 0, 7));
		pupilDilationErrorLabel.setVisible(false);
		pupilDilationErrorLabel.setManaged(false);
		if (pupilDilation != null) {
			if (pupilDilation.equals("declined (please note reason drop were declined)")) {
				pupilDilationComboBox.setValue("Declined (please note reason drop were declined)");
			} else if (pupilDilation.equals("other dilating agents (please note dilating agents used)")) {
				pupilDilationComboBox.setValue("Other Dilating Agents (please note dilating agents used)");
			} else if (pupilDilation.equals("not necessary"))

			{
				pupilDilationComboBox.setValue("Not Necessary");
			} else if (pupilDilation.equals("0")) {
				pupilDilationComboBox.setValue("-------");
			} else {
				pupilDilationComboBox.setValue(pupilDilation);
			}
		}
		if (pupilDilation == null) {
			pupilDilationComboBox.setValue("-------");
		}

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
		haemoglobinLabel.setStyle("-fx-font-weight:bold");
		haemoglobinLabel.setPadding(new Insets(0, 0, 0, 7));
		Label errorLabel = new Label();
		HBox hemoglobinErrorLabel = new HBox(2);
		hemoglobinErrorLabel.getChildren().addAll(haemoglobinLabel, errorLabel);
		haemoglobinTextField = new TextField();
		errorLabel.setStyle("-fx-text-fill: red;"); // Set the error message color to red
		haemoglobinTextField.setPromptText("Type Here");
		if (hemoglobin != null) {
			haemoglobinTextField.setText(hemoglobin);
		}

		haemoglobinTextField
				.setStyle("-fx-background-color:white;-fx-border-color:white;-fx-border-radius:0;-fx-border-width:0;");

		// Add the TextField, Label, and Error Label to the VBox
		vboxForHaemoglobin.getChildren().addAll(hemoglobinErrorLabel, haemoglobinTextField);
		addRealTimeValidation(haemoglobinTextField, errorLabel, vboxForHaemoglobin, true);
		lastEyeExamLabel = new Label("Please select eye exam.To skip,");
		// lastEyeExamLabel.setPadding(new Insets(0,0,0,7));
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
		vboxForLastEyeExam = new VBox();
		vboxForLastEyeExam.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		Label lastEyeExamLabel = new Label("Last Eye Exam"); // Label without asterisk
		lastEyeExamLabel.setStyle("-fx-font-weight:bold");
		Label lasteyeexamasteriskLabel = new Label("*");
		lasteyeexamasteriskLabel.setStyle("-fx-text-fill: red;");
		HBox lastEyeExamHBox = new HBox(1);
		lastEyeExamHBox.setPadding(new Insets(0, 0, 0, 7));
		lastEyeExamHBox.getChildren().addAll(lastEyeExamLabel, lasteyeexamasteriskLabel);
		lastEyeExamComboBox = new ComboBox<>();
		lastEyeExamComboBox.setPrefWidth(screenWidth*.18);
		if (lastEyeExam != null) {
			if (lastEyeExam.equals("Unknown")) {
				lastEyeExamComboBox.setValue("--Select--");
			} else {
				lastEyeExamComboBox.setValue(lastEyeExam);
			}
		}
		if (lastEyeExam == null) {
			lastEyeExamComboBox.setValue("--Select--");
		}

		/***
		 * Listeener is added for last eye combo box
		 */
		/// listener for medical details section
		lastEyeExamComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!"--Select--".equals(newValue)) {
				// Show the error HBox
				hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
				hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
				vboxForLastEyeExam.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}
		});
		lastEyeExamComboBox.setStyle("-fx-background-color: white");
		lastEyeExamComboBox.getItems().addAll("--Select--", "Within 9 months", "9 to 15 months", "15 months to 2 years",
				"2 to 5 years", "More than 5 years", "Never");
		// lastEyeExamComboBox.setValue(lastEyeExam);
		// lastEyeExamComboBox.setPrefSize(260, 5);
		lastEyeExamComboBox.setPrefWidth(screenWidth*18);
		vboxForLastEyeExam.getChildren().addAll(lastEyeExamHBox, lastEyeExamComboBox,
				hboxContainingLastEyeExamLabelandHyperLink);
		///// for USUAL BLOOD SUGAR LEVEL
		Label usualBloodSugalrLabel = new Label("Usual Blood Sugar label");
		usualBloodSugalrLabel.setStyle("-fx-font-weight:bold");
		Label errorLabelUsualBloodSugarLabel = new Label();
		HBox hboxUsualBloodSugarLabel = new HBox(2);
		hboxUsualBloodSugarLabel.getChildren().addAll(usualBloodSugalrLabel, errorLabelUsualBloodSugarLabel);
		highUsualBloodSugar = new TextField();
		highUsualBloodSugar.setPromptText("High");
		highUsualBloodSugar.setPrefWidth(screenWidth*.05);
		if (bloodSugarLevelHigh != null) {
			highUsualBloodSugar.setText(bloodSugarLevelHigh);
		}

		lowUsualBloodSugar = new TextField();
		if (bloodSugarLevelLow != null) {
			lowUsualBloodSugar.setText(bloodSugarLevelLow);
		}

		lowUsualBloodSugar.setPromptText("Low");
		averageUsualBloodSugar = new TextField();
		lowUsualBloodSugar.setPrefWidth(screenWidth*.05);
		if (bloodSugarLevelAvg != null) {
			averageUsualBloodSugar.setText(bloodSugarLevelAvg);
		}

		averageUsualBloodSugar.setPromptText("Average");
		errorLabelUsualBloodSugarLabel.setVisible(false);
		averageUsualBloodSugar.setPrefWidth(screenWidth*.05);
		
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
		yearWithDiabetesLabel.setStyle("-fx-font-weight:bold;");
		// Add an asterisk in red color
		Label asteriskLabelForYearWithDiabetes = new Label("*");
		asteriskLabelForYearWithDiabetes.setStyle("-fx-text-fill: red;");
		HBox yearWithDiabetesHBox = new HBox(1);
		yearWithDiabetesHBox.setPadding(new Insets(0, 0, 0, 7));
		yearWithDiabetesHBox.getChildren().addAll(yearWithDiabetesLabel, asteriskLabelForYearWithDiabetes);
		yearWithDiabatesComboBox = new ComboBox<String>();
		// yearWithDiabatesComboBox.setPrefSize(265, 5);
//		yearWithDiabatesComboBox.setPrefWidth(265);
		yearWithDiabatesComboBox.setPrefWidth(screenWidth*.18);

		yearWithDiabatesComboBox.getItems().addAll("--Select--", "Not diabetic", "Borderline", "Borderline Diabetic",
				"Gestational", "1 year or less", "2 years", "3 years", "4 years", "5 years", "6-10 years",
				"11-15 years", "16-20 years", "More than 20 years");
		if (yearsWithDiabeties != null) {
			if (yearsWithDiabeties.equals("Unknown")) {
				yearWithDiabatesComboBox.setValue("--Select--");
			} else if (yearsWithDiabeties.equals("Border line")) {
				yearWithDiabatesComboBox.setValue("Borderline");
			} else {
				yearWithDiabatesComboBox.setValue(yearsWithDiabeties);
			}
		}
		if (yearsWithDiabeties == null) {
			yearWithDiabatesComboBox.setValue("--Select--");
		}

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
				hboxContaingerrorLabelAndHyperLink.setVisible(false);
				hboxContaingerrorLabelAndHyperLink.setManaged(false);
				vboxForYearOfDiabates.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}

		});
		reasonForConsultation = new TextField();
		reasonForConsultation.setPrefSize(screenWidth*.17,50 );
		reasonForConsultation.setVisible(false);
		reasonForConsultation.setManaged(false);
		reasonForConsultation.setPromptText("Reason for Consultantion");
		if (reasonForConsultation != null) {
			reasonForConsultation.setText(reasonRetinalConsult);
		}

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
				errorReasonForConsultLabel.setVisible(false);
				errorReasonForConsultLabel.setManaged(false);
				reasonForConsultation.setStyle("-fx-border-color:black;-fx-border-radius:3;");
			}
		});

		//reasonForConsultation.setPrefSize(200, 45);
		//reasonForConsultation.setPrefSize(screenWidth*.15,50);
		vboxForYearOfDiabates.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		// vboxForYearOfDiabates.setPrefSize(260, 30);

		// diabetic type vbox
		diabaticTypeVBox = new VBox(5);
		diabaticTypeVBox.setVisible(false);
		diabaticTypeVBox.setManaged(false);
		Label diabeticTypeLabel = new Label("Diabetic Type"); // Label without asterisk
		diabeticTypeLabel.setStyle("-fx-font-weight:bold");
		// Add an asterisk in red color
		Label diabeticasteriskLabel = new Label("*");
		diabeticasteriskLabel.setStyle("-fx-text-fill: red;");
		HBox diabeticTypeHBox = new HBox(1);
		diabeticTypeHBox.setPadding(new Insets(0, 0, 0, 7));
		diabeticTypeHBox.getChildren().addAll(diabeticTypeLabel, diabeticasteriskLabel);
		diabeticTypeComboBox = new ComboBox<>();
		diabeticTypeComboBox.setStyle("-fx-border-width: 0;-fx-border-color:white;-fx-background-color:white;");
		//diabeticTypeComboBox.setPrefSize(265, 5);
		diabeticTypeComboBox.setPrefWidth(screenWidth*.18);
		diabeticTypeComboBox.getItems().addAll("Type-1", "Type-2", "Other");
		diabeticTypeComboBox.setValue("Type-1");
		diabeticTypeErrorlbel = new Label("Please select diabetic type");
		diabeticTypeErrorlbel.setPadding(new Insets(0, 0, 0, 7));
		diabeticTypeErrorlbel.setManaged(false);
		diabeticTypeErrorlbel.setVisible(false);
		diabeticTypeErrorlbel.setStyle(
				"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
		diabaticTypeVBox.getChildren().addAll(diabeticTypeHBox, diabeticTypeComboBox, diabeticTypeErrorlbel);
		diabaticTypeVBox
				.setStyle(" -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;-fx-border-radius: 5;");

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
		insulinTypelabel.setStyle("-fx-font-weight:bold");
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

		insulinTypeComboBox.setStyle("-fx-border-width: 0;-fx-border-color:white;-fx-background-color: white;");
		//insulinTypeComboBox.setPrefSize(240, 5);
		insulinTypeComboBox.setPrefWidth(screenWidth*.18);

		insulinTypeComboBox.getItems().addAll("--Select--", "1 year or less", "2 years", "3 years", "4 years",
				"5 years", "6-10 years", "11-15 years", "16-20 years", "More than 20 years");
		insulinVBox.getChildren().addAll(insulinDurationHBox, insulinTypeComboBox, insulinerror);

		if (duration != null) {
			if (duration.equals("Unknown")) {
				insulinTypeComboBox.setValue("--Select--");
			} else {
				insulinTypeComboBox.setValue(duration);
			}
		}
		if (duration == null) {
			insulinTypeComboBox.setValue("--Select--");
		}
		// Add a listener for insulinTypeComboBox
		insulinTypeComboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue.equals("--Select--")) {
				// Hide the error label if the value is valid (not "---Select---")
				insulinerror.setVisible(false);
				insulinerror.setManaged(false);
				insulinVBox.setStyle("-fx-border-color:black;-fx-border-radius:5;");
			}

		});

		insulinVBox
				.setStyle(" -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;-fx-border-radius: 5;");
		diabaticTypeTextfield = new TextField();
		diabaticTypeTextfield.setPrefSize(screenWidth*.17, 50); 
		diabaticTypeTextfield.setVisible(false);
		diabaticTypeTextfield.setPromptText("Please Mention Type");
		diabaticTypeTextfield.setManaged(false);
		diabaticTypeTextfield
				.setStyle("-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;-fx-border-radius: 5;");
		if (yearWithDiabatesComboBox.getValue() != null) {
			if (yearWithDiabatesComboBox.getValue().equals("Not diabetic")) {
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
			} else if (yearWithDiabatesComboBox.getValue().equals("--Select--")) {
				insulinVBox.setVisible(false);
				insulinVBox.setManaged(false); // Does not take up space when hidden
				diabaticTypeVBox.setVisible(false);
				diabaticTypeVBox.setManaged(false); // Does not take up space when hidden
				diabaticTypeTextfield.setVisible(false);
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
		}
		/***
		 * adding an action for yearOfDiabetesCombo Box
		 */
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
		cholestaorolLabel.setStyle("-fx-font-weight:bold");

		HBox cholestaorolErrorLabel = new HBox(2);
		cholestaorolErrorLabel.getChildren().addAll(cholestaorolLabel, cholestorolLabel);
//Create a TextField with a placeholder text
		cholestorolTextField = new TextField();
		cholestorolTextField.setPromptText("Type Here");
		if (cholestrol != null) {
			cholestorolTextField.setText(cholestrol);

		}

		cholestorolTextField
				.setStyle("-fx-background-color:white;-fx-border-color:white;-fx-border-radius:0;-fx-border-width:0;");
		vboxCholerstrol.getChildren().addAll(cholestaorolErrorLabel, cholestorolTextField);
		addRealTimeValidation(cholestorolTextField, cholestorolLabel, vboxCholerstrol, false);

		VBox vboxtrigy = new VBox();
		vboxtrigy.setStyle(
				"-fx-background-color: white;-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		Label trigyLabel = new Label("Triglycerides");
		trigyLabel.setStyle("-fx-font-weight:bold");
		Label trigyerrorLabel = new Label();
		HBox hboxTriglyError = new HBox(2);
		hboxTriglyError.getChildren().addAll(trigyLabel, trigyerrorLabel);
		trigyLabel.setPadding(new Insets(0, 0, 0, 7));

		trigyTextField = new TextField();
		if (triglycerides != null) {
			trigyTextField.setText(triglycerides);
		}

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
		parentsCheckBox.setPadding(new Insets(0, 0, 0, 15));
		siblingsCheckBox = new CheckBox("Siblings");
		notSureCheckBox = new CheckBox("Not Sure");
		// Set the checkbox based on the glaucomaHistory value
		
		if (glaucomaHistory != null && !glaucomaHistory.isEmpty()) {
			// Split the glaucomaHistory string by commas and trim any extra whitespace
			String[] selectedValues = glaucomaHistory.split(",");

			// Check if "No" is present in the selected values
			boolean noSelected = Arrays.stream(selectedValues).map(String::trim).anyMatch("No"::equalsIgnoreCase);

			if (noSelected) {
				// Disable and unselect all other checkboxes if "No" is selected
				noCheckBox.setSelected(true);
				selfCheckBox.setDisable(true);
				parentsCheckBox.setDisable(true);
				siblingsCheckBox.setDisable(true);
				notSureCheckBox.setDisable(true);

				selfCheckBox.setSelected(false);
				parentsCheckBox.setSelected(false);
				siblingsCheckBox.setSelected(false);
				notSureCheckBox.setSelected(false);
			} else {
				// Otherwise, check and select the corresponding checkboxes
				for (String value : selectedValues) {
					value = value.trim();
					if (value.equalsIgnoreCase("Self")) {
						selfCheckBox.setSelected(true);
					} else if (value.equalsIgnoreCase("Parents")) {
						parentsCheckBox.setSelected(true);
					} else if (value.equalsIgnoreCase("Siblings")) {
						siblingsCheckBox.setSelected(true);
					} else if (value.equalsIgnoreCase("Not Sure")) {
						notSureCheckBox.setSelected(true);
					}
				}
			}
		}

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
		HBox firstLine = new HBox(5); // Adding 10px spacing between checkboxes in the first line
		firstLine.setPadding(new Insets(0, 0, 0, 3)); // Adding padding around the first line
		HBox secondLine = new HBox(5); // Adding 10px spacing between checkboxes in the second line
		secondLine.setPadding(new Insets(0, 0, 0, 3)); // Adding padding around the second line

		// Add CheckBoxes to the HBoxes
		firstLine.getChildren().addAll(selfCheckBox, noCheckBox, parentsCheckBox);
		secondLine.getChildren().addAll(notSureCheckBox, siblingsCheckBox);

		// Create a VBox to hold the HBoxes with spacing between lines
		VBox checkBoxesVBox = new VBox(2); // Adding 10px spacing between lines
		checkBoxesVBox.getChildren().addAll(firstLine, secondLine);
		Label glaucomaHistoryLabel = new Label("Glaucoma History"); // Label without asterisk
		glaucomaHistoryLabel.setStyle("-fx-font-weight:bold");
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
		// Create the Label
		Label pregnantLabel = new Label("Pregnant Now/Within 6 Months");
		// Create the RadioButtonsyear
		RadioButton unKnownButton = new RadioButton("UnKnown");
		RadioButton yesButton = new RadioButton("Yes");
		RadioButton noButton = new RadioButton("No");
		if (pregnany != null) {
			if (pregnany.equals("yes")) {
				yesButton.setSelected(true);
				noButton.setSelected(false);
			} else if (pregnany.equals("Unknown")) {
				unKnownButton.setSelected(true);
				yesButton.setSelected(false);
				noButton.setSelected(false);

			} else if (pregnany.equals("no")) {
				noButton.setSelected(true);
				yesButton.setSelected(false);
				unKnownButton.setSelected(false);
			}
		}

		// Create a ToggleGroup to make the RadioButtons mutually exclusive
		ToggleGroup toggleGroup = new ToggleGroup();
		yesButton.setToggleGroup(toggleGroup);
		noButton.setToggleGroup(toggleGroup);
		unKnownButton.setToggleGroup(toggleGroup);
		HBox hboxForpregnancy = new HBox(5);
		hboxForpregnancy.getChildren().addAll(unKnownButton, yesButton, noButton);

		vboxforPregnancy = new VBox(5); // 10 is the spacing between elements
		vboxforPregnancy.setVisible(false);
		vboxforPregnancy.setPadding(new Insets(2));

		vboxforPregnancy.getChildren().addAll(pregnantLabel, hboxForpregnancy);
		vboxforPregnancy.setPrefSize(screenWidth*.18, 50);
		vboxforPregnancy.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		if (personaldetails != null) {
			ComboBox<String> genderComboBox = personaldetails.getGenderComboBox();

			// Check if the ComboBox is not null
			if (genderComboBox != null) {
				String selectedGender = genderComboBox.getValue(); // Get the selected value

				if ("Female".equals(selectedGender)) { // Use equals on the string literal to avoid NullPointerException
					if (vboxforPregnancy != null) { // Check if vboxforPregnancy is not null
						vboxforPregnancy.setVisible(true);
						vboxforPregnancy.setManaged(true);
					}
				} else {
					if (vboxforPregnancy != null) { // Check if vboxforPregnancy is not null
						vboxforPregnancy.setVisible(false);
						vboxforPregnancy.setManaged(false);
					}
				}
			} else {
				// Handle the case where genderComboBox is null if needed
				System.err.println("Gender ComboBox is null");
			}
		}
		if (personalDetailsToAddPAtient != null) {
			personalDetailsToAddPAtient.getGenderComboBox().setOnAction(e -> {
				if (personalDetailsToAddPAtient.getGenderComboBox().getValue().equals("Female")) {
					// Add your logic here for when the selected gender is "Female"
					vboxforPregnancy.setManaged(true);
					vboxforPregnancy.setVisible(true);
				} else {
					// Add your logic here for when the selected gender is "Female"
					vboxforPregnancy.setManaged(false);
					vboxforPregnancy.setVisible(false);
				}
			});
		}
		/*
		 * HyperTension
		 */

		VBox vboxForHyperTension = new VBox();
		Label hyperTensionLabel = new Label("Hypertension");
		hyperTensionLabel.setStyle("-fx-font-weight:bold");
		// hyperTensionLabel.setAlignment(Pos.TOP_CENTER);
		hyperTensionLabel.setPadding(new Insets(0, 0, 0, 7));

		hyperTensionComboBox = new ComboBox<>();
		hyperTensionComboBox.setPrefWidth(screenWidth*.18);

		// Add items to the ComboBox
		hyperTensionComboBox.getItems().addAll("--Select--", "No", "Controlled", "Uncontrolled", "Unknown");
		if (hypertension != null) {
			if (hypertension.equals("no")) {
				hyperTensionComboBox.setValue("No");
			} else if (hypertension.equals("controlled")) {
				hyperTensionComboBox.setValue("Controlled");
			} else if (hypertension.equals("uncontrolled")) {
				hyperTensionComboBox.setValue("Uncontrolled");
			} else if (hypertension.equals("unknown")) {
				hyperTensionComboBox.setValue("Unknown");
			} else {
				hyperTensionComboBox.setValue(hypertension);
			}
		}
		if (hypertension == null) {
			hyperTensionComboBox.setValue("--Select--");
		}
		hyperTensionComboBox.setStyle("-fx-background-color: white;");
		vboxForHyperTension.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		vboxForHyperTension.getChildren().addAll(hyperTensionLabel, hyperTensionComboBox);

		// gridPane.add(vboxForHyperTension, 0, 7);

		VBox vboxmedication = new VBox();

		// Create a Label for "Medication"
		Label medicationLabel = new Label("Medications");
		medicationLabel.setStyle("-fx-font-weight:bold;");
		medicationLabel.setPadding(new Insets(0, 0, 0, 7));

		// Create a TextArea with placeholder text
		 medicationTextArea = new TextArea();
		if (medications != null) {
			medicationTextArea.setText(medications);
		}

		medicationTextArea.setPrefWidth(screenWidth*.18);
		medicationTextArea.setPrefHeight(60);
		medicationTextArea.setPromptText("Type Here");
		medicationTextArea.setWrapText(true); // Ensure text wraps to the next line
		medicationTextArea.setStyle("-fx-background-color:white; -fx-border-color: transparent;");

		vboxmedication.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		// Add the TextArea and Label to the VBox
		vboxmedication.getChildren().addAll(medicationLabel, medicationTextArea);

		// Add VBox to the GridPane

////SUBJECTIVE DIABATIC CONTROL
		VBox vboxForSubjectiveDiabaticControl = new VBox();
		Label subjectiveDiabaticControllLabel = new Label("Subjective Diabetic Control");
		subjectiveDiabaticControllLabel.setStyle("-fx-font-weight:bold;");
		subjectiveDiabaticControllLabel.setPadding(new Insets(0, 0, 0, 7));
		subjectiveDiabticControlComboBox = new ComboBox<>();

		subjectiveDiabticControlComboBox.setStyle("-fx-background-color: white;");
//Add items to the ComboBox
		subjectiveDiabticControlComboBox.getItems().addAll("--Select--", "Excellent", "Good", "Moderate", "Fair",
				"Poor");
		if (subjectiveDiabeticControl != null)

		{
			if (subjectiveDiabeticControl.equals("Unknown")) {
				subjectiveDiabticControlComboBox.setValue("--Select--");
			} else {
				subjectiveDiabticControlComboBox.setValue(subjectiveDiabeticControl);
			}
		}

		if (subjectiveDiabeticControl == null) {
			subjectiveDiabticControlComboBox.setValue("--Select--");
		}
		subjectiveDiabticControlComboBox.setPrefSize(screenWidth*.18, 5);
		subjectiveDiabticControlComboBox.setStyle("-fx-background-color: white;");

		vboxForSubjectiveDiabaticControl.getChildren().addAll(subjectiveDiabaticControllLabel,
				subjectiveDiabticControlComboBox);

		vboxForSubjectiveDiabaticControl.setStyle(
				"-fx-background-color: white; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		//// MOST RECENT BLOOD TEST
		Label mostRecentLabel = new Label("Most Recent Blood Test");
		mostRecentLabel.setStyle("-fx-font-weight:bold;");
		yearMostRecentBloodTest = new ComboBox<>();
		if (bloodYear != null) {
			yearMostRecentBloodTest.setValue(bloodYear);
		}

		yearMostRecentBloodTest.getItems().addAll("2021", "2022", "2023", "2024");
		yearMostRecentBloodTest
				.setStyle("-fx-background-color: transparent;-fx-border-color:black;-fx-border-radius:5;");
		monthRecentBloodTest = new ComboBox<>();
		monthRecentBloodTest.setStyle("-fx-background-color:white;-fx-border-color:black;-fx-border-radius:5;");
		monthRecentBloodTest.getItems().addAll("--","Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct",
				"Nov", "Dec");
		if (bloodYearMonth != null) {
			monthRecentBloodTest.setValue(bloodYearMonth);
		}

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
		otherInformationVbox.setPrefWidth(screenWidth*.270);
		// Create a Label for "Haemoglobin"
		Label otherInformationLabel = new Label(
				"Other  history Or Information including symptoms, onset, \nand duration");
		otherInformationLabel.setStyle("-fx-font-weight:bold;");
		otherInformationLabel.setPadding(new Insets(0, 0, 0, 0));
		// Create a TextField with a placeholder text
		 otherHistoryTextField = new TextArea();
		if (otherHistory != null) {
			otherHistoryTextField.setText(otherHistory);
		}

		otherHistoryTextField.setPrefWidth(screenWidth*.27);
		otherHistoryTextField.setPrefHeight(60);
		otherHistoryTextField.setPromptText("Type Here");
		// otherHistoryTextField.setText(caseHistory);
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
		 rightIopTextField = new TextField();
		if (iopRight != null) {
			rightIopTextField.setText(iopRight);
		}

		rightIopTextField.setPrefWidth(screenWidth*.03);
		// Error label for both text fields

		iopErrorLabel.setStyle("-fx-text-fill: red;");
		iopErrorLabel.setVisible(false); // Initially hidden
		Label leftEyeIop = new Label("Left Eye");
		leftEyeIop.setPadding(new Insets(0, 9, 0, 0));
		 leftEyeTextField = new TextField();
		if (iopLeft != null) {
			leftEyeTextField.setText(iopLeft);
		}

		leftEyeTextField.setPrefWidth(screenWidth*.03);
		// TextField validation for numeric input

		HBox hboxForIop = new HBox(2);
		hboxForIop.getChildren().addAll(rightEyeIopLabel, rightIopTextField);
		HBox leftEyeIopHbox = new HBox(2);
		leftEyeIopHbox.getChildren().addAll(leftEyeIop, leftEyeTextField);
		VBox vboxForIop = new VBox(8);
		vboxForIop.setPrefWidth(screenWidth*.10);
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
		/* TextField rightEyeField = new TextField(); */
		 visualAquityRightEyeComboBox = new ComboBox<String>();
		visualAquityRightEyeComboBox
				.setStyle("-fx-background-color: transparent;-fx-border-color:black;-fx-border-radius:5;");
		visualAquityRightEyeComboBox.getItems().addAll("20/10", "20/15", "20/20", "20/25", "20/30", "20/40", "20/50",
				"20/60", "20/70", "20/80", "20/100", "20/200", "20/400", "CF 1'", "CF 2'", "CF 3'", "CF 5'", "CF 10'",
				"HM", "LP", "NLP", "6/3", "6/4.8", "6/5", "6/6", "6/7.5", "6/8", "6/9.5", "6/10", "6/12", "6/15",
				"6/19", "6/24", "6/30", "6/38", "6/48", "6/60", "6/190", "-0.30 LogMAR", "-0.20 LogMAR", "-0.10 LogMAR",
				"0.00 LogMAR", "0.10 LogMAR", "0.20 LogMAR", "0.30 LogMAR", "0.40 LogMAR", "0.50 LogMAR", "0.60 LogMAR",
				"0.70 LogMAR", "0.80 LogMAR", "0.90 LogMAR", "1.00 LogMAR", "1.5 LogMAR", "2.0 LogMAR");
		Label leftEyeLabel = new Label("Left Eye");
		visualAquityRightEyeComboBox.setValue("20/10");
		leftEyeLabel.setPadding(new Insets(0, 10, 0, 0));
		// TextField leftEyeField = new TextField();
		visualAquityLeftEyeComboBox = new ComboBox<String>();
		visualAquityLeftEyeComboBox
				.setStyle("-fx-background-color: transparent;-fx-border-color:black;-fx-border-radius:5;");
		visualAquityLeftEyeComboBox.getItems().addAll("20/10", "20/15", "20/20", "20/25", "20/30", "20/40", "20/50",
				"20/60", "20/70", "20/80", "20/100", "20/200", "20/400", "CF 1'", "CF 2'", "CF 3'", "CF 5'", "CF 10'",
				"HM", "LP", "NLP", "6/3", "6/4.8", "6/5", "6/6", "6/7.5", "6/8", "6/9.5", "6/10", "6/12", "6/15",
				"6/19", "6/24", "6/30", "6/38", "6/48", "6/60", "6/190", "-0.30 LogMAR", "-0.20 LogMAR", "-0.10 LogMAR",
				"0.00 LogMAR", "0.10 LogMAR", "0.20 LogMAR", "0.30 LogMAR", "0.40 LogMAR", "0.50 LogMAR", "0.60 LogMAR",
				"0.70 LogMAR", "0.80 LogMAR", "0.90 LogMAR", "1.00 LogMAR", "1.5 LogMAR", "2.0 LogMAR");
		visualAquityLeftEyeComboBox.setValue("20/10");
		HBox righteyeaquity = new HBox(4);
		iopErrorLabelVisualAquity.setStyle("-fx-text-fill: red;");
		iopErrorLabelVisualAquity.setVisible(false); // Initially hidden
		righteyeaquity.getChildren().addAll(rightEyeLabel, visualAquityRightEyeComboBox);

		HBox leftEyeEquity = new HBox(4);
		leftEyeEquity.getChildren().addAll(leftEyeLabel, visualAquityLeftEyeComboBox);

// Pinhole Radio Buttons

		Label pinholeLabel = new Label("Pinhole");
		pinholeLabel.setPadding(new Insets(0, 10, 0, 0));
		ToggleGroup pinholeGroup = new ToggleGroup();
		 pinholeYes = new RadioButton("Yes");
		pinholeYes.setToggleGroup(pinholeGroup);
		 pinholeNo= new RadioButton("No");
		pinholeNo.setToggleGroup(pinholeGroup);
		HBox pinHoleHBox = new HBox(3);
		pinHoleHBox.getChildren().addAll(pinholeLabel, pinholeYes, pinholeNo);
		if (pinhole != null) {
			if (pinhole.equalsIgnoreCase("true")) {
				pinholeYes.setSelected(true);
			} else if (pinhole.equalsIgnoreCase("false")) {
				pinholeNo.setSelected(true);
			}
		}

// With Rx Radio Buttons
		Label withRxLabel = new Label("With Rx");
		withRxLabel.setPadding(new Insets(0, 9, 0, 0));
		ToggleGroup withRxGroup = new ToggleGroup();
		 withRxYes = new RadioButton("Yes");
		withRxYes.setToggleGroup(withRxGroup);
		 withRxNo = new RadioButton("No");
		withRxNo.setToggleGroup(withRxGroup);

		if (withRx != null) {
			if (withRx.equalsIgnoreCase("true")) {
				withRxYes.setSelected(true);
				withRxNo.setSelected(false); // Unselect "No" checkbox
			} else if (withRx.equalsIgnoreCase("false")) {
				withRxYes.setSelected(false); // Unselect "Yes" checkbox
				withRxNo.setSelected(true);
			}
		}

		HBox rxLabel = new HBox(4);
		rxLabel.getChildren().addAll(withRxLabel, withRxYes, withRxNo);
		VBox visualaquityVbox = new VBox(3);
		visualaquityVbox.getChildren().addAll(hboxVisualAquity, righteyeaquity, leftEyeEquity, pinHoleHBox, rxLabel);
		HBox hboxContainingIopAndOtherInformation = new HBox(5);
		hboxContainingIopAndOtherInformation.setPrefWidth(.36*screenWidth);
		hboxContainingIopAndOtherInformation.getChildren().addAll(vboxForIop, otherInformationVbox);
		VBox vboxPupilDilation = new VBox(5);
	//	vboxPupilDilation.getChildren().addAll(vboxForPupilDilation, insulinVBox);
		VBox vboxForyearwithDiab = new VBox(5);
		//vboxForyearwithDiab.getChildren().addAll(vboxForYearOfDiabates, diabaticTypeVBox);
	//	VBox vboxForConsultaion = new VBox(5);
		//vboxForConsultaion.getChildren().addAll(reasonForConsultVBox, diabaticTypeTextfield);
		//h1.setSpacing(1);
	//	h2.setSpacing(1);
		vboxPupilDilation.setPadding(new Insets(4));
		vboxPupilDilation.setStyle("-fx-border-color:black;-fx-border-radius:2;");
		//h1.getChildren().addAll(vboxForPupilDilation, vboxForYearOfDiabates, reasonForConsultVBox);
	//	h2.getChildren().addAll(insulinVBox, diabaticTypeVBox, diabaticTypeTextfield);
		diabaticTypeTextfield.setVisible(false);
		diabaticTypeTextfield.setManaged(false);
		visualaquityVbox.setPadding(new Insets(4));
		visualaquityVbox.setStyle("-fx-border-color:black;-fx-border-radius:2;");
		// v1.setSpacing(10);
	//	v1.getChildren().addAll(h1, h2);
		medicalrecordGridPane.add(vboxForPupilDilation,0,0);
		medicalrecordGridPane.add(vboxForYearOfDiabates,1,0);
		medicalrecordGridPane.add(reasonForConsultVBox,2,0);
		medicalrecordGridPane.add(insulinVBox,0,1);
		medicalrecordGridPane.add(diabaticTypeVBox,1,1);
		medicalrecordGridPane.add(diabaticTypeTextfield,2,1);
		//medicalrecordGridPane.add(v1, 0, 1);
	//	GridPane.setColumnSpan(v1, 3);
		// medicalrecordGridPane.add(vboxPupilDilation,0,0);
		// medicalrecordGridPane.add(vboxForyearwithDiab, 1, 0);
		// medicalrecordGridPane.add(vboxForConsultaion, 2, 0);
//		medicalrecordGridPane.setPrefHeight(Region.USE_COMPUTED_SIZE);

		medicalrecordGridPane.add(vboxForHaemoglobin, 0, 5);
		vboxForHaemoglobin.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxForLastEyeExam, 1, 3);
		vboxForLastEyeExam.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxUsualBloodSugarLabel, 0, 4);
		vboxUsualBloodSugarLabel.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxCholerstrol, 1, 5);
		vboxCholerstrol.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxtrigy, 1, 4);
		vboxtrigy.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxForGlaucomaHistory, 0, 3);
		vboxForGlaucomaHistory.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxforPregnancy, 2, 5);
		vboxforPregnancy.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxForHyperTension, 0, 6);
		vboxForHyperTension.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxmedication, 0, 8);
		vboxmedication.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxForSubjectiveDiabaticControl, 0, 7);
		vboxForSubjectiveDiabaticControl.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(visualaquityVbox, 1, 7);
		visualaquityVbox.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(vboxForMostRecentBloodTest, 1, 6);
		vboxForMostRecentBloodTest.setPrefWidth(screenWidth*.18);
		medicalrecordGridPane.add(hboxContainingIopAndOtherInformation, 0, 9);
		//vboxForMostRecentBloodTest.setPrefWidth(screenWidth*.3);
		GridPane.setColumnSpan(hboxContainingIopAndOtherInformation, 2);
		GridPane.setRowSpan(visualaquityVbox, 2);
//		medicalrecordGridPane.add(vboxForHaemoglobin, 1, 4);
//		vboxForHaemoglobin.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxForLastEyeExam, 2, 2);
//		vboxForLastEyeExam.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxUsualBloodSugarLabel, 1, 3);
//		vboxUsualBloodSugarLabel.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxCholerstrol, 2, 4);
//		vboxCholerstrol.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxtrigy, 2, 3);
//		vboxtrigy.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxForGlaucomaHistory, 1, 2);
//		vboxForGlaucomaHistory.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxforPregnancy, 3, 4);
//		vboxforPregnancy.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxForHyperTension, 1, 5);
//		vboxForHyperTension.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxmedication, 1, 7);
//		vboxmedication.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxForSubjectiveDiabaticControl, 1, 6);
//		vboxForSubjectiveDiabaticControl.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(visualaquityVbox, 2, 6);
//		visualaquityVbox.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(vboxForMostRecentBloodTest, 2, 5);
//		vboxForMostRecentBloodTest.setPrefWidth(screenWidth*.18);
//		medicalrecordGridPane.add(hboxContainingIopAndOtherInformation, 1, 8);
//		GridPane.setColumnSpan(hboxContainingIopAndOtherInformation, 2);
//		GridPane.setRowSpan(visualaquityVbox, 2);
		return medicalrecordGridPane;

	}

	/***
	 * adding real time validation
	 * 
	 * @param textField
	 * @param errorLabel
	 * @param vbox
	 * @param allowDecimals
	 */
	private void addRealTimeValidation(TextField textField, Label errorLabel, VBox vbox, boolean allowDecimals) {
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
	private void showAlert(String title, String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setHeight(200);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

	private static boolean anyCheckBoxesSelected(CheckBox... checkBoxes) {
		for (CheckBox check : checkBoxes) {
			if (check.isSelected()) {
				return true;
			}
		}
		return false;
	}

	// validating medical fields
	public boolean validateMedicalField(MedicalDetails medicalDetails) {
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
		});
		if (yearOfDiabSkipped && lastEyeExamSkipped && glaucomaHistorySkipped) {
			return isValid; // All skip flags are true, skip further validation
		}

		// Validate the pupilDilation ComboBox
		if (medicalDetails != null) {
			medicalDetails.pupilDilationComboBox.getValue().equals("-------");
			pupilDilationErrorLabel.setVisible(false);
			pupilDilationErrorLabel.setManaged(false);

			pupilDilationErrorLabel.setStyle(
					"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
			// vboxForPupilDilation.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			isValid = true;
		} else {
			pupilDilationComboBox.getValue().equals("-------");
			pupilDilationErrorLabel.setVisible(true);
			pupilDilationErrorLabel.setManaged(true);

			pupilDilationErrorLabel.setStyle(
					"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
			vboxForPupilDilation.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			isValid = false;
		}

		if (!yearOfDiabSkipped) {
			if (medicalDetails != null) {
				if (medicalDetails.yearWithDiabatesComboBox.getValue().equals("--Select--")) {
					hboxContaingerrorLabelAndHyperLink.setVisible(false);
					hboxContaingerrorLabelAndHyperLink.setManaged(false);
					// vboxForYearOfDiabates.setStyle("-fx-border-color:red;-fx-border-radius:5;");
					isValid = true;
				}
			} else {
				if (yearWithDiabatesComboBox.getValue() == null
						|| yearWithDiabatesComboBox.getValue().equals("--Select--")) {
					hboxContaingerrorLabelAndHyperLink.setVisible(true);
					hboxContaingerrorLabelAndHyperLink.setManaged(true);
					vboxForYearOfDiabates.setStyle("-fx-border-color:red;-fx-border-radius:5;");
					isValid = false;
				}
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

		if (medicalDetails != null) {
			medicalDetails.insulinTypeComboBox.getValue().equals("--Select--");

			insulinerror.setVisible(false);
			insulinerror.setManaged(false);
			isValid = true;

		} else {
			insulinTypeComboBox.getValue().equals("--Select--");
			insulinerror.setVisible(true);
			insulinerror.setManaged(true);
			insulinVBox.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			insulinerror.setStyle(
					"-fx-font-family: 'Arial'; -fx-text-fill: red; -fx-font-style: italic; -fx-font-size: 10; -fx-font-weight: bold;");
			isValid = false;

		}

		if (!lastEyeExamSkipped) {
			if (medicalDetails != null) {
				if (medicalDetails.lastEyeExamComboBox.getValue().equals("--Select--")) {
					hboxContainingLastEyeExamLabelandHyperLink.setVisible(false);
					hboxContainingLastEyeExamLabelandHyperLink.setManaged(false);
					// vboxForLastEyeExam.setStyle("-fx-border-color:red;-fx-border-radius:5;");
					isValid = true;
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
			} else {
				if (lastEyeExamComboBox.getValue().equals("--Select--")) {
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
		}
		/**
		 * validating diabertic type vbox
		 */
		if (diabeticTypeComboBox.getValue().equals("Select diabetic type")) {
			diabeticTypeErrorlbel.setVisible(true);
			diabeticTypeErrorlbel.setManaged(true);
			diabaticTypeVBox.setStyle("-fx-border-color:red;-fx-border-radius:5;");
			isValid = false;
		}

		return isValid;
	}

	/***
	 * method to validate the changes in medical details form
	 * 
	 * @param pupilDilation
	 * @param yearsWithDiabeties
	 * @param duration
	 * @param reasonRetinalConsult
	 * @param glaucomaHistory
	 * @param lastEyeExam
	 * @param bloodSugarLevelHigh
	 * @param bloodSugarLevelLow
	 * @param bloodSugarLevelAvg
	 * @param triglycerides
	 * @param hemoglobin
	 * @param cholestrol
	 * @param pregnant
	 * @param hypertension
	 * @param bloodDrawMonth
	 * @param bloodDrawYear
	 * @param subjectiveDiabeticControl
	 * @param medications
	 * @param otherHistory
	 * @param visualAcuityRight
	 * @param pinhole
	 * @param withRx
	 * @param iopRight
	 * @param iopLeft
	 * @param visualAcuityLeft 
	 * @return boolean based on condition
	 */
	public boolean checkForMedicalDetailschangeForCurrentPatientClicked(String pupilDilation, String yearsWithDiabeties,
			String duration, String reasonRetinalConsult, String glaucomaHistory, String lastEyeExam,
			String bloodSugarLevelHigh, String bloodSugarLevelLow, String bloodSugarLevelAvg, String triglycerides,
			String hemoglobin, String cholestrol, String pregnant, String hypertension, String bloodDrawMonth,
			String bloodDrawYear, String subjectiveDiabeticControl, String medications, String otherHistory,
			String visualAcuityRight, String pinhole, String withRx, String iopRight, String iopLeft, String visualAcuityLeft) {
		System.out.println("prev pupil dialtion " + pupilDilation);
		System.out.println("year with diabetes is "+yearsWithDiabeties);
		System.out.println("insulin duration is "+duration);
		System.out.println("reason for retainal consult is "+reasonRetinalConsult);
		System.out.println("glaucoma history is "+glaucomaHistory);
		System.out.println("last eye exam is "+lastEyeExam);
		System.out.println("visual aquity right is "+visualAcuityRight);
		System.out.println("visual aquity left is "+visualAcuityLeft);
		System.out.println("pin hole is "+pinhole);
		System.out.println("withRx is"+withRx);
		System.out.println("other history is "+otherHistory.isEmpty());
		System.out.println("ip right is "+iopRight);
		System.out.println("iop left is "+iopLeft);
	    System.out.println("medication is "+medications);
		System.out.println("with rx  is " + withRx);
		System.out.println("blood draw year is "+bloodDrawYear);
		System.out.println("blood draw month is"+bloodDrawMonth);
		System.out.println("subjective diabetic control is "+subjectiveDiabeticControl);
		System.out.println("hypertension value is "+hypertension);
		
		boolean isMedicalFormChanged = false;

		/*
		 * for pupil dilation field change
		 */
		if (pupilDilation.equalsIgnoreCase("Declined (please note reason drop were declined)")) {
			if (!pupilDilationComboBox.getValue()
					.equalsIgnoreCase("declined (please note reason drop were declined)")) {
				isMedicalFormChanged = true;
			}
		} else if (pupilDilation.equalsIgnoreCase("Other Dilating Agents (please note dilating agents used)")) {
			if (!pupilDilationComboBox.getValue()
					.equalsIgnoreCase("other dilating agents (please note dilating agents used)")) {
				isMedicalFormChanged = true;
			}
		} else if (pupilDilation.equalsIgnoreCase("Not Necessary")) {
			if (!pupilDilationComboBox.getValue().equalsIgnoreCase("not necessary")) {
				isMedicalFormChanged = true;
			}
		} else if (pupilDilation.equals("0")) {
			if (!pupilDilationComboBox.getValue().equals("-------")) {
				isMedicalFormChanged = true;
			}
		} else {
			if (!pupilDilationComboBox.getValue().equals(pupilDilation)) {
				isMedicalFormChanged = true;
			}
		}
		/*
		 * year of diabetes field change
	 */
		
		if ("Unknown".equals(yearsWithDiabeties)) {
			if (!yearWithDiabatesComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}
		} else if ("Border line".equals(yearsWithDiabeties)) {
			if (!yearWithDiabatesComboBox.getValue().equals("Borderline")) {
				isMedicalFormChanged = true;
			}
		} else if (yearsWithDiabeties != null) { // Check only if not null here
			if (!yearWithDiabatesComboBox.getValue().equals(yearsWithDiabeties)) {
				isMedicalFormChanged = true;
			}
		}

		// Default case if yearsWithDiabeties is null or not set
		if (yearsWithDiabeties == null || yearsWithDiabeties.isEmpty()) {
			if (!yearWithDiabatesComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}
		}
//		/*
//		 * insulin duration
//		 */
//
		if ("Unknown".equals(duration)) {
			if (!insulinTypeComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}

		} else if ("Border line".equals(duration)) {
			if (!insulinTypeComboBox.getValue().equals("Borderline")) {
				isMedicalFormChanged = true;
			}

		} else if (duration != null) {
			if (!insulinTypeComboBox.getValue().equals(duration)) {
				isMedicalFormChanged = true;
			}

		} else {
			if (!insulinTypeComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}
		}

		/*
		 * glaucoma history
		 */

		if (glaucomaHistory != null && !glaucomaHistory.isEmpty()) {
			// Split the glaucomaHistory string by commas and trim any extra whitespace
			String[] selectedValues = glaucomaHistory.split(",");

			// Check if "No" is present in the selected values
			boolean noSelected = Arrays.stream(selectedValues).map(String::trim).anyMatch("No"::equalsIgnoreCase);

			if (noSelected) {
				// Disable and unselect all other checkboxes if "No" is selected
				if (!noCheckBox.isSelected()) {
					isMedicalFormChanged = true;
				}
				noCheckBox.setSelected(true);
				selfCheckBox.setDisable(true);
				parentsCheckBox.setDisable(true);
				siblingsCheckBox.setDisable(true);
				notSureCheckBox.setDisable(true);

				// Unselect all other checkboxes
				selfCheckBox.setSelected(false);
				parentsCheckBox.setSelected(false);
				siblingsCheckBox.setSelected(false);
				notSureCheckBox.setSelected(false);
			} else {
				// Otherwise, check and select the corresponding checkboxes
				if (!selfCheckBox.isSelected()
						&& Arrays.stream(selectedValues).map(String::trim).anyMatch("Self"::equalsIgnoreCase)) {
					isMedicalFormChanged = true;
					selfCheckBox.setSelected(true);
				}
				if (!parentsCheckBox.isSelected()
						&& Arrays.stream(selectedValues).map(String::trim).anyMatch("Parents"::equalsIgnoreCase)) {
					isMedicalFormChanged = true;
					parentsCheckBox.setSelected(true);
				}
				if (!siblingsCheckBox.isSelected()
						&& Arrays.stream(selectedValues).map(String::trim).anyMatch("Siblings"::equalsIgnoreCase)) {
					isMedicalFormChanged = true;
					siblingsCheckBox.setSelected(true);
				}
				if (!notSureCheckBox.isSelected()
						&& Arrays.stream(selectedValues).map(String::trim).anyMatch("Not Sure"::equalsIgnoreCase)) {
					isMedicalFormChanged = true;
					notSureCheckBox.setSelected(true);
				}
			}
		} else {
			// If glaucomaHistory is null or empty, ensure all checkboxes are unselected
			if (noCheckBox.isSelected() || selfCheckBox.isSelected() || parentsCheckBox.isSelected()
					|| siblingsCheckBox.isSelected() || notSureCheckBox.isSelected()) {
				isMedicalFormChanged = true;
			}
			noCheckBox.setSelected(false);
			selfCheckBox.setSelected(false);
			parentsCheckBox.setSelected(false);
			siblingsCheckBox.setSelected(false);
			notSureCheckBox.setSelected(false);
		}
		

		/*
		 * last eye exam
	     */
		if ("Unknown".equals(lastEyeExam)) {
			if (!lastEyeExamComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}

		} else if (lastEyeExam != null) { // Check only if not null here
			if (!lastEyeExamComboBox.getValue().equals(lastEyeExam)) {
				isMedicalFormChanged = true;
			}

		} else {
			if (!lastEyeExamComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}

		}
//		/*
//		 * usual blood sugar high
//		 */
//
		if (bloodSugarLevelHigh != null) {
			if (!highUsualBloodSugar.getText().equals(bloodSugarLevelHigh)) {
				isMedicalFormChanged = true;
			}

		}
//		/*
//		 * blood sugar low
//		 */
//
		if (bloodSugarLevelLow != null) {
			if (!lowUsualBloodSugar.getText().equals(bloodSugarLevelLow)) {
				isMedicalFormChanged = true;
			}

		}
//		/*
//		 * blood sugar Avg
//		 */
//
		if (bloodSugarLevelAvg != null) {
			if (!averageUsualBloodSugar.getText().equals(bloodSugarLevelAvg)) {
				isMedicalFormChanged = true;
			}

		}
		
		/*
		 * triglycerides
		 */
		if (triglycerides != null) {
			if (!trigyTextField.getText().equals(triglycerides)) {
				isMedicalFormChanged = true;
			}

		}
		if (hemoglobin != null) {
			if (!haemoglobinTextField.getText().equals(hemoglobin)) {
				isMedicalFormChanged = true;
			}
		}
		if (cholestrol != null) {
			if (!cholestorolTextField.getText().equals(cholestrol)) {
				isMedicalFormChanged = true;
			}
		}
	    /*
		 * hypertension
		 */

		if (hypertension != null && !hypertension.isEmpty()) {
			if (hypertension.equalsIgnoreCase("no")) {
				if (!hyperTensionComboBox.getValue().equals("No")) {
					isMedicalFormChanged = true;
				}

			} else if (hypertension.equalsIgnoreCase("controlled")) {
				if (!hyperTensionComboBox.getValue().equals("Controlled")) {
					isMedicalFormChanged = true;
				}

			} else if (hypertension.equalsIgnoreCase("uncontrolled")) {
				if (!hyperTensionComboBox.getValue().equals("Uncontrolled")) {
					isMedicalFormChanged = true;
				}

			} else if (hypertension.equalsIgnoreCase("unknown")) {
				if (!hyperTensionComboBox.getValue().equals("Unknown")) {
					isMedicalFormChanged = true;
				}

			} 

			}
		else if(hypertension!=null &&hypertension.isEmpty())
		{
			if (!hyperTensionComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}
		}
		
		else if(hypertension==null)
		{
			if (!hyperTensionComboBox.getValue().equals("--Select--")) {
				isMedicalFormChanged = true;
			}
		}


		/*
	     * most recent blood test
		 */

		if (subjectiveDiabeticControl != null) {
		    if (subjectiveDiabeticControl.equalsIgnoreCase("Unknown")) {
		        if (!subjectiveDiabticControlComboBox.getValue().equals("--Select--")) {
		            isMedicalFormChanged = true;
		        }
		       
		    } else {
		        if (!subjectiveDiabticControlComboBox.getValue().equals(subjectiveDiabeticControl)) {
		            isMedicalFormChanged = true;
		        }
		       
		    }
		}

		 
	
		/*
		 * blod draw latest
		 */
		if (bloodDrawYear != null) {
		    // Check if yearMostRecentBloodTest.getValue() is not null before comparing
		    if (yearMostRecentBloodTest.getValue() != null && !yearMostRecentBloodTest.getValue().equals(bloodDrawYear)) {
		        isMedicalFormChanged = true;
		    }
		} else {
		    // Handle the case where bloodDrawYear is null or empty, and also check for null values in ComboBox
		    if (yearMostRecentBloodTest.getValue() != null) {
		        isMedicalFormChanged = true;
		    }
		}


		if (!bloodDrawMonth.isEmpty()) {
		    if (!monthRecentBloodTest.getValue().equals(bloodDrawMonth)) {
		        isMedicalFormChanged = true;
		    }
		
		}
		else {
			  if (!monthRecentBloodTest.getValue().isEmpty()) {
			        isMedicalFormChanged = true;
			    }
		}

		
		if (!medications.isEmpty()) {
			
		    if (!medicationTextArea.getText().equals(medications)) {
		        isMedicalFormChanged = true;
		    }
		} else {
		    // Since medications is null, just check if the text area is not empty
		    if (!medicationTextArea.getText().isEmpty()) {
		        isMedicalFormChanged = true;
		    }
		}


			

	
		/*
		 *iop
		 */
    

		if(iopRight!=null)
		{
			 if (!rightIopTextField.getText().equals(iopRight)) {
			        isMedicalFormChanged = true;
		    }
		}
		else
		{
			 if (!rightIopTextField.getText().equals(iopRight)) {
			        isMedicalFormChanged = true;
		    }
		}
	
	    /*
		 * left iop
		 */

		if (iopLeft != null) {
		
			
		    if (!leftEyeTextField.getText().equals(iopLeft)) {
		        isMedicalFormChanged = true;
		    
			}
		}
			else {
				if(!leftEyeTextField.getText().equals(iopLeft));
				isMedicalFormChanged = true;
			}
		
		

		

		/*
		 * other history
		 */
		//if (otherHistory != null) {
		    if (!otherHistory.isEmpty()) {
		        // Check if the text in the text field does not match the otherHistory value
		        if (!otherHistoryTextField.getText().equals(otherHistory)) {
		            isMedicalFormChanged = true;
		        }
		    } else {
		        // Handle the case when otherHistory is empty but not null
		        if (!otherHistoryTextField.getText().isEmpty()) {
		            isMedicalFormChanged = true;
		        }
		    }
		//}

		
		/*
		 * pinhole
		 */
if(pinhole!=null)
{
	   if (pinholeYes.isSelected() && !pinhole.equalsIgnoreCase("true")) {
	        isMedicalFormChanged = true;
	    } else if (pinholeNo.isSelected() && !pinhole.equalsIgnoreCase("false")) {
	        isMedicalFormChanged = true;
	    }
}
	



		/*
		 * withRx
		 */
if(withRx!=null)
{
	   if (withRxYes.isSelected() && !withRx.equalsIgnoreCase("true")) {
	        isMedicalFormChanged = true;
	    } else if (withRxNo.isSelected() && !withRx.equalsIgnoreCase("false")) {
	        isMedicalFormChanged = true;
	    }
}




	
		return isMedicalFormChanged;

}
}


