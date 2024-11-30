package com.autouploader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

import java.time.LocalDate;
import java.util.Date;

public class Appointment {
    private String name;
    private String orderingProvider;

    private LocalDate orderedDate;
   // private ImageView image;
    private Button btn;
    public Appointment(String name, String orderingProvider, LocalDate orderedDate, Button btn) {
        this.name = name;
        this.orderingProvider = orderingProvider;
        this.orderedDate = orderedDate;
        this.btn = btn;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrderingProvider() {
        return orderingProvider;
    }

    public void setOrderingProvider(String orderingProvider) {
        this.orderingProvider = orderingProvider;
    }

    public LocalDate getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDate orderedDate) {
        this.orderedDate = orderedDate;
    }
	public Button getBtn() {
		return btn;
	}

	public void setBtn(Button btn) {
		this.btn = btn;
	}

  



	

  

}






