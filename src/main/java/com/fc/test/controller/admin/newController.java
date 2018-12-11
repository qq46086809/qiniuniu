package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.auto.News;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import com.fc.test.common.interceptor.BASE64DecodedMultipartFile;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("newController")
@Api(value = "新闻动态")
public class newController extends BaseController {

	private String prefix = "admin/new";
	static String accessKey = "wvg0XHiVI2-bubOYj0cga4RQ4l9ish_6eH22GPGB";
	static String secretKey = "_7c0Ije_5u2vObZ02SzsAqS17wCJm74GCGHdwsoY";
	static String bucket = "qiniuniu";
	static String qny = "http://pj8reikgn.bkt.clouddn.com";

	@GetMapping("view")
	@RequiresPermissions("system:new:view")
	public String view(Model model) {

		setTitle(model, new TitleVo("新闻动态", "新闻动态", true, "欢迎进入新闻动态", true, false));
		return prefix + "/list";
	}

	@PostMapping("list")
	@RequiresPermissions("system:new:list")
	@ResponseBody
	public Object list(Tablepar tablepar, String username) {
		PageInfo<News> page = sysNewService.list(tablepar, username);
		TableSplitResult<News> result = new TableSplitResult<News>(page.getPageNum(), page.getTotal(), page.getList());
		return result;
	}

	/**
	 * 新增权限
	 */
	@GetMapping("add")
	@RequiresPermissions("system:new:add")
	public String add() {
		return prefix + "/add";
	}


