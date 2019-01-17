package com.springboot2.jpa.app.main.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 *
 * @author Naveen
 */
@Entity
@Table(name = "app_user", catalog = "test", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"user_name"})})
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@bodyStatId")
@NamedQueries({
	 @NamedQuery(name = "AppUser.findByUserName", query = "SELECT a FROM AppUser a WHERE a.userName = :userName")
})
public class AppUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id", nullable = false)
    private Integer userId;
    @Column(name = "user_name", length = 50)
    private String userName;
    @Column(name = "user_pass", length = 50)
    private String userPass;
    @OneToMany(mappedBy = "appUser",fetch = FetchType.LAZY)
    private List<AppUserDetails> appUserDetailsCollection;

    public AppUser() {
    }

    public AppUser(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

	public List<AppUserDetails> getAppUserDetailsCollection() {
		return appUserDetailsCollection;
	}

	public void setAppUserDetailsCollection(List<AppUserDetails> appUserDetailsCollection) {
		this.appUserDetailsCollection = appUserDetailsCollection;
	}
}
