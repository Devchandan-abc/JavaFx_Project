package com.autouploader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class EditAddPatientDetails {
	public void handleEditPatient(Stage primaryStage) throws FileNotFoundException {
		// Create the dialog
		Dialog<Void> addPatientDialog = new Dialog<>();
		// Remove the default close button and dialog icon
		addPatientDialog.initStyle(StageStyle.UNDECORATED);
		// Calculate the primary of the screen
		Screen screen = Screen.getPrimary();
		// get the visual bounds of the screen
		Rectangle2D bounds = screen.getVisualBounds();
		double centerX = bounds.getWidth() / 2;
		double centerY = bounds.getHeight() / 2;

		// Create the dialog pane
		DialogPane dialogPane = new DialogPane();
		dialogPane.setPrefSize(470, 480); // Set preferred dimensions
		dialogPane.setContent(createPatientForm());

		// Set the dialog position to be centered on the screen
		addPatientDialog.setX(centerX - (dialogPane.getPrefWidth() / 2));
		addPatientDialog.setY(centerY - (dialogPane.getPrefHeight() / 2));
		addPatientDialog.setDialogPane(dialogPane);

		// Create a semi-transparent overlay pane
		StackPane overlay = new StackPane();
		overlay.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
		overlay.setPrefSize(bounds.getWidth(), bounds.getHeight());
		// casting the parent node to stackpane
		StackPane root = (StackPane) primaryStage.getScene().getRoot();
		// adding the overlay on the top of the root stackpane
		root.getChildren().add(overlay);

		// Show the dialog and wait for it to close
		addPatientDialog.showAndWait();

		// Remove the overlay pane after the dialog is closed
		root.getChildren().remove(overlay);
	}
	 

	private VBox createPatientForm() throws FileNotFoundException {
		VBox vboxForAddPatient = new VBox(2);
		HBox labelAndCrossHbox = createHeaderHBox();
		// Create a GridPane for the form
		GridPane gridPane = new GridPane();
		gridPane.setHgap(5);
		gridPane.setVgap(2.5);
		Label headingLabel = new Label("Create a new patient case");
		Label nameLabel = new Label("NAME"); // Label without asterisk

		/// Name

// Add an asterisk in red color
		Label asteriskLabel = new Label("*");
		asteriskLabel.setStyle("-fx-text-fill: red;");

		HBox nameHBox = new HBox(1);
		nameHBox.getChildren().addAll(nameLabel, asteriskLabel);

		TextField nameTextField = new TextField();
		nameTextField.setPromptText("type here");
		HBox hboxForNameAndTextBox = new HBox(5);
	//	hboxForNameAndTextBox.setPrefSize(240, 20);
		hboxForNameAndTextBox.getChildren().addAll(nameHBox, nameTextField);
		nameTextField.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0 0 1 0; -fx-border-color: black;");
		gridPane.add(hboxForNameAndTextBox, 0, 0); // Use the HBox containing nameLabel and asteriskLabel
		Label genderLabel = new Label("GENDER");
		Label asteriskLabel1 = new Label("*");
		asteriskLabel1.setStyle("-fx-text-fill: red;");
		/// GENDER

		HBox hboxforGenderLabelAndAstricksymbol = new HBox(1);

		ComboBox<String> genderComboBox = new ComboBox<>();
		genderComboBox.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0 0 1 0; -fx-border-color: black;");
		genderComboBox.setPromptText("select one");
		genderComboBox.getItems().addAll("Male", "Female");
		genderComboBox.setPrefWidth(150); // Adjust the width as needed
		HBox hboxForGenderAndComboBox = new HBox(5);
		hboxForGenderAndComboBox.getChildren().addAll(hboxforGenderLabelAndAstricksymbol, genderComboBox);
		hboxforGenderLabelAndAstricksymbol.getChildren().addAll(genderLabel, asteriskLabel1);
		hboxforGenderLabelAndAstricksymbol.setPadding(new Insets(6));
		HBox hboxFormrnLabelandAstrick = new HBox(5);
		Label asteriskLabel2 = new Label("*");
		asteriskLabel2.setStyle("-fx-text-fill: red;");
		/// MRN
		Label mrnLabel = new Label("MRN");
		hboxFormrnLabelandAstrick.getChildren().addAll(mrnLabel, asteriskLabel2);
		TextField mrnTextField = new TextField();
		mrnTextField.setPromptText("type here");
		mrnTextField.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0 0 1 0; -fx-border-color: black;");
		HBox hboxForMrnLabelAndTextBox = new HBox(5);
		hboxForMrnLabelAndTextBox.getChildren().addAll(hboxFormrnLabelandAstrick, mrnTextField);
		gridPane.add(hboxForMrnLabelAndTextBox, 0, 1);
		HBox hboxForEthinicityandComboBox = new HBox(5);
		Label ethinicityLabel = new Label("ETHINCITY");
		ComboBox<String> ethinictyComboBox = new ComboBox<>();
		ethinictyComboBox.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0 0 1 0; -fx-border-color: black;");
		ethinictyComboBox.setPromptText("select one");
		ethinictyComboBox.setPrefWidth(150);
		ethinictyComboBox.getItems().addAll("racial", "fair");
		hboxForEthinicityandComboBox.getChildren().addAll(ethinicityLabel, ethinictyComboBox);
		gridPane.add(hboxForEthinicityandComboBox, 1, 1);
		gridPane.add(hboxForGenderAndComboBox, 1, 0);

		HBox hboxForDobLabelandDatePicker = new HBox(10);
		Label dobLabel = new Label("DOB:");
		// Add DOB Label and DatePicker
		TextField dobTextField = new TextField();
		dobTextField.setPromptText("mm-dd-yyyy");

		dobTextField.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0 0 1 0; -fx-border-color: black;");
		hboxForDobLabelandDatePicker.getChildren().addAll(dobLabel, dobTextField);
		gridPane.add(hboxForDobLabelandDatePicker, 0, 2);
		/// pupil dilation

		VBox vboxForPupilDilation = new VBox();
		Text redAsterisk = new Text("*");
		redAsterisk.setFill(Color.RED);

		// Combine the Text node and the rest of the label text
		String pupilDilationStringLabel = "Pupil Dilation";
		Label pupilDilationLabel = new Label(pupilDilationStringLabel + " ");
		// text will be set as the graphic to the label
		pupilDilationLabel.setGraphic(redAsterisk);
		// the astrick trext will be put right to the label
		pupilDilationLabel.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
		pupilDilationLabel.setPadding(new Insets(0, 0, 0, 7));
		ComboBox<String> pupilDilationComboBox = new ComboBox<>();

// Add items to the ComboBox
		pupilDilationComboBox.getItems().addAll("A", "B");
		pupilDilationComboBox.setPromptText("select");
		pupilDilationComboBox.setPrefSize(240,5);
		pupilDilationComboBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
		vboxForPupilDilation.getChildren().addAll(pupilDilationLabel, pupilDilationComboBox);
		vboxForPupilDilation.setPrefSize(240, 30);
		vboxForPupilDilation.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		gridPane.add(vboxForPupilDilation, 0, 3);

// last eye exam
		
		VBox vboxForLastEyeExam = new VBox();
		vboxForLastEyeExam.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		// Create a Text node for the "*" symbol
		Text redAsterisklastEyeExam = new Text("*");
		// redAsterisk.setStyle(Color.RED);
		redAsterisklastEyeExam.setFill(Color.RED);

		// Combine the Text node and the rest of the label text
		String lastEye = "Last Eye Exam";
		Label lastEyeLabel = new Label(lastEye + " ");
		// text will be set as the graphic to the label
		lastEyeLabel.setGraphic(redAsterisklastEyeExam);
		lastEyeLabel.setPadding(new Insets(0, 0, 0, 7));
		lastEyeLabel.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);

		// Label lastEyeExamLabel = new Label("Last Eye Exam ");

		ComboBox<String> lastEyeExamComboBox = new ComboBox<>();
		lastEyeExamComboBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");
// Add items to the ComboBoxdo
		lastEyeExamComboBox.getItems().addAll("A", "B");
		lastEyeExamComboBox.setPromptText("select");
		lastEyeExamComboBox.setPrefSize(240, 5);
		lastEyeExamComboBox.setStyle("-fx-background-color: transparent;");

		vboxForLastEyeExam.getChildren().addAll(lastEyeLabel, lastEyeExamComboBox);
	
		gridPane.add(vboxForLastEyeExam, 0, 4);
		VBox vboxForHaemoglobin=new VBox();
		vboxForHaemoglobin.setPrefSize(240, 30);
		vboxForHaemoglobin.setStyle("-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

// Create a Label for "Haemoglobin"
		Label haemoglobinLabel = new Label("Haemoglobin");
		haemoglobinLabel.setPadding(new Insets(0, 0, 0, 7));

		// Create a TextField with a placeholder text
		TextField haemoglobinTextField = new TextField();
		haemoglobinTextField.setPromptText("type here");
		haemoglobinTextField
		.setStyle("-fx-background-color:transparent;-fx-border-color:white;-fx-border-radius:0;-fx-border-width:0;");

// Add the TextField and Label to the StackPane
		vboxForHaemoglobin.getChildren().addAll(haemoglobinLabel,haemoglobinTextField);

// Add the StackPane to the GridPane
		gridPane.add(vboxForHaemoglobin, 1, 3);
		
		///// for USUAL BLOOD SUGAR LEVEL
	
		VBox vboxUsualBloodSugarLevel=new VBox(1);
		vboxUsualBloodSugarLevel.setStyle("-fx-background-color: transparent ;-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

// Create a Label for "Haemoglobin"
		Label usualBloodSugarLevelLAbel = new Label("USUAL BLOOD SUGAR LEVEL");
		usualBloodSugarLevelLAbel.setPadding(new Insets(0, 0, 0, 7));

// Create a TextField with a placeholder text
		TextField usualBloodSugarLevelTextField = new TextField();
		usualBloodSugarLevelTextField.setPromptText("type here");
		// usualBloodSugarLevelTextField.setPrefWidth(45);
		usualBloodSugarLevelTextField.setPrefSize(165, 5);




// Add the TextField and Label to the StackPane
		vboxUsualBloodSugarLevel.getChildren().addAll(usualBloodSugarLevelLAbel,usualBloodSugarLevelTextField);

// Add the StackPane to the GridPane
		
		gridPane.add(vboxUsualBloodSugarLevel, 1, 4);
		usualBloodSugarLevelTextField
				.setStyle("-fx-background-color:transparent;-fx-border-color:transparent;-fx-border-radius:5;-fx-border-width:1;");
//// YEARS WITH DIABATES


		VBox vboxForYearOfDiabates = new VBox();

		Label yearWithDiabatesLabel = new Label("YEAR WITH DIABATES");

		ComboBox<String> yearWithDiabatesComboBox = new ComboBox<>();
		yearWithDiabatesComboBox.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0 0 1 0; -fx-border-color: black;");
// Add items to the ComboBox
		yearWithDiabatesComboBox.getItems().addAll("A", "B");
		yearWithDiabatesComboBox.setPromptText("select");
		yearWithDiabatesComboBox.setPrefSize(240, 5);
		yearWithDiabatesComboBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

		String yearwithdiabltes = "Year With Diabates";
		Text yearwithdiabatesastrick = new Text("*");
		yearwithdiabatesastrick.setFill(Color.RED);
		Label yearWithDibatesLabel = new Label(yearwithdiabltes + " ");
		yearWithDibatesLabel.setPadding(new Insets(0, 0, 0, 7));
		yearWithDibatesLabel.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
		yearWithDibatesLabel.setGraphic(yearwithdiabatesastrick);
		vboxForYearOfDiabates.getChildren().addAll(yearWithDibatesLabel, yearWithDiabatesComboBox);
		vboxForYearOfDiabates.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		gridPane.add(vboxForYearOfDiabates, 0, 5);

///// CHOLESTORAL

		VBox vboxCholerstrol=new VBox();
		vboxCholerstrol.setStyle("-fx-background-color: transparent; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
				

// Create a Label for "Haemoglobin"
		Label cholestaorolLabel = new Label("CHOLOLESTROL");
		cholestaorolLabel.setPadding(new Insets(0, 0, 0, 7));

// Create a TextField with a placeholder text
		TextField cholestorolTextField = new TextField();
		cholestorolTextField.setPromptText("type here");
		cholestorolTextField.setStyle("-fx-background-color:transparent;-fx-border-color:white;-fx-border-radius:0;-fx-border-width:0;");
		vboxCholerstrol.getChildren().addAll(cholestaorolLabel,cholestorolTextField);

		gridPane.add(vboxCholerstrol, 1, 5);

		//// TRIGY LABEL
	
		VBox vboxtrigy=new VBox();
		vboxtrigy.setStyle("-fx-background-color: transparent ;-fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

// Create a Label for "Haemoglobin"
		Label trigyLabel = new Label("TRIGLYCERIDES");
		trigyLabel.setPadding(new Insets(0, 0, 0, 7));

// Create a TextField with a placeholder text
		TextField trigyTextField = new TextField();
		trigyTextField.setPromptText("type here");

		vboxtrigy.getChildren().addAll(trigyLabel,trigyTextField );

// Add the StackPane to the GridPane

		gridPane.add(vboxtrigy, 1, 6);
		trigyTextField
				.setStyle("-fx-background-color:transparent;-fx-border-color:transparent;-fx-border-radius:5;-fx-border-width:1;");

//////////////MEDICATION
		VBox vboxmedication=new VBox();

// Create a Label for "Haemoglobin"
		Label medicationLabel = new Label(" MEDICATION");
		medicationLabel.setPadding(new Insets(0, 0, 0, 7));

// Create a TextField with a placeholder text
		TextField medicationTextField = new TextField();
		medicationTextField.setPromptText("type here");

		medicationTextField
		.setStyle("-fx-background-color:transparent;-fx-border-color:transparent;");
		vboxmedication.setStyle("-fx-background-color: transparent; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

// Add the TextField and Label to the StackPane
	vboxmedication.getChildren().addAll(medicationLabel,medicationTextField );

		gridPane.add(vboxmedication, 1, 7);


///////////////VISUAL AQUITY

		VBox vboxForVisualAquity = new VBox(10);
		vboxForVisualAquity.setStyle("-fx-border-color: black; -fx-border-width: 1px;-fx-border-radius:3");

		TextArea medicationTextArea = new TextArea();
		medicationTextArea.setPrefSize(240, 50);

		medicationTextArea.setPromptText("type here");
		medicationTextArea
				.setStyle("-fx-background-color:transparent; -fx-border-width: 0 0 0 0; -fx-border-color: white;");
		Label visualAquityLabel = new Label("VISUAL AQUITY");
		visualAquityLabel.setPadding(new Insets(0, 0, 0, 5));
		vboxForVisualAquity.getChildren().addAll(visualAquityLabel, medicationTextArea);
		vboxForVisualAquity.setPrefSize(240, 90);

		gridPane.add(vboxForVisualAquity, 1, 8);

		GridPane.setRowSpan(vboxForVisualAquity, 2); // Span two rows in column 1

		//// GLAUCOMA HISTORY

		VBox vboxForGlaucomaHistory = new VBox();

		ComboBox<String> glaucomaHistoryComboBox = new ComboBox<>();
		glaucomaHistoryComboBox.setStyle("-fx-background-color: transparent ");
// Add items to the ComboBox
		glaucomaHistoryComboBox.getItems().addAll("A", "B");
		glaucomaHistoryComboBox.setPromptText("select");
		glaucomaHistoryComboBox.setPrefSize(240, 5);

		String glaucoaHistory = "Glaucoma History";
		Text galaucomaHistory = new Text("*");
		yearwithdiabatesastrick.setFill(Color.RED);
		Label glaucomaHistoryLabel = new Label(glaucoaHistory + " ");
		glaucomaHistoryLabel.setPadding(new Insets(0, 0, 0, 7));
		glaucomaHistoryLabel.setContentDisplay(javafx.scene.control.ContentDisplay.RIGHT);
		glaucomaHistoryLabel.setGraphic(yearwithdiabatesastrick);

		vboxForGlaucomaHistory.getChildren().addAll(glaucomaHistoryLabel, glaucomaHistoryComboBox);
		vboxForGlaucomaHistory.setStyle(
				"-fx-background-color: transparent; -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		gridPane.add(vboxForGlaucomaHistory, 0, 6);

		/////////// HYPERTENSION
		// StackPane hypertensionStackPane = new StackPane();
		VBox vboxForHyperTension = new VBox();
		Label hyperTensionLabel = new Label("HYPERTENSION");
		// hyperTensionLabel.setAlignment(Pos.TOP_CENTER);
		hyperTensionLabel.setPadding(new Insets(0, 0, 0, 7));

		ComboBox<String> hyperTensionComboBox = new ComboBox<>();

// Add items to the ComboBox
		hyperTensionComboBox.getItems().addAll("A", "B");

		hyperTensionComboBox.setPromptText("select");
	hyperTensionComboBox.setPrefSize(240, 5);
		hyperTensionComboBox.setStyle("-fx-background-color: transparent;");
		vboxForHyperTension.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		vboxForHyperTension.getChildren().addAll(hyperTensionLabel, hyperTensionComboBox);

		gridPane.add(vboxForHyperTension, 0, 7);

//// SUBJECTIVE DIABATIC CONTROL
		// StackPane subjectiveDiabaticControlStackPane = new StackPane();
		VBox vboxForSubjectiveDiabaticControl = new VBox();
		Label subjectiveDiabaticControllLabel = new Label("SUBJECTIVE DIABATIC CONTROL");
		subjectiveDiabaticControllLabel.setPadding(new Insets(0, 0, 0, 7));
		ComboBox<String> subjectiveDiabticControlComboBox = new ComboBox<>();
		subjectiveDiabticControlComboBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0); ");
// Add items to the ComboBox
		subjectiveDiabticControlComboBox.getItems().addAll("A", "B");
		subjectiveDiabticControlComboBox.setPromptText("select");
	subjectiveDiabticControlComboBox.setPrefSize(240, 5);
		subjectiveDiabticControlComboBox.setStyle("-fx-background-color: transparent; ");

		vboxForSubjectiveDiabaticControl.getChildren().addAll(subjectiveDiabaticControllLabel,
				subjectiveDiabticControlComboBox);
		// subjectiveDiabaticControlStackPane.setAlignment(Pos.TOP_LEFT);
		// GridPane.setColumnSpan(pupilDilationStackPane, 2);
		vboxForSubjectiveDiabaticControl.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		gridPane.add(vboxForSubjectiveDiabaticControl, 0, 8);

		////////// MOST RECENT BLOOD TEST
		// StackPane mostRecentBolldTestStackPane = new StackPane();
		VBox vboxForMostRecentBloodTest = new VBox();
		Label mostRecentBloodTestLabel = new Label("MOST RECENT BLOOD TEST");

		mostRecentBloodTestLabel.setPadding(new Insets(0, 0, 0, 7));
		ComboBox<String> mostRecentBloodTestComboBox = new ComboBox<>();
		mostRecentBloodTestComboBox.setStyle("-fx-background-color: rgba(0, 0, 0, 0); ");
// Add items to the ComboBox
		mostRecentBloodTestComboBox.getItems().addAll("A", "B");
		mostRecentBloodTestComboBox.setPromptText("select");
		mostRecentBloodTestComboBox.setPrefSize(240, 5);
		mostRecentBloodTestComboBox.setStyle("-fx-background-color: transparent;");
		vboxForMostRecentBloodTest.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");

		vboxForMostRecentBloodTest.getChildren().addAll(mostRecentBloodTestLabel, mostRecentBloodTestComboBox);
		gridPane.add(vboxForMostRecentBloodTest, 0, 9);
		////// OTHER HISTORY OR INFORMATION
		VBox vboxForOtherPatientInfo = new VBox();

// Create a Label for "Haemoglobin"
		Label otherInformationLabel = new Label("Other  history Or Information");
		otherInformationLabel.setPadding(new Insets(0, 0, 0, 7));

// Create a TextField with a placeholder text
		TextField otherHistoryTextField = new TextField();
		// otherHistoryTextField.setStyle("-fx-background-color: rgba(0, 0, 0, 0);
		// -fx-border-width: 0 0 0 0; -fx-border-color: white;");
		otherHistoryTextField.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 0;  -fx-border-color: rgba(0, 0, 0, 0); ");
		otherHistoryTextField.setPromptText("type here");
		otherHistoryTextField.setPrefSize(165, 10);
		vboxForOtherPatientInfo.setStyle(
				"-fx-background-color: rgba(0, 0, 0, 0); -fx-border-width: 1; -fx-border-color: black; -fx-border-radius: 5;");
		vboxForOtherPatientInfo.getChildren().addAll(otherInformationLabel, otherHistoryTextField);
		gridPane.add(vboxForOtherPatientInfo, 0, 10);
		// Set the column span for the TextField
		GridPane.setColumnSpan(vboxForOtherPatientInfo, 2);

/////BUTTON UPLOAD IMAGE LATER AND UPLAOD IMAGE NOW
		Button uploadImagelater = new Button("UPLOAD IMAGES LATER");
		uploadImagelater.setStyle("-fx-background-color: rgba(0, 0, 0, 0); " + // Set the background color to
																				// transparent
				"-fx-border-color: orange; " + // Set the border color to orange
				"-fx-text-fill: orange; " + // Set the text color to orange
				"-fx-font-weight: bold; " + // Set the font weight to bold
				"-fx-border-radius: 5;" // Set the border radius (adjust the value as needed)
		);
		uploadImagelater.setPrefWidth(240);
		gridPane.add(uploadImagelater, 0, 11);

		Button uploadImageNow = new Button("UPLOAD IMAGES NOW");
		uploadImageNow.setStyle("-fx-background-color: orange; " + // Set the background color to orange
				"-fx-text-fill: white; " + // Set the text color to white
				"-fx-font-weight: bold; " + // Set the font weight to bold
				"-fx-border-radius: 5;" // Set the border radius (adjust the value as needed)
		);
	uploadImageNow.setOnAction(e->{
		
		System.out.println(nameTextField.getText());
	});
		
		

		uploadImageNow.setPrefWidth(240);
		gridPane.add(uploadImageNow, 1, 11);

		vboxForAddPatient.getChildren().addAll(labelAndCrossHbox, gridPane);
		return vboxForAddPatient;
	}
	private HBox createHeaderHBox() throws FileNotFoundException {
		HBox headerHBox = new HBox(280);
		headerHBox.setPadding(new Insets(3, 0, 0, 2));
		// Create a label for the custom dialog layout
		Label addPatientLabel = new Label("Edit Patient Information");
		addPatientLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 12;");

		Image closeImage = new Image(new FileInputStream("resources/images/Exit.png")); // Replace with your image URL
		ImageView imageView = new ImageView(closeImage);
		imageView.setFitWidth(8); // Set your desired width
		imageView.setFitHeight(8); // Set your desired height
		Button btn = new Button();
		btn.setGraphic(imageView);

		btn.setOnAction(e -> {
			Stage stage = (Stage) btn.getScene().getWindow();
			stage.close();
		});

		// Add components to the HBox
		headerHBox.getChildren().addAll(addPatientLabel, btn);
		return headerHBox;
	}

}

