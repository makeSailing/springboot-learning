package com.makesailing.neo.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/9/8 17:35
 */
@Data
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5899295761293711597L;

	private Long id;

	private String name;

	private String password;
}


