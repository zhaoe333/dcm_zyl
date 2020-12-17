package com.cm.entity.rules;

import com.cm.entity.CommonBean;
import lombok.Data;

/**
 * 物流报告文件上传信息 Entity Bean
 * @author popo
 * 2020-11-6
 */
@Data
public class TransitDocUploadingBean extends CommonBean {
    /** ID */
    private long id;
    /** 文件名 */
    private String fileName;
    /** 访问路径，若上传失败则为空 */
    private String fileUrl;
    /** 上传状态: 0--成功; 1--失败 */
    private int uploadingState;
    /** 总记录数 */
    private int totalCount;
    /** 未匹配到相应车辆的记录数 */
    private int failedCount;
}
