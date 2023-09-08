package com.banyue.service;

import com.banyue.api.Vo.Dept;

import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/3/23 16:35
 * @description:
 */
public interface DeptBaseService {

    Dept get(Integer deptNo);

    List<Dept> selectAll();

}
