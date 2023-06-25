package org.docban.domain.user.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;
import org.docban.domain.password.vo.PasswordId;

import java.util.Objects;
import java.util.UUID;


@ToString
public class UserId implements ValueObject<UUID> {

    private static final Long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final UUID uuid;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private UserId( final UUID uuid ) {
        this.uuid = uuid;
        this.validate();
    }

    public static UserId of( final String uuid ) {
        return new UserId( UUID.fromString( uuid ) );
    }

    public static UserId of( final UUID uuid ) {
        return new UserId( uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static UserId build(){
        return new UserId( UUID.randomUUID() );
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
        final UserId target = (UserId) o;
        return Objects.equals( this.uuid, target.uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.uuid == null ) throw new IllegalArgumentException( "El ID del usuario no puede ser nulo" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
