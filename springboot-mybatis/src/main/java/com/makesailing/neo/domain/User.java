package com.makesailing.neo.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/12 16:17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 2188774443310292458L;

  private Long id;

  private String name;

  private Integer age;
}
