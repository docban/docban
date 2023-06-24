package org.docban.core.common.model.vo;

import lombok.ToString;
import org.docban.core.common.base.ValueObject;

import java.util.Objects;
import java.util.Optional;

@ToString
public class LastName implements ValueObject<LastName, String> {

    private static final Long serialVersionUID = 1L;

    /**
     * Regex para validar un apellido.
     * El apellido debe empezar con 2 letras, puede contener letras, espacios y/o guiones, y debe terminar con una letra.
     * El apellido no puede tener más de 200 caracteres.
     */
    public static final String REGEX = "^\\w{2}[\\w\\s-]{197}\\w$";
    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 200;

// ------------------------------------------------------------------------------------------------------------------ \\

    private String firstName;
    private Optional<String> secondName;
    private String value;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public LastName( final String firstName, final Optional<String> secondName ) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.validate();
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

    @Override
    public LastName clone() {
        return new LastName( this.firstName, this.secondName );
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
        if( this.value == null ) throw new IllegalArgumentException( "El apellido no puede ser nulo" );
        if( this.value.length() < MIN_LENGTH ) throw new IllegalArgumentException( String.format( "El apellido no puede tener menos de %d caracteres", MIN_LENGTH ) );
        if( this.value.length() > MAX_LENGTH ) throw new IllegalArgumentException( String.format( "El apellido no puede tener mas de %d caracteres", MAX_LENGTH ) );
        if( !this.value.matches( REGEX ) ) throw new IllegalArgumentException( "El apellido no es valido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
