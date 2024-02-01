package com.boot.bindings;

import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class QuoteAPIResponse {

	private String text;
	private String author;
}
