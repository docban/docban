package org.docban.domain.common.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

    @ParameterizedTest(name = "Email: {0}")
    @ValueSource( strings = { "example@host.com", "example_123@host.com", "EXAMPLE_123@HOST.COM" })
    public void givenAnValidEmail_whenCreateEmail_thenShouldCreateNewEmail( final String givenEmail ) {
        //When
        final Email email = Email.of( givenEmail );

        //Then
        Assertions.assertNotNull(email);
        Assertions.assertEquals( givenEmail, email.value() );
    }

    @ParameterizedTest(name = "Email: {0}")
    @ValueSource( strings = { "example", "example @host.com", "example@host", "example@exa@host.com" })
    public void givenAnInvalidEmail_whenCreateEmail_thenThrowIllegalArgumentException( final String givenEmail ) {
        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> Email.of( givenEmail ) );
        Assertions.assertEquals( "El email no es valido", exception.getMessage() );
    }

    @Test
    public void givenAnNullEmail_whenCreateEmail_thenThrowIllegalArgumentException() {
        //Given
        final String givenEmail = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> Email.of( givenEmail ) );
        Assertions.assertEquals( "El email no puede ser nulo", exception.getMessage() );
    }

    @Test
    public void givenThowIdenticalEmails_whenCompare_thenShouldBeEquals() {
        //Given
        final Email givenEmail1 = Email.of( "example@host.com" );
        final Email givenEmail2 = Email.of( "example@host.com" );

        //When
        final boolean result = givenEmail1.equals( givenEmail2 );

        //Then
        Assertions.assertTrue( result );
    }
}
