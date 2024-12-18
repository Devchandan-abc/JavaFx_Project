package com.autouploader;

import java.time.LocalDate;
import javafx.scene.control.Button;
public class Patient {
    private String name;
    private Integer caseId;
    private Integer mrn;
    private String caseStatus;
    private LocalDate dateCreated;
    private String uploadedBy;
    private LocalDate imageAdded;
    private Button btn;
    public Patient(String name, Integer caseId, Integer mrn, String caseStatus, LocalDate dateCreated2,
			LocalDate imageAddedDateTime,String uploadedBy, Button btn) {
		super();
		this.name = name;
		this.caseId = caseId;
		this.mrn = mrn;
		this.caseStatus = caseStatus;
		this.dateCreated = dateCreated2;
		this.uploadedBy = uploadedBy;
		this.imageAdded = imageAddedDateTime;
		this.btn = btn;
	}


    public void setName(String name) {
        this.name = name;
    }

    public Button getBtn() {
		return btn;
	}

	public void setBtn(Button btn) {
		this.btn = btn;
	}

	public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public void setMrn(Integer mrn) {
        this.mrn = mrn;
    }


    public void setCaseStatus(String caseStatus) {
        this.caseStatus = caseStatus;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    // Add getter methods for each property
    public String getName() {
        return name;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public Integer getMrn() {
        return mrn;
    }

    public String getCaseStatus() {
        return caseStatus;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public LocalDate getImageAdded() {
 		return imageAdded;
 	}

 	public void setImageAdded(LocalDate imageAdded) {
 		this.imageAdded = imageAdded;
 	}



}
