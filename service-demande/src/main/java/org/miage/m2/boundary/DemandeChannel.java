package org.miage.m2.boundary;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;


public interface DemandeChannel {
    @Input
    SubscribableChannel input();
}
