package org.miage.m2.boundary;

import java.util.List;
import java.util.stream.Collectors;
import org.miage.m2.entity.Demande;
import org.miage.m2.entity.Detail;
import org.miage.m2.entity.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Service;


@Service
public class DemandeProcessor implements ResourceProcessor<Resource<? extends Demande>> {

    @Autowired
    ActionClient client;

    @Override
    public Resource<Detail> process(Resource<? extends Demande> resource) {
        Demande demande = resource.getContent();
        List<Action> actions = demande
                .getActionsid()
                .stream()
                .map(client::get)
                .collect(Collectors.toList());
        return new Resource<>(new Detail(demande, actions), resource.getLinks());
    }

}
