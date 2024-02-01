package com.boot.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class ResetPassword {

	private Integer userId;
	private String email;
	private String newPwd;
	private String cnfPwd;
}
