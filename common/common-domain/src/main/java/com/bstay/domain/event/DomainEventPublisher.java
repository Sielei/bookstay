package com.bstay.domain.event;

public interface DomainEventPublisher <T extends DomainEvent<T>>{
    void publish(T domainEvent);
}
