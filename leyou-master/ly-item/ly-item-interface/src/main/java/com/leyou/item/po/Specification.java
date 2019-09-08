package com.leyou.item.po;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author lujianrong
 */
@Data
@Table(name = "tb_specification")
public class Specification {

    @Id
    private Long categoryId;
    private String specifications;

}
