package org.docban.domain.password.event;

import lombok.Getter;
import lombok.ToString;
import org.docban.domain.common.util.event.EventDomain;
import org.docban.domain.password.Password;

@Getter
@ToString
public class CreateNewPasswordEvent implements EventDomain {

    public final Password password;

    public CreateNewPasswordEvent( final Password password ) {
        this.password = password;
    }
}
