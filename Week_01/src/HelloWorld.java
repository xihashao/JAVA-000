public class HelloWorld {
    public static void main(String[] args) {
        int a= 1;
        String str = "spp";
        char ch = 'a';
        byte b = 0;
        boolean mark = true;
        short s = 6;
        long l = 6;
        double d = 3.3;
        float f = 4.4F;

        if(mark){
            for(int i=0;i<s;i++){
                a++;
            }
            int add = a+1;
            double mul = a*s;
            int sub = a-1;
            int div = a/1;
            System.out.println(add);
            System.out.println(mul);
            System.out.println(sub);
            System.out.println(div);
        }
    }
}
