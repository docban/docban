package org.docban.domain.user.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class UserNameTest {

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CORRECT CREATION |--------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @ParameterizedTest(name = "UserName = {0}")
    @ValueSource( strings = { "superpepe", "pepe123", "pepe_123", "pepe_pepi", "pepe-123", "pep" })
    public void givenAnValidUserName_whenCreateUserName_thenShouldCreateNewUserName( final String givenUserName ) {
        //When
        final UserName userName = UserName.of( givenUserName );

        //Then
        Assertions.assertNotNull( userName );
        Assertions.assertEquals( givenUserName, userName.value() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| INCORRECT CREATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @ParameterizedTest(name = "UserName = {0}")
    @ValueSource( strings = {
            "Ángel123",
            "pepe@pepe",
            " pepe",
            "pepe a",
            "123456asda",
            "p",
            "pe",
            "as12a56sdaas12a56sdaas12a56sdaas12a56sdaas12a56sdaas12a56sdaasrat"
    })
    public void givenAnInvalidUserName_whenCreateUserName_thenThrowIllegalArgumentException( final String givenUserName ) {
        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> UserName.of( givenUserName ) );
    }

    @Test
    public void givenANullUserName_whenCreateUserName_thenThrowIllegalArgumentException() {
        //Given
        final String givenUserName = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> UserName.of( givenUserName ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| EQUALS COMPARATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenIdenticalUserName_whenCompare_thenShouldBeEquals() {
        //Given
        final UserName givenUserName1 = UserName.of( "Pepe" );
        final UserName givenUserName2 = UserName.of( "Pepe" );

        //When
        final boolean result = givenUserName1.equals( givenUserName2 );

        //Then
        Assertions.assertTrue( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| NOT EQUALS COMPARATION |--------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenDiferentUserName_whenCompare_thenShouldBeNotEquals() {
        //Given
        final UserName givenUserName1 = UserName.of( "Pepe" );
        final UserName givenUserName2 = UserName.of( "Josefina" );

        //When
        final boolean result = givenUserName1.equals( givenUserName2 );

        //Then
        Assertions.assertFalse( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
