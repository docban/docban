package org.docban.core.common.util.event;

public interface EventDomainListener<E extends EventDomain>{

    void run( E event );
}

