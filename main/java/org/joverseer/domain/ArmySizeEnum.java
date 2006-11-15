package org.joverseer.domain;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mskounak
 * Date: 30 ��� 2006
 * Time: 11:26:42 ��
 * To change this template use File | Settings | File Templates.
 */
public enum ArmySizeEnum implements Serializable {
    unknown(0),
    tiny (5),
    small (1),
    army (2),
    large (3),
    huge (4);

    int size;

    private ArmySizeEnum(int s) {
        size = s;
    }

}
