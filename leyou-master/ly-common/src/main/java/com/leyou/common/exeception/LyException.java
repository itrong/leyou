package com.leyou.common.exeception;

import com.leyou.common.enums.ExceptionEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author lujianrong
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LyException extends RuntimeException{

    private ExceptionEnum exceptionEnum;

}
