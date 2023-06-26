package org.docban.domain.password.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class PasswordIdTest {

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CORRECT CREATION |--------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAValidPasswordIdAsString_whenCreatePasswordId_thenShouldCreateNewPasswordId() {
        //Given
        final String givenUUID = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11";

        //When
        final PasswordId passwordId = PasswordId.of( givenUUID );

        //Then
        Assertions.assertNotNull( passwordId );
        Assertions.assertEquals( UUID.fromString( givenUUID ), passwordId.value() );
    }

    @Test
    public void givenAValidPasswordIdAsUUID_whenCreatePasswordId_thenShouldCreateNewPasswordId() {
        //Given
        final UUID givenUUID = UUID.randomUUID();

        //When
        final PasswordId passwordId = PasswordId.of( givenUUID );

        //Then
        Assertions.assertNotNull( passwordId );
        Assertions.assertEquals( givenUUID, passwordId.value() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| INCORRECT CREATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAnInvalidPasswordIdAsString_whenCreatePasswordId_thenThrowIllegalArgumentException() {
        //Given
        final String givenUUID = "uuid";

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> PasswordId.of( givenUUID ) );
    }

    @Test
    public void givenANullPasswordId_whenCreatePasswordId_thenThrowIllegalArgumentException() {
        //Given
        final UUID givenUUID = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> PasswordId.of( givenUUID ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenNothing_whenBuildPasswordId_thenShouldCreateNewPasswordId() {
        //When
        final PasswordId passwordId = PasswordId.build();

        //Then
        Assertions.assertNotNull( passwordId );
        final UUID uuid = UUID.fromString( passwordId.valueAsString() );
        Assertions.assertNotNull( uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAValidPasswordIdWithSpecificID_whenGetValue_thenShouldReturnTheSameID() {
        //Given
        final UUID givenUUID = UUID.randomUUID();

        //When
        final PasswordId passwordId = PasswordId.of( givenUUID );
        final UUID result = passwordId.value();

        //Then
        Assertions.assertNotNull( passwordId );
        Assertions.assertNotNull( result );
        Assertions.assertEquals( givenUUID, result );
    }

    @Test
    public void givenAValidPasswordIdWithSpecificID_whenGetValueAsString_thenShouldReturnTheSameID() {
        //Given
        final UUID givenUUID = UUID.randomUUID();

        //When
        final PasswordId passwordId = PasswordId.of( givenUUID );
        final String result = passwordId.valueAsString();

        //Then
        Assertions.assertNotNull( passwordId );
        Assertions.assertNotNull( result );
        Assertions.assertEquals( givenUUID.toString(), result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| EQUALS COMPARATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenIdenticalPasswordIds_whenCompare_thenShouldBeEquals() {
        //Given
        final UUID givenUUID = UUID.randomUUID();
        final PasswordId givenPasswordId1 = PasswordId.of( givenUUID );
        final PasswordId givenPasswordId2 = PasswordId.of( givenUUID );

        //When
        final boolean result = givenPasswordId1.equals( givenPasswordId2 );

        //Then
        Assertions.assertTrue( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| NOT EQUALS COMPARATION |--------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenDiferentPasswordIds_whenCompare_thenShouldBeEquals() {
        //Given
        final PasswordId givenPasswordId1 = PasswordId.build();
        final PasswordId givenPasswordId2 = PasswordId.build();

        //When
        final boolean result = givenPasswordId1.equals( givenPasswordId2 );

        //Then
        Assertions.assertFalse( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
