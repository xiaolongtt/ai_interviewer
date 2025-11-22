package com.xiaolintt.service;

import com.xiaolintt.Bo.QuestionLibBo;
import com.xiaolintt.result.PageGraceResult;
import com.xiaolintt.vo.InitQuestionsVO;

import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/15 0:06
 * @注释
 */
public interface IQuestionLibService {
    void createOrUpdate(QuestionLibBo questionLibBo);

    PageGraceResult list(String aiName, String question, Integer page, Integer pageSize);

    void hide(String questionLibId);

    void show(String questionLibId);

    void delete(String questionLibId);

    List<InitQuestionsVO> prepareQuestion(String candidateId);
}
