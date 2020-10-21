import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class c = helloClassLoader.findClass("E:\\workspace\\JAVA-000\\Week_01\\src\\Hello.xlass");
        Object object = c.newInstance();
        Method method = c.getMethod("hello");
        method.invoke(object);
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = null;
        try{
           bytes = getBytes(name);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return defineClass(null,bytes,0,bytes.length);
    }

    private byte[] getBytes(String name) {
        byte[] b = new byte[1];
        try {
            FileInputStream in = new FileInputStream(name);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int length=0;
            while((length = in.read(b))!=-1){
                b[0] = (byte) (255-b[0]);
                outputStream.write(b,0,length);
            }
            outputStream.close();
            b = outputStream.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }
}