package com.sbt.stop_list.ws.jms;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import static java.awt.SystemColor.text;


@Stateless
public class MessageSender {
    //получаем ресурсы сервера для отправки сообщений
//    @Resource(name="jms/SIN_QM1")
//    private ConnectionFactory connectionFactory;
//
//    @Resource(name="jms/SIN_OUT_QUEUE")
//    private Queue destination;


    @Resource(name="ConnectionFactory")
    private QueueConnectionFactory connectionFactory;

    @Resource(name="Queue")
    private  Queue queue;



    public String checkBean(){
        return "JMS producer connectionFactory >> "+(connectionFactory==null?" is null ":" is not null")
               +" queue >>" +(queue==null?" is null ":" is not null");
    }

    public void sendMessage(String messageText){
        try {
//            JMSContext context = connectionFactory.createContext();
//            context.createProducer().send(queue, messageText);
            Connection connection = connectionFactory.createQueueConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            TextMessage message = session.createTextMessage();
            message.setText(messageText);
            session.createProducer(queue).send(message);

        } catch (Exception ex) {
            // handle exception (details omitted)
            ex.printStackTrace();
        }
    }
}
