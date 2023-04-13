package fun5i.app.api.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

// update 3.0.0
public class Subtask {

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
    @SerializedName("total")
    @Expose
    private String total;

    /**
     * No args constructor for use in serialization
     *
     */
    public Subtask() {
    }

    /**
     *
     * @param tasktype
     * @param quantity
     * @param flyingId
     * @param author
     * @param datetimeDone
     * @param description
     * @param privacy
     * @param debitcredit
     * @param datetime
     * @param total
     * @param filenya
     * @param nominal
     * @param statusDone
     * @param sticky
     * @param taskName
     */
    public Subtask(Author author, String tasktype, String taskName, String datetime, String statusDone, String description, String filenya, String nominal, String quantity, String debitcredit, String sticky, String datetimeDone, String privacy, String flyingId, String total) {
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
        this.total = total;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTasktype() {
        return tasktype;
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

    public String getStatusDone() {
        return statusDone ;
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

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getQuantity() {
        return quantity;
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

    public String getPrivacy() {
        return privacy;
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


    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

}