package com.banyue.mapper;


import com.banyue.vo.Dept;

import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/3/23 16:30
 * @description:
 */

/**
 *  如果不添加@mapper注解，则需要在启动类制定包的扫描路径。
 */
public interface DeptMapper {

    /**
     * 根据主键获取数据
     * @param deptNo
     * @return
     */
    Dept selectByPrimaryKey(Integer deptNo);

    /**
     * 获取表中的全部数据
     * @return
     */
    List<Dept> getAll();

}
