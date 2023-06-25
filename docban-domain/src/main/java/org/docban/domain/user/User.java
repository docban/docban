package org.docban.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.docban.domain.common.base.Entity;
import org.docban.domain.common.model.vo.Timestamp;
import org.docban.domain.common.util.event.EventDomainHandler;
import org.docban.domain.common.model.vo.Email;
import org.docban.domain.common.model.vo.LastName;
import org.docban.domain.common.model.vo.Name;
import org.docban.domain.user.event.CreateNewUserEvent;
import org.docban.domain.user.vo.UserId;
import org.docban.domain.user.vo.UserName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Getter
@ToString
public class User implements Entity {

    private static final Long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\
    private UserId id;
    private UserName username;
    private Email email;
    private Name name;
    private Optional<LastName> lastName;
    private LocalDate birthDate;
    private Timestamp creationDate;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public User( final UserName username, final Email email, final Name name, final LastName lastName, final LocalDate birthDate ) {
        //Set Data
        this.id = UserId.build();
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = Optional.of( lastName );
        this.birthDate = birthDate;
        this.creationDate = Timestamp.build();

        //Validate data
        this.validate();

        //Throw Event
        EventDomainHandler.trigger( new CreateNewUserEvent( this ) );
    }

    public User( final UserName username, final Email email, final Name name, final LocalDate birthDate ) {
        //Set Data
        this.id = UserId.build();
        this.username = username;
        this.email = email;
        this.name = name;
        this.lastName = Optional.empty();
        this.birthDate = birthDate;
        this.creationDate = Timestamp.build();

        //Validate data
        this.validate();

        //Throw Event
        EventDomainHandler.trigger( new CreateNewUserEvent( this ) );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( o == null || getClass() != o.getClass() ) return false;
        final User target = (User) o;
        return Objects.equals( this.id, target.id );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| VALIDATION METHODS |------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    private void validate(){
        if( this.id == null ) throw new IllegalArgumentException( "El id no puede ser nulo" );
        if( this.username == null ) throw new IllegalArgumentException( "El nombre de usuario no puede ser nulo" );
        if( this.email == null ) throw new IllegalArgumentException( "El email no puede ser nulo" );
        if( this.name == null ) throw new IllegalArgumentException( "El nombre no puede ser nulo" );
        if( this.birthDate == null ) throw new IllegalArgumentException( "La fecha de nacimiento no puede ser nula" );
        if( this.birthDate.isAfter( LocalDate.now().minusYears( 18 ) ) ) throw new IllegalArgumentException( "El usuario debe ser mayor de edad" );
        if( this.creationDate == null ) throw new IllegalArgumentException( "La fecha de creación de la contraseña no puede ser nula" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
