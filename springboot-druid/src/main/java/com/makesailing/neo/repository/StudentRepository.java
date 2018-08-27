package com.makesailing.neo.repository;

import com.makesailing.neo.domain.StudentEntity;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/23 21:54
 * JpaRepository接口（SpringDataJPA提供的简单数据操作接口）、JpaSpecificationExecutor（SpringDataJPA提供的复杂查询接口）、Serializable（序列化接口）
 */
public interface StudentRepository extends JpaRepository<StudentEntity, Long>, JpaSpecificationExecutor<StudentEntity>,
    Serializable {

}
