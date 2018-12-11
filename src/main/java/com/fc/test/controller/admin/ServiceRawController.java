package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.common.interceptor.BASE64DecodedMultipartFile;
import com.fc.test.model.auto.News;
import com.fc.test.model.auto.Service;
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
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("ServiceRawController")
@Api(value = "服务管理")
public class ServiceRawController extends BaseController {

	private String prefix = "admin/service";
	static String accessKey = "wvg0XHiVI2-bubOYj0cga4RQ4l9ish_6eH22GPGB";
	static String secretKey = "_7c0Ije_5u2vObZ02SzsAqS17wCJm74GCGHdwsoY";
	static String bucket = "qiniuniu";
	static String qny = "http://pj8reikgn.bkt.clouddn.com";

	@GetMapping("view")
	@RequiresPermissions("system:service:view")
	public String view(Model model) {

		setTitle(model, new TitleVo("服务管理", "服务管理", true, "欢迎进入服务管理", true, false));
		return prefix + "/list";
	}

	@PostMapping("list")
	@RequiresPermissions("system:service:list")
	@ResponseBody
	public Object list(Tablepar tablepar, String title) {
		PageInfo<Service> page = sysServiceService.list(tablepar, title);
		TableSplitResult<Service> result = new TableSplitResult<Service>(page.getPageNum(), page.getTotal(), page.getList());
		return result;
	}

	/**
	 * 新增服务
	 */
	@GetMapping("add")
	@RequiresPermissions("system:service:add")
	public String add() {
		return prefix + "/add";
	}


	/**
	 * 服务添加
	 *
	 * @param service
	 * @return
	 * AjaxResult
	 */
	@PostMapping("add")
	@RequiresPermissions("system:service:add")
	@ResponseBody
	public AjaxResult add(Service service) throws IOException {
		System.out.println(service.getTitle());
		if(service.getContent()!=""){
			String contentsrc="";
			System.out.println(service.getContent());
			JSONObject content = JSONObject.fromObject(service.getContent().substring(1,service.getContent().length()-1));
			System.out.println(content);
			Iterator it = content.keys();
			while(it.hasNext()){
				String hashkey = it.next().toString();
				String url = content.getString(hashkey);
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
				String imgFilePath = "E:\\fuwu\\"+uuid+".jpg";//新生成的图片
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
					contentsrc+="<img src='"+qny+"/"+putRet.key+"'>";
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
			service.setContent(contentsrc);
		}
		if(service.getCover()!=""){
			String conversrc="";
			JSONObject cover = JSONObject.fromObject(service.getCover().substring(1,service.getCover().length()-1));
			System.out.println(cover);
			String url = cover.getString("mm");
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
			String imgFilePath = "E:\\fwfm\\"+uuid+".jpg";//新生成的图片
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
				 conversrc="<img src='"+qny+"/"+putRet.key+"'>";
				service.setCover(conversrc);
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
		int b =sysServiceService.insertSelective(service);
		if (b > 0) {
			return success();
		} else {
			return error();
		}


	}


	/**
	 * 删除服务
	 *
	 * @param ids
	 * @return
	 */
	@PostMapping("remove")
	@RequiresPermissions("system:service:remove")
	@ResponseBody
	public AjaxResult remove(String ids) {
		String cover = sysServiceService.ServicecoverByPrimaryKey(ids);
		String content = sysServiceService.ServiceContentByPrimaryKey(ids);
		Set<String> pics = new HashSet<>();
		String img = "";
		Pattern p_image;
		Matcher m_image;
		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
		p_image = Pattern.compile
				(regEx_img, Pattern.CASE_INSENSITIVE);
		m_image = p_image.matcher(cover);
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
			imageurl=imageurl.replace("'", "");
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
		 pics = new HashSet<>();
		m_image = p_image.matcher(content);
		while (m_image.find()) {
			// 得到<img />数据
			img = m_image.group();
			// 匹配<img>中的src数据
			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while (m.find()) {
				pics.add(m.group(1));
			}
		}





		 iterator = pics.iterator();
		while (iterator.hasNext()) {
			String url = iterator.next();
			String imageurl = url.replace(qny + "/", "");
			imageurl=imageurl.replace("'", "");
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
		int b = sysServiceService.deleteByPrimaryKey(ids);
		if (b > 0) {
			return success();
		} else {
			return error();
		}

	}






	/**
	 * 修改服务
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
	 * 修改保存服务
	 * AjaxResult
	 */
	@RequiresPermissions("system:service:edit")
	@PostMapping("/edit")
	@ResponseBody
	public void editSave(News news)throws IOException
	{
//		String htmlStr = news.getDescription();
//		Set<String> pics = new HashSet<>();
//		String img = "";
//		Pattern p_image;
//		Matcher m_image;
//		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
//		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
//		p_image = Pattern.compile
//				(regEx_img, Pattern.CASE_INSENSITIVE);
//		m_image = p_image.matcher(htmlStr);
//		while (m_image.find()) {
//			// 得到<img />数据
//			img = m_image.group();
//			// 匹配<img>中的src数据
//			Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
//			while (m.find()) {
//				pics.add(m.group(1));
//			}
//		}
//
//		Iterator<String> iterator=pics.iterator();
//		while(iterator.hasNext()){
//
//			String url = iterator.next();
//			if(url.substring(0,1).equals("d")){
//				String imageurl =url.replace("data:image/png;base64,", "");
//				BASE64Decoder decoder = new BASE64Decoder();
//				byte[] b = new byte[0];
//				try {
//					b = decoder.decodeBuffer(imageurl);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				for(int i=0;i<b.length;++i)
//				{
//					if(b[i]<0)
//					{//调整异常数据
//						b[i]+=256;
//					}
//				}
//				//生成jpeg图片
//				UUID uuid = UUID.randomUUID();
//				String imgFilePath = "E:\\tupian\\"+uuid+".jpg";//新生成的图片
//				try {
//					OutputStream out = new FileOutputStream(imgFilePath);
//					out.write(b);
//					out.flush();
//					out.close();
//				} catch (FileNotFoundException e) {
//					e.printStackTrace();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//				MultipartFile multipartFile = new BASE64DecodedMultipartFile(b, imageurl);
//				ByteArrayInputStream fileInputStream = (ByteArrayInputStream) multipartFile.getInputStream();
//				Configuration cfg = new Configuration(Zone.zone2());
//				UploadManager uploadManager = new UploadManager(cfg);
//				String key = String.valueOf(uuid);
//				Auth auth = Auth.create(accessKey, secretKey);
//				String upToken = auth.uploadToken(bucket);
//				try {
//					Response response = uploadManager.put(fileInputStream,key,upToken,null, null);
//					//解析上传成功的结果
//					DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
//					System.out.println(putRet.key);
//					System.out.println(url);
//					String var = htmlStr.replace(url,qny+"/"+putRet.key);
//					htmlStr=var;
//					System.out.println(putRet.hash);
//				} catch (QiniuException ex) {
//					Response r = ex.response;
//					System.err.println(r.toString());
//					try {
//						System.err.println(r.bodyString());
//					} catch (QiniuException ex2) {
//						//ignore
//					}
//				}
//			}
//
//		}
//		news.setDescription(htmlStr);
//
//		return toAjax(sysNewService.updateByPrimaryKey(news));
	}
	
}
