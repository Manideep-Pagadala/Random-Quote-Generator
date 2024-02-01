package com.boot.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender sender;

	public boolean sendMail(String sub, String body, String to) {
		boolean flag = false;
		try {
			MimeMessage mimeMessage = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
			helper.setTo(to);
			helper.setSubject(sub);
			helper.setText(body, true);
			sender.send(mimeMessage);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

}
