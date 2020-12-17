package com.cm.dubbo.sys;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 用户信息查询结果
 * @author popo
 * 2020-11-24
 */
@Builder
@Data
public class UserInfo {

	@ApiModelProperty("用户名")
	private String userName;

}