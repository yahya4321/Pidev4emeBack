package com.example.pi_dev_4eme__poker_planning.Api;

import com.example.pi_dev_4eme__poker_planning.Services.SessionServices;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MailStructure {
    private String subject ="Rejoindre Session ";
    private String message ="This is mail send ";
    private Integer idSession;
    private String linkSession;
    private List<String> mails = new ArrayList<>();


}
