package com.example.franciscoandrade.calendarmobile.model;

public class Day {
    
        private int dayNumber;
        private DayDetails dayDetailsList;
        public int getDayNumber() {
            return dayNumber;
        }

        public void setDayNumber(int dayNumber) {
            this.dayNumber = dayNumber;
        }

        public DayDetails getDayDetailsList() {
            return dayDetailsList;
        }

        public void setDayDetailsList(DayDetails dayDetailsList) {
            this.dayDetailsList = dayDetailsList;
        }
    }



