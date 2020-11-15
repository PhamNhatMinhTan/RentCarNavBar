package edu.fu.rentcarnavbar.Object;

public class Version {
    int vs_id;
    String version;

    public Version(int vs_id, String version) {
        this.vs_id = vs_id;
        this.version = version;
    }

    public Version() {
    }

    public int getVs_id() {
        return vs_id;
    }

    public void setVs_id(int vs_id) {
        this.vs_id = vs_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
