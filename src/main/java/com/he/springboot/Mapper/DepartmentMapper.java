package com.he.springboot.Mapper;

import com.he.springboot.Bean.Department;
import org.apache.ibatis.annotations.*;

//指定这个是一个数据库的mapper
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(int id);

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(int id);

//    是否使用自动生成的主键，并将属性和主键绑定起来
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("updata department set departmentName=#{departmentName} where id=#{id}")
    public int updataDept(Department department);

}
