package com.dakkk.dkblog.web.markdown;

import com.dakkk.dkblog.web.markdown.provider.NofollowLinkAttributeProvider;
import com.dakkk.dkblog.web.markdown.renderer.ImageNodeRenderer;
import org.commonmark.Extension;
import org.commonmark.ext.gfm.tables.TablesExtension;
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension;
import org.commonmark.ext.image.attributes.ImageAttributesExtension;
import org.commonmark.ext.task.list.items.TaskListItemsExtension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.Arrays;
import java.util.List;

/**
 * ClassName: MarkdownHelper
 * Package: com.dakkk.dkblog.web.markdown
 *
 * @Create 2024/5/18 21:29
 * @Author dakkk
 * Description:
 */
public class MarkdownHelper {
    /**
     * Markdown 解析器
     */
    private static final Parser PARSER;
    /**
     * HTML 渲染器
     */
    public static final HtmlRenderer HTML_RENDERER;

    /**
     * 初始化
     */
    static {
        // Markdown 拓展
        List<Extension> extensions = Arrays.asList(
                // 引入表格拓展
                TablesExtension.create(),
                // 引入标题锚定项
                HeadingAnchorExtension.create(),
                // 引入自定义图片宽高
                ImageAttributesExtension.create(),
                // 任务列表
                TaskListItemsExtension.create()
        );
        PARSER = Parser.builder().extensions(extensions).build();
        HTML_RENDERER = HtmlRenderer.builder()
                .extensions(extensions)
                // 添加nofollow
                .attributeProviderFactory(context -> new NofollowLinkAttributeProvider())
                // 添加图标的描述功能
                .nodeRendererFactory(context -> new ImageNodeRenderer(context))
                .build();
    }

    /**
     * 将 Markdown 转化为 HTML
     */
    public static String convertMarkdown2Html(String markdown) {
        Node document = PARSER.parse(markdown);
        return HTML_RENDERER.render(document);
    }

    public static void main(String[] args) {
        String markdown = "This is *Sparta*";
        // 默认情况下，该库是不支持markdown表格转换的
        String markdown1 = "| First Header  | Second Header |\n" +
                "| ------------- | ------------- |\n" +
                "| Content Cell  | Content Cell  |\n" +
                "| Content Cell  | Content Cell  |";
        // 测试标题是否锚定
        String markdown2 = "# 一级标题\n" +
                "## 二级标题\n";
        // 测试是否能自定义图片宽高
        String markdown3 = "![text](/url.png){width=640 height=480}";
        // 测试是否能转换任务列表
        String markdown4 = "- [ ] task #1\n- [x] task #2";
        // 测试 nofollow 是否生效
        String markdown5 = "[个人网站域名](http://www.blog.dakkk.top \"个人网站域名\")\n" +
                "\n" +
                "[第三方网站域名](http://www.baidu.com \"第三方网站域名\")";
        // 测试图片的描述是否生效
        String markdown6 = "![图 1-1 技术栈](https://img.quanxiaoha.com/quanxiaoha/169560181378937 \"图 1-1 技术栈\"){width=100 height=100}";
        System.out.println(MarkdownHelper.convertMarkdown2Html(markdown6));
    }
}
