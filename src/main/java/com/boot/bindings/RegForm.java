package com.boot.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class RegForm {

	private String name;

	private String email;

	private Long phno;

	private Integer cid;

	private Integer sid;

	private Integer cityId;

}
