<%include("/java_copyright.include"){}%>
package ${basepackage}.test.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.test.BaseTest;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ${basepackage}.pojo.${table.className};
import ${basepackage}.controller.${table.className}Controller;


/**
 * ${table.className}的controller层单元测试
 */
public class ${table.className}ControllerTest extends BaseTest{

	@Autowired
	private ${table.className}Controller ${table.variableName}Controller;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(${table.variableName}Controller).setMessageConverters(new
                FastJsonHttpMessageConverter()).build();
	}


	@After
	public void tearDown() throws Exception {
		mockMvc = null;
	}
	
	@Test
	public void testInsert() {
	//	mockMvc.perform(MockMvcRequestBuilders.post("/registerInfos").contentType(MediaType.APPLICATION_JSON).content
    //            (JSON.toJSONString(info)))
    //            .andExpect
    //                    (MockMvcResultMatchers
    //                            .status()
    //                            .isOk())
    //            .andDo(MockMvcResultHandlers.print());
	}


	@Test
	public void testGet() throws Exception{
	//	mockMvc.perform(
	//			MockMvcRequestBuilders.get("/user").param("status", "0")
	//					.param("currPage", "1"))
	//			.andExpect(MockMvcResultMatchers.status().isOk())
	//			.andDo(MockMvcResultHandlers.print());
	}

}
