package com.xuecheng.manager_cms.service;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.manager_cms.dao.CmsPageRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PageService {
    @Autowired
    private CmsPageRepository cmsPageRepository;


    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest){
        /**
         * 如果传进来的参数不符合逻辑，手动修改成正常值
         */

        if (queryPageRequest == null){
            queryPageRequest = new QueryPageRequest();
        }

        if (page <= 0){
            page = 1;
        }
        page = page - 1;
        if (size <= 0){
            size = 10;
        }

        //创建一个pageable对象，创建参数--当前页和每页的大小

        Pageable pageable = PageRequest.of(page,size);
        QueryResult queryResult = new QueryResult();

        //查询出分页后的数据


        //构建一个用于精确匹配和模糊匹配的对象
        CmsPage cmsPage = new CmsPage();

        //自定义一个匹配器，实现页面别名的模糊匹配
        ExampleMatcher exampleMatcher = ExampleMatcher.matching().withMatcher("pageAliase",
                ExampleMatcher.GenericPropertyMatchers.contains());


        //站点ID
        if(StringUtils.isNotEmpty(queryPageRequest.getSiteId())){
            cmsPage.setSiteId(queryPageRequest.getSiteId());
        }
        //页面别名
        if(StringUtils.isNotEmpty(queryPageRequest.getPageAliase())){
            cmsPage.setPageAliase(queryPageRequest.getPageAliase());
        }

        //创建一个条件实例对象，用于比较
        Example<CmsPage> example = Example.of(cmsPage,exampleMatcher);

        Page<CmsPage> pages = cmsPageRepository.findAll(example,pageable);

        //获取数据集
        queryResult.setList(pages.getContent());

        //获取总记录数
        queryResult.setTotal(pages.getTotalElements());
        QueryResponseResult queryResponseResult = new QueryResponseResult(CommonCode.SUCCESS,queryResult);
        return  queryResponseResult;
    }





    public CmsPageResult add(CmsPage cmsPage){
        //先查找是否有相同数据
        CmsPage cmsPage1 = cmsPageRepository.findBySiteIdAndPageNameAndPageWebPath(
            cmsPage.getSiteId(),
            cmsPage.getPageName(),
            cmsPage.getPageWebPath()
        );
        if (cmsPage1==null){
            //如果查询结果为null,说明数据库中没有此条数据，是可以插入的

            //设为null,由数据库自动生成
            //其中也有个好处，因为添加和修改都是save方法，设为null，表示是添加
            cmsPage.setPageId(null);
            cmsPageRepository.save(cmsPage);

            //返回结果
            CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS,cmsPage1);
            return cmsPageResult;
        }
        //如果查询出来有相同的数据，就返回错误信息
        return new CmsPageResult(CommonCode.FAIL,null);
    }



    //根据id查询
    public CmsPage findById(String id){
        Optional<CmsPage> optional = cmsPageRepository.findById(id);
        if (optional.isPresent()){
            return optional.get();
        }
        return null;
    }


    //修改数据
    public CmsPageResult update(String id,CmsPage cmsPage){
        CmsPage cp = this.findById(id);
        if (cp != null){
            //更新模板id
            cmsPage.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsPage.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsPage.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsPage.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsPage.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsPage.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //执行更新
            CmsPage save = cmsPageRepository.save(cmsPage);

            if (save != null){
                //返回成功
                CmsPageResult cmsPageResult = new CmsPageResult(CommonCode.SUCCESS, save);
                return cmsPageResult;
            }
        }
        //返回失败
        return new CmsPageResult(CommonCode.FAIL,null);
    }

}
