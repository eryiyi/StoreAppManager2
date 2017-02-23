package com.liangxunwang.unimanager.huanxin.comm.httpclient.utils;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.liangxunwang.unimanager.huanxin.comm.HTTPMethod;
import com.liangxunwang.unimanager.huanxin.comm.HxConstants;

import java.net.URL;

/**
 * Created by 刘先强 on 2015/2/26.
 */
public class GroupUtils {
//    private static Logger LOGGER = LoggerFactory.getLogger(ChatUtils.class);
    private static JsonNodeFactory factory = new JsonNodeFactory(false);


    public static void addGroup(String groupId,String username){
        String addToChatgroupid = groupId;
		String toAddUsername = username;
		ObjectNode addUserToGroupNode = addUserToGroup(groupId, username);
		System.out.println("aaaaaaaaaaaaa"+addUserToGroupNode.toString());
    }
//    public static void main(String[] args){
//        ObjectNode addUserToGroupNode = addUserToGroup("1424921151301843", "142493584939015563050809");
//        ObjectNode getAllMemberssByGroupIdNode = getAllMemberssByGroupId("1424921151301843");
//        System.out.println(getAllMemberssByGroupIdNode.toString());
//        System.out.println(getAllMemberssByGroupIdNode.get("statusCode").asInt()==200);
//    }

    /**
     * 获取群组中的所有成员
     *
     */
    public static ObjectNode getAllMemberssByGroupId(String chatgroupid) {
        ObjectNode objectNode = factory.objectNode();
        // check appKey format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", HxConstants.APPKEY)) {
//            LOGGER.error("Bad format of Appkey: " + HxConstants.APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }

        try {
            URL allMemberssByGroupIdUrl = HTTPClientUtils.getURL(HxConstants.APPKEY.replace("#", "/") + "/chatgroups/"
                    + chatgroupid + "/users");
            objectNode = HTTPClientUtils.sendHTTPRequest(allMemberssByGroupIdUrl, MyToken.getCredential(), null,
                    HTTPMethod.METHOD_GET);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }


    /**
     * 在群组中添加一个人
     *
     */
    public static ObjectNode addUserToGroup(String chatgroupid, String userprimarykey) {
        ObjectNode objectNode = factory.objectNode();
        // check appKey format
        if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", HxConstants.APPKEY)) {
//            LOGGER.error("Bad format of Appkey: " + HxConstants.APPKEY);
            objectNode.put("message", "Bad format of Appkey");
            return objectNode;
        }

        try {
            URL allMemberssByGroupIdUrl = HTTPClientUtils.getURL(HxConstants.APPKEY.replace("#", "/") + "/chatgroups/" + chatgroupid.trim() + "/users/" + userprimarykey);
            ObjectNode dataobjectNode = factory.objectNode();
            objectNode = HTTPClientUtils.sendHTTPRequest(allMemberssByGroupIdUrl, MyToken.getCredential(), dataobjectNode,
                    HTTPMethod.METHOD_POST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return objectNode;
    }

}
