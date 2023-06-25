package org.docban.domain.common.model.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.util.Objects;

@ToString
public class Name implements ValueObject<String> {

    private static final Long serialVersionUID = 1L;

    /**
     * Regex para validar un nombre.
     * El nombre debe empezar con 2 letras, puede contener letras, espacios y/o guiones, y debe terminar con una letra.
     * El nombre no puede tener más de 100 caracteres.
     */
    public static final String REGEX = "^[a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ]{2}[\\sa-zA-Z-áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ]{0,98}$";
    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 100;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String name;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private Name( final String name ) {
        this.name = name;
        this.validate();
    }

    public static Name of( final String name ) {
        return new Name( name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value(){
        return this.name;
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final Name target = (Name) o;
        return Objects.equals( this.name, target.name );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.name == null ) throw new IllegalArgumentException( "El nombre no puede ser nulo" );
        if( this.name.length() < MIN_LENGTH ) throw new IllegalArgumentException( String.format( "El nombre no puede tener menos de %d caracteres", MIN_LENGTH ) );
        if( this.name.length() > MAX_LENGTH ) throw new IllegalArgumentException( String.format( "El nombre no puede tener mas de %d caracteres", MAX_LENGTH ) );
        if( !this.name.matches( REGEX ) ) throw new IllegalArgumentException( "El nombre no es valido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
