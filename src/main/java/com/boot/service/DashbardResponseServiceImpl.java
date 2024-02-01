package com.boot.service;

import java.util.Random;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.boot.bindings.QuoteAPIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DashbardResponseServiceImpl implements DashboardResponseService {

	private QuoteAPIResponse[] quotes;
	Random random = new Random();

	@Override
	public String getQuote() {

		final String url = "https://type.fit/api/quotes";
		int randomNum = 0;
		if (quotes == null) {
			RestTemplate rt = new RestTemplate();
			ResponseEntity<String> forEntity = rt.getForEntity(url, String.class);
			String body = forEntity.getBody();
			ObjectMapper om = new ObjectMapper();
			try {
				quotes = om.readValue(body, QuoteAPIResponse[].class);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		} else {
			randomNum = random.nextInt(quotes.length - 1);
		}

		return quotes[randomNum].getText();

	}

}
