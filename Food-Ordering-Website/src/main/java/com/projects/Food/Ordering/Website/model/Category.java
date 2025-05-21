package com.projects.Food.Ordering.Website.model;


public enum Category{

    VEG("Veg"), NON_VEG("Non-veg");

    private final String label;

     Category(String label){
        this.label = label;
    }

    public String getLabel(){
         return label;
    }


    public Object getName() {
         return name();
    }
}
