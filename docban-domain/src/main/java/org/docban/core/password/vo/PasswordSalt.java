package org.docban.core.password.vo;

import lombok.ToString;
import org.docban.core.common.model.vo.HashSha256;

@ToString
public class PasswordSalt extends HashSha256 {

    private static final Long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public PasswordSalt( final String hash ) {
        super( hash );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| GETTERS METHODS |---------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public PasswordSalt clone() {
        return new PasswordSalt( this.hash );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( !(o instanceof PasswordSalt) ) return false;
        return super.equals( o );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}