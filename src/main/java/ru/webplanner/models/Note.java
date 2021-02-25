package ru.webplanner.models;

import java.sql.Types;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Note {
    private int id;
    private int sectionId;
    private int dayNumber;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthId() {
        return sectionId;
    }

    public void setMonthId(int monthId) {
        this.sectionId = monthId;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
