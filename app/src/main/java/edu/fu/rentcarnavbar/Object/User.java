package edu.fu.rentcarnavbar.Object;

public class User {

    //declare variables of object User
    private String id;
    private String phone;
    //private String password;
    private String name;
    private String email;
    private String address;
    private String identity;
    private int status;

    //Define constructor has parameters
    public User(String id, String phone, String name, String email, String address,
                String identity, int status) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.email = email;
        this.address = address;
        this.identity = identity;
        this.status = status;
    }

    public User(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    //Define constructor have not parameter
    public User() { }

    /** Getter & Setter of object User */
    //region Getter & Setter
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
    //endregion
}
