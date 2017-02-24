package com.cqut.entity;


import net.sf.json.JSONArray;

public class ExchangeMessage{
	private byte[] file;
	public String[] receiver;
	public String[][] keys;
	public String taskVoiceCode;
	public String fileName;
	public String type;
	public String csvFileName;
	
	
	public String getCsvFileName() {
		return csvFileName;
	}
	public void setCsvFileName(String csvFileName) {
		this.csvFileName = csvFileName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(String file) {
		JSONArray fileJA = JSONArray.fromObject(file);
		int length = fileJA.size();
		this.file = new byte[length];
		for(int i = 0;i < length;i++) {
			this.file[i] = Byte.valueOf(fileJA.getString(i)) ;
		}
	}
	public String[] getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		JSONArray receiverJA = JSONArray.fromObject(receiver);
		int length = receiverJA.size();
		this.receiver = new String[length];
		for(int i = 0;i < length;i++) {
			this.receiver[i] = receiverJA.getString(i);
		}
		
	}
	public String[][] getKeys() {
		return keys;
	}
	public void setKeys(String keys) {
		JSONArray keysJA = JSONArray.fromObject(keys);
		int length = keysJA.size();
		this.keys = new String[length][2];
		JSONArray temp;
		for(int i = 0;i < length;i++) {
			temp = keysJA.getJSONArray(i);
			this.keys[i][0] = temp.getString(0); 
			this.keys[i][1] = temp.getString(1); 
		}
		
	}
	public String getTaskVoiceCode() {
		return taskVoiceCode;
	}
	public void setTaskVoiceCode(String taskVoiceCode) {
		this.taskVoiceCode = taskVoiceCode;
	}
	
}
