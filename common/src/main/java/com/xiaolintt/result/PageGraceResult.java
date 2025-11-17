package com.xiaolintt.result;

import com.baomidou.mybatisplus.extension.conditions.query.ChainQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Author xiaolong
 * @Date 2025/11/17 19:17
 * @注释
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageGraceResult {
    private Integer page;
    private Integer total; // 总页数
    private Integer records; // 总记录数
    private List<?> list;
}
