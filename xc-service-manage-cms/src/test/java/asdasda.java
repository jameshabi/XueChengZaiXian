import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsPageParam;
import com.xuecheng.manager_cms.ManageCmsApplication;
import com.xuecheng.manager_cms.dao.CmsPageRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ManageCmsApplication.class)
public class asdasda {
    @Autowired
    private CmsPageRepository cmsPageRepository;

    @Test
    public void test(){
        int page=1;
        int size=10;
        Pageable pageable = PageRequest.of(1,10);
        Page<CmsPage> all = cmsPageRepository.findAll(pageable);
        System.out.println(all.getContent());
    }



    @Test
    public void testInsert(){
        CmsPage cmsPage = new CmsPage();
        cmsPage.setSiteId("s01");
        cmsPage.setTemplateId("t01");
        cmsPage.setPageName("测试页面");
        cmsPage.setPageCreateTime(new Date());
        List<CmsPageParam> cmsPageParams = new ArrayList<>();
        CmsPageParam cmsPageParam = new CmsPageParam();
        cmsPageParam.setPageParamName("param1");
        cmsPageParam.setPageParamValue("value1");
        cmsPageParams.add(cmsPageParam);
        cmsPage.setPageParams(cmsPageParams);
        cmsPageRepository.save(cmsPage);
        System.out.println(cmsPage);
    }



    @Test
    public void testUpdate(){
        Optional<CmsPage> optional = cmsPageRepository.findById("5beeb3c194c7752e444f3680");

        /**
         * 这里为什么多此一举呢，其实这是因为在jdk1.8中，考虑到
         * 空指针异常的原因就是没有判断对象，而直接调用方法或者属性，
         * 所以定义了个规范，你用optional之前需要判断option是否为空，也就相当于判断了
         * optional中的内容存在
         */



        if(optional.isPresent()){
            CmsPage cmsPage = optional.get();
            cmsPage.setPageName("测试页面01");
            cmsPageRepository.save(cmsPage);
        }
    }


    @Test
    public void testDelete(){
        cmsPageRepository.deleteById("5beeb3c194c7752e444f3680");
    }
}
