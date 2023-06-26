package org.docban.domain.common.model.vo;

import lombok.SneakyThrows;
import org.docban.domain.common.base.ValueObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Pattern;

public class HashSha256 implements ValueObject<String> {

    private static final Long serialVersionUID = 1L;

    /** Expresión regular para validar el formato de Hash Sha-256 */
    public static final Pattern PATTERN = Pattern.compile( "^[a-fA-F\\d]{64}$" );

// ------------------------------------------------------------------------------------------------------------------ \\

    public final String hash;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private HashSha256( final String hash ) {
        this.hash = hash;
        this.validate();
    }

    public static HashSha256 of( final String hash ) {
        return new HashSha256( hash );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @SneakyThrows( NoSuchAlgorithmException.class)
    public static HashSha256 build(final String target ) {
        HashSha256 result = null;

        // Crear una instancia de MessageDigest con el algoritmo SHA-256
        final MessageDigest digest = MessageDigest.getInstance("SHA-256" );

        // Obtener el arreglo de bytes del valor a hashear
        final byte[] inputBytes = target.getBytes();

        // Calcular el hash
        final byte[] hashBytes = digest.digest( inputBytes );

        // Convertir los bytes del hash a una representación hexadecimal
        final StringBuilder hexString = new StringBuilder();
        for ( final byte hashByte : hashBytes ) {
            final String hex = Integer.toHexString(0xff & hashByte);
            if ( hex.length() == 1 ) {
                hexString.append( '0' );
            }
            hexString.append( hex );
        }

        result = new HashSha256( hexString.toString() );

        return result;
    }

    public static HashSha256 build( final String... data ) {
        final StringBuilder builder = new StringBuilder();
        Arrays.stream(data).forEach( builder::append );
        return HashSha256.build( builder.toString() );
    }

    public static HashSha256 build() {
        final String instant = String.valueOf( System.currentTimeMillis() );
        return HashSha256.build( instant );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value() {
        return this.hash;
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final HashSha256 target = (HashSha256) o;
        return Objects.equals( this.hash, target.hash );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.hash == null ) throw new IllegalArgumentException( "El Hash (SHA-256) no puede ser nulo" );
        if( !PATTERN.matcher( this.hash ).matches() ) throw new IllegalArgumentException( "El Hash (SHA-256) no es válido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
