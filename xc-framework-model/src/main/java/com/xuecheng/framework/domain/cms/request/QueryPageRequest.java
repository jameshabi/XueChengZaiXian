package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryPageRequest extends RequestData {

    /**
     * 这些都是页面要传回来的参数
     *
     * 站点id----哪个站点
     * 页面id----一个站点多个页面，根据页面id查询一个页面
     * 页面名称----每个页面都有一个名称
     * 别名-----给不同的页面起一个别名，比如首页，轮播图，一看就很容易理解
     * 模板---一个页面有一个模板，要得到模板就需要给出模板id
     *
     *
     *
     * 给出了这些属性，可以不用创建getter/setter方法，直接使用lombok,这个可以自动生成相应的
     * 方法。
     *
     */
    //站点id
    @ApiModelProperty("站点id")
    private String siteId;
    //页面ID
    @ApiModelProperty("页面id")
    private String pageId;
    //页面名称
    @ApiModelProperty("页面名称")
    private String pageName;
    //别名
    @ApiModelProperty("别名")
    private String pageAliase;
    //模版id
    @ApiModelProperty("模板id")
    private String templateId;
}
