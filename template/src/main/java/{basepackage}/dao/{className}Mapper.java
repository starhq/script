<%include("/java_copyright.include"){}%>
package ${basepackage}.dao;


import org.springframework.stereotype.Repository;

import com.shinsoft.pojo.${table.className};

/**
<%include("/java_description.include"){}%>
 */
@Repository("${table.variableName}Mapper")
public interface ${table.className}Mapper extends BaseMapper<${table.className}>{

	
}
