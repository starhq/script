<%include("/java_copyright.include"){}%>
package ${basepackage}.service;

import java.util.List;
import com.github.pagehelper.Page;

import ${basepackage}.pojo.${table.className};

/**
<%include("/java_description.include"){}%>
 */
public interface ${table.className}Service{
		
		/** 
		 * 得到 ${table.className} 集合
		 **/  
		List<${table.className}> list() throws Exception;
		
		/** 
		 * 根据ID得到 ${table.className}
		 **/  
		${table.className} getById(String id) throws Exception;
		
		/** 
		 * 分页查询 ${table.className}
		 **/  
		Page<${table.className}> getPage(int currPage, int size) throws Exception;
		
		/** 
		 * 按id删除${table.className}
		 **/
		void removeById(String id) throws Exception;
		
		/** 
		 * 更新${table.className}
		 **/
		void update(${table.className} ${table.variableName}) throws Exception;
		
		/** 
		 * 保存${table.className}
		 **/
		void save(${table.className} ${table.variableName}) throws Exception;
		
		/** 
		 * 批量${table.className}
		 **/
		void saveBatch(List<${table.className}> ${table.variableName}s) throws Exception;
}
