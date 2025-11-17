package com.xiaolintt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaolintt.po.Candidate;
import org.apache.ibatis.annotations.Mapper;


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

}
