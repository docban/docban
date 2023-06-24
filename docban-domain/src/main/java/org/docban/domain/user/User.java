package org.docban.domain.user;

import lombok.Getter;
import lombok.ToString;
import org.docban.domain.common.base.Entity;
import org.docban.domain.common.util.event.EventDomainHandler;
import org.docban.domain.common.util.timestamp.TimestampBuilder;
import org.docban.domain.common.model.vo.Email;
import org.docban.domain.common.model.vo.LastName;
import org.docban.domain.common.model.vo.Name;
import org.docban.domain.common.model.vo.UUID;
import org.docban.domain.user.event.CreateNewUserEvent;
import org.docban.domain.user.vo.UserId;
import org.docban.domain.user.vo.UserName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

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
    private LocalDateTime creationDate;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public User( final String username, final String email, final String name, final String firstLastName, final Optional<String> secondLastName, final LocalDate birthDate ) {
        //Set Data
        this.id = new UserId( UUID.build().value() );
        this.username = new UserName( username );
        this.email = new Email( email );
        this.name = new Name( name );
        this.lastName = Optional.of( new LastName( firstLastName, secondLastName ) );
        this.birthDate = birthDate;
        this.creationDate = TimestampBuilder.now();

        //Validate data
        this.validate();

        //Throw Event
        EventDomainHandler.trigger( new CreateNewUserEvent( this ) );
    }

    public User( final String username, final String email, final String name, final LocalDate birthDate ) {
        //Set Data
        this.id = new UserId( UUID.build().value() );
        this.username = new UserName( username );
        this.email = new Email( email );
        this.name = new Name( name );
        this.lastName = Optional.empty();
        this.birthDate = birthDate;
        this.creationDate = TimestampBuilder.now();

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
        if( this.creationDate.isAfter( ZonedDateTime.now().toLocalDateTime() )) throw new IllegalArgumentException( "La fecha de creación de la contraseña no puede ser posterior a la fecha actual" );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
