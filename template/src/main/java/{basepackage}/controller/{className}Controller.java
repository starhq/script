<%include("/java_copyright.include"){}%>
package ${basepackage}.controller;


import java.util.List;

import com.github.pagehelper.Page;
import ${basepackage}.common.BussinessException;
import ${basepackage}.common.RestResult;
import ${basepackage}.pojo.${table.className};
import ${basepackage}.service.${table.className}Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 *${table.className}controller层
 *
<%include("/java_description.include"){}%>
 */
@Slf4j
@Controller
@RequestMapping(value = "/${table.variableName}s")
public class ${table.className}Controller{

	@Autowired
	private ${table.className}Service ${table.variableName}Service;
	
	@RequestMapping(method = RequestMethod.GET)
    public
    @ResponseBody
    RestResult list() {
        if (log.isInfoEnabled()) {
            log.info("查询${table.variableName}集合");
        }
        List<${table.className}> ${table.variableName}s;
        try {
            ${table.variableName}s = ${table.variableName}Service.list();
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("查询${table.variableName}集合失败: {}", e);
            }
            throw new BussinessException("查询${table.variableName}集合", e);
        }
        if (log.isInfoEnabled()) {
            log.info("查询${table.variableName}集合成功");
        }
        return new RestResult(200, ${table.variableName}s);
    }
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public
    @ResponseBody
    RestResult getById(@PathVariable String id) {
        if (log.isInfoEnabled()) {
            log.info("查询${table.variableName}: {} 信息", id);
        }
        ${table.className} ${table.variableName};
        try {
            ${table.variableName} = ${table.variableName}Service.get(id);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("查询${table.variableName}集合失败: {}", e);
            }
            throw new BussinessException("查询${table.variableName}信息", e);
        }
        if (log.isInfoEnabled()) {
            log.info("查询${table.variableName}信息成功");
        }
        return new RestResult(200, ${table.variableName});
    }
	
	@RequestMapping(method = RequestMethod.GET, params = {"page", "size"})
    public
    @ResponseBody
    RestResult getPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        if (log.isInfoEnabled()) {
            log.info("分页查询${table.variableName}，当前页: {},每页: {}", page, size);
        }
        Page<${table.className}> ${table.variableName}s;
        try {
            ${table.variableName}s = ${table.variableName}Service.get(page, size);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("分页查询${table.variableName}集合失败: {}", e);
            }
            throw new BussinessException("分页查询${table.variableName}信息失败", e);
        }
		if (log.isInfoEnabled()) {
            log.info("分页查询${table.variableName}信息成功");
        }
        return new RestResult(200, ${table.variableName}s.getResult(), ${table.variableName}s.getTotal());
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public
    @ResponseBody
    RestResult removeById(@PathVariable String id) {
        if (log.isInfoEnabled()) {
            log.info("删除${table.variableName} {} 的信息", id);
        }
        try {
            ${table.variableName}Service.remove(id);
            if (log.isInfoEnabled()) {
                log.info("删除${table.variableName}成功", id);
            }
			return new RestResult(200, "");
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("删除${table.variableName}失败: {}", e);
            }
            throw new BussinessException("删除${table.variableName}失败", e);
        }
    }
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public
    @ResponseBody
    RestResult update(@PathVariable String id, @RequestBody ${table.className} ${table.variableName}) {
        if (log.isInfoEnabled()) {
            log.info("更新${table.variableName} {} 的信息: {}", id, ${table.variableName});
        }
        try {
            ${table.variableName}Service.update(${table.variableName});
            if (log.isInfoEnabled()) {
                log.info("更新${table.variableName}成功");
            }
            return new RestResult(200, "");
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("更新${table.variableName}失败: {}", e);
            }
            throw new BussinessException("更新${table.variableName}失败", e);
        }
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public
    @ResponseBody
    RestResult save(@RequestBody ${table.className} ${table.variableName}) {
        if (log.isInfoEnabled()) {
            log.info("保存apply的信息: {}", ${table.variableName});
        }
        try {
            ${table.variableName}Service.save(${table.variableName});
            if (log.isInfoEnabled()) {
                log.info("保存${table.variableName}成功");
            }
            return new RestResult(200, "");
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error("保存${table.variableName}失败: {}", e);
            }
            throw new BussinessException("保存${table.variableName}失败", e);
        }
    }
	

	
}
