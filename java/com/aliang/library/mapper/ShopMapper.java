package com.aliang.library.mapper;

import com.aliang.library.entity.Shop;
import com.aliang.library.entity.ShopExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ShopMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    long countByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int deleteByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int insert(Shop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int insertSelective(Shop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    List<Shop> selectByExample(ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    Shop selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") Shop record, @Param("example") ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") Shop record, @Param("example") ShopExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(Shop record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table l_shop
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(Shop record);
}