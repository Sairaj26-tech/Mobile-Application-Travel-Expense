package com.example.myapplication;


import java.util.Date;

public class Trip{
   int Tid;
   String Tname;
   String TDestination;
   String TDate;
   String TDescription;
   boolean TRisk;
   boolean TDelete;
   boolean TActive;
   Date TCreatedDate;
   int TCreatedBy;
   Date TModifiedDate;
   int TModifiedBy;
   Date TDeletedDate;
   int TDeletedBy;

    public Trip() {
    }

    public Trip(int Tid, String Tname) {
        this.Tid = Tid;
        this.Tname = Tname;
    }

    public int getTid() {
        return Tid;
    }

    public void setTid(int tid) {
        Tid = tid;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public String getTDestination() {
        return TDestination;
    }

    public void setTDestination(String TDestination) {
        this.TDestination = TDestination;
    }

    public String getTDate() {
        return TDate;
    }

    public void setTDate(String TDate) {
        this.TDate = TDate;
    }

    public String getTDescription() {
        return TDescription;
    }

    public void setTDescription(String TDescription) {
        this.TDescription = TDescription;
    }

    public boolean isTRisk() {
        return TRisk;
    }

    public void setTRisk(boolean TRisk) {
        this.TRisk = TRisk;
    }

    public boolean isTDelete() {
        return TDelete;
    }

    public void setTDelete(boolean TDelete) {
        this.TDelete = TDelete;
    }

    public boolean isTActive() {
        return TActive;
    }

    public void setTActive(boolean TActive) {
        this.TActive = TActive;
    }

    public Date getTCreatedDate() {
        return TCreatedDate;
    }

    public void setTCreatedDate(Date TCreatedDate) {
        this.TCreatedDate = TCreatedDate;
    }

    public int getTCreatedBy() {
        return TCreatedBy;
    }

    public void setTCreatedBy(int TCreatedBy) {
        this.TCreatedBy = TCreatedBy;
    }

    public Date getTModifiedDate() {
        return TModifiedDate;
    }

    public void setTModifiedDate(Date TModifiedDate) {
        this.TModifiedDate = TModifiedDate;
    }

    public int getTModifiedBy() {
        return TModifiedBy;
    }

    public void setTModifiedBy(int TModifiedBy) {
        this.TModifiedBy = TModifiedBy;
    }

    public Date getTDeletedDate() {
        return TDeletedDate;
    }

    public void setTDeletedDate(Date TDeletedDate) {
        this.TDeletedDate = TDeletedDate;
    }

    public int getTDeletedBy() {
        return TDeletedBy;
    }

    public void setTDeletedBy(int TDeletedBy) {
        this.TDeletedBy = TDeletedBy;
    }
}
