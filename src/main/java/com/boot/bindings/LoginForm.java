package com.boot.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class LoginForm {

	private String email;

	private String pwd;

}
