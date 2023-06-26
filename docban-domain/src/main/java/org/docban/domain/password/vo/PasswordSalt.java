package org.docban.domain.password.vo;

import org.docban.domain.common.base.ValueObject;
import org.docban.domain.common.model.vo.HashSha256;

import java.util.Objects;

public class PasswordSalt implements ValueObject<HashSha256> {

    private static final Long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final HashSha256 hash;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private PasswordSalt( final HashSha256 hash ) {
        this.hash = hash;
        this.validate();
    }

    public static PasswordSalt of( final String hash ) {
        return new PasswordSalt( HashSha256.of( hash ) );
    }

    public static PasswordSalt of( final HashSha256 hash ) {
        return new PasswordSalt( hash );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static PasswordSalt build( final String value ){
        return PasswordSalt.of( HashSha256.build( value ) );
    }

    public static PasswordSalt build(){
        return PasswordSalt.of( HashSha256.build() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public HashSha256 value(){
        return this.hash;
    }

    public String valueAsString(){
        return this.hash.value();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final PasswordSalt target = (PasswordSalt) o;
        return Objects.equals( this.hash, target.hash );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.hash == null ) throw new IllegalArgumentException( "El Hash de la sal de la contraseña no puede ser nula" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
