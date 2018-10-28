package cn.buy.utils;

//返回客户端的结果
public class ReturnResult {
	
	private int status;
	private Object data;
	private String message = "操作成功！";
	
	public ReturnResult() {
	}
	public ReturnResult(Object data) {
		this.status = Constants.ReturnResult.SUCCESS;
		this.data = data;
	}
	public ReturnResult(int status, Object data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	//返回成功状态
	public ReturnResult returnSuccess(Object obj){
		this.status = Constants.ReturnResult.SUCCESS;
		this.data = obj;
		this.message = "操作成功！";
		return this;
	}
	//默认值
	public ReturnResult returnSuccess(){
		this.status = Constants.ReturnResult.SUCCESS;
		this.message = "操作成功！";
		return this;
	}
	//返回失败状态
	public ReturnResult returnFail(String message){
		this.status = Constants.ReturnResult.FAIL;
		this.message = message;
		return this;
	}
	@Override
	public String toString() {
		return "ReturnResult [status=" + status + ", data=" + data
				+ ", message=" + message + "]";
	}
	
}
