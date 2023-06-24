package org.docban.core.password.event;

import lombok.Getter;
import lombok.ToString;
import org.docban.core.common.util.event.EventDomain;
import org.docban.core.password.Password;

@Getter
@ToString
public class CreateNewPasswordEvent implements EventDomain {

    public final Password password;

    public CreateNewPasswordEvent( final Password password ) {
        this.password = password;
    }
}
