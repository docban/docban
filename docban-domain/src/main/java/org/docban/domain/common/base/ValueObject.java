package org.docban.domain.common.base;

import java.io.Serializable;

public interface ValueObject<T extends Serializable> extends Serializable{

    T value();

}
