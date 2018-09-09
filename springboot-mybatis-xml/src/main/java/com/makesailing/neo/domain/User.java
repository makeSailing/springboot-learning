package com.makesailing.neo.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * @author jamie.li
 */
@Data
public class User implements Serializable {

    private static final long serialVersionUID = 8899491835123984445L;

    private Long id;

    private String name;

    private Byte age;

    private String pwd;

}