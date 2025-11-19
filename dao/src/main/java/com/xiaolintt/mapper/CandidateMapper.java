package com.xiaolintt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaolintt.po.Candidate;
import com.xiaolintt.vo.CandidateVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


/**
 * <p>
 * 应聘者表 Mapper 接口
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Mapper

public interface CandidateMapper extends BaseMapper<Candidate> {

    List<CandidateVO> queryCandidateLibList(String realName, String mobile);

}
