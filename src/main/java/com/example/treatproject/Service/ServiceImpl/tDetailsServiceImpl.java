package com.example.treatproject.Service.ServiceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.treatproject.Service.tDetailsService;
import com.example.treatproject.mapper.tDetailsMapper;
import com.example.treatproject.pojo.tDetails;
import org.springframework.stereotype.Service;

@Service
public class tDetailsServiceImpl extends ServiceImpl<tDetailsMapper, tDetails> implements tDetailsService {
}
