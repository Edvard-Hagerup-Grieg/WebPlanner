package ru.webplanner.models;

public class Note {
    private int id;
    private int sectionId;
    private int date;
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
        return date;
    }

    public void setDayNumber(int dayNumber) {
        this.date = dayNumber;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
