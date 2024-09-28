package com.example.treatproject.Controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.treatproject.Service.tDetailsService;
import com.example.treatproject.pojo.R;
import com.example.treatproject.pojo.tDetails;
import com.example.treatproject.pojo.tDetailsLike;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class tDetailsController {

    @Autowired
    private tDetailsService tDetailsService;

    @GetMapping("/tdetails/get")
    public R<List<tDetails>> gettDetails(){
        List<tDetails> res = tDetailsService.list();
        return R.success(res);
    }

    @PostMapping("/tdetails/like")
    public R<List<tDetails>> liketDetails(@RequestBody tDetailsLike tDetailsLike){
        LambdaQueryWrapper<tDetails> queryWrapper = new LambdaQueryWrapper<>();
        if (tDetailsLike.getCCategory()!= null){
            queryWrapper.like(tDetails::getCCategory,tDetailsLike.getCCategory());
        }
        if (tDetailsLike.getPName()!=null){
            queryWrapper.like(tDetails::getPName,tDetailsLike.getPName());
        }
        List<tDetails> res = tDetailsService.list(queryWrapper);
        return R.success(res);
    }

    @DeleteMapping("/tdetails/delete")
    public R<String> tdetailsDelete(@RequestParam(name = "id") String id){
        LambdaQueryWrapper<tDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(tDetails::getPCode,id);
        tDetailsService.remove(queryWrapper);
        return R.success("成功");
    }

    @PostMapping("/tdetails/add")
    public R<String> tdetailsAdd(@RequestBody tDetails details){
        Integer number = tDetailsService.count();
        details.setHNumber(number+1);
        float amount = details.getUPrice()*details.getQuantity();
        details.setAmount(amount);
        tDetailsService.save(details);
        return R.success("保存成功");
    }

    @PostMapping("/tdetails/update")
    public R<String> tdetailsupdate(@RequestBody tDetails details){
        LambdaQueryWrapper<tDetails> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(tDetails::getPCode,details.getPCode());
        details.setAmount(details.getUPrice()*details.getQuantity());
        tDetailsService.update(details,queryWrapper);
        return R.success("修改成功");
    }

    @PostMapping("/details/batchall")
    public R<String> tdetailsbatch(@RequestBody tDetails details){
        float amount = details.getUPrice()*details.getQuantity();
        details.setAmount(amount);
        tDetailsService.save(details);
        return R.success("保存成功");
    }
}
