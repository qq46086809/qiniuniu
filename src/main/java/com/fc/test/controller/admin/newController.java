package com.fc.test.controller.admin;

import com.fc.test.common.base.BaseController;
import com.fc.test.common.domain.AjaxResult;
import com.fc.test.model.auto.News;
import com.fc.test.model.custom.TableSplitResult;
import com.fc.test.model.custom.Tablepar;
import com.fc.test.model.custom.TitleVo;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("newController")
@Api(value = "新闻动态")
public class newController extends BaseController{
	
	private String prefix = "admin/new";
	
	
	@GetMapping("view")
	@RequiresPermissions("system:new:view")
    public String view(Model model)
    {	
		
		setTitle(model, new TitleVo("新闻动态", "新闻动态", true,"欢迎进入新闻动态", true, false));
        return prefix + "/list";
    }
	
	@PostMapping("list")
	@RequiresPermissions("system:new:list")
	@ResponseBody
	public Object list(Tablepar tablepar,String username){
		PageInfo<News> page=sysNewService.list(tablepar,username) ;
		TableSplitResult<News> result=new TableSplitResult<News>(page.getPageNum(), page.getTotal(), page.getList());
		return  result;
	}

	/**
	 * 新增权限
	 */
	@GetMapping("add")
	@RequiresPermissions("system:new:add")
	public String add()
	{
		return prefix + "/add";
	}


	/**
	 * 权限添加
	 * @param news
	 * @return
	 */
	@PostMapping("add")
	@RequiresPermissions("system:new:add")
	@ResponseBody
	public AjaxResult add(News news){
		int b=sysNewService.insertSelective(news);
		if(b>0){
			return success();
		}else{
			return error();
		}
	}


	/**
	 * 删除权限
	 * @param ids
	 * @return
	 */
	@PostMapping("remove")
	@RequiresPermissions("system:new:remove")
	@ResponseBody
	public AjaxResult remove(String ids){
		int b=sysNewService.deleteByPrimaryKey(ids);
		if(b>0){
			return success();
		}else{
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
	public AjaxResult editSave(News news)
	{
		return toAjax(sysNewService.updateByPrimaryKey(news));
	}
	
}
