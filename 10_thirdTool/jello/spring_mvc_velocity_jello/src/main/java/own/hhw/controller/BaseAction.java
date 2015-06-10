package own.hhw.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * User: hanwei
 * Date: 15-6-9
 * Time: 上午9:58
 */
public class BaseAction {

    protected String getView(String defaultView, ModelMap modelMap, HttpServletRequest request, HttpServletResponse response) {
        String testFlag = request.getParameter("TEST");
//        LOGGER.info("跳转至" + defaultView + "页面，参数为：" + modelMap);
        if ("1".equals(testFlag)) {
            return getJsonView(modelMap, response);
        } else {
            return defaultView;
        }
    }

    protected String getJsonView(Map jsonMap, HttpServletResponse response) {
        response.setContentType("application/json;charset=GBK");
        JSONObject jsonObject = new JSONObject();
        jsonObject.putAll(jsonMap);
        String json = "";
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            json = jsonObject.toString();
//            LOGGER.info("json结果返回：" + json);
            writer.append(json);
        } catch (IOException e) {
//            LOGGER.error("JSON RETURN ERROR：", e);
            //0.1%的调用失败可能性
            json = "{\"errno\":\"9999\",\"data\":{\"errMsg\":\"请求失败，请稍后重试!\"}}";
            writer.append(json);
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        return null;
    }
}
