package com.cqut.action;

import java.net.URLDecoder;

import javax.annotation.Resource;

import com.cqut.dao.common.ICommonDao;
import com.cqut.util.StringUtil;

public class Test {
	private String userId;
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	public String getUserId() {
		return userId;
	}

	@SuppressWarnings("deprecation")
	public void setUserId(String userId) {
		this.userId = URLDecoder.decode(userId);
	}

	public void exe() {
		// if (userId != null) {
		// // 获取呼叫任务名、呼叫时间、呼叫任务ID
		// String callTaskSql =
		// "select callTaskId, callTaskName, callTime from calltask where createMan = ?";
		// Object[] callTaskParam = { userId };
		// List<Object> ctNameList = commonDao.executeAndReturn(callTaskSql,
		// callTaskParam);
		// if (ctNameList != null && ctNameList.size() > 0) {
		// int length = ctNameList.size();
		// String[] callTaskId = new String[length];
		// JSONObject all = new JSONObject();
		//
		// for (int i = 0; i < length; i++) {
		// Object[] obj = (Object[]) ctNameList.get(i);
		// callTaskId[i] = (String) obj[0];
		//
		// JSONObject jo = new JSONObject();
		// jo.put("callTaskId", obj[0]);
		// jo.put("callTaskName", obj[1]);
		// jo.put("callTime", obj[2]);
		// jo.put("callNum", 0);
		// jo.put("receiptNum", 0);
		// all.put(obj[0], jo);
		// }
		//
		// // 通过callTaskId获取任务语音ID和回执按键次数
		// StringBuilder receiptSbf = new StringBuilder();
		// receiptSbf
		// .append("select tv.callTaskId,re.receiptKey from receipt re,taskVoice tv where re.taskVoiceId = tv.taskVoiceId and (");
		// Object[] receiptParam = new Object[length * 2];
		// int receiptIndex = 0;
		// for (int i = 0; i < length; i++) {
		// receiptSbf
		// .append("(tv.callTaskId = ? and tv.level0 = (select max(level0) from taskvoice where calltaskId = ?)) or");
		// Object temp = ((JSONObject) (all.get(callTaskId[i])))
		// .get("callTaskId");
		// receiptParam[receiptIndex++] = temp;
		// receiptParam[receiptIndex++] = temp;
		// }
		// receiptSbf.setCharAt(receiptSbf.length() - 1, ')');
		// receiptSbf.setCharAt(receiptSbf.length() - 2, ' ');
		// List<Object> callTaskIdList = commonDao.executeAndReturn(
		// receiptSbf.toString(), receiptParam);
		//
		// if (callTaskIdList != null && callTaskIdList.size() > 0) {
		// int receiptKeyLength = callTaskIdList.size();
		//
		// for (int i = 0; i < receiptKeyLength; i++) {
		// Object[] obj = (Object[]) callTaskIdList.get(i);
		//
		// JSONObject joo = all.getJSONObject(obj[0] + "");
		// joo.put("callNum", (Integer) (joo.get("callNum")) + 1);
		//
		// if (obj[1] != null) {
		// joo.put("receiptNum",
		// (Integer) (joo.get("receiptNum")) + 1);
		// }
		// all.put(obj[0], joo);
		// }
		// }
		//
		// JJCommon.sendMessageToJS(all.toString());
		// } else {
		// JJCommon.sendMessageToJS("{}");
		// }
		// } else {
		// JJCommon.sendMessageToJS("userId is null");
		// }
		
	}
	
	public static void main(String[] args) {
		String str = "王珅";
		System.out.println(StringUtil.getPingYin(str));
	}
}
