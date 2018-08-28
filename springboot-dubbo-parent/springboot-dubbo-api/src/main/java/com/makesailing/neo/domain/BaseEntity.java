package com.makesailing.neo.domain;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:28
 */
@Data
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 3683176180914284912L;

	/** 添加时间 */
	private Date dateAdd;

	/** 修改时间 */
	private Date dateUpd;
}


