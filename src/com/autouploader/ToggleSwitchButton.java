package com.autouploader;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class ToggleSwitchButton extends HBox {
	private final Label label = new Label();
	private final Button button = new Button();
	private final SimpleBooleanProperty switchedOn = new SimpleBooleanProperty(false);
	 Properties properties2 = new Properties();
	// Properties properties3=new Properties();
	private final String propertiesFile = "settings.properties";
	public SimpleBooleanProperty switchOnProperty() {
		return switchedOn;
	}

	private void init() {
		loadProperties(); // Load properties from file

		// Set initial state based on loaded properties
		switchedOn.set(Boolean.parseBoolean(properties2.getProperty("state", "false")));
		updateStateLabel();

		getChildren().addAll(label, button);
		button.setOnAction((e) -> {
			switchedOn.set(!switchedOn.get());
			saveProperties(); // Save state to properties file
		});
		label.setOnMouseClicked((e) -> {
			switchedOn.set(!switchedOn.get());
			saveProperties(); // Save state to properties file
		});
		setStyle();
		bindProperties();
	}

	private void setStyle() {
		setWidth(70);
		setStyle("-fx-background-color: grey; -fx-text-fill:black; -fx-background-radius: 5;");
	}

	private void bindProperties() {
		label.prefWidthProperty().bind(widthProperty().divide(2));
		label.prefHeightProperty().bind(heightProperty());
		button.prefWidthProperty().bind(widthProperty().divide(2));
		button.prefHeightProperty().bind(heightProperty());
	}

	private void updateStateLabel() {
		if (switchedOn.get()) {
			label.setText("ON");
			setStyle("-fx-background-color: green;");
			label.toFront();
		} else {
			label.setText("OFF");
			setStyle("-fx-background-color: orange;");
			button.toFront();
		}
	}

	private void saveProperties() {
		properties2.setProperty("state", String.valueOf(switchedOn.get()));
		try {
			FileOutputStream fos = new FileOutputStream(propertiesFile);
			properties2.store(fos, "state");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	private void loadProperties() {
		try {
			FileInputStream fis = new FileInputStream(propertiesFile);
			properties2.load(fis);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public ToggleSwitchButton() {
		init();
		switchedOn.addListener((a, b, c) -> updateStateLabel());
	}
	 
}
