package com.makesailing.neo.dao;

import com.makesailing.neo.domain.User;
import com.makesailing.neo.vo.queryVo.UserQuery;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * #
 *
 * @author <a href="mailto:jamie.li@wolaidai.com">jamie.li</a>
 * @date 2018/8/28 15:55
 */
public interface UserMapper {

	/**
	 * 根据条件分页查询
	 * @param query 查询条件
	 * @return 分页数据
	 */
	List<User> selectListByQuery(UserQuery query);

	/**
	 * 根据条件查询记录总条数
	 * @param query 查询条件
	 * @return 符合条件的记录数
	 */
	int selectCountByQuery(UserQuery query);

	int deleteByPrimaryKey(Long id);

	int insert(User record);

	int insertSelective(User record);


	User selectByPrimaryKey(Long id);


	User selectOneByNameAndPwd(String userName, String pwd);


	User selectOneByUserName(String userName);


	int selectCountByUserName(@Param("userName") String userName, @Param("notId") Long notId);

	int updateByPrimaryKeySelective(User record);


	int updateByPrimaryKey(User record);
}


