package org.docban.core.password;

import lombok.Getter;
import lombok.ToString;
import org.docban.core.common.base.Entity;
import org.docban.core.common.util.event.EventDomainHandler;
import org.docban.core.common.util.timestamp.TimestampBuilder;
import org.docban.core.common.model.vo.HashSha256;
import org.docban.core.common.model.vo.UUID;
import org.docban.core.password.event.CreateNewPasswordEvent;
import org.docban.core.password.vo.PasswordId;
import org.docban.core.password.vo.PasswordSalt;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.regex.Pattern;

@Getter
@ToString
public class Password implements Entity {

    private static final Long serialVersionUID = 1L;

    /**
     * Expresión regular para validar el formato de una contraseña.
     * La constraseña debe contener al menos 8 caracteres, una mayúscula, una minúscula, un número y un caracter especial.
     */
    private static final Pattern PATTERN = Pattern.compile( "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" );
    private static final String TEMPLATE_PASS_BUILD = "%s%s";

// ------------------------------------------------------------------------------------------------------------------ \\
    private PasswordId id;
    private HashSha256 password;
    private PasswordSalt salt;
    private LocalDateTime creationDate;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Password( final String password ) {
        //Validate Password
        this.validatePasswordFormat( password );

        //Set Data
        this.id = new PasswordId( UUID.build().value() );
        this.salt = new PasswordSalt( UUID.build().value() );
        this.password = this.buildPassword( password, this.salt );
        this.creationDate = TimestampBuilder.now();

        //Validate data
        this.validate();

        //Throw Event
        EventDomainHandler.trigger( new CreateNewPasswordEvent( this ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final Password target = (Password) o;
        return Objects.equals( this.id, target.id );
    }

    public boolean equals( final String target ) {
        if ( target == null ) return false;
        return Objects.equals( this.password, this.buildPassword( target, this.salt ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| BUILDER METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private HashSha256 buildPassword( final String password, final PasswordSalt salt ){
        return HashSha256.build( String.format( Password.TEMPLATE_PASS_BUILD, password, salt.value() ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.id == null ) throw new IllegalArgumentException( "La id de la contraseña no puede ser nula" );
        if( this.password == null ) throw new IllegalArgumentException( "La contraseña no puede ser nula" );
        if( this.salt == null ) throw new IllegalArgumentException( "La sal de la contraseña no puede ser nula" );
        if( this.creationDate == null ) throw new IllegalArgumentException( "La fecha de creación de la contraseña no puede ser nula" );
        if( this.creationDate.isAfter( ZonedDateTime.now().toLocalDateTime() )) throw new IllegalArgumentException( "La fecha de creación de la contraseña no puede ser posterior a la fecha actual" );
    }

    private void validatePasswordFormat( final String target ){
        if( target == null ) throw new IllegalArgumentException( "La contraseña no puede ser nula" );
        if( !PATTERN.matcher( target ).matches() ) throw new IllegalArgumentException( "La contraseña no cumple con el formato requerido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
