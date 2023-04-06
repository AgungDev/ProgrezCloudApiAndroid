package fun5i.app.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Subtask {

@SerializedName("total")
@Expose
private String total;
@SerializedName("status_done")
@Expose
private String statusDone;
@SerializedName("flying_id")
@Expose
private String flyingId;

/**
* No args constructor for use in serialization
*
*/
public Subtask() {
}

/**
*
* @param total
* @param flyingId
* @param statusDone
*/
public Subtask(String total, String statusDone, String flyingId) {
super();
this.total = total;
this.statusDone = statusDone;
this.flyingId = flyingId;
}

public String getTotal() {
return total;
}

public void setTotal(String total) {
this.total = total;
}

public String getStatusDone() {
return statusDone;
}

public void setStatusDone(String statusDone) {
this.statusDone = statusDone;
}

public String getFlyingId() {
return flyingId;
}

public void setFlyingId(String flyingId) {
this.flyingId = flyingId;
}

}