package reflection;

import reflection.data.BasicData;

import java.lang.reflect.Method;

public class MethodV1 {
    public static void main(String[] args) {
        Class<BasicData> helloClass = BasicData.class;

        System.out.println("========== methods() ===========");
        Method[] methods = helloClass.getMethods();
        for (Method method : methods) {
            System.out.println("method = " + method);
        }

        System.out.println("========== declaredMethods() ===========");
        Method[] methods1 = helloClass.getDeclaredMethods();
        for (Method method : methods1) {
            System.out.println("declaredMethod = " + method);
        }

    }
}
