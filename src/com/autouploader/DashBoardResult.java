package com.autouploader;

import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;

import com.eyepacs.ext.connectors.https.HttpsConnectors;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ext.eyepacs.encryptor.aes.EyePacsEncryptorAES;

public class DashBoardResult {
	private Stage stage;
	private String userName;
	private String passWord;
	private ComboBox<String> siteCombo;
	private VBox vboxForRefreshImageView;
	private VBox vboxForUpcomingAppointment;
	private Rectangle patientRectangle;
	private Rectangle uploadRectangle;
	private Rectangle dashboardRectangle;
	private Button dashBoardBtn;
	private Button patientBtn;
	private Button uploadbtn;
	private ImageView dashBoardImageView;
	private ImageView uploadImageView;
	private ImageView patientImageView;
	private BorderPane borderPane;
	private Pane pane;

	public void displayResult(Stage stageForDashBoardResult, BorderPane borderPane, String result, Pane fixedRec,
			String userName, String password, ComboBox<String> siteCombo, Rectangle dashBoardRectangle,
			Rectangle uploadRectangle, Rectangle patientRectangle, Button uploadButton, Button patientButton,
			Button dasBoardButton, ImageView dashboardImageView, ImageView uploadImageView, ImageView patientImagweView)
			throws FileNotFoundException {
		
		this.stage = stageForDashBoardResult;
		this.borderPane = borderPane;
		this.pane = fixedRec;
		this.userName = userName;
		this.passWord = password;
		this.siteCombo = siteCombo;
		this.dashBoardBtn = dasBoardButton;
		this.uploadbtn = uploadButton;
		this.patientBtn = patientButton;
		this.dashBoardImageView = dashboardImageView;
		this.uploadImageView = uploadImageView;
		this.patientImageView = patientImagweView;
		this.uploadRectangle = uploadRectangle;
		this.dashboardRectangle = dashBoardRectangle;
		this.patientRectangle = patientRectangle;
		VBox mainContentVbox = new VBox(10);
		VBox VboxContaiingUpcomingtable = new VBox();
		 double screenWidth = Screen.getPrimary().getBounds().getWidth();
	        double screenHeight = Screen.getPrimary().getBounds().getHeight();
	      //vboxForMainContent.setPrefSize(screenWidth*.2, screenHeight*.8);
	//	VboxContaiingUpcomingtable.setPrefSize(520, 550);
	        
	    //    VboxContaiingUpcomingtable.setPrefSize(screenWidth*.6, screenHeight*.8);
	        VboxContaiingUpcomingtable.setPrefWidth(screenWidth*.5);
	        VboxContaiingUpcomingtable.setPrefHeight(screenHeight*.6);
	        //VboxContaiingUpcomingtable.setPrefHeight(screenHeight*.7);
	        
		VboxContaiingUpcomingtable.setPadding(new Insets(0, 0, 0, 20));
		String greeting = generateGreeting(userName);
		Label greetingLabel = new Label(greeting);
		greetingLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
		//// GETTING TODAYS DATE
		LocalDate todayDate = LocalDate.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMMM dd ,yyyy");
		String date = todayDate.format(dateTimeFormatter);
		/// Today's date label
		Label dateLabel = new Label(date);
		dateLabel.setStyle("-fx-font-size: 9 px;");
		HBox greetingHbox = new HBox(16);
		// Align the dateLabel at the bottom within the HBox
		HBox.setMargin(dateLabel, new Insets(10, 0, 0, 0)); // Adjust the margin as needed

		greetingHbox.setPadding(new Insets(10, 0, 0, 17));
		greetingHbox.getChildren().addAll(greetingLabel, dateLabel);
		// ALERT SECTION
		VBox vboxMainAlert = new VBox(5);
		vboxMainAlert.setPrefWidth(screenWidth*.2);
		vboxMainAlert.setPrefHeight(screenHeight*.6);
		
		HBox alertLabelHbox = new HBox(120);
		alertLabelHbox.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(.1))));
		HBox alertDetailsHbox = new HBox(15);
		Label alertLabel = new Label("Alerts");
		alertLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
		Button btn = new Button();
		ImageView image = new ImageView(new Image(new FileInputStream("resources/images/downarrow.png")));
		image.setFitWidth(10);
		image.setFitHeight(10);
		HBox hboxContaingbtnandImage = new HBox();
		// settng the child node at the bottom.
		hboxContaingbtnandImage.setAlignment(Pos.BOTTOM_LEFT);
		hboxContaingbtnandImage.getChildren().addAll(btn, image);
		btn.setStyle("-fx-background-color: white; -fx-border-color: black; -fx-border-width: .5px;");
		btn.setOnAction(e -> System.out.println("hello"));

		btn.setPrefWidth(10);
		alertLabelHbox.getChildren().addAll(alertLabel, hboxContaingbtnandImage);

		alertLabelHbox.setPadding(new Insets(5, 5, 5, 5));
		alertLabelHbox.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(1), new BorderWidths(.8))));

		HBox hbox1 = createAlert("Jan 24", "patients review is done", "uplaod_icon.png");
		HBox hbox2 = createAlert("Jan 28", "not reviewd", "upload_icon.png");
		vboxMainAlert.getChildren().addAll(alertLabelHbox, hbox1, hbox2);
		vboxMainAlert.setPadding(new Insets(5, 5, 5, 5));
		vboxMainAlert.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(3), new BorderWidths(.7))));
		HBox siteStatsAndAlertsBox = new HBox(200);
		VBox upcomingAppointmentsTable = createUpcomingAppointmentsTable();
		// vboxForRefreshImageView=new VBox(refreshImageView);
		VboxContaiingUpcomingtable.getChildren().add(upcomingAppointmentsTable);
		/// Hbox for the alert and the patients statics and table
		HBox hboxForsiteStaticsTableandalert = new HBox(50);
		hboxForsiteStaticsTableandalert.getChildren().addAll(VboxContaiingUpcomingtable, vboxMainAlert);
		// main content

		mainContentVbox.getChildren().addAll(greetingHbox, hboxForsiteStaticsTableandalert);

		fixedRec.getChildren().clear();

		fixedRec.getChildren().add(mainContentVbox);

		// Optional: Print the result to verify its content
		System.out.println("Result Content: " + result);
		/***
		 * setting action for site combobox
		 */
		siteCombo.setOnAction(e -> {

			try {
				// Now fetch only the content that needs to be replaced, like a TableView or
				TableView<Appointment> appointmentTableViewNew = createAppointmentTable();
				// Replace the content at index 1 with the new table or updated content
				vboxForUpcomingAppointment.getChildren().set(1, appointmentTableViewNew); // Replacing the second																					
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}
		});

	}

	/**
	 * 
	 * @param user
	 * @return the generated based on the user name login
	 */
	public String generateGreeting(String user) {
		String[] userLabel = user.split(" ");
		StringBuilder sb = new StringBuilder();
		sb.append(Character.toUpperCase(userLabel[0].charAt(0))).append(userLabel[0].substring(1).toLowerCase());

		// get the Local time
		LocalTime now = LocalTime.now();
		int hour = now.getHour();

		if (hour >= 6 && hour < 12) {
			return "Good Morning ," + sb;
		} else if (hour >= 12 && hour < 18) {
			return "Good Afternoon ," + sb;

		} else {
			return "Good Evening ," + sb;
		}
	}

	/***
	 * method to create an alert
	 * 
	 * @param date
	 * @param description
	 * @param imageName
	 * @return
	 * @throws FileNotFoundException
	 */
	private HBox createAlert(String date, String description, String imageName) throws FileNotFoundException {
		// VBox vboxForalertBox = new VBox(10);
		HBox hboxForalertdesc = new HBox(10);

		// Date label
		Label dateLabel = new Label(date);
		dateLabel.setStyle("-fx-font-weight: bold;");

		// Description label
		Label descriptionLabel = new Label(description);
		ImageView alertImage = new ImageView(new Image(new FileInputStream("resources/images/" + imageName)));
		alertImage.setFitWidth(25); // Set your desired width
		alertImage.setFitHeight(25); // Set your desired height
		hboxForalertdesc.getChildren().addAll(dateLabel, descriptionLabel, alertImage);
		return hboxForalertdesc;
	}

	/***
	 * methid to create upcoming appointnemnt table
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */

	private VBox createUpcomingAppointmentsTable() throws FileNotFoundException {
		ImageView refreshImageView = new ImageView(new Image(new FileInputStream("resources/images/loading_gif.gif")));
		vboxForRefreshImageView = new VBox(refreshImageView);
		refreshImageView.setFitHeight(30);
		refreshImageView.setFitWidth(30);
		vboxForUpcomingAppointment = new VBox(20);
		vboxForUpcomingAppointment.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(.5))));
		vboxForUpcomingAppointment.setPadding(new Insets(10));
		Label label = new Label("Upcoming Appointments");
		label.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		refreshImageView.setVisible(true);
		refreshImageView.setManaged(true);
		// Fetch data and create the TableView
		TableView<Appointment> appointmentTableView = createAppointmentTable();
		// Hide the loading gif after fetching the data
		refreshImageView.setVisible(false);
		refreshImageView.setManaged(false);

		/// Adding patient link calling the utility methods
		Button btn = new Button();
		StackPane plusStackPane = Utilty.addPatientCalled(btn, stage);
		plusStackPane.setPadding(new Insets(0, 0, 0, 400));
		// Add the label and the TableView to the VBox
		vboxForUpcomingAppointment.getChildren().addAll(label, appointmentTableView, plusStackPane);
		return vboxForUpcomingAppointment;
	}
