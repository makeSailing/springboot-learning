package com.makesailing.neo.service.impl;

import com.makesailing.neo.ItemService;
import java.util.Objects;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/27 17:48
 */
@Service(ItemService.SERVICE_ID)
public class ItemServiceImpl implements ItemService {

	@Override
	public boolean checkItemStatus(String id) {
		return Objects.equals(id, "111") ? true : false;
	}
}


