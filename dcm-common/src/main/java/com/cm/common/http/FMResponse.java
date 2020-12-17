package com.cm.common.http;


import lombok.Data;

/**tsp响应体*/
@Data
public class FMResponse<T> {
	
	/**响应code*/
	private String code;
	/**响应信息*/
	private String msg;
	/**响应数据*/
	private T body;
	
	public FMResponse(){
		super();
	}
	
	public FMResponse(String code,String msg){
		this.code = code;
		this.msg = msg;
	}
	
	public FMResponse(String code,String msg,T body){
		this.code = code;
		this.msg = msg;
		this.body=body;
	}

	public FMResponse(FMResponseCode code){
		this.code = code.toString();
		this.msg = code.getCnMsg();
	}

	public FMResponse(FMResponseCode code, T body){
		this.code = code.toString();
		this.msg = code.getCnMsg();
		this.body=body;
	}
	
	public FMResponse(FMResponseCode code, String msg, T body){
		this.code = code.toString();
		this.msg = msg;
		this.body= body;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static FMResponse success(Object body){
		return new FMResponse(FMResponseCode.normal,"成功",body);
	}

	
}
