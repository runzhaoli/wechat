package kklazy.common.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kklazy.common.constants.ContextConstants;
import kklazy.persistence.utils.MD5Utils;
import kklazy.security.model.Dictionary;
import kklazy.security.model.Employee;
import kklazy.security.model.Organization;
import kklazy.security.model.Resource;
import kklazy.security.model.Role;
import kklazy.security.model.User;
import kklazy.weixin.model.WechatAccount;

/**
 * @author kk
 *
 */
public class InitializationData {
	
	/**
	 * 
	 */
	public static Map<String, Object> data = new HashMap<String, Object>();
	
	public static final String USER = "user";
	
	public static final String DICTIONARY = "dictionary";
	
	static {
		user();
		dictionaries();
	}
	
	/**
	 * @return
	 */
	public static void user() {
		
		/**
		 * Add Organization
		 */
		Organization organization = new Organization();
		organization.setCode("Newtouch");
		organization.setName("新致软件");

		/**
		 * Add Role
		 */
		Set<Role> roles = new HashSet<Role>();
		Role r_admin = new Role();
		r_admin.setCode("ROLE_ADMIN");
		r_admin.setName("管理员");

		/**
		 * Add Resource
		 */
		r_admin.setResources(new ArrayList<Resource>());

		Resource upload = new Resource();
		upload.setUrl("/web/upload.kklazy");

		r_admin.getResources().add(upload);

		Resource personnel = new Resource();
		personnel.setType("1");
		personnel.setName("用户管理");
		personnel.setCode("user manager");
		personnel.setUrl("#");
		personnel.setSort(98);
		personnel.setSublevel(new ArrayList<Resource>());
		
		Resource initfans = new Resource();
		initfans.setType("2");
		initfans.setName("粉丝查询");
		initfans.setCode("search");
		initfans.setUrl("/fans/initsearch.kklazy");
		initfans.setSort(1);

		Resource fanssearch = new Resource();
		fanssearch.setUrl("/fans/search.kklazy");
		Resource fanssync = new Resource();
		fanssync.setUrl("/web/sync.kklazy");

		personnel.getSublevel().add(initfans);
		personnel.getSublevel().add(fanssearch);
		personnel.getSublevel().add(fanssync);

		Resource initstaff = new Resource();
		initstaff.setType("2");
		initstaff.setName("新致员工");
		initstaff.setCode("search");
		initstaff.setUrl("/staff/initsearch.kklazy");
		initstaff.setSort(2);
		
		Resource staffsearch = new Resource();
		staffsearch.setUrl("/staff/search.kklazy");
		Resource staffdownloadtemplate = new Resource();
		staffdownloadtemplate.setUrl("/staff/downloadtemplate.kklazy");
		Resource staffuploadexcel = new Resource();
		staffuploadexcel.setUrl("/staff/uploadexcel.kklazy");
		Resource staffdownloadexcel = new Resource();
		staffdownloadexcel.setUrl("/staff/downloadexcel.kklazy");
		Resource staffoperation = new Resource();
		staffoperation.setUrl("/staff/operation.kklazy");
		Resource staffinit = new Resource();
		staffinit.setUrl("/staff/init.kklazy");
		
		personnel.getSublevel().add(initstaff);
		personnel.getSublevel().add(staffsearch);
		personnel.getSublevel().add(staffdownloadtemplate);
		personnel.getSublevel().add(staffuploadexcel);
		personnel.getSublevel().add(staffdownloadexcel);
		personnel.getSublevel().add(staffoperation);
		personnel.getSublevel().add(staffinit);
		
		r_admin.getResources().add(personnel);
		
		r_admin.getResources().add(initfans);
		r_admin.getResources().add(fanssearch);
		r_admin.getResources().add(fanssync);
		
		r_admin.getResources().add(initstaff);
		r_admin.getResources().add(staffsearch);
		r_admin.getResources().add(staffdownloadtemplate);
		r_admin.getResources().add(staffuploadexcel);
		r_admin.getResources().add(staffdownloadexcel);
		r_admin.getResources().add(staffoperation);
		r_admin.getResources().add(staffinit);
		
		Resource module = new Resource();
		module.setType("1");
		module.setName("模块管理");
		module.setCode("module manager");
		module.setUrl("#");
		module.setSort(1);
		module.setSublevel(new ArrayList<Resource>());
		
		Resource initvote = new Resource();
		initvote.setType("2");
		initvote.setName("投票管理");
		initvote.setCode("search");
		initvote.setUrl("/vote/initsearch.kklazy");
		initvote.setSort(1);

		Resource votesearch = new Resource();
		votesearch.setUrl("/vote/search.kklazy");
		Resource votemodify = new Resource();
		votemodify.setUrl("/vote/modify.kklazy");
		Resource votecommit = new Resource();
		votecommit.setUrl("/vote/commit.kklazy");
		Resource voteoptionadd = new Resource();
		voteoptionadd.setUrl("/voteoption/add.kklazy");

		module.getSublevel().add(initvote);
		module.getSublevel().add(votesearch);
		module.getSublevel().add(votemodify);
		module.getSublevel().add(votecommit);
		module.getSublevel().add(voteoptionadd);

		r_admin.getResources().add(module);

		r_admin.getResources().add(initvote);
		r_admin.getResources().add(votesearch);
		r_admin.getResources().add(votemodify);
		r_admin.getResources().add(votecommit);
		r_admin.getResources().add(voteoptionadd);
		
		Resource context = new Resource();
		context.setType("1");
		context.setName("内容管理");
		context.setCode("context manager");
		context.setUrl("#");
		context.setSort(2);
		context.setSublevel(new ArrayList<Resource>());

		Resource initsliderloop = new Resource();
		initsliderloop.setType("2");
		initsliderloop.setName("图片轮播管理");
		initsliderloop.setCode("search");
		initsliderloop.setUrl("/sliderloop/initsearch.kklazy");
		initsliderloop.setSort(1);

		Resource sliderloopsearch = new Resource();
		sliderloopsearch.setUrl("/sliderloop/search.kklazy");
		Resource sliderloopmodify = new Resource();
		sliderloopmodify.setUrl("/sliderloop/modify.kklazy");
		Resource sliderloopcommit = new Resource();
		sliderloopcommit.setUrl("/sliderloop/commit.kklazy");

		context.getSublevel().add(initsliderloop);
		context.getSublevel().add(sliderloopsearch);
		context.getSublevel().add(sliderloopmodify);
		context.getSublevel().add(sliderloopcommit);

		Resource initindexnavmenu = new Resource();
		initindexnavmenu.setType("2");
		initindexnavmenu.setName("首页导航菜单管理");
		initindexnavmenu.setCode("search");
		initindexnavmenu.setUrl("/indexnavmenu/initsearch.kklazy");
		initindexnavmenu.setSort(2);

		Resource indexnavmenusearch = new Resource();
		indexnavmenusearch.setUrl("/indexnavmenu/search.kklazy");
		Resource indexnavmenumodify = new Resource();
		indexnavmenumodify.setUrl("/indexnavmenu/modify.kklazy");
		Resource indexnavmenucommit = new Resource();
		indexnavmenucommit.setUrl("/indexnavmenu/commit.kklazy");

		context.getSublevel().add(initindexnavmenu);
		context.getSublevel().add(indexnavmenusearch);
		context.getSublevel().add(indexnavmenumodify);
		context.getSublevel().add(indexnavmenucommit);

		Resource initarticletype = new Resource();
		initarticletype.setType("2");
		initarticletype.setName("文章类型管理");
		initarticletype.setCode("search");
		initarticletype.setUrl("/articletype/initsearch.kklazy");
		initarticletype.setSort(3);

		Resource articletypesearch = new Resource();
		articletypesearch.setUrl("/articletype/search.kklazy");
		Resource articletypemodify = new Resource();
		articletypemodify.setUrl("/articletype/modify.kklazy");
		Resource articletypecommit = new Resource();
		articletypecommit.setUrl("/articletype/commit.kklazy");

		context.getSublevel().add(initarticletype);
		context.getSublevel().add(articletypesearch);
		context.getSublevel().add(articletypemodify);
		context.getSublevel().add(articletypecommit);

		Resource initarticle = new Resource();
		initarticle.setType("2");
		initarticle.setName("文章管理");
		initarticle.setCode("search");
		initarticle.setUrl("/article/initsearch.kklazy");
		initarticle.setSort(4);

		Resource articlesearch = new Resource();
		articlesearch.setUrl("/article/search.kklazy");
		Resource articlemodify = new Resource();
		articlemodify.setUrl("/article/modify.kklazy");
		Resource articlecommit = new Resource();
		articlecommit.setUrl("/article/commit.kklazy");

		context.getSublevel().add(initarticle);
		context.getSublevel().add(articlesearch);
		context.getSublevel().add(articlemodify);
		context.getSublevel().add(articlecommit);

		
		r_admin.getResources().add(context);

		r_admin.getResources().add(initsliderloop);
		r_admin.getResources().add(sliderloopsearch);
		r_admin.getResources().add(sliderloopmodify);
		r_admin.getResources().add(sliderloopcommit);

		r_admin.getResources().add(initindexnavmenu);
		r_admin.getResources().add(indexnavmenusearch);
		r_admin.getResources().add(indexnavmenumodify);
		r_admin.getResources().add(indexnavmenucommit);

		r_admin.getResources().add(initarticletype);
		r_admin.getResources().add(articletypesearch);
		r_admin.getResources().add(articletypemodify);
		r_admin.getResources().add(articletypecommit);

		r_admin.getResources().add(initarticle);
		r_admin.getResources().add(articlesearch);
		r_admin.getResources().add(articlemodify);
		r_admin.getResources().add(articlecommit);
		
		Resource setting = new Resource();
		setting.setType("1");
		setting.setName("微信设置");
		setting.setCode("wechat setting");
		setting.setUrl("#");
		setting.setSort(3);
		setting.setSublevel(new ArrayList<Resource>());
		
		Resource initmessagetemplate = new Resource();
		initmessagetemplate.setType("2");
		initmessagetemplate.setName("消息模板管理");
		initmessagetemplate.setCode("search");
		initmessagetemplate.setUrl("/messagetemplate/initsearch.kklazy");
		initmessagetemplate.setSort(1);

		Resource messagetemplatesearch = new Resource();
		messagetemplatesearch.setUrl("/messagetemplate/search.kklazy");
		Resource messagetemplatemodify = new Resource();
		messagetemplatemodify.setUrl("/messagetemplate/modify.kklazy");
		Resource messagetemplatecommit = new Resource();
		messagetemplatecommit.setUrl("/messagetemplate/commit.kklazy");

		setting.getSublevel().add(initmessagetemplate);
		setting.getSublevel().add(messagetemplatesearch);
		setting.getSublevel().add(messagetemplatemodify);
		setting.getSublevel().add(messagetemplatecommit);
		
		Resource initmessagecontent = new Resource();
		initmessagecontent.setType("2");
		initmessagecontent.setName("消息内容管理");
		initmessagecontent.setCode("search");
		initmessagecontent.setUrl("/messagecontent/initsearch.kklazy");
		initmessagecontent.setSort(2);

		Resource messagecontentsearch = new Resource();
		messagecontentsearch.setUrl("/messagecontent/search.kklazy");
		Resource messagecontentmodify = new Resource();
		messagecontentmodify.setUrl("/messagecontent/modify.kklazy");
		Resource messagecontentcommit = new Resource();
		messagecontentcommit.setUrl("/messagecontent/commit.kklazy");

		setting.getSublevel().add(initmessagecontent);
		setting.getSublevel().add(messagecontentsearch);
		setting.getSublevel().add(messagecontentmodify);
		setting.getSublevel().add(messagecontentcommit);

		Resource initwechatmenu = new Resource();
		initwechatmenu.setType("2");
		initwechatmenu.setName("微信菜单管理");
		initwechatmenu.setCode("search");
		initwechatmenu.setUrl("/wechatmenu/initsearch.kklazy");
		initwechatmenu.setSort(3);

		Resource wechatmenusearch = new Resource();
		wechatmenusearch.setUrl("/wechatmenu/search.kklazy");
		Resource wechatmenusync = new Resource();
		wechatmenusync.setUrl("/wechatmenu/sync.kklazy");
		Resource wechatmenuadd = new Resource();
		wechatmenuadd.setUrl("/wechatmenu/add.kklazy");
		Resource wechatmenumodify = new Resource();
		wechatmenumodify.setUrl("/wechatmenu/modify.kklazy");
		Resource wechatmenucommit = new Resource();
		wechatmenucommit.setUrl("/wechatmenu/commit.kklazy");

		setting.getSublevel().add(initwechatmenu);
		setting.getSublevel().add(wechatmenusearch);
		setting.getSublevel().add(wechatmenusync);
		setting.getSublevel().add(wechatmenuadd);
		setting.getSublevel().add(wechatmenumodify);
		setting.getSublevel().add(wechatmenucommit);

		r_admin.getResources().add(setting);

		r_admin.getResources().add(initmessagetemplate);
		r_admin.getResources().add(messagetemplatesearch);
		r_admin.getResources().add(messagetemplatemodify);
		r_admin.getResources().add(messagetemplatecommit);

		r_admin.getResources().add(initmessagecontent);
		r_admin.getResources().add(messagecontentsearch);
		r_admin.getResources().add(messagecontentmodify);
		r_admin.getResources().add(messagecontentcommit);

		r_admin.getResources().add(initwechatmenu);
		r_admin.getResources().add(wechatmenusearch);
		r_admin.getResources().add(wechatmenusync);
		r_admin.getResources().add(wechatmenuadd);
		r_admin.getResources().add(wechatmenumodify);
		r_admin.getResources().add(wechatmenucommit);

		Resource system = new Resource();
		system.setType("1");
		system.setName("系统设置");
		system.setCode("system setting");
		system.setUrl("#");
		system.setSort(99);
		system.setSublevel(new ArrayList<Resource>());

		Resource initorganization = new Resource();
		initorganization.setType("2");
		initorganization.setName("机构管理");
		initorganization.setCode("search");
		initorganization.setUrl("/organization/initsearch.kklazy");
		initorganization.setSort(1);

		Resource organizationsearch = new Resource();
		organizationsearch.setUrl("/organization/search.kklazy");
		Resource organizationmodify = new Resource();
		organizationmodify.setUrl("/organization/modify.kklazy");
		Resource organizationcommit = new Resource();
		organizationcommit.setUrl("/organization/commit.kklazy");

		system.getSublevel().add(initorganization);
		system.getSublevel().add(organizationsearch);
		setting.getSublevel().add(organizationmodify);
		setting.getSublevel().add(organizationcommit);

		r_admin.getResources().add(system);

		r_admin.getResources().add(initorganization);
		r_admin.getResources().add(organizationsearch);
		r_admin.getResources().add(organizationmodify);
		r_admin.getResources().add(organizationcommit);
		
		roles.add(r_admin);
		
		/**
		 * Add Role
		 */
		Role r_default = new Role();
		r_default.setCode("ROLE_DEFAULT");
		r_default.setName("默认角色");
		
		/**
		 * Add Resource
		 */
		r_default.setResources(new ArrayList<Resource>());
		
		Resource indexjsp = new Resource();
		indexjsp.setUrl("/webpages/index.jsp");
		
		r_default.getResources().add(indexjsp);

		Resource cente = new Resource();
		cente.setUrl("/console/center.kklazy");
		
		r_default.getResources().add(cente);
		
		Resource index = new Resource();
		index.setUrl("/console/index.kklazy");
		
		r_default.getResources().add(index);

		roles.add(r_default);
		
		/**
		 * Add Wechat Account
		 */
		 // 新致工会公众平台信息
		WechatAccount account = new WechatAccount();
		account.setAccountid("gh_ed89f368acc6");
		account.setAppid("wx20ae2aada16da96a");
		account.setAppsecret("c2c0bccff1883a3e21623273edb2e28a");
		account.setToken("newtouchgh");
		account.setType("1");
		
		account.setName("新致工会");
		account.setEmail("gonghuifwh@newtouch.cn");
		account.setDescription("新致软件工会服务号");
		
		/**
		 * Add Employee and User
		 */
		Employee employee = new Employee();
		employee.setName("kkLazy");
		employee.setOrganization(organization);
		employee.setRoles(roles);
		employee.setEnabled(Boolean.TRUE);

		User user = new User();
		user.setUsername("kk");
		user.setPassword(MD5Utils.md5Hex("kk{kk}"));
		user.setEmployee(employee);
		
		data.put(InitializationData.USER, user);
		
	}
	
