package com.cqut.service.DBAction;

import java.io.File;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import util.SpringUtil;
import net.sf.json.JSONObject;

import com.cqut.dao.common.ICommonDao;
import com.cqut.entity.ExchangeMessage;
import com.cqut.entity.YZ_GroupCall.YZ_GroupCall;
import com.cqut.service.YZ_GroupCall.customInterface.IY_GroupCallService;
import com.cqut.util.SystemUtil;
import com.cqut.util.WriteFile;

@Component
public class DateBaseService {
	private static String basePath = SystemUtil
			.getSystemParameter("taskBasePath");
	@Resource(name = "commonDao")
	private ICommonDao commonDao;

	public void DBOperation(List<ExchangeMessage> emList,
			String[] voiceFileNameList, int[] portList) {
		int length = emList.size();
		StringBuilder fileAll;
		String[] receiveCSVFileNameList = new String[length];
		for (int i = 0; i < length; i++) {

			String receiveCSVFileName = emList.get(i).getCsvFileName();
			fileAll = new StringBuilder();
			fileAll.append(basePath);
			fileAll.append(File.separatorChar);
			fileAll.append(receiveCSVFileName);
			WriteFile.writeReceiveCSV(fileAll.toString(),
					emList.get(i).receiver);
			receiveCSVFileNameList[i] = receiveCSVFileName;
		}

		saveDB(emList, voiceFileNameList, receiveCSVFileNameList, portList);
	}

	public void saveDB(List<ExchangeMessage> emList,
			String[] voiceFileNameList, String[] receiveCSVFileNameList,
			int[] portList) {
		int emListLength = emList.size();
		StringBuilder nameSb;
		String[] nemaList = new String[emListLength];
		for (int i = 0; i < emListLength; i++) {
			nameSb = new StringBuilder();
			nameSb.append("IVR");
			// nameSb.append(emList.get(i).taskVoiceCode);
			nameSb.append((int) Math.random() * 9 + 1);
			nameSb.append("_");
			nameSb.append(System.nanoTime());
			String name = nameSb.toString();
			StringBuilder nameAllSb = new StringBuilder(name);
			nameAllSb.append(".txt");
			WriteFile.writeContentFile(nameAllSb.toString(),
					emList.get(i).keys, voiceFileNameList[i],
					emList.get(i).taskVoiceCode, emList.get(i).type);
			nemaList[i] = name;
		}

		StringBuffer sql;
		for (int i = 0; i < emListLength; i++) {
			sql = new StringBuffer();
			sql.append("set identity_insert YZ_GroupCall ON insert into YZ_GroupCall(id,status1,status2,name,telAtt,CallType,content,theDate,theTime) ");
			String[] timeArr = getTime();
			sql.append("SELECT max(id)+1,");
			sql.append("'1','0','");
			sql.append(nemaList[i]);
			sql.append("','");
			sql.append(receiveCSVFileNameList[i]);
			sql.append("','3','");
			sql.append(nemaList[i]);
			sql.append(",");
			sql.append(portList[i]);
			sql.append("','");
			sql.append(timeArr[0]);
			sql.append("','");
			sql.append(timeArr[1]);
			sql.append("' FROM YZ_GroupCall set identity_insert YZ_GroupCall OFF ");
			try {
				commonDao.execute(sql.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public String[] getTime() {
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_MONTH); // 日
		int month = cal.get(Calendar.MONTH) + 1;// 月
		int year = cal.get(Calendar.YEAR); // 年
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int second = cal.get(Calendar.SECOND);
		int minute = 0;
		if (second < 50) {
			minute = cal.get(Calendar.MINUTE);
		} else {
			minute = cal.get(Calendar.MINUTE) + 1;
		}

		String[] strArr = new String[2];
		strArr[0] = year + "/" + month + "/" + day;
		String hourstr = null;
		String minutestr = null;

		if (minute >= 60) {
			hour += 1;
			minute -= 60;
		}
		if (hour < 10)
			hourstr = "0" + hour;
		else
			hourstr = hour + "";
		if (minute < 10)
			minutestr = "0" + minute;
		else
			minutestr = minute + "";

		strArr[1] = hourstr + ":" + minutestr;
		return strArr;
	}

	/*
	 * public String getId(){ String sql =
	 * "SELECT MAX(ID)+1 AS id FROM YZ_GroupCall"; List<Object> list =
	 * commonDao.executeAndReturn(sql); String id = "1";
	 * if(list!=null&&list.size()!=0){ Object o = list.get(0); if(o != null){ id
	 * = o + ""; } } return id; }
	 */
}
