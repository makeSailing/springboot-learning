package com.makesailing.neo.vo.queryVo;

import java.io.Serializable;
import lombok.Data;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:36
 */
@Data
public class UserQuery extends Pagination implements Serializable {

	private static final long serialVersionUID = -566193958523289991L;

	//查询条件
	private int notId = -1;

	/** 园区编号 */
	private String parkCode;

	/** 登录名 or 真实姓名 */
	private String name;

	/** 登录名 or 真实姓名 模糊查询 */
	private String nameLike;

	//查询条件
	public int groupId = -1; //组ID

}


