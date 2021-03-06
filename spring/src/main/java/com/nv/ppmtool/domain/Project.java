package com.nv.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.org.glassfish.gmbal.Description;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank(message = "Project name is required")
    private String projectName;

    @Column(updatable = false, unique = true)
    @NotBlank(message = "Project identifier is required")
    @Size(min = 4, max = 5, message = "Please use 4 to 5 characters")
    private String projectIdentifier;

    @NotBlank(message = "Project description is required")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date endDate;

    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    @JsonIgnore
    @OneToOne(
            fetch = FetchType.EAGER,    // upon loading project the Backlog is readily available
            cascade = CascadeType.ALL,  // Project is the OWNING side, when Project is deleted Backlog is as well
            mappedBy = "project")       // same attribute name given to the Project in the Backlog entity
    private Backlog backlog;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private User user;

    private String projectLeader;

    public Project() { }

    @PrePersist protected void onCreate()   { this.created_At = new Date(); }
    @PreUpdate protected void onUpdate()    { this.updated_At = new Date(); }

    public Long getId()                     { return id;                }
    public String getProjectName()          { return projectName;       }
    public String getProjectIdentifier()    { return projectIdentifier; }
    public String getDescription()          { return description;       }
    public Date getStartDate()              { return startDate;         }
    public Date getEndDate()                { return endDate;           }
    public Date getCreated_At()             { return created_At;        }
    public Date getUpdated_At()             { return updated_At;        }
    public Backlog getBacklog()             { return backlog;           }

    public User getUser() {
        return user;
    }

    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectName(String projectName)              { this.projectName = projectName;               }
    public void setProjectIdentifier(String projectIdentifier)  { this.projectIdentifier = projectIdentifier;   }
    public void setDescription(String description)              { this.description = description;               }
    public void setStartDate(Date startDate)                    { this.startDate = startDate;                   }
    public void setEndDate(Date endDate)                        { this.endDate = endDate;                       }
    public void setCreated_At(Date created_At)                  { this.created_At = created_At;                 }
    public void setUpdated_At(Date updated_At)                  { this.updated_At = updated_At;                 }
    public void setBacklog(Backlog backlog)                     { this.backlog = backlog;                       }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }
}
