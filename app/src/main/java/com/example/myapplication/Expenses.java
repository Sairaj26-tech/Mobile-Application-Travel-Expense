package com.example.myapplication;

import java.util.Date;

public class Expenses {
    int Eid;
    String EType;
    double EAmount;
    String EDate;
    String EComment;
    boolean EDelete;
    boolean EActive;
    Date ECreatedDate;
    int ECreatedBy;
    Date EModifiedDate;
    int EModifiedBy;
    Date EDeletedDate;
    int EDeletedBy;

    public Expenses() {
    }

    public Expenses(int Eid, String EType) {
        this.Eid = Eid;
        this.EType = EType;
    }

    public int getEid() {
        return Eid;
    }

    public void setEid(int eid) {
        Eid = eid;
    }

    public String getEType() {
        return EType;
    }

    public void setEType(String EType) {
        this.EType = EType;
    }

    public double getEAmount() {
        return EAmount;
    }

    public void setEAmount(double EAmount) {
        this.EAmount = EAmount;
    }

    public String getEDate() {
        return EDate;
    }

    public void setEDate(String EDate) {
        this.EDate = EDate;
    }

    public String getEComment() {
        return EComment;
    }

    public void setEComment(String EComment) {
        this.EComment = EComment;
    }

    public boolean getEDelete() {
        return EDelete;
    }

    public void setEDelete(boolean EDelete) {
        this.EDelete = EDelete;
    }

    public boolean getEActive() {
        return EActive;
    }

    public void setEActive(boolean EActive) {
        this.EActive = EActive;
    }

    public Date getECreatedDate() {
        return ECreatedDate;
    }

    public void setECreatedDate(Date ECreatedDate) {
        this.ECreatedDate = ECreatedDate;
    }

    public int getECreatedBy() {
        return ECreatedBy;
    }

    public void setECreatedBy(int ECreatedBy) {
        this.ECreatedBy = ECreatedBy;
    }

    public Date getEModifiedDate() {
        return EModifiedDate;
    }

    public void setEModifiedDate(Date EModifiedDate) {
        this.EModifiedDate = EModifiedDate;
    }

    public int getEModifiedBy() {
        return EModifiedBy;
    }

    public void setEModifiedBy(int EModifiedBy) {
        this.EModifiedBy = EModifiedBy;
    }

    public Date getEDeletedDate() {
        return EDeletedDate;
    }

    public void setEDeletedDate(Date EDeletedDate) {
        this.EDeletedDate = EDeletedDate;
    }

    public int getEDeletedBy() {
        return EDeletedBy;
    }

    public void setEDeletedBy(int EDeletedBy) {
        this.EDeletedBy = EDeletedBy;
    }


}
