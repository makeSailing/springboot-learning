package com.makesailing.neo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/27 23:03
 */
@Data
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

  private static final long serialVersionUID = -2703945921213927662L;

  @Id
  @GeneratedValue
  @Column(name = "id")
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "address")
  private String address;

  @Column(name = "pwd")
  private String pwd;
}
