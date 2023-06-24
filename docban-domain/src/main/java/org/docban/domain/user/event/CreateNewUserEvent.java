package org.docban.domain.user.event;

import lombok.Getter;
import lombok.ToString;
import org.docban.domain.common.util.event.EventDomain;
import org.docban.domain.user.User;

@Getter
@ToString
public class CreateNewUserEvent implements EventDomain {

    public final User user;

    public CreateNewUserEvent( final User user ) {
        this.user = user;
    }
}
