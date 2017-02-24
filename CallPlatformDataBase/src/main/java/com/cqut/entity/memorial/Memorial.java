package com.cqut.entity.memorial;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import org.directwebremoting.annotations.DataTransferObject;

import com.cqut.entity.AbstractEntity;

/**
 * 备忘录实体
 * 
 * @author wangshen
 * 
 */
@Entity
@DataTransferObject
public class Memorial extends AbstractEntity {

	private static final String[] PROPERTICE_NAME = new String[] {
			"MemorialId", "MemorialTime", "MemorialPosition", "MemorialText",
			"UserId", "MemorialDate" };
	private static final Class<?>[] PROPERTICE_TYPE = new Class[] {
			String.class, String.class, String.class, String.class,
			String.class, String.class };

	public Memorial() {

	}

	public Memorial(Map<String, Object> data) {
		try {
			this.setProperties(data);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 毫秒数+用户编号
	 */
	@Id
	@Column(length = 100)
	public String getMemorialId() {
		Object obj = getProperties().get(PROPERTICE_NAME[0]);
		return obj != null ? obj.toString() : null;
	}

	public void setMemorialId(String MemorialId) {
		getProperties().put(PROPERTICE_NAME[0], MemorialId);
	}

	@Transient
	@Override
	public String getEntityKey() {
		return getMemorialId();
	}

	@Transient
	@Override
	public void setEntityKey(String key) {
		setMemorialId(key);
	}

	/**
	 * 时间
	 */
	@Column(length = 50)
	public String getMemorialTime() {
		Object obj = getProperties().get(PROPERTICE_NAME[1]);
		return obj != null ? obj.toString() : null;
	}

	public void setMemorialTime(String MemorialTime) {
		getProperties().put(PROPERTICE_NAME[1], MemorialTime);
	}

	/**
	 * 地点
	 */
	@Column(length = 50)
	public String getMemorialPosition() {
		Object obj = getProperties().get(PROPERTICE_NAME[2]);
		return obj != null ? obj.toString() : null;
	}

	public void setMemorialPosition(String MemorialPosition) {
		getProperties().put(PROPERTICE_NAME[2], MemorialPosition);
	}

	/**
	 * 备注内容
	 */
	@Column(length = 255)
	public String getMemorialText() {
		Object obj = getProperties().get(PROPERTICE_NAME[3]);
		return obj != null ? obj.toString() : null;
	}

	public void setMemorialText(String MemorialText) {
		getProperties().put(PROPERTICE_NAME[3], MemorialText);
	}

	/**
	 * 用户编号
	 */
	@Column(length = 50)
	public String getUserId() {
		Object obj = getProperties().get(PROPERTICE_NAME[4]);
		return obj != null ? obj.toString() : null;
	}

	public void setUserId(String UserId) {
		getProperties().put(PROPERTICE_NAME[4], UserId);
	}

	/**
	 * 备忘录日期
	 */
	@Column(length = 50)
	public String getMemorialDate() {
		Object obj = getProperties().get(PROPERTICE_NAME[5]);
		return obj != null ? obj.toString() : null;
	}

	public void setMemorialDate(String MemorialDate) {
		getProperties().put(PROPERTICE_NAME[5], MemorialDate);
	}

	@Transient
	@Override
	public String[] getEntityPropertiesName() {
		return PROPERTICE_NAME;
	}

	@Transient
	@Override
	public Class<?>[] getEntityPropertiesType() {
		return PROPERTICE_TYPE;
	}

}
