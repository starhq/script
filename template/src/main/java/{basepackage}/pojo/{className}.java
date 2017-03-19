<%include("/java_copyright.include"){}%>
package ${basepackage}.pojo;


import lombok.Data;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

/**
<%include("/java_description.include"){}%>
 */
@Data
@Alias("${table.variableName}")
public class ${table.className} implements java.io.Serializable{

	<%
	for(column in table.columns){
	%>
	/**
     * comment：${column.columnAlias!}       db_column: ${column.sqlName} 
     */	
	${column.hibernateValidatorExprssion}
	private ${column.javaType} ${column.columnNameLower};
	
	<%
	}
	%>
	
}
