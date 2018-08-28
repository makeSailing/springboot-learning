package com.makesailing.neo.controller;

import com.makesailing.neo.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/27 18:11
 */
@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping("/canbuy")
	public String canBuy(@RequestParam("id") String id){
		return itemService.checkItemStatus(id) ? "can buy!" : "can not buy!";
	}

}


