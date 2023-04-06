package fun5i.app.api.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maintask {

@SerializedName("task_name")
@Expose
private String taskName;
@SerializedName("total")
@Expose
private String total;
@SerializedName("tasktype")
@Expose
private String tasktype;
@SerializedName("isParent")
@Expose
private String isParent;
@SerializedName("task")
@Expose
private List<Task> task;
@SerializedName("flying_id")
@Expose
private String flyingId;
@SerializedName("views")
@Expose
private Integer views;

/**
* No args constructor for use in serialization
*
*/
public Maintask() {
}

/**
*
* @param total
* @param tasktype
* @param isParent
* @param task
* @param flyingId
* @param taskName
* @param views
*/
public Maintask(String taskName, String total, String tasktype, String isParent, List<Task> task, String flyingId, Integer views) {
super();
this.taskName = taskName;
this.total = total;
this.tasktype = tasktype;
this.isParent = isParent;
this.task = task;
this.flyingId = flyingId;
this.views = views;
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

public String getTasktype() {
return tasktype;
}

public void setTasktype(String tasktype) {
this.tasktype = tasktype;
}

public String getIsParent() {
return isParent;
}

public void setIsParent(String isParent) {
this.isParent = isParent;
}

public List<Task> getTask() {
return task;
}

public void setTask(List<Task> task) {
this.task = task;
}

public String getFlyingId() {
return flyingId;
}

public void setFlyingId(String flyingId) {
this.flyingId = flyingId;
}

public Integer getViews() {
return views;
}

public void setViews(Integer views) {
this.views = views;
}

}