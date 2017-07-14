package gr.avalanche.mongobatch.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Application Utilities Class
 * @author eskiada
 */
public class ApplicationUtils {

    //TODO javadoc
    public static int toInt(String input, int defaultValue){
        try{
            return Integer.parseInt(input);
        } catch(NumberFormatException exception){
            return defaultValue;
        }
    }

    /**
     * Build A Map Containing All HttpServletRequestParameters
     * @param request HttpServletRequest
     * @return Map<String, String[]> Containing The Parameters' Names / Values
     */
    public static Map<String, String[]> buildRequestParameters(HttpServletRequest request){
        Map<String, String[]> result = new LinkedHashMap<String, String[]>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String parameterName = parameterNames.nextElement();
            String[] parameterValues = request.getParameterValues(parameterName);
            result.put(parameterName, parameterValues);
        }
        return result;
    }
}
