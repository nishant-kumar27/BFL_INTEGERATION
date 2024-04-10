package com.bfl.api.staging.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "RIT_STAGED_MESSAGE")
public class StageMessage {
	
	@Id
	@SequenceGenerator(sequenceName = "RIT_STAGED_MESSAGE_SEQ", allocationSize = 1, name = "MESSAGE_SEQ")
	@GeneratedValue(generator = "MESSAGE_SEQ")
    private Long id;

    @Column(name = "MESSAGE_DESCRIPTION", nullable = true)
    private String messageDescription;

    @Column(name = "SOURCE", nullable = false)
    private String source;
    
    @Column(name = "DESTINATION", nullable = false)
    private String destination;
    
    @Column(name = "MESSAGE_TYPE", nullable = false)
    private String messageType;

    @Column(name = "MESSAGE_FAMILY", nullable = false)
    private String messageFamily;
    
    @Column(name = "RETRY_COUNT", nullable = false)
    private int retryCount = 0;

    @Column(name = "PROCESSED", nullable = false)
    private String processed = "N";
    
    @Column(name = "DELETED", nullable = false)
    private String deleted = "N";
    
    @Column(name="MESSAGE_DATA", columnDefinition="CLOB NOT NULL") 
    @Lob 
    private String messageData;
    
    @Column(name = "MESSAGE_ERROR", columnDefinition="CLOB NOT NULL")
    private String messageError;
    
    @Column(name = "JOB_ID", nullable = false)
    private String jobId;
    
    @Column(name = "CREATE_DATE_TIME", nullable = false)
    private String createDateTime;
    
    @Column(name = "UPDATE_DATE_TIME", nullable = true)
    private String updateDateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(String createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(String updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

	@Override
	public String toString() {
		return "StageMessage [id=" + id + ", messageDescription=" + messageDescription + ", source=" + source
				+ ", destination=" + destination + ", messageType=" + messageType + ", messageFamily=" + messageFamily
				+ ", retryCount=" + retryCount + ", processed=" + processed + ", deleted=" + deleted + ", messageData="
				+ messageData + ", messageError=" + messageError + ", jobId=" + jobId + ", createDateTime="
				+ createDateTime + ", updateDateTime=" + updateDateTime + "]";
	}
    
    
}
