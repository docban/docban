package org.docban.domain.user.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class UserIdTest {

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CORRECT CREATION |--------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAValidUUIDAsString_whenCreateUserId_thenShouldCreateNewUserId() {
        //Given
        final String givenUUID = "a0eebc99-9c0b-4ef8-bb6d-6bb9bd380a11";

        //When
        final UserId userId = UserId.of( givenUUID );

        //Then
        Assertions.assertNotNull( userId );
        Assertions.assertEquals( UUID.fromString( givenUUID ), userId.value() );
    }

    @Test
    public void givenAValidUUID_whenCreateUserId_thenShouldCreateNewUserId() {
        //Given
        final UUID givenUUID = UUID.randomUUID();

        //When
        final UserId userId = UserId.of( givenUUID );

        //Then
        Assertions.assertNotNull( userId );
        Assertions.assertEquals( givenUUID, userId.value() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| INCORRECT CREATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAnInvalidUUIDAsString_whenCreateUserId_thenThrowIllegalArgumentException() {
        //Given
        final String givenUUID = "uuid";

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> UserId.of( givenUUID ) );
    }

    @Test
    public void givenANullUUID_whenCreateUserId_thenThrowIllegalArgumentException() {
        //Given
        final UUID givenUUID = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> UserId.of( givenUUID ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenNothing_whenBuildUserId_thenShouldCreateNewUserId() {
        //When
        final UserId userId = UserId.build();

        //Then
        Assertions.assertNotNull( userId );
        final UUID uuid = UUID.fromString( userId.valueAsString() );
        Assertions.assertNotNull( uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAValidUserIdWithSpecificUUID_whenGetValue_thenShouldReturnTheSameUUID() {
        //Given
        final UUID givenUUID = UUID.randomUUID();

        //When
        final UserId userId = UserId.of( givenUUID );
        final UUID result = userId.value();

        //Then
        Assertions.assertNotNull( userId );
        Assertions.assertNotNull( result );
        Assertions.assertEquals( givenUUID, result );
    }

    @Test
    public void givenAValidUserIdWithSpecificUUID_whenGetValueAsString_thenShouldReturnTheSameUUID() {
        //Given
        final UUID givenUUID = UUID.randomUUID();

        //When
        final UserId userId = UserId.of( givenUUID );
        final String result = userId.valueAsString();

        //Then
        Assertions.assertNotNull( userId );
        Assertions.assertNotNull( result );
        Assertions.assertEquals( givenUUID.toString(), result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| EQUALS COMPARATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenIdenticalUserIds_whenCompare_thenShouldBeEquals() {
        //Given
        final UUID givenUUID = UUID.randomUUID();
        final UserId givenUserId1 = UserId.of( givenUUID );
        final UserId givenUserId2 = UserId.of( givenUUID );

        //When
        final boolean result = givenUserId1.equals( givenUserId2 );

        //Then
        Assertions.assertTrue( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| NOT EQUALS COMPARATION |--------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenDiferentUserIds_whenCompare_thenShouldBeEquals() {
        //Given
        final UserId givenUserId1 = UserId.build();
        final UserId givenUserId2 = UserId.build();

        //When
        final boolean result = givenUserId1.equals( givenUserId2 );

        //Then
        Assertions.assertFalse( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
