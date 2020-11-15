package edu.fu.rentcarnavbar.Object;

public class InvoiceList {
    private int id;
    private String name;
    private String dateStart;
    private String dateEnd;
    private String image;
    private double total;

    public InvoiceList() {
    }

    public InvoiceList(int id, String name, String dateStart, String dateEnd, String image, double total) {
        this.id = id;
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.image = image;
        this.total = total;
    }

    public InvoiceList(String name, String dateStart, String dateEnd, String image, double total) {
        this.name = name;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.image = image;
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
