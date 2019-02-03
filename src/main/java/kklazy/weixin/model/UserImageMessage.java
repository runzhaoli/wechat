package kklazy.weixin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import kklazy.activities.model.Activity;
import kklazy.activities.model.ActivityItem;

/**
 * 接收用户发送的图片消息
 * 消息类型，此时固定为：image
 * 
 * @author kk
 */
@Entity
@Table(name = "WECHAT_USER_IMAGE_MESSAGE")
public class UserImageMessage extends DefaultMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9154246094990150709L;

	/**
	 * 关联的活动
	 */
	private Activity activity;

	/**
	 * 关联的项目
	 */
	private ActivityItem item;

	/**
	 * 图片链接
	 */
	private String picUrl;

	/**
	 * 图片本地地址
	 */
	private String localUrl;

	/**
	 * 	图片媒体文件id，可以调用获取媒体文件接口拉取数据
	 */
	private String mediaId;

	/**
	 * @return the activity
	 */
	@OneToOne(targetEntity = Activity.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_ID", nullable = true)
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the item
	 */
	@OneToOne(targetEntity = ActivityItem.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_ITEM_ID", nullable = true)
	public ActivityItem getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(ActivityItem item) {
		this.item = item;
	}

	/**
	 * @return the picUrl
	 */
	@Column(name = "PIC_URL")
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the localUrl
	 */
	@Column(name = "LOCAL_URL")
	public String getLocalUrl() {
		return localUrl;
	}

	/**
	 * @param localUrl the localUrl to set
	 */
	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	/**
	 * @return the mediaId
	 */
	@Column(name = "MEDIA_ID")
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
