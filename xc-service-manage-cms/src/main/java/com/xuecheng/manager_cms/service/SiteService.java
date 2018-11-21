package com.xuecheng.manager_cms.service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manager_cms.dao.CmsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SiteService {
    @Autowired
    private CmsSiteRepository cmsSiteRepository;


    public QueryResponseResult findList(){
        QueryResult queryResult = new QueryResult();

        List<CmsSite> list = cmsSiteRepository.findAll();
        //设置站点列表
        queryResult.setList(list);

        //设置站点总数
        if (list!=null){
            queryResult.setTotal(list.size());
        }
        QueryResponseResult qr = new QueryResponseResult(CommonCode.SUCCESS,queryResult);

        return qr;
    }
}