	/**
	 * 权限添加
	 *
	 * @param news
	 * @return
	 */
	@PostMapping("add")
	@RequiresPermissions("system:new:add")
	@ResponseBody
	public AjaxResult add(News news) throws IOException {
		String cover = news.getCover();

		String coverurl = cover.replace("data:image/png;base64,", "");

		BASE64Decoder decoder = new BASE64Decoder();
		byte[] b = new byte[0];
		try {
			b = decoder.decodeBuffer(coverurl);
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < b.length; ++i) {
			if (b[i] < 0) {//调整异常数据
				b[i] += 256;
			}
		}
		//生成jpeg图片
		UUID uuid = UUID.randomUUID();
		String imgFilePath = "E:\\tupian\\" + uuid + ".jpg";//新生成的图片
		try {
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MultipartFile multipartFile = new BASE64DecodedMultipartFile(b, coverurl);
		ByteArrayInputStream fileInputStream = (ByteArrayInputStream) multipartFile.getInputStream();
		Configuration cfg = new Configuration(Zone.zone2());
		UploadManager uploadManager = new UploadManager(cfg);
		String key = String.valueOf(uuid);
		Auth auth = Auth.create(accessKey, secretKey);
		String upToken = auth.uploadToken(bucket);
		try {
			Response response = uploadManager.put(fileInputStream, key, upToken, null, null);
			//解析上传成功的结果
			DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
			System.out.println(putRet.key);
			System.out.println(cover);
			String var = cover.replace(cover, qny + "/" + putRet.key);
			cover = var;
			System.out.println(putRet.hash);
		} catch (QiniuException ex) {
			Response r = ex.response;
			System.err.println(r.toString());
			try {
				System.err.println(r.bodyString());
			} catch (QiniuException ex2) {
				//ignore
			}
		}


		String htmlStr = news.getDescription();
		Set<String> pics = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile
				(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}

		Iterator<String> iterator = pics.iterator();
		while (iterator.hasNext()) {
			String url = iterator.next();
			String imageurl = url.replace("data:image/png;base64,", "");
			 decoder = new BASE64Decoder();
			 b = new byte[0];
			try {
				b = decoder.decodeBuffer(imageurl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片
			 uuid = UUID.randomUUID();
			 imgFilePath = "E:\\tupian\\" + uuid + ".jpg";//新生成的图片
			try {
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 multipartFile = new BASE64DecodedMultipartFile(b, imageurl);
			 fileInputStream = (ByteArrayInputStream) multipartFile.getInputStream();
			 cfg = new Configuration(Zone.zone2());
			 uploadManager = new UploadManager(cfg);
			 key = String.valueOf(uuid);
			 auth = Auth.create(accessKey, secretKey);
			 upToken = auth.uploadToken(bucket);
			try {
				Response response = uploadManager.put(fileInputStream, key, upToken, null, null);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);

				String var = htmlStr.replace(url, qny + "/" + putRet.key);
				htmlStr = var;

			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}
		}
		news.setDescription(htmlStr);
		news.setCover(cover);
		int z = sysNewService.insertSelective(news);
		if (z > 0) {
			return success();
		} else {
			return error();
		}
	}


	/**
	 * 删除权限
	 *
	 * @param ids
	 * @return
	 */
	@PostMapping("remove")
	@RequiresPermissions("system:new:remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		String desc = sysNewService.NewsDescByPrimaryKey(ids);
		Set<String> pics = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile
				(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(desc);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}

		Iterator<String> iterator = pics.iterator();
		while (iterator.hasNext()) {
			String url = iterator.next();
			String imageurl = url.replace(qny + "/", "");
			System.out.println(imageurl);
			Configuration cfg = new Configuration(Zone.zone0());
			Auth auth = Auth.create(accessKey, secretKey);
			BucketManager bucketManager = new BucketManager(auth, cfg);
			try {
				bucketManager.delete(bucket, imageurl);
			} catch (QiniuException ex) {
				//如果遇到异常，说明删除失败
				System.err.println(ex.code());
				System.err.println(ex.response.toString());
			}
		}
		int b = sysNewService.deleteByPrimaryKey(ids);
		if (b > 0) {
			return success();
		} else {
			return error();
		}

	}






	/**
	 * 修改权限
	 * @param id
	 * @param mmap
	 * @return
	 */
	@GetMapping("/edit/{roleId}")
	public String edit(@PathVariable("roleId") String id, ModelMap mmap)
	{
		mmap.put("news", sysNewService.selectByPrimaryKey(id));

		return prefix + "/edit";
	}

	/**
	 * 修改保存权限
	 */
	@RequiresPermissions("system:new:edit")
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(News news)throws IOException
	{
		String cover = news.getCover();
		if(cover.substring(0,1).equals("d")) {
			String coverurl = cover.replace("data:image/png;base64,", "");
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] b = new byte[0];
			try {
				b = decoder.decodeBuffer(coverurl);
			} catch (IOException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {//调整异常数据
					b[i] += 256;
				}
			}
			//生成jpeg图片
			UUID uuid = UUID.randomUUID();
			String imgFilePath = "E:\\tupian\\" + uuid + ".jpg";//新生成的图片
			try {
				OutputStream out = new FileOutputStream(imgFilePath);
				out.write(b);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			MultipartFile multipartFile = new BASE64DecodedMultipartFile(b, coverurl);
			ByteArrayInputStream fileInputStream = (ByteArrayInputStream) multipartFile.getInputStream();
			Configuration cfg = new Configuration(Zone.zone2());
			UploadManager uploadManager = new UploadManager(cfg);
			String key = String.valueOf(uuid);
			Auth auth = Auth.create(accessKey, secretKey);
			String upToken = auth.uploadToken(bucket);
			try {
				Response response = uploadManager.put(fileInputStream, key, upToken, null, null);
				//解析上传成功的结果
				DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
				System.out.println(putRet.key);
				System.out.println(cover);
				String var = cover.replace(cover, qny + "/" + putRet.key);
				cover = var;
				System.out.println(putRet.hash);
			} catch (QiniuException ex) {
				Response r = ex.response;
				System.err.println(r.toString());
				try {
					System.err.println(r.bodyString());
				} catch (QiniuException ex2) {
					//ignore
				}
			}

		}

		String htmlStr = news.getDescription();
		Set<String> pics = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile
				(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}

		Iterator<String> iterator=pics.iterator();
		while(iterator.hasNext()){
			String url = iterator.next();
			if(url.substring(0,1).equals("d")){
				String imageurl =url.replace("data:image/png;base64,", "");
				BASE64Decoder decoder = new BASE64Decoder();
				byte[] b = new byte[0];
				try {
					b = decoder.decodeBuffer(imageurl);
				} catch (IOException e) {
					e.printStackTrace();
				}
				for(int i=0;i<b.length;++i)
				{
					if(b[i]<0)
					{//调整异常数据
						b[i]+=256;
					}
				}
				//生成jpeg图片
				UUID uuid = UUID.randomUUID();
				String imgFilePath = "E:\\tupian\\"+uuid+".jpg";//新生成的图片
				try {
					OutputStream out = new FileOutputStream(imgFilePath);
					out.write(b);
					out.flush();
					out.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				MultipartFile multipartFile = new BASE64DecodedMultipartFile(b, imageurl);
				ByteArrayInputStream fileInputStream = (ByteArrayInputStream) multipartFile.getInputStream();
				Configuration cfg = new Configuration(Zone.zone2());
				UploadManager uploadManager = new UploadManager(cfg);
				String key = String.valueOf(uuid);
				Auth auth = Auth.create(accessKey, secretKey);
				String upToken = auth.uploadToken(bucket);
				try {
					Response response = uploadManager.put(fileInputStream,key,upToken,null, null);
					//解析上传成功的结果
					DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
					System.out.println(putRet.key);
					System.out.println(url);
					String var = htmlStr.replace(url,qny+"/"+putRet.key);
					htmlStr=var;
					System.out.println(putRet.hash);
				} catch (QiniuException ex) {
					Response r = ex.response;
					System.err.println(r.toString());
					try {
						System.err.println(r.bodyString());
					} catch (QiniuException ex2) {
						//ignore
					}
				}
			}

		}
		news.setDescription(htmlStr);
		news.setCover(cover);
		return toAjax(sysNewService.updateByPrimaryKey(news));
	}
	
}
