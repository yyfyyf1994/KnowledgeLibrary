package com.lib.web.user.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.lib.enums.Const;

/**
 * 无需登录的Controller
 * 
 * @author Yu Yufeng
 *
 */
@Controller
public class CommonController {
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	/**
	 * 根据文件地址下载文件
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	// ueditor/jsp/upload/image/20160731/1469934781425082135.jpg
	@RequestMapping(value = "/ueditor/jsp/upload/{type}/{day}/{name}", method = RequestMethod.GET)
	public String download(HttpServletRequest request, HttpServletResponse response) {
		String path = request.getRequestURI();
		path = path.substring(5);
		path = Const.ROOT_PATH + path;
		try {
			InputStream inputStream = new FileInputStream(path);
			OutputStream os = response.getOutputStream();
			byte[] b = new byte[2048];
			int length;
			while ((length = inputStream.read(b)) > 0) {
				os.write(b, 0, length);
			}
			// 这里主要关闭。
			os.close();
			inputStream.close();
		} catch (FileNotFoundException e) {
			LOG.error("文件没有找到"+path);
		} catch (IOException e) {
		}
		return null;
	}
}
