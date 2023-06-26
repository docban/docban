package org.docban.domain.common.util.event;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EventDomainHandlerTest {

// ------------------------------------------------------------------------------------------------------------------ \\
// --------| REGISTER SINGLE LISTENER |------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    void givenAValidEventAndValidListener_whenAddEventListenerAndTriggerTheEventRegistered_thenTheListenerRegisteredShouldBeCalled() {
        //Given
        final EventDomain givenEvent = Mockito.mock( EventDomain.class );
        final EventDomainListener givenListener = Mockito.mock( EventDomainListener.class );

        //When
        EventDomainHandler.addEventListener( givenEvent.getClass(), givenListener );
        EventDomainHandler.trigger( givenEvent );

        //Then
        verify( givenListener ).run( givenEvent );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// --------| REGISTER MULTIPLE LISTENER |---------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    void givenAValidEventAndValidListeners_whenAddEventListenersAndTriggerTheEventRegistered_thenTheListenersRegisteredShouldBeCalled() {
        //Given
        final EventDomain givenEvent = Mockito.mock( EventDomain.class );
        final EventDomainListener givenListener1 = Mockito.mock( EventDomainListener.class );
        final EventDomainListener givenListener2 = Mockito.mock( EventDomainListener.class );

        //When
        EventDomainHandler.addEventListener( givenEvent.getClass(), givenListener1, givenListener2 );
        EventDomainHandler.trigger( givenEvent );

        //Then
        verify( givenListener1 ).run( givenEvent );
        verify( givenListener2 ).run( givenEvent );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// --------| REGISTER MULTIPLE LISTENER AS ARRAY |------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    void givenAValidEventAndValidListenersAsArray_whenAddEventListenersAsArrayAndTriggerTheEventRegistered_thenTheListenersRegisteredShouldBeCalled() {
        //Given
        final EventDomain givenEvent = Mockito.mock( EventDomain.class );
        final EventDomainListener[] givenListeners = {Mockito.mock( EventDomainListener.class ), Mockito.mock( EventDomainListener.class )};

        //When
        EventDomainHandler.addEventListener( givenEvent.getClass(), givenListeners );
        EventDomainHandler.trigger( givenEvent );

        //Then
        Arrays.stream( givenListeners ).forEach( listener -> verify( listener ).run( givenEvent ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// --------| REGISTER MULTIPLE LISTENER AS LIST |-------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    void givenAValidEventAndValidListenersAsList_whenAddEventListenersAsListAndTriggerTheEventRegistered_thenTheListenersRegisteredShouldBeCalled() {
        //Given
        final EventDomain givenEvent = Mockito.mock( EventDomain.class );
        final List<EventDomainListener> givenListeners = Arrays.asList( Mockito.mock( EventDomainListener.class ), Mockito.mock( EventDomainListener.class ) );

        //When
        EventDomainHandler.addEventListener( givenEvent.getClass(), givenListeners );
        EventDomainHandler.trigger( givenEvent );

        //Then
        givenListeners.stream().forEach( listener -> verify( listener ).run( givenEvent ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// --------| PRIVATE CONSTRUCTOR |----------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    void givenNothing_whenTryCreateNewInstanceOfEventDomainHandler_thenThenThrowIllegalStateException() {
        //When and Then
        final Constructor<?>[] constructors = EventDomainHandler.class.getConstructors();
        for( final Constructor<?> constructor : constructors ) {
            constructor.setAccessible( true );
            final IllegalStateException exception = Assertions.assertThrows( IllegalStateException.class, () -> constructor.newInstance() );
            Assertions.assertEquals("Utility class", exception.getMessage() );
        }
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
