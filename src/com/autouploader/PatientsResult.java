package com.autouploader;
import javafx.animation.PauseTransition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import com.eyepacs.ext.connectors.https.HttpsConnectors;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ext.eyepacs.encryptor.aes.EyePacsEncryptorAES;

public class PatientsResult {
	private VBox patientResultVBox;
	private ObservableList<Patient> patientData;
	private Rectangle searchThisWeekRectagle;
	private Rectangle searchThisMonthRecatangle;
	private Rectangle searchLastThreeMonthRectagle;
	private Rectangle searchAllTimeMonthRectangle;
	private StackPane dispalyPatientResultStackpane;
	private Stage stage;
	private String userName;
	private String pass;
	private ComboBox<String> siteComboBox;
	private DateTimeFormatter dateFormatter;
	private TableView<Patient> patientTableview;
	private TextField searchTextField;
	private ImageView refreshImageView;
	private BorderPane borderPane;
	private Pane fixedPane;
	// Create menu items
	private	MenuItem edit ;
			private MenuItem view;
			private MenuItem upload ;
			private Rectangle patRectangle;
			private Rectangle uploadRectangle;
			private Rectangle dashRectangle;
			private Button patientButtonForEdit;
			private Button dashBoardButtonForEdit;
			private Button uploadButtonForedit;
			private ImageView uploadImageview;
			private ImageView patientImageView;
			private ImageView dashboardImageView;

	public void displayPatientResult(Stage stage, BorderPane borderPane, String result, Pane fixedRectangleStackPane,
			ComboBox<String> sitecomboBox, String username, String password, Rectangle patientRectangle, Rectangle uploadRectangle, Rectangle dashboardRectangle,Button uploadButton, Button dashBoardButton, Button patientbtn, ImageView uploadImageView, ImageView dashboardImageView, ImageView patientImageview) throws FileNotFoundException {
        this.uploadImageview=uploadImageView;
        this.patientImageView=patientImageview;
        this.dashboardImageView=dashboardImageView;
		this.stage = stage;
		this.borderPane=borderPane;
		this.pass = password;
		this.userName = username;
		this.siteComboBox = sitecomboBox;
		this.patRectangle=patientRectangle;
		this.dashRectangle=dashboardRectangle;
		this.uploadRectangle=uploadRectangle;
		this.fixedPane=fixedRectangleStackPane;
		this.patientButtonForEdit=patientbtn;
		this.dashBoardButtonForEdit=dashBoardButton;
		this.uploadButtonForedit=uploadButton;
		System.out.println(getLastThreeMonthDate());
		System.out.println(getThisMonthDate());
		System.out.println(getLastWeekDate());
		System.out.println(getPatientsThisMonth(username, password));
		dispalyPatientResultStackpane = new StackPane();
		Screen screeen = Screen.getPrimary();
	
		  Screen screen = Screen.getPrimary();
	        double screenWidth = screen.getBounds().getWidth();  // Get screen width
	        double screenHeight = screen.getBounds().getHeight();  // Get screen height\
	    	dispalyPatientResultStackpane.setPrefWidth(screenWidth*.8);
	    	dispalyPatientResultStackpane.setPrefHeight(screenWidth*.4);
		// this vbox for all content
		patientResultVBox = new VBox(20);
		patientResultVBox.setPadding(new Insets(35));
		patientResultVBox.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(.7))));
		HBox labelAndImage = new HBox(10);
		//labelAndImage.setPrefWidth(screenWidth*.10);
		Label labelForPatientList = new Label("Patients List");
		labelForPatientList.setPrefWidth(screenWidth*.10);
		labelForPatientList.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		Image scrollingImage = new Image(new FileInputStream("resources/images/Refresh.png"));
		ImageView scrollingImageView = new ImageView(scrollingImage);
		scrollingImageView.setFitWidth(16);
		scrollingImageView.setFitHeight(16);
		Button refreshbtn = new Button();
		refreshbtn.setGraphic(scrollingImageView);
		refreshbtn.setStyle("-fx-background-color:transparent; -fx-border-color:transparent;");
		// Set the size for the circle
		double circleSize = 28.0;
		// Create a Circle with orange background
		Circle orangeBackground = new Circle(circleSize / 2, Color.ORANGE);
		// Create a StackPane and add the Rectangle and ImageView
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(orangeBackground, refreshbtn);

		refreshbtn.setOnAction(e -> {
		    // Show the loading gif (refreshImageView)
		    refreshImageView.setVisible(true);
		    refreshImageView.setManaged(true);

		    // Add the refreshImageView to the VBox at index 1
		    patientResultVBox.getChildren().set(1, refreshImageView);
		        System.out.println("Table is being refreshed with new data");

		        try {
		            // Refresh the TableView with new data
		            TableView<Patient> refreshedTable = refreshTable();

		            // Replace the loading gif with the new TableView
		            patientResultVBox.getChildren().set(1, refreshedTable);
		            System.out.println("Table is refreshed with: " + refreshedTable);

		        } catch (FileNotFoundException e1) {
		            e1.printStackTrace();
		        }

		        // Hide the loading gif after the table is updated
		        refreshImageView.setVisible(false);
		        refreshImageView.setManaged(false);
		        System.out.println("Table is refreshed with the new data");
		});


