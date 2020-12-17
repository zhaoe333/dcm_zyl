package com.cm.entity.dealer;

import com.cm.entity.CommonBean;
import lombok.Data;

/**
 * 经销商信息EntityBean
 * @author popo
 * 2020-11-5
 */
@Data
@Deprecated
public class DealerBean extends CommonBean {
	/** 经销商ID */
	private long id;
	/** 经销商代码 */
	private String code;
	/** 经销商名称 */
	private String name;
	/** 所在区域 */
	private String region;
	/** 集团名称 */
	private String groupName;
	/** 经销商等级 */
	private String creditRating;
	/** Business partner ID */
	private String bpid;
	/** Classification: [Active, Inactive, Cancel, Watch] */
	private String status;
	/** Dealer finance manager email address */
	private String email;
	/** Guarantor Chinese name semicomma as splitter in case of multiple */
	private String guarantor;
	/** 数据来源: [CMS] */
	private String source;
}