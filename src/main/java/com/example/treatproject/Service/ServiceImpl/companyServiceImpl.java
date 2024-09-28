package com.example.treatproject.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.treatproject.Service.companyService;
import com.example.treatproject.mapper.companyMapper;
import com.example.treatproject.pojo.company;
import org.springframework.stereotype.Service;

@Service
public class companyServiceImpl extends ServiceImpl<companyMapper, company> implements companyService {
}
