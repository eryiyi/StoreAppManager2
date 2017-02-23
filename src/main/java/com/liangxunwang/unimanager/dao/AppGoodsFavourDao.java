package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.GoodsFavour;
import com.liangxunwang.unimanager.mvc.vo.GoodsFavourVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/10.
 */
@Repository("appGoodsFavourDao")
public interface AppGoodsFavourDao {
    /**
     * ������Ʒ�ղ�
     * @param goodsFavour
     */
    void save(GoodsFavour goodsFavour);
    /**
     * ��ѯĳ����Ʒ�Ƿ��Ѿ��ղع�
     * */
    GoodsFavour isMineFavour(Map<String, Object> map);

    /**
     * ��ѯ�ղ��б�
     * */
    List<GoodsFavourVO> findList(Map<String, Object> map);

    /**
     * �����ղ�UUIDɾ���ղ�
     * */
    void deleteFavourById(String favourId);
 }
