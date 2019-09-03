package com.leyou.item.pojo;

import lombok.Data;

import javax.persistence.Id;

/**
 * @author lujianrong
 */
@Data
public class Specification {

    @Id
    private Long categoryId;
    private String specifications;

}
