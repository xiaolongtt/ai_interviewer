package com.xiaolintt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaolintt.po.QuestionLib;
import com.xiaolintt.vo.QuestionLibVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface QuestionLibMapper extends BaseMapper<QuestionLib> {

    List<QuestionLibVO> selectQuestionByInterviewer(String question, String aiName);

}
