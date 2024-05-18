package com.dakkk.dkblog.web.markdown.provider;

import org.commonmark.node.Link;
import org.commonmark.node.Node;
import org.commonmark.renderer.html.AttributeProvider;

import java.util.Map;

/**
 * ClassName: NofollowLinkAttributeProvider
 * Package: com.dakkk.dkblog.web.markdown.provider
 *
 * @Create 2024/5/18 21:29
 * @Author dakkk
 * Description: 告诉搜索引擎 不要追踪此链接
 * 1. 避免权重分散
 * 2. 提高网站排名
 * 3. 避免垃圾链接
 * 4. 控制页面权重
 */
public class NofollowLinkAttributeProvider implements AttributeProvider {
    /**
     * 网站域名（上线后需要改成自己的域名）
     */
    public static final String DOMAIN = "www.blog.dakkk.top";


    @Override
    public void setAttributes(Node node, String s, Map<String, String> map) {
        if (node instanceof Link){
            Link linkNode = (Link) node;
            // 获取链接地址
            String href = linkNode.getDestination();
            // 如果链接不是自己域名，则添加 rel = “nofollow” 属性
            if (!href.contains(DOMAIN)){
                map.put("rel","nofollow");
            }
        }
    }
}
