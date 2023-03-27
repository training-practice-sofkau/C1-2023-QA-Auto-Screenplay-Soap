package com.sofkau.utils;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class StringToSOAPMessage {
    public static SOAPMessage convert(String soapString) throws SOAPException, IOException {
        MessageFactory factory = MessageFactory.newInstance();
        SOAPMessage message = factory.createMessage(null, new ByteArrayInputStream(soapString.getBytes()));
        return message;
    }
}
