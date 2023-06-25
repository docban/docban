package org.docban.domain.user.vo;

import lombok.ToString;
import org.docban.domain.common.base.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

@ToString
public class UserName implements ValueObject<String> {

    private static final long serialVersionUID = 1L;

    /**
     * Regex para validar un username.
     * El username debe empezar con una letra, debe tener al menos 3 caracteres y puede contener letras, numeros,
     * guiones y guiones bajos.
     */
    public static final Pattern PATTERN = Pattern.compile( "^\\w{1}[\\w\\d_-]{2,64}$" );
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 64;

// ------------------------------------------------------------------------------------------------------------------ \\

    private final String userName;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private UserName( final String userName ) {
        this.userName = userName;
        this.validate();
    }

    public static UserName of( final String userName ) {
        return new UserName( userName );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public String value(){
        return this.userName;
    }

    public String valueAsString(){
        return this.userName;
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final UserName target = (UserName) o;
        return Objects.equals( this.userName, target.userName );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.userName == null ) throw new IllegalArgumentException( "El username no puede ser nulo" );
        if( this.userName.length() < MIN_LENGTH ) throw new IllegalArgumentException( String.format( "El username no puede tener menos de %d caracteres", MIN_LENGTH ) );
        if( this.userName.length() > MAX_LENGTH ) throw new IllegalArgumentException( String.format( "El username no puede tener mas de %d caracteres", MAX_LENGTH ) );
        if( !PATTERN.matcher( this.userName ).matches() ) throw new IllegalArgumentException( "El username no es valido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
