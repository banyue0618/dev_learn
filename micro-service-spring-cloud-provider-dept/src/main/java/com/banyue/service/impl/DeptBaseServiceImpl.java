package com.banyue.service.impl;

import com.banyue.mapper.DeptMapper;
import com.banyue.service.DeptBaseService;
import com.banyue.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/3/23 16:35
 * @description:
 */
@Service
public class DeptBaseServiceImpl implements DeptBaseService  {

    @Autowired
    private DeptMapper deptMapper;


    @Override
    public Dept get(Integer deptNo) {
        return deptMapper.selectByPrimaryKey(deptNo);
    }

    @Override
    public List<Dept> selectAll() {
        return deptMapper.getAll();
    }
}
