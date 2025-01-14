package com.example.pi_dev_4eme__poker_planning.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MailDto {
    String toAddress;
    String senderName;
    String lien;
    String subject;
    String content;
    public MailDto(String toAddress, String senderName, String subject, String content) {
        this.toAddress = toAddress;
        this.senderName = senderName;
        this.subject = subject;
        this.content = content;
    }
}
