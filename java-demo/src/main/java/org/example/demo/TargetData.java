package org.example.demo;

import lombok.Data;

/**
 * 类描述
 *
 * @author cjia
 * @date 2023/9/20 下午 10:14
 */
@Data
public class TargetData {
    private String id;
    private String name;
    private TestData data;
    private int createTime;


    //public String getId() {
    //    return id;
    //}
    //
    //public void setId(String id) {
    //    this.id = id;
    //}
    //
    //public String getName() {
    //    return name;
    //}
    //
    //public void setName(String name) {
    //    this.name = name;
    //}
    //
    //public TestData getData() {
    //    return data;
    //}
    //
    //public void setData(TestData data) {
    //    this.data = data;
    //}
    //
    //public int getCreateTime() {
    //    return createTime;
    //}
    //
    //public void setCreateTime(int createTime) {
    //    this.createTime = createTime;
    //}
}
