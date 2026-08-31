package com.lhy.mapper;

import com.lhy.pojo.Emp;
import com.lhy.pojo.EmpQuaryParam;
import com.lhy.pojo.PageResult;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
//原始方式实现
    /**
     * 获取总记录数
     * @return
     */
    @Select("Select count(*) from emp e left join dept d on e.dept_id=d.id")
    public long count();

    /**
     * 分页查询
     * @return
     */
     //@Select("Select e.*,d.name deptName from emp e left join dept d on e.dept_id = d.id limit #{start},#{pagesize}")
     //public List<Emp> list(Integer start,Integer pagesize);



    //使用PageHelper实现
    public List<Emp> list(EmpQuaryParam empQuaryParam);
    //添加员工
    /**
     * 新增员工数据
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")//获取自增主键值
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void Insert(Emp emp);


    void deleteByIds(List<Integer> ids);

    Emp get(Integer id);

    void update(Emp emp);

    List<Map<String,Object>> countEmpjobdata();

    List<Map<String,Object>> countEmpgenderdata();

    @Select("Select id,username,password,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time from emp")
    List<Emp> getEmp();

    @Select("Select id,username,name from emp where username=#{username} and password=#{password}")
    Emp login(Emp emp);

    List<Map<String, Object>> countStudentdegreedata();

    List<Map<String, Object>> countClassstudentdata();
}
