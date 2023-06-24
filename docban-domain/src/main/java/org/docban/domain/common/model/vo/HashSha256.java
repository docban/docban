package org.docban.domain.common.model.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

@ToString
public class HashSha256 implements ValueObject<HashSha256, String> {

    private static final Long serialVersionUID = 1L;

    /** Expresión regular para validar el formato de Hash Sha-256 */
    public static final Pattern PATTERN = Pattern.compile( "^[a-fA-F\\d]{64}$" );

// ------------------------------------------------------------------------------------------------------------------ \\

    public final String hash;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public HashSha256( final String hash ) {
        this.hash = hash;
        this.validate();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value() {
        return this.hash;
    }

    @Override
    public HashSha256 clone() {
        return new HashSha256( this.hash );
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
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static HashSha256 build( final String target ) {
        HashSha256 result = null;

        try {
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
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static HashSha256 build() {
        final String instant = String.valueOf( System.currentTimeMillis() );
        return HashSha256.build( instant );
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
