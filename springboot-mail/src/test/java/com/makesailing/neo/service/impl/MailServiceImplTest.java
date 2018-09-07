package com.makesailing.neo.service.impl; 

import com.makesailing.neo.SpringbootMailApplicationTests;
import com.makesailing.neo.service.MailService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.springframework.beans.factory.annotation.Autowired;

/** 
* MailServiceImpl Tester. 
* 
* @author makesailing 
* @since <pre>08/28/2018</pre> 
*/ 
public class MailServiceImplTest extends SpringbootMailApplicationTests {

    @Autowired
    private MailService mailService;

    @Before
    public void setUp() throws Exception { 
    } 
    
    @After
    public void tearDown() throws Exception { 
    } 
    
    /** 
    * 
    * Method: sendSimpleMail(String to, String subject, String content) 
    * 
    */ 
    @Test
    public void testSendSimpleMail() throws Exception {
        mailService.sendSimpleMail("601610870@qq.com","test simple mail"," hello this is simple mail");
    } 
    
        
    } 
