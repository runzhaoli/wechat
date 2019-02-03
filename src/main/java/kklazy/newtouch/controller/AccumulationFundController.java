/**
 * 
 */
package kklazy.newtouch.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.newtouch.model.AccumulationFund;
import kklazy.newtouch.service.AccumulationFundService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.callback.AssembleDownloadExcelCallBack;
import kklazy.persistence.callback.AssembleUploadExcelCallBack;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.persistence.utils.FileUtils;
import kklazy.persistence.utils.StringUtils;
import kklazy.persistence.utils.export.ExcelUtils;
import ognl.OgnlException;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.ACCUMULATION_FUND_NAMESPACE)
public class AccumulationFundController extends BasePageController<AccumulationFund, String> {

	@Autowired
	private AccumulationFundService accumulationFundService;

	@Override
	public PageService<AccumulationFund, String> pageservice() {
		return accumulationFundService;
	}

	@Override
	public String path() {
		return "/webpages/newtouch/accumulationfund";
	}

	/* (non-Javadoc)
	 * @see kklazy.common.controller.BasePageController#searchhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultPageQueryModel, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final AccumulationFund entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page<AccumulationFund> page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					if (StringUtils.isNotBlank(entity.getEmpno())) {
						criteria.add(Restrictions.eq("empno", entity.getEmpno()));
					}
					if (StringUtils.isNotBlank(entity.getIdentity())) {
						criteria.createAlias("staff", "staff").add(Restrictions.like("staff.identity", entity.getIdentity(), MatchMode.ANYWHERE));
					}

					if (entity instanceof DefaultModel) {
						criteria.add(Restrictions.eq("enabled", ((DefaultModel) entity).getEnabled()));
						criteria.add(Restrictions.eq("delete", ((DefaultModel) entity).getDelete()));
					}
					return criteria;
				}
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.ASC, "empno"))
				)
			);
			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultExcelController#exceltemplatehandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected AssembleDownloadExcelCallBack<AccumulationFund> exceltemplatehandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, AccumulationFund entity) {
		return new AssembleDownloadExcelCallBack<AccumulationFund>() {
			@Override
			public List<AccumulationFund> assembleData(List<AccumulationFund> data) {
				return data;
			}
			@Override
			public String assembleSuffix() {
				return "xls";
			}
			@Override
			public Map<String, String> assembleTitle(Map<String, String> title) {
				title.put("empno", "工号[必填项]");
				title.put("accumulationFundAccount", "公积金账号[必填项]");
				return title;
			}
		};
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultExcelController#exceluploadhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, org.springframework.web.multipart.MultipartFile, kklazy.persistence.callback.AssembleUploadExcelCallBack)
	 */
	@Override
	protected CommonResponse exceluploadhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, MultipartFile file, AssembleUploadExcelCallBack callback) {
		CommonResponse retval = CommonResponse.FAILURE();
		String filename = file.getOriginalFilename();
		String suffix = FileUtils.suffix(filename);
		try {
			if (StringUtils.isBlank(suffix) || !StringUtils.inCollection(suffix, "xls", "xlsx")) {
				throw new IOException("suffix[ " + suffix + " ] error!");
			}
			List<AccumulationFund> entities = ExcelUtils.readExcel(callback.assembleIndex(), file.getInputStream(), suffix, clazz, callback.assembleColumn(new LinkedHashMap<Integer, String>()), null);
			accumulationFundService.importAccumulationFunds(entities);
			retval = CommonResponse.SUCCESS();
			retval.setMessage("文件上传成功,页面即将重新加载...");
			Logger.getLogger(getClass()).info("accumulation fund account upload end.");
		} catch (IOException e) {
			e.printStackTrace();
			retval.setMessage("上传的文件格式不正确哟,确认是Excel吗？~");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			retval.setMessage("上传的文件不对啦,真的是Excel吗?不要随便改个后缀来冒充哟~");
		} catch (OgnlException e) {
			e.printStackTrace();
			retval.setMessage("上传的文件中存在错误的数据~错误信息:" + e);
		} catch (Exception e) {
			e.printStackTrace();
			retval.setMessage("上传文件出现了难得一见的系统错误~错误信息:" + e);
		}
		return retval;
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultExcelController#exceluploadhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap)
	 */
	@Override
	protected AssembleUploadExcelCallBack exceluploadhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Logger.getLogger(getClass()).info("accumulation fund account upload start.");
		return new AssembleUploadExcelCallBack() {
			@Override
			public Map<Integer, String> assembleColumn(Map<Integer, String> column) {
				column.put(0, "empno");
				column.put(1, "accumulationFundAccount");
				return column;
			}
		};
	}

}
