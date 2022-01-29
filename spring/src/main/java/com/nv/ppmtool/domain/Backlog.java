package com.nv.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer PTSequence = 0;

    private String projectIdentifier;

    // OneToOne with Project (each Project has one Backlog, and one Backlog belongs to one Project)

    // OneToMany with ProjectTask (Backlog has one or more ProjectTasks, but ProjectTask belongs to one Backlog)

    public Backlog() {}

    public Long getId()                     { return id;                }
    public Integer getPTSequence()          { return PTSequence;        }
    public String getProjectIdentifier()    { return projectIdentifier; }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPTSequence(Integer PTSequence) {
        this.PTSequence = PTSequence;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }
}
