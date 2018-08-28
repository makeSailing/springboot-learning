package com.makesailing.neo.domain;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:34
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

	private static final long serialVersionUID = 7600340255924472925L;

	/** 主键ID，自增  */
	private Long id;

	/** 登录名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 园区编号 */
	private String parkCode;

	/** 园区 */
	private String parkName;

	/** 密码 */
	private String pwd;

	/** 状态，0-无效，1-有效 */
	private Byte state;

	/** 角色，0-园区管理员，1-系统管理员 */
	private Byte role;

	/** 最近一次访问时间 */
	private Date lastDate;

	/** 最后一次登录的IP */
	private String lastIp;

	/** 登录次数 */
	private Integer loginCount;
}


