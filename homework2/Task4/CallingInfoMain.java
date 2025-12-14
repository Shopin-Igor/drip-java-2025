package Task4;

public class CallingInfoMain {

    public static CallingInfo callingInfo() {
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        StackTraceElement caller = stackTrace[1];
        String className = caller.getClassName();
        String methodName = caller.getMethodName();
        return new CallingInfo(className, methodName);
    }


    public static void main(String[] args) {
        CallingInfo info = callingInfo();
        System.out.println("Class: " + info.className());
        System.out.println("Method: " + info.methodName());
    }
}