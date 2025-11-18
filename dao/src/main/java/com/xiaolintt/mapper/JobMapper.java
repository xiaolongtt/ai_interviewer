package com.xiaolintt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaolintt.po.Job;
import com.xiaolintt.vo.JobVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 职位信息表 Mapper 接口
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Mapper
public interface JobMapper extends BaseMapper<Job> {

    List<JobVo> selectListByInterviewer();

    JobVo selectJobVoById(String jobId);
}
