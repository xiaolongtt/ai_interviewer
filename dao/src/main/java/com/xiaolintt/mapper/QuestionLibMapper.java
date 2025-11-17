package com.xiaolintt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaolintt.po.QuestionLib;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 面试题库表（每个数字人面试官都会对应一些面试题） Mapper 接口
 * </p>
 *
 * @author fyx
 * @since 2024-11-17
 */
@Mapper
public interface QuestionLibMapper extends BaseMapper<QuestionLib> {

}
