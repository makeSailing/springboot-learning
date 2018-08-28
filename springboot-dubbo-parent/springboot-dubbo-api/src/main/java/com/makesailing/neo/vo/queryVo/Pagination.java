package com.makesailing.neo.vo.queryVo;

import lombok.Data;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:38
 */
@Data
public class Pagination {

	/**
	 * 第几页 从0开始
	 */
	private int pageNo = 0;

	//每页多少条数据
	private int pageSize = 10;

	private int totalCount = 0;

	private int skip = 0;
}


