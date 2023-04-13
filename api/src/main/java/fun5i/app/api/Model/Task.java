package fun5i.app.api.Model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// update 3.0.0
public class Task {

    @SerializedName("author")
    @Expose
    private Author author;
    @SerializedName("tasktype")
    @Expose
    private String tasktype;
    @SerializedName("task_name")
    @Expose
    private String taskName;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("status_done")
    @Expose
    private String statusDone;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("filenya")
    @Expose
    private String filenya;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("debitcredit")
    @Expose
    private String debitcredit;
    @SerializedName("sticky")
    @Expose
    private String sticky;
    @SerializedName("datetime_done")
    @Expose
    private String datetimeDone;
    @SerializedName("privacy")
    @Expose
    private String privacy;
    @SerializedName("flying_id")
    @Expose
    private String flyingId;
    @SerializedName("isParent")
    @Expose
    private String isParent;
    @SerializedName("total")
    @Expose
    private String total;
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
     * @param tasktype
     * @param quantity
     * @param isParent
     * @param flyingId
     * @param author
     * @param datetimeDone
     * @param description
     * @param privacy
     * @param subtask
     * @param debitcredit
     * @param datetime
     * @param total
     * @param filenya
     * @param nominal
     * @param statusDone
     * @param sticky
     * @param taskName
     */
    public Task(Author author, String tasktype, String taskName, String datetime, String statusDone, String description, String filenya, String nominal, String quantity, String debitcredit, String sticky, String datetimeDone, String privacy, String flyingId, String isParent, String total, List<Subtask> subtask) {
        super();
        this.author = author;
        this.tasktype = tasktype;
        this.taskName = taskName;
        this.datetime = datetime;
        this.statusDone = statusDone;
        this.description = description;
        this.filenya = filenya;
        this.nominal = nominal;
        this.quantity = quantity;
        this.debitcredit = debitcredit;
        this.sticky = sticky;
        this.datetimeDone = datetimeDone;
        this.privacy = privacy;
        this.flyingId = flyingId;
        this.isParent = isParent;
        this.total = total;
        this.subtask = subtask;
    }


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public int getTasktype() {
        return Integer.parseInt(tasktype);
    }

    public void setTasktype(String tasktype) {
        this.tasktype = tasktype;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public boolean getStatusDone() {
        return Boolean.parseBoolean(statusDone);
    }

    public void setStatusDone(String statusDone) {
        this.statusDone = statusDone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilenya() {
        return filenya;
    }

    public void setFilenya(String filenya) {
        this.filenya = filenya;
    }

    public int getNominal() {
        return Integer.parseInt(nominal);
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public int getQuantity() {
        return Integer.parseInt(quantity);
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getDebitcredit() {
        return debitcredit;
    }

    public void setDebitcredit(String debitcredit) {
        this.debitcredit = debitcredit;
    }

    public String getSticky() {
        return sticky;
    }

    public void setSticky(String sticky) {
        this.sticky = sticky;
    }

    public String getDatetimeDone() {
        return datetimeDone;
    }

    public void setDatetimeDone(String datetimeDone) {
        this.datetimeDone = datetimeDone;
    }

    public int getPrivacy() {
        return Integer.parseInt(privacy);
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    public String getFlyingId() {
        return flyingId;
    }

    public void setFlyingId(String flyingId) {
        this.flyingId = flyingId;
    }

    public boolean getIsParent() {
        return Boolean.parseBoolean(isParent);
    }

    public void setIsParent(String isParent) {
        this.isParent = isParent;
    }

    public int getTotal() {
        return Integer.parseInt(total);
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<Subtask> getSubtask() {
        return subtask;
    }

    public void setSubtask(List<Subtask> subtask) {
        this.subtask = subtask;
    }

}