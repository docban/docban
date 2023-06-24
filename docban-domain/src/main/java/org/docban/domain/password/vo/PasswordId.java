package org.docban.domain.password.vo;

import lombok.ToString;
import org.docban.domain.common.model.vo.UUID;
import org.docban.domain.user.vo.UserId;

@ToString
public class PasswordId extends UUID {

    private static final Long serialVersionUID = 1L;

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| CONSTRUCTOR |-------------------------------------------------------------------------------------------- \\
// ------------------------------------------------------------------------------------------------------------------ \\

    public PasswordId( final String uuid ) {
        super( uuid );
    }

// ------------------------------------------------------------------------------------------------------------------ \\
// -------| COMPARATION METHODS |------------------------------------------------------------------------------------ \\
// ------------------------------------------------------------------------------------------------------------------ \\

    @Override
    public boolean equals( final Object o ) {
        if ( !(o instanceof PasswordId) ) return false;
        return super.equals( o );
    }

// ------------------------------------------------------------------------------------------------------------------ \\

}
