package com.jd.jr.eco.component.enums;

import com.jd.jr.eco.component.exception.CodeException;
import org.junit.Test;

/**
 * @author wangjianqiang24
 * @date 2020/6/28
 */
public class ReponseEnumTest {


    @Test
    public void codeTest(){
        try {
            ReponseEnum.NULL.assertNotNull(null,1111);
        }catch (CodeException e){
            System.out.println(e.getInfo());
        }

    }




}