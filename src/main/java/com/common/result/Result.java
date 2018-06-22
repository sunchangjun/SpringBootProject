package com.common.result;


public class Result {
	//返回码
		private String code;
		//返回信息
		private String message;
		//对象1
		private Object date;
		//对象2
		private Object extraData;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public Object getDate() {
			return date;
		}
		public void setDate(Object date) {
			this.date = date;
		}
		public Object getExtraData() {
			return extraData;
		}
		public void setExtraData(Object extraData) {
			this.extraData = extraData;
		}
		
		/**
		 * 设置返回结果
		 * @param retResult
		 * @param code
		 * @param message
		 * @param object
		 * @return
		 */
		public static Result setRetDate(String code,String message,Object object){
			Result retResult = new Result();
			retResult.setCode(code);
			retResult.setMessage(message);
			retResult.setDate(null==object?"":object);
			return retResult;
		}
		
		public static Result setRetDate(String code,String message,Object object, Object extraObject){
			Result retResult = new Result();
			retResult.setCode(code);
			retResult.setMessage(message);
			retResult.setDate(null==object?"":object);
			retResult.setExtraData(null==extraObject?"":extraObject);
			return retResult;
		}

}
