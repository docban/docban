package org.docban.domain.common.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

public class LastNameTest {

    @ParameterizedTest( name = "LastName = FirstName: {0}, SecondName: {1}" )
    @CsvSource({ "Herce,Soto", "Fernández,González", "Pérez,del Río" })
    public void givenAnValidFirstLastNameAndSecondLastName_whenCreateLastName_thenShouldCreateNewLastName( final String givenFirstLastName, final String givenSecondLastName ){
        //When
        final LastName lastName = LastName.of( givenFirstLastName, givenSecondLastName );

        //Then
        Assertions.assertNotNull( lastName );
        Assertions.assertEquals( String.format( "%s %s", givenFirstLastName, givenSecondLastName ), lastName.value() );
        Assertions.assertEquals( givenFirstLastName, lastName.getFirstName() );

        final Optional<String> secondLastName = lastName.getSecondName();
        Assertions.assertTrue( secondLastName.isPresent() );
        Assertions.assertEquals( givenSecondLastName, secondLastName.get() );
    }

    @ParameterizedTest( name = "LastName = FirstName: {0}" )
    @ValueSource( strings = { "Herce", "Fernández", "del Río" })
    public void givenAnValidFirstLastName_whenCreateLastName_thenShouldCreateNewLastName( final String givenFirstLastName ){
        //When
        final LastName lastName = LastName.of( givenFirstLastName );

        //Then
        Assertions.assertNotNull( lastName );
        Assertions.assertEquals( givenFirstLastName, lastName.value() );
        Assertions.assertEquals( givenFirstLastName, lastName.getFirstName() );

        final Optional<String> secondLastName = lastName.getSecondName();
        Assertions.assertFalse( secondLastName.isPresent() );
    }

    @ParameterizedTest( name = "LastName = FirstName: {0}, SecondName: {1}" )
    @CsvSource({
            "Herce123,Soto",
            "Herce,Soto123",
            "' Fernández',González",
            "Fernández,' González'",
            "Pérez.dos,del Río",
            "Pérez,del Río.dos",
            "A,Nuñez",
            "Nuñez,A",
            "María-Josefa-Antonietta-Isabella-Cristina-Giorgina-Francisca-Gabriella-Carolina-Inés-Teresa-Avila-Cecilia,proof",
            "proof,María-Josefa-Antonietta-Isabella-Cristina-Giorgina-Francisca-Gabriella-Carolina-Inés-Teresa-Avila-Cecilia"
    })
    public void givenAnInvalidFirsLastNameAndSecondLastName_whenCreateLastName_thenThrowIllegalArgumentException( final String givenFirstLastName, final String givenSecondLastName ){
        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> LastName.of( givenFirstLastName, givenSecondLastName ) );
    }

    @ParameterizedTest( name = "LastName = FirstName: {0}" )
    @ValueSource( strings = {
            "Herce123",
            " Fernández",
            "Pérez.dos",
            "A",
            "María-Josefa-Antonietta-Isabella-Cristina-Giorgina-Francisca-Gabriella-Carolina-Inés-Teresa-Avila-Cecilia"
    })
    public void givenAnInvalidFirsLastName_whenCreateLastName_thenThrowIllegalArgumentException( final String givenFirstLastName ){
        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> LastName.of( givenFirstLastName ) );
    }

    @Test
    public void givenAnNullFirstLastName_whenCreateLastName_thenThrowIllegalArgumentException() {
        //Given
        final String givenLastName = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> LastName.of( givenLastName ) );
    }

    @Test
    public void givenIdenticalFirstLastNameAndSecondLastName_whenCompare_thenShouldBeEquals() {
        //Given
        final LastName givenLastName1 = LastName.of( "Herce", "Fernández" );
        final LastName givenLastName2 = LastName.of( "Herce", "Fernández" );

        //When
        final boolean result = givenLastName1.equals( givenLastName2 );

        //Then
        Assertions.assertTrue( result );
    }

    @Test
    public void givenIdenticalFirstLastName_whenCompare_thenShouldBeEquals() {
        //Given
        final LastName givenLastName1 = LastName.of( "Fernández" );
        final LastName givenLastName2 = LastName.of( "Fernández" );

        //When
        final boolean result = givenLastName1.equals( givenLastName2 );

        //Then
        Assertions.assertTrue( result );
    }

    @Test
    public void givenDiferentFirstLastNameAndSecondLastName_whenCompare_thenShouldBeNotEquals() {
        //Given
        final LastName givenLastName1 = LastName.of( "Herce", "Fernández" );
        final LastName givenLastName2 = LastName.of( "Fernández", "Herce" );

        //When
        final boolean result = givenLastName1.equals( givenLastName2 );

        //Then
        Assertions.assertFalse( result );
    }

    @Test
    public void givenDiferentFirstLastName_whenCompare_thenShouldBeNotEquals() {
        //Given
        final LastName givenLastName1 = LastName.of( "Herce" );
        final LastName givenLastName2 = LastName.of( "Fernández" );

        //When
        final boolean result = givenLastName1.equals( givenLastName2 );

        //Then
        Assertions.assertFalse( result );
    }
}
