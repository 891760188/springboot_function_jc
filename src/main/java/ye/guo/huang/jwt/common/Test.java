package ye.guo.huang.jwt.common;


public class Test {


    /**
     * name : 名字
     * desc : {"a":1,"b":"2我","c":true}
     */

    private String name;
    private Desc desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Desc getDesc() {
        return desc;
    }

    public void setDesc(Desc desc) {
        this.desc = desc;
    }

    public static class Desc {
        /**
         * a : 1
         * b : 2我
         * c : true
         */

        private int a;
        private String b;
        private boolean c;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public boolean isC() {
            return c;
        }

        public void setC(boolean c) {
            this.c = c;
        }
    }
}
