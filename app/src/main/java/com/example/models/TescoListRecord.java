package com.example.models;

import java.util.List;

public class TescoListRecord {

    public String image;
    public String superDepartment;
    public int tpnb;
    public String ContentsMeasureType;
    public String name;
    public int UnitOfSale;
    public double AverageSellingUnitWeight;
    public List<String> description;
    public String UnitQuantity;
    public int id;
    public double ContentsQuantity;
    public String department;
    public double price;
    public double unitprice;

    public TescoListRecord(){}

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSuperDepartment() {
        return superDepartment;
    }

    public void setSuperDepartment(String superDepartment) {
        this.superDepartment = superDepartment;
    }

    public int getTpnb() {
        return tpnb;
    }

    public void setTpnb(int tpnb) {
        this.tpnb = tpnb;
    }

    public String getContentsMeasureType() {
        return ContentsMeasureType;
    }

    public void setContentsMeasureType(String contentsMeasureType) {
        ContentsMeasureType = contentsMeasureType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUnitOfSale() {
        return UnitOfSale;
    }

    public void setUnitOfSale(int unitOfSale) {
        UnitOfSale = unitOfSale;
    }

    public double getAverageSellingUnitWeight() {
        return AverageSellingUnitWeight;
    }

    public void setAverageSellingUnitWeight(double averageSellingUnitWeight) {
        AverageSellingUnitWeight = averageSellingUnitWeight;
    }

    public List<String> getDescription() {
        return description;
    }

    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getUnitQuantity() {
        return UnitQuantity;
    }

    public void setUnitQuantity(String unitQuantity) {
        UnitQuantity = unitQuantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getContentsQuantity() {
        return ContentsQuantity;
    }

    public void setContentsQuantity(double contentsQuantity) {
        this.ContentsQuantity = contentsQuantity;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getUnitprice() {
        return unitprice;
    }

    public void setUnitprice(double unitprice) {
        this.unitprice = unitprice;
    }

    @Override
    public String toString() {
        return "TescoListRecord{" +
                "image='" + image + '\'' +
                ", superDepartment='" + superDepartment + '\'' +
                ", tpnb=" + tpnb +
                ", ContentsMeasureType='" + ContentsMeasureType + '\'' +
                ", name='" + name + '\'' +
                ", UnitOfSale=" + UnitOfSale +
                ", AverageSellingUnitWeight=" + AverageSellingUnitWeight +
                ", description=" + description +
                ", UnitQuantity='" + UnitQuantity + '\'' +
                ", id=" + id +
                ", ContentsQuantity=" + ContentsQuantity +
                ", department='" + department + '\'' +
                ", price=" + price +
                ", unitprice=" + unitprice +
                '}';
    }
}
