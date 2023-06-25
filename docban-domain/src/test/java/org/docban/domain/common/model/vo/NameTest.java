package org.docban.domain.common.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    @ParameterizedTest(name = "Name = {0}")
    @ValueSource( strings = { "Ángel", "Patricia", "María del Carmen", "Juan-Carlos" })
    public void givenAnValidName_whenCreateName_thenShouldCreateNewName( final String givenName ) {
        //When
        final Name name = Name.of( givenName );

        //Then
        Assertions.assertNotNull( name );
        Assertions.assertEquals( givenName, name.value() );
    }

    @ParameterizedTest(name = "Name = {0}")
    @ValueSource( strings = { "Ángel123", "Patricia.mola", " María", "A", "María-Josefa-Antonietta-Isabella-Cristina-Giorgina-Francisca-Gabriella-Carolina-Inés-Teresa-Avila-Cecilia" })
    public void givenAnInvalidName_whenCreateName_thenThrowIllegalArgumentException( final String givenName ) {
        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> Name.of( givenName ) );
    }

    @Test
    public void givenAnNullName_whenCreateName_thenThrowIllegalArgumentException() {
        //Given
        final String givenName = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> Name.of( givenName ) );
    }

    @Test
    public void givenThrowIdenticalName_whenCompare_thenShouldBeEquals() {
        //Given
        final Name givenName1 = Name.of( "Pepe" );
        final Name givenName2 = Name.of( "Pepe" );

        //When
        final boolean result = givenName1.equals( givenName2 );

        //Then
        Assertions.assertTrue( result );
    }
}
