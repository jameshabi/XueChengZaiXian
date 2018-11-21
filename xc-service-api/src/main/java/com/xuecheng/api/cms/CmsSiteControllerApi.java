package com.xuecheng.api.cms;


import com.xuecheng.framework.model.response.QueryResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "CMS站点管理接口",description = "cms站点管理接口，提供站点的增、删、改、查")
public interface CmsSiteControllerApi{

    @ApiOperation(value = "查询站点列表")
    public QueryResponseResult findList();
}