	public static void dictionaries() {
		
		List<Dictionary> retval = new ArrayList<Dictionary>();
		
		retval.add(new Dictionary(ContextConstants.GROUP.STATUS, ContextConstants.STATUS.STATUS_01, "未开始", "1", null, null, null, 1));
		retval.add(new Dictionary(ContextConstants.GROUP.STATUS, ContextConstants.STATUS.STATUS_02, "进行中", "2", null, null, null, 2));
		retval.add(new Dictionary(ContextConstants.GROUP.STATUS, ContextConstants.STATUS.STATUS_03, "已结束", "3", null, null, null, 3));

		retval.add(new Dictionary(ContextConstants.GROUP.GENDER, ContextConstants.GENDER.GENDER_MALE, "男", "1", null, null, null, 1));
		retval.add(new Dictionary(ContextConstants.GROUP.GENDER, ContextConstants.GENDER.GENDER_FEMALE, "女", "2", null, null, null, 2));

		retval.add(new Dictionary(ContextConstants.GROUP.RESOURCE_TYPE, ContextConstants.RESOURCE_TYPE.TYPE_MENU, "菜单", "1", null, null, null, 1));
		retval.add(new Dictionary(ContextConstants.GROUP.RESOURCE_TYPE, ContextConstants.RESOURCE_TYPE.TYPE_URL, "超链接", "2", null, null, null, 2));
		retval.add(new Dictionary(ContextConstants.GROUP.RESOURCE_TYPE, ContextConstants.RESOURCE_TYPE.TYPE_BUTTON, "按钮", "3", null, null, null, 3));
		
		retval.add(new Dictionary(ContextConstants.GROUP.PAGE_SIZE, "10", "10", "10", null, null, null, 1));
		retval.add(new Dictionary(ContextConstants.GROUP.PAGE_SIZE, "20", "20", "20", null, null, null, 2));
		retval.add(new Dictionary(ContextConstants.GROUP.PAGE_SIZE, "30", "30", "30", null, null, null, 3));
		retval.add(new Dictionary(ContextConstants.GROUP.PAGE_SIZE, "50", "50", "50", null, null, null, 4));
		retval.add(new Dictionary(ContextConstants.GROUP.PAGE_SIZE, "100", "100", "100", null, null, null, 5));
		
		data.put(InitializationData.DICTIONARY, retval);
	}

}