// adding label and imageview in hbox
		labelAndImage.getChildren().addAll(labelForPatientList, stackPane);
		Button searchThisWeekButton = new Button("This Week");
		searchThisWeekRectagle = new Rectangle();
		searchThisWeekRectagle.setHeight(3);
		searchThisWeekRectagle.setWidth(60);
		VBox vboxContaingButtonAndRectagle = new VBox();
		vboxContaingButtonAndRectagle.setAlignment(Pos.CENTER);
		vboxContaingButtonAndRectagle.getChildren().addAll(searchThisWeekButton, searchThisWeekRectagle);
		searchThisWeekRectagle.setFill(Color.WHITE);
		searchThisWeekButton.setStyle("-fx-background-color:transparent; -fx-border-color:transparent;");

		Button searchThisMonthButton = new Button("This Month");
		VBox vboxContainingThisMonthButtonAndRectangle = new VBox();
		searchThisMonthRecatangle = new Rectangle();
		searchThisMonthRecatangle.setFill(Color.WHITE);
		searchThisMonthRecatangle.setHeight(3);
		searchThisMonthRecatangle.setWidth(60);
		vboxContainingThisMonthButtonAndRectangle.setAlignment(Pos.CENTER);

		vboxContainingThisMonthButtonAndRectangle.getChildren().addAll(searchThisMonthButton,
				searchThisMonthRecatangle);

		searchThisMonthButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		/// Action for This Month button

		Button searchLast3MonthButton = new Button("Last 3 Month");
		VBox vboxContainingThisThreeMonthButtonAndRectangle = new VBox();
		searchLastThreeMonthRectagle = new Rectangle();
		searchLastThreeMonthRectagle.setFill(Color.WHITE);
		searchLastThreeMonthRectagle.setHeight(3);
		searchLastThreeMonthRectagle.setWidth(70);
		vboxContainingThisThreeMonthButtonAndRectangle.setAlignment(Pos.CENTER);

		vboxContainingThisThreeMonthButtonAndRectangle.getChildren().addAll(searchLast3MonthButton,
				searchLastThreeMonthRectagle);

		searchLast3MonthButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		// Action for Last 3 Months button
		Button searchButtonFor6Month = new Button("Last 6 Months");
		VBox vboxContainingAllTimeButtonAndRectangle = new VBox();
		searchAllTimeMonthRectangle = new Rectangle();
		searchAllTimeMonthRectangle.setFill(Color.WHITE);
		searchAllTimeMonthRectangle.setHeight(3);
		searchAllTimeMonthRectangle.setWidth(44);
		vboxContainingAllTimeButtonAndRectangle.setAlignment(Pos.CENTER);

		vboxContainingAllTimeButtonAndRectangle.getChildren().addAll(searchButtonFor6Month,
				searchAllTimeMonthRectangle);

		searchButtonFor6Month.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

		HBox hboxFortextboxContaingImageInside = new HBox();
		hboxFortextboxContaingImageInside.setSpacing(5);
		Button btnForsearchPatient = new Button();
		Image searchImageTextBox = new Image("file:/C:/Auto_Uplaoder/AutoUploader/images/search.png");
		ImageView searchImageView = new ImageView(searchImageTextBox);
		searchImageView.setFitWidth(10);
		searchImageView.setFitHeight(10);
		btnForsearchPatient.setGraphic(searchImageView);
		btnForsearchPatient.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		searchTextField = new TextField();
		searchTextField.setStyle("-fx-background-color: transparent; -fx-border-width: 0 0 0 0; ");
        // adding the searchtextfield and button in hbox 
		hboxFortextboxContaingImageInside.getChildren().addAll(searchTextField, btnForsearchPatient);
		hboxFortextboxContaingImageInside.setAlignment(Pos.CENTER);

		hboxFortextboxContaingImageInside.setStyle("-fx-border-radius: 5;" + "-fx-border-color: black;");
		searchTextField.setPromptText("search");
		// searchTextField.setDisable(false);
		searchTextField.setPrefSize(170, 20);
		// Add a key event listener to the searchTextField
		searchTextField.setOnKeyPressed(event -> {
		    if (event.getCode() == KeyCode.ENTER) {
		        performSearch();
		    }
		});
		
		btnForsearchPatient.setOnAction(e -> {
			// Get the search text and trim any leading/trailing spaces
			String searchedText = searchTextField.getText().trim();

			// Check if the search text is not empty
			if (!searchedText.isEmpty()) {
				// Filter the data based on the search text
				ObservableList<Patient> filteredData = filterDataBySearchText(patientData, searchedText);

				// Update the TableView with the filtered data
				updateTable(filteredData);

			} else {
				// Reset the TableView to show all patient data if the search text is empty
				updateTable(patientData);
			}
		});
		
		/***
		 * adding action for site combo box ,based on the table is updated
		 */

		sitecomboBox.setOnAction(e -> {
			Rectangle selectedRectangle = null;
			if (searchThisWeekRectagle.getFill().equals(Color.ORANGE)) {
				selectedRectangle = searchThisWeekRectagle;
			} else if (searchThisMonthRecatangle.getFill().equals(Color.ORANGE)) {
				selectedRectangle = searchThisMonthRecatangle;
			} else if (searchLastThreeMonthRectagle.getFill().equals(Color.ORANGE)) {
				selectedRectangle = searchLastThreeMonthRectagle;
			} else {
				selectedRectangle = searchAllTimeMonthRectangle;
			}

			if (selectedRectangle == searchThisWeekRectagle) {
				searchThisWeekButton.fire();
			} else if (selectedRectangle == searchThisMonthRecatangle) {
				searchThisMonthButton.fire();
			} else if (selectedRectangle == searchLastThreeMonthRectagle) {
				searchLast3MonthButton.fire();
			} else {
				searchButtonFor6Month.fire();
			}
		});

		HBox patintsHeaderHbox = new HBox(screenWidth*.01);
		

	        // Set spacing based on screen width (for example: 1/20th of screen width)
	       // double spacing = screenWidth*.05 ;  // Adjust this formula as needed
	        //patintsHeaderHbox.setSpacing();
		// adding differnt component to hbox button and heeder
	        labelAndImage.setPrefWidth(screenWidth*.15);
	        vboxContaingButtonAndRectagle.setPrefWidth(screenWidth*.10);
	        vboxContainingThisMonthButtonAndRectangle.setPrefWidth(screenWidth*.10);
	        vboxContainingThisThreeMonthButtonAndRectangle.setPrefWidth(screenWidth*.10);
	        vboxContainingAllTimeButtonAndRectangle.setPrefWidth(screenWidth*.10);
	        hboxFortextboxContaingImageInside.setPrefWidth(screenWidth*.12);
	        
		patintsHeaderHbox.getChildren().addAll(labelAndImage, vboxContaingButtonAndRectagle,
				vboxContainingThisMonthButtonAndRectangle, vboxContainingThisThreeMonthButtonAndRectangle,
				vboxContainingAllTimeButtonAndRectangle, hboxFortextboxContaingImageInside);
		refreshImageView = new ImageView(new Image(new FileInputStream("resources/images/loading_gif.gif")));
		refreshImageView.setFitHeight(30);
		refreshImageView.setFitWidth(30);
		patientResultVBox.getChildren().addAll(patintsHeaderHbox, refreshImageView);
		// patientResultVBox.setAlignment(Pos.CENTER);

		// patientResultVBox.getChildren().add(refreshImageView);

		searchButtonFor6Month.setOnAction(e -> {
			// Reset colors for the rectangles
			searchThisMonthRecatangle.setFill(Color.WHITE);
			searchThisWeekRectagle.setFill(Color.WHITE);
			searchLastThreeMonthRectagle.setFill(Color.WHITE);
			searchAllTimeMonthRectangle.setFill(Color.ORANGE);

			// Show the loading gif (refreshImageView)
			refreshImageView.setVisible(true);
			refreshImageView.setManaged(true);

			// Clear the VBox and add the refreshImageView at index 1
			patientResultVBox.getChildren().remove(1); // Clear the current node at index 1
			patientResultVBox.getChildren().add(1, refreshImageView); // Add loading gif at index 1
			TableView<Patient> thisMonthTableView = null;
			try {
				thisMonthTableView = createPatientResult(getPatientsThisMonth(username, pass));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Replace the refreshImageView with the TableView
			patientResultVBox.getChildren().remove(1); // Remove loading gif
			patientResultVBox.getChildren().add(1, thisMonthTableView); // Add the new TableView
			// Hide the loading gif after the table is displayed
			refreshImageView.setVisible(false);
			refreshImageView.setManaged(false);
		});
		searchLast3MonthButton.setOnAction(event -> {
			// Set the fill color for rectangles
			searchThisMonthRecatangle.setFill(Color.WHITE);
			searchThisWeekRectagle.setFill(Color.WHITE);
			searchLastThreeMonthRectagle.setFill(Color.ORANGE);
			searchAllTimeMonthRectangle.setFill(Color.WHITE);
			patientResultVBox.getChildren().set(1, refreshImageView); // Replace current node with loading gif
			refreshImageView.setVisible(true);
			refreshImageView.setManaged(true);

			// Create a pause transition to simulate loading delay
			PauseTransition pause = new PauseTransition(Duration.millis(300)); // Adjust to 300ms or more
			TableView<Patient> lastThreeMonthTableView = null;
			try {
				lastThreeMonthTableView = createPatientResult(getPatientsLastThreeMonth(username, password));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// Replace the loading gif with the TableView
			patientResultVBox.getChildren().set(1, lastThreeMonthTableView);
			refreshImageView.setVisible(false);
			refreshImageView.setManaged(false);
		});
		searchThisMonthButton.setOnAction(event -> {

			// Update the rectangle fill colors to indicate the selected time range
			searchThisMonthRecatangle.setFill(Color.ORANGE);
			searchThisWeekRectagle.setFill(Color.WHITE);
			searchLastThreeMonthRectagle.setFill(Color.WHITE);
			searchAllTimeMonthRectangle.setFill(Color.WHITE);

			// Display the loading gif
			refreshImageView.setVisible(true);
			refreshImageView.setManaged(true);
			patientResultVBox.getChildren().set(1, refreshImageView); // Show the loading gif at index 1
			// Fetch and display patient data after the delay
			TableView<Patient> thisMonthTableView = null;
			// try {
			try {
				thisMonthTableView = createPatientResult(getPatientsThisMonth(username, pass));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// Replace the loading gif with the TableView
			patientResultVBox.getChildren().set(1, thisMonthTableView);

			// Hide the loading gif after fetching the data
			refreshImageView.setVisible(false);
			refreshImageView.setManaged(false);
		});
		// Add VBox to StackPane and adjust margins
		dispalyPatientResultStackpane.getChildren().clear(); // Clear any previous data in the StackPane
		dispalyPatientResultStackpane.getChildren().add(patientResultVBox);
		StackPane.setMargin(patientResultVBox, new Insets(10, 0, 0, 15));

		// Clear and re-add the StackPane with the updated patient results
		fixedRectangleStackPane.getChildren().clear();
		fixedRectangleStackPane.getChildren().add(dispalyPatientResultStackpane);

		// });

		searchThisWeekButton.setOnAction(e ->

		{
			// Set the colors for the rectangles
			searchThisWeekRectagle.setFill(Color.ORANGE);
			searchThisMonthRecatangle.setFill(Color.WHITE);
			searchLastThreeMonthRectagle.setFill(Color.WHITE);
			searchAllTimeMonthRectangle.setFill(Color.WHITE);

			// Show the loading gif
			refreshImageView.setVisible(true);
			refreshImageView.setManaged(true);
			patientResultVBox.getChildren().set(1, refreshImageView); // Show loading gif at index 1
			TableView<Patient> lastWeekPatientTable = null;
			System.out.println("last week table view ism" + lastWeekPatientTable);
			try {
				lastWeekPatientTable = createPatientResult(getLastWeekData(username, password));
				System.out.println("last week table view is" + lastWeekPatientTable);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			patientResultVBox.getChildren().set(1, lastWeekPatientTable); // Replace loading gif with table
			// Hide the loading gif after fetching the data
			refreshImageView.setVisible(false);
			refreshImageView.setManaged(false);
		});
		searchThisMonthButton.fire();

	}
	/***
	 * method to perform search based on search text in the textfield and update the table
	 */
private void performSearch() {
		// TODO Auto-generated method stub
	 String searchedText = searchTextField.getText().trim();

	    // Check if the search text is not empty
	    if (!searchedText.isEmpty()) {
	        // Filter the data based on the search text
	        ObservableList<Patient> filteredData = filterDataBySearchText(patientData, searchedText);

	        // Update the TableView with the filtered data
	        updateTable(filteredData);
	    } else {
	        // Reset the TableView to show all patient data if the search text is empty
	        updateTable(patientData);
	    }
		
	}
/***
 * method to create a table view 
 * @param responseListPatient
 * @return
 * @throws FileNotFoundException
 */
	public TableView<Patient> createPatientResult(Map<HttpsConnectors.RESPONSE, String> responseListPatient)
			throws FileNotFoundException {
		System.out.println(responseListPatient);
		patientTableview = new TableView<>();
	patientTableview.setStyle("-fx-background-color: white;");
		TableColumn<Patient, String> namePatientColumn = new TableColumn<>();
		namePatientColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		 // Create a custom label for the header with desired styling
        Label headerLabel = new Label("Name");
        headerLabel.setFont(new Font("Arial", 14));       // Set font size and family
        headerLabel.setTextFill(Color.BLACK);               // Set text color
        //headerLabel.setAlignment(Pos.BOTTOM_CENTER);             // Center align the text
        headerLabel.setStyle("-fx-background-color:transparent" ); // Set background color
HBox nameHeader=new HBox(headerLabel);
nameHeader.setAlignment(Pos.CENTER_LEFT);

        // Set the label as the header graphic
        namePatientColumn.setGraphic(nameHeader);
	//namePatientColumn.setStyle("-fx-background-color:transparent;");
		TableColumn<Patient, Integer> caseIdColumn = new TableColumn<>();
		caseIdColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		
        Label headerLabelForcaseId = new Label("Case Id");
        headerLabelForcaseId.setFont(new Font("Arial", 14));       // Set font size and family
        headerLabelForcaseId.setTextFill(Color.BLACK);               // Set text color
        headerLabelForcaseId.setStyle("-fx-background-color:transparent" ); // Set background color
HBox caseIdHeaderHBox=new HBox(headerLabelForcaseId);
caseIdHeaderHBox.setAlignment(Pos.CENTER_LEFT);
caseIdColumn.setGraphic(caseIdHeaderHBox);
/*
 * MRN COLUMN
 */
		TableColumn<Patient, Integer> mrnColumn = new TableColumn<>();
		mrnColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
	     Label headerLabelForMrn = new Label("MRN");
	     headerLabelForMrn.setFont(new Font("Arial", 14));       // Set font size and family
	     headerLabelForMrn.setTextFill(Color.BLACK);               // Set text color
	     headerLabelForMrn.setStyle("-fx-background-color:transparent" ); // Set background color
	HBox mrnHeaderHBox=new HBox(headerLabelForMrn);
	mrnHeaderHBox.setAlignment(Pos.CENTER_LEFT);
	mrnColumn.setGraphic(mrnHeaderHBox);
	
	/*
	 * Case Status
	 */
		TableColumn<Patient, String> caseStatusColumn = new TableColumn<>();
	caseStatusColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
    Label headerLabelForCaseStatus = new Label("Case Status");
    headerLabelForCaseStatus.setFont(new Font("Arial", 14));       // Set font size and family
    headerLabelForCaseStatus.setTextFill(Color.BLACK);               // Set text color
    headerLabelForCaseStatus.setStyle("-fx-background-color:transparent" ); // Set background color
HBox caseStatusHeaderHBox=new HBox( headerLabelForCaseStatus);
caseStatusHeaderHBox.setAlignment(Pos.CENTER_LEFT);
caseStatusColumn.setGraphic(caseStatusHeaderHBox);
/*
 * Image Added
 */
		TableColumn<Patient, String> imageAddedDate = new TableColumn<>();
	imageAddedDate.setStyle("-fx-background-color:white;-fx-text-fill:black;");
	  Label headerLabelForImageAdded  = new Label("Image Added");
	  headerLabelForImageAdded.setFont(new Font("Arial", 14));       // Set font size and family
	  headerLabelForImageAdded.setTextFill(Color.BLACK);               // Set text color
	  headerLabelForImageAdded.setStyle("-fx-background-color:transparent" ); // Set background color
	HBox imageAddedHeaderHBox=new HBox(  headerLabelForImageAdded);
	imageAddedHeaderHBox.setAlignment(Pos.CENTER_LEFT);
	 imageAddedDate.setGraphic(imageAddedHeaderHBox);
	 /*
	  * Uploaded by
	  */
		TableColumn<Patient, String> uploadedBy = new TableColumn<>();
		uploadedBy.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		
		  Label headerLabelForUploadedBy  = new Label("Uploaded By");
		  headerLabelForUploadedBy.setFont(new Font("Arial", 14));       // Set font size and family
		  headerLabelForUploadedBy.setTextFill(Color.BLACK);               // Set text color
		  headerLabelForUploadedBy.setStyle("-fx-background-color:transparent" ); // Set background color
		HBox uploadedByHeaderHBox=new HBox(   headerLabelForUploadedBy);
		uploadedByHeaderHBox.setAlignment(Pos.CENTER_LEFT);
		uploadedBy.setGraphic(uploadedByHeaderHBox);
		
		TableColumn<Patient, Button> imageContaingEditableImage = new TableColumn<>("");
		imageContaingEditableImage.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		TableColumn<Patient, String> caseCreatedDate = new TableColumn<>();
		
		caseCreatedDate.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		  Label headerLabelForCaseCreated  = new Label("Case Created");
		  headerLabelForCaseCreated.setFont(new Font("Arial", 14));       // Set font size and family
		  headerLabelForCaseCreated.setTextFill(Color.BLACK);               // Set text color
		  headerLabelForCaseCreated.setStyle("-fx-background-color:transparent" ); // Set background color
		HBox caseCraetedHeaderHBox=new HBox(headerLabelForCaseCreated);
		caseCraetedHeaderHBox.setAlignment(Pos.CENTER_LEFT);
		caseCreatedDate.setGraphic(caseCraetedHeaderHBox);

		patientTableview.getColumns().addAll(namePatientColumn, caseIdColumn, mrnColumn, caseStatusColumn,
				caseCreatedDate, imageAddedDate, uploadedBy, imageContaingEditableImage);

		namePatientColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
		caseIdColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getCaseId()));
		mrnColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getMrn()));
		caseStatusColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCaseStatus()));

		// Use DateTimeParsingUtil for formatting
		caseCreatedDate.setCellValueFactory(
				data -> new SimpleStringProperty(Utilty.formatDate(data.getValue().getDateCreated())));

		imageAddedDate.setCellValueFactory(
				data -> new SimpleStringProperty(Utilty.formatDate(data.getValue().getImageAdded())));

		uploadedBy.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getUploadedBy()));
		imageContaingEditableImage.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBtn()));

		patientData = FXCollections.observableArrayList();

		Map<HttpsConnectors.RESPONSE, String> responseListForPatient = responseListPatient;
		String jsonResponse = responseListForPatient.get(HttpsConnectors.RESPONSE.VALUE);

		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
		JsonArray patientList = jsonObject.getAsJsonArray("patient_list");
		for (JsonElement patientElement : patientList) {
			System.out.println("patient element is "+patientElement);
			JsonObject patientObject = patientElement.getAsJsonObject();
			String patientName = patientObject.get("patientName").getAsString();
			int patientId = patientObject.get("patientId").getAsInt();
			int caseId = patientObject.get("caseId").getAsInt();
			String caseStatus = patientObject.get("caseStatus").getAsString();
			// Handle the dateCreated value
			LocalDate dateCreated = null;
			JsonElement dateCreatedElement = patientObject.get("dateCreated");
			if (dateCreatedElement != null && !dateCreatedElement.isJsonNull()) {
				try {
					dateCreated = Utilty.parseDate(dateCreatedElement.getAsString());
				} catch (IllegalArgumentException e) {
					System.err.println("Error parsing dateCreated: " + e.getMessage());
					// You might want to log this error or handle it in some way
				}
			}

			// Handle the imageAddedDate value
			LocalDate imageAddedDate1 = null;
			JsonElement imageAddedDateElement = patientObject.get("imageAddedDate");
			if (imageAddedDateElement != null && !imageAddedDateElement.isJsonNull()) {
				try {
					imageAddedDate1 = Utilty.parseDate(imageAddedDateElement.getAsString());
				} catch (IllegalArgumentException e) {
					System.err.println("Error parsing imageAddedDate: " + e.getMessage());
					// You might want to log this error or handle it in some way
				}
			}
			String uploadedByPatient = patientObject.get("uploadedBy").getAsString();
			patientData.add(new Patient(patientName, patientId, caseId, caseStatus, dateCreated, imageAddedDate1,
					uploadedByPatient, btnForPatient(caseStatus,patientId,patientName,caseId)));
		}

		patientTableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		patientTableview.setItems(patientData);
		caseCreatedDate.setSortable(true);
		caseCreatedDate.setSortType(TableColumn.SortType.ASCENDING); // Set the default sort order
        patientTableview.getSortOrder().add(caseCreatedDate);         // Add column to sort order to enable sorting on load
		return patientTableview;
	}

	// Method to filter data based on search text
	private ObservableList<Patient> filterDataBySearchText(ObservableList<Patient> patientData, String searchText) {
		// Ensure that patientData is not null before proceeding
		System.out.println(searchText);
		if (patientData == null) {
			return FXCollections.observableArrayList(); // Return an empty list if patientData is null
		}
		System.out.println(patientData);

		// Create a new list to store filtered data
		ObservableList<Patient> filteredList = FXCollections.observableArrayList();

		// Iterate through the patient data and check if any patient matches the search
		// text by name
		for (Patient patient : patientData) {
			if(patient!=null) {
				
			
			if (patient.getName() != null  ) {
				// Log the patient name for debugging purposes
				System.out.println("Patient Name: " + patient.getName());
				System.out.println("mrn is: "+patient.getMrn());
				System.out.println("case id is"+patient.getCaseId());
				System.out.println("upload status is"+patient.getUploadedBy());
				System.out.println("case status is"+patient.getCaseStatus());
				System.out.println("date created is"+patient.getDateCreated());

				// Perform a case-insensitive search by name
				String name = patient.getName().toLowerCase();
				String mrn=patient.getMrn().toString().toLowerCase();
				String caseId=patient.getCaseId().toString().toLowerCase();
				String uploadStatusIs=patient.getUploadedBy().toLowerCase();
				String dateUploaded=patient.getDateCreated().toString().toLowerCase();
				String dateCreated=patient.getDateCreated().toString().toLowerCase();
				String caseStatus=patient.getCaseStatus().toLowerCase();
				String searchTextForFilter=searchText.toLowerCase();
				if (name.contains(searchTextForFilter) || mrn.contains(searchTextForFilter)|| caseId.contains(searchTextForFilter)||caseId.contains(searchTextForFilter)||uploadStatusIs.contains(searchTextForFilter)||dateUploaded.contains(searchTextForFilter)||dateCreated.contains(searchTextForFilter)||caseStatus.contains(searchTextForFilter)) {
					// Add the matching patient to the filtered list
					filteredList.add(patient);
				}
				
			}
		}
		}

		return filteredList;
	}
	

	private TableView<Patient> refreshTable() throws FileNotFoundException {
	//	ObservableList<Patient> newData = null;
		Rectangle selectedRectangle = null;
		if (searchThisWeekRectagle.getFill().equals(Color.ORANGE)) {
			selectedRectangle = searchThisWeekRectagle;
		} else if (searchThisMonthRecatangle.getFill().equals(Color.ORANGE)) {
			selectedRectangle = searchThisMonthRecatangle;
		} else if (searchLastThreeMonthRectagle.getFill().equals(Color.ORANGE)) {
			selectedRectangle = searchLastThreeMonthRectagle;
		} else {
			selectedRectangle = searchAllTimeMonthRectangle;
		}

		if (selectedRectangle == searchThisWeekRectagle) {

			// Make the loading image visible
			refreshImageView.setVisible(true);
			refreshImageView.setManaged(true);

			TableView<Patient> thisWeekTableView = createPatientResult(getLastWeekData(userName, pass));
			return thisWeekTableView;

		}

		else if (selectedRectangle == searchThisMonthRecatangle) {
			TableView<Patient> thisMonthTableView = createPatientResult(getPatientsThisMonth(userName, pass));
			return thisMonthTableView;

		} else if (selectedRectangle == searchLastThreeMonthRectagle) {
			TableView<Patient> lastThreeMonthTableView = createPatientResult(getPatientsLastThreeMonth(userName, pass));
			return lastThreeMonthTableView;
		} else {
			return createPatientResult(getLastSixMonthData(userName, pass));

		}
		// return patientTableview;

	}

	// Method to update the TableView with new data
	private void updateTable(ObservableList<Patient> data) {
		patientTableview.setItems(data);
	}

	private Button btnForPatient(String caseStatus, int patientId, String patientName, int caseId) throws FileNotFoundException {
		Image shortcutImage = new Image(new FileInputStream("resources/images/vertical_png.png"));
		System.out.println(patientName);
		System.out.println(patientId);

		ImageView shortCutImageView = new ImageView(shortcutImage);
		shortCutImageView.setFitWidth(20);
		shortCutImageView.setFitHeight(15);
		Button btnForContectMenu = new Button();
		btnForContectMenu.setStyle("-fx-background-color:transparent; -fx-border-color: transparent;");
		btnForContectMenu.setGraphic(shortCutImageView);
		ContextMenu contextMenu = new ContextMenu();

		// Create menu items
		MenuItem edit = new MenuItem("Edit");
		MenuItem view = new MenuItem("View on EyePACS");
		MenuItem upload = new MenuItem("Upload Images");

		// Add menu items to the context menu
		contextMenu.getItems().addAll(edit, view, upload);
		// Disable Edit and Upload if case status is "Reviewed" or "Non-Reviewed"
	    if (caseStatus.equalsIgnoreCase("Reviewed") ) {
	        edit.setDisable(true);   // Disable Edit menu item
	        upload.setDisable(true); // Disable Upload menu item
	    }
	    view.setOnAction(event -> {
	        // Handle the "View on EyePACS" action (call the local API)
	        String viewUrl = "https://localhost/viewCase?clinicalCase.caseId=" + caseId + "&index=0";
	        openUrlInBrowser(viewUrl);
	    });
		edit.setOnAction(e -> {
			//EditAddPatientDetails editPatient = new EditAddPatientDetails();
			UploadResult uploadResult=new UploadResult();
			//editPatient.handleEditPatient(stage);
			//uploadResult.labelForPatient("Raj", "100001");
			try {
				uploadResult.displayUpload(stage, borderPane, "editPatient", fixedPane, userName, pass, siteComboBox,patientName,patientId,patRectangle,uploadRectangle,dashRectangle,uploadButtonForedit,dashBoardButtonForEdit,patientButtonForEdit,uploadImageview,dashboardImageView,patientImageView);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
	

		upload.setOnAction(e -> {
			UploadResult uploadResultForUpload=new UploadResult();
			try {
				uploadResultForUpload.displayUpload(stage, borderPane, "uploadPatient", fixedPane, userName, pass, siteComboBox,patientName,patientId,patRectangle,uploadRectangle,dashRectangle,uploadButtonForedit,dashBoardButtonForEdit,patientButtonForEdit,uploadImageview,dashboardImageView,patientImageView);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

		btnForContectMenu.setOnAction(e -> {
			// get the position of the button when it is clicked
			Point2D screenPosition = btnForContectMenu.localToScreen(btnForContectMenu.getBoundsInLocal().getMinX(),
					btnForContectMenu.getBoundsInLocal().getMinY());
			double x = screenPosition.getX();
			double y = screenPosition.getY();
			System.out.println("context menu is called");
			contextMenu.show(shortCutImageView, x - 100.0, y + 30.0); // Adjust position if needed

		});
		

		return btnForContectMenu;

	}
	
	
	// Method to open the URL in the default web browser
	private void openUrlInBrowser(String url) {
	    try {
	        // Create a URI object
	        URI uri = new URI(url);
	        // Use Desktop to browse the URL
	        if (Desktop.isDesktopSupported()) {
	            Desktop.getDesktop().browse(uri);
	        } else {
	            System.out.println("Desktop is not supported. Cannot open the URL.");
	        }
	    } catch (URISyntaxException | IOException e) {
	        e.printStackTrace();
	    }
	}
	private Map<HttpsConnectors.RESPONSE, String> getPatientsLastThreeMonth(String userName, String password) {
		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, password);
		Utilty utilty = new Utilty();

		String urlForLastThreeMonth = "/uploader/patient/list/byDate/"
				+ utilty.getSiteIdFromComboBox(userName, password, siteComboBox);
		String lastThreeMonthDate = getLastThreeMonthDate();

		String jsonStringForLastThreesMonth = "{\"dateString\" : \"" + lastThreeMonthDate + "\"}";
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, urlForLastThreeMonth,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect(jsonStringForLastThreesMonth, credString,
				"text/plain", "application/json");
		System.out.println("Response = " + response.get(HttpsConnectors.RESPONSE.CODE));
		System.out.println("Response value = " + response.get(HttpsConnectors.RESPONSE.VALUE));
		return response;
	}

	private Map<HttpsConnectors.RESPONSE, String> getPatientsThisMonth(String userName, String password) {

		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, password);
		Utilty utilty = new Utilty();
		String urlForThisMonth = "/uploader/patient/list/byDate/"
				+ utilty.getSiteIdFromComboBox(userName, password, siteComboBox);
		String lastMonthDate = getThisMonthDate();
		String jsonStringForThisMonth = "{\"dateString\" : \"" + lastMonthDate + "\"}";
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, urlForThisMonth,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect(jsonStringForThisMonth, credString,
				"text/plain", "application/json");
		System.out.println("Response = " + response.get(HttpsConnectors.RESPONSE.CODE));
		System.out.println("Response value = " + response.get(HttpsConnectors.RESPONSE.VALUE));
		return response;

	}

	private Map<HttpsConnectors.RESPONSE, String> getLastWeekData(String userName, String password) {

		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, password);
		Utilty utilty = new Utilty();
		String urlForLastWeek = "/uploader/patient/list/byDate/"
				+ utilty.getSiteIdFromComboBox(userName, password, siteComboBox);

		String lastWeekDate = getLastWeekDate();
		String lastWeekjsonString = "{\"dateString\" : \"" + lastWeekDate + "\"}";
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, urlForLastWeek,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect(lastWeekjsonString, credString,
				"text/plain", "application/json");
		System.out.println("Response = " + response.get(HttpsConnectors.RESPONSE.CODE));
		System.out.println("Response value = " + response.get(HttpsConnectors.RESPONSE.VALUE));
		return response;

	}

	private Map<HttpsConnectors.RESPONSE, String> getLastSixMonthData(String userName, String password) {
		// "{\"dateString\" : \"20240501\"}"
		Utilty utilty = new Utilty();

		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, password);
		String urlForLastSixMonth = "/uploader/patient/list/byDate/"
				+ utilty.getSiteIdFromComboBox(userName, password, siteComboBox);
		// String lastSixMonthDate=getSixMonthsAgoDate();
		String dateString = getSixMonthsAgoDate();
		String jsonString = "{\"dateString\" : \"" + dateString + "\"}";
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, urlForLastSixMonth,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect(jsonString, credString, "text/plain",
				"application/json");
		System.out.println("Response = " + response.get(HttpsConnectors.RESPONSE.CODE));
		System.out.println("Response value = " + response.get(HttpsConnectors.RESPONSE.VALUE));
		return response;

	}

	public static String getSixMonthsAgoDate() {
		LocalDate sixMonthsAgo = LocalDate.now().minusMonths(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return sixMonthsAgo.format(formatter);
	}

	public static String getLastThreeMonthDate() {
		LocalDate sixMonthsAgo = LocalDate.now().minusMonths(3);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return sixMonthsAgo.format(formatter);
	}

	// Method for Last Week (7 days ago)
	public static String getLastWeekDate() {
		LocalDate lastWeek = LocalDate.now().minusWeeks(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return lastWeek.format(formatter);
	}

	// Method for This Month (1st day of the current month)
	public static String getThisMonthDate() {
		LocalDate firstDayOfThisMonth = LocalDate.now().withDayOfMonth(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return firstDayOfThisMonth.format(formatter);
	}
}
