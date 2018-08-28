package com.makesailing.neo.service;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/27 17:46
 */
public interface ItemService {

	String SERVICE_ID = "itemService";

	/**
	 * 检查商品是否可售
	 * @param id
	 * @return
	 */
	boolean checkItemStatus(String id);
}
