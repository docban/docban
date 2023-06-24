package org.docban.core.common.model.vo;

import lombok.ToString;
import org.docban.core.common.base.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

@ToString
public class UUID implements ValueObject<UUID, String> {

    private static final Long serialVersionUID = 1L;

    /** Expresión regular para validar el formato de un UUID */
    public static final Pattern PATTERN = Pattern.compile( "^[a-fA-F\\d]{8}-[a-fA-F\\d]{4}-[a-fA-F\\d]{4}-[a-fA-F\\d]{4}-[a-fA-F\\d]{12}$" );

// ------------------------------------------------------------------------------------------------------------------ \\

    public final String uuid;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public UUID( final String uuid ) {
        this.uuid = uuid;
        this.validate();
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value() {
        return this.uuid;
    }

    @Override
    public UUID clone() {
        return new UUID( this.uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final UUID target = (UUID) o;
        return Objects.equals( this.uuid, target.uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static UUID build() {
        final String instant = String.valueOf( System.currentTimeMillis() );
        return new UUID( java.util.UUID.fromString( instant ).toString() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.uuid == null ) throw new IllegalArgumentException( "El ID no puede ser nulo" );
        if( !PATTERN.matcher( this.uuid ).matches() ) throw new IllegalArgumentException( "El ID no es válido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
