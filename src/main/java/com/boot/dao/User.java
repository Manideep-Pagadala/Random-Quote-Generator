package com.boot.dao;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "User_Details")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	private String name;

	@Column(unique = true)
	private String email;

	private String pwd;

	private Long phno;

	private Integer cid;

	private Integer sid;

	private Integer cityId;

	@CreationTimestamp
	@Column(updatable = false)
	@Temporal(TemporalType.DATE)
	private Date creationDate;

	@UpdateTimestamp
	@Column(insertable = false)
	@Temporal(TemporalType.DATE)
	private Date updatationDate;

	private Character pwdUpdated;

}
