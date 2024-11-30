package com.autouploader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;

import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class AddNewPatient {
	private TextField diabaticTypeTextfield;
	private VBox insulinVBox;
	private VBox diabaticTypeVBox;
	private Button uploadImageNow;
	private double screenWidth;
	private   double screenHeight;
	
/**
 * 
 * @param primaryStage
 * @throws FileNotFoundException
 */
	public void handleAddPatient(Stage primaryStage) throws FileNotFoundException {
		// calculating the screen with and height
		screenWidth = Screen.getPrimary().getBounds().getWidth();
         screenHeight = Screen.getPrimary().getBounds().getHeight();
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
		dialogPane.setPrefSize(screenWidth*.6, screenHeight*.7); // Set preferred dimensions
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

	private ScrollPane createPatientForm() throws FileNotFoundException {
		ScrollPane addPatientScrollPane = new ScrollPane();
		VBox vboxForAddPatient = new VBox(2);
		HBox labelAndCrossHbox = createHeaderHBox();
		PersonalDetails personalDetailsForAddAotient=new PersonalDetails();
		GridPane personalDetailGridPane=personalDetailsForAddAotient.personalGridForUpload(null,null,null,null,null,null,null,null,null,null);
	MedicalDetails medicalDetails=new MedicalDetails();
	GridPane medGridPane=medicalDetails.medicalRecordGRidPane(null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,personalDetailsForAddAotient);
/////BUTTON UPLOAD IMAGE LATER AND UPLAOD IMAGE NOW
		Button uploadImagelater = new Button("UPLOAD IMAGES LATER");
		uploadImagelater.setStyle("-fx-background-color: rgba(0, 0, 0, 0); " + // Set the background color to
																				// transparent
				"-fx-border-color: orange; " + // Set the border color to orange
				"-fx-text-fill: orange; " + // Set the text color to orange
				"-fx-font-weight: bold; " + // Set the font weight to bold
				"-fx-border-radius: 5;" // Set the border radius (adjust the value as needed)
		);
		uploadImagelater.setOnAction(e->{
//			PersonalDetails pd=new PersonalDetails();
			personalDetailsForAddAotient.validateFormFields();
			medicalDetails.validateMedicalField(null);
		//	pd.validateFormFields();
			
		});
		uploadImagelater.setPrefWidth(screenWidth*.18);
	
uploadImageNow = new Button("UPLOAD IMAGES NOW");
		uploadImageNow.setStyle("-fx-background-color: orange; " + // Set the background color to orange
				"-fx-text-fill: white; " + // Set the text color to white
				"-fx-font-weight: bold; " + // Set the font weight to bold
				"-fx-border-radius: 5;" // Set the border radius (adjust the value as needed)
		);
		uploadImageNow.setOnAction(e->
		{
			personalDetailsForAddAotient.validateFormFields();
		medicalDetails.validateMedicalField(null);
			
		});
	
		
		
		uploadImageNow.setPrefWidth(screenWidth*.18);
		HBox hboxForImageUpload=new HBox(10);
		hboxForImageUpload.getChildren().addAll(uploadImagelater,uploadImageNow);
		vboxForAddPatient.getChildren().addAll(labelAndCrossHbox,personalDetailGridPane,medGridPane,hboxForImageUpload);
		addPatientScrollPane.setContent(vboxForAddPatient);
		return addPatientScrollPane;
	}
	private boolean validatonPersonaldetals()
	{
		PersonalDetails pd=new PersonalDetails();
		boolean validate=pd.validateFormFields();
		return validate;
		
	}
	private boolean validateMedicalField()
	{
		MedicalDetails med =new MedicalDetails();
return med.validateMedicalField(null);

	}
	

	private HBox createHeaderHBox() throws FileNotFoundException {
		HBox headerHBox = new HBox(600);
		headerHBox.setPadding(new Insets(3, 0, 0, 2));
		// Create a label for the custom dialog layout
		Label addPatientLabel = new Label("New Patient Case");
		addPatientLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: black; -fx-font-size: 12;");
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

}
