package org.example.service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.entity.MoData;
import org.example.domain.entity.QMoData;
import org.example.domain.vo.MoDataVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 单表操作
 *
 * @author cjia
 * @date 2023/9/3 下午 03:15
 */
@SpringBootTest
@Slf4j
class MoDataRepositoryTest01 {
    @Autowired
    JPAQueryFactory jpaQueryFactory;

    @Autowired
    MoDataRepository moDataRepository;

    /**
     * MoData实体类对应的Q类
     */
    private static final QMoData qMoData = QMoData.moData;

    @BeforeEach
    void setUp() {
    }

    @Test
    @Transactional
    @Rollback(false)
    void test01() {
        //单表的新增操作
        //1、准备数据
        MoData moData = new MoData();
        moData.setLineBody("line1");
        moData.setBoxNum(13L);
        moData.setCreateTime(new Timestamp(System.currentTimeMillis()));
        moData.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        //2、执行新增操作
        MoData data = moDataRepository.save(moData);
        log.info("新增后的数据:{}", data);

    }

    @Test
    @Transactional
    @Rollback(false)
    void test02() {
        //单表的删除操作
        //以id为删除判断条件，或者其他条件
        long affectedRows = jpaQueryFactory.delete(qMoData)
                .where(qMoData.id.eq(101L))
                .execute();
        log.info("受影响的行数：{}", affectedRows);
        //jpaQueryFactory.delete(qMoData).where(qMoData.lineBody.eq("line1"));

    }

    @Test
    @Transactional
    @Rollback(false)
    void test03() {
        //单表的删除修改操作
        //以id为修改判断条件，或者其他条件
        long affectedRows = jpaQueryFactory.update(qMoData)
                .set(qMoData.boxNum, 20L)
                .set(qMoData.updateTime, new Timestamp(System.currentTimeMillis()))
                .where(qMoData.id.eq(100L))
                .execute();
        log.info("受影响的行数：{}", affectedRows);

    }

    @Test
        //@Transactional
        //@Rollback(false)
    void test04() {
        //单表的查询操作
        //查询单表的全部字段, 多条记录
        List<MoData> moDataList = jpaQueryFactory.select(qMoData)
                .from(qMoData)
                .where(qMoData.lineBody.eq("line1")
                        .and(qMoData.id.goe(50)))
                .fetch();
        //或者
        //jpaQueryFactory.selectFrom(qMoData)
        //        .where(qMoData.lineBody.eq("line2").and(qMoData.id.goe(50)))
        //        .fetch();
        moDataList.forEach(System.out::println);

        //查询唯一的记录，如果不止一条，会报错
        MoData moData1 = jpaQueryFactory.select(qMoData)
                .from(qMoData)
                .where(qMoData.lineBody.eq("line1")
                        .and(qMoData.id.eq(50L)))
                .fetchOne();
        //查询第一条记录
        MoData moData2 = jpaQueryFactory.select(qMoData)
                .from(qMoData)
                .where(qMoData.lineBody.eq("line1")
                        .and(qMoData.id.eq(50L)))
                .fetchFirst();

    }

    @Test
        //@Transactional
        //@Rollback(false)
    void test05() {
        //单表的查询操作
        //自定义结果映射集，以及需要查询的字段
        List<MoDataVO> moDataVOS = jpaQueryFactory.select(
                        //可以是其他的对象，后面的字符串路径名称对应 该对象的属性名称
                        Projections.bean(MoDataVO.class,
                                //需要返回的字段,可以起别名
                                qMoData.id,
                                qMoData.lineBody,
                                qMoData.createTime.as("createTime")))
                .from(qMoData)
                .where(qMoData.lineBody.eq("line1")
                        .and(qMoData.id.goe(50)))
                .fetch();

        moDataVOS.forEach(System.out::println);

    }

    @Test
    void test06() {
        //动态拼接条件查询
        BooleanBuilder whereBuilder = new BooleanBuilder();
        //定义一个条件
        String lineBody = "line1";
        if (lineBody != null) {
            whereBuilder.and(qMoData.lineBody.eq(lineBody));
        }
        //还可以拼接其他条件
        //执行查询
        List<MoData> moDataList = jpaQueryFactory.selectFrom(qMoData)
                .where(qMoData.id.goe(50L)
                        .and(whereBuilder))
                .fetch();
        moDataList.forEach(System.out::println);
    }
}