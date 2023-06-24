package org.docban.domain.common.util.event;

public interface EventDomainListener<E extends EventDomain>{

    void run( E event );
}

