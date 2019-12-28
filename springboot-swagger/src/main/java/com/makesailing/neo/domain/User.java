package com.makesailing.neo.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * #
 *
 * @date 2018/8/7 10:53
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 5353304049198007619L;

    private Long id;

    private String name;

    private Integer age;
}


