package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 创建一个接口，主要作用是定义一个后端将页面相关数据传回给前端的方法
 */



@Api(value = "cms页面管理接口",description = "cms页面管理接口，提供页面的增、删、改、查")
public interface CmsPageControllerApi {

    /**
     * @param page ------前端传回来的当前页
     * @param size ------前端传回来的每页要显示多少条数据
     * @param queryPageRequest -----前端传回来的参数组合成一个实体类对象
     * @return
     */



    @ApiOperation(value = "分页查询方法")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "page",value = "当前页码",required = true,paramType = "path",dataTypeClass = int.class
            ),
            @ApiImplicitParam(
                    name = "size",value = "每页记录数",required = true,paramType = "path",dataTypeClass = int.class
            )
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);





    @ApiOperation("页面增加")
    public CmsPageResult add(CmsPage cmsPage);





    @ApiOperation("通过ID查询页面")
    public CmsPage findById(String id);


    @ApiOperation("修改页面")
    public CmsPageResult edit(String id,CmsPage cmsPage);




}
