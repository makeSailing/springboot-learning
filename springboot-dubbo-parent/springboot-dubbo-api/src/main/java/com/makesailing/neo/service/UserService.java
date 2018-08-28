package com.makesailing.neo.service;

import com.makesailing.neo.domain.User;
import com.makesailing.neo.vo.queryVo.UserQuery;
import com.makesailing.neo.vo.resultVo.PageModel;
import java.util.List;

public interface UserService {

    String SERVICE_ID = "userService";

    /**
     * 根据id查询
     * @param id  用户ID
     * @return  商户订单
     */
    User findOneById(Long id);

    /**
     * 根据用户名密码查询
     * @param userName
     * @param pwd
     * @return
     */
    User findOneByNameAndPwd(String userName, String pwd);

    /**
     * 根据用户名称查询
     * @param userName
     * @return
     */
    User findOneByUserName(String userName);

    /**
     * 根据组ID查询
     * @param groupId
     * @return
     */
    List<User> findListByGroupId(int groupId);

    /**
     * 根据角色列表查询
     * @param roleTypes
     * @return
     */
    List<User> findListByRoles(String roleTypes);

    /**
     * 根据条件分页查询
     * @param query 查询条件
     * @return 分页信息
     */
    PageModel<User> findPageByQuery(UserQuery query);


    /**
     * 根据条件查询记录总条数
     * @param query 查询条件
     * @return 符合条件的记录数
     */
    int findCountByQuery(UserQuery query);

    /**
     * 查询账号是否存在
     * @param userName 账号
     * @param notId  ！= ID
     * @return 0：不存在，其他：存在。
     */
    int findCountByUserName(String userName, Long notId);

    /**
     * 添加一个用户
     * @param user
     * @return
     */
    int insertOne(User user);

    /**
     * 插入(插入属性不为空的值)
     * @param user
     * @return
     */
    int insertSelective(User user);
    /**
     * 根据ID修改用户信息
     * @param user
     * @return
     */
    int updateById(User user);

    /**
     * 根据ID修改组信息
     * @param user
     * @return
     */
    int updateByIdSelective(User user);


    int delById(Long id);

//    PageModel<User> getCarrierPage(Integer pageIndex, Integer pageSize, CarrierVo carrierVo);


}