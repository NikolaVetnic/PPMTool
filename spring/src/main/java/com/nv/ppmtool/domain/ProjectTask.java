package com.nv.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, updatable = false)
    private String projectSequence;

    @NotBlank(message = "Please include a project task summary")
    private String summary;

    private String acceptanceCriteria;

    private String status;

    private Integer priority;

    private Date dueDate;

    @Column(updatable = false)
    private String projectIdentifier;

    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @JoinColumn(name="backlog_id")
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Backlog backlog;

    public ProjectTask() { }

    @PrePersist protected void onCreate()   { this.created_At = new Date(); }
    @PreUpdate protected void onUpdate()    { this.updated_At = new Date(); }

    public Long getId()                     { return id;                    }
    public String getProjectSequence()      { return projectSequence;       }
    public String getSummary()              { return summary;               }
    public String getAcceptanceCriteria()   { return acceptanceCriteria;    }
    public String getStatus()               { return status;                }
    public Integer getPriority()            { return priority;              }
    public Date getDueDate()                { return dueDate;               }
    public String getProjectIdentifier()    { return projectIdentifier;     }
    public Date getCreated_At()             { return created_At;            }
    public Date getUpdated_At()             { return updated_At;            }
    public Backlog getBacklog()             { return backlog;               }

    public void setProjectSequence(String projectSequence)          { this.projectSequence = projectSequence;       }
    public void setSummary(String summary)                          { this.summary = summary;                       }
    public void setAcceptanceCriteria(String acceptanceCriteria)    { this.acceptanceCriteria = acceptanceCriteria; }
    public void setStatus(String status)                            { this.status = status;                         }
    public void setPriority(Integer priority)                       { this.priority = priority;                     }
    public void setDueDate(Date dueDate)                            { this.dueDate = dueDate;                       }
    public void setProjectIdentifier(String projectIdentifier)      { this.projectIdentifier = projectIdentifier;   }
    public void setCreated_At(Date created_At)                      { this.created_At = created_At;                 }
    public void setUpdated_At(Date updated_At)                      { this.updated_At = updated_At;                 }
    public void setBacklog(Backlog backlog)                         { this.backlog = backlog;                       }

    @Override
    public String toString() {
        return "ProjectTask{" +
                "id=" + id +
                ", projectSequence='" + projectSequence + '\'' +
                ", summary='" + summary + '\'' +
                ", acceptanceCriteria='" + acceptanceCriteria + '\'' +
                ", status='" + status + '\'' +
                ", priority=" + priority +
                ", dueDate=" + dueDate +
                ", projectIdentifier='" + projectIdentifier + '\'' +
                ", created_At=" + created_At +
                ", updated_At=" + updated_At +
                '}';
    }
}
