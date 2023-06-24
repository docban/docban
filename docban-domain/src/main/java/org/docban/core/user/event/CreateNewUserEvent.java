package org.docban.core.user.event;

import lombok.Getter;
import lombok.ToString;
import org.docban.core.common.util.event.EventDomain;
import org.docban.core.user.User;

@Getter
@ToString
public class CreateNewUserEvent implements EventDomain {

    public final User user;

    public CreateNewUserEvent( final User user ) {
        this.user = user;
    }
}
