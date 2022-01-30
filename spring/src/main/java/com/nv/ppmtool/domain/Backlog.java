package com.nv.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer PTSequence = 0;

    private String projectIdentifier;

    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)  // LAZY doesn't load relationships unless specifically requested
    private Project project;

    // OneToMany with ProjectTask (Backlog has one or more ProjectTasks, but ProjectTask belongs to one Backlog)

    public Backlog() { }

    public Long getId()                     { return id;                }
    public Integer getPTSequence()          { return PTSequence;        }
    public String getProjectIdentifier()    { return projectIdentifier; }
    public Project getProject()             { return project;           }

    public void setId(Long id)                                  { this.id = id;                                 }
    public void setPTSequence(Integer PTSequence)               { this.PTSequence = PTSequence;                 }
    public void setProjectIdentifier(String projectIdentifier)  { this.projectIdentifier = projectIdentifier;   }
    public void setProject(Project project)                     { this.project = project;                       }
}
