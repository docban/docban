package org.docban.domain.common.model.vo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class HashSha256Test {

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CORRECT CREATION |--------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @ParameterizedTest(name = "HashSha256 = {0}")
    @ValueSource(strings = {"5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", "df250e2d70c9256e5dc33584b39ed2f405d4931a1d643d5d79930168afc288ee", "2314ec44c47b02523437690805581816305fe3cb780f65fcef0d2b630c06bbcd"})
    public void givenValidHash_whenCreateHashSha256_thenShouldCreateNewHashSha256(final String givenHash) {

        // When
        final HashSha256 hashSha256 = HashSha256.of(givenHash);

        // Then
        Assertions.assertNotNull(hashSha256);
        Assertions.assertEquals(givenHash, hashSha256.value());

    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| INCORRECT CREATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @ParameterizedTest(name = "HashSha256 = {0}")
    @ValueSource(strings = {"password", "passwordExample", "Abc123."})
    public void givenInvalidHash_whenCreateHashSha256_thenThrowIllegalArgumentException(final String givenHash) {

        // When and Then
        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> HashSha256.of(givenHash));

    }

    @Test
    public void givenNullHash_whenCreateHashSha256_thenThrowIllegalArgumentException() {

        // Given
        final String givenHash = null;

        // When and Then
        final IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> HashSha256.of(givenHash));

    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenValidTarget_whenBuildHash_thenShouldCreateNewHashSha256() {

        // Given
        final String givenTarget = "password";

        // When
        final HashSha256 hashSha256 = HashSha256.build(givenTarget);

        // Then
        Assertions.assertNotNull(hashSha256);
        Assertions.assertEquals("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8", hashSha256.value());

    }

    @Test
    public void givenStringArray_whenBuildHash_thenShouldCreateNewHashSha256() {

        // Given
        final String[] givenTarget = {"password", "example"};

        // When
        HashSha256 hash = HashSha256.build(givenTarget);

        // Then
        Assertions.assertNotNull(hash);
        Assertions.assertEquals("53db4365d910283c7f6292763b0b2a1b4ea03a6c415fa9c71645db1ac1705cad", hash.value());

    }

    @Test
    public void givenNothing_whenBuildHash_thenShouldCreateNewHashSha256() {

        // When
        HashSha256 hash = HashSha256.build();

        // Then
        Assertions.assertNotNull(hash);
        Assertions.assertTrue(!hash.value().isEmpty());

    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| EQUALS COMPARATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenIdenticalHash_whenCompare_thenSouldBeEquals() {

        // Given
        final HashSha256 hash1 = HashSha256.of("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
        final HashSha256 hash2 = HashSha256.of("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");

        // When
        final boolean result = hash1.equals(hash2);

        // Then
        Assertions.assertTrue(result);

    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| NOT EQUALS COMPARATION |--------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenDifferentHash_whenCompare_thenShouldNotBeEquals() {

        // Given
        final HashSha256 hash1 = HashSha256.of("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8");
        final HashSha256 hash2 = HashSha256.of("df250e2d70c9256e5dc33584b39ed2f405d4931a1d643d5d79930168afc288ee");

        // When
        final boolean result = hash1.equals(hash2);

        // Then
        Assertions.assertFalse(result);

    }

}