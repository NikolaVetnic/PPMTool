package com.nv.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer projectTaskSequence = 0;

    private String projectIdentifier;

    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)  // LAZY doesn't load relationships unless specifically requested
    private Project project;

    @OneToMany(
            cascade = CascadeType.REFRESH,
            fetch = FetchType.EAGER,
            mappedBy = "backlog",
            orphanRemoval = true)
    private List<ProjectTask> projectTasks = new ArrayList<>();

    public Backlog() { }

    public void incPTSequence() {
        projectTaskSequence++;
    }

    public Long getId()                         { return id;                    }
    public Integer getProjectTaskSequence()     { return projectTaskSequence;   }
    public String getProjectIdentifier()        { return projectIdentifier;     }
    public Project getProject()                 { return project;               }
    public List<ProjectTask> getProjectTasks()  { return projectTasks;          }

    public void setProjectTaskSequence(Integer projectTaskSequence) { this.projectTaskSequence = projectTaskSequence;   }
    public void setProjectIdentifier(String projectIdentifier)      { this.projectIdentifier = projectIdentifier;       }
    public void setProject(Project project)                         { this.project = project;                           }
    public void setProjectTasks(List<ProjectTask> projectTasks)     { this.projectTasks = projectTasks;                 }
}
