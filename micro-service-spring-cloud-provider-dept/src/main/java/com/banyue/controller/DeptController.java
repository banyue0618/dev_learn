package com.banyue.controller;

import com.banyue.api.Vo.Dept;
import com.banyue.service.DeptBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: zhangsp
 * @date: 2023/3/23 16:37
 * @description:
 */
@RestController
public class DeptController {

    @Autowired
    private DeptBaseService deptBaseService;

    @GetMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable("id") int id) {
        return deptBaseService.get(id);
    }
    @GetMapping(value = "/dept/list")
    public List<Dept> list() {
        return deptBaseService.selectAll();
    }

}
