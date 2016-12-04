package com.sbt.stop_list.ws;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Stateful;
import java.util.ArrayList;

@Singleton
public class MessageList extends ArrayList<String> {

    @PostConstruct
    public void init(){
        add("test first message");
    }
}
