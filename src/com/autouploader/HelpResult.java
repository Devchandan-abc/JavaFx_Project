package com.autouploader;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.*;

public class HelpResult {
	private Stage stage;
	private BorderPane pane;
	private Pane fixedRectangleStackPane;
	private Button backButton;

	public void displayPatientResult(Stage stage, BorderPane borderPane, String result, Pane fixedRectangleStackPane)
			throws FileNotFoundException {

		this.stage = stage;
		this.fixedRectangleStackPane = fixedRectangleStackPane;
		this.pane = borderPane;
		VBox vBoxForMainHelp = new VBox(30);
		VBox helpResultContentVBox = new VBox(15);
		VBox VboxForContactUs = new VBox(15);

		Label helpLabel = new Label("How can we help?");
		helpLabel.setFont(Font.font("Arial", FontWeight.BOLD, 35));
		helpLabel.setPadding(new Insets(30, 0, 0, 20));

		Screen screeen = Screen.getPrimary();
		fixedRectangleStackPane.setPrefWidth(screeen.getVisualBounds().getWidth() * .8);

		vBoxForMainHelp.setPrefWidth(screeen.getVisualBounds().getWidth() * .9);
		HBox hboxMainContent = new HBox();
		// Image
		Image faqImage = new Image(new FileInputStream("resources/images/help image.png"));
		ImageView imageView = new ImageView(faqImage);
		imageView.setFitWidth(60); // Set the width as per your requirement
		imageView.setFitHeight(50); // Set the height as per your requirement
		HBox hboxForImage = new HBox();
		hboxForImage.getChildren().add(imageView);
		hboxForImage.setPadding(new Insets(0, 0, 0, 100));
		// Image for contact
		Image contactImage = new Image(new FileInputStream("resources/images/contact.png"));
		ImageView contactImageView = new ImageView(contactImage);

		contactImageView.setFitWidth(60);
		contactImageView.setFitHeight(60);
		HBox hboxForContactImage = new HBox();
		hboxForContactImage.getChildren().add(contactImageView);
		hboxForContactImage.setPadding(new Insets(0, 0, 0, 130));

		// Label
		Label infoLabel = new Label("FAQ");
		infoLabel.setPadding(new Insets(0, 0, 0, 100));
		infoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
		// label for image
		Label contactLabel = new Label("Contact Us");
		contactLabel.setPadding(new Insets(0, 0, 0, 100));

		contactLabel.setFont(Font.font("Arial", FontWeight.BOLD, 26));
		// Paragraph
		String paragraphText = "Your paragraph text goes here. Provide any relevant information.<br>In test when we are assigning or removing the site from eye care provider that time not happening in test but in local it is fixed.";
		Text paragraph = new Text(paragraphText);
		paragraph.setWrappingWidth(300); // Set the width as per your requirement
		// Paragraph for ContactUs
		Text contactUsText = new Text(
				"Your paragraph text goes here. Provide any relevant information.<br>In test when we are assigning or removing the site from eye care provider that time not happening in test but in local it is fixed.");
		contactUsText.setWrappingWidth(300);
		// Hyperlink
		Hyperlink helpHyperlink = new Hyperlink("Read FAQ >");
		helpHyperlink.setPadding(new Insets(0, 0, 0, 100));
		helpHyperlink.setOnAction(event -> {
			System.out.println("action started");
			fixedRectangleStackPane.getChildren().clear();
			VBox vboxForHelpMenu = createTextForVBox();
			fixedRectangleStackPane.getChildren().add(vboxForHelpMenu);
		});

		// Hyperlink for contact us
		Hyperlink contactUsHyperLink = new Hyperlink("Get Support >");
		contactUsHyperLink.setPadding(new Insets(0, 0, 0, 100));
		contactUsHyperLink.setOnAction(event -> {
			fixedRectangleStackPane.getChildren().clear();
			VBox VboxForConatactMenu = createTextForVBox();
			fixedRectangleStackPane.getChildren().add(VboxForConatactMenu);
		});

		// Add all elements to the VBox
		helpResultContentVBox.getChildren().addAll(hboxForImage, infoLabel, paragraph, helpHyperlink);
		helpResultContentVBox.setPrefWidth(screeen.getVisualBounds().getWidth() * .3);
		VboxForContactUs.getChildren().addAll(hboxForContactImage, contactLabel, contactUsText, contactUsHyperLink);
		// Clear and add the VBox to the fixedRectangleStackPane
		fixedRectangleStackPane.getChildren().clear();
		// fixedRectangleStackPane.getChildren().addAll(helpResultContentVBox,VboxForContactUs);
		hboxMainContent.getChildren().addAll(helpResultContentVBox, VboxForContactUs);
		VboxForContactUs.setPrefWidth(screeen.getVisualBounds().getWidth()*.3);
		// hboxMainContent.setPadding(new Insets(100));
	
		hboxMainContent.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(.5))));
		// hboxMainContent.setPrefWidth(screeen.getVisualBounds().getWidth()*.7);
		// hboxMainContent.setPrefWidth(900); // Set preferred width
		// hboxMainContent.setPrefHeight(screeen.getVisualBounds().getWidth()*.6); //
		// Set preferred height
		hboxMainContent.setPadding(new Insets(100, 0, 0, 40));
		
		// setting margin above the vbox
		VBox.setMargin(hboxMainContent, new Insets(20));
		vBoxForMainHelp.getChildren().addAll(helpLabel, hboxMainContent);
		fixedRectangleStackPane.getChildren().add(vBoxForMainHelp);
		System.out.println(result);
	}

	private VBox createTextForVBox()

	{

		VBox vboxForHelp = new VBox(10);
		backButton = new Button("< back");
		backButton.setStyle("-fx-background-color: transparent;");
		backButton.setOnAction(e -> {
			try {
				displayPatientResult(stage, pane, "Some Result", fixedRectangleStackPane);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		Label lbl = new Label("Frequently  Asked   Questions");
		VBox vboxForParagaph = new VBox(10);
		Label labelForWorkFlow = new Label("What is the WorkFlow for using the AutoUploader ?");
		labelForWorkFlow.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		lbl.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		String paragraphText = "This is a paragraph of text. It can span  This is a paragraph of text. It can span multiple lines It can span multiple lines  This is a paragraph of text. It can span multiple lines It can span multiple lines  \n"
				+ "It can span multiple lines.\n" + "JavaFX provides various controls for displaying text.";
		Label paragraphLabel = new Label(paragraphText);

		Label labelForWorkContact = new Label("What is the WorkFlow for using the AutoUploader ?");
		labelForWorkContact.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		lbl.setFont(Font.font("Arial", FontWeight.BOLD, 25));
		String paragraphTextForContact = "This is a paragraph of text. It can span  This is a paragraph of text. It can span multiple lines It can span multiple lines  This is a paragraph of text. It can span multiple lines It can span multiple lines  \n"
				+ "It can span multiple lines.\n" + "JavaFX provides various controls for displaying text.";
		Label paragraphLabelForContact = new Label(paragraphTextForContact);
		vboxForParagaph.getChildren().addAll(labelForWorkFlow, paragraphLabel, labelForWorkContact,
				paragraphLabelForContact);

		vboxForHelp.getChildren().addAll(backButton, lbl, vboxForParagaph);
		// VBox.setMargin(vboxForHelp, new Insets(20));
		vboxForParagaph.setBorder(new Border(
				new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(.5))));
		vboxForHelp.setPadding(new Insets(0, 0, 0, 20));

		return vboxForHelp;

	}

}
