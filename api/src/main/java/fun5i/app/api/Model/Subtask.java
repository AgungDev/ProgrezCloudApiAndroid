package fun5i.app.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// update 2.0.1
public class Subtask {

    @SerializedName("task_name")
    @Expose
    private String taskName;
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
     * @param taskName
     * @param total
     * @param flyingId
     * @param statusDone
     */
    public Subtask(String taskName, String total, String statusDone, String flyingId) {
        super();
        this.taskName = taskName;
        this.total = total;
        this.statusDone = statusDone;
        this.flyingId = flyingId;
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