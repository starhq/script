package ${basepackage}.service.impl;


import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import ${basepackage}.service.${table.className}Service;
import org.springframework.beans.factory.annotation.Autowired;
import ${basepackage}.dao.${table.className}Mapper;
import org.springframework.stereotype.Service;
import ${basepackage}.${table.className};
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service("${table.variableName}Service")
public class ${table.className}ServiceImpl implements ${table.className}Service{

	@Autowired
	private ${table.className}Mapper ${table.variableName}Mapper;
	
	@Override
    public void saveBatch(List<${table.className}> ${table.variableName}s) throws Exception {
        ${table.variableName}Mapper.saveBatch(${table.variableName}s);
    }
	
	@Override
    public void save(${table.className} ${table.variableName}) throws Exception {
        ${table.variableName}Mapper.save(${table.variableName});
    }
	
	@Override
    public void update(${table.className} ${table.variableName}) throws Exception {
        ${table.variableName}Mapper.update(${table.variableName});
    }
	
	@Override
    public void removeById(String id) throws Exception {
        ${table.variableName}Mapper.removeById(id);
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public List<${table.className}> list() throws Exception {
        return ${table.variableName}Mapper.list();
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public ${table.className} getById(String id) throws Exception {
        return ${table.variableName}Mapper.getById(id);
    }
	
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @Override
    public Page<${table.className}> getPage(int currPage, int size) throws Exception {
        PageHelper.startPage(currPage, size);
        return (Page<${table.className}>) ${table.variableName}Mapper.list();
    }
	
}
