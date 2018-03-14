package org.miage.m2.boundary;

import org.miage.m2.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActionClient {
    @Autowired
    ActionRestClient restClient;
    

    public Action get(String id) {
        return restClient.get(id);
    }

    public Action fallback(String id) {
        return new Action("non disponible");
    }
    
}

