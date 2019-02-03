package kklazy.security.controller;

import java.awt.image.BufferedImage;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Producer;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.utils.StringUtils;

/**
 * 生成验证码
 * 
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.KAPTCHA_NAMESPACE)
public class KaptchaController extends BasePageController<DefaultModel, String> {
	
	public static final String VERIFY = "verify";

	@Resource(name = "kaptcha")
	private Producer kaptcha;

	@Override
	public PageService<DefaultModel, String> pageservice() { return null; }
	
	@Override
	public String path() { return StringUtils.EMPTY; }

	/**
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = KaptchaController.VERIFY)
	public void verify(HttpServletRequest request, HttpServletResponse response) throws Exception {

		response.setDateHeader("Expires", 0);

		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");

		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");

		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");

		// return a jpeg
		response.setContentType("image/jpeg");

		// create the text for the image
		String text = kaptcha.createText();

		// store the text in the session
		request.getSession().setAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY, text);

		// create the image with the text
		BufferedImage bi = kaptcha.createImage(text);
		ServletOutputStream out = response.getOutputStream();

		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}

	}
}
