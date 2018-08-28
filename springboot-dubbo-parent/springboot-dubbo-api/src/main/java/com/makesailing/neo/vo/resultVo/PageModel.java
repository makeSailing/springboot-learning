package com.makesailing.neo.vo.resultVo;

import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:37
 */
@Data
public class PageModel<T> implements Serializable {

	private static final long serialVersionUID = 4834394989255600895L;
	/**
	 * 结果集
	 */
	private List<T> datas;
	/**
	 * 每页多少条数据
	 */
	private int pageSize = 10;
	/**
	 * 第几页 从0开始
	 */
	private int pageNo = 0;
	/**
	 * 总条数
	 */
	private long totalCount = 0;
	/**
	 * 总页数
	 */
	private int totalPages = 0;

	private int last_index = 0;

}


