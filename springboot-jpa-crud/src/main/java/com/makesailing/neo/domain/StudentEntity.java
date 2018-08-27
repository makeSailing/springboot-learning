package com.makesailing.neo.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/23 21:45
 */
@Data
@Entity
@Table(name = "student") // 注明表名,防止不一致的情况
@Repository
public class StudentEntity implements Serializable {

  private static final long serialVersionUID = 3222266340054874562L;

  @Id // 主键
  @GeneratedValue //自增
  @Column(name = "id") //列名
  private Long id;

  @Column(name = "name")
  private String name;

  @Column(name = "age")
  private Integer age;

  @Column(name = "address")
  private String address;
}
