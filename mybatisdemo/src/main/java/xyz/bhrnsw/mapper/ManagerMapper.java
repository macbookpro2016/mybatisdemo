package xyz.bhrnsw.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import xyz.bhrnsw.beans.Manager;

public interface ManagerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbg.generated Sun Mar 18 16:05:33 CST 2018
     */
    int deleteByPrimaryKey(@Param("empNo") Integer empNo, @Param("deptNo") String deptNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbg.generated Sun Mar 18 16:05:33 CST 2018
     */
    int insert(Manager record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbg.generated Sun Mar 18 16:05:33 CST 2018
     */
    Manager selectByPrimaryKey(@Param("empNo") Integer empNo, @Param("deptNo") String deptNo);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbg.generated Sun Mar 18 16:05:33 CST 2018
     */
    List<Manager> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table dept_manager
     *
     * @mbg.generated Sun Mar 18 16:05:33 CST 2018
     */
    int updateByPrimaryKey(Manager record);
}