/****
 * craeting th eupcoming appointmet table
 * @return tableview of upcoming appointment table
 * @throws FileNotFoundException
 */
	
	
	private TableView<Appointment> createAppointmentTable() throws FileNotFoundException {
		// Create and populate the TableView with upcoming appointments data
		TableView<Appointment> appointmentsTable = new TableView<>();
		// Add columns to the TableView (e.g., Name, Ordering Provider, Date Ordered)
		TableColumn<Appointment, String> nameColumn = new TableColumn<>();
		nameColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		  Label headerLabelForName = new Label("Name");
		  headerLabelForName.setFont(new Font("Arial", 14));       // Set font size and family
		  headerLabelForName.setTextFill(Color.BLACK);               // Set text color
		  headerLabelForName.setStyle("-fx-background-color:transparent" ); // Set background color
		HBox nameHeaderHBox=new HBox(headerLabelForName);
		nameHeaderHBox.setAlignment(Pos.CENTER_LEFT);
		nameColumn.setGraphic(nameHeaderHBox);
		TableColumn<Appointment, String> orderingProviderColumn = new TableColumn<>();
		orderingProviderColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		  Label headerLabelForOrderingProvider = new Label("Ordering Provider");
		  headerLabelForOrderingProvider.setFont(new Font("Arial", 14));       // Set font size and family
		  headerLabelForOrderingProvider.setTextFill(Color.BLACK);               // Set text color
		  headerLabelForOrderingProvider.setStyle("-fx-background-color:transparent" ); // Set background color
		HBox orderingProviderHeaderHBox=new HBox( headerLabelForOrderingProvider);
		 orderingProviderHeaderHBox.setAlignment(Pos.CENTER_LEFT);
		orderingProviderColumn.setGraphic( orderingProviderHeaderHBox);
		TableColumn<Appointment, LocalDate> dateOrderedColumn = new TableColumn<>();
		dateOrderedColumn.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		  Label headerLabelForDateOrdered = new Label("Date Ordered");
		  headerLabelForDateOrdered .setFont(new Font("Arial", 14));       // Set font size and family
		  headerLabelForDateOrdered .setTextFill(Color.BLACK);               // Set text color
		  headerLabelForDateOrdered .setStyle("-fx-background-color:transparent" ); // Set background color
		HBox dateOrderedHeaderHBox=new HBox(  headerLabelForDateOrdered);
		dateOrderedHeaderHBox.setAlignment(Pos.CENTER_LEFT);
		dateOrderedColumn.setGraphic( dateOrderedHeaderHBox);
		TableColumn<Appointment, Button> imageUpload = new TableColumn<>();
		imageUpload.setStyle("-fx-background-color:white;-fx-text-fill:black;");
		appointmentsTable.setStyle("-fx-background-color: transparent");
		  Label headerLabelForUpload = new Label("Upload");
		  headerLabelForUpload.setFont(new Font("Arial", 14));       // Set font size and family
		  headerLabelForUpload .setTextFill(Color.BLACK);               // Set text color
		  headerLabelForUpload.setStyle("-fx-background-color:transparent" ); // Set background color
		HBox uploadHeaderHBox=new HBox(headerLabelForUpload);
		uploadHeaderHBox.setAlignment(Pos.CENTER_LEFT);
		 imageUpload.setGraphic(uploadHeaderHBox);
		appointmentsTable.getColumns().addAll(nameColumn, orderingProviderColumn, dateOrderedColumn, imageUpload);
		Image image1 = new Image(new FileInputStream("resources/images/Help.png"));
		ImageView imageView1 = new ImageView(image1);
		imageView1.setFitHeight(12);
		imageView1.setFitWidth(12);
		Image image2 = new Image(new FileInputStream("resources/images/right arrow.jfif"));
		ImageView imageView2 = new ImageView(image2);
		imageView2.setFitWidth(12);
		imageView2.setFitHeight(12);
		ObservableList<Appointment> imgList = FXCollections.observableArrayList();
		nameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getName()));
		orderingProviderColumn
				.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getOrderingProvider()));
		dateOrderedColumn.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getOrderedDate()));
		imageUpload.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getBtn()));
		Map<HttpsConnectors.RESPONSE, String> responseListForPatient = getThisMonthData(userName, passWord);
		String jsonResponse = responseListForPatient.get(HttpsConnectors.RESPONSE.VALUE);
		System.out.println("json string response for upcoming data is " + jsonResponse);
		Gson gson = new Gson();
		// parsing the json string to json object
		JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
		JsonArray patientList = jsonObject.getAsJsonArray("patient_list");

		for (JsonElement patientElement : patientList) {
		    JsonObject patientObject = patientElement.getAsJsonObject();

		    String patientName = patientObject.get("patientName").getAsString();
		    int patientId = patientObject.get("patientId").getAsInt();
		    int caseId = patientObject.get("caseId").getAsInt();
		    String caseStatus = patientObject.get("caseStatus").getAsString();
		    
		    String orderingProvider = "";  // Initialize with empty string

		    if (patientObject.has("orderingProvider")) {
		        if (!patientObject.get("orderingProvider").isJsonNull()) {
		            String value = patientObject.get("orderingProvider").getAsString();
		            // Check if the value is "null" string and convert to empty string
		            orderingProvider = (value == null || value.equals("null")) ? "" : value;

		        } else {
		            System.out.println("orderingProvider is JsonNull");
		            orderingProvider = "";
		        }
		    } else {
		        System.out.println("No orderingProvider field found");
		        orderingProvider = "";
		    }

		    // Handle the dateCreated value
		    LocalDate dateCreated = null;
		    JsonElement dateCreatedElement = patientObject.get("dateCreated");
		    if (dateCreatedElement != null && !dateCreatedElement.isJsonNull()) {
		        try {
		            dateCreated = Utilty.parseDate(dateCreatedElement.getAsString());
		        } catch (IllegalArgumentException e) {
		            System.err.println("Error parsing dateCreated: " + e.getMessage());
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
		        }
		    }

		    // Handle uploadedBy with null check
		    String uploadedByPatient = patientObject.has("uploadedBy") && !patientObject.get("uploadedBy").isJsonNull() 
		        ? patientObject.get("uploadedBy").getAsString() 
		        : "";

		    // Add new Patient data with null checks
		    imgList.add(
		        new Appointment(patientName, orderingProvider, dateCreated, btnForPatient(patientId, patientName))
		    );
		}

		// Set the column resize policy to constrained
		appointmentsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		/// enable sorting based on order craetd date when table is loaded
        appointmentsTable.setItems(imgList);
        dateOrderedColumn.setSortable(true);
		dateOrderedColumn.setSortType(TableColumn.SortType.ASCENDING); // Set the default sort order
		appointmentsTable.getSortOrder().add(dateOrderedColumn);  
		return appointmentsTable;

	}

	/**
	 * method creating button for each patient
	 * 
	 * @param patientId
	 * @param caseId
	 * @return
	 * @throws FileNotFoundException
	 */

	private Button btnForPatient(int patientId, String patientName) throws FileNotFoundException {
		// System.out.println("case id for this patient is "+caseId);
		System.out.println("patient id for this patient is" + patientId);
		Image shortcutImage = new Image(new FileInputStream("resources/images/vertical_png.png"));
		ImageView shortCutImageView = new ImageView(shortcutImage);

		shortCutImageView.setFitWidth(15);
		shortCutImageView.setFitHeight(15);
		Button btnForUploadForUpcomingAppointmanent = new Button();

		btnForUploadForUpcomingAppointmanent
				.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
		btnForUploadForUpcomingAppointmanent.setGraphic(shortCutImageView);
		btnForUploadForUpcomingAppointmanent.setOnAction(e -> {
			// System.out.println("patient case id is "+caseId);
			System.out.println(patientName);
			System.out.println("patint id is " + patientId);
			UploadResult uploadforUpcomingAppointment = new UploadResult();
			try {
				uploadforUpcomingAppointment.displayUpload(stage, borderPane, "uploadPatientFromDashBoard", pane, userName, passWord,
						siteCombo, patientName, patientId, patientRectangle, uploadRectangle, dashboardRectangle,
						uploadbtn, dashBoardBtn, patientBtn, uploadImageView, dashBoardImageView, patientImageView);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		return btnForUploadForUpcomingAppointmanent;
	}

	/***
	 * method for getting the this month data
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */

	private Map<HttpsConnectors.RESPONSE, String> getThisMonthData(String userName, String password) {

		String credString = new EyePacsEncryptorAES().encryptEyePacsCredentials(userName, password);
		// Original JSON string
		String jsonString = "{\"dateString\" : \"20240801\", \"caseStatus\" : \"scheduled\"}";

		// Call method to get the current date
		String thisMonthDate = getThisMonthDate();

		// Replace "20240801" with the new date string
		jsonString = jsonString.replace("20240801", thisMonthDate);
		Utilty utilty = new Utilty();
		String urlForLastSixMonth = "/uploader/patient/list/byDate/"
				+ utilty.getSiteIdFromComboBox(userName, password, siteCombo);
		HttpsConnectors connector = new HttpsConnectors(HttpsConnectors.SERVER.LOCALHOST, urlForLastSixMonth,
				HttpsConnectors.METHOD.POST);
		Map<HttpsConnectors.RESPONSE, String> response = connector.apiConnect(jsonString, credString, "text/plain",
				"application/json");
		System.out.println("Response = " + response.get(HttpsConnectors.RESPONSE.CODE));
		System.out.println("Response value = " + response.get(HttpsConnectors.RESPONSE.VALUE));
		return response;

	}

	/***
	 * method to get the this month date
	 * 
	 * @return
	 */
	public static String getThisMonthDate() {
		LocalDate firstDayOfThisMonth = LocalDate.now().withDayOfMonth(1);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		return firstDayOfThisMonth.format(formatter);
	}

}