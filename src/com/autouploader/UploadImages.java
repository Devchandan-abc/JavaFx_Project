package com.autouploader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;

import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UploadImages {
	private ImageView imageView;
	private int imageCount = 0;
	private ImageView uploadImageViewStatus;

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public int getImageCount() {
		return imageCount;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	

	public VBox createUpload() {
//		this.uploadImageViewStatus=uploadImageView;
		// Create a GridPane to hold the images
		GridPane imageGrid = new GridPane();
		imageGrid.setPadding(new Insets(10));
		imageGrid.setHgap(10);
		imageGrid.setVgap(10);
		// Create a Rectangle to represent the drag target
		VBox dragTarget = new VBox();

		dragTarget.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
		dragTarget.setPrefSize(100, 300);

		// Setup drag-and-drop events
		dragTarget.setOnDragOver(event -> {
			if (event.getGestureSource() != dragTarget && event.getDragboard().hasFiles()) {
				event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				dragTarget.setStyle("-fx-background-color: white;");
			}
			event.consume();
		});

		dragTarget.setOnDragEntered(event -> {
			if (event.getGestureSource() != dragTarget && event.getDragboard().hasFiles()) {
				// dragTarget.setFill(Color.GREEN);
				dragTarget.setStyle("-fx-background-color: white;");

			}
			event.consume();
		});

		dragTarget.setOnDragExited(event -> {
			// dragTarget.setFill(Color.LIGHTGRAY);
			dragTarget.setStyle("-fx-background-color: white;");
			event.consume();
		});

		dragTarget.setOnDragDropped(event -> {
			Dragboard db = event.getDragboard();
			boolean success = false;
			if (db.hasFiles()) {
				success = true;
				for (File file : db.getFiles()) {
					displayImage(file, imageGrid);
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

		// Button to upload from computer
		Button uploadFromComputerButton = new Button("UPLOAD FROM COMPUTER");
		// uploadContent.getChildren().add(uploadFromComputerButton);
		uploadFromComputerButton.setPrefSize(440, 28);
		uploadFromComputerButton.setStyle("-fx-background-color: orange; " + // Set
				"-fx-text-fill: white; " + // Set the text
				"-fx-font-weight: bold; " + // Set the font weight to bold
				"-fx-border-radius: 10;" + "-fx-background-radius:10;" // Set the border radius (adjust the value as
																		// needed)
		);

		uploadFromComputerButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters()
					.add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif", "*.tiff"));
			Properties properties1 = new Properties();
			try {
				// Load the properties file
				properties1.load(new FileInputStream("settings.properties"));

				// Get the property value and print it out
				String selectedPath = properties1.getProperty("imagepath");
				System.out.println("Selected Value: " + selectedPath);
				fileChooser.setInitialDirectory(new File(selectedPath));

			} catch (IOException ex) {
				ex.printStackTrace();
			}

			File selectedFile = fileChooser.showOpenDialog(null);
			if (selectedFile != null) {
				displayImage(selectedFile, imageGrid);
			}
		});
		
		VBox root = new VBox(10);
		VBox vbox1 = new VBox(10);
		vbox1.setPadding(new Insets(4, 0, 0, 0));
		Label label = new Label("Drag and drop files into this container");
		imageView = new ImageView(new Image("file:C:\\Auto_Uplaoder\\drag.jpg"));
		imageView.setVisible(true);
		imageView.setManaged(true);
		imageView.setFitHeight(80);
		imageView.setFitWidth(100);
		vbox1.getChildren().addAll(uploadFromComputerButton, label, imageView);
		vbox1.setAlignment(Pos.CENTER);

		// Create a root layout and add the components

		imageGrid.setAlignment(Pos.CENTER);

		dragTarget.getChildren().addAll(vbox1, imageGrid);

		root.getChildren().add(dragTarget);
		root.setStyle("-fx-background-color: white;");
		return root;

	}

	public List<Image> loadImages(List<File> selectedFiles) {
		return selectedFiles.stream().map(file -> new Image(file.toURI().toString())).collect(Collectors.toList());

	}

	/***
	 * method to display the image in the grid pane
	 * 
	 * @param file
	 * @param imageGrid
	 */
//	private void displayImage(File file, GridPane imageGrid) {
//		
//		try {
//			String imageType = Files.probeContentType(file.toPath());
//			if (imageType != null && imageType.startsWith("image")) {
//
//				Image image = new Image(new FileInputStream(file));
//				ImageView imageView = new ImageView(image);
//				
//				imageView.setFitWidth(100);
//				imageView.setFitHeight(100);
//				imageView.setPreserveRatio(true);
//
//				int column = imageCount % 4;
//				int row = imageCount / 4;
//
//				imageGrid.add(imageView, column, row);
//				imageCount++;
//				  // Hide the dragImageView after the first image is added
//	            if (imageCount > 0) {
//	                this.imageView.setVisible(false);
//	                this.imageView.setManaged(false);
//	            }
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	private void displayImage(File file, GridPane imageGrid) {
//	    try {
//	        String imageType = Files.probeContentType(file.toPath());
//	        if (imageType != null && imageType.startsWith("image")) {
//	            Image image = new Image(new FileInputStream(file));
//
//	            // Create a StackPane to overlay the ImageView and delete button
//	            StackPane imageContainer = new StackPane();
//
//	            ImageView imageView = new ImageView(image);
//	            imageView.setFitWidth(100);
//	            imageView.setFitHeight(100);
//	            imageView.setPreserveRatio(true);
//
//	            // Create the delete button
//	            Button deleteButton = new Button("X");
//	            deleteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 5;");
//	            deleteButton.setPrefSize(25, 25); // Set size for the button
//
//	            // Position the delete button in the top right corner
//	            StackPane.setAlignment(deleteButton, Pos.TOP_RIGHT);
//
//	            // Add action to the delete button
//	            deleteButton.setOnAction(event -> {
//	                imageGrid.getChildren().remove(imageContainer); // Remove the image container from the grid
//	                imageCount--; // Decrease the image count
//	                updateImageViewVisibility(); // Update visibility of dragImageView if needed
//	            });
//
//	            // Add ImageView and delete button to the StackPane
//	            imageContainer.getChildren().addAll(imageView, deleteButton);
//	            imageGrid.add(imageContainer, imageCount % 4, imageCount / 4);
//	            imageCount++;
//	            
//	            // Hide the dragImageView after the first image is added
//	            updateImageViewVisibility();
//	        }
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	    }
//	}
//
//
//	private void updateImageViewVisibility() {
//	    this.imageView.setVisible(imageCount == 0);
//	    this.imageView.setManaged(imageCount == 0);
//	}
	private void displayImage(File file, GridPane imageGrid) {
		try {
			String imageType = Files.probeContentType(file.toPath());
			if (imageType != null && imageType.startsWith("image")) {
				Image image = new Image(new FileInputStream(file));

				// Create a StackPane to overlay the ImageView and delete button
				VBox imageContainer = new VBox();

				ImageView imageView = new ImageView(image);
				imageView.setFitWidth(100);
				imageView.setFitHeight(100);
				imageView.setPreserveRatio(true);
				// Create the delete button
				Button deleteButton = new Button();
				ImageView deleteIconImageView = new ImageView(
						new Image(new FileInputStream("resources/images/Exit.png")));
				deleteButton.setGraphic(deleteIconImageView);
				deleteIconImageView.setFitHeight(5);
				deleteIconImageView.setFitWidth(5);
				HBox buttonContainer = new HBox(25,deleteButton);
				buttonContainer.setAlignment(Pos.TOP_RIGHT); 
				// Align the button to the right
	            deleteButton.setOnAction(event -> {
	                // Create a dialog for custom confirmation
	                Dialog<String> dialog = new Dialog<>();
	                dialog.setTitle("Delete Image");
	            dialog.initStyle(StageStyle.UNDECORATED);
	            
	                // Create the dialog pane (content area)
	                DialogPane dialogPane = dialog.getDialogPane();
	             // dialogPane.setPrefHeight();
	                dialog.setOnShown(ev -> {
		                // Get screen coordinates of deleteButton
		                Bounds buttonBounds = deleteButton.localToScreen(deleteButton.getBoundsInLocal());
		                // Position dialog to the left of the delete button
		                dialog.setX(buttonBounds.getMaxX());
		                dialog.setY(buttonBounds.getMinY());
		            });
	                
	                // Create hyperlinks for the options
	                Hyperlink deleteFromGridLink = new Hyperlink("Delete from Here");
	                Hyperlink deleteFromFileSystemLink = new Hyperlink("Delete from Folder");
	                
	                // Add action listeners for the hyperlinks
	                deleteFromGridLink.setOnAction(e -> {
	                    // Option: Delete from Grid only
	                    imageGrid.getChildren().remove(imageContainer); // Remove from grid
	                    imageCount--; // Decrease the image count
	                    shiftImages(imageGrid); // Shift images to fill the gap
	                    dialog.close(); // Close the dialog
	                    updateImageViewVisibility(); // Update visibility of dragImageView if needed
	                    
	                });
	                
	                deleteFromFileSystemLink.setOnAction(e -> {
	                    // Option: Delete from both Grid and File System
	                    imageGrid.getChildren().remove(imageContainer); // Remove from grid
	                    imageCount--; // Decrease the image count
	                    shiftImages(imageGrid); // Shift images to fill the gap
	                    updateImageViewVisibility(); // Update visibility of dragImageView if needed
	                    
	                    // Also delete the file from the file system
	                    File fileToDelete = new File("path/to/your/image.png"); // Replace with the correct file path
	                    if (fileToDelete.exists()) {
	                        boolean deleted = fileToDelete.delete(); // Delete the file
	                       
	                    }
	                    dialog.close(); // Close the dialog
	                });
	                HBox hboxfordeleteFromGrid=new HBox();
	                try {
						hboxfordeleteFromGrid.getChildren().addAll(deleteFromGridLink,createHeaderHBox());
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
	                
	                VBox vboxForHyperLinks=new VBox();
	                vboxForHyperLinks.getChildren().addAll(hboxfordeleteFromGrid,deleteFromFileSystemLink);
	              //  HBox content = null;
//					try {
//						content = new HBox(vboxForHyperLinks,createHeaderHBox());
//					} catch (FileNotFoundException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
	                dialogPane.setContent(vboxForHyperLinks);
	      
	                // Show the dialog
	                dialog.showAndWait();
	            });
		
				// Add ImageView and delete button to the StackPane
				imageContainer.getChildren().addAll(buttonContainer, imageView);
				imageGrid.add(imageContainer, imageCount % 4, imageCount / 4);
				imageCount++;

				// Hide the dragImageView after the first image is added
				updateImageViewVisibility();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private HBox createHeaderHBox() throws FileNotFoundException {
		HBox headerHBox = new HBox(30);
		Button btnForClosingDialog = new Button();

		btnForClosingDialog.setStyle("-fx-background-color: rgba(0, 0, 0, 0);  -fx-border-color: rgba(0, 0, 0, 0);");
		Image closeImage = new Image(new FileInputStream("resources/images/Exit.png"));
		ImageView imageView = new ImageView(closeImage);
		imageView.setFitWidth(5); // Set your desired width
		imageView.setFitHeight(5); // Set your desired height
		btnForClosingDialog.setGraphic(imageView);
		btnForClosingDialog.setOnAction(e -> {
			Stage stage = (Stage) btnForClosingDialog.getScene().getWindow();
			stage.close();
		});
		headerHBox.setAlignment(Pos.TOP_RIGHT);
		// Add components to the HBox
		headerHBox.getChildren().addAll(btnForClosingDialog);
		return headerHBox;
	}

	private void shiftImages(GridPane imageGrid) {
		// Create a new list to hold the current visible images
		List<Node> visibleImages = new ArrayList<>();

		// Collect all visible images
		for (Node node : imageGrid.getChildren()) {
			if (node.isVisible()) {
				visibleImages.add(node);
			}
		}

		// Clear the GridPane and re-add the visible images
		imageGrid.getChildren().clear();
		for (int i = 0; i < visibleImages.size(); i++) {
			imageGrid.add(visibleImages.get(i), i % 4, i / 4);
		}

		// Update imageCount
		imageCount = visibleImages.size();
	}

	private void updateImageViewVisibility() {
		this.imageView.setVisible(imageCount == 0);
		this.imageView.setManaged(imageCount == 0);
	}

}
