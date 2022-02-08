package com.nv.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @Email(message = "Username needs to be an email")
    @NotBlank(message = "Username field is required")
    private String username;

    @NotBlank(message = "Please enter your full name")
    private String fullName;

    @NotBlank(message = "Password field is required")
    private String password;

    @Transient
    private String confirmPassword;

    @Column(updatable = false)
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    // OneToMany with Project

    public User() { }

    @PrePersist protected void onCreate()   { this.created_At = new Date(); }
    @PreUpdate protected void onUpdate()    { this.updated_At = new Date(); }

    public Long getId()                 { return id;                }
    public String getUsername()         { return username;          }
    public String getFullName()         { return fullName;          }
    public String getPassword()         { return password;          }
    public String getConfirmPassword()  { return confirmPassword;   }
    public Date getCreated_At()         { return created_At;        }
    public Date getUpdated_At()         { return updated_At;        }

    public void setId(Long id)                              { this.id = id;                             }
    public void setUsername(String username)                { this.username = username;                 }
    public void setFullName(String fullName)                { this.fullName = fullName;                 }
    public void setPassword(String password)                { this.password = password;                 }
    public void setConfirmPassword(String confirmPassword)  { this.confirmPassword = confirmPassword;   }
    public void setCreated_At(Date created_At)              { this.created_At = created_At;             }
    public void setUpdated_At(Date updated_At)              { this.updated_At = updated_At;             }
}
