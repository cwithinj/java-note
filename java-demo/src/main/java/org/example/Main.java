package org.example;

import org.example.demo.SourceData;
import org.example.demo.TargetData;
import org.example.demo.TestData;
import org.example.demo.User;
import org.example.demo.inter.BeanMapper;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/20 下午 10:09
 */
public class Main {
    public static void main(String[] args) {
        SourceData source = new SourceData();
        source.setId("123");
        source.setName("abc");
        source.setCreateTime(System.currentTimeMillis());
        TestData testData = new TestData();
        testData.setId("123");


        TargetData target = BeanMapper.INSTANCE.map(source);
        System.out.println(target.getId() + ":" + target.getName() + ":" + target.getCreateTime());
        System.out.println(source.getData() == target.getData());
        User user = new User();
        System.out.println(user.getName());
    }
}