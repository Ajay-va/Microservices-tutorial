package com.micro.user.service.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public record MailRequestBody(String recipient,String body,String subject,String to) { }
