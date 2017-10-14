package com.spring.common.model.util.tools;

import javax.script.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * js引擎
 */
public class JavaScriptEngine {

    private static ScriptEngineManager factory = null;
    private static ScriptEngine engine = null;

    /**
     * java js引擎，通过数学公式获得积分
     *
     * @param map
     * @param script
     * @return
     */
    public static BigDecimal getMathFunctionValue(Map<String, Object> map, String script) {
        if (engine == null) {
            factory = new ScriptEngineManager();
            engine = factory.getEngineByName("JavaScript");
        }
        try {
            Compilable compilable = (Compilable) engine;
            Bindings bindings = engine.createBindings(); //Local级别的Binding
            CompiledScript JSFunction = compilable.compile(script); //解析编译脚本函数
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                bindings.put(entry.getKey(), entry.getValue());
            }
            Object result = JSFunction.eval(bindings);
            return new BigDecimal((result == null ? 0 : result).toString());
        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return new BigDecimal(0);
    }
}
