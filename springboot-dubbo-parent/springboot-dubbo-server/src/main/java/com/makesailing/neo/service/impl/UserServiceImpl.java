package com.makesailing.neo.service.impl;

import com.makesailing.neo.dao.UserMapper;
import com.makesailing.neo.domain.User;
import com.makesailing.neo.service.UserService;
import com.makesailing.neo.vo.queryVo.UserQuery;
import com.makesailing.neo.vo.resultVo.PageModel;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:47
 */
@Service(UserService.SERVICE_ID)
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public User findOneById(Long id) {
		return this.userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User findOneByNameAndPwd(String userName, String pwd) {
		return this.userMapper.selectOneByNameAndPwd(userName, pwd);
	}

	@Override
	public User findOneByUserName(String userName) {
		return this.userMapper.selectOneByUserName(userName);
	}

	@Override
	public PageModel<User> findPageByQuery(UserQuery query) {
		PageModel<User> pageModel = new PageModel<User>();
		pageModel.setDatas(this.userMapper.selectListByQuery(query));//列表数据
		pageModel.setTotalCount(this.userMapper.selectCountByQuery(query));//总条数
		return pageModel;
	}

	@Override
	public int findCountByQuery(UserQuery query) {
		return this.userMapper.selectCountByQuery(query);
	}

	@Override
	public List<User> findListByGroupId(int groupId) {
		return null;
	}

	@Override
	public List<User> findListByRoles(String roleTypes) {
		return null;
	}

	@Override
	public int findCountByUserName(String userName, Long notId) {
		if(notId == null){
			notId = 0L;
		}
		return this.userMapper.selectCountByUserName(userName, notId);
	}

	@Override
	public int insertOne(User user) {
		return this.userMapper.insert(user);
	}

	@Override
	public int insertSelective(User user) {
		return this.userMapper.insertSelective(user);
	}

	@Override
	public int updateById(User user) {
		return this.userMapper.updateByPrimaryKey(user);
	}

	@Override
	public int updateByIdSelective(User user) {
		return this.userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public int delById(Long id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}
}


