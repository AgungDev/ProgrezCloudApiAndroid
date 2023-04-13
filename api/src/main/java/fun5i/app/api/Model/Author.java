package fun5i.app.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// update 3.0.0
public class Author {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("avatar")
    @Expose
    private String avatar;

    /**
     * No args constructor for use in serialization
     *
     */
    public Author() {
    }

    /**
     *
     * @param id
     * @param fullname
     * @param avatar
     */
    public Author(String id, String fullname, String avatar) {
        super();
        this.id = id;
        this.fullname = fullname;
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

}