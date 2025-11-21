package com.xiaolintt.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xiaolintt.Bo.VerifySMSBO;
import com.xiaolintt.enums.RedisKeyEnum;
import com.xiaolintt.enums.ResponseStatusEnum;
import com.xiaolintt.exception.MyCustomException;
import com.xiaolintt.mapper.CandidateMapper;
import com.xiaolintt.mapper.InterviewRecordMapper;
import com.xiaolintt.po.Candidate;
import com.xiaolintt.po.InterviewRecord;
import com.xiaolintt.service.IWelcomeService;
import com.xiaolintt.vo.CandidateVO;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/21 21:50
 * @注释
 */
@Service
public class welcomeServiceImpl implements IWelcomeService {
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Resource
    private CandidateMapper candidateMapper;

    @Resource
    private InterviewRecordMapper interviewRecordMapper;
    @Override
    public String getSMSCode(String mobile) {
        if(StringUtils.isBlank(mobile)){
            throw new MyCustomException(ResponseStatusEnum.SYSTEM_ERROR_NOT_BLANK);
        }
        //检查是否以及生成了验证码，防止重复生成
        if(Boolean.TRUE.equals(redisTemplate.hasKey(RedisKeyEnum.SMS_CODE.getKEY() + mobile))){
            throw new MyCustomException(ResponseStatusEnum.SMS_NEED_WAIT_ERROR);
        }
        // 生成随机验证码
        String code = String.valueOf((int) (Math.random() * 900000 + 100000));
        System.out.println("验证码为：" + code);
        // 存储到redis
        redisTemplate.opsForValue().set(RedisKeyEnum.SMS_CODE.getKEY() + mobile, code, 2, TimeUnit.MINUTES);
        return code;
    }

    @Override
    public CandidateVO verify(VerifySMSBO verifySMSBO) {
        if(StringUtils.isBlank(verifySMSBO.getMobile())){
            throw new MyCustomException(ResponseStatusEnum.SYSTEM_ERROR_NOT_BLANK);
        }
        if(StringUtils.isBlank(verifySMSBO.getSmsCode())){
            throw new MyCustomException(ResponseStatusEnum.SYSTEM_ERROR_NOT_BLANK);
        }
        //redis中判断验证码是否正确
        //判断redis中是否有该手机号的验证码
        if(Boolean.FALSE.equals(redisTemplate.hasKey(RedisKeyEnum.SMS_CODE.getKEY() + verifySMSBO.getMobile()))){
            throw new MyCustomException(ResponseStatusEnum.SMS_CODE_ERROR);
        }
        String redisCode = redisTemplate.opsForValue().get(RedisKeyEnum.SMS_CODE.getKEY() + verifySMSBO.getMobile());
        if(StringUtils.isBlank(redisCode)||!redisCode.equals(verifySMSBO.getSmsCode())){
            throw new MyCustomException(ResponseStatusEnum.SMS_CODE_ERROR);
        }

        //根据用户手机号查询用户是否为候选人
        LambdaQueryWrapper<Candidate> lambdaQueryWrapper=new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Candidate::getMobile,verifySMSBO.getMobile());
        Candidate candidate = candidateMapper.selectOne(lambdaQueryWrapper);
        if(candidate==null){
            throw new MyCustomException(ResponseStatusEnum.USER_INFO_NOT_EXIST_ERROR);
        }
        //判断用户是否已经进行了面试
        LambdaQueryWrapper<InterviewRecord> lambdaQueryWrapper1 =new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.eq(InterviewRecord::getCandidateId,candidate.getId());
        Long count = interviewRecordMapper.selectCount(lambdaQueryWrapper1);
        if(count>0) {
            throw new MyCustomException(ResponseStatusEnum.USER_ALREADY_DID_INTERVIEW_ERROR);
        }
        //保存用户token
        String Token = UUID.randomUUID().toString();
        //将token保存到redis中，以后用户访问其它页面将要在header中携带token进行访问。
        redisTemplate.opsForValue().set(RedisKeyEnum.TOKEN.getKEY() + Token, candidate.getId(), 2, TimeUnit.HOURS);
        //将用户信息返回给前端
        CandidateVO candidateVO=new CandidateVO();
        BeanUtils.copyProperties(candidate, candidateVO);
        candidateVO.setUserToken(Token);
        candidateVO.setCandidateId(candidate.getId());
        //删除redis中的验证码
        redisTemplate.delete(RedisKeyEnum.SMS_CODE.getKEY() + verifySMSBO.getMobile());
        return candidateVO;
    }
}
