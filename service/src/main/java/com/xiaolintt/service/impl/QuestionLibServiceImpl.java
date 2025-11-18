package com.xiaolintt.service.impl;

import com.github.pagehelper.PageHelper;
import com.xiaolintt.Bo.QuestionLibBo;
import com.xiaolintt.mapper.QuestionLibMapper;
import com.xiaolintt.po.QuestionLib;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.service.IQuestionLibService;
import com.xiaolintt.vo.QuestionLibVO;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:06
 * @注释
 */
@Service
public class QuestionLibServiceImpl implements IQuestionLibService {
    @Resource
    private QuestionLibMapper questionLibMapper;

    @Override
    public void createOrUpdate(QuestionLibBo questionLibBo) {
        String id = questionLibBo.getId();
        QuestionLib questionLib = new QuestionLib();
        BeanUtils.copyProperties(questionLibBo, questionLib);
        questionLib.setIsOn(1);
        questionLib.setUpdatedTime(LocalDateTime.now());
        if(StringUtils.isBlank(id)){
            // 新增
            questionLib.setCreateTime(LocalDateTime.now());
            questionLibMapper.insert(questionLib);
        }else{
            // 更新
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public PageGraceResult list(String aiName, String question, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<QuestionLibVO> list = questionLibMapper.selectQuestionByInterviewer(question, aiName);
        long records = questionLibMapper.selectCount(null);
        int totalPages = ((int) records + pageSize - 1) / pageSize;
        return new PageGraceResult(page, totalPages, (int) records, list);
    }

    @Override
    public void hide(String questionLibId) {
        QuestionLib questionLib = questionLibMapper.selectById(questionLibId);
        if(questionLib != null){
            questionLib.setIsOn(0);
            questionLib.setUpdatedTime(LocalDateTime.now());
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public void show(String questionLibId) {
        QuestionLib questionLib = questionLibMapper.selectById(questionLibId);
        if(questionLib != null){
            questionLib.setIsOn(1);
            questionLib.setUpdatedTime(LocalDateTime.now());
            questionLibMapper.updateById(questionLib);
        }
    }

    @Override
    public void delete(String questionLibId) {
        questionLibMapper.deleteById(questionLibId);
    }
}
