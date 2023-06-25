package org.docban.domain.common.model.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.util.Objects;
import java.util.Optional;

@ToString
public class LastName implements ValueObject<String> {

    private static final Long serialVersionUID = 1L;

    /**
     * Regex para validar un apellido.
     * El apellido debe empezar con 2 letras, puede contener letras, espacios y/o guiones, y debe terminar con una letra.
     * El apellido no puede tener más de 200 caracteres.
     */
    public static final String PATTERN = "^[a-zA-ZáéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ]{2}[\\sa-zA-Z-áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ]{0,198}$";

    /** Regex para validar un Apellido individualmente. */
    public static final String PATTERN_NAME = Name.PATTERN;
    public static final int MIN_LENGTH = 2;
    public static final int MIN_LENGTH_NAME = Name.MIN_LENGTH;
    public static final int MAX_LENGTH = 200;
    public static final int MAX_LENGTH_NAME = Name.MAX_LENGTH;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String firstName;
    private final Optional<String> secondName;
    private String value;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private LastName( final String firstName, final Optional<String> secondName ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.validate();
    }

    public static LastName of( final String firstName, final String secondName ) {
        return new LastName( firstName, Optional.of( secondName ) );
    }

    public static LastName of( final String firstName ) {
        return new LastName( firstName, Optional.empty() );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value(){
        if( this.value == null ){
            this.value = this.firstName;
            if( this.secondName.isPresent() ) this.value = String.join( " ", this.value, this.secondName.get() );
        }
        return this.value;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public Optional<String> getSecondName() {
        return this.secondName;
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final LastName target = (LastName) o;
        return Objects.equals( this.firstName, target.firstName ) && Objects.equals( this.secondName, target.secondName );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.firstName == null ) throw new IllegalArgumentException( "El primer apellido no puede ser nulo" );
        if( this.value() == null ) throw new IllegalArgumentException( "El apellido no puede ser nulo" );

        if( this.firstName.length() < MIN_LENGTH_NAME ) throw new IllegalArgumentException( String.format( "El primer apellido no puede tener menos de %d caracteres", MIN_LENGTH_NAME ) );
        if( this.secondName.isPresent() && this.secondName.get().length() < MIN_LENGTH_NAME ) throw new IllegalArgumentException( String.format( "El segundo apellido no puede tener menos de %d caracteres", MIN_LENGTH_NAME ) );
        if( this.value().length() < MIN_LENGTH ) throw new IllegalArgumentException( String.format( "El apellido no puede tener menos de %d caracteres", MIN_LENGTH ) );

        if( this.firstName.length() > MAX_LENGTH_NAME ) throw new IllegalArgumentException( String.format( "El primer apellido no puede tener mas de %d caracteres", MAX_LENGTH_NAME ) );
        if( this.secondName.isPresent() && this.secondName.get().length() > MAX_LENGTH_NAME ) throw new IllegalArgumentException( String.format( "El segundo apellido no puede tener mas de %d caracteres", MAX_LENGTH_NAME ) );
        if( this.value().length() > MAX_LENGTH ) throw new IllegalArgumentException( String.format( "El apellido no puede tener mas de %d caracteres", MAX_LENGTH ) );

        if( !this.firstName.matches( PATTERN_NAME ) ) throw new IllegalArgumentException( "El primer apellido no es valido" );
        if( this.secondName.isPresent() && !this.secondName.get().matches( PATTERN_NAME ) ) throw new IllegalArgumentException( "El segundo apellido no es valido" );
        if( !this.value().matches(PATTERN) ) throw new IllegalArgumentException( "El apellido no es valido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
