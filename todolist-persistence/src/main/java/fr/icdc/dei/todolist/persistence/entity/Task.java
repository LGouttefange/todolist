package fr.icdc.dei.todolist.persistence.entity;


import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table
public class Task {

    private static final String EUROPEAN_DATE_FORMAT = "dd/MM/yyyy";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_task")
    private long id;

    @Column(nullable = false)
    private String label;

    @Column
    private Date beginningDate;

    @Column
    private Date estimatedEndingDate;

    @Column(name = "endingDate")
    private Date endingDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user")
    private User user;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public void setBeginningDate(Date beginningDate) {
        this.beginningDate = beginningDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;

    }

    public Date getEstimatedEndingDate() {
        return estimatedEndingDate;
    }

    public void setEstimatedEndingDate(Date estimatedEndingDate) {
        this.estimatedEndingDate = estimatedEndingDate;
    }


    public Date getEndingDate() {
        return this.endingDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getBeginningDate() {
        return beginningDate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBeginningDateWithoutTime() {
        return formatDate(beginningDate);
    }

    public String getEstimatedEndingDateWithoutTime() {
        return formatDate(estimatedEndingDate);
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat(EUROPEAN_DATE_FORMAT).format(date);
    }


}
