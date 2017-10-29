package og.liufeng.weixin.main;

import og.liufeng.course.menu.Button;
import og.liufeng.course.menu.ClickButton;
import og.liufeng.course.menu.ComplexButton;
import og.liufeng.course.menu.Menu;
import og.liufeng.course.pojo.Token;
import og.liufeng.course.util.CommonUtil;
import og.liufeng.course.util.MenuUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuManager {
    private static Logger logger = LoggerFactory.getLogger(MenuManager.class);

    private static Menu getMenu() {
        ClickButton btn11 = new ClickButton();
        btn11.setName("开源中国");
        btn11.setType("click");
        btn11.setKey("oschina");

        ClickButton btn12 = new ClickButton();
        btn12.setName("ITeye");
        btn12.setType("click");
        btn12.setKey("iteye");

        ClickButton btn13 = new ClickButton();
        btn13.setName("CocoaChina");
        btn13.setType("view");
        btn13.setKey("http://www.iteye.com");

        ClickButton btn21 = new ClickButton();
        btn21.setName("淘宝");
        btn21.setType("view");
        btn21.setKey("http://m.taobao.com");

        ClickButton btn22 = new ClickButton();
        btn22.setName("京东");
        btn22.setType("view");
        btn22.setKey("http://m.jd.com");

        ClickButton btn23 = new ClickButton();
        btn23.setName("唯品会");
        btn23.setType("view");
        btn23.setKey("http://m.vipshop.com");

        ClickButton btn24 = new ClickButton();
        btn24.setName("当当网");
        btn24.setType("view");
        btn24.setKey("http://m.dangdang.com");

        ClickButton btn25 = new ClickButton();
        btn25.setName("苏宁易购");
        btn25.setType("view");
        btn25.setKey("http://m.suning.com");

        ClickButton btn31 = new ClickButton();
        btn31.setName("多泡");
        btn31.setType("view");
        btn31.setKey("http://www.duopao.com");

        ClickButton btn32 = new ClickButton();
        btn32.setName("一窝88");
        btn32.setType("view");
        btn32.setKey("http://www.yi588.com");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("技术交流");
        mainBtn1.setSub_button(new Button[] { btn11, btn12, btn13 });

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("购物");
        mainBtn2.setSub_button(new Button[] { btn21, btn22, btn23, btn24, btn25 });

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("网页游戏");
        mainBtn3.setSub_button(new Button[] { btn31, btn32 });

        Menu menu = new Menu();
        menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });

        return menu;
    }

    public static void main(String[] args) {
        String appId = "";
        String appSecret = "";

        Token token = CommonUtil.getToken(appId, appSecret);

        if (null != token) {
            boolean result = MenuUtil.createMenu(getMenu(), token.getAccessToken());

            if (result) {
                logger.info("菜单创建成功!");
            } else {
                logger.info("菜单创建失败!");
            }
        }
    }

}
