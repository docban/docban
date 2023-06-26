package org.docban.domain.password.vo;

import org.docban.domain.common.model.vo.HashSha256;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordSaltTest {

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CORRECT CREATION |--------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAValidSaltAsString_whenCreatePasswordSalt_thenShouldCreateNewPasswordSalt() {
        //Given
        final String givenSalt = "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97";

        //When
        final PasswordSalt passwordSalt = PasswordSalt.of( givenSalt );

        //Then
        Assertions.assertNotNull( passwordSalt );
        final HashSha256 salt = HashSha256.of( givenSalt );
        Assertions.assertEquals( salt, passwordSalt.value() );
    }

    @Test
    public void givenAValidSalt_whenCreatePasswordSalt_thenShouldCreateNewPasswordSalt() {
        //Given
        final HashSha256 givenSalt = HashSha256.of( "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97" );

        //When
        final PasswordSalt passwordSalt = PasswordSalt.of( givenSalt );

        //Then
        Assertions.assertNotNull( passwordSalt );
        Assertions.assertEquals( givenSalt, passwordSalt.value() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| INCORRECT CREATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAnInvalidSaltAsString_whenCreatePasswordSalt_thenThrowIllegalArgumentException() {
        //Given
        final String givenSalt = "salt";

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> PasswordSalt.of( givenSalt ) );
    }

    @Test
    public void givenANullSalt_whenCreatePasswordSalt_thenThrowIllegalArgumentException() {
        //Given
        final HashSha256 givenSalt = null;

        //When and Then
        final IllegalArgumentException exception = Assertions.assertThrows( IllegalArgumentException.class, () -> PasswordSalt.of( givenSalt ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenNothing_whenBuildPasswordSalt_thenShouldCreateNewPasswordSalt() {
        //When
        final PasswordSalt passwordSalt = PasswordSalt.build();

        //Then
        Assertions.assertNotNull( passwordSalt );
        final HashSha256 hashSha256 = passwordSalt.value();
        Assertions.assertNotNull( hashSha256 );
    }

    @Test
    public void givenAString_whenBuildPasswordSalt_thenShouldCreateNewPasswordSalt() {
        //Given
        final String expectSalt = "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97";
        final String givenSalt = "salt";

        //When
        final PasswordSalt passwordSalt = PasswordSalt.build( givenSalt );

        //Then
        Assertions.assertNotNull( passwordSalt );
        Assertions.assertEquals( expectSalt, passwordSalt.valueAsString() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS |------------------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenAValidPasswordSalt_whenGetValue_thenShouldReturnTheSameHash() {
        //When
        final HashSha256 expectSalt = HashSha256.of( "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97" );
        final String givenSalt = "salt";
        final PasswordSalt passwordSalt = PasswordSalt.build( givenSalt );

        //Then
        Assertions.assertNotNull( passwordSalt );
        Assertions.assertEquals( expectSalt, passwordSalt.value() );
    }

    @Test
    public void givenAValidPasswordSalt_whenGetValueAsString_thenShouldReturnTheSameHashString() {
        //When
        final String expectSalt = "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97";
        final String givenSalt = "salt";
        final PasswordSalt passwordSalt = PasswordSalt.build( givenSalt );

        //Then
        Assertions.assertNotNull( passwordSalt );
        Assertions.assertEquals( expectSalt, passwordSalt.valueAsString() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| EQUALS COMPARATION |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenIdenticalPasswordsSalt_whenCompare_thenShouldBeEquals() {
        //Given
        final String salt = "63479ad69a090b258277ec8fba6f99419a2ffb248981510657c944ccd1148e97";
        final PasswordSalt givenPasswordSalt1 = PasswordSalt.of( salt );
        final PasswordSalt givenPasswordSalt2 = PasswordSalt.of( salt );

        //When
        final boolean result = givenPasswordSalt1.equals( givenPasswordSalt2 );

        //Then
        Assertions.assertTrue( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| NOT EQUALS COMPARATION |--------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Test
    public void givenDiferentPasswordsSalt_whenCompare_thenShouldBeNotEquals() {
        //Given
        final String salt1 = "dc90cf07de907ccc64636ceddb38e552a1a0d984743b1f36a447b73877012c39";
        final String salt2 = "dbc4579ae2b3ab293213f42bb852706ea995c3b5c3987f8aa9faae5004acb3cf";
        final PasswordSalt givenPasswordSalt1 = PasswordSalt.of( salt1 );
        final PasswordSalt givenPasswordSalt2 = PasswordSalt.of( salt2 );

        //When
        final boolean result = givenPasswordSalt1.equals( givenPasswordSalt2 );

        //Then
        Assertions.assertFalse( result );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
