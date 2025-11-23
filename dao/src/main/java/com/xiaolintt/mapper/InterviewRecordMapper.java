package com.xiaolintt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaolintt.po.InterviewRecord;
import com.xiaolintt.vo.CandidateVO;
import com.xiaolintt.vo.InterviewRecordVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 面试记录表 Mapper 接口
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Mapper
public interface InterviewRecordMapper extends BaseMapper<InterviewRecord> {

    List<InterviewRecordVO> queryInterviewRecordLibList(String realName, String mobile);
}
