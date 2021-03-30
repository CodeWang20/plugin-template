package com.dhcc.bizmessage.platform;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.junit.Test;
import xyz.downgoon.snowflake.Snowflake;



/**
 * @description: webservice调用测试
 * @author: 罗帅
 * @date: 2020-09-29
 */
@Slf4j
public class CxfClient {
    private static final Snowflake snowFlake = new Snowflake(1, 2);


    private static String xml = "<LIST>" +
            "<GL_ZSGK>" +
            "<GK_BM>1234</GK_BM>" +
            "<GK_ZWQC>测试客户</GK_ZWQC>" +
            "<GK_ZWSC>单位中文俗称</GK_ZWSC>" +
            "<GK_ZWJC>测试</GK_ZWJC>" +
            "<GK_YWQC>单位英文全称</GK_YWQC>" +
            "<GK_YWJC>单位英文简称</GK_YWJC>" +
            "<GK_SZGJ>中国</GK_SZGJ>" +
            "<GK_SZSF>湖南</GK_SZSF>" +
            "<GK_SZCS>长沙</GK_SZCS>" +
            "<GK_SZSS>所在省市</GK_SZSS>" +
            "<GK_ZZJGDM>组织机构代码</GK_ZZJGDM>" +
            "<GK_DWXXDZ>单位详细地址</GK_DWXXDZ>" +
            "<GK_YZBM>411800</GK_YZBM>" +
            "<GK_DWLXDH>10010</GK_DWLXDH>" +
            "<GK_DWMS>单位描述</GK_DWMS>" +
            "<GK_GYSMDLX>黑名单</GK_GYSMDLX>" +
            "<GK_LX>供应商</GK_LX>" +
            "<GK_DATASTATUS>启用</GK_DATASTATUS>" +
            "</GL_ZSGK>" +
            "</LIST>";

    @Test
    public void test1() {
        //创建JaxWsDynamicClientFactory作为调用客户端；
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //下面的是接口wsdl的地址
        String wsUrl = "http://localhost:8080/integration-platform-main/services/ksDataService?wsdl";
        //这是需要调用的方法名字
        String method = "test";
        try {
            //客户端调用wsdl地址
            Client client = dcf.createClient(wsUrl);
            //把webservice接口的方法放入 后面为请求参数 xml格式的 后面的参数看传参是上面类型的，
            //我的是string类型，经过测试，后面其实写什么都行，只要类型一样就行
            Object[] objects = client.invoke(method, "1");
            System.out.println(objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    @Test
    public void test2() {
        //创建JaxWsDynamicClientFactory作为调用客户端；
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        //下面的是接口wsdl的地址
        String wsUrl = "http://localhost:8080/integration-platform-main/services/ksDataService?wsdl";
        //这是需要调用的方法名字
        String method = "receiveData";
        try {
            //客户端调用wsdl地址
            Client client = dcf.createClient(wsUrl);
            //把webservice接口的方法放入 后面为请求参数 xml格式的 后面的参数看传参是上面类型的，
            Object[] objects = client.invoke(method, xml);
            System.out.println(objects[0].toString());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
