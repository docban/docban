package org.docban.domain.user;

import org.docban.domain.common.model.vo.Email;
import org.docban.domain.common.model.vo.LastName;
import org.docban.domain.common.model.vo.Name;
import org.docban.domain.user.vo.UserName;

import java.time.LocalDate;
import java.util.Optional;

public final class UserFactory {

    private UserFactory() {
        throw new IllegalStateException( "Utility class" );
    }

    public static User build( final String username, final String email, final String name, final String firstLastName, final Optional<String> secondLastName, final LocalDate birthDate ) {
        final UserName newUserName = UserName.of( username );
        final Email newEmail = Email.of( email );
        final Name newName = Name.of( name );
        final LastName newLastName = LastName.of( firstLastName );
        return new User( newUserName, newEmail, newName, newLastName, birthDate );
    }

    public static User build( final String username, final String email, final String name, final LocalDate birthDate ) {
        final UserName newUserName = UserName.of( username );
        final Email newEmail = Email.of( email );
        final Name newName = Name.of( name );
        return new User( newUserName, newEmail, newName, birthDate );
    }
}
