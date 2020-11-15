package edu.fu.rentcarnavbar.Object;

public class Invoice {
    private int id;
    private String dateStart;
    private String dateEnd;
    private double total;
    private String name;
    private String phone;
    private String identity;
    private int status;
    private int u_id;
    private int v_id;

    public Invoice() {
    }

    public Invoice(int id, String dateStart, String dateEnd, double total, String name, String phone, String identity, int status, int u_id, int v_id) {
        this.id = id;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.total = total;
        this.name = name;
        this.phone = phone;
        this.identity = identity;
        this.status = status;
        this.u_id = u_id;
        this.v_id = v_id;
    }

    public Invoice(String dateStart, String dateEnd, double total, String name, String phone, String identity, int status, int u_id, int v_id) {
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.total = total;
        this.name = name;
        this.phone = phone;
        this.identity = identity;
        this.status = status;
        this.u_id = u_id;
        this.v_id = v_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getU_id() {
        return u_id;
    }

    public void setU_id(int u_id) {
        this.u_id = u_id;
    }

    public int getV_id() {
        return v_id;
    }

    public void setV_id(int v_id) {
        this.v_id = v_id;
    }
}
