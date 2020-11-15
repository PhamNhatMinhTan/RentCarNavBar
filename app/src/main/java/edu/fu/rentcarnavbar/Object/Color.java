package edu.fu.rentcarnavbar.Object;

public class Color {
    int color_id;
    String color;

    public Color(int color_id, String color) {
        this.color_id = color_id;
        this.color = color;
    }

    public Color() {
    }

    public int getColor_id() {
        return color_id;
    }

    public void setColor_id(int color_id) {
        this.color_id = color_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
