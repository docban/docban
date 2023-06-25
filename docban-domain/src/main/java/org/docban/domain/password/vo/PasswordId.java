package org.docban.domain.password.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.util.Objects;
import java.util.UUID;

@ToString
public class PasswordId implements ValueObject<UUID> {

    private static final Long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final UUID uuid;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private PasswordId( final UUID uuid ) {
        this.uuid = uuid;
        this.validate();
    }

    public static PasswordId of( final String uuid ) {
        return new PasswordId( UUID.fromString( uuid ) );
    }

    public static PasswordId of( final UUID uuid ) {
        return new PasswordId( uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static PasswordId build(){
        return new PasswordId( UUID.randomUUID() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public UUID value(){
        return this.uuid;
    }

    public String valueAsString(){
        return this.uuid.toString();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final PasswordId target = (PasswordId) o;
        return Objects.equals( this.uuid, target.uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.uuid == null ) throw new IllegalArgumentException( "El ID de la contraseña no puede ser nula" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
