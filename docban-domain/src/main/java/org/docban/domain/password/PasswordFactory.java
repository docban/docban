package org.docban.domain.password;

import org.docban.domain.common.model.vo.HashSha256;
import org.docban.domain.password.vo.PasswordSalt;
import org.docban.domain.user.vo.UserId;

import java.util.UUID;

public final class PasswordFactory {

    private PasswordFactory() {
        throw new IllegalStateException( "Utility class" );
    }

    public static Password build( final UUID ownerId, final HashSha256 salt, final String password ) {
        final UserId newOwnerId = UserId.of( ownerId );
        final PasswordSalt newSalt = PasswordSalt.of( salt );
        return new Password( newOwnerId, newSalt, password );
    }

    public static Password build( final String ownerId, final String salt, final String password ) {
        return PasswordFactory.build( UUID.fromString( ownerId ), HashSha256.of( salt ), password );
    }

    public static Password build( final UUID ownerId, final String password ) {
        final UserId newOwnerId = UserId.of( ownerId );
        final PasswordSalt newSalt = PasswordSalt.build();
        return new Password( newOwnerId, newSalt, password );
    }

    public static Password build( final String ownerId, final String password ) {
        return PasswordFactory.build( UUID.fromString( ownerId ), password );
    }
}
