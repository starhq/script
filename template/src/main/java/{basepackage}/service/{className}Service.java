<%include("/java_copyright.include"){}%>
package ${basepackage}.service;

import java.util.List;
import com.github.pagehelper.Page;

import ${basepackage}.pojo.${table.className};

/**
 * ${table.className}service层
 *
<%include("/java_description.include"){}%>
 */
public interface ${table.className}Service{
		
		/** 
		 * 得到 ${table.className} 集合
		 * @return ${table.className}集合
		 **/  
		List<${table.className}> list();
		
		/**
		 * 根据ID得到 ${table.className}
		 *
		 * @param id 主键
		 * @return ${table.className}对象
		 */
		${table.className} get(String id);
		
		/**
		 * 分页查询 ${table.className}
		 *
		 * @param currPage 起始页
		 * @param size     大小
		 * @return 带分页信息的${table.className}集合
		 */
		Page<${table.className}> get(int currPage, int size);
		
		 /**
		 * 按id删除${table.className}
		 *
		 * @param id 主键
		 */
		void remove(String id);
		
		/**
		 * 更新${table.className}
		 *
		 * @param ${table.variableName} 对象
		 */
		void update(${table.className} ${table.variableName});
		
		/** 
		 * 保存${table.className}
		 *
		 * @param ${table.variableName} 对象
		 **/
		void save(${table.className} ${table.variableName});
		
		/** 
		 * 批量${table.className}
		 *
		 * @param ${table.variableName}s 对象
		 **/
		void saveBatch(List<${table.className}> ${table.variableName}s);
}
