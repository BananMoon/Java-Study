package ch9_일반적인_프로그래밍_원칙.Item65;

import java.lang.reflect.Constructor;

public class PayFactoryReflection {
    /* 아래와 같은 if/else문은 추가 요구사항이 있을 때 마다 변경 필요
    public Object getInstace( String className) {
        if (className.equals("PayCard")) {
            return new PayCard();
        } else if (className.equals("PayCash"))
            return new PayCash();
        return null;
    }*/

    public Object getInstace( String className) throws ReflectiveOperationException {   // 상위 클래스로 에러 처리
        Class<?> aClass = Class.forName(className);
        System.out.println(aClass.getName());
        Constructor<?> constructor = aClass.getConstructor(null);
        PaySystem card = (PaySystem) constructor.newInstance();// casting
        return card;
    }

    public static void main(String[] args) {
        PayFactoryReflection payFactoryReflection = new PayFactoryReflection();
        try {
            PaySystem pay = (PaySystem) payFactoryReflection.getInstace("ch9_일반적인_프로그래밍_원칙.Item65.PayCard");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
        // 결제 로직...
    }
}
