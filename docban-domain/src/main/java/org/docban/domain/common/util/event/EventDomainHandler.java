package org.docban.domain.common.util.event;

import java.util.*;

public final class EventDomainHandler {

    private static final Map<Class<? extends EventDomain>, Set<EventDomainListener>> register = new HashMap<>();

    private EventDomainHandler() {
        throw new IllegalStateException( "Utility class" );
    }

    public static final <E extends EventDomain, L extends EventDomainListener<E>> void addEventListener( final Class<E> event, final L listener ){
        final Set<EventDomainListener> listeners = EventDomainHandler.register.computeIfAbsent( event, k -> new HashSet<>() );
        listeners.add( listener );
    }

    public static final <E extends EventDomain, L extends EventDomainListener<E>> void addEventListener( final Class<E> event, final L... listeners ){
        Arrays.stream( listeners ).forEach( listener -> addEventListener( event, listener ) );
    }

    public static final <E extends EventDomain, L extends EventDomainListener<E>> void addEventListener( final Class<E> event, final List<L> listeners ){
        listeners.stream().forEach( listener -> addEventListener( event, listener ) );
    }

    public static void trigger( final EventDomain event ){
        if( EventDomainHandler.register.containsKey( event.getClass() ) ){
            final Set<EventDomainListener> listeners = EventDomainHandler.register.get( event.getClass() );
            listeners.stream().forEach( listener -> listener.run( event ) );
        }
    }
}
