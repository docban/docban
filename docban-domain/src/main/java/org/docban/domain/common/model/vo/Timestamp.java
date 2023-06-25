package org.docban.domain.common.model.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Objects;

@ToString
public class Timestamp implements ValueObject<Instant> {

    private static final Long serialVersionUID = 1L;

    /**
     * Regex para validar un nombre.
     * El nombre debe empezar con 2 letras, puede contener letras, espacios y/o guiones, y debe terminar con una letra.
     * El nombre no puede tener más de 100 caracteres.
     */
    public static final String REGEX = "^\\w{2}[\\w\\s-]{98}\\w$";

// ------------------------------------------------------------------------------------------------------------------ \\

    private final Instant timestamp;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private Timestamp( final Instant timestamp ) {
        this.timestamp = timestamp;
        this.validate();
    }

    public static Timestamp of( final Instant instant ) {
        return new Timestamp( instant );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public static Timestamp build(){
        return new Timestamp( Instant.now() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public Instant value(){
        return this.timestamp;
    }

    public LocalDateTime valueAsLocalDateTime(){
        return LocalDateTime.ofInstant( this.timestamp, java.time.ZoneId.systemDefault() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final Timestamp target = (Timestamp) o;
        return Objects.equals( this.timestamp, target.timestamp );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.timestamp == null ) throw new IllegalArgumentException( "El timestamp no puede ser nulo" );
        if( this.timestamp.isAfter( Instant.now() ) ) throw new IllegalArgumentException( "El timestamp no puede ser posterior a la fecha actual" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}