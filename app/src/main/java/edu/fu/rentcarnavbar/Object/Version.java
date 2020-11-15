package edu.fu.rentcarnavbar.Object;

public class Version {
    int vs_id;
    String version;

    public Version(int vs_id, String version) {
        this.vs_id = vs_id;
        this.version = version;
    }

    public int getVs_id() {
        return vs_id;
    }

    public String getVersion() {
        return version;
    }
}
