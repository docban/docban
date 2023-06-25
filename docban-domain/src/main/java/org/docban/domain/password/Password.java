package org.docban.domain.password;

import lombok.Getter;
import lombok.ToString;
import org.docban.domain.common.base.Entity;
import org.docban.domain.common.model.vo.HashSha256;
import org.docban.domain.common.model.vo.Timestamp;
import org.docban.domain.common.util.event.EventDomainHandler;
import org.docban.domain.password.event.CreateNewPasswordEvent;
import org.docban.domain.password.vo.PasswordId;
import org.docban.domain.password.vo.PasswordSalt;
import org.docban.domain.user.vo.UserId;

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

// ------------------------------------------------------------------------------------------------------------------ \\
    private PasswordId id;
    private UserId ownerId;
    private HashSha256 password;
    private PasswordSalt salt;
    private Timestamp creationDate;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public Password( final UserId ownerId, final PasswordSalt salt, final String password ) {
        //Validate Password
        this.validatePasswordFormat( password );

        //Set Data
        this.id = PasswordId.build();
        this.ownerId = ownerId;
        this.salt = salt;
        this.password = HashSha256.build( password, this.salt.valueAsString() );
        this.creationDate = Timestamp.build();

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

    public boolean equals( final String password ) {
        if ( password == null ) return false;
        return Objects.equals( this.password, HashSha256.build( password, this.salt.valueAsString() ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.id == null ) throw new IllegalArgumentException( "La id de la contraseña no puede ser nula" );
        if( this.ownerId == null ) throw new IllegalArgumentException( "La id del usuario no puede ser nulo" );
        if( this.password == null ) throw new IllegalArgumentException( "La contraseña no puede ser nula" );
        if( this.salt == null ) throw new IllegalArgumentException( "La sal de la contraseña no puede ser nula" );
        if( this.creationDate == null ) throw new IllegalArgumentException( "La fecha de creación de la contraseña no puede ser nula" );
    }

    private void validatePasswordFormat( final String target ){
        if( target == null ) throw new IllegalArgumentException( "La contraseña no puede ser nula" );
        if( !PATTERN.matcher( target ).matches() ) throw new IllegalArgumentException( "La contraseña no cumple con el formato requerido" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
