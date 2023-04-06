package fun5i.app.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Project {

@SerializedName("image")
@Expose
private String image;
@SerializedName("status_done")
@Expose
private String statusDone;
@SerializedName("name")
@Expose
private String name;
@SerializedName("privacy")
@Expose
private String privacy;
@SerializedName("progress")
@Expose
private String progress;
@SerializedName("flying_id")
@Expose
private String flyingId;

/**
* No args constructor for use in serialization
*
*/
public Project() {
}

/**
*
* @param image
* @param flyingId
* @param statusDone
* @param name
* @param privacy
* @param progress
*/
public Project(String image, String statusDone, String name, String privacy, String progress, String flyingId) {
super();
this.image = image;
this.statusDone = statusDone;
this.name = name;
this.privacy = privacy;
this.progress = progress;
this.flyingId = flyingId;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getStatusDone() {
return statusDone;
}

public void setStatusDone(String statusDone) {
this.statusDone = statusDone;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getPrivacy() {
return privacy;
}

public void setPrivacy(String privacy) {
this.privacy = privacy;
}

public String getProgress() {
return progress;
}

public void setProgress(String progress) {
this.progress = progress;
}

public String getFlyingId() {
return flyingId;
}

public void setFlyingId(String flyingId) {
this.flyingId = flyingId;
}

}