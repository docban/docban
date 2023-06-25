package org.docban.domain.common.model.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.util.Objects;

@ToString
public class Email implements ValueObject<String> {

    private static final Long serialVersionUID = 1L;

    /** Regex para validar un email */
    public static final String PATTERN = "^[\\w.+-]+@[\\w-]+\\.[\\w-.]+$";

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String email;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private Email( final String email ) {
        this.email = email;
        this.validate();
    }

    public static Email of( final String email ) {
        return new Email( email );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value() {
        return this.email;
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final Email target = (Email) o;
        return Objects.equals( this.email, target.email );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHOS |-------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.email == null ) throw new IllegalArgumentException( "El email no puede ser nulo" );
        if( !this.email.matches(PATTERN) ) throw new IllegalArgumentException( "El email no es valido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
