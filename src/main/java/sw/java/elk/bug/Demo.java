package sw.java.elk.bug;

import lombok.extern.slf4j.Slf4j;
import org.apache.juli.logging.LogFactory;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Demo
{
    public static void main(String[] args) throws  Exception{
        Connection con = Jsoup
                .connect("http://ams.dfyz.cn.com/");// 获取连接
        con.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");// 配置模拟浏览器
        Connection.Response execute = con.execute();// 获取响应

        Document d1 = Jsoup.parse(execute.body());// 转换为Dom树
        List<Element> et = d1.select("el-form").select("el-form-item").select("el-input");// 获取form表单，可以通过查看页面源码代码得知
        // 获取，cooking和表单属性，下面map存放post时的数据
        System.out.println(et);
        Map<String, String> datas = new HashMap<>();

        et.get(0).attr("value","admin");
        et.get(1).attr("value","123456");
        datas.put( et.get(0).attr("value"),et.get(1).attr("value"));
//            if (e.attr("name").equals("username")) {
//                e.attr("value", "17512009681");// 设置用户名
//            }
//            if (e.attr("name").equals("password")) {
//                e.attr("value", "Un112200"); // 设置用户密码
//            }
//            if (e.attr("name").length() > 0) {// 排除空值表单属性
//                datas.put(e.attr("name"), e.attr("value"));
//            }


//
        Connection con2 = Jsoup
                .connect("http://ams.dfyz.cn.com/");
        con2.header("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:29.0) Gecko/20100101 Firefox/29.0");
        // 设置cookie和post上面的map数据
        Connection.Response login = con2.ignoreContentType(true).method(Connection.Method.POST)
                .data(datas).cookies(execute.cookies()).execute();
        // 打印，登陆成功后的信息
        System.out.println(login.body());
        // 登陆成功后的cookie信息，可以保存到本地，以后登陆时，只需一次登陆即可
        Map<String, String> map = login.cookies();
        for (String s : map.keySet()) {
            System.out.println(s + "      " + map.get(s));
        }
  }
}
