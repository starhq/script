<%include("/java_copyright.include"){}%>
package ${basepackage}.test.service;

import java.util.List;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.shinsoft.test.BaseTest;

import ${basepackage}.pojo.${table.className};
import ${basepackage}.service.${table.className}Service;

/**
 * ${table.className}的service层单元测试
 */
public class ${table.className}ServiceImplTest extends BaseTest{

	@Autowired
	private ${table.className}Service ${table.variableName}Service;
	
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
