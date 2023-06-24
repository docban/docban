package org.docban.core.common.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class EmailTest {

    @ParameterizedTest(name = "Email: {0}")
    @ValueSource( strings = { "example@host.com", "example_123@host.com" })
    public void givenAnValidEmail_whenCreateEmail_thenShouldCreateNewEmail( final String givenEmail) {
        //When
        final Email email = new Email( givenEmail );

        //Then
        Assertions.assertNotNull(email);
        Assertions.assertEquals( givenEmail, email.value() );
    }

    @ParameterizedTest(name = "Email: {0}")
    @ValueSource( strings = { "example", "example @host.com", "example@host", "example@exa@host.com" })
    public void givenAnInvalidEmail_whenCreateEmail_thenThrowIllegalArgumentException( final String givenEmail ) {
        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> new Email( givenEmail ) );
        Assertions.assertEquals( "El email no es valido", exception.getMessage() );
    }

    @Test
    public void givenAnNullEmail_whenCreateEmail_thenThrowIllegalArgumentException() {
        //Given
        final String givenEmail = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> new Email( givenEmail ) );
        Assertions.assertEquals( "El email no puede ser nulo", exception.getMessage() );
    }

    @Test
    public void givenThowIdenticalEmails_whenCompare_thenShouldBeEquals() {
        //Given
        final Email givenEmail1 = new Email( "example@host.com" );
        final Email givenEmail2 = new Email( "example@host.com" );

        //When
        final boolean result = givenEmail1.equals( givenEmail2 );

        //Then
        Assertions.assertTrue( result );
    }

    @Test
    public void givenAnEmail_whenClone_thenShouldCreateNewEmail() {
        //Given
        final Email givenEmail = new Email( "example@host.com" );

        //When
        final Email newEmailValue = givenEmail.clone();

        //Then
        Assertions.assertNotNull( newEmailValue );
        Assertions.assertEquals( givenEmail, newEmailValue );
        Assertions.assertNotEquals( givenEmail.hashCode(), newEmailValue.hashCode() );
    }
}
