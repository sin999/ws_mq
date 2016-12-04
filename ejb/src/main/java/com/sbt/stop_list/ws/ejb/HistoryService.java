package com.sbt.stop_list.ws.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class HistoryService {
    @PersistenceContext(unitName = "orasin")
    private EntityManager entityManager;


    public String checkEM(){
        return entityManager==null ? " EM IS NULL " : "EM IS NOT NULL !";
    }
}
