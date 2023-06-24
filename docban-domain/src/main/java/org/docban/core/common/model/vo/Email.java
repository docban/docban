package org.docban.core.common.model.vo;

import lombok.ToString;
import org.docban.core.common.base.ValueObject;

import java.util.Objects;

@ToString
public class Email implements ValueObject<Email, String> {

    private static final Long serialVersionUID = 1L;

    /** Regex para validar un email */
    public static final String REGEX = "^[\\w\\d_.+-]+@[\\w\\d-]+\\.[\\w\\d-.]+$";

// ------------------------------------------------------------------------------------------------------------------ \\

    private String email;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Email( final String email ) {
        this.email = email;
        this.validate();
    }
// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value() {
        return this.email;
    }

    @Override
    public Email clone() {
        return new Email( this.email );
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
        if( !this.email.matches( REGEX ) ) throw new IllegalArgumentException( "El email no es valido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
