package com.liangxunwang.unimanager.huanxin.comm.httpclient.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.liangxunwang.unimanager.huanxin.comm.HTTPMethod;
import com.liangxunwang.unimanager.huanxin.comm.HxConstants;
import com.liangxunwang.unimanager.huanxin.comm.httpclient.vo.EndPoints;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;

/**
 * Created by 刘先强 on 2015/2/6.
 */
public class ChatUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(ChatUtils.class);
    private static JsonNodeFactory factory = new JsonNodeFactory(false);



    public static boolean register(String username,String pwd){
        ObjectNode datanode = JsonNodeFactory.instance.objectNode();
        datanode.put("username",username);
        datanode.put("password", pwd);
        ObjectNode createNewIMUserSingleNode = createNewIMUserSingle(datanode);
        if (null != createNewIMUserSingleNode) {
            LOGGER.info("注册IM用户[单个]: " + createNewIMUserSingleNode.toString());
            if(createNewIMUserSingleNode.get("statusCode").asInt()==200){
                return true;
            }
        }
        return  false;
    }

    public static boolean del(String username){
        /**
         * 删除IM用户[单个]
         */
        ObjectNode deleteIMUserByUserPrimaryKeyNode = deleteIMUserByUserPrimaryKey(username);
        if (null != deleteIMUserByUserPrimaryKeyNode) {
            LOGGER.info("删除IM用户[单个]: " + deleteIMUserByUserPrimaryKeyNode.toString());
            if(deleteIMUserByUserPrimaryKeyNode.get("statusCode").asInt()==200){
                return true;
            }

        }
        return false;
    }

    /**
     * 注册IM用户[单个]
     *
     * 给指定Constants.APPKEY创建一个新的用户
     *
     * @param dataNode
     * @return
     */
    private static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

        ObjectNode objectNode = factory.objectNode();

        // check Constants.APPKEY format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", HxConstants.APPKEY)) {
            LOGGER.error("Bad format of Constants.APPKEY: " + HxConstants.APPKEY);

            objectNode.put("message", "Bad format of Constants.APPKEY");

            return objectNode;
        }

        objectNode.removeAll();

        // check properties that must be provided
        if (null != dataNode && !dataNode.has("username")) {
            LOGGER.error("Property that named username must be provided .");

            objectNode.put("message", "Property that named username must be provided .");

            return objectNode;
        }
        if (null != dataNode && !dataNode.has("password")) {
            LOGGER.error("Property that named password must be provided .");

            objectNode.put("message", "Property that named password must be provided .");

            return objectNode;
        }

        try {

            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.USERS_URL, MyToken.getCredential(), dataNode,
                    HTTPMethod.METHOD_POST);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }


    /**
     * 删除IM用户[单个]
     *
     * 删除指定Constants.APPKEY下IM单个用户
     *
     *
     * @param userPrimaryKey
     * @return
     */
    public static ObjectNode deleteIMUserByUserPrimaryKey(String userPrimaryKey) {
        ObjectNode objectNode = factory.objectNode();

        // check Constants.APPKEY format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", HxConstants.APPKEY)) {
            LOGGER.error("Bad format of Constants.APPKEY: " + HxConstants.APPKEY);

            objectNode.put("message", "Bad format of Constants.APPKEY");

            return objectNode;
        }

        try {

            URL deleteUserPrimaryUrl = HTTPClientUtils.getURL(HxConstants.APPKEY.replace("#", "/") + "/users/"
                    + userPrimaryKey);
            objectNode = HTTPClientUtils.sendHTTPRequest(deleteUserPrimaryUrl, MyToken.getCredential(), null,
                    HTTPMethod.METHOD_DELETE);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * 重置密码
     * @param hxUserName
     * @param pw
     */
    public static  boolean resetPW(String hxUserName,String pw){
        String username = hxUserName;
        ObjectNode json2 = factory.objectNode();
        json2.put("newpassword", pw);
        ObjectNode modifyIMUserPasswordWithAdminTokenNode = modifyIMUserPasswordWithAdminToken(username, json2);
        if (null != modifyIMUserPasswordWithAdminTokenNode) {
            LOGGER.info("重置IM用户密码 提供管理员token: " + modifyIMUserPasswordWithAdminTokenNode.toString());
        }
        ObjectNode imUserLoginNode2 = imUserLogin(username, json2.get("newpassword").asText());
        if (null != imUserLoginNode2) {
            LOGGER.info("重置IM用户密码后,IM用户登录: " + imUserLoginNode2.toString());
            if(imUserLoginNode2.get("statusCode").asInt()==200){
             return true;
            }
        }
        return false;
    }

    /**
     * 重置IM用户密码 提供管理员token
     *
     * @param userPrimaryKey
     * @param dataObjectNode
     * @return
     */
    public static ObjectNode modifyIMUserPasswordWithAdminToken(String userPrimaryKey, ObjectNode dataObjectNode) {
        ObjectNode objectNode = factory.objectNode();

        // check Constants.APPKEY format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", HxConstants.APPKEY)) {
            LOGGER.error("Bad format of Constants.APPKEY: " + HxConstants.APPKEY);

            objectNode.put("message", "Bad format of Constants.APPKEY");

            return objectNode;
        }

        if (StringUtils.isEmpty(userPrimaryKey)) {
            LOGGER.error("Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");

            objectNode.put("message",
                    "Property that named userPrimaryKey must be provided，the value is username or uuid of imuser.");

            return objectNode;
        }

        if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
            LOGGER.error("Property that named newpassword must be provided .");

            objectNode.put("message", "Property that named newpassword must be provided .");

            return objectNode;
        }

        try {
            URL modifyIMUserPasswordWithAdminTokenUrl = HTTPClientUtils.getURL(HxConstants.APPKEY.replace("#", "/")
                    + "/users/" + userPrimaryKey + "/password");
            objectNode = HTTPClientUtils.sendHTTPRequest(modifyIMUserPasswordWithAdminTokenUrl, MyToken.getCredential(),
                    dataObjectNode, HTTPMethod.METHOD_PUT);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

    /**
     * IM用户登录
     *
     * @param username
     * @param password
     * @return
     */
    public static ObjectNode imUserLogin(String username, String password) {

        ObjectNode objectNode = factory.objectNode();

        // check appKey format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", HxConstants.APPKEY)) {
            LOGGER.error("Bad format of Appkey: " + HxConstants.APPKEY);

            objectNode.put("message", "Bad format of Appkey");

            return objectNode;
        }
        if (StringUtils.isEmpty(username)) {
            LOGGER.error("Your username must be provided，the value is username or uuid of imuser.");

            objectNode.put("message", "Your username must be provided，the value is username or uuid of imuser.");

            return objectNode;
        }
        if (StringUtils.isEmpty(password)) {
            LOGGER.error("Your password must be provided，the value is username or uuid of imuser.");

            objectNode.put("message", "Your password must be provided，the value is username or uuid of imuser.");

            return objectNode;
        }

        try {
            ObjectNode dataNode = factory.objectNode();
            dataNode.put("grant_type", "password");
            dataNode.put("username", username);
            dataNode.put("password", password);

            objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.TOKEN_APP_URL, null, dataNode,
                    HTTPMethod.METHOD_POST);

        } catch (Exception e) {
            throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
        }

        return objectNode;
    }


}
