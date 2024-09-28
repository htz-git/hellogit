package com.example.treatproject.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.treatproject.Service.companyService;
import com.example.treatproject.pojo.R;
import com.example.treatproject.pojo.company;
import com.example.treatproject.pojo.companyLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class companyController {

    @Autowired
    private companyService companyService;

    @GetMapping("/company/get")
    public R<List<company>> getCompany(){
        List<company> res = companyService.list();
        return R.success(res);
    }

    @PostMapping("/company/add")
    public R<String> companyAdd(@RequestBody company company){
        Integer current = companyService.count();
        company.setCompanyId(current+1);
        companyService.save(company);
        return R.success("新增成功");
    }

    @PostMapping("/company/like")
    public R<List<company>> companyLike(@RequestBody companyLike companyLike){
        LambdaQueryWrapper<company> queryWrapper = new LambdaQueryWrapper<>();
        if (companyLike.getCompanyType()!=null){
            queryWrapper.like(company::getCompanyType,companyLike.getCompanyType());
        }
        if (companyLike.getPostcode()!=null){
            queryWrapper.like(company::getPostcode,companyLike.getPostcode());
        }
        List<company> res = companyService.list(queryWrapper);
        return R.success(res);
    }

    @DeleteMapping("/company/delete")
    public R<String> companyDelete(@RequestParam(name = "id") Integer id){
        LambdaQueryWrapper<company> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(company::getCompanyId,id);
        companyService.remove(queryWrapper);
        return R.success("删除成功");
    }

    @PostMapping("/company/update")
    public R<String> companyUpdate(@RequestBody company company){
        LambdaQueryWrapper<company> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(com.example.treatproject.pojo.company::getCompanyName,company.getCompanyName());
        companyService.update(company,queryWrapper);
        return R.success("修改成功");
    }

}
