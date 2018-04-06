<%include("/java_copyright.include"){}%>
package ${basepackage}.dao;


import org.springframework.stereotype.Repository;

import ${basepackage}.pojo.${table.className};

/**
 * ${table.className}dao层
 *
<%include("/java_description.include"){}%>
 */
@Repository("${table.variableName}Mapper")
public interface ${table.className}Mapper extends BaseMapper<${table.className}>{

	
}
