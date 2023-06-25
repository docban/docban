package org.docban.domain.common.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Instant;
import java.time.LocalDateTime;

public class TimestampTest {

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CORRECT CREATION |--------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAnValidInstant_whenCreateTimestamp_thenShouldCreateNewTimestamp() {
        //Given
        final Instant givenInstant = Instant.now();

        //When
        final Timestamp timestamp = Timestamp.of( givenInstant );

        //Then
        Assertions.assertNotNull( timestamp );
        Assertions.assertEquals( givenInstant, timestamp.value() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| INCORRECT CREATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAFutureInstant_whenCreateTimestamp_thenThrowIllegalArgumentException() {
        //Given
        final Instant givenInstant = Instant.now().plusMillis( 1000 );

        //When And Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> Timestamp.of( givenInstant ) );
    }

    @Test
    public void givenANulñInstant_whenCreateTimestamp_thenThrowIllegalArgumentException() {
        //Given
        final Instant givenInstant = null;

        //When And Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> Timestamp.of( givenInstant ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenNothing_whenBuildTimestamp_thenShouldCreateNewTimestampWithInstantNow() {
        //When
        final Timestamp timestamp = Timestamp.build();

        //Then
        Assertions.assertNotNull( timestamp );
        Assertions.assertTrue( Instant.now().minusMillis( 1000 ).isBefore( timestamp.value() ) );
        Assertions.assertTrue( Instant.now().isAfter( timestamp.value() ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAnValidInstant_whenGetValueAsLocalDateTime_thenShouldReturnTheTimestampAsLocalDateTime() {
        //Given
        final Instant givenInstant = Instant.now();
        final LocalDateTime givenLocalDateTime = LocalDateTime.ofInstant( givenInstant, java.time.ZoneId.systemDefault() );

        //When
        final Timestamp timestamp = Timestamp.of( givenInstant );

        //Then
        Assertions.assertNotNull( timestamp );
        Assertions.assertEquals( 0, timestamp.valueAsLocalDateTime().compareTo( givenLocalDateTime ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| EQUALS COMPARATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenIdenticalTimestamps_whenCompare_thenShouldBeEquals() {
        //Given
        final Instant now = Instant.now();
        final Timestamp givenTimestamp1 = Timestamp.of( now );
        final Timestamp givenTimestamp2 = Timestamp.of( now );

        //When
        final boolean result = givenTimestamp1.equals( givenTimestamp2 );

        //Then
        Assertions.assertTrue( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| NOT EQUALS COMPARATION |--------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenDiferentTimestamps_whenCompare_thenShouldBeNotEquals() {
        //Given
        final Timestamp givenTimestamp1 = Timestamp.of( Instant.now().minusMillis( 50 ) );
        final Timestamp givenTimestamp2 = Timestamp.of( Instant.now() );

        //When
        final boolean result = givenTimestamp1.equals( givenTimestamp2 );

        //Then
        Assertions.assertFalse( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
