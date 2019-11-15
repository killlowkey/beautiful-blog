package com.predicated.blog.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author Ray
 * @date created in 2019/11/15 21:53
 */
@Data
@Entity
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 请求 ip
     */
    private String requestIp;

    /**
     * 请求方法
     */
    private String classMethod;

    /**
     * 请求参数
     */
    private String params;

    /**
     * 请求耗时
     */
    private Long time;

    /**
     * 日志描述
     */
    private String description;

    @CreationTimestamp
    private Date createTime;

    public Log(String ip, Long time) {
        this.requestIp = ip;
        this.time = time;
    }
}
