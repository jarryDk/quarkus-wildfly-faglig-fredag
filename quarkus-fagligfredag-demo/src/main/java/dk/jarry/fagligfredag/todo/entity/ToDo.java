package dk.jarry.fagligfredag.todo.entity;

import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Date;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.QueryHint;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author Michael Bornholdt Nielsen
 */
@Entity
@Table(name = "todos")
@NamedQuery( //
		name = "ToDos.findAll", //
		query = "SELECT f FROM ToDo f ORDER BY f.subject", //
		hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class ToDo {

	@Id
	@SequenceGenerator( //
			name = "todosSequence", //
			sequenceName = "todos_id_seq", //
			allocationSize = 1, //
			initialValue = 10)
	@GeneratedValue( //
			strategy = GenerationType.SEQUENCE, //
			generator = "todosSequence")
	private Integer id;

	private String subject;
	
	@Column(columnDefinition="TEXT")
	private String body;

	@Temporal(TIMESTAMP)
	private Date createdDate;
	@Temporal(TIMESTAMP)
	private Date updatedDate;

	@Temporal(TIMESTAMP)
	private Date startDate;
	@Temporal(TIMESTAMP)
	private Date endDate;

	private Integer priority;
	private Integer importens;

	private String owner;
	
	private String createBy;
	private String updatedBy;

	public ToDo() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getImportens() {
		return importens;
	}

	public void setImportens(Integer importens) {
		this.importens = importens;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

}
