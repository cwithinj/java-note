package org.example.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.service.MyWebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 类描述
 *
 * @author cjia
 * @date 2024/1/21 上午 11:34
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private MyWebSocketHandler webSocketHandler;

    /**
     * 每个session 50条消息
     */
    final int i1 = 50;

    /**
     * 先用apiPost7建立几个连接，我建立的3个，对应的id分别是1,2,3
     * 这样的话，count传值3，就可以模拟给这3个session发消息
     *
     * @param count
     * @return
     */
    @GetMapping("/test-01")
    public Object test(@RequestParam Integer count)  {
        for (int j = 0; j < i1; j++) {
            for (int i = 1; i <= count; i++) {
                int finalI = i;
                new Thread(()->{
                    try {
                        //模拟随机睡眠时间
                        //if (finalI == 1) {
                        //    Thread.sleep(10);
                        //}
                        //if (finalI == 2) {
                        //    Thread.sleep(30);
                        //}
                        //if (finalI == 3) {
                        //    Thread.sleep(40);
                        //}
                        webSocketHandler.sendMessage(String.valueOf(finalI), "测试消息" + finalI);
                        log.info("================>当前线程返回:{}", Thread.currentThread().getName());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();

            }
        }
        return "{'code':200,'message':'接口调用成功!'}";
    }

}
