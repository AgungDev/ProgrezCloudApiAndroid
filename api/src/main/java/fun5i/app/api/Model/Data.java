package fun5i.app.api.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// update 1.2.0
public class Data {

@SerializedName("maintask")
@Expose
private List<Maintask> maintask;
@SerializedName("project")
@Expose
private Project project;

/**
* No args constructor for use in serialization
*
*/
public Data() {
}

/**
*
* @param maintask
* @param project
*/
public Data(List<Maintask> maintask, Project project) {
super();
this.maintask = maintask;
this.project = project;
}

public List<Maintask> getMaintask() {
return maintask;
}

public void setMaintask(List<Maintask> maintask) {
this.maintask = maintask;
}

public Project getProject() {
return project;
}

public void setProject(Project project) {
this.project = project;
}

}