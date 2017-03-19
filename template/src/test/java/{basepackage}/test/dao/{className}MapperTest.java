<%include("/java_copyright.include"){}%>
package ${basepackage}.test.dao;

import java.util.List;

import ${basepackage}.test.BaseTest;
import ${basepackage}.dao.${table.className}Mapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.pojo.${table.className};


/**
 * ${table.className}Mapper的单元测试
 */
public class ${table.className}MapperTest extends BaseTest{

	@Autowired
	private ${table.className}Mapper ${table.variableName}Mapper;
	
	/**
	 * 测试保存${table.className}
	 */
	@Test
	public void testSave() {
		

	}

	/**
	 * 测试批量保存${table.className}
	 */
	@Test
	public void testSaveBatch() {
		

	}

	/**
	 * 测试按id删除
	 */
	@Test
	public void testRemoveById() {
	
	}
	
	/**
	 * 测试更新${table.className}
	 */
	@Test
	public void testUpdate() {
	
	}
	
	/**
	 * 测试获取${table.className}列表
	 */
	@Test
	public void testList() {
	
	}
	
	/**
	 * 测试按id获取${table.className}
	 */
	@Test
	public void testGetById() {
	
	}
}
