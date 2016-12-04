package com.sbt.stop_list.ws.jms;

import com.sbt.stop_list.ws.MessageList;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.validation.constraints.AssertFalse;
import java.util.ArrayList;
import java.util.List;
@MessageDriven(
        activationConfig = { @ActivationConfigProperty(
                propertyName = "destinationType", propertyValue = "javax.jms.Queue"
        ) })
public class MDBExample implements MessageListener {

    @EJB
    private MessageList messageList;

    public List<String> getMessageList() {
        return messageList;
    }

    //метод, вызываемый при получении нового сообщения
    @Override
    public void onMessage(Message msg) {
        try {
            TextMessage message = (TextMessage)msg;
            //считываем свойство из соответствующего поля, заданное вручную в consumer
            System.out.println("FROM MDB - client type IS " + message.getStringProperty("clientType"));
            //считываем  само сообщение
            System.out.println("FROM MDB - payload  IS" + message.getText());
            messageList.add(message.getText());
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

}