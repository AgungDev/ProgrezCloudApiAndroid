package fun5i.app.api.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Task {

@SerializedName("task_name")
@Expose
private String taskName;
@SerializedName("total")
@Expose
private String total;
@SerializedName("isParent")
@Expose
private String isParent;
@SerializedName("flying_id")
@Expose
private String flyingId;
@SerializedName("subtask")
@Expose
private List<Subtask> subtask;

/**
* No args constructor for use in serialization
*
*/
public Task() {
}

/**
*
* @param total
* @param isParent
* @param flyingId
* @param taskName
* @param subtask
*/
public Task(String taskName, String total, String isParent, String flyingId, List<Subtask> subtask) {
super();
this.taskName = taskName;
this.total = total;
this.isParent = isParent;
this.flyingId = flyingId;
this.subtask = subtask;
}

public String getTaskName() {
return taskName;
}

public void setTaskName(String taskName) {
this.taskName = taskName;
}

public String getTotal() {
return total;
}

public void setTotal(String total) {
this.total = total;
}

public String getIsParent() {
return isParent;
}

public void setIsParent(String isParent) {
this.isParent = isParent;
}

public String getFlyingId() {
return flyingId;
}

public void setFlyingId(String flyingId) {
this.flyingId = flyingId;
}

public List<Subtask> getSubtask() {
return subtask;
}

public void setSubtask(List<Subtask> subtask) {
this.subtask = subtask;
}

}