package com.wjq.component.enums;

import com.wjq.component.exception.CodeRunTimeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public class ReponseEnumTest {


    @Test
    public void codeTest(){
        try {
            ReponseEnum.NULL.assertNotNull(null,1111);
        }catch (CodeRunTimeException e){
            Assertions.assertEquals(ReponseEnum.NULL.getCode(),e.getCode());
        }
    }


    @Test
    public void predCondTest(){
        try {
            ReponseEnum.NULL.assertCond((obj) ->{
                return obj == null;
            },null,"指定判断条件");
        }catch (CodeRunTimeException e){
            Assertions.assertEquals(ReponseEnum.NULL.getCode(),e.getCode());
        }


    }



}