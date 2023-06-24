package org.docban.domain.common.base;

import java.io.Serializable;

public interface ValueObject<S extends Serializable> extends Serializable{

    S value();
}
