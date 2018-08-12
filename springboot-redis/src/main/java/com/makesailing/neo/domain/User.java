package com.makesailing.neo.domain;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/11 23:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  private static final long serialVersionUID = 5353304049198007619L;

  private String name;

  private Integer age;
}
