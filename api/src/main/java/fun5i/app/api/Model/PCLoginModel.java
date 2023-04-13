package fun5i.app.api.Model;

/**
 *
 * @author fun5i
 */
// update 1.2.0
public class PCLoginModel {

    private String flying_id,fullname,photo;
    private PCCredentials credentials;

    public PCLoginModel(String flyingID, String fullname, String photo, PCCredentials cs){
        this.flying_id=flyingID;
        this.fullname= fullname;
        this.photo=photo;
        this.credentials=cs;
    }

    public String getFlying_id() {
        return flying_id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getPhoto() {
        return photo;
    }

    public PCCredentials getCredentials() {
        return credentials;
    }


}
